package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        public Node next;
        K key;
        V value;

        Node(K k, V v, Node next) {
            key = k;
            value = v;
            this.next = next;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int tableSize;
    private double loadFactor;

    /**
     * Iterator method
     * */
    @Override
    public Iterator<K> iterator() {
        return (Iterator<K>) new CollectionIterator();
    }

    private class CollectionIterator implements Iterator<Collection<Node>> {
        private int position;
        public CollectionIterator() {
            position = 0;
        }

        @Override
        public boolean hasNext() {
            return position < size;
        }

        @Override
        public Collection<Node> next() {
            Collection<Node> returnNode = buckets[position];
            position += 1;
            return returnNode;
        }
    }

    /** Constructors */
    public MyHashMap() {
        this.size = 0;
        this.tableSize = 16;
        this.loadFactor = 0.75;
        this.buckets = new Collection[tableSize];
    }

    public MyHashMap(int initialSize) {
        this.size = 0;
        this.tableSize = initialSize;
        this.loadFactor = 0.75;
        this.buckets = new Collection[tableSize];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        this.size = 0;
        this.tableSize = initialSize;
        this.loadFactor = maxLoad;
        this.buckets = new Collection[tableSize];
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value, null);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return new Collection[tableSize];
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!

    private int hash (K key) {
        return (key == null) ? 0 : (0x7ffffff & key.hashCode()) % buckets.length;
    }

    // find method, to iterate through the collection  at a current index in the buckets array
    private Node find (K key, Collection<Node> bucket) {
        Iterator<Node> watcher = bucket.iterator();
        while(watcher.hasNext()) {
            Node n = watcher.next();
            if(n.key.equals(key)) {
                return n;
            }
        }
        return null;
    }

    private void updateSize() {
        MyHashMap<K,V> newMap = new MyHashMap(tableSize * 2, 0.75);
        // call the KeySet method to get all of the keys in a Set view
        Set<K> set = this.keySet();
        // iterate over the Key set
        Iterator<K> watcher = set.iterator();
        while(watcher.hasNext()) {
            K key = watcher.next();
            V value = get(key);
            newMap.put(key, value);
        }
        this.size = newMap.size;
        this.tableSize = newMap.tableSize;
        this.loadFactor = newMap.loadFactor;
        this.buckets = newMap.buckets;
    }

    @Override
    public void clear() {
        size = 0;
        for (int i = 0 ; i < buckets.length; i+= 1) {
            //buckets[i] = null;
            if(buckets[i] != null) {
                buckets[i].clear();
            }

        }
        // collection.remove()
    }

    @Override
    public boolean containsKey(K key) {
        int h = hash(key);
        if (buckets[h] != null) {
            Iterator<Node> watcher = buckets[h].iterator();
            while(watcher.hasNext()) {
                Node n = watcher.next();
                if(n.key.equals(key)) {
                    return true;
                }
            }
            return false;
        }
        return false;
    }

    @Override
    public V get(K key) {
        int h = hash(key);
        if (buckets[h] != null) {
            Iterator<Node> watcher = buckets[h].iterator();
            while(watcher.hasNext()) {
                Node n = watcher.next();
                if(n.key.equals(key)) {
                    return n.value;
                }
            }
            return null;
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int h = hash(key);
        Collection<Node> index = buckets[h];
        if (index == null) {
            buckets[h] = createBucket();
        }
        Node n = find(key, buckets[h]);
        if (n == null) {
            Node newNode = createNode(key, value);
            buckets[h].add(newNode);
            size += 1;
            if ((Double.valueOf(size) / Double.valueOf(tableSize)) > loadFactor) {
                this.updateSize();
            }
        } else {
            n.value = value;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<K> keySet() {
        // see the Iterable<key> keys method in the princeton notes
        Set<K> set = new HashSet<K>();
        for(int i = 0; i < buckets.length; i++) {
            // loop through each list at the current index
            if(buckets[i] != null) {
                Iterator<Node> watcher = buckets[i].iterator();
                while(watcher.hasNext()) {
                    Node n = watcher.next();
                    set.add(n.key);
                }
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException("Method not supported");
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException("Method not supported");
    }
}
