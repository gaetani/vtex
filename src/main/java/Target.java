import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Simple test with recursion.
 *
 * This is a challenge proposed by Vtex
 *
 *Testes

 1. Escreva um programa que, dado uma lista de números e uma soma alvo, retorne todas as combinações de um ou mais itens da lista que a soma seja igual a soma alvo.
 Exemplo:

 Entrada: Para soma alvo 6 de lista (1, 2, 3, 4, 6) o resultado é:

 Saida: (1, 2, 3)

 (2, 4)

 (6)

 */
public class Target {

    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int target = Integer.parseInt(scan.nextLine());
        List<Integer> list = Pattern.compile(" ").splitAsStream(scan.nextLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        String out = targetByPowerSet(list, target);
        System.out.println(out);
    }


    /**
     * Algoritmo utilizado para a busca do alvo.
     *
     * @param numbers
     * @param target
     * @return
     */
    public static String targetByPowerSet(List<Integer> numbers, final int target){

        List<List<Integer>> powerset = powerset(numbers);

        //Depois de montado os sets, varre a lista a procura dos invertebrados.
        StringBuilder out = new StringBuilder();
        powerset.stream().filter(integers -> integers.stream()
                    .reduce(Integer::sum).orElse(0) == target).iterator()
                    .forEachRemaining(ins -> {
                        out.append(ins);
                        out.append('\n');
                    });

        return out.toString();
    }

    /**
     * Exemplo de power set em http://rosettacode.org/wiki/Power_set#Recursion
     * @param list
     * @param <T>
     *
     * @return
     */
    public static <T> List<List<T>> powerset(List<T> list) {
        List<List<T>> ps = new ArrayList<List<T>>();
       ps.add(new ArrayList<T>());   // add the empty set

        // for every item in the original list
        for (T item : list) {
            List<List<T>> newPs = new ArrayList<List<T>>();


            for (List<T> subset : ps) {
                // copy all of the current powerset's subsets
                newPs.add(subset);

                // plus the subsets appended with the current item
                List<T> newSubset = new ArrayList<T>(subset);
                newSubset.add(item);
                newPs.add(newSubset);

            }

            // powerset is now powerset of list.subList(0, list.indexOf(item)+1)
            ps = newPs;
        }
        return ps;
    }

}
