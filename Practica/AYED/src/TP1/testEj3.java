package TP1;

public class testEj3 {

	public static void main(String[] args) {
		Estudiante[] vecEst=new Estudiante[2];
		Profesor[] vecProf=new Profesor[3];
		Estudiante E1=new Estudiante("Dylan", "Clatt", 12, "DC@gmail.com", "numero 363" ); 
		Estudiante E2=new Estudiante("Candela", "Barrionuevo", 14, "CB@gmail.com", "numero 942" );
		vecEst[0]=E1; 
		vecEst[1]=E2;
		Profesor P1=new Profesor("Carlos", "Clatt", "CC@yahoo.com",4, "UNLP");
		Profesor P2=new Profesor("Sergio", "Barrionuevo", "S@yahoo.com",5, "UNLP");
		Profesor P3=new Profesor("Silvia", "Lacasa", "Sil@yahoo.com",1, "UNLP");
		vecProf[0]=P1; 
        vecProf[1]=P2;
        vecProf[2]=P3;
        for(int i=0;i<=1;i++) {
        	System.out.print(vecEst[i].tusDatos());
        }
        for(int i=0;i<=2;i++) {
        	System.out.print(vecProf[i].tusDatos());
        }
	}

}
