package com.gerard.longitud;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;

import static com.gerard.huff.Main.tabla;


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

            String s="['.', 8], [';', 9], ['B', 10], ['C', 10], ['E', 10], ['L', 9], ['N', 11], ['S', 10], ['V', 11], ['a', 3], ['b',6], ['c', 5], ['d', 5], ['e', 3], ['f', 8], ['g', 7], ['h', 7], ['i', 5], ['j', 8], ['l', 4], ['m', 6], ['n', 4], ['o', 4], ['p', 6], ['q', 6], ['r', 4], ['s', 4], ['t', 5], ['u', 5], ['v', 8], ['x', 11], ['y', 6], ['z', 8], ['??', 8], ['??', 9], ['??', 7], ['??', 9], ['??', 9], ['??', 11]";

            s= s.replace(" ","");
            s= s.replace("[","");
            s= s.replace("]","");
            s= s.replace("'","");
            s= s.replace(","," ");

            Scanner ss=new Scanner(s);
            Map<String,Integer> dic= new LinkedHashMap<>();
            Map<String,String> m1=new LinkedHashMap<>();
            Map<String,String> m2=new LinkedHashMap<>();
            dic.put(" ",3);
            dic.put(",", 6);


            while(ss.hasNext()) dic.put(String.valueOf(ss.next().charAt(0)), ss.nextInt());

            ArrayList<Integer> longitud =new ArrayList<>(dic.values());
            String[] can=codigoscanonicos(longitud);
            String[] keys= dic.keySet().toArray(new String[0]);
            for(int i=0;i< keys.length;++i){
                m1.put(keys[i],can[i]);
                m2.put(can[i],keys[i]);
            }

//            String m= "1111111001010000100011110001011110101010001101001000001111001" +
//                    "10001111110000111111000110000111100000011110010111100000101001100" +
//                    "111011000011110101101000011111101111011100011111101011011100011111" +
//                    "11010011010110101101110001111111110110111110111110111111111111100" +
//                    "011110010111011011000111001011110110001100011010000010001101001000" +
//                    "011110010111100000101001100100011101100110000010001100001110111000" +
//                    "1111100001111000110011010101010100100000001000110100100000101011010" +
//                    "01001011110100101100101100101001010100011000011100011110110010011111" +
//                    "10000010110110100001110010010110001100001001010100001011011011111001" +
//                    "00000110111101110101011101101011100001111001100010100110011011001000" +
//                    "110010111000110100001110110011010110101000100100011001101010110001100" +
//                    "10110111000111100000011101101111100110101100100001011100100100001110" +
//                    "110001000011110011000110001010000100011100010101110011011010111110100" +
//                    "00111101001111110100101011000111111010111000100000101001100011001100" +
//                    "000110111000111011101000111000111011011110001111011001100100001100010" +
//                    "100001000101010100110010110111000101000001100010000010100100100101011" +
//                    "01110010001010000101001110100101101011101000011000101001000011111001111" +
//                    "00110100000110001010000100011001011010010001100011100110000111111011110" +
//                    "001010011011000001000110100100001001101010110100100001011011010101110011" +
//                    "0101100110000100101111111011010101101110011101100001100010001101001010110" +
//                    "1110010001010001001000100100001100001110001000001111000010010101101011101" +
//                    "100001010001111110110001010000100010010010110011100110011000001100100001001110000111101100001011" +
//                    "1011110110001000100000101001010110101110100001010011001101100100011000011010011111110110011010100" +
//                    "0110001000110011001011100101010110111001000101000111101100010001001110000111101100001010001011011" +
//                    "1000101100011110001010001000110010111100001001010100010101010011110100111110011000101111111101110" +
//                    "00011110000001111000000111011011111001101011001000010110001001110000001100010100001000111111001011" +
//                    "11111111101111101011110100010100100000011101101111011001000110111110110101110001110101100110101111" +
//                    "11000101011000111011000010111101000101100111100011010000100110100111000001110110000101111100010000" +
//                    "10001101110010011010111010000101001100110110010001100000101011000001011100010111000101111111100001" +
//                    "111000110011010101001100001011110100010001110001100101100110010101101110011101100000100011010010000" +
//                    "001111010001011000001011011001001110010101011000100011110000001010001001001000010110001110000000101" +
//                    "11000000111101000110011011110000010000101011000010111011110110001000111000010111000110100001111010" +
//                    "11010111011010011010110111";
            String m="La heroica ciudad dorm??a la siesta. El viento Sur, caliente y" +
                    ", perezoso, empujaba las nubes blanquecinas que se rasgaban al correr hacia el" +
                    ", Norte. En las calles no hab??a m??s ruido que el rumor estridente de los" +
                    ", remolinos de polvo, trapos, pajas y papeles que iban de arroyo en arroyo, de" +
                    ", acera en acera, de esquina en esquina revolando y persigui??ndose, como" +
                    ", mariposas que se buscan y huyen y que el aire envuelve en sus pliegues" +
                    ", invisibles. Cual turbas de pilluelos, aquellas migajas de la basura," +
                    ", aquellas sobras de todo se juntaban en un mont??n, par??banse como dormidas un" +
                    ", momento y brincaban de nuevo sobresaltadas, dispers??ndose, trepando unas por" +
                    ", las paredes hasta los cristales temblorosos de los faroles, otras hasta los" +
                    ", carteles de papel mal pegado a las esquinas, y hab??a pluma que llegaba a un" +
                    ", tercer piso, y arenilla que se incrustaba para d??as, o para a??os, en la" +
                    ", vidriera de un escaparate, agarrada a un plomo. Vetusta, la muy noble y leal" +
                    ", ciudad, corte en lejano siglo, hac??a la digesti??n del cocido y de la olla" +
                    ", podrida, y descansaba oyendo entre sue??os el mon??tono y familiar zumbido de" +
                    ", la campana de coro, que retumbaba all?? en lo alto de la esbeltatorre en la" +
                    ", Santa Bas??lica. La torre de la catedral, poema rom??ntico de piedra,delicado" +
                    ", himno, de dulces l??neas de belleza muda y perenne, era obra del siglo diez y" +
                    ", seis, aunque antes comenzada, de estilo g??tico, pero, cabe decir, moderado" +
                    ", por un instinto de prudencia y armon??a que modificaba las vulgares" +
                    ", exageraciones de esta arquitectura. La vista no se fatigaba contemplando" +
                    ", horas y horas aquel ??ndice depiedra que se??alaba al cielo; no era una de" +
                    ", esas torres cuya aguja se quiebra desutil, m??s flacas que esbeltas," +
                    ", amaneradas, como se??oritas cursis que aprietandemasiado el cors??; era maciza" +
                    ", sin perder nada de su espiritual grandeza, y hasta sussegundos corredores," +
                    ", elegante balaustrada, sub??a como fuerte castillo, lanz??ndosedesde all?? en" +
                    ", pir??mide de ??ngulo gracioso, inimitable en sus medidas y proporciones.Como" +
                    ", haz de m??sculos y nervios la piedra enrosc??ndose en la piedra trepaba a la" +
                    ", altura, haciendo equilibrios de acr??bata en el aire; y como prodigio de" +
                    ", juegosmalabares, en una punta de caliza se manten??a, cual imantada, una bola" +
                    ", grande debronce dorado, y encima otra m??s peque??a, y sobre ??sta una cruz de" +
                    ", hierro que acababa en pararrayos.";

            Map<String,Integer> a;

            a=tabla(m,1);
          String s2= encode(m,m1);

            System.out.println(a);

        }
}
