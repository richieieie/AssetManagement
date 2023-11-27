package data;

import utils.Inputter;
import utils.Visual;

import java.io.UnsupportedEncodingException;
import java.time.LocalDate;
import java.util.Objects;

import static utils.Inputter.decode;
import static utils.Inputter.getString;

public class EmployeeList extends ObjectList<Employee> {
    public EmployeeList(String path) {
        super(path);
    }

    @Override
    public Employee searchById(String id) {
        for (Employee e : this) {
            if (e.getId().equals(id))
                return e;
        }

        return null;
    }

    public Employee login() {
        // Get id from user
        String id;
        do {
            id = Inputter.getString("ID (E000000): ", "[Ee]\\d{6}", "Please enter with " +
                    "(E000000) " + "format").toUpperCase();
            if (objectNotFound(id))
                System.out.println("ID " + id + " doesn't existed");
        } while (objectNotFound(id));

        // Get password from user
        String password = Inputter.getString("Password: ");
        Employee employee = this.searchById(id);

        // Check if the employee existed or not?
        if (Objects.isNull(employee))
            return null;

        // Try to compare encoded password with raw password
        try {
            if (!decode(employee.getPassword()).equals(password)) {
                System.out.println("Password is not the same");
                return null;
            }

            return employee;
        } catch (UnsupportedEncodingException e) {
            System.out.println("Can't login");
            return null;
        }
    }

    public Employee register() {
        Employee newEmployee;

        // Get account's id from user and check if it existed or not
        String id;
        do {
            id = Inputter.getString("ID (E000000): ", "[Ee]\\d{6}", "Please enter with " +
                    "(E000000) " + "format").toUpperCase();
            if (!objectNotFound(id))
                System.out.println("ID " + id + " existed");
        } while (!objectNotFound(id));

        // Get account's info from user
        String name = Inputter.getStringWithCap("Name: ");
        String role = Inputter.getString("Role (EM | MA): ", "(?i)(em|ma)",
                "Please enter EM or MA").toUpperCase();
        String sex = Inputter.capitalizeWords(getString("Sex (Male | Female): ", "(?i)(male|female)", "Please enter " +
                "Male or Female"));

        // Get date from and format it before store
        LocalDate formattedDate = Inputter.getLocalDate("Birthdate (DD/MM/YYYY): ");

        // Get raw password from user and encode it
        String password;
        try {
            password = Inputter.encode(getString("Password: "));
        } catch (UnsupportedEncodingException e) {
            System.out.println("Can't create the employee");
            return null;
        }

        newEmployee = new Employee(id, name, formattedDate, role, sex, password);

        // Try to store this new account
        if (!this.addNew(newEmployee))
            return null;

        return newEmployee;
    }

    @Override
    public void showAll() {
        Visual.printDataList(this, new String[]{"ID", "Name", "Birthdate", "Role", "Sex",
                "Password"}, "EMPLOYEES");
    }
}
