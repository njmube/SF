
package helper;

import hibernate.HibernateUtil;
import entity.TbTipoContrato;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TipoContratoFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbTipoContrato tipoContrato) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(tipoContrato != null){
                auditoriaGuardar(tipoContrato);
                id = (Integer) sesion.save(tipoContrato); 
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

    public boolean update(TbTipoContrato tipoContrato) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(tipoContrato);
            sesion.update(tipoContrato); 
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
    
    public boolean delete(TbTipoContrato tipoContrato) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(tipoContrato != null){
                sesion.delete(tipoContrato); 
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

    public TbTipoContrato search(Integer idTipoContrato) throws HibernateException 
    { 
        TbTipoContrato tipoContrato = null;  
        try 
        { 
            iniciarOperacion(); 
            tipoContrato = (TbTipoContrato) sesion.get(TbTipoContrato.class, idTipoContrato); 
        } finally 
        { 
            sesion.close(); 
        }  

        return tipoContrato; 
    } 
    
    public TbTipoContrato searchDescripcion(String descripcion) throws HibernateException 
    { 
        TbTipoContrato usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbTipoContrato where tcDescripcion = '"+ descripcion + "'";
            List<TbTipoContrato> lista = sesion.createQuery(cadena).list();
            for (TbTipoContrato p : lista) {  
                if (p.getTcDescripcion().equals(descripcion)) {  
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
    
    public List<TbTipoContrato> listAll() throws HibernateException 
    { 
        List<TbTipoContrato> listaTipoContratos = null;  

        try 
        { 
            iniciarOperacion(); 
            listaTipoContratos = sesion.createQuery("from TbTipoContrato").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTipoContratos; 
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

    private void auditoriaGuardar(TbTipoContrato usr) 
    {
        HttpSession session = Util.getSession();
        String tipoContrato = (String) session.getAttribute("username");
        usr.setTcUserInsert(tipoContrato);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTcFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbTipoContrato usr) 
    {
        HttpSession session = Util.getSession();
        String tipoContrato = (String) session.getAttribute("username");
        usr.setTcUserUpdate(tipoContrato);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTcFechaUpdate(date);
        
    }
}
