
package helper;

import entity.TbPermiso;
import hibernate.HibernateUtil;
import entity.TbRoles;
import entity.TbRolesXPermiso;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import helper.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RolFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbRoles rol) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(rol != null){
                auditoriaGuardar(rol);
                id = (Integer) sesion.save(rol); 
                if(id != 0){
                    rta = true;
                    tx.commit();  
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
    
    public boolean update(TbRoles rol) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(rol);
            sesion.update(rol); 
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
    
    public boolean delete(TbRoles rol) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(rol != null){
                sesion.delete(rol); 
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
    
    public TbRoles search(Integer idRol) throws HibernateException 
    { 
        TbRoles rol = null;  
        try 
        { 
            iniciarOperacion(); 
            rol = (TbRoles) sesion.get(TbRoles.class, idRol); 
        } finally 
        { 
            sesion.close(); 
        }  

        return rol; 
    } 
    
    public TbRoles searchRol(String rol) throws HibernateException 
    { 
        TbRoles usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbRoles where rolNombre = '"+ rol + "'";
            List<TbRoles> lista = sesion.createQuery(cadena).list();
            for (TbRoles p : lista) {  
                if (p.getRolNombre().equals(rol)) {  
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
    
    public List<TbRoles> listAll() throws HibernateException 
    { 
        List<TbRoles> listaRols = null;  

        try 
        { 
            iniciarOperacion(); 
            listaRols = sesion.createQuery("from TbRoles").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaRols; 
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
    
    private void auditoriaGuardar(TbRoles usr)
    {
        HttpSession session = Util.getSession();
        String permiso = (String) session.getAttribute("username");
        usr.setRolUserInsert(permiso);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setRolFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbRoles usr)
    {
        HttpSession session = Util.getSession();
        String permiso = (String) session.getAttribute("username");
        usr.setRolUserUpdate(permiso);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setRolFechaUpdate(date);
        
    }

}
