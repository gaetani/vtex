import org.junit.Test;

import java.util.Stack;

import static junit.framework.TestCase.assertTrue;

public class DFSTest {


    @Test
    public void testDfs() throws Exception {
        int[][] edges =
                {{0, 1}, {0, 2}, {1, 0}, {2, 0}, {1, 3}, {3, 1}, {2, 4}, {4, 2}};
        Stack<DFS.Vertex<Integer>> path = DFS.dfs(DFS.createGraph(edges), 4);
        assertTrue(!path.empty());


    }
}
