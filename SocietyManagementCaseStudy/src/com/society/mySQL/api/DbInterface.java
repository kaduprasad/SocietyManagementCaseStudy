package com.society.mySQL.api;

import com.society.csv.api.ColumnConfig;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public interface DbInterface {
    Scanner scan = new Scanner(System.in);
//    EstablishSQLConnection connect = new EstablishSQLConnection();

    void insertRowIntoTable(String table) throws SQLException;
    void displayTable(String tableName, List<ColumnConfig.ColumnDetails> details) throws SQLException;
    void deleteRowFromTable() throws SQLException;
}
