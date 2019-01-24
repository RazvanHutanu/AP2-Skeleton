public interface SetInterface<E extends Comparable<E>> {
    /**
     * @precondition
     * The value to be added is not already in the list
     * @postcondition
     * Insert the value in the list
     */
    void add(E value);
    /**
     * @precondition
     * A list to get the size of
     * @postcondition
     * Return the size of the list
     */
    int getSize();
    /**
     * @precondition
     * An existing list
     * @postcondition
     * Return a copy of the list
     */
    ListInterface<E> getList();
    /**
     * @precondition
     * 2 existing sets to make the difference from
     * @postcondition 
     * Return the difference of 2 sets as aux
     */
    SetInterface<E> difference(SetInterface<E> set2);
    /**
     * @precondition
     * 2 existing sets to make the intersection from
     * @postcondition
     * Return the intersection of 2 sets as aux
     */
    SetInterface<E> intersection(SetInterface<E> list);
    /**
     * @precondition
     * 2 existing sets to make the union from
     * @postcondition
     * Return the union of 2 sets as aux
     */
    SetInterface<E> union(SetInterface<E> list);
    /**
     * @precondition
     * 2 existing sets to make the symmetric difference from
     * @postcondition
     * Return the symmetric difference of 2 sets as aux
     */
    SetInterface<E> symmetricDifference(SetInterface<E> list);
    /**
     * @precondition
     * An existing list to be copied
     * @postcondition
     * Return the new copied list as aux
     */
    SetInterface<E> copy(ListInterface<E> toCopy);
    /**
     * @precondition
     * --
     * @postcondition
     * Return the variable converted to a string
     */
    String toString()
    /**
     * @precondition
     * --
     * @postcondition
     * Return a new set which contains the elements from the set to be copied
     */
    Set<E> copySet()
}
