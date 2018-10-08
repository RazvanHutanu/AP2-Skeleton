public class List<E extends Comparable<E>> implements ListInterface<E> {
    private Node head;
    private Node tail;
    private Node current;
    private int length;

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
        head = null;
        tail = null;
        length = 0;
        return this;
    }

    @Override
    public int size() {
        return this.length;
    }

    @Override
    public ListInterface<E> insert(E d) {
        Node tmp = new Node(d, tail, null);

        if( head == null )
            head = tmp;
        else
            tail.next = tmp;

        tail = tmp;
        current = tmp;
        length++;
        return this;
    }

    @Override
    public E retrieve() {
        if (!isEmpty()) {
            System.out.println("GOT HERE");
            System.out.println(this.current.data);
            return this.current.data;
        }
        else {
            //eroare
            return null;
        }
    }

    @Override
    public ListInterface<E> remove() {
//        if(length != 0){
//            if(current == tail)
//
//        } else {
//            current = null;
//            //error
//            return null;
//        }
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
