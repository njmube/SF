package manageBeans;

import entity.TbCliente;
import helper.ContratoFH;
import entity.TbContrato;
import entity.TbTipoContrato;
import helper.ClienteFH;
import helper.TipoContratoFH;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

 
@ManagedBean(name = "contratoBean")
@ViewScoped 
public class ContratoBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbContrato> lista;
    private TbContrato newContrato;
    private TbContrato selectedContrato;    
    private String selectedCliente;
    private List<TbCliente> listaCliente;
    private String selectedTipoContrato;
    private List<TbTipoContrato> listaTipoContrato;
    private Date FInicial;
    private Date FFinal;    


    public ContratoBean() {
        updateList();
        updateCliente();
        updateTipoContrato();
        this.newContrato = new TbContrato();
        this.selectedContrato = new TbContrato();
        this.FFinal = null;
        this.FInicial = null;        
    }
    
    public void btnCreate() {
        ContratoFH helperC = new ContratoFH();
        String msg;
        setearCliente(this.newContrato);
        if (helperC.create(this.newContrato)) {
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
        ContratoFH helperC = new ContratoFH();
        String msg;  
        setearCliente(this.selectedContrato);
        if (helperC.update(this.selectedContrato)) {
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
        ContratoFH helperC = new ContratoFH();
        String msg;
        if (helperC.delete(this.selectedContrato)) {
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
    
    public String getCliente(Integer idCliente){
        ClienteFH helperC = new ClienteFH();
        TbCliente cliente = helperC.search(idCliente);
        String nombre = cliente.getCliNombre();
        String apellido = cliente.getCliApellido();
        return nombre + ',' + apellido;
        
    }
    
    public String getTipoContrato(Integer idTipoContrato){
        TipoContratoFH helperTC = new TipoContratoFH();
        TbTipoContrato tipoContrato = helperTC.search(idTipoContrato);
        return tipoContrato.getTcDescripcion();
    }
        
    private void updateList() {
        ContratoFH helperC = new ContratoFH();
        this.lista = helperC.listAll();
    }    
 
    private void updateCliente() {
        ClienteFH helperC = new ClienteFH();
        this.listaCliente = helperC.listAll();
        this.selectedCliente = null;
    }
   
    private void updateTipoContrato() {
        TipoContratoFH helperTC = new TipoContratoFH();
        this.listaTipoContrato = helperTC.listAll();
        this.selectedTipoContrato = null;
    }

    private void setearCliente(TbContrato contrato) {
        Integer monto = contrato.getConCantidadCuotas() * contrato.getConMontoCuota();
        contrato.setConMonto(monto);
        
        ClienteFH helperC = new ClienteFH();
        TbCliente cliente = helperC.searchNyA(this.selectedCliente);
        contrato.setTbCliente(cliente);
        
        TipoContratoFH helperTC = new TipoContratoFH();
        TbTipoContrato tipoContrato = helperTC.searchDescripcion(this.selectedTipoContrato);
        contrato.setTbTipoContrato(tipoContrato);
        
        contrato.setConFechaFin(this.FFinal);
        contrato.setConFechaInicio(this.FInicial);
    }

    
    // Get & Set
    public List<TbContrato> getLista() {
        return lista;
    }

    public void setLista(List<TbContrato> lista) {
        this.lista = lista;
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

    public String getSelectedTipoContrato() {
        return selectedTipoContrato;
    }

    public void setSelectedTipoContrato(String selectedTipoContrato) {
        this.selectedTipoContrato = selectedTipoContrato;
    }

    public List<TbTipoContrato> getListaTipoContrato() {
        return listaTipoContrato;
    }

    public void setListaTipoContrato(List<TbTipoContrato> listaTipoContrato) {
        this.listaTipoContrato = listaTipoContrato;
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

    public TbContrato getNewContrato() {
        return newContrato;
    }

    public void setNewContrato(TbContrato newContrato) {
        this.newContrato = newContrato;
    }

    public TbContrato getSelectedContrato() {
        return selectedContrato;
    }

    public void setSelectedContrato(TbContrato selectedContrato) {
        this.selectedContrato = selectedContrato;
    }


}