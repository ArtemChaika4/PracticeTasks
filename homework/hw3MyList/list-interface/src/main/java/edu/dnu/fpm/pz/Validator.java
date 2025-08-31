package edu.dnu.fpm.pz;
public class Validator {
    public static void checkListIndex(int index, int size) throws MyException {
        if(index < 0 || index >= size){
            throw new MyException("Index out of bounds: index = " + index + ", size = " + size);
        }
    }
    public static void checkInitialCapacity(int initialCapacity) throws MyException {
        if(initialCapacity < 0){
            throw new MyException("Illegal Capacity: " + initialCapacity);
        }
    }
}
