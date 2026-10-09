/** TechLab - Desarrollador: Pablo De Lillo. */
package com.techlab.utilidades;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
public final class Validaciones {
 private Validaciones(){}
 public static String texto(Scanner sc,String mensaje){while(true){System.out.print(mensaje);if(!sc.hasNextLine())throw new IllegalStateException("Fin de entrada.");String s=sc.nextLine().trim();if(!s.isEmpty())return s;System.out.println("No puede estar vacío.");}}
 public static int entero(Scanner sc,String m){while(true){try{return Integer.parseInt(texto(sc,m));}catch(NumberFormatException e){System.out.println("Ingresá un entero válido.");}}}
 public static int noNegativo(Scanner sc,String m){while(true){int n=entero(sc,m);if(n>=0)return n;System.out.println("No puede ser negativo.");}}
 public static int positivo(Scanner sc,String m){while(true){int n=entero(sc,m);if(n>0)return n;System.out.println("Debe ser mayor que cero.");}}
 public static double decimal(Scanner sc,String m,boolean positivo){while(true){try{double d=Double.parseDouble(texto(sc,m).replace(',','.'));if(Double.isFinite(d)&&(positivo?d>0:d>=0))return d;}catch(NumberFormatException e){}System.out.println("Número inválido.");}}
 public static LocalDate fecha(Scanner sc,String m){while(true){try{return LocalDate.parse(texto(sc,m));}catch(DateTimeParseException e){System.out.println("Usá AAAA-MM-DD.");}}}
 public static boolean confirmar(Scanner sc,String m){return texto(sc,m).equalsIgnoreCase("S");}
}
