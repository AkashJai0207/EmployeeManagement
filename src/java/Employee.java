
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SMI188
 */
public class Employee 
{
    private String empid;
    private String name;
    private String position;
    private String email;
    private long phno;
    private String doj;
    private String dob;
    private String addr;
    private String maritialstatus;

    public Employee() {
    }

    public Employee(String empid, String name, String position, String email, long phno, String doj, String dob, String addr, String maritialstatus) {
        this.empid = empid;
        this.name = name;
        this.position = position;
        this.email = email;
        this.phno = phno;
        this.doj = doj;
        this.dob = dob;
        this.addr = addr;
        this.maritialstatus = maritialstatus;
    }
    public String getEmpid() {
        return empid;
    }

    public void setEmpid(String empid) {
        this.empid = empid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getPhno() {
        return phno;
    }

    public void setPhno(long phno) {
        this.phno = phno;
    }

    public String getDoj() {
        return doj;
    }

    public void setDoj(String doj) {
        this.doj = doj;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddr() {
        return addr;
    }

    public void setAddr(String addr) {
        this.addr = addr;
    }

    public String getMaritialstatus() {
        return maritialstatus;
    }

    public void setMaritialstatus(String maritialstatus) {
        this.maritialstatus = maritialstatus;
    }

//    @Override
//    public String toString() {
//        return "Employee{" + "empid=" + empid + ", name=" + name + ", position=" + position + ", email=" + email + ", phno=" + phno + ", doj=" + doj + ", dob=" + dob + ", addr=" + addr + ", maritialstatus=" + maritialstatus + '}';
//    }
}
