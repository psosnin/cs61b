import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;
import java.lang.reflect.Array;

public class MyHashMap<K, V> implements Map61B<K, V> {
    int size = 0;
    HashSet<K> keySet = new HashSet<>();
    Node[] buckets;
    double loadFactor;

    public static void main(String[] args) {
        MyHashMap<Integer, String> map = new MyHashMap(4);
        map.put(10, "ten");
        map.put(9, "nine");
        map.put(26, "twenty six");
        map.put(25, "25");
        map.put(26, "26");
        map.put(0, "zero");


    }

    private class Node {
        K key;
        V value;
        Node next;
        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    public MyHashMap() {
        buckets = (Node[]) Array.newInstance(Node.class, 16);
        loadFactor = 0.75;
    }

    public MyHashMap(int initialSize) {
        buckets = (Node[]) Array.newInstance(Node.class, initialSize);
        loadFactor = 0.75;
    }

    public MyHashMap(int initialSize, double lf) {
        buckets = (Node[]) Array.newInstance(Node.class, initialSize);
        loadFactor = lf;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keySet = new HashSet<>();
        size = 0;
        buckets = (Node[]) Array.newInstance(Node.class, 16);
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), buckets.length);
        return get(key, buckets[index]);
    }

    private V get(K key, Node node) {
        if (node == null) {
            return null;
        } else if (node.key.equals(key)) {
            return node.value;
        } else {
            return get(key, node.next);
        }
    }

    private void resize(int capacity) {
        Node[] tmp = buckets;
        size = 0;
        buckets = (Node[]) Array.newInstance(Node.class, capacity);
        for (int i = 0; i < tmp.length; i++) {
            Node ptr = tmp[i];
            while (ptr != null) {
                put(ptr.key, ptr.value);
                ptr = ptr.next;
            }
        }
    }
    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */
    @Override
    public void put(K key, V value) {
        if ((double) size / buckets.length > loadFactor) {
            resize(buckets.length*2);
        }
        int index = Math.floorMod(key.hashCode(), buckets.length);
        buckets[index] = put(key, value, buckets[index]);
    }

    private Node put(K key, V value, Node node) {
        if (node == null) {
            keySet.add(key);
            size = size + 1;
            return new Node(key, value);
        } else if (node.key == key) {
            node.value = value;
            return node;
        } else {
            node.next = put(key, value, node.next);
            return node;
        }
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
}
