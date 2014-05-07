package manageBeans;

import entity.TbRoles;
import entity.TbRolesXUsuario;
import helper.UsuarioFH;
import entity.TbUsuario;
import helper.RolFH;
import helper.RolesXUsuarioFH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;
 
@ManagedBean(name = "usuarioBean")
@ViewScoped 
public class UsuarioBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbUsuario> listaUsuario;
    private List<TbRoles> listaRol;
    private TbUsuario newUsuario;
    private TbUsuario selectedUsuario; 
    private DualListModel<String> roles; 


    public UsuarioBean() {
        updateList();
        updateRoles();
        this.newUsuario = new TbUsuario();
        this.selectedUsuario = new TbUsuario();
    }
    
    public void btnCreate() {
        UsuarioFH helperU = new UsuarioFH();
        String msg;
        if (helperU.create(this.newUsuario)) {
            msg = "se creo";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
            
        } else {
            msg = "error al crear";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnUpdate() {  
        UsuarioFH helperU = new UsuarioFH();
        String msg;
        if (helperU.update(this.selectedUsuario)) {
            msg = "se modificó";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
        } else {
            msg = "error al modificar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnDelete() {
        String msg;
        UsuarioFH helperU = new UsuarioFH();
        TbUsuario usuario = helperU.search(this.selectedUsuario.getUsCod());
        if (helperU.delete(usuario)) {
            msg = "se eliminó";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
        } else {
            msg = "error al eliminar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnPass() {  
        UsuarioFH helperU = new UsuarioFH();
        String msg;
        if (helperU.pass(this.selectedUsuario)) {
            msg = "se modificó";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
        } else {
            msg = "error al modificar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    public void btnConfig() {  
        RolesXUsuarioFH helperRXU = new RolesXUsuarioFH();
        String msg;
        if (helperRXU.config(this.selectedUsuario, this.roles.getTarget())) {
            msg = "se configuro su rol";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
        } else {
            msg = "error al configurar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    private void updateRoles() {
        RolFH helperR = new RolFH();
        List<String> source = new ArrayList<String>();  
        List<String> target = new ArrayList<String>();        
        this.roles = new DualListModel<String>(source, target);  
    }
    private void updateList() {
        UsuarioFH helperU = new UsuarioFH();
        this.listaUsuario = helperU.listAll();
    }

    public DualListModel<String> getRoles(TbUsuario usuario){
        List<String> source = rolesSource(usuario.getUsCod()); // permisos que no son del rol
        List<String> target = rolesTarget(usuario.getUsCod()); // permisos que son del rol
        DualListModel<String> per = new DualListModel<String>(source, target);
        return per;
    } 
    
    private List<String> rolesTarget(Integer idUsuario){
        List<String> listaRol = new ArrayList<String>(); 
        RolesXUsuarioFH helperRXU = new RolesXUsuarioFH();        
        List<TbRolesXUsuario> lista = helperRXU.listRXU(idUsuario);
        RolFH helperR = new RolFH();
        for(TbRolesXUsuario rxp: lista){
            TbRoles rol = helperR.search(rxp.getTbRoles().getRolCod());
            listaRol.add(rol.getRolNombre());
        }
        return listaRol;
    }

    private List<String> rolesSource(Integer idUsuario){
        RolFH helperR = new RolFH();
        List<TbRoles> lista = helperR.listAll();
        List<String> listaRol = new ArrayList<String>(); 
        List<String> listaRol1 = rolesTarget(idUsuario);
        boolean band; 
        for(TbRoles p: lista){
           band = true; 
           for(String r: listaRol1){
               if (p.getRolNombre().equals(r))
                   band = false;
           }
           if (band)
               listaRol.add(p.getRolNombre()); 
        }  
              
        return listaRol;
    }
    
    public List<TbRoles> rolesDeUsuario(TbUsuario usuario){
        List<TbRoles> listaR = new ArrayList<TbRoles>(); 
        RolesXUsuarioFH helperRXP = new RolesXUsuarioFH();        
        List<TbRolesXUsuario> lista = helperRXP.listRXU(usuario.getUsCod());
        RolFH helperR = new RolFH();
        for(TbRolesXUsuario rxu: lista){
            TbRoles p = helperR.search(rxu.getTbRoles().getRolCod());
            listaR.add(p);
        }
        return listaR;
    }
    
    // Get & Set
    public List<TbUsuario> getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(List<TbUsuario> listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public TbUsuario getNewUsuario() {
        return newUsuario;
    }

    public void setNewUsuario(TbUsuario newUsuario) {
        this.newUsuario = newUsuario;
    }

    public TbUsuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(TbUsuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public DualListModel<String> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<String> roles) {
        this.roles = roles;
    }

    public List<TbRoles> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<TbRoles> listaRol) {
        this.listaRol = listaRol;
    }
    

}