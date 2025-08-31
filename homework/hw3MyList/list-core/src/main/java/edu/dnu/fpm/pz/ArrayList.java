package edu.dnu.fpm.pz;

public class ArrayList<T> implements IList<T>{
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int size;
    public ArrayList(){
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }
    public ArrayList(int initialCapacity) throws MyException {
        Validator.checkInitialCapacity(initialCapacity);
        array = new Object[initialCapacity];
        size = 0;
    }
    @Override
    public T get(int index) throws MyException {
       Validator.checkListIndex(index, size());
       return (T)array[index];
    }
    private void grow(){
        int minCapacity = size() + 1;
        int oldCapacity = array.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        Object[] newArray = new Object[Math.max(newCapacity, minCapacity)];
        System.arraycopy(array, 0, newArray, 0, size());
        array = newArray;
    }
    @Override
    public void add(int index, T element) throws MyException {
        if(index == 0){
            addFirst(element);
            return;
        }
        Validator.checkListIndex(index, size());
        if(isFull()){
            grow();
        }
        System.arraycopy(array, index, array, index + 1, size() - index);
        array[index] = element;
        size++;
    }
    @Override
    public void addFirst(T element) {
        if(isFull()){
            grow();
        }
        System.arraycopy(array, 0, array, 1, size());
        array[0] = element;
        size++;
    }
    @Override
    public void addLast(T element) {
        if(isFull()){
            grow();
        }
        array[size] = element;
        size++;
    }
    @Override
    public T remove(int index) throws MyException {
        Validator.checkListIndex(index, size());
        T element = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size() - index - 1);
        array[size() - 1] = null;
        size--;
        return element;
    }
    @Override
    public void replace(int index, T element) throws MyException {
        Validator.checkListIndex(index, size());
        array[index] = element;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public boolean isEmpty() {
        return size == 0;
    }
    private boolean isFull(){
        return size == array.length;
    }
}
