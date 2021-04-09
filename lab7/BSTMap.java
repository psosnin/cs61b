import java.util.Set;
import java.util.Iterator;

public class BSTMap<K extends Comparable<K>, V>  implements Map61B<K, V>  {
    int size = 0;
    TreeNode root = null;

    public static void main(String[] args) {
        BSTMap<Integer, String> map = new BSTMap();
        map.put(1, "one");
        map.put(2, "two");
        map.put(6, "six");
        map.put(4, "four");
        map.put(5, "five");
        System.out.println(map.get(4));
        System.out.println(map.get(1));
    }

    private class TreeNode {
        K key;
        V value;
        TreeNode leftChild;
        TreeNode rightChild;

        TreeNode(K k, V v) {
            key = k;
            value = v;
        }
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        root = null;
        size = 0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return null != get(key);
    }

    /** Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key){
        return get(key, root);
    }

    private V get(K key, TreeNode root) {
        if (root == null) {
            return null;
        } else if (key.equals(root.key)) {
            return root.value;
        } else if (key.compareTo(root.key) < 0) {
            return get(key, root.leftChild);
        } else {
            return get(key, root.rightChild);
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size(){
        return size;
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value){
        assert(key != null);
        root = put(root, key, value);
    }

    private TreeNode put(TreeNode root, K key, V value) {
        if (root == null) {
            size = size + 1;
            return new TreeNode(key, value);
        } else if (root.key.compareTo(key) > 0) {
            root.leftChild = put(root.leftChild, key, value);
        } else if (root.key.compareTo(key) < 0) {
            root.rightChild = put(root.rightChild, key, value);
        }
        return root;
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
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

}
