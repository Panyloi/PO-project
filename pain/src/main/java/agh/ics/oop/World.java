package agh.ics.oop;

import java.util.Iterator;
import java.util.TreeSet;

public class World {
    public static void main(String[] args){
        TreeSet<Integer> ts = new TreeSet<>();
        ts.add(61);
        ts.add(10);
        ts.add(87);
        ts.add(39);
        Iterator<Integer> it = ts.iterator();
        System.out.println(it.next());
        System.out.println(it.next());

        int[][] arr = new int[2][2];
        System.out.println(arr[0][1]);
    }
}
