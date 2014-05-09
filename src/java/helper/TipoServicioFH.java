package helper;

import hibernate.HibernateUtil;
import entity.TbTipoServicio;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TipoServicioFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbTipoServicio tipoServicio) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(tipoServicio != null){
                auditoriaGuardar(tipoServicio);
                id = (Integer) sesion.save(tipoServicio); 
                if(id != 0){
                    tx.commit(); 
                    rta = true;
                }
            }
            if(id != 0)
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

    public boolean update(TbTipoServicio tipoServicio) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(tipoServicio);
            sesion.update(tipoServicio); 
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
    
  
    public boolean delete(TbTipoServicio tipoServicio) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(tipoServicio != null){
                sesion.delete(tipoServicio); 
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

    public TbTipoServicio search(Integer idTipoServicio) throws HibernateException 
    { 
        TbTipoServicio tipoServicio = null;  
        try 
        { 
            iniciarOperacion(); 
            tipoServicio = (TbTipoServicio) sesion.get(TbTipoServicio.class, idTipoServicio); 
        } finally 
        { 
            sesion.close(); 
        }  

        return tipoServicio; 
    } 
    
    public TbTipoServicio searchTipoServicio(String tipoServicio) throws HibernateException 
    { 
        TbTipoServicio tipo = null; 
        try 
        { 
                iniciarOperacion();  
                String cadena = "from TbTipoServicio where tsDescripcion = '"+ tipoServicio + "'";
                List<TbTipoServicio> lista = sesion.createQuery(cadena).list();
                for (TbTipoServicio t : lista) {  
                    if (t.getTsDescripcion().equals(tipoServicio)) {  
                        return t;  
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

        return tipo; 
    }     
       
    
    public List<TbTipoServicio> listAll() throws HibernateException 
    { 
        List<TbTipoServicio> listaTipoServicios = null;  

        try 
        { 
            iniciarOperacion(); 
            listaTipoServicios = sesion.createQuery("from TbTipoServicio").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTipoServicios; 
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

    private void auditoriaGuardar(TbTipoServicio usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setTsUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTsFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbTipoServicio usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setTsUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTsFechaUpdate(date);
        
    }
    



}