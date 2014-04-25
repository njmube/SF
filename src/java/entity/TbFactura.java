package entity;
// Generated 25/04/2014 10:50:16 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbFactura generated by hbm2java
 */
public class TbFactura  implements java.io.Serializable {


     private int facCod;
     private TbCliente tbCliente;
     private TbTimbrado tbTimbrado;
     private String facTipo;
     private int facNro;
     private Date facFechaEmision;
     private Integer facTotal;
     private String facUserInsert;
     private String facUserUpdate;
     private Date facFechaInsert;
     private Date facFechaUpdate;
     private boolean facEstado;
     private Set tbFacturaXDetalleFacturas = new HashSet(0);
     private Set tbFacturaXContratos = new HashSet(0);

    public TbFactura() {
    }

	
    public TbFactura(int facCod, TbCliente tbCliente, TbTimbrado tbTimbrado, String facTipo, int facNro, Date facFechaEmision, String facUserInsert, String facUserUpdate, Date facFechaInsert, Date facFechaUpdate, boolean facEstado) {
        this.facCod = facCod;
        this.tbCliente = tbCliente;
        this.tbTimbrado = tbTimbrado;
        this.facTipo = facTipo;
        this.facNro = facNro;
        this.facFechaEmision = facFechaEmision;
        this.facUserInsert = facUserInsert;
        this.facUserUpdate = facUserUpdate;
        this.facFechaInsert = facFechaInsert;
        this.facFechaUpdate = facFechaUpdate;
        this.facEstado = facEstado;
    }
    public TbFactura(int facCod, TbCliente tbCliente, TbTimbrado tbTimbrado, String facTipo, int facNro, Date facFechaEmision, Integer facTotal, String facUserInsert, String facUserUpdate, Date facFechaInsert, Date facFechaUpdate, boolean facEstado, Set tbFacturaXDetalleFacturas, Set tbFacturaXContratos) {
       this.facCod = facCod;
       this.tbCliente = tbCliente;
       this.tbTimbrado = tbTimbrado;
       this.facTipo = facTipo;
       this.facNro = facNro;
       this.facFechaEmision = facFechaEmision;
       this.facTotal = facTotal;
       this.facUserInsert = facUserInsert;
       this.facUserUpdate = facUserUpdate;
       this.facFechaInsert = facFechaInsert;
       this.facFechaUpdate = facFechaUpdate;
       this.facEstado = facEstado;
       this.tbFacturaXDetalleFacturas = tbFacturaXDetalleFacturas;
       this.tbFacturaXContratos = tbFacturaXContratos;
    }
   
    public int getFacCod() {
        return this.facCod;
    }
    
    public void setFacCod(int facCod) {
        this.facCod = facCod;
    }
    public TbCliente getTbCliente() {
        return this.tbCliente;
    }
    
    public void setTbCliente(TbCliente tbCliente) {
        this.tbCliente = tbCliente;
    }
    public TbTimbrado getTbTimbrado() {
        return this.tbTimbrado;
    }
    
    public void setTbTimbrado(TbTimbrado tbTimbrado) {
        this.tbTimbrado = tbTimbrado;
    }
    public String getFacTipo() {
        return this.facTipo;
    }
    
    public void setFacTipo(String facTipo) {
        this.facTipo = facTipo;
    }
    public int getFacNro() {
        return this.facNro;
    }
    
    public void setFacNro(int facNro) {
        this.facNro = facNro;
    }
    public Date getFacFechaEmision() {
        return this.facFechaEmision;
    }
    
    public void setFacFechaEmision(Date facFechaEmision) {
        this.facFechaEmision = facFechaEmision;
    }
    public Integer getFacTotal() {
        return this.facTotal;
    }
    
    public void setFacTotal(Integer facTotal) {
        this.facTotal = facTotal;
    }
    public String getFacUserInsert() {
        return this.facUserInsert;
    }
    
    public void setFacUserInsert(String facUserInsert) {
        this.facUserInsert = facUserInsert;
    }
    public String getFacUserUpdate() {
        return this.facUserUpdate;
    }
    
    public void setFacUserUpdate(String facUserUpdate) {
        this.facUserUpdate = facUserUpdate;
    }
    public Date getFacFechaInsert() {
        return this.facFechaInsert;
    }
    
    public void setFacFechaInsert(Date facFechaInsert) {
        this.facFechaInsert = facFechaInsert;
    }
    public Date getFacFechaUpdate() {
        return this.facFechaUpdate;
    }
    
    public void setFacFechaUpdate(Date facFechaUpdate) {
        this.facFechaUpdate = facFechaUpdate;
    }
    public boolean isFacEstado() {
        return this.facEstado;
    }
    
    public void setFacEstado(boolean facEstado) {
        this.facEstado = facEstado;
    }
    public Set getTbFacturaXDetalleFacturas() {
        return this.tbFacturaXDetalleFacturas;
    }
    
    public void setTbFacturaXDetalleFacturas(Set tbFacturaXDetalleFacturas) {
        this.tbFacturaXDetalleFacturas = tbFacturaXDetalleFacturas;
    }
    public Set getTbFacturaXContratos() {
        return this.tbFacturaXContratos;
    }
    
    public void setTbFacturaXContratos(Set tbFacturaXContratos) {
        this.tbFacturaXContratos = tbFacturaXContratos;
    }




}

