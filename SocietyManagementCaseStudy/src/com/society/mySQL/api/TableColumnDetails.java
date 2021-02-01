package com.society.mySQL.api;

public class TableColumnDetails {

    String columnName;
    String colDataType;

    public TableColumnDetails(String columnName, String colDataType) {
        this.columnName = columnName;
        this.colDataType = colDataType;
    }
}
