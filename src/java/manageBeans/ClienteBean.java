package manageBeans;

import helper.ClienteFH;
import entity.TbCliente;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
 
@ManagedBean(name = "clienteBean")
@ViewScoped 
public class ClienteBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbCliente> listaCliente;
    private TbCliente newCliente;
    private TbCliente selectedCliente;

    public ClienteBean() {
        updateList();
        this.newCliente = new TbCliente();
        this.selectedCliente = new TbCliente();
    }

    public void btnClienteCreate() {
        ClienteFH helperC = new ClienteFH();
        String msg;
        if (helperC.create(this.newCliente)) {
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
    public void btnClienteUpdate() {  
        ClienteFH helperC = new ClienteFH();
        String msg;
        if (helperC.update(this.selectedCliente)) {
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
    public void btnClienteDelete() {
        String msg;
        ClienteFH helperC = new ClienteFH();
        TbCliente cliente = helperC.search(this.selectedCliente.getCliCod());
        if (helperC.delete(cliente)) {
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
        ClienteFH helperC = new ClienteFH();
        this.listaCliente = helperC.listAll();
    } 
  
    
    // Get & Set
    public List<TbCliente> getListaCliente() {
        return listaCliente;
    }

    public void setListaCliente(List<TbCliente> listaCliente) {
        this.listaCliente = listaCliente;
    }

    public TbCliente getNewCliente() {
        return newCliente;
    }

    public void setNewCliente(TbCliente newCliente) {
        this.newCliente = newCliente;
    }

    public TbCliente getSelectedCliente() {
        return selectedCliente;
    }

    public void setSelectedCliente(TbCliente selectedCliente) {
        this.selectedCliente = selectedCliente;
    }

    
    
}