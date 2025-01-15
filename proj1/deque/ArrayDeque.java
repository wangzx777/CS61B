package deque;

public class ArrayDeque<T> implements Deque<T>{
    protected int size;
    protected T[] items;
    protected int first;
    protected int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 6;
        last = 7;
    }

    public void resize() {
        T[] a = (T[]) new Object[items.length * 2];
        int oldLen = items.length;
        int i = (first + 1) % oldLen; /*新数组的对应原来数组的位置*/
        int j = 1;
        while ((i+oldLen) % oldLen != last) {
            a[j] = items[i];
            i = (i + 1) % oldLen;
            j = (j + 1) % oldLen;
        }
        items = a;
        first = 0;
        last = oldLen;
    }
    public void addLast(T x) {
        if (first == last) {
            resize();
        }
        items[last] = x;
        last = (last + 1 + items.length) % items.length;
        size += 1;
    }

    public void addFirst(T x) {
        if (first == last) {
            resize();
        }
        items[first] = x;
        first = (first - 1 + items.length) % items.length;
        size += 1;
    }



    public int size() {
        return size;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        first  = (first + 1 + items.length) % items.length;
        T item = items[first];
        items[first] = null;
        size = size - 1;
        return item;
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        last  = (last - 1 + items.length) % items.length;
        T item = items[last];
        items[last] = null;
        size = size - 1;
        return item;
    }


    public T get(int index) {
        return items[(first + index + 1) % items.length];
    }

    public void printDeque() {
        int i = (first + 1) % items.length;
        while (i != last) {
            System.out.print(items[i]+" ");
            i = (i+1) % items.length;
        }
    }

    public ArrayDeque(ArrayDeque other) {
        first = other.first;
        last = other.last;
        size = other.size;
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }


}
