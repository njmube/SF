package manageBeans;

import helper.GastoFH;
import entity.TbGasto;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "gastoBean")
@ViewScoped 
public class GastoBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbGasto> listaGasto;
    private TbGasto newGasto;
    private TbGasto selectedGasto;

    public GastoBean() {
        updateList();
        this.newGasto = new TbGasto();
        this.selectedGasto = new TbGasto();
    }

    public void btnGastoCreate() {
        GastoFH helperG = new GastoFH();
        String msg;
        if (helperG.create(this.newGasto)) {
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
    public void btnGastoUpdate() {  
        GastoFH helperG = new GastoFH();
        String msg;
        if (helperG.update(this.selectedGasto)) {
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
    public void btnGastoDelete() {
        String msg;
        GastoFH helperG = new GastoFH();
        TbGasto cliente = helperG.search(this.selectedGasto.getGaCod());
        if (helperG.delete(cliente)) {
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
        GastoFH helperG = new GastoFH();
        this.listaGasto = helperG.listAll();
    }

    // Get & Set
    public List<TbGasto> getListaGasto() {
        return listaGasto;
    }

    public void setListaGasto(List<TbGasto> listaGasto) {
        this.listaGasto = listaGasto;
    }

    public TbGasto getNewGasto() {
        return newGasto;
    }

    public void setNewGasto(TbGasto newGasto) {
        this.newGasto = newGasto;
    }

    public TbGasto getSelectedGasto() {
        return selectedGasto;
    }

    public void setSelectedGasto(TbGasto selectedGasto) {
        this.selectedGasto = selectedGasto;
    }
    

    
    
}

