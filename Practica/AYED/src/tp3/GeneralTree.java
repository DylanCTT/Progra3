package tp3;

import java.util.List;

import java.util.LinkedList;


public class GeneralTree<T> {

	private T data;
	private List<GeneralTree<T>> children = new LinkedList<GeneralTree<T>>();

	public GeneralTree(T data) {
		this.data = data;
	}
	public GeneralTree() {
		
	}

	public GeneralTree(T data, List<GeneralTree<T>> children) {
		this(data);
		this.children = children;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public void setChildren(List<GeneralTree<T>> children) {
		if (children != null)
			this.children = children;
	}

	public List<GeneralTree<T>> getChildren() {
		return this.children;
	}

	public void addChild(GeneralTree<T> child) {
		this.getChildren().add(child);
	}

	public boolean isLeaf() {
		return !this.hasChildren();
	}

	public boolean hasChildren() {
		return children != null && !children.isEmpty();
	}

	public boolean isEmpty() {
		return this.data == null && !hasChildren();
	}

	public void removeChild(GeneralTree<T> child) {
		if (this.hasChildren()) {
			List<GeneralTree<T>> children = this.getChildren();
			if (children.contains(child))
				children.remove(child);
		}
	}
	
	public int altura() {
		if(!this.isLeaf()) {
			int max=0;
			int alt;
			for(GeneralTree<T> h : this.getChildren()) {
				alt = h.altura();
				if(alt>max)max=alt;
			}
			return max+1;
		}
		else return 0;
	}
	
	public int ancho() {
		if(this!=null) {
			if(this.hasChildren()) {
				int aux;
				int max_ancho = this.getChildren().size();
				for(GeneralTree<T> nodo : this.getChildren()) {
					aux=nodo.ancho();
					if(aux>max_ancho)max_ancho=aux;
				}
				return max_ancho;
			}
		}
		return (this.isEmpty()?0:1);
	}
	
	public int nivel(T dato) {
		if(this!=null) {
			if(this.getData()==dato)return 0;
			if(this.hasChildren()) {
				int aux;
				for(GeneralTree<T> nodo : this.getChildren()) {
					aux=nodo.nivel(dato);
					if(aux>=0)return aux+1;
				}
			}
		}
		return -1;
	}
	
	public GeneralTree<T> encontrarNodo(T data){
		if(this!=null&&!this.isEmpty()) {
			if(this.getData()==data)return this;
			if(this.hasChildren()) {
				GeneralTree<T> aux;
				for(GeneralTree<T> nodo : this.getChildren()) {
					aux = nodo.encontrarNodo(data);
					if(aux!=null)return aux;
				}
			}
		}
		return null;
	}
	
	public boolean esAncestro(T a, T b) {
		if(a!=null&&b!=null) {
			GeneralTree<T> aux = this.encontrarNodo(a);
			if(aux!=null) {
				aux = aux.encontrarNodo(b);
				return aux!=null;
			}
		}
		return false;
	}
	
	
	
}
