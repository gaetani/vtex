import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Vtex
 *
 *  2. Fazer um programa que encontra um caminho a partir de uma origem até o destino navegando entre os nós.
 *  Cada nó é representado por um número inteiro. Cada possibilidade de caminho é representado por um dicionário no formato
 *  (Inteiro -> Lista( Inteiros)), onde a chave é o número do nó, e a lista são as ligações unidirecional daquele nó.
 *  O resultado é uma lista de inteiros representando o caminho pelos nós.

 Exemplo:

 Entrada: origem: 1, destino: 4,

 caminhos: ( 1 -> (2, 3), 2 -> (4), 3->(5) )

 Saída: (1, 2, 4)
 */
public class DFS {


    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        int origin = Integer.parseInt(scan.nextLine());
        int dest = Integer.parseInt(scan.nextLine());
        //List<Integer> list = Pattern.compile(" ").splitAsStream(scan.nextLine()).mapToInt(Integer::parseInt).boxed().collect(Collectors.toList());
        //Simulando os edges
        int[][] edges =
                {{0, 1}, {0, 2}, {1, 0}, {2, 0}, {1, 3}, {3, 1}, {2, 4}, {4, 2}};

        Stack<Vertex<Integer>> path = dfs(createGraph(edges), dest);
        printPath(path);
    }

    private static void printPath(Stack<Vertex<Integer>> path) {
        if(!path.empty()){
            System.out.print("( ");
            for (Vertex<Integer> u : path)
            {
                System.out.print(u.getData() + ", ");
            }
            System.out.println(" )");
        } else {
            System.out.println("No path found");
        }
    }


    public static List<Vertex<Integer>> createGraph(int[][] edges) {
        List<Vertex<Integer>> v = new ArrayList<Vertex<Integer>>();
        for (int i = 1; i <= edges.length; i++) {
            v.add(new Vertex<Integer>(i));
        }

        for (int[] e : edges) {
            v.get(e[0]).successors().add(v.get(e[1]));
        }
        return v;
    }

    public static Stack<Vertex<Integer>> dfs(List<Vertex<Integer>> vertexList, final Integer dest){
        Stack<Vertex<Integer>> path = new Stack<Vertex<Integer>>();
        if (Vertex.depthFirstSearch(vertexList.get(0), new GoalFunction<Integer>() {
            public boolean evaluate(Vertex<Integer> v) {
                return v.getData().equals(dest);
            }
        }, path));

        return path;
    }



    interface GoalFunction<T>
    {
        boolean evaluate(Vertex<T> o);
    }

    static class Vertex<T> {
        private final T data;
        private final List<Vertex<T>> _successors = new ArrayList<Vertex<T>>();

        Vertex(T data) {
            this.data = data;
        }

        T getData() {
            return data;
        }

        List<Vertex<T>> successors() {
            return _successors;
        }

        public static <T> boolean depthFirstSearch(Vertex<T> start,
                                                   GoalFunction<T> isGoal,
                                                   Stack<Vertex<T>> result) {
            if (result.contains(start)) {
                return false;
            }

            result.push(start);

            if (isGoal.evaluate(start)) {
                return true;
            }
            for (Vertex<T> v : start.successors()) {
                if (depthFirstSearch(v, isGoal, result)) {
                    return true;
                }
            }

            // No path was found
            result.pop();
            return false;
        }

    }


}
