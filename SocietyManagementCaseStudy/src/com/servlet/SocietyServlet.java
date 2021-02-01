package com.servlet;

import com.society.entity.Society;
import com.society.csv.api.CsvInterface;
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

public class SocietyServlet extends HttpServlet {


    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {

        CsvInterface societyCsvService = new SocietyCsvService(new ArrayList<Society>());
        List<Society> list = null;
        try {
            list = societyCsvService.readObjectsList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error in reading socList ");
        }

//       "Inside doGet() of SocietyServlet "
        resp.setContentType("text/json");
        PrintWriter writer = resp.getWriter();

        String socListJSON = "[";
        int size = list.size();
        String comma = "";

        for (Society society : list) {
            String obj =comma+"{";
            obj = obj + "\"Name\":\""+society.getName()+"\"," +
                        "\"Flats\":"+society.getNoOfFlats();
            obj = obj + "}";

            if(comma.isEmpty()){
                comma = ",";
            }
            socListJSON = socListJSON + obj;
        }
        String finalJsonList = (socListJSON+"]");
        writer.println(finalJsonList);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{

        CsvInterface societyCsvService = new SocietyCsvService(new ArrayList<Society>());
        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/json");
//        "Inside doPost of Society Servlet");
        String socName = req.getParameter("Sname");
        String address = req.getParameter("Address");
        String flats = req.getParameter("nof");
        int noOfFlats = Integer.valueOf(flats);

        Society society = new Society(address,socName,noOfFlats);
        try {
            societyCsvService.appendToFile(society);
            writer.print("Added " + socName + " Society.");
        }catch (Exception e){
            throw new ServletException("Error in getting soc values ");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        SocietyCsvService societyCsvService = new SocietyCsvService(new ArrayList<Society>());
        PrintWriter writer = resp.getWriter();
//        ("Inside doDelete() of Society");

        String socNameToBeDeleted = "";
        BufferedReader reader = req.getReader();// here society is loaded
        String line;
        while ((line = reader.readLine()) != null) {
            socNameToBeDeleted = socNameToBeDeleted + line;
        }
        System.out.println(socNameToBeDeleted);
        try {
            societyCsvService.deleteByName(socNameToBeDeleted);
            writer.print("removed " + socNameToBeDeleted);
        }catch (Exception e){
//            "Error in deleting Society ",e);
            throw new ServletException("Error in deleting soc ");

        }
    }
}