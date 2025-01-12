package com.archsoft;

public class Main {

	public static void main(String[] args) {
		ConnectionPool pool = new ConnectionPool(4);
		
		ConnUse[] uses = new ConnUse[10];
		
		for (int i = 0; i < uses.length; i++) {
			uses[i] = new ConnUse(pool);
		}
		
		for (int i = 0; i < uses.length; i++) {
			uses[i].start();
		}
	}
}
