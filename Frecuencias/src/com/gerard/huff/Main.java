package com.gerard.huff;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.*;


public class Main {


    public double Long(String c, ArrayList<String> a){


        return 0;
    }

    public static Map<String,Integer> tabla(String m,int num){
        SortedMap<String,Integer> res=new TreeMap<>();

        for(int i=0;i<m.length();i=i+num){
            String aux="";
            for(int j=0; j<num;++j){
                aux+=m.charAt(j+i);
            }
            int count = res.containsKey(aux) ? res.get(aux) : 0;
            res.put(aux, count + 1);
        }

        return res;
    }

    public static void main(String[] args) {

        String s ="La heroica ciudad dormía la siesta. El viento Sur, caliente y " +
                "perezoso, empujaba las nubes blanquecinas que se rasgaban al correr hacia el " +
                "Norte. En las calles no había más ruido que el rumor estridente de los " +
                "remolinos de polvo, trapos, pajas y papeles que iban de arroyo en arroyo, de " +
                "acera en acera, de esquina en esquina revolando y persiguiéndose, como " +
                "mariposas que se buscan y huyen y que el aire envuelve en sus pliegues " +
                "invisibles. Cual turbas de pilluelos, aquellas migajas de la basura, " +
                "aquellas sobras de todo se juntaban en un montón, parábanse como dormidas un " +
                "momento y brincaban de nuevo sobresaltadas, dispersándose, trepando unas por " +
                "las paredes hasta los cristales temblorosos de los faroles, otras hasta los " +
                "carteles de papel mal pegado a las esquinas, y había pluma que llegaba a un " +
                "tercer piso, y arenilla que se incrustaba para días, o para años, en la " +
                "vidriera de un escaparate, agarrada a un plomo. Vetusta, la muy noble y leal " +
                "ciudad, corte en lejano siglo, hacía la digestión del cocido y de la olla " +
                "podrida, y descansaba oyendo entre sueños el monótono y familiar zumbido de " +
                "la campana de coro, que retumbaba allá en lo alto de la esbeltatorre en la " +
                "Santa Basílica. La torre de la catedral, poema romántico de piedra,delicado " +
                "himno, de dulces líneas de belleza muda y perenne, era obra del siglo diez y " +
                "seis, aunque antes comenzada, de estilo gótico, pero, cabe decir, moderado " +
                "por un instinto de prudencia y armonía que modificaba las vulgares " +
                "exageraciones de esta arquitectura. La vista no se fatigaba contemplando " +
                "horas y horas aquel índice depiedra que señalaba al cielo; no era una de " +
                "esas torres cuya aguja se quiebra desutil, más flacas que esbeltas, " +
                "amaneradas, como señoritas cursis que aprietandemasiado el corsé; era maciza " +
                "sin perder nada de su espiritual grandeza, y hasta sussegundos corredores, " +
                "elegante balaustrada, subía como fuerte castillo, lanzándosedesde allí en " +
                "pirámide de ángulo gracioso, inimitable en sus medidas y proporciones.Como " +
                "haz de músculos y nervios la piedra enroscándose en la piedra trepaba a la " +
                "altura, haciendo equilibrios de acróbata en el aire; y como prodigio de " +
                "juegosmalabares, en una punta de caliza se mantenía, cual imantada, una bola " +
                "grande debronce dorado, y encima otra más pequeña, y sobre ésta una cruz de " +
                "hierro que acababa en pararrayos. ";




        Map<String,Integer> a;

        a=tabla(s,1);

        System.out.println(a);
    }
}
