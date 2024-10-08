package deque;

public class LinkedListDeque<T> {
    private ListNode sentinel;
    private int size;
    private class ListNode{
        T value;
        ListNode front;
        ListNode back;
        public ListNode(T x,ListNode a, ListNode b){
            this.value = x;
            this.back = b;
            this.front = a;
        }
    }
    /**创建空的结点**/
    public LinkedListDeque(){
        this.sentinel = new ListNode(null,null,null);
        this.sentinel.front = this.sentinel;
        this.sentinel.back = this.sentinel;
        this.size = 0;
    }
    /**创建非空结点**/
    public LinkedListDeque(T x){
        this.sentinel = new ListNode(null,null,null);
        this.sentinel.front = this.sentinel.back = new ListNode(x,sentinel,sentinel);
        this.size = 1;

    }

    public void addFirst(T item){
        ListNode p = sentinel.back;
        /**不能让p=newListNode，这样p的指向改变，可以操作的是p.back或是p.next两个不同的指针**/
        sentinel.back = new ListNode(item,sentinel,sentinel.back);
        p.front = sentinel.back;
        this.size += 1;
    }

    public void addLast(T item){
        ListNode p = sentinel.front;
        p.back = new ListNode(item,p,sentinel);
        sentinel.front = p.back;
        size += 1;
    }

    public boolean isEmpty(){
        if (sentinel.back == null){
            return true;
        }
        else {
            return false;
        }
    }

    public int size(ListNode p){
        if (p.back == sentinel){
            return 1;
        }
        return 1+size(p.back);
    }
    public int size(){
        return size(sentinel.back);
    }

    public void printDeque(ListNode p){
        if (p == sentinel){
            System.out.println();
            return;
        }
        System.out.print(p.value + " ");
        printDeque(p.back);
    }
    public void printDeque(){
        printDeque(sentinel.back);
    }

    public T removeFirst(){
        ListNode p = sentinel.back;
        if (p.back == sentinel){
            return null;
        }
        T item = p.value;
        sentinel.back = p.back;
        p.back.front = sentinel;
        p.front = p.back = null;
        size -= 1;
        return item;
    }

    public T removeLast(){
        ListNode p = sentinel.front;
        if (sentinel.back == sentinel){
            return null;
        }
        T item = p.value;
        sentinel.front = p.front;
        p.front.back = sentinel;
        p.front = p.back = null;
        size -= 1;
        return item;
    }

    public T get(int index){
        ListNode p = sentinel.back;
        if (p == null){
            return null;
        }
        for (int i = 0; i < index ; i++) {
            p = p.back;
        }
        return p.value;
    }

    public LinkedListDeque(LinkedListDeque other){
        sentinel = new ListNode(null,null,null);
        sentinel.front = sentinel.back = sentinel;
        for (int i = 0; i < other.size() ; i++) {
            addLast((T) other.get(i));
        }
    }

    public static void main(String[] args) {
//        LinkedListDeque<Integer> deque = new LinkedListDeque<>(32);
//        deque.addFirst(42);
//        deque.addLast(43);
//        deque.printDeque();
//        System.out.println(deque.get(1));
//        System.out.println(deque.size);
//        LinkedListDeque deque_dot = new LinkedListDeque(deque);
//        deque_dot.printDeque();
        LinkedListDeque deque = new LinkedListDeque(32);
        deque.addLast(42);
        deque.addLast('a');
        deque.addLast("hello");
        deque.printDeque();
    }
}
