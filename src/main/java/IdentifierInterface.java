public interface IdentifierInterface {
    /**************************************
     *
     * @param toAdd
     */
    
    /**
     * @precondition
     * An existing identifier 
     * @postcondition
     * A character has been added to the identifier
     */
    public void add (char toAdd);

    /**
     * @precondition 
     * There are 2 identifiers which are not empty
     * @postcondition
     * Return a boolean value
     * False: If they are not equal
     * True: If they are equal
     */
    public boolean equals ();

    /**
     * @precondition 
     * An existing identifier to get
     * @postcondition
     * Return the identifier
     */
    public StringBuffer getIdentifier();
    /**
     * @precondition
     * An existing identifier to get its size
     * @postcondition
     * Return the length of the identifier
     */
    public int getSize();
    /**
     * @precondition
     * --
     * @postcondition
     * Return a string with the hashcode
     */
    public int hashCode();

    public void print();
}
