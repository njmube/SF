package entity;
// Generated 25/04/2014 10:50:16 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbTipoContrato generated by hbm2java
 */
public class TbTipoContrato  implements java.io.Serializable {


     private int tcCod;
     private String tcDescripcion;
     private String tcUserInsert;
     private String tcUserUpdate;
     private Date tcFechaInsert;
     private Date tcFechaUpdate;
     private Set tbContratos = new HashSet(0);

    public TbTipoContrato() {
    }

	
    public TbTipoContrato(int tcCod, String tcUserInsert, String tcUserUpdate, Date tcFechaInsert, Date tcFechaUpdate) {
        this.tcCod = tcCod;
        this.tcUserInsert = tcUserInsert;
        this.tcUserUpdate = tcUserUpdate;
        this.tcFechaInsert = tcFechaInsert;
        this.tcFechaUpdate = tcFechaUpdate;
    }
    public TbTipoContrato(int tcCod, String tcDescripcion, String tcUserInsert, String tcUserUpdate, Date tcFechaInsert, Date tcFechaUpdate, Set tbContratos) {
       this.tcCod = tcCod;
       this.tcDescripcion = tcDescripcion;
       this.tcUserInsert = tcUserInsert;
       this.tcUserUpdate = tcUserUpdate;
       this.tcFechaInsert = tcFechaInsert;
       this.tcFechaUpdate = tcFechaUpdate;
       this.tbContratos = tbContratos;
    }
   
    public int getTcCod() {
        return this.tcCod;
    }
    
    public void setTcCod(int tcCod) {
        this.tcCod = tcCod;
    }
    public String getTcDescripcion() {
        return this.tcDescripcion;
    }
    
    public void setTcDescripcion(String tcDescripcion) {
        this.tcDescripcion = tcDescripcion;
    }
    public String getTcUserInsert() {
        return this.tcUserInsert;
    }
    
    public void setTcUserInsert(String tcUserInsert) {
        this.tcUserInsert = tcUserInsert;
    }
    public String getTcUserUpdate() {
        return this.tcUserUpdate;
    }
    
    public void setTcUserUpdate(String tcUserUpdate) {
        this.tcUserUpdate = tcUserUpdate;
    }
    public Date getTcFechaInsert() {
        return this.tcFechaInsert;
    }
    
    public void setTcFechaInsert(Date tcFechaInsert) {
        this.tcFechaInsert = tcFechaInsert;
    }
    public Date getTcFechaUpdate() {
        return this.tcFechaUpdate;
    }
    
    public void setTcFechaUpdate(Date tcFechaUpdate) {
        this.tcFechaUpdate = tcFechaUpdate;
    }
    public Set getTbContratos() {
        return this.tbContratos;
    }
    
    public void setTbContratos(Set tbContratos) {
        this.tbContratos = tbContratos;
    }




}

