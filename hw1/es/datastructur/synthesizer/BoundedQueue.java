package es.datastructur.synthesizer;
public interface BoundedQueue<T> {

    /* return the size of the buffer */
    int capacity();

    /* return the number of items currently in the buffer */
    int fillCount();

    /* add item x to the end of the queue */
    void enqueue(T x);

    /* delete and return the item from the front of the queue */
    T dequeue();

    /* return (but not delete) the item from the front of the queue */
    T peek();

    /* return true if the buffer is empty */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* return true if the buffer is full */
    default boolean isFull() {
        return fillCount() == capacity();
    }

}
