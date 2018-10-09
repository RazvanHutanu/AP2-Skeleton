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

        if( head == null ) {
            head = tmp;
            tail = tmp;
        }
        else{
            Node tmp2 = head;
            for(int i = 0; i < length; i++){
                if(current.data.compareTo(tmp2.data) < 0){ // current.data - tmp2.data < 0
                    tmp.prior = tmp2.prior;
                    tmp.next = tmp2;
                    tmp2.prior = tmp;
                }
            }
        }


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
        if(! isEmpty()){
            if(current == tail){
                tail = tail.prior;
                current = tail;
            } else {
                current.prior.next = current.next;
            }
        } else {
            //error
            return null;
        }
        return null;
    }

    @Override
    public boolean find(E d) {
//        Node tmp = head;
//
//        while(tmp.next != null) {
//            if(tmp.data == d)
//            {
//                current = tmp;
//            }
//            else if (isEmpty()) {
//                current = null;
//            }
//            //else if (head > d) {
//                //current = head;
//            }
//            tmp = tmp.next;
//
//        }
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
        return null;
    }
}
