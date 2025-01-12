package com.archsoft;

import java.util.*;

public class Main {
	
	public static void main(String[] args) {
		Map<Integer, Veiculo> mapVeiculos =
				new HashMap<>();
		List<Veiculo> listVeiculos = new ArrayList<Veiculo>(1000000);
		
		for (int i = 1; i < 1000000; i++) {
			Veiculo v = new Veiculo(i);
			mapVeiculos.put(i, v);
			listVeiculos.add(v);
		}
		
		long t1 = System.currentTimeMillis();
		Veiculo ultimo = mapVeiculos.get(1000000);
		long t2 = System.currentTimeMillis();
		
		System.out.printf("Tempo para localizar o item(Map): %dms\n", (t2-t1));
		
		t1 = System.currentTimeMillis();
		/*for (Veiculo v: listVeiculos) {
			if (v.getRenavam().equals(1000000)) {
				break;
			}
		}*/
//		Iterator<Veiculo> it = listVeiculos.iterator();
//		while (it.hasNext()) {
//			if (it.next().equals(1000000)) {
//				break;
//			}
//		}
		listVeiculos.stream()
				.filter(veiculo -> veiculo.getRenavam().equals(1000000))
				.findFirst();

		t2 = System.currentTimeMillis();
		System.out.printf("Tempo para localizar o item(List): %dms", (t2-t1));
	}
}
