package helper;

import hibernate.HibernateUtil;
import entity.TbServicio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ServicioFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbServicio servicio) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(servicio != null){
                auditoriaGuardar(servicio);
                id = (Integer) sesion.save(servicio); 
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

    public boolean update(TbServicio servicio) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(servicio);
            sesion.update(servicio); 
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
    
  
    public boolean delete(TbServicio servicio) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(servicio != null){
                sesion.delete(servicio); 
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

    public TbServicio search(Integer idServicio) throws HibernateException 
    { 
        TbServicio servicio = null;  
        try 
        { 
            iniciarOperacion(); 
            servicio = (TbServicio) sesion.get(TbServicio.class, idServicio); 
        } finally 
        { 
            sesion.close(); 
        }  

        return servicio; 
    } 
    
    public TbServicio searchServicio(String servicio) throws HibernateException 
    { 
        TbServicio usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbServicio where serDescripcion = '"+ servicio + "'";
            List<TbServicio> lista = sesion.createQuery(cadena).list();
            for (TbServicio p : lista) {  
                if (p.getSerDescripcion().equals(servicio)) {  
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
    
    public List<TbServicio> listAll() throws HibernateException 
    { 
        List<TbServicio> listaServicios = null;  

        try 
        { 
            iniciarOperacion();             
            listaServicios = sesion.createQuery("from TbServicio").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaServicios; 
    } 
    
    public List<TbServicio> listXTipoServicio(Integer idTipoServicio) throws HibernateException 
    { 
        List<TbServicio> listaServicio = null;  
        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbServicio where tbTipoServicio = '"+ idTipoServicio + "'";
            listaServicio = sesion.createQuery(cadena).list(); 
            if (listaServicio != null)
                return listaServicio;
        } finally 
        { 
            sesion.close(); 
        } 
        return listaServicio; 
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

    private void auditoriaGuardar(TbServicio usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setSerUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setSerFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbServicio usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setSerUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setSerFechaUpdate(date);
        
    }
    



}
