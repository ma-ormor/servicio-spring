package marco.servicio.interfaz;

import org.springframework.data.repository.CrudRepository;

import marco.servicio.modelo.Usuario;

public interface RepositorioUsuario 
  extends CrudRepository<Usuario, Integer>{
	
	public Usuario findByAlias(String alias);
}