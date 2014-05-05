package helper;

import hibernate.HibernateUtil;
import entity.TbTimbrado;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class TimbradoFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbTimbrado timbrado) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(timbrado != null){
                auditoriaGuardar(timbrado);
                id = (Integer) sesion.save(timbrado); 
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

    public boolean update(TbTimbrado timbrado) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(timbrado);
            sesion.update(timbrado); 
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
    
  
    public boolean delete(TbTimbrado timbrado) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(timbrado != null){
                sesion.delete(timbrado); 
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

    public TbTimbrado search(Integer idtimbrado) throws HibernateException 
    { 
        TbTimbrado timbrado = null;  
        try 
        { 
            iniciarOperacion(); 
            timbrado = (TbTimbrado) sesion.get(TbTimbrado.class, idtimbrado); 
        } finally 
        { 
            sesion.close(); 
        }  

        return timbrado; 
    } 
    
    public TbTimbrado searchTimbrado(String timbrado) throws HibernateException 
    { 
        TbTimbrado usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbTimbrado where timNro = '"+ timbrado + "'";
            List<TbTimbrado> lista = sesion.createQuery(cadena).list();
            for (TbTimbrado p : lista) {  
                if (p.getTimNro().equals(timbrado)) {  
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
    
    public List<TbTimbrado> listAll() throws HibernateException 
    { 
        List<TbTimbrado> listaTimbrados = null;  

        try 
        { 
            iniciarOperacion(); 
            listaTimbrados = sesion.createQuery("from TbTimbrado").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaTimbrados; 
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

    private void auditoriaGuardar(TbTimbrado usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setTimUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTimFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbTimbrado usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setTimUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setTimFechaUpdate(date);
        
    }
    



}

