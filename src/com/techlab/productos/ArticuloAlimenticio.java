/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.productos;

public class ArticuloAlimenticio extends Articulo {
    private int diasParaVencimiento;

    public ArticuloAlimenticio(int codigo, String nombre, double precio, int stock,
                               Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, stock, categoria);
        setDiasParaVencimiento(diasParaVencimiento);
    }

    public int getDiasParaVencimiento() { return diasParaVencimiento; }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        if (diasParaVencimiento < 0) throw new IllegalArgumentException("Los días para vencimiento no pueden ser negativos.");
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() { return "Alimenticio"; }

    @Override
    public String getDetalleEspecifico() {
        return "Días para vencimiento: " + diasParaVencimiento;
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }
}
