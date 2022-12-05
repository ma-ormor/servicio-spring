package marco.servicio.modelo;

public class Respuesta{
  String message;
  Object data;
  boolean success;
  
  public Respuesta(String message, Object data, boolean success) {
    this.message = message;
    this.data = data;
    this.success = success; 
  }//builder
  
  public String getMessage() {
    return message;}
  
  public Object getData() {
    return data;}
  
  public boolean isSuccess() {
    return success;}
}//class