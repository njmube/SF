package entity;
// Generated 25/04/2014 10:50:16 AM by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * TbPermiso generated by hbm2java
 */
public class TbPermiso  implements java.io.Serializable {


     private int perCod;
     private String perDescripcion;
     private Boolean perAdd;
     private Boolean perDelete;
     private Boolean perUpdate;
     private String perUserInsert;
     private String perUserUpdate;
     private Date perFechaInsert;
     private Date perFechaUpdate;
     private Set tbRolesXPermisos = new HashSet(0);

    public TbPermiso() {
    }

	
    public TbPermiso(int perCod, String perUserInsert, String perUserUpdate, Date perFechaInsert, Date perFechaUpdate) {
        this.perCod = perCod;
        this.perUserInsert = perUserInsert;
        this.perUserUpdate = perUserUpdate;
        this.perFechaInsert = perFechaInsert;
        this.perFechaUpdate = perFechaUpdate;
    }
    public TbPermiso(int perCod, String perDescripcion, Boolean perAdd, Boolean perDelete, Boolean perUpdate, String perUserInsert, String perUserUpdate, Date perFechaInsert, Date perFechaUpdate, Set tbRolesXPermisos) {
       this.perCod = perCod;
       this.perDescripcion = perDescripcion;
       this.perAdd = perAdd;
       this.perDelete = perDelete;
       this.perUpdate = perUpdate;
       this.perUserInsert = perUserInsert;
       this.perUserUpdate = perUserUpdate;
       this.perFechaInsert = perFechaInsert;
       this.perFechaUpdate = perFechaUpdate;
       this.tbRolesXPermisos = tbRolesXPermisos;
    }
   
    public int getPerCod() {
        return this.perCod;
    }
    
    public void setPerCod(int perCod) {
        this.perCod = perCod;
    }
    public String getPerDescripcion() {
        return this.perDescripcion;
    }
    
    public void setPerDescripcion(String perDescripcion) {
        this.perDescripcion = perDescripcion;
    }
    public Boolean getPerAdd() {
        return this.perAdd;
    }
    
    public void setPerAdd(Boolean perAdd) {
        this.perAdd = perAdd;
    }
    public Boolean getPerDelete() {
        return this.perDelete;
    }
    
    public void setPerDelete(Boolean perDelete) {
        this.perDelete = perDelete;
    }
    public Boolean getPerUpdate() {
        return this.perUpdate;
    }
    
    public void setPerUpdate(Boolean perUpdate) {
        this.perUpdate = perUpdate;
    }
    public String getPerUserInsert() {
        return this.perUserInsert;
    }
    
    public void setPerUserInsert(String perUserInsert) {
        this.perUserInsert = perUserInsert;
    }
    public String getPerUserUpdate() {
        return this.perUserUpdate;
    }
    
    public void setPerUserUpdate(String perUserUpdate) {
        this.perUserUpdate = perUserUpdate;
    }
    public Date getPerFechaInsert() {
        return this.perFechaInsert;
    }
    
    public void setPerFechaInsert(Date perFechaInsert) {
        this.perFechaInsert = perFechaInsert;
    }
    public Date getPerFechaUpdate() {
        return this.perFechaUpdate;
    }
    
    public void setPerFechaUpdate(Date perFechaUpdate) {
        this.perFechaUpdate = perFechaUpdate;
    }
    public Set getTbRolesXPermisos() {
        return this.tbRolesXPermisos;
    }
    
    public void setTbRolesXPermisos(Set tbRolesXPermisos) {
        this.tbRolesXPermisos = tbRolesXPermisos;
    }




}

