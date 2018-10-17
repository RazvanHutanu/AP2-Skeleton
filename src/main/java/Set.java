public class Set<E extends Comparable<E>> implements SetInterface<E>{
    private ListInterface<E> set = new List<>();

    Set(){
        set.init();
    }

    @Override
    public void add(E value) {
        set.insert(value);
    }

    private ListInterface<E> getList(){
        return set.copy();
    }

    @Override
    public int getSize(){
        return set.size();
    }

    @Override
    public SetInterface<E> difference(SetInterface<E> list){
        ListInterface<E> set2 = getList();
        SetInterface<E> aux = new Set();

        set.goToFirst();

        do{
            if(! set2.find(set.retrieve()))
                aux.add(set.retrieve());
        }while(set.goToNext());

        return aux;
    }

    @Override
    public SetInterface<E> intersection(SetInterface<E> list){
        ListInterface<E> set2 = getList();
        SetInterface<E> aux = new Set();

        set.goToFirst();

        do{
            if(set2.find(set.retrieve()))
                aux.add(set.retrieve());
        }while(set.goToNext());

        return aux;
    }

    @Override
    public SetInterface<E> union(SetInterface<E> list) {
        ListInterface<E> set2 = getList();
        SetInterface<E> aux = new Set();

        set.goToFirst();

        do{
            aux.add(set.retrieve());
        }while(set.goToNext());

        set2.goToFirst();

        do{
            if(! set.find(set2.retrieve()))
                aux.add(set2.retrieve());
        }while(set2.goToNext());

        return aux;
    }

    @Override
    public SetInterface<E> symmetricDifference(SetInterface<E> list) {
        ListInterface<E> set2 = getList();
        SetInterface<E> aux = new Set();

        set.goToFirst();

        do{
            if(! set2.find(set.retrieve()))
                aux.add(set.retrieve());
        }while(set.goToNext());

        set2.goToFirst();

        do{
            if(! set.find(set2.retrieve()))
                aux.add(set2.retrieve());
        }while(set2.goToNext());

        return aux;
    }


}
