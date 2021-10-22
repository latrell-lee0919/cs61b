package bstmap;

import java.util.Iterator;
import java.util.Set;

public class BSTMap <Key extends Comparable<Key>, Value> implements Map61B{
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
    public boolean containsKey(Object key) {
        // can maybe just call the get method and return false if it's null
        return false;
    }

    @Override
    public Object get(Object key) {
        // start at the root
        // if key is greater than the key in the root go right
        // otherwise go left
        // keep going until you reach the node with the key and return it
        // if it's not there, return null
        return null;
    }

    @Override
    public int size() {
        return root.size;
    }

    @Override
    public void put(Object key, Object value) {
        // increment root's size by 1
        root.size += 1;
        // check if the key is greater than or less than root's key
        // if greater than, place to the right
        // if less than, place to the left
    }

    public void printInOrder() {

    }

    @Override
    public Set keySet() {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Object remove(Object key) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Object remove(Object key, Object value) {
        throw new UnsupportedOperationException("Operation not supported");
    }

    @Override
    public Iterator iterator() {
        throw new UnsupportedOperationException("Operation not supported");
    }
}
