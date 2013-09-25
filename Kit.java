package Trabalho_2;

import isel.leic.usbio.*;
import isel.leic.utils.*;

public class Kit {

	public static void main(String[] args) {

		int a = 1;
		boolean esq = true, b = true;

		while (b) {

			Time.sleep(200);
			UsbPort.out(a);

			if (esq)
				a <<= 1;

			else
				a >>= 1;

			if (a == 128 || a== 1<<7 || a==0x80)
				esq = false;
			else if (a == 1)
				esq = true;

		}

	}

}
