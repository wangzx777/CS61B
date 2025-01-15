package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>  {
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c) {
        super();
        this.c = c;
    }
    public T max() {
        if (isEmpty()) return null;
        int i = (first + 1) % items.length;
        T m = items[i];
        while (i != last) {
            if (items[i] == null) {
                i = (i+1) % items.length;
                continue;
            }
            if (c.compare(m,items[i]) < 0) {
                m = items[i];
            }
            i = (i+1) % items.length;
        }
        return m;
    }

    public T max(Comparator<T> c1) {
        if (isEmpty()) return null;
        int i = first + 1;
        T m = items[i];
        while (i != last) {
            if (items[i] == null) {
                i = (i+1) % items.length;
                continue;
            }
            if (c1.compare(m,items[i]) < 0) {
                m = items[i];
            }
            i = (i+1) % items.length;
        }
        return m;
    }
}
