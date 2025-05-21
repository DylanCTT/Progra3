package TP1;

import java.util.ArrayList;
import java.util.List;

public class testArrayListEj7 {

	public static void main(String[] args) {
		List <EstudianteEj7> vecList=new ArrayList<EstudianteEj7>();
		vecList.add(new EstudianteEj7("rasa","036943")); 
        vecList.add(new EstudianteEj7("Joaco","02425/3"));
        vecList.add(new EstudianteEj7("Dylan","03772/9"));
		List <EstudianteEj7> copiaVec= new ArrayList<EstudianteEj7>();
		copiaVec.addAll(vecList);
		for (int i=0;i<3;i++) {
			System.out.println(vecList.get(i));
			System.out.println(copiaVec.get(i));
		}
		
		vecList.get(0).setNombreYApellido("rasanaus");
		for (int i=0;i<3;i++) {
			System.out.println(vecList.get(i));
			System.out.println(copiaVec.get(i));
		}
		vecList.add(new EstudianteEj7("capuzzi","91203"));

	}
}
