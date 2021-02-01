package com.servlet;


import com.society.entity.Member;
import com.society.entity.Society;
import com.society.csv.api.CsvInterface;
import com.society.csv.service.MemberCsvService;
import com.society.csv.service.SocietyCsvService;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class MemberServlet extends HttpServlet {


    public String getJsonStringFromList(List<Member> list){

        String socListJSON = "[";
        int size = list.size();
        String comma = "";

        for (Member member : list) {
            String obj =comma+"{";
            obj = obj + "\"Name\":\""+member.getName()+"\"," +
                    "\"FlatNo\":"+member.getFlatNo()+"," +
                    "\"SocName\":\""+member.getSocName()+"\"";
            obj = obj + "}";

            if(comma.isEmpty()){
                comma = ",";
            }
            socListJSON = socListJSON + obj;
        }
        String finalJsonList = (socListJSON+"]");
        return finalJsonList;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) {
        PrintWriter writer = null;


        try {
            CsvInterface memberCsvService = new MemberCsvService(new ArrayList<Society>());
            List<Member> list = memberCsvService.readObjectsList();
//            logger.info("Inside Get() Method of MemberServlet ");


            resp.setContentType("text/json");
            writer = resp.getWriter();

            String url = req.getRequestURI();
            System.out.println(url);
            String[] urlParts = url.split("/");
            String socNameOfMember = urlParts[urlParts.length - 1];
            System.out.println("name after url split " + socNameOfMember);

            SocietyCsvService societyCsvService = new SocietyCsvService(new ArrayList<Society>());
            List<Society> societyList = societyCsvService.readObjectsList();
            List<Member> memberListOfSoc = null;
            for (Society society : societyList) { // traverse through societyList for matching name
                if (socNameOfMember.equals(society.getName().trim())) {
                    memberListOfSoc = society.getMemberList();
                    System.out.println("Memberlist " + society.getMemberList());
                }
            }

            System.out.println("memberList " + memberListOfSoc);
            String jsonListOfMembersOfSpecifiedSoc = getJsonStringFromList(memberListOfSoc);
            writer.println(jsonListOfMembersOfSpecifiedSoc);

//            System.out.println(list);
//            String finalJsonList = getJsonStringFromList(list);
//            writer.println(finalJsonList);

        }catch (Exception e){
            e.printStackTrace();
            resp.setStatus(500);
            writer.print("Failed to get society member details");
        }
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{


        CsvInterface memberCsvService = new MemberCsvService(new ArrayList<Society>());
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");

        String memberName = req.getParameter("Mname");
        String socName = req.getParameter("SocName");
        String flatNo = req.getParameter("FlatNo");
        int noOfFlats = Integer.valueOf(flatNo);

        Member member = new Member(noOfFlats,memberName,socName);
        try {
            memberCsvService.appendToFile(member);
            writer.println("Member successfully added");

        }catch (Exception e){
            throw new ServletException("Error in getting member values ");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
        MemberCsvService memberCsvService = new MemberCsvService(new ArrayList<Member>());
        PrintWriter writer = resp.getWriter();
//        logger.info("Inside doDelete() of Member ");

        String memberNameToBeDeleted = "";
        BufferedReader reader = req.getReader();
        String line;
        while ((line = reader.readLine()) != null) {
            memberNameToBeDeleted = memberNameToBeDeleted + line;
        }
        System.out.println(memberNameToBeDeleted);
        try {
            memberCsvService.deleteByName(memberNameToBeDeleted);
            writer.print("removed " + memberNameToBeDeleted);
        }catch (Exception e){
            throw new ServletException("Error in getting soc values ");
        }
    }
}