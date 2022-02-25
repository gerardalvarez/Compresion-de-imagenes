package com.gerard.longitud;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;


public class Main {

    //CODIFICAR A BINARIO
    public static String encode(String m, Map<String,String> a) {
        String str= "";
        CharacterIterator it = new StringCharacterIterator(m);
        while (it.current() != CharacterIterator.DONE)
        {
            str=str+a.get(String.valueOf(it.current()));
                it.next();
               // System.out.print(it.current());
           if( a.get(String.valueOf(it.current()))==null){
              //System.out.println(it.current());
               break;
           }
        }
        return str;
    }



    //BINARIO A LETRA
    public static String decode(String m, Map<String,String> a) {

        CharacterIterator it = new StringCharacterIterator(m);
        String str="";
        String aux="";
        while (it.current() != CharacterIterator.DONE) {
            aux+= it.current();
            if (a.containsKey(aux)){
                  str+=a.get(aux);
                  aux="";
              }
          it.next();
        }
      return str;
    }

//PASAR A BINARIO CON LONGITUD
    public static String toBinary(int x, int len)
    {
        final char[] buff = new char[len];

        for (int i = len - 1; i >= 0 ; i--)
        {
            int mask = 1 << i;
            buff[len - 1 - i] = (x & mask) != 0 ? '1' : '0';
        }
        return new String(buff);
    }

//CREAR CODIGO A PARTIR DE LAS LONGITUDES
    public static String[] codigoscanonicos(ArrayList<Integer> longitud){

        String[] res= new String[longitud.size()];
        List<Integer> aux = new ArrayList<>();
        longitud.forEach((element) -> aux.add(new Integer(element)));


        Collections.sort(longitud);
          Object[] arr= longitud.toArray();

        int c_code = 0, curr_len = 0, next_len = (int) arr[0];
        c_code = (c_code) << (next_len - curr_len);
        String x=toBinary(c_code,next_len);
        int a=aux.indexOf(next_len);
        res[a]=x;
        aux.set(a,-1);
        for (int i = 0; i < arr.length-1; i++) {


            curr_len = (int)arr[i];

                if (i == arr.length - 1) next_len = curr_len;
                else next_len = (int)arr[i + 1];

                c_code = (c_code + 1) << (next_len - curr_len);
                x=toBinary(c_code,next_len);
                a=aux.indexOf(next_len);
                res[a]=x;
                aux.set(a,-1);

        }

        return res;
    }



