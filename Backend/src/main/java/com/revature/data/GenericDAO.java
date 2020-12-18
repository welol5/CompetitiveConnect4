package com.revature.data;

import java.util.List;

public interface GenericDAO <T>{
    public T add(T t);
    public T getById(Integer id);
    public List<T> getAll();
    public void update(T t);
    public void delete(T t);
}
