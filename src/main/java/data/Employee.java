package data;

import utils.Inputter;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@SuppressWarnings("unused")
public class Employee implements Serializable, DataRow {
    private String id;
    private String name;
    private LocalDate birthdate;
    private String role;
    private String sex;
    private String password;
    public Employee() {

    }
    public Employee(String id, String name, LocalDate birthdate, String role, String sex,
                    String password) {
        this.id = id;
        this.name = name;
        this.birthdate = birthdate;
        this.role = role;
        this.sex = sex;
        this.password = password;
    }

    @SuppressWarnings("BooleanMethodIsAlwaysInverted")
    public static boolean checkStatus(Employee em, String role) {
        if (Objects.isNull(em)) {
            System.out.println("You must log in before using the program!!!");
            return false;
        } else if (!em.getRole().equals(role)) {
            System.out.println("You don't have permission to do this action!!!");
            return false;
        }

        return true;
    }

    public static boolean checkStatus(Employee em) {
        if (Objects.isNull(em)) {
            System.out.println("You must log in before using the program!!!");
            return false;
        }

        return true;
    }

    public String getId() {
        return id;
    }

    private void setEmployeeId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Employee{" + "id='" + id + '\'' + ", name='" + name + '\'' + ", birthdate" +
                "=" + birthdate.format(Inputter.formatterHalf) + ", role='" + role + '\'' + ", " +
                "sex='" + sex + '\'' +
                ", " +
                "password='" + password + '\'' + '}';
    }

    @Override
    public String toStringRow(int[] lens) {
        return String.format("|" + "%1$" + lens[0] + "s|" + "%2$" + lens[1] + "s|" +
                        "%3$" + lens[2] + "s|%4$" + lens[3] + "s|%5$" + lens[4] + "s|" + "%6$" + lens[5] + "s|", id,
                name, birthdate.format(Inputter.formatterHalf), role,
                sex, password);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(getId(), employee.getId());
    }

}
