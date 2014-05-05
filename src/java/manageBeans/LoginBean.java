/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manageBeans;

import helper.UsuarioFH;
import helper.Util;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
 
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String password;
    private String message;
    private String uname;
    private boolean logueado;
 

    /**
     * Login 
     * @return login o home
     */
    public String loginProject()
    {
        UsuarioFH helperU = new UsuarioFH();  
        boolean result = helperU.existe(uname, password); 
        HttpSession session = Util.getSession();  
        if (result) {
            // get Http Session and store username                      
            session.setAttribute("username", uname);
            logueado = true;
            System.err.println("Se logueo correctamente");
            return "home";
        } else {
            
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                    "Login Invalido!",
                    "Intente de Nuevo!"));
            session.invalidate();
 
            // invalidate session, and redirect to other pages
            System.err.println("NO existe el usuario");
            logueado = false;
            return "login";
        }
    }
    
    /**
  * Logout
  * @return login
  */
    public String logout() 
    {
      logueado = false; 
      HttpSession session = Util.getSession();
      session.invalidate();
      return "login";
   }
    
    // get & set
    public String getPassword() 
    {
        return password;
    }

    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getMessage()
    {
        return message;
    }

    public void setMessage(String message) 
    {
        this.message = message;
    }

    public String getUname() 
    {
        return uname;
    }

    public void setUname(String uname) 
    {
        this.uname = uname;
    }

    public boolean isLogueado() 
    {
        return logueado;
    }

    public void setLogueado(boolean logueado) 
    {
        this.logueado = logueado;
    }
   
}
