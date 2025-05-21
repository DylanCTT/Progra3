package tp2;

public class RedBinariaLlena {

	private BinaryTree<Integer> ab= null;
	
	public RedBinariaLlena (BinaryTree<Integer> ab) {
		this.setAb(ab);
	}
	
	
	public BinaryTree<Integer> getAb() {
		return ab;
	}


	public void setAb(BinaryTree<Integer> ab) {
		this.ab = ab;
	}
	
	public int retardoReenvio (BinaryTree<Integer> ab) {
		if(ab!=null&&!ab.isEmpty()) {
			int izq = retardoReenvio(ab.getLeftChild());
			int der = retardoReenvio(ab.getRightChild());
			return (izq>der?(ab.getData()+izq):(ab.getData()+der));
		}
		return 0;
	}

}
