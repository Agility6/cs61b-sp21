package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {


    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(T i, Node n) {
            item = i;
            next = n;
        }
    }
    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        // init sentinel
        sentinel = new Node(null, null);
        size = 0;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    @Override
    public void addFirst(T item) {
        Node firstNode = sentinel.next;
        // firstNode -> currentNode and currentNode -> firstNode,
        firstNode.prev = new Node(item, firstNode);
        sentinel.next = firstNode.prev;
        firstNode.prev.prev = sentinel;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node lastNode = sentinel.prev;
        lastNode.next = new Node(item, sentinel);
        sentinel.prev = lastNode.next;
        lastNode.next.prev = lastNode;
        size += 1;

    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            System.out.print(p.item + " ");
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node removeFirstNode = sentinel.next;
        T removeFirstNodeItem = removeFirstNode.item;
        sentinel.next = removeFirstNode.next;
        removeFirstNode.next.prev = sentinel;
        removeFirstNode.next = null;
        removeFirstNode.prev = null;
        removeFirstNode.item = null;
        size -= 1;
        return removeFirstNodeItem;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node removeLastNode = sentinel.prev;
        T removeLastNodeItem = removeLastNode.item;
        removeLastNode.prev.next = sentinel;
        sentinel.prev = removeLastNode.prev;
        removeLastNode.next = null;
        removeLastNode.prev = null;
        removeLastNode.item = null;
        size -= 1;
        return removeLastNodeItem;
    }

    @Override
    public T get(int index) {

        if (index < 0 || isEmpty()) {
            return null;
        }

        int count = 0;
        for (Node p = sentinel.next; p.item != null; p = p.next) {
            if (count == index) {
                return p.item;
            }
            count++;
        }

        return null;
    }

    /**
     * 3
     * p.n
     * @param index
     * @return
     */
    public T getRecursive(int index) {

        Node p = sentinel.next;
        if (index < 0 || isEmpty()) {
            return null;
        }

        return getRecurHelper(index, 0, p);
    }

    private T getRecurHelper(int index, int nodeIndex, Node p) {

        if (p.item == null) {
            return null;
        }

        if (index == nodeIndex) {
            return p.item;
        }

        return getRecurHelper(index, nodeIndex + 1, p.next);
    }

    /** ---------- Iterable ---------- */
    @Override
    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {

        private int wizPos;

        private LinkedListDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T item = get(wizPos);
            wizPos++;
            return item;
        }
    }

    @Override
    public boolean equals(Object obj) {

        if (obj == null) {
            return false;
        }

        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Deque)) {
            return false;
        }

        LinkedListDeque<T> o = (LinkedListDeque<T>) obj;
        for (int i = 0; i < size; i++) {
            if (!o.get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }

    private static void main(String[] args) {

        LinkedListDeque<String> lld = new LinkedListDeque<>();

        lld.addFirst("3");
        lld.addFirst("2");
        lld.addFirst("1");
        lld.addLast("4");
        lld.addLast("5");
        lld.addLast("6");
        lld.addLast("7");

        Iterator<String> seer = lld.iterator();

        while (seer.hasNext()) {
            String s = seer.next();
            System.out.println(s);
        }

        LinkedListDeque<String> lld2 = new LinkedListDeque<>();
        lld2.addFirst("1");
        lld2.addFirst("2");
        lld2.addFirst("3");

        LinkedListDeque<String> lld3 = new LinkedListDeque<>();
        lld3.addFirst("1");
        lld3.addFirst("2");
        lld3.addFirst("3");

        System.out.println(lld2.equals(lld3));
    }
}
