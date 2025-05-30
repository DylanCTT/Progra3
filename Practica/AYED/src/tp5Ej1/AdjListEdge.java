package tp5Ej1;

public class AdjListEdge<T> implements Edge<T> {
	private Vertex<T> target;
	private int weight;
	
	public AdjListEdge(Vertex<T> target, int weight){
		this.target = target;
		this.weight = weight;
	}
	
	@Override
	public Vertex<T> getTarget() {
		return target;
	}

	@Override
	public int getWeight() {
		return weight;
	}
}