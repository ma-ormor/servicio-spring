package marco.servicio.controlador;

import java.util.NoSuchElementException;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import marco.servicio.interfaz.RepositorioUsuario;
import marco.servicio.modelo.Respuesta;
import marco.servicio.modelo.Usuario;

@RestController @RequestMapping("/usuario")
public class ServicioUsuario{
  @Autowired private RepositorioUsuario usuarios;
  
  @GetMapping
  public Respuesta leerTodos() {
    Iterable<Usuario> lista = usuarios.findAll();  
    
    lista.forEach(new Consumer<Usuario>(){
      @Override public void accept(Usuario usuario) {
        usuario.setContrasena(null);
      }//method
    });//for
    
    if(usuarios.count() > 0)
      return new Respuesta("Usuarios encontrados", lista, true);
    return new Respuesta("Sin usuarios", lista, false); 
  }//method
  
  @GetMapping("/{id}")
  public Respuesta leer(@PathVariable("id") int clave) {
    Usuario usuario;
    
    try {
      usuario = usuarios.findById(clave).get();}
    catch(NoSuchElementException exception) {
      return new Respuesta("No existe", null, false);}
    
    usuario.setContrasena(null);
    return new Respuesta("Usuario encontrado", usuario, true);
  }//method
  
  @PostMapping()
  public Respuesta crear(
    @RequestParam(value="alias") String alias,
    @RequestParam(value="contraseña") String contrasena
  ) {
    Usuario usuario = new Usuario();
    int clave;
    
    usuario.setAlias(alias);
    usuario.setContrasena(contrasena);
    usuario.setRol("cliente");
    usuarios.save(usuario);
    
    clave = obtenerId(usuario); 
    
    if(clave != -1)
      return new Respuesta("Registrado", clave, true);
    return new Respuesta("No registrado", clave, false);
  }//method
  
  @PutMapping("/{id}")
  public Respuesta actualizar(
    @PathVariable(value="id") int clave,
    @RequestParam(value="alias") String alias,
    @RequestParam(value="contraseña") String contrasena
  ) {
    Usuario usuario;
    
    try{
      usuario = usuarios.findById(clave).get();}
    catch(NoSuchElementException exception){
      return new Respuesta("Sin actualizar", clave, false);}
    
    usuario.setAlias(alias);
    usuario.setContrasena(contrasena);
    usuarios.save(usuario);
    
    return new Respuesta("Actualizado", clave, true);
  }//method
  
  @DeleteMapping("/{id}")
  public Respuesta borrar(@PathVariable(value="id") int clave) {
    try{
      usuarios.findById(clave).get();}
    catch (NoSuchElementException exception) {
      return new Respuesta("No encontrado", clave, false);}
    usuarios.deleteById(clave);
    
    return new Respuesta("Eliminado", clave, true);
  }//method 
  
  private int obtenerId(Usuario usuario) {
    for(Usuario registro:usuarios.findAll())
      if(
        usuario.getAlias() == registro.getAlias() &&
        usuario.getContrasena() == registro.getContrasena()
      )return registro.getClave();
    return -1;
  }//method
}//class