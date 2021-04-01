public interface Deque<T> {
    /* Adds an item of type T to the front of the deque*/
    void addFirst(T item);

    /* Adds an item of type T to the back of the deque*/
    void addLast(T item);

    /* Returns true if the deque is empty, false otherwise*/
    default boolean isEmpty() {
        return size() == 0;
    }

    /* Returns the number of items in the deque*/
    int size();

    /* Prints the items in the deque from first to last, separated by a space.
    Once all the items have been printed, print out a new line */
    void printDeque();

    /*Removes and returns the first item at the front of the deque.
    If no such item exists, return null */
    T removeFirst();

    /*Removes and returns the first item at the back of the deque.
    If no such item exists, return null */
    T removeLast();

    /*Gets the item at the given index, where 0 is the front, 1 is the next item, etc.
    If no such item exists, return null. Does not alter the deque*/
    T get(int index);
}
