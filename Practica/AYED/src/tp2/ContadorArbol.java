package tp2;
import java.util.ArrayList;

public class ContadorArbol {
	
	private BinaryTree<Integer> ab= null;
	
	public ContadorArbol (BinaryTree<Integer> ab) {
		this.setAb(ab);
	}
	
	
	public BinaryTree<Integer> getAb() {
		return ab;
	}


	public void setAb(BinaryTree<Integer> ab) {
		this.ab = ab;
	}

	public ArrayList<Integer> numerosParesInOrder(){ //  funciona con array list, linked y queue? 
		ArrayList<Integer> vec =new ArrayList<Integer>();
		numerosParesRecuInOrder(vec,ab);
		return vec;
	}
	private void numerosParesRecuInOrder(ArrayList<Integer> vec, BinaryTree<Integer> ab) {
		if (ab!=null) {
			numerosParesRecuInOrder(vec, ab.getLeftChild());
			if(ab.getData()%2==0)vec.add(ab.getData());
			this.numerosParesRecuInOrder(vec, ab.getRightChild());
		}
	}
	
	public ArrayList<Integer> numerosParesPostOrder(){
		ArrayList<Integer> vec =new ArrayList<Integer>();
		numerosParesRecuPostOrder(vec,ab);
		return vec;
	}
	
	private void numerosParesRecuPostOrder(ArrayList<Integer> vec,BinaryTree<Integer> ab) {
		if(ab!=null) {
			numerosParesRecuPostOrder(vec, ab.getLeftChild());
			this.numerosParesRecuPostOrder(vec, ab.getRightChild());
			if(ab.getData()%2==0)vec.add(ab.getData());
		}
	}
	
}
