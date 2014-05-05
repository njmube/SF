package manageBeans;

import entity.TbCliente;
import helper.FacturaFH;
import entity.TbFactura;
import helper.ClienteFH;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "facturaBean")
@ViewScoped 
public class FacturaBean implements Serializable {
 
    private static final long serialVersionUID = 1L;    
    private List<TbFactura> listaFacturaContado;
    private List<TbFactura> listaFacturaCredito;
    private TbFactura newFactura;
    private TbFactura selectedFactura;
    private String selectedCliente;
    private List<TbCliente> listaCliente;

    public FacturaBean() {
        updateList();
        updateCliente();
        this.newFactura = new TbFactura();
        this.selectedFactura = new TbFactura();
    }

    public void btnFacturaCreate() {
        FacturaFH helperF = new FacturaFH();
        String msg;
        if (helperF.create(this.newFactura)) {
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
    public void btnFacturaUpdate() {  
        FacturaFH helperF = new FacturaFH();
        String msg;
        if (helperF.update(this.selectedFactura)) {
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
    public void btnFacturaDelete() {
        String msg;
        FacturaFH helperF = new FacturaFH();
        TbFactura factura = helperF.search(this.selectedFactura.getFacCod());
        if (helperF.delete(factura)) {
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
        FacturaFH helperF = new FacturaFH();
        this.listaFacturaContado = helperF.listContado();
        this.listaFacturaCredito = helperF.listCredito();
    } 
  
    private void updateCliente() {
        ClienteFH helperC = new ClienteFH();
        this.listaCliente = helperC.listAll();
        this.selectedCliente = null;
    }
 
    public String getCliente(Integer idCliente){
        ClienteFH helperC = new ClienteFH();
        TbCliente cliente = helperC.search(idCliente);
        String nombre = cliente.getCliNombre();
        String apellido = cliente.getCliApellido();
        return nombre + ',' + apellido;
        
    }
    
    // Get & Set
    public List<TbFactura> getListaFacturaContado() {
        return listaFacturaContado;
    }

    public void setListaFacturaContado(List<TbFactura> listaFacturaContado) {
        this.listaFacturaContado = listaFacturaContado;
    }

    public List<TbFactura> getListaFacturaCredito() {
        return listaFacturaCredito;
    }

    public void setListaFacturaCredito(List<TbFactura> listaFacturaCredito) {
        this.listaFacturaCredito = listaFacturaCredito;
    }

    public TbFactura getNewFactura() {
        return newFactura;
    }

    public void setNewFactura(TbFactura newFactura) {
        this.newFactura = newFactura;
    }

    public TbFactura getSelectedFactura() {
        return selectedFactura;
    }

    public void setSelectedFactura(TbFactura selectedFactura) {
        this.selectedFactura = selectedFactura;
    }

    public String getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(String selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    public List<TbCliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<TbCliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
}