package DescFunc;
import isel.leic.*;
import isel.leic.usbio.*;
import isel.leic.utils.*;

public class LCD {

	public static boolean center = false;
	public static final int e = 0x80;
	public static final int w = 0x4F;
	public static final int rs = 0x40;

	public static void writeE(int bits, boolean b) {
		Kit.setBits(0x80);
		if (b) {
			Kit.write(bits | rs, w);
		} else {
			Kit.write(bits, w);
		}
		Kit.resetBits(0x80);
	}
	
	

	/* Realiza a sequencia de inicialização para comunicação a 4 bits */
	public static void init() {
		Time.sleep(50);
		writeE(0x03, false);
		Time.sleep(10);
		writeE(0x03, false);
		Time.sleep(1);
		writeE(0x03, false);

		writeE(0x02, false);

		// function set
		// N = 1 , 1ª linha
		writeE(0x02, false);
		writeE(0x08, false);
		// display off
		writeE(0x00, false);
		writeE(0x08, false);
		// display clear
		clear();
		// entry mode se
		writeE(0x00, false);
		writeE(0x06, false);
	}

	/* Apaga todos os acaracteres do display */
	public static void clear() {

		writeE(0x00, false);
		writeE(0x01, false);

	}

	/* Posiciona o cursor na linha (0..1) e coluna (0..15) indicadas */
	public static void posCursor(int line, int col) {
		int coisas=(line*0x40+col)|0x80; 
		writeE(coisas>>4, false);
	writeE(coisas&0x0F,false);
	}

	/* Acerta o tipo de cursor: Visivel ou invisivel; A piscar ou constante */
	public static void setCursor(boolean visible, boolean blinking) {
		// 0x0C -> N F
		// visible 0x02 blinking 0x01
		writeE(0x00, false);
		if (visible && blinking) {
			writeE(0x0F, false);
		} else if (visible && !blinking) {
			writeE(0x0E, false);
		} else if (!visible && blinking) {
			writeE(0x0D, false);
		} else {
			writeE(0x0C, false);
		}
	}

	/*
	 * Escreve o caracter indicado no local do cursor e o cursor avança para a
	 * proxima coluna
	 */
	
	public static void write(char c) {
		int numb = c;
		writeE(numb>>4 , true);
		writeE(numb&0x0F , true);
	
	}

	/*
	 * Escreve o texto indicado no local do cursor e o cursor avança para a
	 * coluna seguinte
	 */
	public static void write(String txt) {
		for (int i = 0; i < txt.length(); ++i)
			write(txt.charAt(i));
	}

	/*
	 * Indica se o texto escrito com writeLine() nas chamadas seguintes deve ou
	 * não ficar centrado na linha
	 */
	public static void setCenter(boolean value) {
		center = value;
	}

	/*
	 * Escreve o texto indicado na linha indicada (0 ou 1). O resto da linha é
	 * preenchida com espaços. O texto fica centrado ou alinhado à esquerda,
	 * dependendo da última chamada a SetCenter()
	 */
	public static void writeLine(int line, String txt) {
		posCursor(line, 0);
		if (center) {
			posCursor(line, (16 / 2) - (txt.length() / 2));
		}
		write(txt);
	}

	public static void main(String[] args){
		
		init();
		setCursor(true,true);
		//setCenter(true);
		posCursor(0,4);
		write("Isel");
		posCursor(1,4);
		write("uma merda");
		
		
		//clear();
		
	}
}
