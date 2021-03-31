public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int start;


    /*Creates an empty array deque*/
    public ArrayDeque() {
        size = 0;
        start = 0;
        items = (T[]) new Object[8];
    }

    /*Creates a deep copy of "other"
    public ArrayDeque(ArrayDeque other) {
    }*/
    public static void main(String[] args) {
        System.out.println();
        ArrayDeque<Integer> test = new ArrayDeque<>();
        for (int i = 0; i < 5; i++){
            test.addLast(i);
            test.addFirst(i);
        }
        test.printDeque();
        System.out.println(test.get(0));
        System.out.println(test.get(7));
    }

    /* Adds an item of type T to the front of the deque*/
    public void addFirst(T item) {
        if (size >= items.length) {
            resizeItems(items.length*2);
        }
        items[Math.floorMod((start-1), items.length)] = item;
        start = Math.floorMod((start-1),items.length);
        size = size + 1;
    }

    /* Resizes the items array to a new capacity */
    public void resizeItems(int cap){
        if (cap > items.length) {
            T[] tmp = (T[]) new Object[cap];
            System.arraycopy(items, start, tmp, 0, items.length - start );
            System.arraycopy(items, 0, tmp, items.length - start, start);
            items = tmp;
            start = 0;
        }

    }

    /*Adds an item of type T to the back of the deque*/
    public void addLast(T item) {
        if (size >= items.length) {
            resizeItems(items.length*2);
        }
        items[(start + size) % items.length] = item;
        size = size + 1;
    }

    /*Returns true if deque is empty, false otherwise*/
    public boolean isEmpty() {
        return size == 0;
    }

    /*Returns the number of items in the deque*/
    public int size() {
        return size;
    }

    /*Prints the items in the deque from first to last, separated bu a space.
     Once all the items have been printed, print out a new line*/
    public void printDeque() {
        for(int i = 0; i < size; i++) {
            System.out.print(items[Math.floorMod((start + i), items.length)] + " ");
        }
        System.out.println();
    }

    /*Removes and returns the item at the front of the deque, If no such item exists, return null*/
    public T removeFirst() {
        return null;
    }

    /*Removes and returns the item at the back of the deque, If no such item exists, return null*/
    public T removeLast() {
        return null;
    }

    /*Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    If no such item exists, returns null. Must not alter the deque. */
    public T get(int index) {
        return items[Math.floorMod((start + index), items.length)];
    }

}