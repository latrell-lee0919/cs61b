package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap <Key extends Comparable<Key>, Value> implements Map61B<Key, Value>{
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int size;

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public BSTMap() {
    }

    @Override
    public void clear() {
        // set root's left and right node's = to null
        root.right = null;
        root.left = null;
        // set root's size equal to 0
        root.size = 0;
    }

    @Override
    public boolean containsKey(Key key) {
        // can maybe just call the get method and return false if it's null
        return false;
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if(key == null) {
            // if it's not there, return null
            return null;
        }
        // start at the root
        int comp = key.compareTo(root.key); // 0 means equal, less than 0 is less than, greater than 0 is greater than
        // keep going until you reach the node with the key and return it
        if (comp == 0) {
            return root.val;
        } else if (comp < 0) {
            // if key is less than the key in the root go left
            return get(x.left, key);
        } else {
            // if key is greater than the key in the root go right
            return get(x.right, key);
        }

    }

    @Override
    public int size() {
        return root.size;
    }

    @Override
    public void put(Key key, Value value) {
//        // increment root's size by 1
//        root.size += 1;
//        // check if the key is greater than or less than root's key
//        int comp = key.compareTo(root.key);
//        // if greater than, place to the right
//        // if less than, place to the left
        root = put(root, key, value); // check this during tutoring
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.size = 1 + x.left.size + x.right.size;
        return x;
    }

    public void printInOrder() {

    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Value remove(Key key) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Value remove(Key key, Value value) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
