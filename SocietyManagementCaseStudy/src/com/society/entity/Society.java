package com.society.entity;



import java.util.ArrayList;
import java.util.List;

public class Society implements Comparable<Society>{
    public String address;
    public String name;
    public Integer noOfFlats;
    public List<Member> memberList = new ArrayList<>();

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public void addMember(Member memberObj) {
        memberList.add(memberObj);
    }

    public void addMemberToGivenMemberList(List<Member> givenMemberList, Member member)  {
        givenMemberList.add(member);
        memberList = givenMemberList;
    }

    public void removeMember(Member memberObj) {
        memberList.remove(memberObj);
    }

    public Society(String address, String name, int noOfFlats) {
        this.address = address;
        this.name = name;
        this.noOfFlats = noOfFlats;
    }

    public Society(){
        address = null;
        name = null;
        noOfFlats = 0;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setNoOfFlats(Integer noOfFlats) {
        this.noOfFlats = noOfFlats;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public Integer getNoOfFlats() {
        return noOfFlats;
    }

    @Override
    public String toString() {
        String socStr =  "\nSociety [ " +
                " SocName : " + name +
                ", Address : " + address +
                ", No_of_flats : " + noOfFlats  +
                " ] \nMemberList : " + memberList +
                '}';
        return  socStr;
    }

    public String socInfo(){
        String socInfo = "\n"+name+","+address+","+noOfFlats+",";//(memberList.get(socIndex-1).memberInfo());
        return socInfo;
    }

    public String columnWiseSocInformation(String[] arrayOfSocities){

        String name = arrayOfSocities[0];
        String addr = arrayOfSocities[1];
        String flats = arrayOfSocities[2];
//        String socInformation = "SocName :"+name+" Address :"+addr+" No Of Flats :"+flats+" ";
        String socInformation = " "+name+" "+addr+" "+flats+" ";

        return socInformation;
    }

    public String societyNames(){
        String socNames = "Society : "+name;
        System.out.println(socNames);
        return socNames;
    }

    public String socName(){
        String socName = name+",";
        System.out.println();
        return socName;
    }

    public List<Member> membersList(){
        return memberList;
    }


    public void memberNames(){
        System.out.println(memberList.toString());
    }

    @Override
    public int hashCode() {

        return noOfFlats.hashCode()+name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj == null || obj.getClass() != this.getClass())
            return false;

        Society society = (Society) obj; // typecast society to obj

        return society.name.equals(this.name) && society.noOfFlats.equals(this.noOfFlats);
    }

    @Override
    public int compareTo(Society society) {
        return name.compareTo(society.getName());
    }
}
