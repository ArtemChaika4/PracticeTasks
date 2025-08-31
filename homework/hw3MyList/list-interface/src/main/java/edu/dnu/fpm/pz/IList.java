package edu.dnu.fpm.pz;

public interface IList<T> {
    T get(int index) throws MyException;
    void add(int index, T element) throws MyException;
    void addFirst(T element);
    void addLast(T element);
    T remove(int index) throws MyException;
    void replace(int index, T element) throws MyException;
    int size();
    boolean isEmpty();
}
