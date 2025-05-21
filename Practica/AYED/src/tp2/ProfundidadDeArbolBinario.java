package tp2;

public class ProfundidadDeArbolBinario {
	
	private BinaryTree<Integer> ab= null;
	
	
	public BinaryTree<Integer> getAb() {
		return ab;
	}


	public void setAb(BinaryTree<Integer> ab) {
		this.ab = ab;
	}


	public int sumaElementosProfundidad(int p) {
		return sumaElementosProfundidadRecursivoPostOrder(p, ab);
	}
	
	private int sumaElementosProfundidadRecursivoPostOrder(int p,BinaryTree<Integer> ab) {
		if(ab!=null && p>=0) {
			return sumaElementosProfundidadRecursivoPostOrder(p-1,ab.getLeftChild())+
				   sumaElementosProfundidadRecursivoPostOrder(p-1,ab.getRightChild())+
				   (p==0 ? ab.getData() : 0);  // operador ternario!!
		}
		return 0;
	}
}

