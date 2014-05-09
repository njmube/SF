
package helper;

import hibernate.HibernateUtil;
import entity.TbContrato;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ContratoFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbContrato contrato) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(contrato != null){
                auditoriaGuardar(contrato);
                id = (Integer) sesion.save(contrato); 
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

    public boolean update(TbContrato contrato) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(contrato);
            sesion.update(contrato); 
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
    
    public boolean delete(TbContrato contrato) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(contrato != null){
                sesion.delete(contrato); 
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

    public boolean generarFacturasXContrato(TbContrato contrato) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(contrato != null){
                // 1. generar facturas
                
                // 2. guardar facturas
               
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
    

    public TbContrato search(Integer idContrato) throws HibernateException 
    { 
        TbContrato contrato = null;  
        try 
        { 
            iniciarOperacion(); 
            contrato = (TbContrato) sesion.get(TbContrato.class, idContrato); 
        } finally 
        { 
            sesion.close(); 
        }  

        return contrato; 
    } 
    
    public TbContrato searchNro(int nro) throws HibernateException 
    { 
        TbContrato con = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbContrato where conNro = '"+ nro + "'";
            List<TbContrato> lista = sesion.createQuery(cadena).list();
            for (TbContrato p : lista) {  
                if (p.getConNro() == nro) {  
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

        return con; 
    }
    
    public List<TbContrato> listAll() throws HibernateException 
    { 
        List<TbContrato> listaUsuarios = null;  

        try 
        { 
            iniciarOperacion(); 
            listaUsuarios = sesion.createQuery("from TbContrato").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaUsuarios; 
    }  
    
    public List<TbContrato> listXCliente(Integer idCliente) throws HibernateException 
    { 
        List<TbContrato> listaUsuarios = null;  

        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbContrato where tbCliente = '"+ idCliente + "'";
            listaUsuarios = sesion.createQuery(cadena).list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaUsuarios; 
    }
    
    public List<TbContrato> listXTipoContrato(Integer idTipoContrato) throws HibernateException 
    { 
        List<TbContrato> listaUsuarios = null;  

        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbContrato where tbTipoContrato = '"+ idTipoContrato + "'";
            listaUsuarios = sesion.createQuery(cadena).list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaUsuarios; 
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

    private void auditoriaGuardar(TbContrato usr) 
    {
        HttpSession session = Util.getSession();
        String contrato = (String) session.getAttribute("username");
        usr.setConUserInsert(contrato);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setConFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbContrato usr) 
    {
        HttpSession session = Util.getSession();
        String contrato = (String) session.getAttribute("username");
        usr.setConUserUpdate(contrato);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setConFechaUpdate(date);
        
    }
}
