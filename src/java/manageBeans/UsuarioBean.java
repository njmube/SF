package manageBeans;

import helper.UsuarioFH;
import entity.TbUsuario;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "usuarioBean")
@ViewScoped 
public class UsuarioBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbUsuario> listaUsuario;
    private TbUsuario newUsuario;
    private TbUsuario selectedUsuario;

    public UsuarioBean() {
        updateList();
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
    private void updateList() {
        UsuarioFH helperU = new UsuarioFH();
        this.listaUsuario = helperU.listAll();
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

    
    
}