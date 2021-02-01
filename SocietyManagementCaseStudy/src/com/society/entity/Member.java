package com.society.entity;

public class Member{

    public  Integer flatNo;
    public  String name;
    public  String socName;

    public Member(Integer flatNo, String name, String socName) {
        this.flatNo = flatNo;
        this.name = name;
        this.socName = socName;
    }

    public String getSocName() {
        return socName;
    }

    public String getName() {
        return name;
    }

    public void setFlatNo(Integer flatNo) {
        this.flatNo = flatNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSocName(String socName) {
        this.socName = socName;
    }

    public Integer getFlatNo() {
        return flatNo;
    }

    public Member() {
        flatNo = 0;
        name = "Null";
    }

    public String memberName(){
        String memberName = name+",";
        return memberName;
    }

    @Override
    public String toString() {

        String memberStr = "\nMember { " +
                "FlatNo : " + flatNo +
                ", Name : " + name +" }";
//        System.out.println(memberStr);
        return memberStr;
    }

    public String memberInfo(String socName) {

        String memberStr = "\n"+socName+" "+ flatNo +","+ name +",";
        return memberStr;
    }

    public String columnWiseMemberInformation(String[] arrayOfMembers){


        String socName = arrayOfMembers[0];
        String flatNo = arrayOfMembers[1];
        String name = arrayOfMembers[2];

        String socInformation = " "+socName+" "+flatNo+" "+name+" ";
        return socInformation;
    }


}