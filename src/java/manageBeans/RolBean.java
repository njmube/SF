package manageBeans;

import entity.TbPermiso;
import helper.RolFH;
import entity.TbRoles;
import entity.TbRolesXPermiso;
import helper.PermisoFH;
import helper.RolesXPermisoFH;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.model.DualListModel;
 
@ManagedBean(name = "rolBean")
@ViewScoped 
public class RolBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private List<TbRoles> listaRol;
    private List<TbPermiso> listaPermiso;
    private TbRoles newRol;
    private TbRoles selectedRol;
    private DualListModel<String> permisos; 

    public RolBean() {
        updateList();       
        updatePermisos();     
        this.newRol = new TbRoles();
        this.selectedRol = new TbRoles();  
        
    }

    public void btnCreate() {
        RolFH helperR = new RolFH();
        String msg;
        if (helperR.create(this.newRol)) {
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
        RolFH helperR = new RolFH();
        String msg;
        if (helperR.update(this.selectedRol)) {
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
        String msg;
        RolFH helperR = new RolFH();
        TbRoles rol = helperR.search(this.selectedRol.getRolCod());
        if (helperR.delete(rol)) {
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
    
    public void btnConfig() {  
        RolesXPermisoFH helperRXP = new RolesXPermisoFH();
        String msg;        
        if (helperRXP.config(this.selectedRol, this.permisos.getTarget())) {
            msg = "se configuro";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
            updateList();
        } else {
            msg = "error al configurar";
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
    
    private void updateList() {
        RolFH helperR = new RolFH();
        this.listaRol = helperR.listAll();
    }
      
    private void updatePermisos() {
        PermisoFH helperP = new PermisoFH();
        List<String> source = new ArrayList<String>();  
        List<String> target = new ArrayList<String>();  
        List<TbPermiso> lista = helperP.listAll();         
        this.permisos = new DualListModel<String>(source, target);  
    
    }
      
    public DualListModel<String> getPermisos(TbRoles rol){
        List<String> source = permisosSource(rol.getRolCod()); // permisos que no son del rol
        List<String> target = permisosTarget(rol.getRolCod()); // permisos que son del rol
        DualListModel<String> per = new DualListModel<String>(source, target);
        return per;
    } 
    
    public List<TbPermiso> permisosDelRol(TbRoles rol){
        List<TbPermiso> listaPer = new ArrayList<TbPermiso>(); 
        RolesXPermisoFH helperRXP = new RolesXPermisoFH();        
        List<TbRolesXPermiso> lista = helperRXP.listRXP(rol.getRolCod());
        PermisoFH helperP = new PermisoFH();
        for(TbRolesXPermiso rxp: lista){
            TbPermiso p = helperP.search(rxp.getTbPermiso().getPerCod());
            listaPer.add(p);
        }
        return listaPer;
    }
    
    private List<String> permisosTarget(Integer idRol){
        List<String> listaPer = new ArrayList<String>(); 
        RolesXPermisoFH helperRXP = new RolesXPermisoFH();        
        List<TbRolesXPermiso> lista = helperRXP.listRXP(idRol);
        PermisoFH helperP = new PermisoFH();
        for(TbRolesXPermiso rxp: lista){
            TbPermiso p = helperP.search(rxp.getTbPermiso().getPerCod());
            listaPer.add(p.getPerDescripcion());
        }
        return listaPer;
    }

    private List<String> permisosSource(Integer idRol){
        PermisoFH helperP = new PermisoFH();
        List<TbPermiso> lista = helperP.listAll();
        List<String> listaPer = new ArrayList<String>(); 
        List<String> listaPer1 = permisosTarget(idRol);
        boolean band; 
        for(TbPermiso p: lista){
           band = true; 
           for(String r: listaPer1){
               if (p.getPerDescripcion().equals(r))
                   band = false;
           }
           if (band)
               listaPer.add(p.getPerDescripcion()); 
        }  
              
        return listaPer;
    }

    // Get & Set
    public List<TbRoles> getListaRol() {
        return listaRol;
    }

    public void setListaRol(List<TbRoles> listaRol) {
        this.listaRol = listaRol;
    }

    public TbRoles getNewRol() {
        return newRol;
    }

    public void setNewRol(TbRoles newRol) {
        this.newRol = newRol;
    }

    public TbRoles getSelectedRol() {
        return selectedRol;
    }

    public void setSelectedRol(TbRoles selectedRol) {
        this.selectedRol = selectedRol;
    }

    public DualListModel<String> getPermisos() {
        return permisos;
    }

    public void setPermisos(DualListModel<String> permisos) {
        this.permisos = permisos;
    }

    public List<TbPermiso> getListaPermiso() {
        return listaPermiso;
    }

    public void setListaPermiso(List<TbPermiso> listaPermiso) {
        this.listaPermiso = listaPermiso;
    }
    
}