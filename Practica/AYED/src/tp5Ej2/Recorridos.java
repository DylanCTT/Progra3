package tp5Ej2;

import java.util.List;

import tp1Ej8.Queue;
import tp5Ej1.Edge;
import tp5Ej1.Graph;
import tp5Ej1.Vertex;

public class Recorridos<T> {

	
	public void dfs(Graph<T> grafo) {
		boolean [] marca =new boolean [grafo.getSize()];  //en este vector guardo las visitas de los vertices
		for (int i=0; i<grafo.getSize(); i++) {
			if (!marca[i]) {
				System.out.println("larco con: "+ grafo.getVertex(i).getData());
				dfs (i,grafo,marca);
			}
		}
	}
	
	private void dfs(int i, Graph<T> grafo, boolean[] marca) {
		marca[i] = true;
		Vertex<T> v = grafo.getVertex(i);
		System.out.println(v);
		List<Edge<T>> adyacentes = grafo.getEdges(v); //declaro lista de adyacentes de el vertice
		for (Edge<T> e: adyacentes){  //para cada adyacente guardo en lista
			int j = e.getTarget().getPosition();
			if (!marca[j]) {  //cheque si ya pase por el vertice para agregarlo
				dfs(j, grafo, marca);
			}
		}
	}
	
	public void bfs(Graph<T> grafo) {
		 boolean[] marca = new boolean[grafo.getSize()];
		 for (int i = 0; i <= marca.length; i++) {
				 if (!marca[i]){
						 this.bfs(i, grafo, marca); 
				 }
		 }
	}
	
	private void bfs(int i, Graph<T> grafo, boolean[] marca) {
		Queue<Vertex<T>> q = new Queue<Vertex<T>>();
		q.enqueue(grafo.getVertex(i));
		marca[i] = true;
		while (!q.isEmpty()) {
				Vertex<T> w = q.dequeue();
				System.out.println(w);
				// para todos los vecinos de w:
				List<Edge<T>> adyacentes = grafo.getEdges(w);
				for (Edge<T> e: adyacentes) {
						int j = e.getTarget().getPosition();
						if (!marca[j]) {
								marca[j] = true;
								//Vertex<T> v = e.getTarget();
								q.enqueue(e.getTarget());
						}
				}
		}
	}

}
