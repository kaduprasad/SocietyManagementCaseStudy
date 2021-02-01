package com.society.csv.service;

import com.society.entity.MemberMaintenance;
import com.society.csv.api.AbstractCsvService;
import com.society.csv.api.ColumnConfig;
import com.society.csv.api.CustomFileWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MemberMaintenanceCsvService extends AbstractCsvService<MemberMaintenance> {

    public MemberMaintenanceCsvService() {
        initColumnConfig();
        fileConfig = new CustomFileWriter("MemberMaintenance","C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\SocietyManagement\\resources\\",".csv");
    }

    @Override
    public void initColumnConfig() {
        columnConfig = new ColumnConfig();

        columnConfig.addColumnDetailIntoList("MemberName","String","Member name");
        columnConfig.addColumnDetailIntoList("Amount","int","Maintenance Amount.");
        columnConfig.addColumnDetailIntoList("MaintenanceDate","LocalDate","Maintenance Date");
    }

    @Override
    public List<MemberMaintenance> readObjectsList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        fileConfig.readLine(); // for read headers and leave the 1st line
        String line = fileConfig.readLine();
        List<MemberMaintenance> memberMaintenanceList = new ArrayList<>();

        while (line != null) {

            MemberMaintenance memberMaintenance = readRow2(line, new MemberMaintenance());
            memberMaintenanceList.add(memberMaintenance);
            line = fileConfig.readLine();
        }
        return memberMaintenanceList;
    }

    @Override
    public void appendToFile(List<MemberMaintenance> memberList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        if(memberList == null)
        return;

        if(!fileConfig.isFileExists())
            addHeader();


        for (MemberMaintenance memberMaintenance : memberList) {
            addRow(memberMaintenance);
        }

    }

    @Override
    public void writeToFile(List<MemberMaintenance> memberList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

        if(memberList == null )
             return;

        if(!fileConfig.isFileExists())
            addHeader();

        for (MemberMaintenance memberMaintenance : memberList) {
            writeRow(memberMaintenance);
        }

    }

    @Override
    public void deleteByName(String name) {
//        MemberMaintenance memberToBeDeleted = null;
//        List<MemberMaintenance> memberMaintenanceList = readObjectsList();
//        for (MemberMaintenance memberMaintenance : memberMaintenanceList) {
//            if(name.equals(memberMaintenance.getMemberName())){
//                memberToBeDeleted = memberMaintenance;
//            }
//        }
//        if(memberToBeDeleted != null)
//            memberMaintenanceList.remove(memberToBeDeleted);
//
//        writeToFile(memberMaintenanceList);
    }

    @Override
    public void delete(List listOfObjects) {

    }

    @Override
    public void generateFile() {

    }

    @Override
    public void appendToFile(MemberMaintenance object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        addRow(object);
    }
}
