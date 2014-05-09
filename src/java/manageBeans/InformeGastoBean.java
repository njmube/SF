/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package manageBeans;


import helper.GastoFH;
import entity.TbGasto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


/**
 *
 * @author stfy-warrior
 */
@ManagedBean (name = "informeGastoBean")
@ViewScoped
public class InformeGastoBean implements Serializable {    
    private List<TbGasto> listaGasto;
    private Date FInicial;
    private Date FFinal;    
    private Double total;
    
    /**
     * Creates a new instance of InformeSueldoBean
     */
    public InformeGastoBean() {        
        this.listaGasto = new ArrayList<TbGasto>();        
        this.FFinal = null;
        this.FInicial = null;
    }
    
    public void listar(){        
        this.total = 0.0;
        if ( FFinal != null && FInicial != null){
             GastoFH helperD = new GastoFH();              
             listaGasto = helperD.listarXFecha(FInicial, FFinal); 
             calcularTotal();
            } 
        }

    private void calcularTotal() {
        for(TbGasto g: listaGasto)
            total += g.getGaMonto();
    }

    // get & set
    public List<TbGasto> getListaGasto() {
        return listaGasto;
    }

    public void setListaGasto(List<TbGasto> listaGasto) {
        this.listaGasto = listaGasto;
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

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
 }



