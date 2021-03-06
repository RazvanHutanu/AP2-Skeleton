public class Identifier implements IdentifierInterface{

    StringBuffer buffer;

    Identifier(){
        buffer = new StringBuffer();
    }

    public void add (char toAdd){
        buffer.append(toAdd);
    }

    @Override
    public boolean equals (Object obj){
        if(this == obj) return true;
        if(obj == null) return false;
        if(getClass() != obj.getClass()) return false;
        Identifier tmp = (Identifier) obj;
        if(buffer == null) return false;
        if(tmp.buffer == null) return false;

        return tmp.buffer.toString().equals(buffer.toString());
    }

    public StringBuffer getIdentifier(){
        return buffer;
    }

    public int getSize(){
        return buffer.length();
    }

    public void print(){
        System.out.println(buffer + "\n");
    }

    @Override
    public int hashCode(){
        return buffer.toString().hashCode();
    }
}
