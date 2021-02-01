package com.society.csv.service;

import com.society.entity.Member;
import com.society.entity.Society;
import com.society.csv.api.AbstractCsvService;
import com.society.csv.api.ColumnConfig;
import com.society.csv.api.CustomFileWriter;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SocietyCsvService extends AbstractCsvService<Society> {

    List<Society> societyList;
    MemberCsvService memberCsvService;

    @SuppressWarnings("unchecked")
    
	public SocietyCsvService( List societyList) {
        this.societyList = societyList;
        memberCsvService = new MemberCsvService(societyList);
        initColumnConfig();
        fileConfig = new CustomFileWriter("Society", "C:\\Program Files\\Apache Software Foundation\\Tomcat 9.0\\webapps\\SocietyManagement\\resources\\", ".csv");
        //new file created.
    }

    public void initColumnConfig() {
        columnConfig = new ColumnConfig();

        columnConfig.addColumnDetailIntoList("Name", "String", "Society Name");
        columnConfig.addColumnDetailIntoList("Address", "String", "Society Address");
        columnConfig.addColumnDetailIntoList("NoOfFlats", "int", "Society NoOfFlats");
    }

    public Map<String, List<Member>> getSocMemberMap() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException  {
        List<Member> members = memberCsvService.readObjectsList();
        Map<String, List<Member>> socMembers = new HashMap<>();

        for (Member member : members) {
            String socName = member.getSocName();
            List<Member> memberList = socMembers.get(socName);
            if (memberList == null) {
                memberList = new ArrayList<>();
                memberList.add(member);
            } else {
                memberList.add(member);
            }
            socMembers.put(socName, memberList);
        }
        return socMembers;
    }

    @Override
    public List<Society> readObjectsList() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        // memberCsvService.readObjectsList();

        fileConfig.readLine(); // for reading header
        String line = fileConfig.readLine();
        Map<String, List<Member>> socMembers = getSocMemberMap();

        while (line != null) {

            Society society = readRow(line, new Society());

            List<Member> memberList = socMembers.get(society.getName());
            society.setMemberList(memberList);
            societyList.add(society);
            line = fileConfig.readLine();
        }
        return societyList;

    }

    @Override
    public void generateFile() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        addHeader();
        for (Society society : societyList) {
            addRow(society);
        }
        memberCsvService.generateFile();
    }

    @Override
    public void appendToFile(List<Society> currentSocList) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {

        if (!fileConfig.isFileExists())
            addHeader();
        for (Society society : currentSocList) {
            addRow(society);
        }
        for (Society society : currentSocList) {
            memberCsvService.appendToFile(society.getMemberList());
        }
    }

    public void writeToFile(List<Society> currentSocList) throws InvocationTargetException, NoSuchMethodException, IllegalAccessException, IOException {


            if (!fileConfig.isFileExists())
                addHeader();


            for (Society society : currentSocList) {
                writeRow(society);
            }
            for (Society society : currentSocList) {
                memberCsvService.writeToFile(society.getMemberList());
            }

    }

    @Override
    public void appendToFile(Society object) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        addRow(object);
    }

    @Override
    public void delete(List<Society> listOfObjects) {

    }

    public void deleteByName(String name) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, IOException {


        Society socToBeDeleted = null;
        List<Society> socList = readObjectsList();
        for (Society society : socList) {
            if (name.equals(society.getName())) {
                socToBeDeleted = society;
            }
        }

        if (socToBeDeleted != null) {
            socList.remove(socToBeDeleted);
            List<Member> memberList = socToBeDeleted.getMemberList();
            memberCsvService.delete(memberList);
        }
        writeToFile(socList);

    }
}