package com.society.entity;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class MemberMaintenance {
    String memberName;
    Integer amount;
    Date date = new Date();
    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy");
    String stringDate = dateFormat.format(date);
    LocalDate maintenanceDate;

    public MemberMaintenance( String memberName, Integer amount, LocalDate maintenanceDate) {
        this.memberName = memberName;
        this.amount = amount;
        this.maintenanceDate = maintenanceDate;
    }

    public String getMemberName() {
        return memberName;
    }

    @Override
    public String toString() {
        return  "\nMemberMaintenanceInfo [ " +
                " memberName : " + memberName +
                ", Amount : " + amount +
                ", Maintenance Date : " + maintenanceDate +
                " ]";
    }

    public String memberNames()  {
        String memberNames = "Member : "+ memberName;
        System.out.println(memberNames);
        return memberNames;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public LocalDate getMaintenanceDate() {
        return maintenanceDate;
    }

    public void setMaintenanceDate(LocalDate maintenanceDate) {
        this.maintenanceDate = maintenanceDate;
    }

    public MemberMaintenance() {
        memberName = null;
        amount = 0;
        maintenanceDate = null;
    }

//    public Member searchForMemberByName(String memberName){
//        Member member = null;
//        for (Member memberOfSoc : memberList) {
//            if (memberName.equals(memberOfSoc.getName().trim())){
//                member = memberOfSoc;
//            }
//        }
//        return member;
//    }


}
