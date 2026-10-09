/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Cada línea relaciona un producto con la cantidad pedida y su precio al momento de comprar.
 */
package com.techlab.pedidos;

import com.techlab.productos.Producto;

public class LineaPedido {
    private final Producto producto;
    private final int cantidad;
    private final double precioUnitario;

    public LineaPedido(Producto producto, int cantidad) {
        if (producto == null) throw new IllegalArgumentException("Producto inválido.");
        if (cantidad <= 0) throw new IllegalArgumentException("La cantidad debe ser mayor a cero.");
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = producto.getPrecio();
    }

    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public double calcularSubtotal() { return precioUnitario * cantidad; }

    @Override
    public String toString() {
        return String.format("%s x %d | $%.2f c/u | Subtotal: $%.2f",
                producto.getNombre(), cantidad, precioUnitario, calcularSubtotal());
    }
}
