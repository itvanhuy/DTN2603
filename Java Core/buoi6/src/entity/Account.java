package entity;

import java.sql.Date;

public class Account {
    private int accountId;
    private String email;
    private String username;
    private String fullname;
    private int departmentId;
    private int positionId;
    private Date createDate;

    public Account() {
    }

    public Account(int accountId, String email, String username, String fullname,
                   int departmentId, int positionId, Date createDate) {
        this.accountId = accountId;
        this.email = email;
        this.username = username;
        this.fullname = fullname;
        this.departmentId = departmentId;
        this.positionId = positionId;
        this.createDate = createDate;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    @Override
    public String toString() {
        return String.format("| %-4d | %-25s | %-20s | %-25s | %-8d | %-8d | %-12s |",
                accountId, email, username, fullname, departmentId, positionId, createDate);
    }
}