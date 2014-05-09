package manageBeans;

import entity.TbCliente;
import entity.TbContrato;
import entity.TbTipoContrato;
import helper.ClienteFH;
import helper.ContratoFH;
import helper.TipoContratoFH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author stfy-warrior
 */
@ManagedBean (name = "informeContratoBean")
@ViewScoped
public class InformeContratoBean implements Serializable {
 
    private String selectedCliente;     
    private List<TbCliente> listaCliente;   
    private List<TbContrato> listaContrato;
    
    private String selectedTipoContrato;
    private List<TbTipoContrato> listaTipoContrato;
    

    /**
     * Creates a new instance of InformeContratoBean
     */
    public InformeContratoBean() {
        ClienteFH helperC = new ClienteFH();
        this.listaCliente = new ArrayList<TbCliente>();
        this.listaCliente = helperC.listAll();
        this.selectedCliente = null; 
        TipoContratoFH helperTC = new TipoContratoFH();
        this.listaTipoContrato = helperTC.listAll();
        this.selectedTipoContrato = null;
 
    }
    /**
     * Lista de Contrato por cliente
     */ 
    public void listarXCliente(){
        if ( selectedCliente != null){
            ClienteFH helperC = new ClienteFH();
            TbCliente cliente = helperC.searchNyA(selectedCliente); 
            
            if (cliente != null){
                ContratoFH helperCo = new ContratoFH();  
                Integer idCliente = cliente.getCliCod();
                this.listaContrato = helperCo.listXCliente(idCliente);
            }
            
        }
        
    }
    
    /**
     * Lista de Contrato por tipo de contrato
     */ 
    public void listarXTipoContrato(){
        if ( selectedTipoContrato != null){
            TipoContratoFH helperTC = new TipoContratoFH();
            TbTipoContrato tipoContrato = helperTC.searchDescripcion(selectedTipoContrato); 
            
            if (tipoContrato != null){
                ContratoFH helperCo = new ContratoFH();  
                Integer idTipoContrato = tipoContrato.getTcCod();
                this.listaContrato = helperCo.listXTipoContrato(idTipoContrato);
            }
            
        }
        
    }
    // get & set
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

    public List<TbContrato> getListaContrato() {
        return listaContrato;
    }

    public void setListaContrato(List<TbContrato> listaContrato) {
        this.listaContrato = listaContrato;
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
    
    

}