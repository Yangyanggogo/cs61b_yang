package synthesizer;
import org.junit.Test;


import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(4);
        arb.enqueue(10);
        arb.enqueue(9);
        arb.enqueue(8);
        arb.enqueue(7);
        arb.dequeue();
        assertEquals(Integer.valueOf(9), arb.peek());
        arb.dequeue();
        assertEquals(Integer.valueOf(8), arb.peek());
        assertEquals(Integer.valueOf(2), Integer.valueOf(arb.fillCount));

    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
