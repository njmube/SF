package manageBeans;

import helper.TimbradoFH;
import entity.TbTimbrado;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "timbradoBean")
@ViewScoped 
public class TimbradoBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbTimbrado> listaTimbrado;
    private TbTimbrado newTimbrado;
    private TbTimbrado selectedTimbrado;
    private Date FInicial;
    private Date FFinal;
    

    public TimbradoBean() {
        updateList();
        this.newTimbrado = new TbTimbrado();
        this.selectedTimbrado = new TbTimbrado();
    }

    public void btnTimbradoCreate() {
        TimbradoFH helperTim = new TimbradoFH();
        String msg;
        this.newTimbrado.setTimSecuencia(9999999);
        setearFecha(this.newTimbrado);
        if (helperTim.create(this.newTimbrado)) {
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
    public void btnTimbradoUpdate() {  
        TimbradoFH helperTim = new TimbradoFH();
        String msg;
        this.selectedTimbrado.setTimSecuencia(9999999);
        setearFecha(this.selectedTimbrado);
        if (helperTim.update(this.selectedTimbrado)) {
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
    public void btnTimbradoDelete() {
        String msg;
        TimbradoFH helperTim = new TimbradoFH();
        TbTimbrado timbrado = helperTim.search(this.selectedTimbrado.getTimCod());
        if (helperTim.delete(timbrado)) {
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
        TimbradoFH helperTim = new TimbradoFH();
        this.listaTimbrado = helperTim.listAll();
    }

    // Get & Set
    public List<TbTimbrado> getListaTimbrado() {
        return listaTimbrado;
    }

    public void setListaTimbrado(List<TbTimbrado> listaTimbrado) {
        this.listaTimbrado = listaTimbrado;
    }

    public TbTimbrado getNewTimbrado() {
        return newTimbrado;
    }

    public void setNewTimbrado(TbTimbrado newTimbrado) {
        this.newTimbrado = newTimbrado;
    }

    public TbTimbrado getSelectedTimbrado() {
        return selectedTimbrado;
    }

    public void setSelectedTimbrado(TbTimbrado selectedTimbrado) {
        this.selectedTimbrado = selectedTimbrado;
    }

    public Date getFInicial() {
        return FInicial;
    }

    public void setFInicial(Date FInicial) {
        this.FInicial = FInicial;
    }

    public Date getFFinal() {
        return FFinal;
    }

    public void setFFinal(Date FFinal) {
        this.FFinal = FFinal;
    }

    private void setearFecha(TbTimbrado timbrado) {
        timbrado.setTimInicioVigencia(FInicial);
        timbrado.setTimFinVigencia(FFinal);
    }
    
   
}
