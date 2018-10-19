public interface SetInterface<E extends Comparable<E>> {
    void add(E value);
    int getSize();
    ListInterface<E> getList();
    SetInterface<E> difference(SetInterface<E> set2);
    SetInterface<E> intersection(SetInterface<E> list);
    SetInterface<E> union(SetInterface<E> list);
    SetInterface<E> symmetricDifference(SetInterface<E> list);
}
