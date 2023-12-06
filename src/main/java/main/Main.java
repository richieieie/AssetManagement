package main;

import data.*;
import utils.DataInitiator;
import utils.Inputter;
import utils.Visual;

public class Main {
    public static void main(String[] args) {
        System.out.println(System.getProperty("java.version"));
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
                case 8 -> Executor.sendANewRequest(assets, requests, em);
                case 9 -> Executor.cancelRequest(requests, em);
                case 10 -> Executor.returnBorrowRequest(assets, borrows, requests, em);
                case 11 -> em = Executor.logUserOut(em);
                default -> System.out.println("Exiting...");
            }
        } while (choice >= 1 && choice <= 11);

        Inputter.scanner.close();
    }
}
