package deque;

public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        this.items = (T[]) new Object[8];
        this.nextFirst = 4;
        this.nextLast = 5;
        this.size = 0;
    }

    private void resize(int cap) {

        /** When resize is met, nextLast will always be in front of nextFirst */
        T[] newItems = (T[]) new Object[cap];
        int nextLastIndex = 0;
        int nextFirstIndex = newItems.length - 1;

        for (nextLastIndex = 0; items[nextLastIndex] != null; nextLastIndex++) {
            newItems[nextLastIndex] = items[nextLastIndex];
        }
        nextLast = nextLastIndex;

        for (int i = items.length - 1; items[i] != null ; i--) {
            newItems[nextFirstIndex--] = items[i];
        }
        nextFirst = nextFirstIndex;

        items = newItems;

    }

    @Override
    public void addFirst(T item) {

       if (nextLast == nextFirst) {
           resize(items.length * 2);
       }

       if (nextFirst < 0)   nextFirst = items.length - 1;

       items[nextFirst--] = item;
       size += 1;
    }

    @Override
    public void addLast(T item) {

        if (nextLast == nextFirst) {
            resize(items.length * 2);
        }

        if (nextLast >= items.length) nextLast = 0;

        items[nextLast++] = item;
        size += 1;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void printDeque() {
        for (T i : items) {
            System.out.print(i + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) return null;
        size -= 1;
        if (nextLast >= items.length - 1) nextFirst = 0;
        return items[++nextFirst];
    }

    @Override
    public T removeLast() {
        if (isEmpty()) return null;
        size -= 1;
        if (nextLast <= 0) nextLast = items.length;
        return items[--nextLast];
    }

    @Override
    public T get(int index) {
        int idx = nextFirst + 1 + index;
        if (nextFirst + 1 + index >= items.length) {
            idx -= items.length;
        }
        return items[idx];
    }
}
