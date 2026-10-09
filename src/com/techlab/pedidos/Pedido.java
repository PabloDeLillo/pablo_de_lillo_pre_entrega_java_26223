/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Un pedido reúne varias líneas y permite consultar el total de la compra.
 */
package com.techlab.pedidos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pedido {
    private static int contadorId = 1;
    private final int id;
    private final List<LineaPedido> lineas;

    public Pedido(List<LineaPedido> lineas) {
        if (lineas == null || lineas.isEmpty()) throw new IllegalArgumentException("El pedido debe tener al menos un producto.");
        this.id = contadorId++;
        this.lineas = new ArrayList<>(lineas);
    }

    public int getId() { return id; }
    public List<LineaPedido> getLineas() { return Collections.unmodifiableList(lineas); }
    public double calcularTotal() {
        double total = 0;
        for (LineaPedido linea : lineas) total += linea.calcularSubtotal();
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nPedido #" + id + "\n");
        for (LineaPedido linea : lineas) sb.append("  - ").append(linea).append("\n");
        sb.append(String.format("TOTAL: $%.2f", calcularTotal()));
        return sb.toString();
    }
}
