
package helper;

import entity.TbPermiso;
import entity.TbRoles;
import entity.TbRolesXUsuario;
import hibernate.HibernateUtil;
import entity.TbUsuario;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import manageBeans.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;


public class UsuarioFH
{  
    private Session sesion; 
    private Transaction tx;  

    public boolean create(TbUsuario usuario) throws HibernateException 
    { 
        boolean rta = false;
        Integer id = 0;          
        try 
        { 
            iniciarOperacion(); 
            if(usuario != null){
                auditoriaGuardar(usuario);
                setearPassword(usuario);
                id = (Integer) sesion.save(usuario); 
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

    public boolean update(TbUsuario usuario) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(usuario);
            setearPassword(usuario);
            sesion.update(usuario); 
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
    
    public boolean delete(TbUsuario usuario) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();
            if(usuario != null){
                sesion.delete(usuario); 
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

    public TbUsuario search(Integer idUsuario) throws HibernateException 
    { 
        TbUsuario usuario = null;  
        try 
        { 
            iniciarOperacion(); 
            usuario = (TbUsuario) sesion.get(TbUsuario.class, idUsuario); 
        } finally 
        { 
            sesion.close(); 
        }  

        return usuario; 
    } 
    
    public TbUsuario searchUsuario(String usuario) throws HibernateException 
    { 
        TbUsuario usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbUsuario where usUsuario = '"+ usuario + "'";
            List<TbUsuario> lista = sesion.createQuery(cadena).list();
            for (TbUsuario p : lista) {  
                if (p.getUsUsuario().equals(usuario)) {  
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
    
    public TbUsuario searchCi(Integer ci) throws HibernateException 
    { 
        TbUsuario usr = null; 
        try 
        { 
            iniciarOperacion();  
            String cadena = "from TbUsuario where usCi = "+ ci ;
            List<TbUsuario> lista = sesion.createQuery(cadena).list();
            for (TbUsuario p : lista) {  
                if (p.getUsCi() == ci) {  
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
    
    public List<TbUsuario> listAll() throws HibernateException 
    { 
        List<TbUsuario> listaUsuarios = null;  

        try 
        { 
            iniciarOperacion(); 
            listaUsuarios = sesion.createQuery("from TbUsuario").list(); 
        } finally 
        { 
            sesion.close(); 
        }  

        return listaUsuarios; 
    }  

    public boolean pass(TbUsuario usuario) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion();            
            auditoriaActualizar(usuario);
            setearPassword(usuario);
            sesion.update(usuario); 
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
    
    public boolean existe(String usr, String pw) throws HibernateException 
    { 
        boolean rta = false;
        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbUsuario where usPassword = md5('" + pw +"')";
            List<TbUsuario> lista = sesion.createQuery(cadena).list();             
            for(TbUsuario u: lista){
                if (u.getUsUsuario().equals(usr))
                    rta = true;
            }
           
            
        }catch (HibernateException he) 
        { 
            manejarExcepcion(he); 
            throw he; 
        } finally 
        { 
            sesion.close(); 
        }  

        return rta; 
    } 
    
    public List<TbRoles> getRoles(Integer idUsuario) throws HibernateException 
    { 
        List<TbRolesXUsuario> lista = null;  
        List<TbRoles> listaP = null; 
        try 
        { 
            iniciarOperacion(); 
            String cadena = "from TbRolesXUsuario where tbUsuario = '"+ idUsuario + "'";
            lista = sesion.createQuery(cadena).list(); 
            for (TbRolesXUsuario p : lista) {  
                listaP.add(p.getTbRoles()); 
            }  
        } finally 
        { 
            sesion.close(); 
        }  

        return listaP; 
    }
    
    public List<TbPermiso> getPermisos(Integer idUsuario) throws HibernateException 
    { 
        List<TbPermiso> listaP = null; 
        try 
        { 
            iniciarOperacion();
            List<TbRoles> listaR = getRoles(idUsuario);
            RolFH helperR = new RolFH();
            for(TbRoles r : listaR){
                List<TbPermiso> lista = helperR.getPermisos(r.getRolCod());
                for(TbPermiso p: lista)
                    listaP.add(p);
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

    private void auditoriaGuardar(TbUsuario usr) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setUsUserInsert(usuario);
        
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setUsFechaInsert(date);        
        auditoriaActualizar(usr);
        
    }
    
    private void auditoriaActualizar(TbUsuario usr) 
    {
        HttpSession session = Util.getSession();
        String usuario = (String) session.getAttribute("username");
        usr.setUsUserUpdate(usuario);
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        usr.setUsFechaUpdate(date);
        
    }

    
    private String md5(String message)
    {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            //converting byte array to Hexadecimal String
            StringBuilder sb = new StringBuilder(2*hash.length); 
            for(byte b : hash){
                sb.append(String.format("%02x", b&0xff));
            }
            digest = sb.toString();            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioFH.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(UsuarioFH.class.getName()).log(Level.SEVERE, null, ex);
        }
        return digest;
    }

    private void setearPassword(TbUsuario usuario) 
    {
        String pw = usuario.getUsPassword();
        usuario.setUsPassword(md5(pw));
    }

}
