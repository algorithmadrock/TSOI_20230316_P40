/*
RESUMO      : Classe Principal, o "Banco" com o conjunto de caixas eletrônicos
PROGRAMADORA: Luiza Felix
DATA        : 31/03/2023
 */

package view;

import javax.swing.JOptionPane;

import controller.ATM;

public class Principal {
	public static void main(String[] args) {
		JOptionPane.showMessageDialog(null, "IMPORTANTE!\n As operações, saldos e usuários são criados aleatoriamente, o foco do enunciado era a visualização do comportamento das threads.");
		
		
		iniciar();
	}

	private static void iniciar() {
		for (int i = 0; i < 20; i++) {
			int ID = (int) (Math.random()*200 + 1000); // 1000~1199
			int tipo = (int) (Math.random()*2 + 1); // 1: deposito | 2: saque
			int saldo = (int) (Math.random()*501); // posso n ter nada na minha conta antes;
			int valor = (int) (Math.random()* 500 + 1); //min. 1 real para realizar uma operação
			
			
			Thread caixa = new ATM(ID, tipo, saldo, valor);
			caixa.start();
		}

	}
}
