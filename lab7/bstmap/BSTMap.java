package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{

    private Node root;
    private Set<K> keyset = new HashSet<>();


    private class Node {
        private K key;
        private V value;
        Node left;
        Node right;
        private int size;


        public Node(K key,V value,int size) {
            this.key = key;
            this.value = value;
            this.size = size;
        }

        }

    public BSTMap() {}

    @Override
    public void clear() {
        root = null;
    }

    private int size(Node p) {
        if (p == null) {
            return 0;
        }
        return p.size;
    }
    @Override
    public int size() {
        return size(root);
    }



    @Override
    public boolean containsKey(K key) {
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
            return new Node(key,value,1);
        }
        int tep = p.key.compareTo(key);
        if (tep > 0) {
            p.left = putHelper(key,value,p.left);
        }
        if (tep < 0) {
            p.right = putHelper(key,value,p.right);
        }
        else p.value = value;
        p.size = size(p.left) + size(p.right) + 1;
        return p;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls get() with a null key");
        root = putHelper(key,value,root);
    }

    private Node delectMinNode(Node p) {
      if (p.left == null) {
          return p.right;
      }
      p.size = size(p.left)+size(p.right)+1;
      p.left = delectMinNode(p.left);
      return p;
    }

    public void delectMinNode() {
        if (root.size == 0) {
            throw new NoSuchElementException("no none node exit");
        }
        root = delectMinNode(root);
    }

     private Node delectMaxNode(Node p) {
        if (p.right == null) {
            return p.left;
        }
        p.right = delectMaxNode(p.right);
        return p;
     }

     public void delectMaxNode() {
         if (root.size == 0) {
             throw new NoSuchElementException("no none node exit");
         }
        delectMaxNode(root);
     }

    private void traver(Node p) {
        if (p == null) {
            return;
        }
        traver(p.left);
        keyset.add(p.key);
        traver(p.right);
    }
    @Override
    public Set<K> keySet()  {
        traver(root);
        return keyset;
    }

    private Node minNode(Node p) {
        if (p.left == null) return p;
        return minNode(p.left);
    }
    public K minNode() {
        Node p = minNode(root);
        return p.key;
    }

    private Node remove(K key, Node p) {
        if (p == null) {
            return null;
        }
        int tmp = key.compareTo(p.key);
        if (tmp < 0) p.left = remove(key,p.left);
        if (tmp > 0) p.right = remove(key,p.right);
        else {
            if (p.right == null) return p.left;
            if (p.left == null) return p.right;
            Node t = p;
            p = minNode(t.right);
            p.right = delectMinNode(t);
            p.left = t.left;
        }
        p.size = size(p.left) + size(p.right) + 1;
        return p;
    }
    @Override
    public V remove(K key) {
        if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
        root = remove(key,root);

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


    public static void main(String[] args) {
        BSTMap<Integer,String> bstMap= new BSTMap<>();
        bstMap.put(2,"a");
        for (int i = 97; i < 108; i++) {
            String s = " " + i;
            bstMap.put(i,s);
        }
        for (int i = 23; i < 33; i++) {
            String s = " " + i;
            bstMap.put(i,s);
        }
        bstMap.printInOrder();
        System.out.println(bstMap.size());
        bstMap.delectMinNode();
        System.out.println(bstMap.size());
    }

    }


