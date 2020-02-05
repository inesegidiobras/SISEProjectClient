package com.insure.client;

import com.insure.client.gen.ClaimDataStore;
import com.insure.client.gen.ClaimDataStoreService;

import javax.swing.*;
import javax.xml.ws.BindingProvider;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static void main(String args[]){
        ClaimDataStoreService db = new ClaimDataStoreService();

        ClaimDataStore DBStore = db.getClaimDataStorePort();

        ((BindingProvider) DBStore).getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
                "http://localhost:8090/claimservice");

        Scanner Scan = new Scanner(new BufferedInputStream(System.in));
        PrintStream pout = new PrintStream(new BufferedOutputStream(System.out), false);

        System.out.println("Welcome Officer. What is the next step?");
        System.out.println(
                "Create (C)laim | Create (D)ocument | (R)ead claim | (M)odify Eligibility | (U)pdate claim | (D)elete Claims");

        runDataStore();
    }

    public static void runDataStore(){
        String id_user= JOptionPane.showInputDialog("Insert your user Id:");
        int user=Integer.parseInt(id_user);
        while(true){
            try{
                String clientType=JOptionPane.showInputDialog("Insert the UserType:"+"\n"+
                        "[1] Officer" + "\n" +
                        "[2] Insured");
                if (clientType.equals("1")){
                    String officerMethod=JOptionPane.showInputDialog("Insert the operation:"+"\n"+
                        "[1] Create Claim" + "\n" +
                        "[2] Add Document"+"\n"+
                        "[3] Retrieve Documents"+"\n"+
                        "Exit");
                if (officerMethod.equals("Exit")){
                    break;
                }
                if (clientType.equals("1")){
                    String insuredMethod=JOptionPane.showInputDialog("Insert the operation:"+"\n"+
                            "[1] Create Claim" + "\n" +
                            "[2] Add Document"+"\n"+
                            "[3] Retrieve Documents"+"\n"+
                            "Exit");
                        if (insuredMethod.equals("Exit")){
                            break;


            }
        }

       /*while (Scan.hasNext()) {
            char l = Scan.next().charAt(0);
            if (l == 'C') {
                System.out.println("Enter id_user please and description");
                String description = Scan.next();
                int id_user = Scan.nextInt();
                Claim claim = ClaimDataStore.createClaim(id_user, description);
                System.out.println("Claim " + claim.getIdClaim() + " was created successfully!");
            } else if (l == 'D') {
                System.out.println("Enter id_claim and content");
                document=Claim.createDocument
                System.out.println("Document " + document.getIdClaim() + " was created successfully!");
            }
            } else if (l == 'M') {
                System.out.println("Enter id_user");
                int id_user = Scan.nextInt();
                int id_claim = 0;
                ClaimDataStore.changeEligibility(id_claim);
                boolean isEligible = ClaimDataStore.isEligible(id_claim);
                System.out.println("Claim eligibility:" + isEligible);
            }
        }
        pout.close();
        Scan.close();*/
    }
}