package com.society.csv.api;

import java.util.ArrayList;
import java.util.List;

public class ColumnConfig {
    List<ColumnDetails> columnDetailsList = new ArrayList<>(); // list of column details

    public void addColumnDetailIntoList(String objectFieldName, String colDataType, String colTitle){ //just add to list
        columnDetailsList.add(new ColumnDetails(objectFieldName,colDataType,colTitle)); // adds through inner class // creates new column detail object
    };

    public List<ColumnDetails> getColumnDetailsList() {
        return columnDetailsList;
    } // get list

    public class ColumnDetails{ // inner class
        String ObjectFieldName;
        String colDataType;
        String colTitle;

        public ColumnDetails(String objectFieldName, String colDataType, String colTitle) {
            this.ObjectFieldName = objectFieldName;
            this.colDataType = colDataType;
            this.colTitle = colTitle;
        }

        public String getObjectFieldName() {
            return ObjectFieldName;
        }

        public String getColDataType() {
            return colDataType;
        }

        public String getColTitle() {
            return colTitle;
        }

        public void setObjectFieldName(String objectFieldName) {
            this.ObjectFieldName = objectFieldName;
        }

        public void setColDataType(String colDataType) {
            this.colDataType = colDataType;
        }

        public void setColTitle(String colTitle) {
            this.colTitle = colTitle;
        }
    }
}
