package tp2;

import tp1Ej8.Queue;


public class BinaryTree <T> {
	
	private T data;
	private BinaryTree<T> leftChild;   
	private BinaryTree<T> rightChild; 

	
	public BinaryTree() {
		super();
	}

	public BinaryTree(T data) {
		this.data = data;
		/*this.leftChild= null;
		this.rightChild= null;*/
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	/*
	 * Preguntar antes de invocar si hasLeftChild()
	 * @return
	 */
	public BinaryTree<T> getLeftChild() {
		return leftChild;
	}
	/**
	 * Preguntar antes de invocar si hasRightChild()
	 * @return
	 */
	public BinaryTree<T> getRightChild() {
		return this.rightChild;
	}

	public void addLeftChild(BinaryTree<T> child) {
		this.leftChild = child;
	}

	public void addRightChild(BinaryTree<T> child) {
		this.rightChild = child;
	}

	public void removeLeftChild() {
		this.leftChild = null;
	}

	public void removeRightChild() {
		this.rightChild = null;
	}

	public boolean isEmpty(){
		return (this.isLeaf() && this.getData() == null);
	}

	public boolean isLeaf() {
		return (!this.hasLeftChild() && !this.hasRightChild());

	}
		
	public boolean hasLeftChild() {
		return this.leftChild!=null;
	}

	public boolean hasRightChild() {
		return this.rightChild!=null;
	}
	@Override
	public String toString() {
		return this.getData().toString();
	}

	public  int contarHojas() {
		return this.contarRecursivo(this);
	}

	private int contarRecursivo (BinaryTree<T> A) {
		int cont=0;
		if (A.isLeaf()) {
			return 1;
		}
		if (A.hasLeftChild()) cont+=this.contarRecursivo(A.getLeftChild());
		if (A.hasRightChild()) cont+=this.contarRecursivo(A.getRightChild());
		return cont;
	}
		
    	 
	public BinaryTree<T> espejo(){
		BinaryTree<T> aux = new BinaryTree<T>(this.getData()); //nuevo nodo
		if (this.hasLeftChild()) aux.addRightChild(this.getLeftChild().espejo());
		if (this.hasRightChild()) aux.addLeftChild(this.getRightChild().espejo());
		return aux;
	}

	// 0<=n<=m
	public void entreNiveles(int n, int m){
			BinaryTree<T> ab = null;
			Queue<BinaryTree<T>> cola = new Queue<BinaryTree<T>>();
			cola.enqueue(this);
			cola.enqueue(null);
			int t=0;
			while (!cola.isEmpty() && n<=m) {
				ab = cola.dequeue();
				if (ab != null) {
					if (t==n) System.out.println("- "+ ab.getData());
					if (ab.hasLeftChild()) cola.enqueue(ab.getLeftChild());
					if (ab.hasRightChild()) cola.enqueue(ab.getRightChild());
				} else if (!cola.isEmpty()) {
					if (t==n) System.out.println();
					cola.enqueue(null);
					t++;
					if(t>n) n++;
				}
			}
		}
}
		


