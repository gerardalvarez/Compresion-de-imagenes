package com.gerard.freq;

// Java Program for Canonical Huffman Encoding

import java.io.*;
import java.util.*;

import static com.gerard.huff.Main.tabla;
import static com.gerard.longitud.Main.toBinary;

// Nodes of Huffman tree
class Node {

    int data;
    String c;

    Node left;
    Node right;
}

// comparator class helps to compare the node
// on the basis of one of its attribute.
// Here we will be compared
// on the basis of data values of the nodes.
class Pq_compare implements Comparator<Node> {
    public int compare(Node a, Node b)
    {

        return a.data - b.data;
    }
}

class Canonical_Huffman {

    // Treemap to store the
    // code lengths(sorted) as keys
    // and corresponding(sorted)
    // set of characters as values
    static TreeMap<Integer, TreeSet<String> > data;

    // Constructor to initialize the Treemap
    public Canonical_Huffman()
    {
        data = new TreeMap<Integer, TreeSet<String> >();
    }

    // Recursive function
    // to generate code lengths
    // from regular Huffman codes
    static void code_gen(Node root, int code_length)
    {
        if (root == null)
            return;

        // base case; if the left and right are null
        // then its a leaf node.
        if (root.left == null && root.right == null) {

            // check if key is present or not.
            // If not present add a new treeset
            // as value along with the key
            data.putIfAbsent(code_length, new TreeSet<String>());

            // c is the character in the node
            data.get(code_length).add(root.c);
            return;
        }

        // Add 1 when on going left or right.
        code_gen(root.left, code_length + 1);
        code_gen(root.right, code_length + 1);
    }

    static void testCanonicalHC(int n, String[] chararr, int[] freq, SortedMap<String, String> m1)
    {

        // min-priority queue(min-heap).
        PriorityQueue<Node> q
                = new PriorityQueue<Node>(n, new Pq_compare());

        for (int i = 0; i < n; i++) {

            // creating a node object
            // and adding it to the priority-queue.
            Node node = new Node();

            node.c = chararr[i];
            node.data = freq[i];

            node.left = null;
            node.right = null;

            // add functions adds
            // the node to the queue.
            q.add(node);
        }

        // create a root node
        Node root = null;

        // extract the two minimum value
        // from the heap each time until
        // its size reduces to 1, extract until
        // all the nodes are extracted.
        while (q.size() > 1) {

            // first min extract.
            Node x = q.peek();
            q.poll();

            // second min extract.
            Node y = q.peek();
            q.poll();

            // new node f which is equal
            Node nodeobj = new Node();

            // to the sum of the frequency of the two nodes
            // assigning values to the f node.
            nodeobj.data = x.data + y.data;
            nodeobj.c = "-";

            // first extracted node as left child.
            nodeobj.left = x;

            // second extracted node as the right child.
            nodeobj.right = y;

            // marking the f node as the root node.
            root = nodeobj;

            // add this node to the priority-queue.
            q.add(nodeobj);
        }

        // Creating a canonical Huffman object
        Canonical_Huffman obj = new Canonical_Huffman();

        // generate code lengths by traversing the tree
        code_gen(root, 0);

        // Object array to store the keys
        Object[] arr = data.keySet().toArray();

        // Set initial canonical code=0
        int c_code = 0, curr_len = 0, next_len = 0;

        for (int i = 0; i < arr.length; i++) {
            Iterator it = data.get(arr[i]).iterator();

            // code length of current character
            curr_len = (int)arr[i];

            while (it.hasNext()) {

                // Display the canonical codes
//                System.out.println(it.next() + ":"
//                        + toBinary(c_code,curr_len));
                m1.put(it.next().toString(),toBinary(c_code,curr_len));

                // if values set is not
                // completed or if it is
                // the last set set code length
                // of next character as current
                // code length
                if (it.hasNext() || i == arr.length - 1)
                    next_len = curr_len;
                else
                    next_len = (int)arr[i + 1];

                // Generate canonical code
                // for next character using
                // regular code length of next
                // character
                c_code = (c_code + 1) << (next_len - curr_len);
            }
        }
    }

    private static void intro(Map<String, Integer> a, String[] arr1, int[] arr2) {

        Set entries = a.entrySet();
        Iterator entriesIterator = entries.iterator();
        int i = 0;
        while(entriesIterator.hasNext()){
            Map.Entry mapping = (Map.Entry) entriesIterator.next();
            arr1[i] = mapping.getKey().toString();
            arr2[i] = (int)mapping.getValue();
            i++;
        }
    }


    public static void main(String args[]) throws IOException
    {
        String m="The Island of Doctor Moreau, by H. G. Wells. [...] The Leopard-man had happened to go under: that was all the difference. Poor brute! Poor brutes! I began to see the viler aspect of Moreau's cruelty. I had not thought before of the pain and trouble that came to these poor victims after they had passed from Moreau's hands. I had shivered only at the days of actual torment in the enclosure. But now that seemed to me the lesser part. Before, they had been beasts, their instincts fitly adapted to their surroundings, and happy as living things may be. Now they stumbled in the shackles of humanity, lived in a fear that never died, fretted by a law they could not understand; their mock-human existence, begun in an agony, was one long internal struggle, one long dread of Moreau--and for what? It was the wantonness of it that stirred me.";
        Map<String,Integer> a= tabla(m,1);


        String[] chararr = { "a", "b", "c", "d" ,"e"};
        String[] arr1 = new String[a.size()];
        int[] arr2 = new int[a.size()];
        intro(a,arr1,arr2);
        SortedMap<String,String> m1=new TreeMap<>();
       int[] freq = { 40,680,40,200 ,640};
      testCanonicalHC(a.size(),arr1,arr2,m1);
        System.out.println(m1);
    }


}