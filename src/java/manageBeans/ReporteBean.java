package manageBeans;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


@ManagedBean (name = "reporteBean")
@ViewScoped
public class ReporteBean implements Serializable{
    // formato de informe
    private String formato; 
    // archivo jrxml
    private String file;
    // parametros
    private String tipoServicio;
    private String tipoContrato;
    private String cliente;
    private String contrato;
    private String desde;
    private String hasta;   
    private String parametro;

    public ReporteBean() {            
            
    }
       
    public void descargarPDF() {         
        formato = "pdf";
        setearParametro();
        String url = "/Reporte" + parametro;
        FacesContext context = FacesContext.getCurrentInstance();        
        try {
        context.getExternalContext().dispatch(url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }
    
   public void descargarXLS() {
        formato = "xls";
        setearParametro();
        String url = "/Reporte" + parametro;
        FacesContext context = FacesContext.getCurrentInstance();        
        try {
        context.getExternalContext().dispatch(url);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            context.responseComplete();
        }
    }

    private void setearParametro() {
        if (file != null && formato != null){
            if(file.equals("cliente")) // reporte cliente
               parametro = "?file=" + file + "&formato=" + formato;   
            if(file.equals("servicioTipo") && tipoServicio != null) // reporte de contrato por tipo
               parametro = "?file=" + file + "&formato=" + formato + "&tipoServicio=" + tipoServicio;
            if(file.equals("contratoTipo") && tipoContrato != null) // reporte de contrato por tipo
               parametro = "?file=" + file + "&formato=" + formato + "&tipoContrato=" + tipoContrato;
            if(file.equals("contratoCliente") && cliente != null) // reporte de contrato por cliente
               parametro = "?file=" + file + "&formato=" + formato + "&cliente=" + cliente;
            if(file.equals("gasto") && desde != null && hasta != null ) // reporte de gasto
               parametro = "?file=" + file + "&formato=" + formato + "&desde=" + desde + "&hasta=" + hasta;
        }
        System.err.println("Parametro: " + parametro);
            
    }

    // get & set
    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getContrato() {
        return contrato;
    }

    public void setContrato(String contrato) {
        this.contrato = contrato;
    }

    public String getDesde() {
        return desde;
    }

    public void setDesde(String desde) {
        this.desde = desde;
    }

    public String getHasta() {
        return hasta;
    }

    public void setHasta(String hasta) {
        this.hasta = hasta;
    }

    public String getParametro() {
        return parametro;
    }

    public void setParametro(String parametro) {
        this.parametro = parametro;
    }
    
       
}