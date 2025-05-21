package TP1;

public class Ejercicio5 {
	public static int Maximo (int[] vec, int dimL) {
		int max=-1;
		for (int i=0;i<dimL;i++) {
			if(vec[i]>max)
				max=vec[i];
		}
		return max;
	}
	public static int Minimo (int[] vec, int dimL) {
		int min=9999;
		for (int i=0;i<dimL;i++) {
			if(vec[i]<min)
				min=vec[i];
		}
		return min;
	}
	public static double Promedio (int[] vec, int dimL) {
		int suma=0;
		for (int i=0;i<dimL;i++) {
			suma+=vec[i];
		}
		double promedio= suma/dimL;
		return promedio;
	}
	
	public static String informarDatos(int max,int min, double Promedio) {
		return "el maximo es: "+ max + " el numero minimo es: "+ min + " el promedio es: "+ Promedio;
		
	}
	public static void main(String[] args) {
		int[] vec ={1, 6, 8, 16};
		int max= Maximo (vec, vec.length);
		int min= Minimo(vec, vec.length);
		System.out.println(informarDatos(max, min, Promedio(vec, vec.length)));

	}

}
