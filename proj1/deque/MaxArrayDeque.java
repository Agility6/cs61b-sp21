package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {

    private Comparator<T> cmp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        cmp = c;
    }

    public T max() {

        if (isEmpty()) return null;
        T maxItem = this.get(0);
        for (T i : this) {
            // o1 > o2 return positive integer
            if (cmp.compare(i, maxItem) > 0) maxItem = i;
        }
        return maxItem;
    }

     public T max(Comparator<T> c) {

        if (isEmpty()) return null;
        T max = this.get(0);
        for (T i : this) {
            if (c.compare(i, max) > 0) max = i;
        }

        return max;
     }

    public static void main(String[] args) {

        // 匿名内部类
        Comparator<Integer> cmp = new Comparator<>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.compareTo(o2);
            }
        };

        MaxArrayDeque mad1 = new MaxArrayDeque(cmp);

        int n = 99;

        for (int i = n; i >= 0; i--) {
            mad1.addFirst(i);
        }

        System.out.println(mad1.max());

    }
}
