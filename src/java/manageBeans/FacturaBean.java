package manageBeans;

import entity.TbCliente;
import entity.TbDetalleFactura;
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
    
    private List<TbDetalleFactura> selectedListaDetalle;
    private List<TbDetalleFactura> newListaDetalle;    
    private TbDetalleFactura newDetalle;
    private TbDetalleFactura selectedDetalle;
    private Double montoCinco;
    private Double montoDiez ;
    private Double montoExentas; 
    
    

    public FacturaBean() {
        updateList();
        updateCliente();
        this.newFactura = new TbFactura();
        this.selectedFactura = new TbFactura();
        this.newDetalle = new TbDetalleFactura();
        this.selectedDetalle = new TbDetalleFactura();
    }

    public void btnFacturaCreate() {
        FacturaFH helperF = new FacturaFH();
        String msg;
        if (helperF.create(this.newFactura, this.newListaDetalle)) {
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
        if (helperF.update(this.selectedFactura, this.selectedListaDetalle)) {
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
        if (helperF.delete(factura, this.selectedListaDetalle)) {
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

    public List<TbDetalleFactura> getSelectedListaDetalle() {
        return selectedListaDetalle;
    }

    public void setSelectedListaDetalle(List<TbDetalleFactura> selectedListaDetalle) {
        this.selectedListaDetalle = selectedListaDetalle;
    }

    public List<TbDetalleFactura> getNewListaDetalle() {
        return newListaDetalle;
    }

    public void setNewListaDetalle(List<TbDetalleFactura> newListaDetalle) {
        this.newListaDetalle = newListaDetalle;
    }

    public TbDetalleFactura getNewDetalle() {
        return newDetalle;
    }

    public void setNewDetalle(TbDetalleFactura newDetalle) {
        this.newDetalle = newDetalle;
    }

    public TbDetalleFactura getSelectedDetalle() {
        return selectedDetalle;
    }

    public void setSelectedDetalle(TbDetalleFactura selectedDetalle) {
        this.selectedDetalle = selectedDetalle;
    }

    public Double getMontoCinco() {
        return montoCinco;
    }

    public void setMontoCinco(Double montoCinco) {
        this.montoCinco = montoCinco;
    }

    public Double getMontoDiez() {
        return montoDiez;
    }

    public void setMontoDiez(Double montoDiez) {
        this.montoDiez = montoDiez;
    }

    public Double getMontoExentas() {
        return montoExentas;
    }

    public void setMontoExentas(Double montoExentas) {
        this.montoExentas = montoExentas;
    }
    
    
}