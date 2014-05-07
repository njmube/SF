package manageBeans;

/**
 *
 * @author stfy-warrior
 */
import java.io.Serializable;
 
public class DetalleFactura implements Serializable {
 
    private static final long serialVersionUID = 1L;
    private int cantidad;
    private String item; // descripcion    
    private Double precioUnitario;
    private Double exentas;
    private Double cincoPorciento;
    private Double diezPorciento;

    public DetalleFactura(int cantidad, String item, Double precioUnitario, Double exentas, Double cincoPorciento, Double diezPorciento) {
        this.cantidad = cantidad;
        this.item = item;
        this.precioUnitario = precioUnitario;
        this.exentas = exentas;
        this.cincoPorciento = cincoPorciento;
        this.diezPorciento = diezPorciento;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getExentas() {
        return exentas;
    }

    public void setExentas(Double exentas) {
        this.exentas = exentas;
    }

    public Double getCincoPorciento() {
        return cincoPorciento;
    }

    public void setCincoPorciento(Double cincoPorciento) {
        this.cincoPorciento = cincoPorciento;
    }

    public Double getDiezPorciento() {
        return diezPorciento;
    }

    public void setDiezPorciento(Double diezPorciento) {
        this.diezPorciento = diezPorciento;
    }
    
    
  
}