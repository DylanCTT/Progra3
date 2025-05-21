package TP1;

public class Profesor {
	private String nombre;
	private String apellido;
	private String email;
	private int catedra;
	private String facultad;
	
	public Profesor(){
		
	}

	public Profesor(String nombre, String apellido, String email, int catedra, String facultad) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.catedra = catedra;
		this.facultad = facultad;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCatedra() {
		return catedra;
	}

	public void setCatedra(int catedra) {
		this.catedra = catedra;
	}

	public String getFacultad() {
		return facultad;
	}

	public void setFacultad(String facultad) {
		this.facultad = facultad;
	}
	public String tusDatos() {
		String aux="tu nombre: "+ getNombre()+" tu apellido: "+ getApellido()+ 
		" tu email: "+ getEmail()+ " tu catedra: "+ getCatedra()+ " tu facultad: "+ 
		getFacultad();
		return aux;
	}
	
	

}
