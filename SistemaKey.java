package DescFunc;

import isel.leic.utils.Time;

import java.util.*;
import java.io.*;

public class SistemaKey {
	public static String[] dados = new String[1000];
	public static String[] msgs = new String[1000];

	public static void read() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("File.txt"));
		String line;
		int i = 0;
		while ((line = br.readLine()) != null) {
			
			dados[i] = line;
			if(!dados[i].equalsIgnoreCase(" "))
			if(dados[i].substring(dados[i].lastIndexOf("-")).length()>5){
				if(msgs[i]==null)
					msgs[i]=dados[i].substring(dados[i].lastIndexOf("-")+6);
			}
					++i;	
		}
		br.close();
	}

	public static void write() throws FileNotFoundException {
		int i = 0;
		PrintWriter pw = new PrintWriter(new FileOutputStream("File.txt"));
		while (dados[i] != null) {
			if (msgs[i] != null ) {if(!msgs[i].equalsIgnoreCase("")){
				//if (msgs[i].length() != 0) {
				if (dados[i].equalsIgnoreCase(" ")){
					pw.write(dados[i]);
					pw.println();
			}
				else{
					pw.write(dados[i].substring(0,dados[i].lastIndexOf("-")+5) + " " + msgs[i]);
					//pw.write(dados[i]);
					pw.println();
				}/*} else {
					pw.write(dados[i]);
					pw.println();
				}*/
			} 
			
		}else {
			pw.write(dados[i]);
			pw.println();
		}++i;
	}
		pw.close();
		
	}

	public static void actualizar(boolean bool) throws IOException {
		if (bool) {
			read();
		} else {
			write();
		}
	}

	public static void receiveNr() throws IOException {
		LCD.clear();
		LCD.write("Insert Number"); // System.out.println("Incert Number");
		int count = 3;
		char x = 0;
		String str = "";
		while (count > 0) {
			x = Key.waitKey(5000);
			if (x != 0) {
				str += x;
				--count;
				LCD.posCursor(1, 0);
				LCD.write(str);
				System.out.println(x);
				if (x == 'C') {
					LCD.clear();
					receiveNr();
				}
			}
			
		}
		if (str.length() > 3) {
			LCD.clear();
			LCD.write("Invalido");
			receiveNr();
		}
		x = Key.waitKey(5000);

		if (x == 'E') {
			findNrUtil(str);
		} else
			receiveNr();
	}

	public static void manutencao() throws IOException {
		Scanner kbd = new Scanner(System.in);
		System.out.println("B-insertUser D-RemoveUser F-insertMsg");
		String manut = kbd.next();
		kbd = new Scanner(System.in);
		if (manut.equalsIgnoreCase("B")) {
			System.out.println("Insert User and Pin");
			insertUser(kbd.nextLine(), true, 0);
			end();
			return;
		}
		if (manut.equalsIgnoreCase("D")) {
			System.out.println("Intruduza o nr d utilizador a remover");
			removeUser(kbd.next());
			end();
			return;
		}
		if (manut.equalsIgnoreCase("F")) {
			System.out.println("Insira o nr d utilizador para colocar msg");
			int pos = kbd.nextInt();
			
			insertMsg(pos);
			end();
			return;
		}
	}

	public static void findNrUtil(String nr) throws IOException {
		int i = 0;
		if (nr.equalsIgnoreCase("DDD")) {
			System.out.println("Escolha a operacao de Manutencao");
			LCD.clear();
			LCD.write("Out of Service");
			manutencao();
			return;
		}
		for (; dados[i] != null; ++i) {
			if (!dados[i].equalsIgnoreCase(" ")) {
				if (dados[i].substring(0, 3).equalsIgnoreCase(nr)) {
					LCD.clear();
					LCD.write(dados[i].substring(6, dados[i].lastIndexOf("-")));
					LCD.posCursor(1, 0);
					LCD.write("Insert Pin ***");

					System.out.println(dados[i].substring(6, dados[i]
							.lastIndexOf("-")));
					// System.out.println("Insert Pin ***");
					insertPin(dados[i].substring(6,
							dados[i].lastIndexOf("-") + 5), i);
					return;
				}
			}
		}
		LCD.clear();
		LCD.write("Inexistente");
		Time.sleep(1000);
		System.out.println("Utilizador inexistente");
		receiveNr();
	}

	public static void insertPin(String nome, int pos) throws IOException {
		int count = 3;
		char x = 0;
		String pin = "";
		String car = "---";
		while (count > 0) {
			x = Key.waitKey(5000);
			if (x != 0) {
				if (x == 'C')
					receiveNr();
				pin += x;
				car = "*" + car;
				--count;
			} else {
				return;
			}
			LCD.clear();
			System.out.println(x);
			LCD.write("Insert Pin" + car.substring(0, 3));

		}
		x = Key.waitKey(5000);
		if (pin.equalsIgnoreCase(nome.substring(nome.lastIndexOf("-") + 2, nome
				.lastIndexOf("-") + 5))) {
			if (x == 'A')
				changePin(nome, pos);
			if (x == 'E') {
				LCD.clear();
				if (msgs[pos] != null  ) {
					if(!msgs[pos].equalsIgnoreCase("")){
					if (msgs[pos].length() != 0) {
						LCD.clear();
						LCD.write("OK");
						LCD.posCursor(1, 0);
						LCD.write(msgs[pos]);
						Time.sleep(5000);
						// removeMsg(pos);
					
					LCD.clear();
					LCD.write("Deseja apagar a");
					LCD.posCursor(1, 0);
					LCD.write("msg Sim:E Nao:C");
					x = Key.waitKey(5000);
					if (x == 'C') {
						return;
						
					}
					//n remove a msg, n le d file,
					if (x == 'E') {
						System.out.print("coisas");
						removeMsg(pos);
						return;
					}	
				}}
				}System.out.println("OK");
				LCD.write("OK");
				Time.sleep(5000);
				receiveNr();
			}
		} else if (x == 'E') {
			LCD.clear();
			LCD.write("Invalid Pin");
			Time.sleep(5000);
			receiveNr();
		}
	}

	public static void changePin(String dados, int pos) throws IOException {
		// Scanner kbd = new Scanner(System.in);
		LCD.clear();
		LCD.write("Alterar Pin");
		int count = 3;
		char x = 0;
		String str = "";
		String car = "---";
		while (count > 0) {
			x = Key.waitKey(5000);
			if (x != 0) {
				str += x;
				car = "*" + car;
				--count;
			} else
				return;
			LCD.clear();
			System.out.println(x);
			LCD.write("Insert Pin" + car.substring(0, 3));
			

		}
		dados = dados.substring(0, dados.lastIndexOf("-") + 2) + str;
		LCD.clear();
		LCD.write("Pin alterado");
		Time.sleep(5000);
		insertUser(dados, false, pos);
		actualizar(false);
	}

	public static void insertUser(String nome, boolean bool, int pos) throws IOException {
		int i = 0;
		if (!bool) {
			if (pos < 10) {
				dados[pos] = "00" + pos + " - " + nome;
				return;
			} else if (pos < 100) {
				dados[pos] = "0" + pos + " - " + nome;
				return;
			} else {
				dados[pos] = pos + " - " + nome;

			}
		} else {

			for (; i < dados.length; ++i) {
				if (dados[i] == null || dados[i].equalsIgnoreCase(" ")) {
					System.out.println(i);
					if (i < 10) {
						dados[i] = "00" + i + " - " + nome;
						return;
					} else if (i < 100) {
						dados[i] = "0" + i + " - " + nome;
						return;
					} else {
						dados[i] = i + " - " + nome;
						return;
					}
					
				}
			}
		}
	}

	public static void removeUser(String nrUtil) {
		Scanner kbd = new Scanner(System.in);
		for (int i = 0; i < dados.length; ++i) {
			if(dados[i] != null){
			if (dados[i].length() != 0) {
				if (dados[i].substring(0, 3).equalsIgnoreCase(nrUtil)) {
					System.out.println(dados[i].substring(6, dados[i]
							.lastIndexOf("-")));
					System.out.println("Deseja Remover Sim:1, Nao:0");
					if (kbd.next().equalsIgnoreCase("1")) {
						dados[i] = " ";
						return;
					} else
						return;
				}
			}
			}
		}

	}

	public static void end() throws IOException {
		Scanner kbd = new Scanner(System.in);
		System.out.println("Terminar o Programa Sim:1,Nao:0");
		if (kbd.next().equalsIgnoreCase("1")) {
			actualizar(false);
			System.exit(0);// receiveNr();
		} else {
			System.out.println("Sair Manutencao Sim:1,Nao:0");
			if (kbd.next().equalsIgnoreCase("1")) {
				actualizar(false);
				receiveNr();
			} else
				manutencao();

		}
	}

	public static void insertMsg(int pos) throws FileNotFoundException {
		System.out.println("Insira 1 msg");
		Scanner kbd = new Scanner(System.in);
		String msg = kbd.nextLine();
		msgs[pos] = msg;
		write();
	}

	public static void removeMsg(int pos) throws FileNotFoundException {
		msgs[pos] = " ";
		write();
	}

	public static void main(String[] args) throws IOException {
		LCD.init();

		while (true) {

			try {
				actualizar(true);
				LCD.setCursor(true, true);
				receiveNr();

				actualizar(false);

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
