package marco.servicio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="producto")
public class Producto{
  @Id
  @Column(name="p_clave") private int clave;
  @Column(name="p_nombre") private String nombre;
  @Column(name="p_descripcion") private String descripcion;
  @Column(name="p_cantidad") private int cantidad;
  @Column(name="p_costo") private double costo;
  
  public int getClave() 
    {return clave;}
  
  public void setClave(int clave) 
    {this.clave = clave;}
  
  public String getNombre() 
    {return nombre;}
  
  public void setNombre(String nombre) 
    {this.nombre = nombre;}
  
  public String getDescripcion() 
    {return descripcion;}
  
  public void setDescripcion(String descripcion) 
    {this.descripcion = descripcion;}
  
  public int getCantidad() 
    {return cantidad;}
  
  public void setCantidad(int cantidad) 
   {this.cantidad = cantidad;} 
  
  public double getCosto() 
    {return costo;}
  
  public void setCosto(double costo)
    {this.costo = costo;}
}//class