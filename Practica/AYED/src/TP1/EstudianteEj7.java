package TP1;

public class EstudianteEj7{
	private String nombreYApellido;
	private String num;
	
	public EstudianteEj7 () {
		
	}
	
	public EstudianteEj7(String nombreYApellido, String num) {
		this.nombreYApellido = nombreYApellido;
		this.num = num;
	}
	
	public String getNombreYApellido() {
		return nombreYApellido;
	}
	public void setNombreYApellido(String nombreYApellido) {
		this.nombreYApellido = nombreYApellido;
	}
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String toString() {
		return " el nombre y apellido del alumno es: "+ getNombreYApellido()+ " el Numero de alumno es: "+ getNum();
	}
	

}
