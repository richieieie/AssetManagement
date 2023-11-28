package utils;

import data.*;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataInitiator {
    public static AssetList assets = new AssetList("src/main/resources/asset.dat");
    public static BorrowList borrows = new BorrowList("src/main/resources/borrow.dat");
    public static RequestList requests = new RequestList("src/main/resources/request.dat");
    public static EmployeeList employees = new EmployeeList("src/main/resources/employee.dat");
    public static String[] menuOptions = {"Login", "Register an account (Only MA)", "Create " +
            "new asset (Only MA)", "Updating " +
            "asset's information (Only MA)", "Approve the request of employee (Only MA)", "Show list of " +
            "borrow asset (Only MA)", "Search asset " +
            "by " +
            "name " +
            "(DESCENDING)", "Borrow the assets", "Cancel request", "Return asset", "Log out"};

    public static void addAll() throws UnsupportedEncodingException {
        addRawAssets(assets);
        addRawBorrows(borrows);
        addRawRequests(requests);
        addRawEmployees(employees);
    }

    public static void addRawAssets(AssetList assets) {
        assets.addNew(new Asset("A001", "Samsung Projector", "White", 500, 3.2, 10));
        assets.addNew(new Asset("A002", "Macbook Pro 2016", "Silver", 1000, 2.2, 5));
    }

    public static void addRawBorrows(BorrowList borrows) {
        borrows.addNew(new Borrow("B001", "A001", "E160001", 1, LocalDateTime.parse("23-12-2021 " +
                "15:13:46", Inputter.formatter)));
        borrows.addNew(new Borrow("B002", "A001", "E160001", 1, LocalDateTime.parse("25-12-2021 " +
                "16:14:56", Inputter.formatter)));
        borrows.addNew(new Borrow("B003", "A002", "E160798", 1, LocalDateTime.parse("15-12-2021 " +
                "17:15:52", Inputter.formatter)));
        borrows.addNew(new Borrow("B007", "A001", "E160240", 1, LocalDateTime.parse("26-12-2021 " +
                "12:16:53", Inputter.formatter)));
    }

    public static void addRawRequests(RequestList requests) {
        requests.addNew(new Request("R001", "A001", "E140499", 1,
                LocalDateTime.parse("23-12-2021 13:17:56", Inputter.formatter)));
        requests.addNew(new Request("R002", "A002", "E140499", 1,
                LocalDateTime.parse("24-12-2021 12:18:56", Inputter.formatter)));
        requests.addNew(new Request("R003", "A001", "E140499", 1,
                LocalDateTime.parse("23-12-2021 11:19:56", Inputter.formatter)));
        requests.addNew(new Request("R007", "A002", "E140499", 1,
                LocalDateTime.parse("24-12-2021 10:10:56", Inputter.formatter)));
    }

    public static void addRawEmployees(EmployeeList employees) throws UnsupportedEncodingException {
        employees.addNew(new Employee("E160001", "Nguyen Hong Hiep", LocalDate.parse("12-06" +
                        "-2000 00:00:00"
                , Inputter.formatter),
                "EM", "Male"
                , Inputter.encode("123456")));
        employees.addNew(new Employee("E160240", "Tran Dinh Khanh", LocalDate.parse("15-07" +
                        "-2002 00:00:00"
                , Inputter.formatter),
                "EM", "Male"
                , Inputter.encode("123456")));
        employees.addNew(new Employee("E140449", "Le Buu Nhan", LocalDate.parse("10-07" +
                        "-2002 00:00:00"
                , Inputter.formatter),
                "EM", "Male"
                , Inputter.encode("123456")));
        employees.addNew(new Employee("E160798", "Truong Le Minh", LocalDate.parse("03-12" +
                        "-2002 00:00:00"
                , Inputter.formatter),
                "EM", "Male"
                , Inputter.encode("123456")));
        employees.addNew(new Employee("E160052", "Hoa Doan", LocalDate.parse("05-06" +
                        "-1990 00:00:00"
                , Inputter.formatter),
                "MA", "Male"
                , Inputter.encode("123456")));
    }
}
