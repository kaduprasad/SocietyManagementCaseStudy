package com.society.mySQL.api;
import com.society.csv.api.ColumnConfig;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDbService implements DbInterface{
    EstablishSQLConnection connect = new EstablishSQLConnection();
    public ColumnConfig columnConfig;

    public void DisplayDatabaseTable(String tableName ) throws SQLException {

        Statement statement = connect.getStatement();
        ResultSet resultSet = statement.executeQuery("select * from "+tableName);

        while(resultSet.next()){
            //Retrieve by column name
            int roll  = resultSet.getInt("roll");
            String name = resultSet.getString("name");
            int marks = resultSet.getInt("marks");
            int divClass = resultSet.getInt("class");

            //Display values
            System.out.println(roll+"  \t"+ name +" \t  "+marks+"  \t "+divClass);
        }
        resultSet.close();
    }

    @Override
    public void deleteRowFromTable() throws SQLException {

    }


    @Override
    public void insertRowIntoTable(String tableName) throws SQLException {

    }

    public  ResultSet executeDisplayQuery(String entity) throws SQLException {
        Statement statement = connect.getStatement();
        ResultSet resultSet = statement.executeQuery("select * from "+ entity);
        return resultSet;
    }

    @Override
    public void displayTable(String tableName, List<ColumnConfig.ColumnDetails> columnDetails) throws SQLException {

        ResultSet resultSet = executeDisplayQuery(tableName);

        while (resultSet.next()){ // use java reflection here
            String socName  = resultSet.getString(columnDetails.get(0).getObjectFieldName());
            String address = resultSet.getString(columnDetails.get(1).getObjectFieldName());
            int noOfFlats = resultSet.getInt(columnDetails.get(2).getObjectFieldName());

            String displayColumns = "";
            for (int i = 0; i < columnDetails.size(); i++) {
                displayColumns = displayColumns + columnDetails.get(i) + " \t ";
            }
            //Display values
            System.out.println(socName+"  \t"+ address +" \t  "+noOfFlats+"  \t ");
            System.out.println(displayColumns);
        }
        resultSet.close();
    }

    public void createTable(){
        System.out.println("Enter Table name");
        String tableName = scan.next();

        List<TableColumnDetails> colDetailsList = new ArrayList<>();

        while(true){
            System.out.println("1.Enter colName & dataType \n2.Exit");
            int choice = scan.nextInt();
            if(choice == 1){
                TableColumnDetails details = new TableColumnDetails(scan.next(),scan.next());
                colDetailsList.add(details);
            }else
                break;
        }

        String columns = "";
        for (int i = 0; i < colDetailsList.size(); i++) {
            if(i == colDetailsList.size()-1)
                columns = columns+" "+colDetailsList.get(i).columnName+" "+colDetailsList.get(i).colDataType;
            else
                columns = columns+" "+colDetailsList.get(i).columnName+" "+colDetailsList.get(i).colDataType+",";
        }

        String sqlCreateTableQuery = "create table"+tableName+"("+columns+");";
        connect.executeQuery(sqlCreateTableQuery);
    }
}
