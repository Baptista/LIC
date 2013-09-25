package Trabalho_1;

public class Bases {

	private static int base;

	private static String total;

	public static void base(int n, int b) {

		while (base < b) {

			int dividendo = n * base;
			int resto = 0;
			String aux = "";

			while (dividendo > 0) {

				resto = (dividendo % b);
				dividendo = dividendo / b;

				if (b == 16) {

					if (resto > 9 && resto < 16) {

						resto += (55);
						aux += String.valueOf((char) resto);

					} else
						aux += String.valueOf(resto);
				}

				else if (b != 16)
					aux += String.valueOf(resto);

			}

			total = "";
			for (int i = aux.length() - 1; i >= 0; --i) {
				total += aux.charAt(i);
			}
			System.out.println(n + "*" + base + "=" + total);
			++base;

		}

	}

	public static void main(String[] args) {

		Bases.base(3, 8);
	}

}
