/*
RESUMO      : Classe do Caixa Eletrônico (com saque e depósito apenas). Simulo o comportamento de um caixa eletrônico normal que realiza apenas duas operações
PROGRAMADORA: Luiza Felix
DATA        : 31/03/2023
 */

package controller;

import java.util.concurrent.Semaphore;

public class ATM extends Thread {

	private static Semaphore deposito = new Semaphore(1);
	private static Semaphore saque = new Semaphore(1);
	
	private int ID, tipo, saldo, valor; // pelo menos 1 real de depósito.

	public ATM(int ID, int tipo, int saldo, int valor ) {
		this.ID = ID;
		this.tipo = tipo;
		this.saldo = saldo;
		this.valor = valor;
		
	}

	@Override
	public void run() {
		if (tipo == 1) {
			deposito();
		} else {
			saque();
		}
	}

	private void saque() {
		try {
			saque.acquire();
			
			if(valor < saldo) {
				saldo -= valor;
				System.out.println("SAQUE - CLIENTE ID"+ID+"\n	O saque de R$"+ valor + " solicitado pela conta ID"+ ID + " foi aceito!\n	Saldo Atual R$" + saldo + "\n");
			} else {
				System.out.println("SAQUE - CLIENTE ID"+ID+"\n	O valor de saque solicitado (R$" + valor + ") e maior que  o saldo disponivel na conta ID" + ID + ". A operacao nao pode ser concluída.\n	Saldo Atual R$" + saldo + "\n");
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			saque.release();
		}
	}

	private void deposito() {
		try {
			deposito.acquire();
			saldo += valor;
			System.out.println("DEPOSITO - CLIENTE ID"+ID+"\n	O deposito de R$" + valor + " solicitado pela conta ID" + ID + " foi aceito!\n	Saldo Atual R$" + saldo + "\n");
			

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			deposito.release();
		}
		
	}
}
