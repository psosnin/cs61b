package bearmaps;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T>{
    private int size;
    private ArrayHeap<T> heap;

    public ArrayHeapMinPQ() {
        size = 0;
        heap = new ArrayHeap();
    }

    /* Adds an item with the given priority value. Throws an
     * IllegalArgumentException if item is already present.
     * You may assume that item is never null. */
    public void add(T item, double priority) {
        heap.add(item, priority);
    }
    /* Returns true if the PQ contains the given item. */
    public boolean contains(T item) {
        return heap.contains(item);
    }
    /* Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T getSmallest() {
        return heap.getSmallestLabel();
    }
    /* Removes and returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    public T removeSmallest() {
        return heap.removeSmallest();
    }
    /* Returns the number of items in the PQ. */
    public int size() {
        return heap.size;
    }
    /* Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    public void changePriority(T item, double priority) {
        heap.delete(item);
        heap.add(item, priority);
    }
}
