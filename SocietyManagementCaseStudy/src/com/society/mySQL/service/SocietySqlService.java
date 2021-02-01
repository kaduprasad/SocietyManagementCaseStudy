package com.society.mySQL.service;
import com.society.csv.api.ColumnConfig;
import com.society.mySQL.api.AbstractDbService;
import java.sql.SQLException;
import java.util.List;

public class SocietySqlService extends AbstractDbService {

    public void addRowSociety(){

    }

    public void setColumnConfig() {
        columnConfig = new ColumnConfig();

        columnConfig.addColumnDetailIntoList("Name", "String", "Society Name");
        columnConfig.addColumnDetailIntoList("Address", "String", "Society Address");
        columnConfig.addColumnDetailIntoList("NoOfFlats", "int", "Society NoOfFlats");
    }

    public void displaySocietyData() throws SQLException {
        // take soc info (column names) and pass it to the functions
        List<ColumnConfig.ColumnDetails> socColumnList = columnConfig.getColumnDetailsList();

        displayTable("society",socColumnList);
    }
}
