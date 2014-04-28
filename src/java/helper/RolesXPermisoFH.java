
package helper;

import entity.TbPermiso;
import entity.TbRoles;
import entity.TbRolesXPermiso;
import hibernate.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import manageBeans.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RolesXPermisoFH
{  
    private Session sesion; 
    private Transaction tx;  

    public Integer guardar(TbRolesXPermiso rxp) throws HibernateException 
    { 
        Integer id = 0;  

        try 
        { 
            iniciarOperacion(); 
            auditoriaGuardar(rxp);
            id = (Integer) sesion.save(rxp); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  

        return id; 
    }  

    public void actualizar(TbRolesXPermiso rxp) throws HibernateException 
    { 
        try 
        { 
            iniciarOperacion(); 
            auditoriaActualizar(rxp);
            sesion.update(rxp); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  
    
    public boolean config(TbRoles rol , List<String> permisos) throws HibernateException 
    { 
        boolean rta = false;
        PermisoFH helperP = new PermisoFH();
        TbPermiso per = null;
                    
        try 
        { 
            iniciarOperacion(); 
            System.err.println("rol: "+rol.getRolNombre());
            
            for (String p : permisos) {
                per = helperP.searchDescripcion(p);
                System.err.println("per: "+ per.getPerDescripcion());
                TbRolesXPermiso rxp = new TbRolesXPermiso();
                rxp.setTbPermiso(per);
                rxp.setTbRoles(rol);
                auditoriaGuardar(rxp);
                sesion.save(rxp); 
            }
            rta = true;
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
        return rta;
    } 
    
    public void eliminar(TbRolesXPermiso rxp) throws HibernateException 
    { 
        try 
        { 
            iniciarOperacion(); 
            sesion.delete(rxp); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public TbRolesXPermiso buscar(Integer idRolesXPantallas) throws HibernateException 
    { 
        TbRolesXPermiso rolesXPantallas = null;  
        try 
        { 
            iniciarOperacion(); 
            rolesXPantallas = (TbRolesXPermiso) sesion.get(TbRolesXPermiso.class, idRolesXPantallas); 
        } finally 
        { 
            sesion.close(); 
        }  

        return rolesXPantallas; 
    }  
    
    public List<TbRolesXPermiso> listar() throws HibernateException 
    { 
        List<TbRolesXPermiso> listaRolesXPantallass = null;  

        try 
        { 
            iniciarOperacion(); 
            listaRolesXPantallass = sesion.createQuery("from TbRolesXPermiso").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaRolesXPantallass; 
    }  

    public List<TbPermiso> getPermisos(Integer idRol) throws HibernateException 
    { 
        List<TbRolesXPermiso> lista = null;  
        List<TbPermiso> listaP = null; 
        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbRolesXPermiso where tbRoles = '"+ idRol + "'";
            lista = sesion.createQuery(cadena).list(); 
            for (TbRolesXPermiso p : lista) {  
                listaP.add(p.getTbPermiso()); 
            }  
        } finally 
        { 
            sesion.close(); 
        }  

        return listaP; 
    }
    
    private void iniciarOperacion() throws HibernateException 
    { 
        sesion = HibernateUtil.getSessionFactory().openSession(); 
        tx = sesion.beginTransaction(); 
    }  

    private void manejarExcepcion(HibernateException he) throws HibernateException 
    { 
        tx.rollback(); 
        throw new HibernateException("Ocurrió un error en la capa de acceso a datos", he); 
    } 

    private void auditoriaGuardar(TbRolesXPermiso rxp) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        rxp.setRopUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        rxp.setRopFechaInsert(date);
        
        auditoriaActualizar(rxp);
        
    }
    
    private void auditoriaActualizar(TbRolesXPermiso rxp) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        rxp.setRopUserUpdate(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        rxp.setRopFechaUpdate(date);
        
    }

    

}
