package DescFunc;
import isel.leic.usbio.UsbPort;
 import isel.leic.utils.Time;
 import java.util.*;
  
public class Kit {
 
	        
 public static int val;
 public static int valOut;
 /* Lê o input port para posterior consulta */
 public static void read() {
 val=~UsbPort.in();
	
 }
 /* Consulta o valor representado pelos bits da mascara que foram previamente
 lidos com read()
 * Retorna o valor correspondente dos bits com 0 para GND e 1 para Vcc
 * Por exemplo retorna 0x0F se I0..I3 estão a Vcc e I4..I7 estão a GND */
 public static int getBits(int mask) {
 return mask&val;
 }
 /* Consulta o bit indicado pela mascara previamente lido com read()
 * Retorna true se o bit indicado pela mascara está a Vcc */
 public static boolean isBit(int mask) {
 	return ((val&mask)!=0);
 }
 /* Lê e consulta o valor representado pelos bits da mascara */
 public static int readBits(int mask) {
 read();
 return getBits(mask); }
	
/* Lê e consulta o bit indicado pela mascara */
 public static boolean readBit(int mask) {
 read();
 return isBit(mask); }

	
 /* Coloca a Vcc os bits indicados pela mascara e os restantes bits mantêm o
 valor. */
 public static void setBits(int mask) {
		 
	out(valOut = (valOut|mask));
 }
 /* Coloca a GND os bits indicados pela mascara e os restantes bits mantêm o
 valor. */
 public static void resetBits(int mask) {
 	out(valOut=(valOut&~mask));	
		
 }
	
/* Inverte o valor dos bits indicados pela mascara e os restantes bits mantêm
 o valor. */
 public static void invertBits(int mask) {
	out(valOut=(valOut^mask));
 }
	
 /* Altera os valores dos bits indicados pela mascara para os bits
 correspondentes em ´value´ 1-Vcc 0-GND */
 public static void write(int values, int mask , boolean tx) {
 	(!tx)?out(valOut=(valOut&~mask)|(values&mask)):Interface.serieTx(values);
 }
 
 public static void out(int x){
	UsbPort.out(~x);
 }
 
}

