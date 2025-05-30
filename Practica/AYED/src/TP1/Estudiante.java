package TP1;

public class Estudiante {
	private String nombre;
	private String apellido;
	private int comision;
	private String email;
	private String direccion;
	
	public Estudiante() {
		
	}
	public Estudiante(String nombre, String apellido, int comision, String email, String direccion) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.comision = comision;
		this.email = email;
		this.direccion = direccion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public int getComision() {
		return comision;
	}
	public void setComision(int comision) {
		this.comision = comision;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	
	public String tusDatos() {
		String aux="tu nombre: "+ getNombre()+" tu apellido: "+ getApellido()+ 
		" tu email: "+ getEmail()+ " tu comision: " +getComision()+ " tu direccion: "+ getDireccion();
		return aux;
	}
	@Override
	public String toString() {
		return "Estudiante [nombre=" + nombre + ", apellido=" + apellido + ", comision=" + comision + ", email=" + email
				+ ", direccion=" + direccion + "]";
	}
}
	
