package com.society.csv.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface CsvInterface<T> {

    void addHeader() throws IOException;
    void addRow(T row) throws NoSuchMethodException,IllegalAccessException , InvocationTargetException;
    T readRow(String line, T object) throws NoSuchMethodException,IllegalAccessException , InvocationTargetException;
    List<T> readObjectsList() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    void initColumnConfig();
    void generateFile() throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    void appendToFile(List<T> objects) throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    void appendToFile(T object) throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException;
    void writeToFile(List<T> objects) throws  InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException;
    void deleteByName(String name) throws  NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException;
    void delete(List<T> listOfObjects);
}
