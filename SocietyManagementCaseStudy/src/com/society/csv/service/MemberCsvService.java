package com.society.csv.service;

import com.society.entity.Member;
import com.society.entity.Society;
import com.society.csv.api.AbstractCsvService;
import com.society.csv.api.ColumnConfig;
import com.society.csv.api.CustomFileWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

public class MemberCsvService extends AbstractCsvService<Member> {

    List<Society> socList;

    public MemberCsvService(List socList) {
        this.socList = socList;

        initColumnConfig();
        fileConfig = new CustomFileWriter("Member","C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\SocietyManagement\\resources\\",".csv");
    }


    public void initColumnConfig(){
        columnConfig = new ColumnConfig();

        columnConfig.addColumnDetailIntoList("Name","String","Member name");
        columnConfig.addColumnDetailIntoList("FlatNo","int","Member FlatNo.");
        columnConfig.addColumnDetailIntoList("SocName","String","Member socName");
    }

    @Override
    public List<Member> readObjectsList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        fileConfig.readLine(); // for read headers and leave the 1st line
        String line = fileConfig.readLine();
        List<Member> memberList = new ArrayList<>();

        while (line != null) {

            Member member = readRow(line, new Member());
            memberList.add(member);
            line = fileConfig.readLine();
        }
        return memberList;

    }

    @Override
    public void generateFile() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        addHeader();
        for (Society society : socList)
            for (Member member : society.getMemberList())
                addRow(member);
    }

    @Override
    public void appendToFile(List<Member> memberList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        if (memberList == null)
            return;

        if (!fileConfig.isFileExists())
            addHeader();

        for (Member member : memberList) {
            addRow(member);
        }

    }

    @Override
    public void appendToFile(Member object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        addRow(object);
    }

    public void writeToFile(List<Member> memberList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {

        if (memberList == null)
            return;

        if (!fileConfig.isFileExists())
            addHeader();

        for (Member member : memberList) {
            writeRow(member);
        }

    }

    @Override
    public void deleteByName(String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {

            Member memberToBeDeleted = null;
            List<Member> memberList = readObjectsList();
            for (Member member : memberList) {
                if (name.equals(member.getName())) {
                    memberToBeDeleted = member;
                }
            }
            if (memberToBeDeleted != null)
                memberList.remove(memberToBeDeleted);

            writeToFile(memberList);
    }

    public void delete(List<Member> memberList) {
//        Member memberToBeDeleted = null;
//        List<Member> memberList = readObjectsList();
//        for (Member member : memberList) {
//            if(name.equals(member.getName())){
//                memberToBeDeleted = member;
//            }
//        }
//        if(memberToBeDeleted != null)
//            memberList.remove(memberToBeDeleted);
//
//        writeToFile(memberList);
    }
}
