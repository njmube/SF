package helper;

import hibernate.HibernateUtil;
import entity.TbCliente;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class ClienteFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbCliente cliente) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(cliente != null){
                auditoriaGuardar(cliente);
                id = (Integer) sesion.save(cliente); 
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

    public boolean update(TbCliente cliente) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(cliente);
            sesion.update(cliente); 
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
    
  
    public boolean delete(TbCliente cliente) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(cliente != null){
                sesion.delete(cliente); 
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

    public TbCliente search(Integer idCliente) throws HibernateException 
    { 
        TbCliente cliente = null;  
        try 
        { 
            iniciarOperacion(); 
            cliente = (TbCliente) sesion.get(TbCliente.class, idCliente); 
        } finally 
        { 
            sesion.close(); 
        }  

        return cliente; 
    } 
    
    public TbCliente searchCliente(String cliente) throws HibernateException 
    { 
        TbCliente usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbCliente where cliNombre = '"+ cliente + "'";
            List<TbCliente> lista = sesion.createQuery(cadena).list();
            for (TbCliente p : lista) {  
                if (p.getCliNombre().equals(cliente)) {  
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
    
    public TbCliente searchNyA(String nya) throws HibernateException 
    { 
        TbCliente usr = null; 
        try 
        { 
            iniciarOperacion();  
            String[] nyaArray = nya.split(",");
            String nombre = nyaArray[0];
            String apellido = nyaArray[1];
            String cadena = "from TbCliente where cliNombre = '"+ nombre + "' and cliApellido = '"+ apellido + "'";
            List<TbCliente> lista = sesion.createQuery(cadena).list();
            for (TbCliente p : lista) {  
                if (p.getCliNombre().equals(nombre) && p.getCliApellido().equals(apellido)) {  
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
    public List<TbCliente> listAll() throws HibernateException 
    { 
        List<TbCliente> listaClientes = null;  

        try 
        { 
            iniciarOperacion(); 
            listaClientes = sesion.createQuery("from TbCliente").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaClientes; 
    } 
    
     public boolean comprobarCedula(Integer ci) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbCliente where cliCi = '" + ci +"'";
            List<TbCliente> lista = sesion.createQuery(cadena).list();             
           for(TbCliente c: lista){
                if (c.getCliCi().equals(ci))
                   rta = true;
            }
           
            
        }catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        }   finally 
        { 
           sesion.close(); 
        }  

        return rta; 
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

    private void auditoriaGuardar(TbCliente usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setCliUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setCliFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbCliente usr) {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setCliUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setCliFechaUpdate(date);
        
    }
    



}
