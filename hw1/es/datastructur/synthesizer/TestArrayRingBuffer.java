package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer(10);
        arb.enqueue(10);
        arb.enqueue(13);
        arb.enqueue(12);
        assertEquals(arb.dequeue(), 10);
        assertEquals(arb.peek(), 13);
        assertEquals(arb.dequeue(), 13);
        assertEquals(arb.fillCount(), 1);
        assertFalse(arb.isFull());
        assertFalse(arb.isEmpty());
        assertEquals(arb.dequeue(), 12);
        assertTrue(arb.isEmpty());
    }
}
