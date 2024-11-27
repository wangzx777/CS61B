package hashmap;

//import net.sf.saxon.expr.Component;
//import org.eclipse.jetty.server.RequestLog;
//import org.w3c.dom.Node;

import java.util.*;

/**
 * A hash table-backed Map implementation. Provides amortized constant time
 * access to elements via get(), remove(), and put() in the best case.
 * <p>
 * Assumes null keys will never be inserted, and does not resize down upon remove().
 *
 * @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {

    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    private double loadFactor = 0.75;
    private int M = 16;
    private int size;
    // You should probably define some more!

    /**
     * Constructors
     */
    public MyHashMap() {
        buckets = createTable(M);
        for (int i = 0; i < M; i++) {
            buckets[i] = createBucket();
        }
    }

    public MyHashMap(int initialSize) {
        M = initialSize;
        buckets = createTable(M);
        for (int i = 0; i < M; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad     maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        M = initialSize;
        loadFactor = maxLoad;
        buckets = createTable(M);
        for (int i = 0; i < M; i++) {
            buckets[i] = createBucket();
        }
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     * <p>
     * The only requirements of a hash table bucket are that we can:
     * 1. Insert items (`add` method)
     * 2. Remove items (`remove` method)
     * 3. Iterate through items (`iterator` method)
     * <p>
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     * <p>
     * Override this method to use different data structures as
     * the underlying bucket type
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        Collection<Node> bucket = new ArrayList<>();
        return bucket;
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     * <p>
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection<?>[tableSize];
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    @Override
    public void clear() {
        size = 0;
        for(Collection<Node> bucket : buckets) {
            bucket.clear();
        }
    }

    private int getIndex(K key,int Max) {
        int index = Math.floorMod(key.hashCode(), Max);
        return index;
    }

    @Override
    public boolean containsKey(K key) {
        int index = getIndex(key,M);
        for (Node n : buckets[index]) {
            if (n.key.equals(key)) return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public V get(K key) {
        int index = getIndex(key,M);
        for (Node n : buckets[index]) {
            if (n.key.equals(key)) return n.value;
        }
        return null;
    }

    private void resize(int Max) {
        Collection<Node>[] newBuckets = createTable(Max);
        for (int i = 0; i < newBuckets.length; i++) {
            newBuckets[i] = createBucket();
        }
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for (Node n : buckets[i]) {
                    int newIndex = getIndex(n.key,Max);
                    newBuckets[newIndex].add(n);
                }
            }
        }
        buckets = newBuckets;
    }

    @Override
    public void put(K key, V value) {
        int index = getIndex(key,M);
        for (Node node : buckets[index]) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }
        buckets[index].add(new Node(key, value));
        size += 1;
        if (size / M > loadFactor) {
            M = M * 2;
            resize(M);
        }

    }

    @Override
    public Set<K> keySet() {
        Set<K> myKeySet = new HashSet<>();
        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] != null) {
                for(Node n : buckets[i]) {
                    myKeySet.add(n.key);
                }
            }
        }
        return myKeySet;
    }

    @Override
    public V remove(K key) {
        for (Node n : buckets[getIndex(key,M)]) {
            if (n.key == key) {
                n.key = null;
                return n.value;
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    private class KeyIterator implements Iterator<K> {
        private int stepCount;

        public KeyIterator() {
            stepCount = 0;
        }

        @Override
        public boolean hasNext() {
            return (stepCount < M) && (buckets[stepCount].iterator().hasNext());
        }

        @Override
        public K next() {
            while (true) {
                Iterator<Node> bucketIterator = buckets[stepCount].iterator();
                if (bucketIterator.hasNext()) {
                    return bucketIterator.next().key;
                } else {
                    stepCount++;
                    if (stepCount > M - 1) {
                        throw new NoSuchElementException(" No such element");
                    }
                }
            }
//            return null 反而不对，因为不会执行
        }


    }

}
