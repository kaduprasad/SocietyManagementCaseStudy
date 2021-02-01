package com.society.mySQL.api;
import java.sql.*;

public class EstablishSQLConnection {

    public Statement statement;
    public ResultSet resultSet;
    public Connection connection;

    public Connection createConnectionWithSQL(String dbName) throws SQLException {
        String sqlUsername = "root";
        String sqlPassword = "prasad";

        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+dbName, sqlUsername , sqlPassword);
        return connection;
    }

    public void executeQuery(String query){
        try {
            statement.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement() {
        return statement;
    }
//    public void DisplayDatabaseTable(String tableName) throws SQLException {
//
//        ResultSet resultSet = statement.executeQuery("select * from "+tableName);
//
//        System.out.println();
//        System.out.println("Roll\tName\tMarks\tClass");
//        while(resultSet.next()){
//            //Retrieve by column name
//            int roll  = resultSet.getInt("roll");
//            String name = resultSet.getString("name");
//            int marks = resultSet.getInt("marks");
//            int divClass = resultSet.getInt("class");
//
//            //Display values
//            System.out.println(roll+"  \t"+ name +" \t  "+marks+"  \t "+divClass);
//        }
//
//        resultSet.close();
//    }
}
