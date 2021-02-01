package com.society.input;

import com.society.entity.Member;
import com.society.entity.Society;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("unused")
public class MemberInput extends AbstractUserInput<Member> {
    Scanner scan = new Scanner(System.in);
    SocietyInput societyInput;

    public static final int SHOW_MEMBER_LIST = 1;
    public static final int ADD_MEMBER_IN_NEWLIST = 2;
    public static final int ADD_MEMBER_IN_LIST = 3;
    public static final int REMOVE_MEMBER = 4;
    public static final int EXIT = 5;

    public static void printInstructionsOfMember() {
        System.out.println();
        System.out.println(" 2.1. Print MembersList ");
        System.out.println(" 2.2. Add Member in newList");
        System.out.println(" 2.3. Add Member");
        System.out.println(" 2.4. Remove Member");
        System.out.println(" 2.5. Exit.");
    }
//

    private Member readMember() {

        System.out.println("Enter member name :");
        Member member = new Member(0, scan.next(), null);
        return member;
    }

    public boolean removeMemberFromSociety(Society societyObj) {

        System.out.println("Enter the Name of member to be Removed :");
        String name = scan.next();
        for (Member member : societyObj.memberList) {
            if (name.equals(member.memberName())) {
                societyObj.removeMember(member);
                return true;
            }
        }
        return false;
    }

//    public void memberOptions(Society societyObj) {
//        int option = 0;
//        List<Member> newMemberList = new ArrayList<>();
//
//        while (option != EXIT) {
//            printInstructionsOfMember();
//            System.out.println("Enter your Choice :");
//            option = scan.nextInt();
//
//            switch (option) {
//                case SHOW_MEMBER_LIST:// option-- 1
//                    societyObj.memberNames();
//                    break;
//
//                case ADD_MEMBER_IN_NEWLIST:// option-- 2
//                    Member memberObj = readMember();
//                    memberObj.setSocName(societyObj.getName());
////                    societyObj.addMember(memberObj);
//                    societyObj.addMemberToGivenMemberList(newMemberList,memberObj);
//                    societyObj.memberNames();
//                    break;
//
//                case ADD_MEMBER_IN_LIST: //  option-- 3
//                    Member member = readMember();
//                    member.setSocName(societyObj.getName());
//                    societyObj.addMember(member);
//                    break;
//
//                case REMOVE_MEMBER: // 4
//                    societyObj.memberNames();
//                    try {
//                        boolean isRemoved = removeMemberFromSociety(societyObj); // remove member
////                         logger.info("Operation performed of removing member status "+isRemoved);
//                    }catch (Exception e){
////                        logger.error("Error occured while removing member ",e);
//                    }
//                    break;
//
//                case 5:
//                    break;
//            }
//        }
//    }
}