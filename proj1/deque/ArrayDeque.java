package deque;

public class ArrayDeque {
    int size;
    private int[] items;
    int first;
    int last;

    public ArrayDeque(){
        items = new int[8];
        size = 0;
        first = 6;
        last = 7;
    }

    public void resize(){
        int[] a = new int[size*2];
        int oldItems = items.length;
        int i = (first + 1) % items.length;
        for (int j = 0; j < items.length; j++) {
            a[j] = items[i];
            i = (i + 1) % items.length;
        }
        items = a;
        first = items.length-1;
        last = oldItems;
    }
    public void addLast(int x){
        if (size == items.length){
            resize();
        }
        items[last] = x;
        last = (last+1+items.length) % items.length;
        size +=1;
    }

    public void addFirst(int x){
        if (size == items.length){
            resize();
        }
        items[first] = x;
        first = (first-1+items.length) % items.length;
        size +=1;
    }

    

    public int size(){
        return size;
    }

    public int remove(){
        int x = items[size-1];
        size = size-1;
        return x;
    }

    public int get(int index){
        return items[index-first];
    }

    public void printDeque(){
        int i = (first+1) % items.length;
        for (int j = 0; j < items.length; j++) {
            if (items[i] == 0){
                break;
            }
            System.out.print(items[i]+" ");
            i = (i + 1) % items.length;
        }
    }

    public static void main(String[] args) {
        ArrayDeque arr = new ArrayDeque();
        arr.addFirst(2);
        arr.addFirst(1);
        for (int i = 3; i < 9; i++) {
            arr.addLast(i);
        }
        arr.addLast(10);
        arr.addFirst(33);
        arr.printDeque();
    }
}
