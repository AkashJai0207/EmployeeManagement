
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author SMI188
 */
@Named(value = "employeeBean")
@SessionScoped
public class EmployeeBean implements Serializable {

    private Employee selemployee;
    private List<Employee> emp = new ArrayList<>();
    private String empid;
    private String name;
    private String position;
    private String email;
    private long phno;
    private Date doj;
    private Date dob;
    private String addr;
    private String maritialstatus;
    private static final String NAME_PATTERN = "^[a-zA-Z ]+$";

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

    public Date getDoj() {
        return doj;
    }

    public void setDoj(Date doj) {
        this.doj = doj;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
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

    //---------------------------------------------------------------
    public Employee getSelemployee() {
        return selemployee;
    }

    public void setSelemployee(Employee selemployee) {
        this.selemployee = selemployee;
    }

    public List<Employee> getEmp() {
        return emp;
    }

    public void setEmp(List<Employee> emp) {
        this.emp = emp;
    }

    @PostConstruct
    public void init() {
        emp.add(new Employee("SMI188", "Akash", "Java Developer", "jaiakazh@gmail.com", 7603845472l, "2024-01-17", "2003-07-02", "Chennai", "Single"));
        emp.add(new Employee("SMI192", "Pravin V", "Java Developer", "pravin@gmail.com", 6381039829l, "2024-02-08", "2002-06-18", "Kumbakonam", "Maried"));
        emp.add(new Employee("SMI191", "Veera Ragavan", "Java Developer", "ragavan@gmail.com", 9344583073l, "2024-02-08", "2002-02-28", "Trichy", "Single"));
        emp.add(new Employee("SMI190", "Veeraiyan", "Java Developer", "veeraiyan@gmail.com", 6379068281l, "2024-02-08", "2000-10-04", "Thanjavur", "Maried")); 
    }

    public void add() {
        try {
            int crdate = getYearFromDate(this.getDoj());
            int dofb = getYearFromDate(this.getDob());
            System.out.println(crdate + " " + dofb);
            if ((crdate - dofb) >= 20) {
                Employee em = new Employee();
                em.setEmpid(this.getEmpid());
                em.setName(this.getName());
                em.setAddr(this.getAddr());
                em.setDob(convertToString(this.getDob()));
                em.setDoj(convertToString(this.getDoj()));
                em.setEmail(this.getEmail());
                em.setMaritialstatus(this.getMaritialstatus());
                em.setPhno(this.getPhno());
                em.setPosition(this.getPosition());
                this.getEmp().add(em);
                System.out.println("/////////////////////////////////////////////->"+emp);

                empid = "";
                name = "";
                position = "";
                email = "";
                phno = 0;
                doj = null;
                dob = null;
                addr = "";
                maritialstatus = "";

                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Data saved successfully!", "Data saved successfully!"));
            } else {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Invalid DOB", "Age must be greater than 20"));
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error occurred: " + e.getMessage(), null));
        }
    }

    private int getYearFromDate(Date date) {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
        return Integer.parseInt(yearFormat.format(date));
    }

    public String edit(String idToEdit) {
        for (Employee emp : emp) {
            if (emp.getEmpid().equals(idToEdit)) {
                this.empid = emp.getEmpid();
                this.name = emp.getName();
                this.position = emp.getPosition();
                this.email = emp.getEmail();
                this.phno = emp.getPhno();
                this.doj = convertToDate(emp.getDoj());
                this.dob = convertToDate(emp.getDob());
                this.addr = emp.getAddr();
                this.maritialstatus = emp.getMaritialstatus();
            }
        }
        return "updateEmp";
    }

    public String update(String idToUpdate) {
        for (Employee emp : emp) {
            if (emp.getEmpid().equals(idToUpdate)) {
                emp.setName(name);
                emp.setAddr(addr);
                emp.setDob(convertToString(this.getDob()));
                emp.setDoj(convertToString(this.getDoj()));
                emp.setEmail(email);
                emp.setMaritialstatus(maritialstatus);
                emp.setPhno(phno);
                emp.setPosition(position);

                empid = "";
                name = "";
                position = "";
                email = "";
                phno = 0;
                doj = null;
                dob = null;
                addr = "";
                maritialstatus = "";
            }
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee updated successfully!", "Employee with ID " + idToUpdate + " has been updated."));

        return "employeeTable";
    }

    public static String convertToString(Date date) {
        if (date == null) {
            return null; // Handle null date gracefully
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(date);
    }

    public static Date convertToDate(String dateString) {
        Date date = null;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormat.parse(dateString);
            
        } catch (ParseException e) {
            e.printStackTrace(); // Handle parsing exception as needed
        }
        return date;
    }

    public void validatePhNo(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String str = value.toString();
        if (str.length() != 10) {
            FacesMessage message = new FacesMessage("Enter valid PhoneNo");
            throw new ValidatorException(message);
        }
    }

    public void validateID(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String id = value.toString();
        for (Employee emp : emp) {
            if (emp.getEmpid().equals(id)) {
                FacesMessage message = new FacesMessage("Employee ID already Exist");
                throw new ValidatorException(message);
            }
        }
    }

    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        String mailid = value.toString();
        if (!mailid.matches(".*@gmail.com")) {
            FacesMessage message = new FacesMessage("Invalid Mail ID");
            throw new ValidatorException(message);
        }
        for (Employee emp : emp) {
            if (emp.getEmail().equals(mailid)) {
                FacesMessage message = new FacesMessage("Mail ID already Exist");
                throw new ValidatorException(message);
            }
        }
    }

//    public void validateEmail(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        String mailId = value.toString().trim();
//
//        for (Employee emp : emp) {
//            if (emp.getEmail().equalsIgnoreCase(mailId)) {
//                FacesMessage message = new FacesMessage("Email address already exists");
//                throw new ValidatorException(message);
//            }
//        }
//
//        if (!isValidEmail(mailId)) {
//            FacesMessage message = new FacesMessage("Invalid email address format");
//            throw new ValidatorException(message);
//        }
//    }
//
//    private boolean isValidEmail(String email) {
//
//        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
//        return email.matches(emailRegex);
//    }
    public void isValidName(FacesContext context, UIComponent component, Object value) throws ValidatorException {
        Pattern pattern = Pattern.compile(NAME_PATTERN);
        Matcher matcher = pattern.matcher((String) value);

        if (!matcher.matches()) {
            FacesMessage message = new FacesMessage("Enter valid Name");
            throw new ValidatorException(message);
        }
    }

    public void del(String empIdToDelete) {
        Employee employeeToRemove = null;
        for (Employee emp : emp) {
            if (emp.getEmpid().equals(empIdToDelete)) {
                employeeToRemove = emp;
                break;
            }
        }
        if (employeeToRemove != null) {
            this.emp.remove(employeeToRemove);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Employee deleted successfully!", "Employee with ID " + empIdToDelete + " has been deleted."));
        }
    }
    public List<String> autocom(String prestr)
    {
        List<String> matches = new ArrayList<>();
        if(this.empid.toUpperCase().startsWith(prestr.toUpperCase()))
        {
           matches.add(this.empid);
        }
        return matches;
    }
}
