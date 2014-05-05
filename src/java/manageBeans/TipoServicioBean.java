package manageBeans;

import helper.TipoServicioFH;
import entity.TbTipoServicio;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "tipoServicioBean")
@ViewScoped 
public class TipoServicioBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbTipoServicio> listaTipoServicio;
    private TbTipoServicio newTipoServicio;
    private TbTipoServicio selectedTipoServicio;
    private String selectedServicio;


    public TipoServicioBean() {
        updateList();
        this.newTipoServicio = new TbTipoServicio();
        this.selectedTipoServicio = new TbTipoServicio();
    }

    public void btnTipoServicioCreate() {
        TipoServicioFH helperU = new TipoServicioFH();
        String msg;
        if (helperU.create(this.newTipoServicio)) {
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
    public void btnTipoServicioUpdate() {  
        TipoServicioFH helperU = new TipoServicioFH();
        String msg;
        if (helperU.update(this.selectedTipoServicio)) {
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
    public void btnTipoServicioDelete() {
        String msg;
        TipoServicioFH helperU = new TipoServicioFH();
        TbTipoServicio tipoServicio = helperU.search(this.selectedTipoServicio.getTsCod());
        if (helperU.delete(tipoServicio)) {
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
        TipoServicioFH helperU = new TipoServicioFH();
        this.listaTipoServicio = helperU.listAll();
    } 
  
    
    // Get & Set
    public List<TbTipoServicio> getListaTipoServicio() {
        return listaTipoServicio;
    }

    public void setListaServicio(List<TbTipoServicio> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }

    public TbTipoServicio getNewTipoServicio() {
        return newTipoServicio;
    }

    public void setNewTipoServicio(TbTipoServicio newTipoServicio) {
        this.newTipoServicio = newTipoServicio;
    }

    public TbTipoServicio getSelectedTipoServicio() {
        return selectedTipoServicio;
    }

    public void setSelectedTipoServicio(TbTipoServicio selectedTipoServicio) {
        this.selectedTipoServicio = selectedTipoServicio;
    }

    public String getSelectedServicio() {
        return selectedServicio;
    }

    public void setSelectedServicio(String selectedServicio) {
        this.selectedServicio = selectedServicio;
    }

   
    
    
}
