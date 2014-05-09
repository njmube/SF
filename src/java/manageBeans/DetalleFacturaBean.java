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
    private Double montoCinco;
    private Double montoDiez ;
    private Double montoExentas; 
    
    // DETALLE DE FACTURA
    private String item; // descripcion
    private int cantidad;
    private Double precioUnitario;
    private String total;  // 1: Exentas 2: IVA 5% 3: IVA 10%
    private Double tCinco;
    private Double tDiez ;
    private Double tExentas;     
    DetalleFactura order;
 
    private static final ArrayList<DetalleFactura> orderList = new ArrayList<DetalleFactura>();
 
    public ArrayList<DetalleFactura> getOrderList() {
        return orderList;
    }
    
    public String addAction() {
        setearTotal();
        DetalleFactura orderitem = new DetalleFactura( this.cantidad , this.item, this.precioUnitario, this.tExentas, this.tCinco, this.tDiez);
        orderList.add(orderitem);
        montos();
        inicializar();        
        return null;
    }
    
    public void onEdit(RowEditEvent event) { 
        FacesMessage msg = new FacesMessage("Detalle de Factura Editado",((DetalleFactura) event.getObject()).getItem());  
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        montos();
        inicializar(); 
    }  
       
    public void onCancel(RowEditEvent event) {  
        FacesMessage msg = new FacesMessage("Detalle de Factura Cancelado");   
        FacesContext.getCurrentInstance().addMessage(null, msg); 
        orderList.remove((DetalleFactura) event.getObject());
        montos();
        inicializar(); 
    }
    
    private void montos(){
        montoExentas = 0.0;
        montoCinco = 0.0;
        montoDiez = 0.0;
        for(DetalleFactura det: orderList){
            montoExentas += det.getExentas();
            montoCinco += det.getCincoPorciento();
            montoDiez +=  det.getDiezPorciento();
        }
    } 
    
    private void inicializar() {
        cantidad = 0;
        item = "";
        precioUnitario = 0.0; 
        total = "";        
    }
    
    private void setearTotal() {        
        if(total.equals("1"))
            tExentas = cantidad * precioUnitario * 1.0;
        else
            tExentas = 0.0;
        if(total.equals("2"))
            tCinco = cantidad * precioUnitario * 1.0;
        else
            tCinco = 0.0;
        if(total.equals("3"))
            tDiez = cantidad * precioUnitario * 1.0;
        else
            tDiez = 0.0;
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

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Double gettCinco() {
        return tCinco;
    }

    public void settCinco(Double tCinco) {
        this.tCinco = tCinco;
    }

    public Double gettDiez() {
        return tDiez;
    }

    public void settDiez(Double tDiez) {
        this.tDiez = tDiez;
    }

    public Double gettExentas() {
        return tExentas;
    }

    public void settExentas(Double tExentas) {
        this.tExentas = tExentas;
    }

    public Double getMontoCinco() {
        return montoCinco;
    }

    public void setMontoCinco(Double montoCinco) {
        this.montoCinco = montoCinco;
    }

    public Double getMontoDiez() {
        return montoDiez;
    }

    public void setMontoDiez(Double montoDiez) {
        this.montoDiez = montoDiez;
    }

    public Double getMontoExentas() {
        return montoExentas;
    }

    public void setMontoExentas(Double montoExentas) {
        this.montoExentas = montoExentas;
    }

}