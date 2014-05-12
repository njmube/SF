package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;


public class Reporte extends HttpServlet
{
    private String file;
    private String dirJrxml;
    String jdbc = "jdbc:postgresql://localhost:5432/contratospratt";
    String bd = "contratospratt";
    String pw = "contratospratt";
    String rootJrxml = "/home/stfy-warrior/Escritorio/jrxml/"; 
    Map parametros;
    String arch, desde, hasta, tipoServicio, cliente, contrato, tipoContrato, formato;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        Calendar cal = Calendar.getInstance();
        Date date = cal.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
        file = request.getParameter("file") + "-" + sdf.format(date);
        dirJrxml = rootJrxml + request.getParameter("file")+ ".jrxml";        
        
        Connection conexion = null;
        ServletOutputStream out = response.getOutputStream();        
        JasperReport jasperReport;
        JasperPrint jasperPrint;
        JasperDesign jasperDesign;
           
        try {
            System.err.println("holaaa"); 
            arch = request.getParameter("file");
            formato = request.getParameter("formato");
            desde = request.getParameter("desde");
            hasta = request.getParameter("hasta");
            tipoServicio = request.getParameter("tipoServicio");
            cliente = request.getParameter("cliente");
            contrato = request.getParameter("contrato");  
            tipoContrato = request.getParameter("tipoContrato"); 
            setearParametros();            
            System.err.println("file:" + arch);
            System.err.println("formato:" + formato);
            
            Class.forName("org.postgresql.Driver");
            conexion = DriverManager.getConnection(jdbc, bd, pw );
            jasperDesign = JRXmlLoader.load(dirJrxml);
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, conexion);
                         
            response.setHeader("Cache-Control", "no-cache");
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Expires", 0);
            
            //for creating report in xls format
            if(formato.equals("xls")){
                response.setHeader("Content-Disposition", "attachment; filename="+file+".xls");
                response.setContentType("application/xls");
                JRXlsxExporter exporter = new JRXlsxExporter();
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
                exporter.exportReport(); 
            }
            //for creating report in pdf format
            if(formato.equals("pdf")){
                response.setHeader("Content-Disposition", "attachment; filename="+file+".pdf");
                response.setContentType("application/pdf");
                JasperExportManager.exportReportToPdfStream(jasperPrint, out);
            }
            out.flush();
            out.close();
            if(conexion != null)
                conexion.close();
        }
        catch(SQLException sqle)
        {
            System.err.println(sqle.getMessage());
        }
        catch (ClassNotFoundException e)
        {
            System.err.println("No such class found!");
        }
        catch (JRException e)
        {
            // display stack trace in the browser
            StringWriter stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            e.printStackTrace(printWriter);
            response.setContentType("text/plain");
            response.getOutputStream().print(stringWriter.toString());
        }
        
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

    public Date deStringToDate(String fecha){ 
        SimpleDateFormat formatoDelTexto = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy",Locale.ENGLISH);
        Date fechaEnviar=null; 
        try { 
            fechaEnviar = (Date) formatoDelTexto.parse(fecha); 
            return fechaEnviar; 
        } 
        catch (ParseException ex) { 
            ex.printStackTrace(); 
            return null; 
        } 
   }

    private void setearParametros() {
        // setear parametros
        parametros = new HashMap();                  
        if( tipoServicio != null)  // reporte de servicio por tipo
           parametros.put("tipoServicio", tipoServicio);  
        if( tipoContrato != null) // reporte de contrato por tipo
           parametros.put("tipoContrato", tipoContrato);   
        if( cliente != null) // reporte de contrato por cliente
           parametros.put("cliente", cliente); 
        if( desde != null && hasta != null ){ // reporte de gasto
           parametros.put("desde", deStringToDate(desde));                
           parametros.put("hasta", deStringToDate(hasta));
        }        
    }

 }

