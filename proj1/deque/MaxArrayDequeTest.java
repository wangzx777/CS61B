package deque;

import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {

    public class People {
        int age;
        String name;
        int high;

        public People(int a, int h, String n) {
            age = a;
            high = h;
            name = n;
        }
    }

    public class MyComparator1 implements Comparator<People>{
        @Override
        public int compare(People o1, People o2) {
            if (o1.high > o2.high) return 1;
            if (o1.high < o2.high) return -1;
            else return 0;
        }
    }
    public class MyComparator2 implements Comparator<People>{
        @Override
        public int compare(People o1, People o2) {
            if (o1.age > o2.age) return 1;
            if (o1.age < o2.age) return -1;
            else return 0;
        }
    }

    @Test
    public void testOne() {
        MyComparator1 c1 = new MyComparator1();
        MaxArrayDeque<People> mad1 = new MaxArrayDeque<>(c1);
        for (int i = 0; i < 5; i++) {
            mad1.addLast(new People(i, (int) (Math.random() * 100),"ab"));
        }
//        for (int i = 0; i < mad1.items.length ; i++) { 复杂问题，为什么这里会报错，哪怕没有把object当成people操作，网上说是因为
//        items = (T[]) new Object[8]创建泛型数组出问题了
//            System.out.println(mad1.items[i]);
//        }
        for (int i = 0; i < 5; i++) {
            System.out.println(mad1.get((i) % 8).high);
        }

        System.out.println(mad1.max().high);

    }

    @Test
    public void testTwo() {
        MyComparator1 c1 = new MyComparator1();
        MyComparator2 c2 = new MyComparator2();
        MaxArrayDeque<People> mad1 = new MaxArrayDeque<>(c1);
        for (int i = 0; i < 5; i++) {
            mad1.addLast(new People(i, (int) (Math.random() * 100),"ab"));
        }

        for (int i = 0; i < 5; i++) {
            System.out.println(mad1.get((i) % 8).age);
        }

        System.out.println(mad1.max(c2).age);

    }

}
