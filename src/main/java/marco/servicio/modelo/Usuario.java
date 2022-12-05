package marco.servicio.modelo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity(name = "usuario")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Usuario{
  @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
  @Column(name="u_clave") private int clave;
  @Column(name="u_alias") private String alias;
  @Column(name="u_contrasena") private String contrasena;
  @Column(name="u_rol") private String rol;
  
  public int getClave() {return clave;}
  
  public void setClave(int clave) {this.clave = clave;}
  
  public String getAlias() {return alias;}
  
  public void setAlias(String alias) {this.alias = alias;}
  
  public String getContrasena() {return contrasena;}
  
  public void setContrasena(String contrasena) 
    {this.contrasena = contrasena;}
  
  public String getRol() {return rol;}
  
  public void setRol(String rol) {this.rol = rol;}
}//class