package tp5Ej1;

public class AdjMatrixVertex<T> implements Vertex<T> {
	
	private T dato;
	private int posicion;
	
	protected AdjMatrixVertex(T dato, int posicion) { /*porque protected el constructors si yo voy a tener que intanciar vertices?*/
		this.dato = dato;
		this.posicion = posicion;
	}
	
	public T getData() {
		return this.dato;
	}
	
	public void setData(T data) {
		this.dato = data;
	}

	public int getPosition() {
		return posicion;
	}

	public void setPosition(int posicion) {
		this.posicion = posicion;
	}

	protected void decrementPosition() {
		this.posicion--;
	}
	
	
}
