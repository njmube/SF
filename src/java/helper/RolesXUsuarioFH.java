
package helper;

import entity.TbRoles;
import entity.TbRolesXUsuario;
import entity.TbUsuario;
import hibernate.HibernateUtil;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpSession;
import helper.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class RolesXUsuarioFH
{  
    private Session sesion; 
    private Transaction tx;  

    public Integer create(TbRolesXUsuario rxu) throws HibernateException 
    { 
        Integer id = 0;  

        try 
        { 
            iniciarOperacion(); 
            auditoriaGuardar(rxu);
            id = (Integer) sesion.save(rxu); 
            if(id != 0){
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

        return id; 
    }  

    public void update(TbRolesXUsuario rxu) throws HibernateException 
    { 
        try 
        { 
            iniciarOperacion(); 
            auditoriaActualizar(rxu);
            sesion.update(rxu); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  
    
    public boolean config(TbUsuario usuario , List<String> roles) throws HibernateException 
    { 
        boolean rta = false;
        RolFH helperR = new RolFH();
        TbRoles rol = null;
        try 
        { 
            iniciarOperacion(); 
            System.err.println("usuario: "+usuario.getUsUsuario());
            for (String p : roles) {
                rol = helperR.searchRol(p);
                System.err.println("rol: "+ rol.getRolNombre());
                TbRolesXUsuario rxu = new TbRolesXUsuario();
                rxu.setTbUsuario(usuario);
                rxu.setTbRoles(rol);
                auditoriaGuardar(rxu);
                sesion.save(rxu); 
            }
            rta = true;
            tx.commit(); 
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
    
    public void delete(TbRolesXUsuario rxu) throws HibernateException 
    { 
        try 
        { 
            iniciarOperacion(); 
            sesion.delete(rxu); 
            tx.commit(); 
        } catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        } 
    }  

    public TbRolesXUsuario search(Integer idRolesXPantallas) throws HibernateException 
    { 
        TbRolesXUsuario rolesXPantallas = null;  
        try 
        { 
            iniciarOperacion(); 
            rolesXPantallas = (TbRolesXUsuario) sesion.get(TbRolesXUsuario.class, idRolesXPantallas); 
        } finally 
        { 
            sesion.close(); 
        }  

        return rolesXPantallas; 
    }  
    
    public List<TbRolesXUsuario> listAll() throws HibernateException 
    { 
        List<TbRolesXUsuario> listaRolesXPantallass = null;  

        try 
        { 
            iniciarOperacion(); 
            listaRolesXPantallass = sesion.createQuery("from TbRolesXUsuario").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaRolesXPantallass; 
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

    private void auditoriaGuardar(TbRolesXUsuario rxu) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        rxu.setRouUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        rxu.setRouFechaInsert(date);
        
        auditoriaActualizar(rxu);
        
    }
    
    private void auditoriaActualizar(TbRolesXUsuario rxu) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        rxu.setRouUserUpdate(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        rxu.setRouFechaUpdate(date);
        
    }

    

}
