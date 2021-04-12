import edu.princeton.cs.algs4.Queue;
import static org.junit.Assert.*;
import org.junit.Test;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Hello");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("Amaze");
        tas.enqueue("Bloat");
        tas.enqueue("Cats");
        tas.enqueue("Volt");
        tas.enqueue("Zener");
        assertTrue(isSorted(QuickSort.quickSort(tas)));
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Hello");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        tas.enqueue("Amaze");
        tas.enqueue("Bloat");
        tas.enqueue("Cats");
        tas.enqueue("Volt");
        tas.enqueue("Zener");
        assertTrue(isSorted(MergeSort.mergeSort(tas)));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
