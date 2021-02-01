package com.society;

import com.society.csv.api.CsvInterface;
import com.society.csv.service.MemberMaintenanceCsvService;
import com.society.csv.service.SocietyCsvService;
import com.society.entity.MemberMaintenance;
import com.society.entity.Society;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) throws Exception {
        CsvInterface societyCsvService = new SocietyCsvService(new ArrayList<Society>());
        MemberMaintenance memberMaintenance = new MemberMaintenance();
        MemberMaintenanceCsvService memberMaintenanceCsvService = new MemberMaintenanceCsvService();

    }
}







