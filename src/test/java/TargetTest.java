import junit.framework.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by fgaetani on 1/18/16.
 */
public class TargetTest {

    private static final String OUT = "[6]\n[2, 4]\n[1, 2, 3]\n";
    private static final int TAMANHO_LISTA = 32;

    public void testMain() throws Exception {




    }

    @Test
    public void testTargetByPowerSet() throws Exception {

        String powerSet = Target.targetByPowerSet(Arrays.asList(1, 2, 3, 4, 6), 6);

        assertEquals(OUT, powerSet);

    }

    @Test
    public void testPowerset() throws Exception {
        List< Integer> numbers = Arrays.asList(1, 2, 3, 4, 6);
        List<List<Integer>> powerSet = Target.powerset(numbers);

        assertEquals(TAMANHO_LISTA, powerSet.size());

    }
}
