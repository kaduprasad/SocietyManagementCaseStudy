package com.society.csv.api;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;

public abstract class AbstractCsvService<T> implements CsvInterface<T>{

    public ColumnConfig columnConfig;
    public CustomFileWriter fileConfig;
    public AbstractCsvService abstractCsvService;


    @Override
    public void addHeader()  {

        if(fileConfig.isFileExists())                  //                DataType Obj : List
            return;                                    //                    |     |     |
        String header = null;                          //  foreach loop (Society  soc: socList)
        for (ColumnConfig.ColumnDetails columnDetails : columnConfig.getColumnDetailsList()) // ColumnDetails is innerclass if ColumnConfig
                header = (header == null) ? (columnDetails.colTitle) : (header + ","+columnDetails.colTitle); //condition ? ifTrue : ifFalse
        fileConfig.append(header);
    }

    public void addRow(T object) throws NoSuchMethodException , IllegalAccessException , InvocationTargetException{
        String row = null;

            for (ColumnConfig.ColumnDetails columnDetails : columnConfig.getColumnDetailsList()) { // foreach loop for traversing list

                Method method = object.getClass().getMethod("get"+columnDetails.getObjectFieldName());
                Object value = method.invoke(object);

                if(value != null)
                    row = (row == null) ? (value+"") : (row+","+value);
                else
                    row = (row == null) ? ("") : (row+",");
            }
            fileConfig.append(row);
        }

    public T readRow(String line,T object) throws NoSuchMethodException , IllegalAccessException , InvocationTargetException{

        String lineInfo[] = line.split(",");

        for (int i = 0; i < columnConfig.getColumnDetailsList().size(); i++) {
            ColumnConfig.ColumnDetails columnDetails = columnConfig.getColumnDetailsList().get(i);
                Method method = object.getClass().getMethod("set" + columnDetails.getObjectFieldName(),
                        columnDetails.getColDataType().equals("String") ? String.class : Integer.class);
                method.invoke(object, columnDetails.getColDataType().equals("String") ? lineInfo[i] : Integer.valueOf(lineInfo[i]));

        }
        return object;
    }

    public T readRow2(String line,T object)  throws NoSuchMethodException , IllegalAccessException , InvocationTargetException{

        String lineInfo[] = line.split(",");

        for (int i = 0; i < columnConfig.getColumnDetailsList().size(); i++) {
            ColumnConfig.ColumnDetails columnDetails = columnConfig.getColumnDetailsList().get(i);
//                Method method = object.getClass().getMethod("set" + TableColDetails.getObjectFieldName(),
//                        TableColDetails.getColDataType().equals("String") ? String.class : Integer.class );
////                method.invoke(object, TableColDetails.getColDataType().equals("String") ? lineInfo[i] : Integer.valueOf(lineInfo[i]));

            if(columnDetails.getColDataType().equals("String")){
                Method method = object.getClass().getMethod("set" + columnDetails.getObjectFieldName(), String.class);
                method.invoke(object,  lineInfo[i]);
            }
            else if(columnDetails.getColDataType().equals("int")){
                Method method = object.getClass().getMethod("set" + columnDetails.getObjectFieldName(), Integer.class);
                method.invoke(object, Integer.valueOf(lineInfo[i]));
            }
            else if(columnDetails.getColDataType().equals("LocalDate")){
                Method method = object.getClass().getMethod("set" + columnDetails.getObjectFieldName(), LocalDate.class);
                method.invoke(object,LocalDate.parse(lineInfo[i]));
            }
        }
        return object;
    }


    public void writeRow(T object) throws IOException,NoSuchMethodException , IllegalAccessException, InvocationTargetException {//(Object object,Class objectClass) {
        String row = null;

        for (ColumnConfig.ColumnDetails columnDetails : columnConfig.getColumnDetailsList()) {

            Method method = object.getClass().getMethod("get"+columnDetails.getObjectFieldName());
            Object value = method.invoke(object);

            if(value != null)
                row = (row == null) ? (value+"") : (row+","+value);
            else
                row = (row == null) ? ("") : (row+",");

        }
        fileConfig.write(row);
    }
}