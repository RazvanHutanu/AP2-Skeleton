public class Set<E extends Comparable<E>> implements SetInterface<E>{
    private ListInterface<E> list = new List<>();

    Set(){
        list.init();
    }

    @Override
    public void add(E value) {
        list.insert(value);
    }

    public ListInterface<E> getList(){
        return list.copy();
    }

    @Override
    public int getSize(){
        return list.size();
    }

    private Set<E> copySet(){
        Set<E> set2 = new Set<>();
        set2.list = list.copy();
        return set2;
    }

    @Override
    public SetInterface<E> difference(SetInterface<E> list){
        ListInterface<E> set2 = list.getList();
        SetInterface<E> aux = new Set();

        this.list.goToFirst();

        do{
            if(! set2.find(this.list.retrieve()))
                aux.add(this.list.retrieve());
        }while(this.list.goToNext());

        return aux;
    }

    @Override
    public SetInterface<E> intersection(SetInterface<E> list){
        ListInterface<E> set2 = list.getList();
        SetInterface<E> aux = new Set();

        this.list.goToFirst();

        do{
            if(set2.find(this.list.retrieve()))
                aux.add(this.list.retrieve());
        }while(this.list.goToNext());

        return aux;
    }

    @Override
    public SetInterface<E> union(SetInterface<E> list) {
        ListInterface<E> set2 = list.getList();
        SetInterface<E> aux = new Set();

        this.list.goToFirst();

        do{
            aux.add(this.list.retrieve());
        }while(this.list.goToNext());

        set2.goToFirst();

        do{
            if(! this.list.find(set2.retrieve()))
                aux.add(set2.retrieve());
        }while(set2.goToNext());

        System.out.println(aux.toString());
        return aux;
    }

    @Override
    public SetInterface<E> symmetricDifference(SetInterface<E> list) {
        ListInterface<E> set2 = list.getList();
        SetInterface<E> aux = new Set();

        this.list.goToFirst();

        do{
            if(! set2.find(this.list.retrieve()))
                aux.add(this.list.retrieve());
        }while(this.list.goToNext());

        set2.goToFirst();

        do{
            if(! this.list.find(set2.retrieve()))
                aux.add(set2.retrieve());
        }while(set2.goToNext());

        return aux;
    }

    @Override
    public String toString(){
        String tmp = "";
        Set<E> set2 = copySet();

        set2.list.goToFirst();

        do{
            tmp = tmp + " " + set2.list.retrieve();
        }while(this.list.goToNext());

        return tmp;
    }

}
