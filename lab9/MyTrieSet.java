import java.util.*;

public class MyTrieSet implements TrieSet61B {


    public static void main(String[] args) {
        MyTrieSet trie = new MyTrieSet();
        trie.add("thirty");
        trie.add("thirteen");
        trie.add("ten");
        trie.add("fifteen");
        System.out.println(trie.contains("hello"));
        System.out.println(trie.contains("thirty"));
        trie.keysWithPrefix("thir");
        System.out.println(trie.keysWithPrefix("thir"));
    }

    Node root;

    private class Node {
        private Character label;
        private HashMap<Character, Node> children;
        private boolean keyNode;

        Node(Character k) {
            label = k;
            children = new HashMap<>();
            keyNode = false;
        }

        Node(Character k , boolean b) {
            label = k;
            children = new HashMap<>();
            keyNode = b;
        }

        /* adds a child to the node */
        public void add(Node node) {
            children.put(node.label, node);
        }

        /* returns true if the node has a child with label k */
        public boolean hasChild(Character k) {
            return !(get(k) == null);
        }

        /* returns the child node with the label k if it exists, null otherwise */
        public Node get(Character k) {
            return children.get(k);
        }

        /* returns the label of the node */
        public Character label() {
            return label;
        }

        /* returns true if the node is a key node */
        public boolean keyNode() {
            return keyNode;
        }

        /* sets the node to be a key node */
        public void setKeyNode() {
            keyNode = true;
        }

        /* returns a collection of all the child nodes */
        public Collection<Node> children() {
            return children.values();
        }
    }

    MyTrieSet() {
        root = new Node(null);
    }

    /** Clears all items out of Trie */
    @Override
    public void clear() {
        root = new Node(null);
    }

    /** Returns true if the Trie contains KEY, false otherwise */
    @Override
    public boolean contains(String key) {
        Node ptr = root;
        for (int i = 0; i < key.length(); i++) {
            if (!ptr.hasChild(key.charAt(i))) {
                return false;
            } else {
                ptr = ptr.get(key.charAt(i));
            }
        }
        return ptr.keyNode();
    }

    /** Inserts string KEY into Trie */
    @Override
    public void add(String key) {
        Node ptr = root;
        for (int i = 0; i < key.length(); i++) {
            if (!ptr.hasChild(key.charAt(i))) {
                Node n = new Node(key.charAt(i));
                ptr.add(n);
            }
            ptr = ptr.get(key.charAt(i));
        }
        ptr.setKeyNode();
    }

    /** Returns a list of all words that start with PREFIX */
    @Override
    public List<String> keysWithPrefix(String prefix) {
        List<String> words = new ArrayList<>();
        Node ptr = root;
        for (int i = 0; i < prefix.length(); i++) {
            if (ptr.hasChild(prefix.charAt(i))) {
                ptr = ptr.get(prefix.charAt(i));
            } else {
                return null;
            }
        }
        for (String word : getWordsFromNode(ptr, true)) {
            words.add(prefix + word);
        }
        return words;
    }

    /* returns all the strings that start at node START and end at a key node. the start node's label
    is included only if the bool TOP is false */
    private List<String> getWordsFromNode(Node start, boolean top) {
        List<String> words = new ArrayList();
        if (start.keyNode() && start != root && !top) {
            words.add( "" + start.label());
        }
        for (Node n : start.children()) {
            for (String word : getWordsFromNode(n, false)) {
                if (start != root && !top) {
                    words.add(start.label() + word);
                } else {
                    words.add(word);
                }
            }
        }
        return words;
    }

    /** Returns the longest prefix of KEY that exists in the Trie
     * Not required for Lab 9. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }

}
