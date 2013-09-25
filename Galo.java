

import java.util.*;

public class Galo {

	private static char jog1 = 'X';

	private static char jog2 = 'O';

	private static char[][] arr = { { '1', '2', '3' }, { '4', '5', '6' },
			{ '7', '8', '9' } };

	public static void jogada(int n, char jogador) {

		int i = 0, j = 0;

		switch (n) {
		case 1:
			i = 0;
			j = 0;
			break;
		case 2:
			i = 0;
			j = 1;
			break;
		case 3:
			i = 0;
			j = 2;
			break;
		case 4:
			i = 1;
			j = 0;
			break;
		case 5:
			i = 1;
			j = 1;
			break;
		case 6:
			i = 1;
			j = 2;
			break;
		case 7:
			i = 2;
			j = 0;
			break;
		case 8:
			i = 2;
			j = 1;
			break;
		case 9:
			i = 2;
			j = 2;
			break;
		}

		arr[i][j] = jogador;

	}

	public static void mostrar(char[][] arr) {

		int[] countsX = new int[8];
		int[] countsO = new int[8];
		int count = 0;

		for (int i = 0; i < arr.length; ++i) {

			for (int j = 0; j < arr[0].length; ++j) {

				if (arr[i][j] == 'X') {

					count++;

					if (i == j)
						countsX[0]++;

					if (Math.abs(i - j) == 2 || i == 1 && j == 1)
						countsX[1]++;

					if (i == 0)
						countsX[2]++;
					if (i == 1)
						countsX[3]++;
					if (i == 2)
						countsX[4]++;
					if (j == 0)
						countsX[5]++;
					if (j == 1)
						countsX[6]++;
					if (j == 2)
						countsX[7]++;

				}
				if (arr[i][j] == 'O') {

					count++;

					if (i == j)
						countsO[0]++;

					if (Math.abs(i - j) == 2 || i == 1 && j == 1)
						countsO[1]++;

					if (i == 0)
						countsO[2]++;
					if (i == 1)
						countsO[3]++;
					if (i == 2)
						countsO[4]++;
					if (j == 0)
						countsO[5]++;
					if (j == 1)
						countsO[6]++;
					if (j == 2)
						countsO[7]++;

				}
				if (j != 2)
					System.out.print(arr[i][j] + "|");
				else
					System.out.print(arr[i][j]);
			}
			System.out.println();

			if (i != 2)
				System.out.println("------");

		}

		for (int h = 0; h < countsX.length; ++h) {
			if (countsX[h] == 3) {
				System.out.println("jogador 1 ganhou");
				System.exit(0);
			}
			if (countsO[h] == 3) {
				System.out.println("jogador 2 ganhou");
				System.exit(0);
			}
		}

		if (count == 9) {
			System.out.println("Empatado");
			System.exit(0);
		}
	}

	public static void main(String[] args) {

		Scanner s = new Scanner(System.in);

		for (;;) {

			int jog = s.nextInt();

			while (jog > 9 || jog < 1) {
				System.out.println("joga outra vez");
				jog = s.nextInt();
			}

			Galo.jogada(jog, 'X');
			Galo.mostrar(arr);

			int jog2 = s.nextInt();

			while (jog2 > 9 || jog2 < 1) {
				System.out.println("joga outra vez");
				jog2 = s.nextInt();
			}

			Galo.jogada(jog2, 'O');
			Galo.mostrar(arr);
		}

	}

}
