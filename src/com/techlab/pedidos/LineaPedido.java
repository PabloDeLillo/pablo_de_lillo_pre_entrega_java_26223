/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.pedidos;

import com.techlab.productos.Articulo;

public class LineaPedido {
    private final Articulo articulo;
    private final int cantidad;
    private final double precioUnitario;

    public LineaPedido(Articulo articulo, int cantidad) {
        if (articulo == null) throw new IllegalArgumentException("Artículo inválido.");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        this.articulo = articulo;
        this.cantidad = cantidad;
        this.precioUnitario = articulo.getPrecio();
    }

    public Articulo getArticulo() { return articulo; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double calcularSubtotal() { return precioUnitario * cantidad; }

    @Override
    public String toString() {
        return String.format("%s x %d | $%.2f c/u | Subtotal: $%.2f",
                articulo.getNombre(), cantidad, precioUnitario, calcularSubtotal());
    }
}
