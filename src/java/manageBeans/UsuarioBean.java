package manageBeans;

import entity.TbRoles;
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
    private TbUsuario newUsuario;
    private TbUsuario selectedUsuario;
    private List<String> selectedRoles;  
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
    
    private void updateList() {
        UsuarioFH helperU = new UsuarioFH();
        this.listaUsuario = helperU.listAll();
    }

    private void updateRoles() {
        RolFH helperR = new RolFH();
        List<String> source = new ArrayList<String>();  
        List<String> target = new ArrayList<String>();  
        List<TbRoles> listaRoles = helperR.listAll();
        for(TbRoles p: listaRoles)
            source.add(p.getRolNombre());            
        this.roles = new DualListModel<String>(source, target);  
    }
    
    
    public void getRol(){
        Integer idUsuario = this.selectedUsuario.getUsCod();
        RolFH helperR = new RolFH();
        List<String> source = new ArrayList<String>();  
        List<String> target = new ArrayList<String>();  
        List<TbRoles> listaRoles = helperR.listAll();
        UsuarioFH helperU = new UsuarioFH();
        List<TbRoles> listaActual = helperU.getRoles(idUsuario);
        for(TbRoles p: listaRoles)
            source.add(p.getRolNombre()); 
        for(TbRoles p: listaActual)
            target.add(p.getRolNombre());
        for(String r: source){
            for(String p: target){
                if(r.equals(p))
                    source.remove(r);
            }
        }
        this.roles = new DualListModel<String>(source, target);  
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

    public List<String> getSelectedRoles() {
        return selectedRoles;
    }

    public void setSelectedRoles(List<String> selectedRoles) {
        this.selectedRoles = selectedRoles;
    }

    public DualListModel<String> getRoles() {
        return roles;
    }

    public void setRoles(DualListModel<String> roles) {
        this.roles = roles;
    }

}