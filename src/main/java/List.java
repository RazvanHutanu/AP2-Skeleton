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
        Node tmp = new Node(d, null, null);

        if( isEmpty() ) {
            head = tmp;
            tail = tmp;
            current = tmp;
            length++;
            return this;
        }

        if(tmp.data.compareTo(head.data) < 0){
            head.prior = tmp;
            tmp.next = head;
            head = tmp;
            current = tmp;
            length++;
            return this;
        }

        Node tmp2 = head;
        while(tmp2 != null) {

            if (tmp.data.compareTo(tmp2.data) < 0) {
                tmp2.prior.next = tmp;
                tmp.prior = tmp2.prior;
                tmp.next = tmp2;
                tmp2.prior = tmp;


                current = tmp;
                length++;
                return this;
            }

            tmp2 = tmp2.next;

        }

        tmp.prior = tail;
        tail.next = tmp;
        tail = tmp;
        current = tmp;
        length++;
        return this;
    }

    private void printList() {
        Node tmp = head;
        if(!isEmpty())
            while(tmp != null) {
                System.out.print(tmp.data + " - ");
                tmp = tmp.next;
            }
    }

    @Override
    public E retrieve() {
        if (isEmpty())
            return null;
        return current.data;

    }

    @Override
    public ListInterface<E> remove() {
        if (this.isEmpty())
            return null;
        if (current.prior == null && this.size() > 2) {
            current = current.next;
            current.prior = null;
            head = current;
            length--;
            return this;
        }
        if (this.size() == 1) {
            current = null;
            length--;
            return this;
        } else if (current.next == null) {
            current = current.prior;
            current.next = null;
            length--;
            return this;
        } else {
            Node tmp = current.prior;
            current = current.next;
            tmp.next = current;
            current.prior = tmp;
            length--;
            return this;
        }
    }


    @Override
    public boolean find(E d) {
        Node tmp = head;

        if (isEmpty()) {
            current = null;
            return false;
        }

        while(tmp != null) {

            if(d.compareTo(tmp.data) == 0) {
                current = tmp;
                return true;
            }
            tmp = tmp.next;
        }

        if (head.data.compareTo(d) > 0) {
            current = head;
            return false;
        }
        tmp = head;

        while(tmp.data.compareTo(d) < 0 && tmp.next != null){
            tmp = tmp.next;
        }
        if(tmp.data.compareTo(d) < 0) {
            current = tmp;
        }
        else {
            current = tmp.prior;
            return false;
        }

        return false;
        }



    @Override
    public boolean goToFirst() {
        if(isEmpty()){
            return false;
        }
        else {
            current = head;
            return true;
        }

    }

    @Override
    public boolean goToLast() {
        if(isEmpty()){
            return false;
        }
        else {
            System.out.println("Got to the last");
            current = tail;
            return true;
        }

    }

    @Override
    public boolean goToNext() {
        if(isEmpty() || current == tail){
            return false;
        }
        else {
            current = current.next;
            return true;
        }

    }

    @Override
    public boolean goToPrevious() {
        if(isEmpty() || current == head){
            return false;
        }
        else {
            current = current.prior;
            return true;
        }

    }

    @Override
    public ListInterface<E> copy() {
        return this;
    }

}
