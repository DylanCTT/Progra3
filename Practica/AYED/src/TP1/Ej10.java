package TP1;
import tp1Ej8.DoubleEndedQueue;
public class Ej10 {
	public static void main(String[] args) {
		DoubleEndedQueue<ClienteEj9> colaBanco= new DoubleEndedQueue<ClienteEj9>();
		colaBanco.enqueue(new ClienteEj9("Marta", true));
		colaBanco.enqueue(new ClienteEj9("Marcos", false));
		colaBanco.enqueue(new ClienteEj9("Tatiana", true));
		colaBanco.enqueue(new ClienteEj9("Carlos", false));
		while (!colaBanco.isEmpty()){
			if (colaBanco.head().getEsEmbarazada()) {
				colaBanco.enqueueFirst(colaBanco.head());
			}
			System.out.println(colaBanco.head().getNombre());
			colaBanco.dequeue();
		}
		
	}
	
	
}
