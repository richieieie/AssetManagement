package main;

import data.*;
import utils.Inputter;
import utils.Visual;

import java.util.Objects;

public class Executor {
    public static void registerAccount(EmployeeList employeeList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        if(Objects.isNull(employeeList.register())) {
            System.out.println("Failed to register");
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
                if (!assetList.createNewAsset()) {
                    System.out.println("Failed to create new asset, please try " +
                            "again!!!");
                } else {
                    System.out.println("A new asset is added");
                }
            }

        } while (subChoice == 1);
    }

    public static void findBorrows(BorrowList borrowList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        borrowList.showAll();
    }

    public static void editAsset(AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        if (!assetList.updateAsset())
            System.out.println("Failed to update the asset!!!");
        else
            System.out.println("The asset is updated");
    }

    public static void findAssets(AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        assetList.searchAssetsByName(Inputter.getStringWithCap("Name: "));
    }

    public static void approveRequest(RequestList requestList, BorrowList borrowList,
                                      AssetList assetList, Employee e) {
        if (!Employee.checkStatus(e, "MA"))
            return;

        requestList.showAll();

        if (!requestList.approveOne(assetList, borrowList))
            System.out.println("Failed to approve this request!!!");
        else
            System.out.println("The request is approved");
    }
}
