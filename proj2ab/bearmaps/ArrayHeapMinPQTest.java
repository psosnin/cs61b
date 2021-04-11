package bearmaps;

import org.junit.Test;

import java.lang.reflect.Array;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class ArrayHeapMinPQTest {

    @Test
    public void testAddAndContains() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ();
        pq.add("a", 1);
        pq.add("b", 2);
        pq.add("c", 3);
        assertTrue(pq.contains("a"));
        assertTrue(pq.contains("b"));
        assertFalse(pq.contains("t"));
        assertEquals(pq.size(), 3);
        boolean thrown = false;
        try {
            pq.add("a", 1);
        } catch (IllegalArgumentException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    public void testGetAndRemoveSmallest() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ();
        pq.add("b", 2);
        pq.add("c", 3);
        pq.add("a", 1);
        pq.add("d", 4);
        assertEquals("a", pq.getSmallest());
        assertEquals("a", pq.removeSmallest());
        assertEquals(pq.size(), 3);
        assertTrue(pq.contains("a"));
        boolean thrown = false;
        try {
            pq.removeSmallest();
            pq.removeSmallest();
            pq.removeSmallest();
            pq.removeSmallest();
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        assertEquals(pq.size(), 0);
        assertTrue(thrown);
    }


    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Test
    public void testChangePriority() {
        ArrayHeapMinPQ<String> pq = new ArrayHeapMinPQ();
        pq.add("b", 2);
        pq.add("c", 3);
        pq.add("a", 10);
        pq.add("d", 4);
        pq.add("r", 4);
        pq.add("p", 4);
        pq.add("q", 4);
        pq.add("s", 4);
        assertEquals("b", pq.getSmallest());
        pq.changePriority("a", 1);
        pq.changePriority("c", 10);
        pq.changePriority("d", 20);
        pq.changePriority("b", 5);
        assertEquals("a", pq.getSmallest());
        boolean thrown = false;
        try {
            pq.changePriority("t", 10);
        } catch (NoSuchElementException e) {
            thrown = true;
        }
        assertTrue(thrown);
    }
}
