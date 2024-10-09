package deque;

public class ArrayDeque<T> {
    private int size;
    private T[] items;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        first = 6;
        last = 7;
    }

    public void resize() {
        T[] a = (T[]) new Object[size * 2];
        int oldItems = items.length;
        int i = (first + 1) % items.length;
        for (int j = 0; j < items.length; j++) {
            a[j] = items[i];
            i = (i + 1) % items.length;
        }
        items = a;
        first = items.length - 1;
        last = oldItems;
    }
    public void addLast(T x) {
        if (size == items.length) {
            resize();
        }
        items[last] = x;
        last = (last + 1 + items.length) % items.length;
        size += 1;
    }

    public void addFirst(T x) {
        if (size == items.length) {
            resize();
        }
        items[first] = x;
        first = (first - 1 + items.length) % items.length;
        size += 1;
    }

    

    public int size() {
        return size;
    }

    public T remove() {
        T x = items[size - 1];
        size = size - 1;
        return x;
    }

    public T get(int index) {
        return items[index - first];
    }

    public void printDeque() {
        int i = (first + 1) % items.length;
        for (int j = 0; j < items.length; j++) {
            if (items[i] == null) {
                break;
            }
            System.out.print(items[i] + " ");
            i = (i + 1) % items.length;
        }
    }

    public ArrayDeque(ArrayDeque other) {
        first = other.first;
        last = other.last;
        size = other.size;
        items = (T[]) new Object[other.items.length];
        System.arraycopy(other.items, 0, items, 0, other.items.length);
    }

    public static void main(String[] args) {

    }
}
