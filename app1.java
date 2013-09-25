import java.util.*;

public class app1 {

	public static void decimalToBinary(int n)

	{
		if (n < 0 || n > 16284)
			return;
		int[] arr = { 2, 8, 16 };
		String[] arrHex = { "A", "B", "C", "D", "E", "F" };
		for (int numb = 0; numb < arr.length; ++numb) {
			int i = 0, a = n;
			String s = "";
			while (a > 0) {
				int res = a % arr[numb];
				a /= arr[numb];
				if (res > 9 && res < 16) {
					res = res - 10;
					s += arrHex[res];
				} else {
					s += String.valueOf(res);
				}
			}
			for (i = s.length() - 1; i >= 0; --i)
				System.out.print(s.charAt(i));
			System.out.println();

		}
	}

	public static void main(String[] args) {

		Scanner kbd = new Scanner(System.in);
		int a = kbd.nextInt();
		app1.decimalToBinary(a);

	}
}