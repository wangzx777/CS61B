package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private int size;
    private Node root;
    private Set<K> keyset = new HashSet<>();


    private class Node {
        private K key;
        private V value;
        Node left;
        Node right;

        public Node(K key,V value,Node left,Node right) {
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        }
//    public BSTMap(K key,V value,Node left,Node right) {
//        this.root = new Node(key,value,left,right);
//        size = 1;
//    }
    public BSTMap() {}

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

//    @Override
//    public int compareTo(K key) {
//        if ()
//    } 不用在类里实现comparable，因为比较对象仅是K



    @Override
    public boolean containsKey(K key) {
//        return containsKeyHelper(key,root);
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        return findNode(key,root) != null;
    }

    private <T> T findNode(K key,Node p) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        if (p == null) return null;
        if (p.key.compareTo(key) == 0) {
            return (T) p;
        }
        if (p.key.compareTo(key) > 0) {
            return findNode(key,p.left);
        }
        return findNode(key,p.right);
    }

    @Override
    public V get(K key) {
        Node q = findNode(key,root);
        if (findNode(key,root) == null) {
            return null;
        }
        return q.value;
    }

    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key,value,null,null);
        }
        int tep = p.key.compareTo(key);
        if (tep > 0) {
            p.left = putHelper(key,value,p.left);
        }
        if (tep < 0) {
            p.right = putHelper(key,value,p.right);
        }
        else p.value = value;
        return p;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        root = putHelper(key,value,root);
    }

    private void traver(Node p) {
        if (p == null) {
            return;
        }
        keyset.add(p.key);
        traver(p.left);
        traver(p.right);
    }
    @Override
    public Set<K> keySet()  {
        throw new java.lang.UnsupportedOperationException();
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
        throw new UnsupportedOperationException();
    }

    private void printInOrder(Node p) {
        if (p == null) {
            return;
        }
        printInOrder(p.left);
        System.out.println(p.key);
        printInOrder(p.right);
    }
    public void printInOrder() {
        printInOrder(root);
    }


//    public static void main(String[] args) {
//        BSTMap<Integer,String> bstMap= new BSTMap<>();
//        bstMap.put(2,"a");
//        for (int i = 97; i < 108; i++) {
//            String s = " " + i;
//            bstMap.put(i,s);
//        }
//        for (int i = 23; i < 33; i++) {
//            String s = " " + i;
//            bstMap.put(i,s);
//        }
//        bstMap.printOrder();
//    }

    }


