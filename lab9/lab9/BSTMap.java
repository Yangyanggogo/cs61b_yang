package lab9;

import java.util.*;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Yang Chen
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */
    private Set<K> keyset; /* keyset */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.size = 0;
        this.clear();
        keyset = new HashSet<K>();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (key == null) {
            throw new IllegalArgumentException("calls get() with a null key");
        }
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            return getHelper(key, p.left);
        }
        else if (cmp > 0) {
            return getHelper(key, p.right);
        }
        else {
            return p.value;
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = putHelper(key, value, p.left);
        }
        else if (cmp > 0) {
            p.right = putHelper(key, value, p.right);
        }
        else {
            p.value = value;
        }
        return p;
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("calls put() with a null key");
        }
        root = putHelper(key, value, root);
        keyset.add(key);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        return keyset;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    @Override
    public V remove(K key) {
        if (get(key) == null) {
            return null;
        }
        keyset.remove(key);
        V val = get(key);
        root = removeHelper(key, root);
        return val;
    }

    private Node removeHelper(K key, Node p) {
        if (p == null) {
            return null;
        }
        int cmp = key.compareTo(p.key);
        if (cmp < 0) {
            p.left = removeHelper(key, p.left);
        } else if (cmp > 0) {
            p.right = removeHelper(key, p.right);
        } else {
            if (p.left == null) {
                return p.right;
            } else if (p.right == null) {
                return p.left;
            } else {
                Node x = p;
                p = min(x.right);
                p.right = deleteMin(x.right);
                p.left = x.left;
            }
        }
        return p;
    }

    private Node min(Node p) {
        if (p.left == null) {
            return p;
        }
        return min(p.left);
    }

    private Node deleteMin(Node p) {
        if (p.left == null) {
            return p.right;
        }
        p.left = deleteMin(p.left);
        size -= 1;
        return p;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        if (get(key) == null) {
            return null;
        }
        if (value.equals(get(key))) {
            return remove(key);
        }
        else {
            throw new InputMismatchException();
        }
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        Stack<Node> stack = new Stack();

        private BSTMapIterator() {
            pushRightToLeft(root);
        }

        private void pushRightToLeft(Node p) {
            if (p == null) {
                return;
            }
            pushRightToLeft(p.right);
            stack.push(p);
            pushRightToLeft(p.left);
        }

        @Override
        public boolean hasNext() {
            if(stack.isEmpty()) {
                return false;
            }
            return true;
        }

        @Override
        public K next() {
            Node temp = stack.pop();
            return temp.key;
        }
    }
}
