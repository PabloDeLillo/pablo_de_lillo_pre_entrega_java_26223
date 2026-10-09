/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.utilidades;

public final class Secuencias {
    private static int categoria = 0;

    private Secuencias() {}

    public static int siguienteCategoria() { return ++categoria; }

    public static void registrarCategoria(int id) {
        if (id > categoria) categoria = id;
    }
}
