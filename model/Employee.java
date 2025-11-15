package model;

public class Employee {
    
    private int employeeId;
    private String full_name;
    private String password;
    private String role;
    private String contact_info;

    public Employee() {}

    public Employee(int employeeId, String full_name, String password, String role, String contact_info) {
        this.employeeId = employeeId;
        this.full_name = full_name;
        this.password = password;
        this.role = role;
        this.contact_info = contact_info;
    }

    public int getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getFull_name() {
        return full_name;
    }
    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    public String getContact_info() {
        return contact_info;
    }
    public void setContact_info(String contact_info) {
        this.contact_info = contact_info;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", full_name=" + full_name + ", role=" + role + ", contact_info=" + contact_info + "]";
    }
}
