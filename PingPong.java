package Trabalho_2;

import isel.leic.usbio.*;
import isel.leic.utils.*;

public class PingPong {

	public static void main(String[] args) {

		int a = 1;
		boolean b = true, esq = true;

		while (b) {
			
			if (esq)
				a <<= 1;

			else
				a >>= 1;

				UsbPort.out(a);
				Time.sleep(200);
				

				
			if (a == 1 << 7 && UsbPort.in() == 1 << 7)
				esq = false;
			else if (a == 1 && UsbPort.in() == 1)
				esq = true;

			else if( a == 1 && UsbPort.in() != 1 || a==1 << 7 && UsbPort.in() != 1 << 7 ){
				System.out.println("ganhou");
				b=false;}

		}

	}

}
