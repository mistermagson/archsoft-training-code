package com.archsoft;


public class Main {

	public static void main(String[] args) {
		Pessoa p = new Pessoa();
		p.setNome("Maradona");
		
//		try {
//			p.setIdade(1);
//		} catch (IdadeNegativaException e) {
//			System.out.println("Aviso: a data nao foi atualizada");
//		} catch (IdadeMuitoGrandeException e) {
//			System.out.println("Aviso: a data nao foi atualizada");
//		} finally {
//			System.out.println("Este bloco sera executado independente de erro");
//		}

		try {
			p.setIdade(-1);
		} catch (IdadeNegativaException | IdadeMuitoGrandeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// faz um tratamento generico
		}
	}
}
