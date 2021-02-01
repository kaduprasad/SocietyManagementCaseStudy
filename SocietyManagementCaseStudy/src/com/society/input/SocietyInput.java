package com.society.input;
import com.society.entity.Society;
import java.util.*;

public class SocietyInput extends AbstractUserInput<Society> {
    private Scanner scan = new Scanner(System.in);
    public List<Society> socList;
    MemberInput memberInput = new MemberInput();
    MemberMaintenanceInput memberMaintenanceInput;

    public static final int ADD_SOCIETY = 1;
    public static final int SHOW_SOCIETY_LIST = 2;
    public static final int DELETE_SOCIETY = 3;
    public static final int SELECT_CURRENT_SOC = 4;
    public static final int MEMBER_MAINTENANCE_OPTIONS = 5;

    public SocietyInput(List<Society> socList) {
        this.socList = socList;
    }

    private Society readSociety() {
        System.out.println("Enter soc name :");
        return new Society(null, scan.next(), 0);
    }
//
//    public void showAndSelectSocietyFromList() {
//        System.out.println("List of Societies :");
//        int index = 1;
//        for (Society society : socList) {
//            System.out.print(index + ". ");
//            society.societyNames();
//            index++;
//        }
//
//        System.out.println(" Select Society for further operations :");
//        int socNumber = scan.nextInt() - 1;
//        if (socNumber > socList.size() - 1) {
//            System.out.println("Invalid society number select. Please try again.");
//        }
//        else{
//                if(socList.size() <= socNumber ){
//                    throw new InputException("Cannot get the entered Society ");
//                }
//                Society society = socList.get(socNumber);
//                memberInput.memberOptions(society);
//
//        }
//    }

    public void addSocIntoList(Society society,List<Society> currentSocList)  {

        society = readSociety();
        socList.add(society);
        currentSocList.add(society);
        System.out.println(society.getName() + " Society added");
    }

    public void removeSocFromList(String socName)  {

            for (Society society : socList) {
                if (socName.equals(society.socName())) {
                    socList.remove(society);
                    System.out.println(socName + " Society Removed");
                }
            }
    }

//    public static void printInstructionsOfSoc() {
//        System.out.println();
//        System.out.println(" 1. Add New Society ");
//        System.out.println(" 2. Show Society List");
//        System.out.println(" 3. delete Society from List");
//        System.out.println(" 4. Select current Soc for Member Options");
//        System.out.println(" 5. Member Maintenance Options");
//        System.out.println(" 6. Exit.");
//    }


//    public List<Society> getInputForSocList() {
//        Society societyObj = null;
//        List<Society> currentSocList = new ArrayList<>();
//
//        int option;
//        do {
//            printInstructionsOfSoc();
//            option = scan.nextInt();
//
//            if (option == ADD_SOCIETY) {  // add society
//                addSocIntoList(societyObj, currentSocList);
//
//            } else if (option == SHOW_SOCIETY_LIST) { // Soclist For operations
//                showAndSelectSocietyFromList();
//
//            } else if (option == DELETE_SOCIETY) {
//                System.out.println("Enter the SocName To be deleted");
//                removeSocFromList(scan.next());
//
//            } else if (option == SELECT_CURRENT_SOC) { //memberOptions
//                memberInput.memberOptions(societyObj);
//
//            } else if (option == MEMBER_MAINTENANCE_OPTIONS) {
//                memberMaintenanceInput.getMemberMaintenanceInput();
//
//            } else if (option == 6) { // Exit
//                break;
//            }
//
//        } while (option < 6);
//
//        if(option > 6) {
//            System.out.println("Please Enter Proper Option");
//            getInputForSocList();
//        }
//
//        System.out.println(socList);
//        return currentSocList;
//    }

//    public void writeToSocietyCsvFile() throws Exception{
//
//        BufferedWriter writeInSoc = new BufferedWriter( new FileWriter("C:/Users/prasad/IdeaProjects/Assignment 1/resources/society.csv")); // FileWriter
//        BufferedWriter writeInMember = new BufferedWriter( new FileWriter("C:/Users/prasad/IdeaProjects/Assignment 1/resources/members.csv"));// Writer
//
//        String socInfo = "SocName,Address,NoOfFlats,";
//        writeInSoc.append(socInfo);
//
//        String memberInfo = "SocName, Flat No.,MemberName,";
//        writeInMember.append(memberInfo);
//
////        for (int socIndex = 0; socIndex < socList.size(); socIndex++) {
//        for (int socIndex = 0; socIndex < socList.size(); socIndex++) {
//
//            ArrayList<Member> membList = socList.get(socIndex).memberList;
//            writeInSoc.append(socList.get(socIndex).socInfo());
//
//            for (int memberIndex = 0 ; memberIndex < membList.size() ; memberIndex++) {
////                writeInSoc.append(membList.get(memberIndex).memberInfo());
//                writeInMember.append(membList.get(memberIndex).memberInfo(socList.get(socIndex).socName()));
//            }
//            System.out.print("\n");
//        }
//
////        writeInSoc.append(socList.toString());
//        writeInMember.close();
//        writeInSoc.close();
//    }
//
//    public void readfromSocCsvFile() throws Exception{
//
//        BufferedReader readFromSoc = new BufferedReader(new FileReader("C:/Users/prasad/IdeaProjects/Assignment 1/resources/society.csv")); // FileWriter
//
//        BufferedReader readFromMember = new BufferedReader(new FileReader("C:/Users/prasad/IdeaProjects/Assignment 1/resources/members.csv")); // Writer
//
//        List<String> readList = new ArrayList();
//
//        String socInfo = readFromSoc.readLine();
//
//        while (socInfo != null) {
//            String societyInfo[] = socInfo.split(",");
//
//            Society soc = new Society();
//            String socList =  soc.columnWiseSocInformation(societyInfo);
//            readList.add(socList);
//            socInfo = readFromSoc.readLine();
//        }
//        System.out.println();
////        for (int i = 0; i < societyInfo.length; i++ ){
//////            System.out.println(societyInfo[i]+" "+societyInfo[i+1]+" "+societyInfo[i+2]+" ");
////            readList.add(societyInfo[i]+" "+societyInfo[i+1]+" "+societyInfo[i+2]+" ");
////            i = i+2;
////        }
//        System.out.println(readList.size());
//        for(int i = 0; i < readList.size(); i++){
//            System.out.println(readList.get(i));
//        }
////        String memberInfo = " ";
////        String memb = readFromMember.readLine();
////        while (memberInfo !=  null) {
////            memberInfo = memberInfo + " " + memb;
////            memb = readFromMember.readLine();
////        }
////        String members[] = memberInfo.split(",");
////        for (int i = 0; i <= members.length; i++ ){
////            System.out.println(members[i]+" ");
////        }
//
////        for (int socIndex = 0; socIndex < socList.size(); socIndex++) {
////
////            ArrayList<Member> membList = socList.get(socIndex).memberList;
////            readFromSoc.append(socList.get(socIndex).socInfo());
////
////
////            for (int memberIndex = 0 ; memberIndex < membList.size() ; memberIndex++) {
//////                writeInSoc.append(membList.get(memberIndex).memberInfo());
////                readFromMember.append(membList.get(memberIndex).memberInfo());
////            }
////        }
//
////        writeInSoc.append(socList.toString());
//        readFromMember.close();
//        readFromSoc.close();
//    }
}