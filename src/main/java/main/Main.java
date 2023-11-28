package main;

import data.*;
import utils.DataInitiator;
import utils.Inputter;
import utils.Visual;

import java.io.UnsupportedEncodingException;

public class Main {
    public static void main(String[] args) {
        try {
            // You can delete this method and all dat files if you don't want to initialize raw
            // data. You can change the path name to store dat files in DataInitiator.java
            DataInitiator.addAll();

            AssetList assets = DataInitiator.assets;
            RequestList requests = DataInitiator.requests;
            BorrowList borrows = DataInitiator.borrows;
            EmployeeList employees = DataInitiator.employees;
            Employee em = null;

            // Prompt user to choose option
            int choice;
            do {
                Visual.printMenu(DataInitiator.menuOptions);
                choice = Inputter.getInt("Your choice: ");

                switch (choice) {
                    case 1 -> em = Executor.logUserIn(employees, em);
                    case 2 -> Executor.registerAccount(employees, em);
                    case 3 -> Executor.createAsset(assets, em);
                    case 4 -> Executor.editAsset(assets, em);
                    case 5 -> Executor.approveRequest(requests, borrows, assets, em);
                    case 6 -> Executor.showAllBorrowReqs(borrows, em);
                    case 7 -> Executor.findAssetsByName(assets, em);
                    case 8 -> em = Executor.logUserOut(em);
                    default -> System.out.println("Exiting...");
                }
            } while (choice >= 1 && choice <= 8);

            Inputter.scanner.close();
        } catch (UnsupportedEncodingException e) {
            System.out.println("Encoding or decoding errors");
        }
    }
}
