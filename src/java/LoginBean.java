/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
/**
 *
 * @author SMI188
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable
{
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LoginBean() {
    }
    
       // Method to validate credentials
    public String login() {
        if ("admin".equals(username) && "password".equals(password)) {
            // Redirect to home page or dashboard
            return "home"; // Navigation case defined in faces-config.xml
        } else {
            // Invalid credentials, show error message
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid credentials", "Invalid username or password"));
            return ""; // Navigation case defined in faces-config.xml
        }
    }
//    public String logout()
//    {
//        return "index.xhtml";
//    }
}
