package marco.servicio.controlador;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marco.servicio.interfaz.RepositorioProducto;
import marco.servicio.modelo.Respuesta;
import marco.servicio.modelo.Producto;

@RestController @RequestMapping("/producto")
public class ServicioProducto{
  @Autowired private RepositorioProducto productos;
  
  @GetMapping
  public Respuesta leerTodos() {
    return new 
      Respuesta("Productos encontrados", productos.findAll(), true); 
  }//method
  
  @GetMapping("/{id}")
  public Respuesta leer(@PathVariable("id") int clave) {
    Producto producto;
    
    try{
      producto = productos.findById(clave).get();}
    catch(NoSuchElementException exception) {
      return new Respuesta("Producto no existe", null, false);}
    return new Respuesta("Producto encontrado", producto, true);
  }//method

  @PostMapping
  public Respuesta crear(
    @RequestParam(value="nombre") String nombre,
    @RequestParam(value="descripcion") String descripcion,
    @RequestParam(value="cantidad") int cantidad,
    @RequestParam(value="costo") double costo
  ) {
    Producto nuevo = new Producto();
    int clave;
    
    nuevo.setNombre(nombre);
    nuevo.setDescripcion(descripcion);
    nuevo.setCantidad(cantidad);
    nuevo.setCosto(costo);
    
    productos.save(nuevo);
    clave = obtenerId(nuevo);
    
    if(clave != -1)
      return new Respuesta("Registrado", clave, true);
    return new Respuesta("No registrado", clave, false);
  }//method
  
  @PutMapping("/{id}")
  public Respuesta actualizar(
    @PathVariable(value="id") int clave,
    @RequestParam(value="nombre") String nombre,
    @RequestParam(value="descripcion") String descripcion,
    @RequestParam(value="cantidad") int cantidad,
    @RequestParam(value="costo") double costo
  ) {
    Producto producto;
    
    try{
      producto = productos.findById(clave).get();}
    catch(NoSuchElementException exception) {
      return new Respuesta("No encontrado", clave, false);}

    producto.setNombre(nombre);
    producto.setDescripcion(descripcion);
    producto.setCantidad(cantidad);
    producto.setCosto(costo);

    productos.save(producto);

    return new Respuesta("Actualizado", clave, true);
  }//method
  
  @DeleteMapping("/{id}")
  public Respuesta borrar(@PathVariable(value="id") int clave) {
    try{
      productos.findById(clave).get();}
    catch(NoSuchElementException exception) {
      return new Respuesta("No encontrado", clave, false);}
    
    productos.deleteById(clave);
    return new Respuesta("Eliminado", clave, true);
  }//method
  
  private int obtenerId(Producto producto) {
    for(Producto registro:productos.findAll())
      if(
        producto.getNombre().equals(registro.getNombre()) &&
        producto.getDescripcion().equals(registro.getDescripcion()) &&
        producto.getCantidad() == registro.getCantidad() &&
        producto.getCosto() == registro.getCosto()
      )return registro.getClave();
    return -1;
  }//method
}//class