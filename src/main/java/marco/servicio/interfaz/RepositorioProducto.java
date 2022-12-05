package marco.servicio.interfaz;

import org.springframework.data.repository.CrudRepository;

import marco.servicio.modelo.Producto;

public interface RepositorioProducto 
  extends CrudRepository<Producto, Integer>{}//interface