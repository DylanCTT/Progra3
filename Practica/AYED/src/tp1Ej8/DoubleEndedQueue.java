package tp1Ej8;

import java.util.ArrayList;

public class DoubleEndedQueue<T> extends Queue<T> {
	public void enqueueFirst(T t) {
		if(this.isEmpty()) {
			data = new ArrayList<T>();
		}
		data.add(0,t);	
	}

}

