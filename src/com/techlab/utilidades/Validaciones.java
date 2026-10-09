/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.utilidades;

import java.util.Scanner;

public final class Validaciones {
    private Validaciones() {}

    public static String texto(Scanner sc, String mensaje) {
        while (true) {
            System.out.print(mensaje);
            if (!sc.hasNextLine()) throw new IllegalStateException("Fin de entrada.");
            String valor = sc.nextLine().trim();
            if (!valor.isEmpty()) return valor;
            System.out.println("No puede estar vacío.");
        }
    }

    public static int entero(Scanner sc, String mensaje) {
        while (true) {
            try {
                return Integer.parseInt(texto(sc, mensaje));
            } catch (NumberFormatException e) {
                System.out.println("Ingresá un entero válido.");
            }
        }
    }

    public static int noNegativo(Scanner sc, String mensaje) {
        while (true) {
            int numero = entero(sc, mensaje);
            if (numero >= 0) return numero;
            System.out.println("No puede ser negativo.");
        }
    }

    public static int positivo(Scanner sc, String mensaje) {
        while (true) {
            int numero = entero(sc, mensaje);
            if (numero > 0) return numero;
            System.out.println("Debe ser mayor que cero.");
        }
    }

    public static double decimal(Scanner sc, String mensaje, boolean positivo) {
        while (true) {
            try {
                double numero = Double.parseDouble(texto(sc, mensaje).replace(',', '.'));
                if (Double.isFinite(numero) && (positivo ? numero > 0 : numero >= 0)) return numero;
            } catch (NumberFormatException ignored) { }
            System.out.println("Número inválido.");
        }
    }

    // Nombres alternativos para que las validaciones también sean fáciles de reconocer en la consigna.
    public static int leerEnteroNoNegativo(Scanner sc, String mensaje) { return noNegativo(sc, mensaje); }
    public static int leerEnteroPositivo(Scanner sc, String mensaje) { return positivo(sc, mensaje); }
    public static double leerDecimalNoNegativo(Scanner sc, String mensaje) { return decimal(sc, mensaje, false); }

    public static boolean confirmar(Scanner sc, String mensaje) {
        return texto(sc, mensaje).equalsIgnoreCase("S");
    }
}
