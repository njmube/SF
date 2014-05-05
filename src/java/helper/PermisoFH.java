
package helper;

import hibernate.HibernateUtil;
import entity.TbPermiso;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import helper.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class PermisoFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbPermiso permiso) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(permiso != null){
                auditoriaGuardar(permiso);
                id = (Integer) sesion.save(permiso); 
                if(id != 0){
                    tx.commit(); 
                    rta = true;                
                }
            }

            
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
    
    public boolean update(TbPermiso permiso) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(permiso);
            sesion.update(permiso); 
            tx.commit();
            rta = true;            
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
    
    public boolean delete(TbPermiso permiso) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(permiso != null){
                sesion.delete(permiso); 
                tx.commit();
                rta = true; 
            }
 
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
    
    public TbPermiso search(Integer idPermiso) throws HibernateException 
    { 
        TbPermiso permiso = null;  
        try 
        { 
            iniciarOperacion(); 
            permiso = (TbPermiso) sesion.get(TbPermiso.class, idPermiso); 
        } finally 
        { 
            sesion.close(); 
        }  

        return permiso; 
    } 
    
    public TbPermiso searchDescripcion(String descripcion) throws HibernateException 
    { 
        TbPermiso usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbPermiso where perDescripcion = '"+ descripcion + "'";
            List<TbPermiso> lista = sesion.createQuery(cadena).list();
            for (TbPermiso p : lista) {  
                if (p.getPerDescripcion().equals(descripcion)) {  
                    return p;  
                }  
            }  
                        
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } 
        finally 
        { 
             sesion.close(); 
        }  

        return usr; 
    }
    
    public List<TbPermiso> listAll() throws HibernateException 
    { 
        List<TbPermiso> listaPermisos = null;  

        try 
        { 
            iniciarOperacion(); 
            listaPermisos = sesion.createQuery("from TbPermiso").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaPermisos; 
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
    
    private void auditoriaGuardar(TbPermiso usr) {
        HttpSession session = Util.getSession();
        String permiso = (String) session.getAttribute("username");
        usr.setPerUserInsert(permiso);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setPerFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbPermiso usr) {
        HttpSession session = Util.getSession();
        String permiso = (String) session.getAttribute("username");
        usr.setPerUserUpdate(permiso);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setPerFechaUpdate(date);
        
    }

}
