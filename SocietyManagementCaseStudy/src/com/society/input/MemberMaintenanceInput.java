package com.society.input;

import com.society.entity.MemberMaintenance;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MemberMaintenanceInput extends AbstractUserInput<MemberMaintenance> {

    private Scanner scan = new Scanner(System.in);
    public List<MemberMaintenance> memberMaintenanceList ;

    public static final int SHOW_MEMBER_MAINTENANCE_LIST = 1;
    public static final int ADD_MAINTENANCE_IN_LIST = 2;
    public static final int EXIT = 3;

    public MemberMaintenanceInput(List<MemberMaintenance> memberMaintenanceList) {
        this.memberMaintenanceList = memberMaintenanceList;
    }

    private static void printInstructionsOfMemberMaintenance() {
        System.out.println();
        System.out.println(" 1. Print Member MaintenanceList ");
        System.out.println(" 2. Add Member Maintenance");
        System.out.println(" 3. Exit.");
    }

    public MemberMaintenance readMemberMaintenance()  {
         try {
            System.out.println("Enter Member name :");
            String memberName = scan.next();
            System.out.println("Enter Maintenance Amount");
            int maintenanceAmount = scan.nextInt();

            LocalDate date = LocalDate.now();//
            return new MemberMaintenance(memberName, maintenanceAmount, date);
        }
         catch (NullPointerException e){
            System.out.println("Enter Proper values of MemberMaintenance ");
            throw new NullPointerException("Entered Member maintenance values are wrong");
         }
    }

    public void addMemberMaintenanceIntoList(List<MemberMaintenance> currentMemberMaintenanceList)  {
            MemberMaintenance memberMaintenance = readMemberMaintenance();
            currentMemberMaintenanceList.add(memberMaintenance);
            memberMaintenanceList.add(memberMaintenance);
    }

    public void showMemberMaintenanceList() {
        System.out.println("List of Member Maintenance :");
        int index = 1;
        for (MemberMaintenance memberMaintenance : memberMaintenanceList ) {
            System.out.print(index + ". ");
            memberMaintenance.memberNames();
            index++;
        }
    }

    public List<MemberMaintenance> getMemberMaintenanceInput() {
        int option;
        List<MemberMaintenance> currentMemberMaintenanceList = new ArrayList<>();

        do {
            printInstructionsOfMemberMaintenance();
            System.out.println("Enter your Choice :");
            option = scan.nextInt();

            if (option == SHOW_MEMBER_MAINTENANCE_LIST){
                showMemberMaintenanceList();
            }
            else if(option == ADD_MAINTENANCE_IN_LIST) {
                addMemberMaintenanceIntoList(currentMemberMaintenanceList); // add member Maintenance

            }else if(option == EXIT)// option == 3
                break;

        }while (option != EXIT);

        System.out.println(memberMaintenanceList);
        return currentMemberMaintenanceList;
    }
}