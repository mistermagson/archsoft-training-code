package com.archsoft;

import java.util.ArrayList;
import java.util.List;

public class New {

	void exemplo() {
		List<String> list = new ArrayList<>();
		
		list.add("Primeiro");
		list.add("Segundo");
		//Erro de compilacao
//		list.add(new Object());
//		list.add(1);
		
		List<Integer> numeros = new ArrayList<Integer>();
		numeros.add(10);
		numeros.add(20);
		numeros.add(30);
		
		for (int i = 0; i < numeros.size(); i++) {
			Integer valor = numeros.get(i);
		}
	}
}
