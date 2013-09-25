package DescFunc;

import isel.leic.*;
import isel.leic.usbio.*;
import isel.leic.utils.*;

public class Key {
	// Mascaras das ligações ao input port.
	private static final int VAL = 0x40;
	private static final int DATA = 0x0F;
	private static char lastKey;
	private static char[] coisas = { 'A', 'B', 'C', 'D', 'E', 'F' };

	// Se uma tecla está premida retorna a tecla: '0'..'9','A'..'F'
	// Retorna o valor 0 (zero) se nenhuma tecla está premida.
	public static char pressedKey() {
		Kit.read();
		if (Kit.getBits(VAL) != VAL) {
			return 0;
		} else {
			if (Kit.getBits(DATA) < 10)
				return (char) (Kit.getBits(DATA) + '0');
			else {
				return coisas[Kit.getBits(DATA) - 10];
			}
		}
	}

	// Se uma tecla foi premida retorna a tecla: '0'..'9','A'..'F'
	// Retorna o valor 0 (zero) se nenhuma tecla foi premida.
	// Retorna o valor 0 (zero) se a tecla premida já foi retornada.
	public static char getKey() {
		char number = pressedKey();
		if (lastKey == number) {
			return 0;
		} else {
			lastKey = number;// while (pressedKey()==number);
			return number;
		}

	}

	// Se uma tecla foi premida retorna a tecla: '0'..'9','A'..'F'
	// Se nenhuma tecla foi premida espera durante o tempo indicado
	// em milisegundos que seja premida uma tecla.
	// Retorna o valor 0 (zero) se não for premida uma tecla durante
	// o tempo indicado.
	public static char waitKey(long timeout) {
		char c;
		long timeAux=Time.getTimeInMillis();
		long count=0;
			while (count <timeout ) {
				if ((c = getKey()) > 0) {
					return c;
				}
			count =Time.getTimeInMillis()-timeAux;
				} 
			return 0;
			
	}

	public static void main(String[] args) {
	while(true){
		int P2=1,P1=1,P0=1;
		int UP=0;
			P0=~P0;
		P1=P1^P0^UP;
	P2=((P2^(P1&P0))&~UP) | ((P2^(~P1&~P0))&UP);
System.out.println(P2+P1+P0);
Time.sleep(1000);
	}
		/*
 
		LCD.init();
		LCD.setCursor(true, true);

		boolean b = true;
		char x = 0;
		int coisas = 0;
		while (b) {
			x = waitKey(5000);
			
			if(x==0)
				LCD.clear();
			else{
				LCD.clear();
			LCD.write(x);
			System.out.println(x);
			}
	*/
	/*	char x=0;
		while(true){
			lastKey=x;
			x=pressedKey();
			
			if(x=='A' && lastKey=='F')
				System.exit(0);
			else
			{
				System.out.println(x);
				Time.sleep(500);
			}
			}
		*///
		// x = getKey();
		// System.out.println(x);
		// if (x == 0 + '0') {
		// LCD.clear();
		// } else {
		// LCD.write(x);
		// Time.sleep(5000);
		// }
		// waitKey(5000);
		// }

	}
}
