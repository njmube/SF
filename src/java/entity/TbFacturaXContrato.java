package entity;
// Generated 25/04/2014 10:50:16 AM by Hibernate Tools 3.6.0


import java.util.Date;

/**
 * TbFacturaXContrato generated by hbm2java
 */
public class TbFacturaXContrato  implements java.io.Serializable {


     private int facconCod;
     private TbContrato tbContrato;
     private TbFactura tbFactura;
     private String facconUserInsert;
     private String facconUserUpdate;
     private Date facconFechaInsert;
     private Date facconFechaUpdate;

    public TbFacturaXContrato() {
    }

    public TbFacturaXContrato(int facconCod, TbContrato tbContrato, TbFactura tbFactura, String facconUserInsert, String facconUserUpdate, Date facconFechaInsert, Date facconFechaUpdate) {
       this.facconCod = facconCod;
       this.tbContrato = tbContrato;
       this.tbFactura = tbFactura;
       this.facconUserInsert = facconUserInsert;
       this.facconUserUpdate = facconUserUpdate;
       this.facconFechaInsert = facconFechaInsert;
       this.facconFechaUpdate = facconFechaUpdate;
    }
   
    public int getFacconCod() {
        return this.facconCod;
    }
    
    public void setFacconCod(int facconCod) {
        this.facconCod = facconCod;
    }
    public TbContrato getTbContrato() {
        return this.tbContrato;
    }
    
    public void setTbContrato(TbContrato tbContrato) {
        this.tbContrato = tbContrato;
    }
    public TbFactura getTbFactura() {
        return this.tbFactura;
    }
    
    public void setTbFactura(TbFactura tbFactura) {
        this.tbFactura = tbFactura;
    }
    public String getFacconUserInsert() {
        return this.facconUserInsert;
    }
    
    public void setFacconUserInsert(String facconUserInsert) {
        this.facconUserInsert = facconUserInsert;
    }
    public String getFacconUserUpdate() {
        return this.facconUserUpdate;
    }
    
    public void setFacconUserUpdate(String facconUserUpdate) {
        this.facconUserUpdate = facconUserUpdate;
    }
    public Date getFacconFechaInsert() {
        return this.facconFechaInsert;
    }
    
    public void setFacconFechaInsert(Date facconFechaInsert) {
        this.facconFechaInsert = facconFechaInsert;
    }
    public Date getFacconFechaUpdate() {
        return this.facconFechaUpdate;
    }
    
    public void setFacconFechaUpdate(Date facconFechaUpdate) {
        this.facconFechaUpdate = facconFechaUpdate;
    }




}

