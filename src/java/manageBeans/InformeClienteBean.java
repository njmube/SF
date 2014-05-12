package manageBeans;

import entity.TbCliente;
import helper.ClienteFH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author stfy-warrior
 */
@ManagedBean (name = "informeClienteBean")
@ViewScoped
public class InformeClienteBean implements Serializable {
 

    private List<TbCliente> listaCliente;    

    /**
     * Creates a new instance of InformeClienteBean
     */
    public InformeClienteBean() { 
        this.listaCliente = new ArrayList<TbCliente>();
    }
    /**
     * Lista de Cliente
     */ 
    public void listarCliente(){
        ClienteFH helperC = new ClienteFH();
        this.listaCliente = helperC.listAll();
    }
    
    // get & set
    public List<TbCliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<TbCliente> listaCliente) {
        this.listaCliente = listaCliente;
    }
    
}