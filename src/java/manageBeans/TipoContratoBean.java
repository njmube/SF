package manageBeans;

import helper.TipoContratoFH;
import entity.TbTipoContrato;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "tipoContratoBean")
@ViewScoped 
public class TipoContratoBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbTipoContrato> listaTipoContrato;
    private TbTipoContrato newTipoContrato;
    private TbTipoContrato selectedTipoContrato;
    private String selectedServicio;


    public TipoContratoBean() {
        updateList();
        this.newTipoContrato = new TbTipoContrato();
        this.selectedTipoContrato = new TbTipoContrato();
    }

    public void btnTipoContratoCreate() {
        TipoContratoFH helperU = new TipoContratoFH();
        String msg;
        if (helperU.create(this.newTipoContrato)) {
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
    public void btnTipoContratoUpdate() {  
        TipoContratoFH helperU = new TipoContratoFH();
        String msg;
        if (helperU.update(this.selectedTipoContrato)) {
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
    public void btnTipoContratoDelete() {
        String msg;
        TipoContratoFH helperU = new TipoContratoFH();
        TbTipoContrato tipoContrato = helperU.search(this.selectedTipoContrato.getTcCod());
        if (helperU.delete(tipoContrato)) {
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
        TipoContratoFH helperU = new TipoContratoFH();
        this.listaTipoContrato = helperU.listAll();
    } 
  
    
    // Get & Set
    public List<TbTipoContrato> getListaTipoContrato() {
        return listaTipoContrato;
    }

    public void setListaServicio(List<TbTipoContrato> listaTipoContrato) {
        this.listaTipoContrato = listaTipoContrato;
    }

    public TbTipoContrato getNewTipoContrato() {
        return newTipoContrato;
    }

    public void setNewTipoContrato(TbTipoContrato newTipoContrato) {
        this.newTipoContrato = newTipoContrato;
    }

    public TbTipoContrato getSelectedTipoContrato() {
        return selectedTipoContrato;
    }

    public void setSelectedTipoContrato(TbTipoContrato selectedTipoContrato) {
        this.selectedTipoContrato = selectedTipoContrato;
    }

    public String getSelectedServicio() {
        return selectedServicio;
    }

    public void setSelectedServicio(String selectedServicio) {
        this.selectedServicio = selectedServicio;
    }

   
    
    
}
