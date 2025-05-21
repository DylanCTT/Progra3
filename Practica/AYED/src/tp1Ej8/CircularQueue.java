package tp1Ej8;

public class CircularQueue<T> extends Queue<T> {
	
	public CircularQueue() {
		
	}
	
	public T shift (){
		if (!super.isEmpty()) {
			T primero= super.dequeue();
			this.enqueue(primero);
			return primero; 	
		}
		return null;
	}
	

}
