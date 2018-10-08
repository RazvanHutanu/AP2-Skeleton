public class List<E extends Comparable<E>> implements ListInterface<E> {
    private Node head;
    private Node tail;
    private Node current;
    private int length = 0;
    private class Node {

        E data;
        Node prior, next;

        public Node(E d) {
            this(d, null, null);
        }

        public Node(E data, Node prior, Node next) {
            this.data = data == null ? null : data;
            this.prior = prior;
            this.next = next;
        }

    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public ListInterface<E> init() {
        return null;
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public ListInterface<E> insert(E d) {
        return null;
    }

    @Override
    public E retrieve() {
        return null;
    }

    @Override
    public ListInterface<E> remove() {
        return null;
    }

    @Override
    public boolean find(E d) {
        return false;
    }

    @Override
    public boolean goToFirst() {
        return false;
    }

    @Override
    public boolean goToLast() {
        return false;
    }

    @Override
    public boolean goToNext() {
        return false;
    }

    @Override
    public boolean goToPrevious() {
        return false;
    }

    @Override
    public ListInterface<E> copy() {
        return null;
    }
}
