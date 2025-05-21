package tp2;

public class ParcialArbolesEj7 {
	
	BinaryTree<Integer> ab =null;

	public BinaryTree<Integer> getAb() {
		return ab;
	}

	public void setAb(BinaryTree<Integer> ab) {
		this.ab = ab;
	}
	
	public boolean isLeftTree (int num) {
		return Ayuda1(this.ab, num);
	}
	
	private boolean Ayuda1(BinaryTree<Integer> ab, int num) {
		int izq,der;
		ab= Buscador(ab,num);
		
		if (ab!=null) {
			if (ab.hasLeftChild()) izq=Contador(ab.getLeftChild());
			else izq=-1;
			if (ab.hasRightChild()) der=Contador(ab.getRightChild());
			else der=-1;
			if (izq>der)return true;
		}
		return false;
	}
	private BinaryTree<Integer> Buscador (BinaryTree<Integer> ab, int num){
		if ( ab!=null && ab.getData()!=num ) {
			Ayuda1(ab.getLeftChild(), num);
			Ayuda1(ab.getRightChild(), num);
		}
		return ab;
	}
	
	private int Contador (BinaryTree<Integer> ab) {
		int cont=0;
		if (ab!=null){
			Contador(ab.getLeftChild());
			Contador(ab.getRightChild());
			cont+=hasOneChild(ab);
		}
		return cont;
	}
	
	private int hasOneChild(BinaryTree<Integer> ab) {
		if (ab.getLeftChild()!=null && ab.getRightChild()==null) {
			return 1;
		}
		if (ab.getLeftChild()==null && ab.getRightChild()!=null) {
			return 1;
		}
		return 0;
	}
	
}
