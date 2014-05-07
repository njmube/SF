package manageBeans;
 
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;
 
@ManagedBean(name = "detalleFacturaBean")
@SessionScoped
public class DetalleFacturaBean implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private String Cliente;
    private Date fechaEmision;
    private String tipoFactura;
    
    private String item; // descripcion
    private int cantidad;
    private Double precioUnitario;
    private boolean exentas;
    private boolean cincoPorciento;
    private boolean diezPorciento;
    DetalleFactura order;
 
    private static final ArrayList<DetalleFactura> orderList = new ArrayList<DetalleFactura>();
 
    public ArrayList<DetalleFactura> getOrderList() {
        return orderList;
    }
 
    public String addAction() {
        Double tCinco = calcularTotal(exentas);
        Double tDiez = calcularTotal(cincoPorciento);
        Double tExentas = calcularTotal(diezPorciento);        
        DetalleFactura orderitem = new DetalleFactura( this.cantidad , this.item, this.precioUnitario, tExentas, tCinco, tDiez);
        orderList.add(orderitem);
        inicializar();        
        return null;
    }
    
    public void onEdit(RowEditEvent event) {  
        Double tCinco = calcularTotal(exentas);
        Double tDiez = calcularTotal(cincoPorciento);
        Double tExentas = calcularTotal(diezPorciento); 
        FacesMessage msg = new FacesMessage("Detalle de Factura Editado",((DetalleFactura) event.getObject()).getItem());  
        FacesContext.getCurrentInstance().addMessage(null, msg);  
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Detalle de Factura Cancelado");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        orderList.remove((DetalleFactura) event.getObject());
    }
    
    private Double calcularTotal(boolean isTotal) {
        Double total = 0.0;
        if(isTotal){
            total = cantidad * precioUnitario * 1.0;
        }
        return total;
    }
    
    private void inicializar() {
        cantidad = 0;
        item = "";
        precioUnitario = 0.0;
        exentas = false;
        cincoPorciento = false;
        diezPorciento = false;              
        
    }

    // Get & Set
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public boolean isExentas() {
        return exentas;
    }

    public void setExentas(boolean exentas) {
        this.exentas = exentas;
    }

    public boolean isCincoPorciento() {
        return cincoPorciento;
    }

    public void setCincoPorciento(boolean cincoPorciento) {
        this.cincoPorciento = cincoPorciento;
    }

    public boolean isDiezPorciento() {
        return diezPorciento;
    }

    public void setDiezPorciento(boolean diezPorciento) {
        this.diezPorciento = diezPorciento;
    }

    
    public DetalleFactura getOrder() {
        return order;
    }

    public void setOrder(DetalleFactura order) {
        this.order = order;
    }

    public String getCliente() {
        return Cliente;
    }

    public void setCliente(String Cliente) {
        this.Cliente = Cliente;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public String getTipoFactura() {
        return tipoFactura;
    }

    public void setTipoFactura(String tipoFactura) {
        this.tipoFactura = tipoFactura;
    }



}