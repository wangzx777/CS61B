package gh2;


 import deque.Deque;
 import deque.ArrayDeque;


//Note: This file will not compile until you complete the Deque implementations
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */

     private Deque<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
//     TODO：创建容量 = SR / 频率的缓冲区。您需要
//        将此除法操作的结果转换为 int。为
//        更高的准确性，请在转换之前使用 Math.round（） 函数。
//        您最初应该用零填充缓冲区数组。
        int capacity = (int) Math.round(SR/frequency);
        buffer = new ArrayDeque<>();
        for (int i = 0; i < capacity; i++) {
            buffer.addLast(0.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int tmp = buffer.size();
        while (!buffer.isEmpty()) {
            buffer.removeLast();
        }
        for (int i = 0; i < tmp; i++) {
            buffer.addLast(Math.random() - 0.5);

        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
      double first = buffer.removeFirst();
      double second = buffer.get(0);
      double newDouble = (first + second) / 2;
      buffer.addLast(newDouble);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        // TODO: Return the correct thing.
        return buffer.get(0);
    }
}
    // TODO: Remove all comments that say TODO when you're done.
