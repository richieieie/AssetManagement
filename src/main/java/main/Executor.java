package main;

import data.*;
import utils.Inputter;
import utils.Visual;

import java.util.Objects;

public class Executor {
    public static void registerAccount(EmployeeList employeeList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        if (Objects.isNull(employeeList.register())) {
            return;
        }

        System.out.println("Registered");
    }

    public static Employee logUserIn(EmployeeList employeeList, Employee e) {
        if (!Objects.isNull(e)) {
            System.out.println("You have to log out!!!");
            return e;
        }

        e = employeeList.login();

        if (!Objects.isNull(e))
            System.out.println("Logged in");
        return e;
    }

    public static Employee logUserOut(Employee e) {
        if (Objects.isNull(e)) {
            System.out.println("You didn't log in!!!");
        } else {
            System.out.println("Logged out");
        }
        return null;
    }

    public static void createAsset(AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Continue creating"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (!assetList.createNew())
                    System.out.println("Failed to create new asset, please try " +
                            "again!!!");
                else
                    System.out.println("A new asset is added");

            }

        } while (subChoice == 1);
    }

    public static void cancelRequest(RequestList requestList, Employee e) {
        if (!Employee.checkStatus(e))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Continue cancelling"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (!requestList.cancelOne(e))
                    System.out.println("You don't have any borrow requests now or wrong ID!!!");
                else
                    System.out.println("Request is cancelled");
            }

        } while (subChoice == 1);
    }

    public static void returnBorrowRequest(AssetList assetList, BorrowList borrowList,
                                           RequestList requestList,
                                           Employee e) {
        if (!Employee.checkStatus(e))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Continue returning"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (!requestList.returnOne(e, assetList, borrowList))
                    System.out.println("You don't have any borrow requests now or wrong ID!!!");
                else
                    System.out.println("Asset is returned");
            }

        } while (subChoice == 1);
    }

    public static void showAllBorrowReqs(BorrowList borrowList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        borrowList.showAll();
    }

    public static void editAsset(AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Continue updating"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (assetList.updateAsset())
                    System.out.println("The asset is updated!!!");
            }

        } while (subChoice == 1);
    }

    public static void findAssetsByName(AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e)) {
            return;
        }

        assetList.searchAssetsByName(Inputter.getStringWithCap("Name: "));
    }

    public static void sendANewRequest(AssetList assetList, RequestList requestList, Employee e) {
        if (!Employee.checkStatus(e))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Send request"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (!requestList.sendNew(e, assetList))
                    System.out.println("Asset is not found or failed to send a " +
                            "new request. Please try again!!!");
                else
                    System.out.println("A new request is sent");
            }

        } while (subChoice == 1);
    }

    public static void approveRequest(RequestList requestList, BorrowList borrowList,
                                      AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        int subChoice;
        do {
            Visual.printMenu(new String[]{"Continue approving"});
            subChoice = Inputter.getInt("Your choice: ");

            if (subChoice == 1) {
                if (requestList.approveOne(assetList, borrowList))
                    System.out.println("The request is approved");
            }

        } while (subChoice == 1);
    }
}
