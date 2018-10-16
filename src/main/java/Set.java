public class Set extends List implements SetInterface<StringBuffer>{
    private ListInterface<StringBuffer> set = new List<>();

    Set(){
        set.init();
    }

    @Override
    public void add(StringBuffer value) {
        set.insert(value);
    }

    @Override
    public ListInterface<StringBuffer> getList(){
        return set.copy();
    }

    @Override
    public int getSize(){
        return set.size();
    }

    @Override
    public SetInterface<StringBuffer> difference(SetInterface<StringBuffer> list){
        ListInterface<StringBuffer> set2 = getList();
        SetInterface<StringBuffer> aux = new Set();

        set.goToFirst();

        do{
            if(! set2.find(set.retrieve()))
                aux.add(set.retrieve());
        }while(set.goToNext());

        return aux;
    }

    @Override
    public SetInterface<StringBuffer> intersection(SetInterface<StringBuffer> list){
        ListInterface<StringBuffer> set2 = getList();
        SetInterface<StringBuffer> aux = new Set();

        set.goToFirst();

        do{
            if(set2.find(set.retrieve()))
                aux.add(set.retrieve());
        }while(set.goToNext());

        return aux;
    }

    @Override
    public SetInterface<StringBuffer> union(SetInterface<StringBuffer> list) {
        ListInterface<StringBuffer> set2 = getList();
        SetInterface<StringBuffer> aux = new Set();

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
    public SetInterface<StringBuffer> symmetricDifference(SetInterface<StringBuffer> list) {
        ListInterface<StringBuffer> set2 = getList();
        SetInterface<StringBuffer> aux = new Set();

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
