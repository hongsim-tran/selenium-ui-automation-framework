package simtran.core.exceptions;

public class ProvidedClassNotAnEnum extends IllegalArgumentException{

    public ProvidedClassNotAnEnum(String e){
        super(String.format("Provided class %s is not an Enum type", e));
    }
}
