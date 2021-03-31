public class LinkedListDeque<T> {
    private int size;
    private DNode sentinel;

    private class DNode {
        private T item;
        private DNode next;
        private DNode prev;

        /*Creates an empty DNode*/
        DNode() { }

        /*Creates a DNode*/
        DNode(T i, DNode n, DNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    /*Creates an empty linked list deque*/
    public LinkedListDeque() {
        size = 0;
        sentinel = new DNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public static void main(String[] args) {
        LinkedListDeque test = new LinkedListDeque();
        System.out.println(test.isEmpty());
        test.addFirst(10);
        test.addFirst(8);
        test.addLast(12);
        test.printDeque();
        LinkedListDeque copy = new LinkedListDeque(test);
        copy.printDeque();
    }

    /*Creates a deep copy of "other"*/
    public LinkedListDeque(LinkedListDeque other) {
        size = other.size();
        sentinel = new DNode();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        while (!other.isEmpty()) {
            addLast((T) other.removeFirst());
        }
    }

    /* Adds an item of type T to the front of the deque*/
    public void addFirst(T item) {
        size = size + 1;
        sentinel.next.prev = new DNode(item, sentinel.next, sentinel);
        sentinel.next = sentinel.next.prev;
    }

    /*Adds an item of type T to the back of the deque*/
    public void addLast(T item) {
        size = size + 1;
        sentinel.prev.next = new DNode(item, sentinel, sentinel.prev);
        sentinel.prev = sentinel.prev.next;
    }

    /*Returns true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        if (sentinel.next == sentinel && sentinel.prev == sentinel) {
            return true;
        }
        return false;
    }

    /*Returns the number of items in the deque*/
    public int size() {
        return size;
    }

    /*Prints the items in the deque from first to last, separated bu a space.
     Once all the items have been printed, print out a new line*/
    public void printDeque() {
        DNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.print(ptr.item + " ");
            ptr = ptr.next;
        }
        System.out.println();
    }

    /*Removes and returns the item at the front of the deque, If no such item exists, return null*/
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        size = size - 1;
        T item = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return item;
    }

    /*Removes and returns the item at the back of the deque, If no such item exists, return null*/
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        size = size - 1;
        T item = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return item;
    }

    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque. */
    public T get(int index) {
        if (index < size) {
            DNode ptr = sentinel.next;
            while (index > 0) {
                ptr = ptr.next;
                index = index - 1;
            }
            return ptr.item;
        }
        return null;
    }

    /*Same as get, but uses recursion*/
    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        } else {
            return getRecursiveHelper(sentinel.next, index);
        }
    }

    private T getRecursiveHelper(DNode start, int index) {
        if (index == 0) {
            return start.item;
        } else {
            return getRecursiveHelper(start.next, index - 1);
        }
    }
}
