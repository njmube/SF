package manageBeans;

import helper.ServicioFH;
import entity.TbServicio;
import entity.TbTipoServicio;
import helper.TipoServicioFH;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "servicioBean")
@ViewScoped 
public class ServicioBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbServicio> listaServicio;
    private TbServicio newServicio;
    private TbServicio selectedServicio;
    private String selectedTipoServicio;
    private List <TbTipoServicio> listaTipoServicio;
    
    public ServicioBean() {
        updateList();
        updateTipoServicio();
        this.newServicio = new TbServicio();
        this.selectedServicio = new TbServicio();
    }

    public void btnServicioCreate() {
        ServicioFH helperS = new ServicioFH();
        String msg;
        setearTipoServicio(this.newServicio);
        if (helperS.create(this.newServicio)) {
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
    public void btnServicioUpdate() {  
        ServicioFH helperS = new ServicioFH();
        String msg;
        setearTipoServicio(this.selectedServicio);
        if (helperS.update(this.selectedServicio)) {
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
    public void btnServicioDelete() {
        String msg;
        ServicioFH helperS = new ServicioFH();
        TbServicio servicio = helperS.search(this.selectedServicio.getSerCod());
        if (helperS.delete(servicio)) {
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
        ServicioFH helperS = new ServicioFH();
        this.listaServicio = helperS.listAll();
    } 
    
    private void updateTipoServicio() {
        TipoServicioFH helperTS = new TipoServicioFH();
        this.listaTipoServicio = helperTS.listAll();
    }
    
  
    private void setearTipoServicio(TbServicio servicio) {
        TipoServicioFH helperTS = new TipoServicioFH();
        TbTipoServicio tipoServicio = helperTS.searchTipoServicio(selectedTipoServicio);
        servicio.setTbTipoServicio(tipoServicio);
    }
    
    public String getTipoServicio(Integer idTipoServicio){
        TipoServicioFH helperTS = new TipoServicioFH();
        TbTipoServicio tipoServicio = helperTS.search(idTipoServicio);
        return tipoServicio.getTsDescripcion();
    }

    // Get & Set
    public List<TbServicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<TbServicio> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public TbServicio getNewServicio() {
        return newServicio;
    }

    public void setNewServicio(TbServicio newServicio) {
        this.newServicio = newServicio;
    }

    public TbServicio getSelectedServicio() {
        return selectedServicio;
    }

    public void setSelectedServicio(TbServicio selectedServicio) {
        this.selectedServicio = selectedServicio;
    }

    public String getSelectedTipoServicio() {
        return selectedTipoServicio;
    }

    public void setSelectedTipoServicio(String selectedTipoServicio) {
        this.selectedTipoServicio = selectedTipoServicio;
    }

    public List<TbTipoServicio> getListaTipoServicio() {
        return listaTipoServicio;
    }

    public void setListaTipoServicio(List<TbTipoServicio> listaTipoServicio) {
        this.listaTipoServicio = listaTipoServicio;
    }



}