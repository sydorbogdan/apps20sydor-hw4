package ua.edu.ucu.autocomplete;

import immutable.Queue;
import org.junit.Test;
import immutable.ImmutableLinkedList;

import static org.junit.Assert.*;

public class QueueTest {
    @Test
    public void testQueue() {
        Queue testQueue = new Queue();
        for (int i = 1; i < 5; i++) {
            testQueue.enqueue(i);
        }
        for (int i = 1; i < 5; i++) {
            assertEquals(i, testQueue.dequeue());
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDequeueException() {
        Queue testQueue = new Queue();
        testQueue.dequeue();
    }

}
