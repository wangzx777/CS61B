package deque;

public class LinkedListDeque<T> {
    private ListNode sentinel;
    private int size;
    private class ListNode {
        T value;
        ListNode front;
        ListNode back;
        public ListNode(T x) {
            this.value = x;
        }
    }
    /**创建空的结点**/
    public LinkedListDeque() {
        this.sentinel = new ListNode(null);
        this.sentinel.front = this.sentinel;
        this.sentinel.back = this.sentinel;
        this.size = 0;
    }

    public void addFirst(T item) {
        ListNode p = sentinel.back;
        /**不能让p=newListNode，这样p的指向改变，可以操作的 是p.back或 是p.next两个不同的指针**/
        ListNode q  = new ListNode(item);
        sentinel.back = q;
        q.front = sentinel;
        q.back = p;
        p.front = q;
        this.size += 1;
    }

    public void addLast(T item) {
        ListNode p = sentinel.front;
        ListNode q = new ListNode(item);
        sentinel.front = q;
        q.front = p;
        q.back = sentinel;
        p.back = q;
        size += 1;
    }

    public boolean isEmpty() {
        if (sentinel.back == sentinel) {
            return true;
        }
        return false;
    }

    public int size(ListNode p) {
        if (p.back == sentinel) {
            return 0;
        }
        return 1 + size(p.back);
    }
    /**从头开始的size*/
    public int size() {
        return size(sentinel);
    }

    public void printDeque(ListNode p) {
        if (p == sentinel) {
            System.out.println();
            return;
        }
        System.out.print(p.value + " ");
        printDeque(p.back);
    }
    public void printDeque() {
        printDeque(sentinel.back);
    }

    public T removeFirst() {
        ListNode p = sentinel.back;
        if (p == sentinel) {
            return null;
        }
        T item = p.value;
        sentinel.back = p.back;
        p.back.front = sentinel;
        size -= 1;
        return item;
    }

    public T removeLast() {
        ListNode p = sentinel.front;
        if (p == sentinel) {
            return null;
        }
        T item = p.value;
        sentinel.front = p.front;
        p.front.back = sentinel;
        size -= 1;
        return item;
    }

    public T get(int index) {
        if (index >= size)  throw new IndexOutOfBoundsException("index must < size");
        ListNode p = sentinel.back;
        if (p == null) {
            return null;
        }
        for (int i = 0; i < index; i++) {
            p = p.back;
        }
        return p.value;
    }

    public T getRecursive(int index) {
        if (index >= size)  throw new IndexOutOfBoundsException("index must < size");
        return getRecursiveHelper(index,sentinel);
    }
    private T getRecursiveHelper(int index, ListNode p) {
        if (index == -1) {
            return p.value;
        }
        return getRecursiveHelper(index-1,p.back);
    }

}
