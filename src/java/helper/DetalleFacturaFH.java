
package helper;

import hibernate.HibernateUtil;
import entity.TbDetalleFactura;
import entity.TbFactura;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class DetalleFacturaFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbDetalleFactura detalleFactura) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(detalleFactura != null){
                auditoriaGuardar(detalleFactura);
                id = (Integer) sesion.save(detalleFactura); 
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
    
    public boolean createAll(List<TbDetalleFactura> lista, TbFactura factura) throws HibernateException 
    { 
        boolean rta = false, band = true;
        Integer id = 0; 
        
        try 
        { 
            iniciarOperacion();
            for(TbDetalleFactura detalleFactura: lista){
                if(detalleFactura != null){ 
                    detalleFactura.setTbFactura(factura);
                    auditoriaGuardar(detalleFactura);
                    id = (Integer) sesion.save(detalleFactura); 
                    if(id == 0){
                        band = false;                        
                    }
                }
            }
            if (band){
                rta = true;
                tx.commit(); 
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

    public boolean update(TbDetalleFactura detalleFactura) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(detalleFactura);
            sesion.update(detalleFactura); 
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
    
    public boolean updateAll(List<TbDetalleFactura> lista) throws HibernateException 
    { 
        boolean rta = false, band = false;
        try 
        { 
            iniciarOperacion();  
            for(TbDetalleFactura detalleFactura: lista){
                if(detalleFactura != null){
                    auditoriaActualizar(detalleFactura);
                    sesion.update(detalleFactura);
                    band = true;
                }
            }
            if (band){
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
    
    public boolean delete(TbDetalleFactura detalleFactura) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(detalleFactura != null){
                sesion.delete(detalleFactura); 
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

     public boolean deleteAll(List<TbDetalleFactura> lista) throws HibernateException 
    { 
        boolean rta = false, band = false;
        try 
        { 
            iniciarOperacion();  
            for(TbDetalleFactura detalleFactura: lista){
                if(detalleFactura != null){
                    sesion.delete(detalleFactura); 
                    band = true;
                }
            }
            if (band){
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
    
    public TbDetalleFactura search(Integer idDetalleFactura) throws HibernateException 
    { 
        TbDetalleFactura detalleFactura = null;  
        try 
        { 
            iniciarOperacion(); 
            detalleFactura = (TbDetalleFactura) sesion.get(TbDetalleFactura.class, idDetalleFactura); 
        } finally 
        { 
            sesion.close(); 
        }  

        return detalleFactura; 
    } 
    
    public List<TbDetalleFactura> listAll() throws HibernateException 
    { 
        List<TbDetalleFactura> listaDetalleFacturas = null;  

        try 
        { 
            iniciarOperacion(); 
            listaDetalleFacturas = sesion.createQuery("from TbDetalleFactura").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaDetalleFacturas; 
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

    private void auditoriaGuardar(TbDetalleFactura usr) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setDfUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setDfFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbDetalleFactura usr) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setDfUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setDfFechaUpdate(date);
        
    }

   

}