        public static void main(String[] args) {
            String s="('-', 8), ('.', 6), (';', 9), ('D', 9), ('F', 9), ('G', 9), ('H', 9), ('I', 7), ('M', 7), ('T', 9), ('W', 9), ('[', 10), (']', 10), ('a', 4), ('b', 7), ('c', 6), ('d', 5), ('e', 3), ('f', 5), ('g', 6), ('h', 5), ('i', 4), ('k', 7), ('l', 5), ('m', 5), ('n', 4), ('o', 4), ('p', 7), ('r', 4), ('s', 5), ('t', 5), ('u', 5), ('v', 6), ('w', 6), ('x', 9), ('y', 5)";
            s= s.replace(" ","");
            s= s.replace("(","");
            s= s.replace(")","");
            s= s.replace("'","");
            s= s.replace(","," ");

            Scanner ss=new Scanner(s);
            Map<String,Integer> dic= new LinkedHashMap<>();
            Map<String,String> m1=new LinkedHashMap<>();
            Map<String,String> m2=new LinkedHashMap<>();
            dic.put(" ",3);
            dic.put("'",9);
            dic.put(",", 6);


            while(ss.hasNext()) dic.put(String.valueOf(ss.next().charAt(0)), ss.nextInt());

            ArrayList<Integer> longitud =new ArrayList<>(dic.values());
            String[] can=codigoscanonicos(longitud);
            String[] keys= dic.keySet().toArray(new String[0]);
            for(int i=0;i< keys.length;++i){
                m1.put(keys[i],can[i]);
                m2.put(can[i],keys[i]);
            }
          // String m= "La heroica ciudad dormía la siesta. El viento Sur, caliente y perezoso, empujaba las nubes blanquecinas que se rasgaban al correr hacia el Norte. En las calles no había más ruido que el rumor estridente de los remolinos de polvo, trapos, pajas y papeles que iban de arroyo en arroyo, de acera en acera, de esquina en esquina revolando y persiguiéndose, como mariposas que se buscan y huyen y que el aire envuelve en sus pliegues invisibles. Cual turbas de pilluelos, aquellas migajas de la basura, aquellas sobras de todo se juntaban en un montón, parábanse como dormidas un momento y brincaban de nuevo sobresaltadas, dispersándose, trepando unas por las paredes hasta los cristales temblorosos de los faroles, otras hasta los carteles de papel mal pegado a las esquinas, y había pluma que llegaba a un tercer piso, y arenilla que se incrustaba para días, o para años, en la vidriera de un escaparate, agarrada a un plomo. Vetusta, la muy noble y leal ciudad, corte en lejano siglo, hacía la digestión del cocido y de la olla podrida, y descansaba oyendo entre sueños el monótono y familiar zumbido de la campana de coro, que retumbaba allá en lo alto de la esbeltatorre en la Santa Basílica. La torre de la catedral, poema romántico de piedra,delicado himno, de dulces líneas de belleza muda y perenne, era obra del siglo diez y seis, aunque antes comenzada, de estilo gótico, pero, cabe decir, moderado por un instinto de prudencia y armonía que modificaba las vulgares exageraciones de esta arquitectura. La vista no se fatigaba contemplando horas y horas aquel índice depiedra que señalaba al cielo; no era una de esas torres.";

            String m= "1111111001010000100011110001011110101010001101001000001111001100011111100001111110001100001111000000111100101111000001010011001110110000111101011010000111111011110111000111111010110111000111111101001101011010110111000111111111011011111011111011111111111110001111001011101101100011100101111011000110001101000001000110100100001111001011110000010100110010001110110011000001000110000111011100011111000011110001100110101010101001000000010001101001000001010110100100101111010010110010110010100101010001100001110001111011001001111110000010110110100001110010010110001100001001010100001011011011111001000001101111011101010111011010111000011110011000101001100110110010001100101110001101000011101100110101101010001001000110011010101100011001011011100011110000001110110111110011010110010000101110010010000111011000100001111001100011000101000010001110001010111001101101011111010000111101001111110100101011000111111010111000100000101001100011001100000110111000111011101000111000111011011110001111011001100100001100010100001000101010100110010110111000101000001100010000010100100100101011011100100010100001010011101001011010111010000110001010010000111110011110011010000011000101000010001100101101001000110001110011000011111101111000101001101100000100011010010000100110101011010010000101101101010111001101011001100001001011111110110101011011100111011000011000100011010010101101110010001010001001000100100001100001110001000001111000010010101101011101100001010001111110110001010000100010010010110011100110011000001100100001001110000111101100001011101111011000100010000010100101011010111010000101001100110110010001100001101001111111011001101010001100010001100110010111001010101101110010001010001111011000100010011100001111011000010100010110111000101100011110001010001000110010111100001001010100010101010011110100111110011000101111111101110000111100000011110000001110110111110011010110010000101100010011100000011000101000010001111110010111111111110111110101111010001010010000001110110111101100100011011111011010111000111010110011010111111000101011000111011000010111101000101100111100011010000100110100111000001110110000101111100010000100011011100100110101110100001010011001101100100011000001010110000010111000101110001011111111000011110001100110101010011000010111101000100011100011001011001100101011011100111011000001000110100100000011110100010110000010110110010011100101010110001000111100000010100010010010000101100011100000001011100000011110100011001101111000001000010101100001011101111011000100011100001011100011010000111101011010111011010011010110111";
            //String s1 = encode(m, m1);
            //String s1 = encode(m, m1);

            String s2= decode(m,m2);

           System.out.println(s2);
           // System.out.println(s2);
           // System.out.println(s1);
        }
}
