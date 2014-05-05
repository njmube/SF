package helper;

import hibernate.HibernateUtil;
import entity.TbFactura;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class FacturaFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbFactura factura) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(factura != null){
                auditoriaGuardar(factura);
                id = (Integer) sesion.save(factura); 
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

    public boolean update(TbFactura factura) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(factura);
            sesion.update(factura); 
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
    
  
    public boolean delete(TbFactura factura) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(factura != null){
                sesion.delete(factura); 
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

    public TbFactura search(Integer idFactura) throws HibernateException 
    { 
        TbFactura factura = null;  
        try 
        { 
            iniciarOperacion(); 
            factura = (TbFactura) sesion.get(TbFactura.class, idFactura); 
        } finally 
        { 
            sesion.close(); 
        }  

        return factura; 
    } 
    
    public TbFactura searchSecuencia(String secuencia) throws HibernateException 
    { 
        TbFactura usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbFactura where facSecuencia = '"+ secuencia + "'";
            List<TbFactura> lista = sesion.createQuery(cadena).list();
            for (TbFactura p : lista) {  
                if (p.getFacSecuencia().equals(secuencia)) {  
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
    
    public TbFactura searchSecuenciaYTimbrado(String syt) throws HibernateException 
    { 
        TbFactura usr = null; 
        try 
        { 
            iniciarOperacion();  
            String[] nyaArray = syt.split(",");
            String secuencia = nyaArray[0];
            String timbrado = nyaArray[1];
            String cadena = "from TbFactura where facSecuencia = '"+ secuencia + "' and facTimbrado = '"+ timbrado + "'";
            List<TbFactura> lista = sesion.createQuery(cadena).list();
            for (TbFactura p : lista) {  
                if (p.getFacSecuencia().equals(secuencia) && p.getFacTimbrado().equals(timbrado)) {  
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
    public List<TbFactura> listAll() throws HibernateException 
    { 
        List<TbFactura> listaFacturas = null;  

        try 
        { 
            iniciarOperacion(); 
            listaFacturas = sesion.createQuery("from TbFactura").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaFacturas; 
    } 
    
    public List<TbFactura> listContado() throws HibernateException 
    { 
        List<TbFactura> listaFacturas = null;  
        String tipo = "FACTURA CONTADO";
        try 
        { 
            iniciarOperacion(); 
            listaFacturas = sesion.createQuery("from TbFactura where facTipo = '"+ tipo + "'").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaFacturas; 
    } 
    
    public List<TbFactura> listCredito() throws HibernateException 
    { 
        List<TbFactura> listaFacturas = null;  
        String tipo = "FACTURA CRÉDITO";
        try 
        { 
            iniciarOperacion(); 
            listaFacturas = sesion.createQuery("from TbFactura where facTipo = '"+ tipo + "'").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaFacturas; 
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

    private void auditoriaGuardar(TbFactura usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setFacUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setFacFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbFactura usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setFacUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setFacFechaUpdate(date);
        
    }
    



}
