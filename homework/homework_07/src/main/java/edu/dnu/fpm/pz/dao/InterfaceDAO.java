package edu.dnu.fpm.pz.dao;

import edu.dnu.fpm.pz.myexceptions.MyException;

import java.util.List;

public interface InterfaceDAO<T> {
    void create(T entity) throws MyException;
    void create(List<T> entities) throws MyException;
    List<T> read() throws MyException;
    T read(int id) throws MyException;
    void update(T entity) throws MyException;
    void update(List<T> entities) throws MyException;
    void delete(int entityId) throws MyException;
}
