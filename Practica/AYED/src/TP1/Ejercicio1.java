package TP1;

public class Ejercicio1 {
	
	public static void contFor (int a, int b)  {
		if (a==b) 
			System.out.println("los numeros son iguales.");
			else if (a>b) {
				for (int i=b;i>=a;i++)
					System.out.println(i);
			}
			else if (a<b) {
				for (int i=a;i<=b;i++)
					System.out.println(i);
			}
	}
	public static void contWhile (int a, int b) {
		if (a==b) 
			System.out.println("los numeros son iguales.");
			else if (a>b) {
				while (b<=a)
					System.out.println(b++);
			}
			else if (a<b) {
				while (b>=a)
					System.out.println(a++);
			}
	}
	public static void contSinNada (int a,int b) {
		if(a==b)
			System.out.println("termino");
		else if (a<=b) {
		    	System.out.println(a);
		    	contSinNada(++a,b);
		}
		else if (b>=a) {
			System.out.println(b);
			contSinNada(a,++b);
		}
	}
}
