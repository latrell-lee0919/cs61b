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
        root = null;
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
        if(get(key) == null) {
            return false;
        } else {
            return true;
        }
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

        if(x == null) {
            return null;
        }
        // start at the root
        int comp = key.compareTo(x.key); // 0 means equal, less than 0 is less than, greater than 0 is greater than
        // keep going until you reach the node with the key and return it
        if (comp == 0) {
            return x.val;
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
        if(root == null) {
            return 0;
        } else {
            return root.size;
        }
    }

    public int size(Node x) {
        if(x == null) {
            return 0;
        } else {
            return x.size;
        }
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
            x = new Node(key, val, 1);
            return x;
        }

        x.size = 1 + size(x);

        int comp = key.compareTo(x.key);
        if (comp < 0) {
            x.left = put(x.left, key, val);
        } else if (comp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        return x;
    }

    public void printInOrder() {
        String inOrder = printInOrder(root);
        System.out.println(inOrder);
    }

    private String printInOrder(Node x) {
        if(x == null) {
            return "";
        }
        String output = "";

        String left = printInOrder(x.left);

        String right = printInOrder(x.right);

        output = left + x.key.toString() + right;

        // start at root
        return output;
    }

    @Override
    public Value remove(Key key) {
        Value val = get(key);
        remove(root, key);
        return val;
    }

    private Node remove(Node x, Key key) {
        if (get(key) == null) {
            return null;
        }

        int comp = key.compareTo(x.key);

        if(comp < 0) {
            // search left and remove
            x.left = remove(x.left, key);
            return x;

        } else if (comp > 0) {
            // search right and remove
            x.right = remove(x.right, key);
            return x;
        } else {
            // on the node with the key, remove it
            return null;
        }
    }


    @Override
    public Set keySet() {
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

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("Latrell", 1);
        b.put("Hunter", 1);
        b.put("Tutoring", 1);
        b.put("Binary", 2);
        b.put("Coding", 1);
        b.printInOrder();
        System.out.println(b.remove("Coding"));
        b.printInOrder();

    }
}


