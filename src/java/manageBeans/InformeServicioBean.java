package manageBeans;

import helper.ServicioFH;
import helper.TipoServicioFH;
import entity.TbServicio;
import entity.TbTipoServicio;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author stfy-warrior
 */
@ManagedBean (name = "informeServicioBean")
@ViewScoped
public class InformeServicioBean implements Serializable {
 
    private String selectedTipoServicio;     
    private List<TbTipoServicio> listaTipoServicio;   
    private List<TbServicio> listaServicio;
    

    /**
     * Creates a new instance of InformeServicioBean
     */
    public InformeServicioBean() {
        TipoServicioFH helperST = new TipoServicioFH();
        this.listaTipoServicio = new ArrayList<TbTipoServicio>();
        this.listaTipoServicio = helperST.listAll();
        this.selectedTipoServicio = null;
        
 
    }
    /**
     * Lista de servicio por tipo 
     */ 
    public void listarXTipo(){
        if ( selectedTipoServicio != null){
            TipoServicioFH helperTS = new TipoServicioFH();
            TbTipoServicio tipoServicio = helperTS.searchTipoServicio(selectedTipoServicio); 
            
            if (tipoServicio != null){
                ServicioFH helperS = new ServicioFH();  
                Integer idTipoServicio = tipoServicio.getTsCod();
                this.listaServicio = helperS.listXTipoServicio(idTipoServicio);
            }
            
        }
        
    }
    
    // get & set
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

    public List<TbServicio> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(List<TbServicio> listaServicio) {
        this.listaServicio = listaServicio;
    }
}