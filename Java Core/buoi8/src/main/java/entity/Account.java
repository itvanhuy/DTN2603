package entity;

import java.time.LocalDate;

public class Account {
    private int id;
    private String username;
    private String fullName;
    private String email;
    private Department department;
    private Position position;
    private LocalDate createDate;

    public Account() {}

    public Account(int id, String username, String fullName, String email, Department department, Position position, LocalDate createDate) {
        this.id = id;
        this.username = username;
        this.fullName = fullName;
        this.email = email;
        this.department = department;
        this.position = position;
        this.createDate = createDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        String departmentName = (department != null && department.getName() != null) ? department.getName() : "";
        String positionName = (position != null && position.getName() != null) ? position.getName().name() : "";
        return String.format("| %-4d | %-20s | %-20s | %-25s | %-15s | %-15s | %-12s |",
                id, email, username, fullName, departmentName, positionName,
                createDate == null ? "" : createDate.toString());
    }
}