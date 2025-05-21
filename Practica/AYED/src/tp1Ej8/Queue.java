package tp1Ej8;

import java.util.ArrayList;

public class Queue<T> extends Sequence {
	
	protected ArrayList<T> data= new ArrayList<T>();

	public Queue() {
		data = new ArrayList<T>();
	}
	
	public void enqueue( T t) { 
		data.add(t);
	}
	
	public T dequeue() {
		return data.remove(0);
	}
	
	public T head() {
		return data.get(0);
	}
	
	@Override
	public int size() {
		return data.size();
	}
	
	@Override
	public boolean isEmpty() {
		return (data.size()==0);
	}
	
	@Override
	public String toString() {
		String aux="";
		for(int i=0;i<this.size();i++) {
			aux = aux+data.get(i).toString();
		}
		return aux;
	}

}
