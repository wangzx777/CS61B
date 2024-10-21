package randomizedtest;

import afu.org.checkerframework.checker.igj.qual.I;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */

public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testAListNoResizing() {
        AListNoResizing<Integer> a = new AListNoResizing<>();
        a.addLast(4);
        a.addLast(5);
        a.addLast(6);
        assertEquals(6,(int)(a.removeLast()));
        assertEquals(5,(int)(a.removeLast()));
        assertEquals(4,(int)(a.removeLast()));
    }

    @Test
    public void testBuggyAList() {
        BuggyAList<Integer> b = new BuggyAList<>();
        int N = 5000    ;
//      生成随机数检测
        for (int i = 0; i < N; i++) {
            int randomFlag = StdRandom.uniform(0,3);
            if (randomFlag == 0) {
                int newElem = StdRandom.uniform(0,100);
                b.addLast(newElem);
//                System.out.println("add " + newElem);
            }
            else if (randomFlag == 1) {
                int size = b.size();
//                System.out.println("size:" + size);
            }
            else {
                if (b.size() > 0) {
                    int removeElem = b.removeLast();
//                    System.out.println("remove" + removeElem);
                }
            }
        }
    }

}
