package bearmaps;
import java.util.ArrayList;
import java.util.List;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class ArrayHeap<T> {
    int size;
    List<Node> heap;
    HashSet<T> keys;

    private class Node implements Comparable<Node>{
        T label;
        double priority;

        public Node(T l, double p) {
            label = l;
            priority = p;
        }

        @Override
        public int compareTo(Node node) {
            return (int) (priority - node.priority);
        }
    }

    public ArrayHeap() {
        size = 0;
        keys = new HashSet<>();
        heap = new ArrayList<>();
        heap.add(new Node(null, -1));
    }

    public void delete(T label) {
        if (!keys.contains(label)) {
            throw new NoSuchElementException("Trying to remove item not present in the queue");
        } else {
            keys.remove(label);
            int indexToRemove = find(label);
            swap(size, indexToRemove);
            heap.remove(size);
            size = size - 1;
            heapifyDown(indexToRemove);
        }
    }

    /* finds the index of the node in the heap with the given label */
    private int find(T label) {
        for (int i = 0; i <= size; i++) {
            if (label.equals(item(i).label)) {
                return i;
            }
        }
        return -1;
    }

    /* adds a Node with LABEL and PRIORITY to the heap in the correct place */
    public void add(T label, double priority) {
        if (keys.contains(label)) {
            throw new IllegalArgumentException("Key already present in the queue");
        } else {
            keys.add(label);
            size = size + 1;
            heap.add(size, new Node(label, priority));
            heapifyUp(size);
        }
    }

    /* returns the label of the smallest node in the heap*/
    public T getSmallestLabel() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap.get(1).label;
    }

    /* removes and returns the label of the smallest node in the heap*/
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        Node smallest = heap.get(1);
        heap.set(1, heap.get(size));
        size = size - 1;
        heapifyDown(1);
        return smallest.label;
    }

    /* swaps the node at INDEX with its children until it reaches its rightful place. */
    private void heapifyDown(int index) {
        while (index*2 <= size) {
            if (leftChild(index).compareTo(item(index)) < 0) {
                swap(index, leftChildIndex(index));
                index = leftChildIndex(index);
            } else if (leftChild(index).compareTo(item(index)) < 0) {
                swap(index, rightChildIndex(index));
                index = rightChildIndex(index);
            } else {
                return;
            }
        }
    }

    /* swaps the item at INDEX with its parent until it reaches its rightful place */
    private void heapifyUp(int index) {
        while (index > 0) {
            if (item(index).compareTo(parent(index)) < 0) {
                swap(index, parentIndex(index));
                index = parentIndex(index);
            } else {
                return;
            }
        }
    }

    /* swaps the nodes at index i and index j */
    private void swap(int i, int j) {
        Node tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /* returns the node at index i */
    private Node item(int i) {
        return heap.get(i);
    }
    /* returns the left child node of the element at index i */
    private Node leftChild(int i) {
        return heap.get(leftChildIndex(i));
    }

    /* returns the right child node of the element at index i */
    private Node rightChild(int i) {
        return heap.get(rightChildIndex(i));
    }

    /* returns the index of the parent of the node at index i. */
    private Node parent(int i) {
        return heap.get(parentIndex(i));
    }

    /* returns the index of the left child of the node at index i */
    private int leftChildIndex(int i) {
        return i * 2;
    }

    /* returns the index of the right child of the node at index i */
    private int rightChildIndex(int i) {
        return i * 2 + 1;
    }

    /* returns the index of the parent of the node at index i.
    * the root item returns 0 as its parent.*/
    private int parentIndex(int i) {
        return i / 2;
    }

    public boolean contains(T i) {
        return keys.contains(i);
    }

    public int size() {
        return size;
    }

}
