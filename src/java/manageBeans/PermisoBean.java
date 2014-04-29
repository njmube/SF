package manageBeans;

import helper.PermisoFH;
import entity.TbPermiso;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "permisoBean")
@ViewScoped 
public class PermisoBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbPermiso> listaPermiso;
    private TbPermiso newPermiso;
    private TbPermiso selectedPermiso;

    public PermisoBean() {
        updateList();
        this.newPermiso = new TbPermiso();
        this.selectedPermiso = new TbPermiso();
    }

    public void btnCreate() {
        PermisoFH helperP = new PermisoFH();
        String msg;
        if (helperP.create(this.newPermiso)) {
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
        PermisoFH helperP = new PermisoFH();
        String msg;
        if (helperP.update(this.selectedPermiso)) {
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
        PermisoFH helperP = new PermisoFH();
        TbPermiso permiso = helperP.search(this.selectedPermiso.getPerCod());
        if (helperP.delete(permiso)) {
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
    
    private void updateList() {
        PermisoFH helperP = new PermisoFH();
        this.listaPermiso = helperP.listAll();
    }
  
    // Get & Set
    public List<TbPermiso> getListaPermiso() {
        return listaPermiso;
    }

    public void setListaPermiso(List<TbPermiso> listaPermiso) {
        this.listaPermiso = listaPermiso;
    }

    public TbPermiso getNewPermiso() {
        return newPermiso;
    }

    public void setNewPermiso(TbPermiso newPermiso) {
        this.newPermiso = newPermiso;
    }

    public TbPermiso getSelectedPermiso() {
        return selectedPermiso;
    }

    public void setSelectedPermiso(TbPermiso selectedPermiso) {
        this.selectedPermiso = selectedPermiso;
    }

    
    
}