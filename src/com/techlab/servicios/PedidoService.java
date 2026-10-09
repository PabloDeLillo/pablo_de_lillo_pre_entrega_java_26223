/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Antes de confirmar una compra, reviso que alcance el stock para todos los artículos.
 */
package com.techlab.servicios;

import com.techlab.excepciones.StockInsuficienteException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Articulo;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PedidoService {
    private final List<Pedido> pedidos = new ArrayList<>();

    public Pedido crearPedido(List<LineaPedido> lineas) throws StockInsuficienteException {
        if (lineas == null || lineas.isEmpty()) throw new IllegalArgumentException("Pedido vacío.");

        Map<Articulo, Integer> cantidades = new LinkedHashMap<>();
        for (LineaPedido linea : lineas) {
            Articulo articulo = linea.getArticulo();
            long suma = (long) cantidades.getOrDefault(articulo, 0) + linea.getCantidad();
            if (suma > Integer.MAX_VALUE) {
                throw new StockInsuficienteException("Cantidad demasiado grande para " + articulo.getNombre());
            }
            cantidades.put(articulo, (int) suma);
        }

        // Primero valido todo el pedido. El stock se descuenta únicamente si todo está correcto.
        for (Map.Entry<Articulo, Integer> entrada : cantidades.entrySet()) {
            if (entrada.getValue() > entrada.getKey().getStock()) {
                throw new StockInsuficienteException(
                        "Stock insuficiente para " + entrada.getKey().getNombre()
                                + ". Disponible: " + entrada.getKey().getStock()
                                + ", solicitado: " + entrada.getValue());
            }
        }

        Pedido pedido = new Pedido(lineas);
        for (Map.Entry<Articulo, Integer> entrada : cantidades.entrySet()) {
            entrada.getKey().setStock(entrada.getKey().getStock() - entrada.getValue());
        }
        pedidos.add(pedido);
        return pedido;
    }

    public List<Pedido> getPedidos() { return java.util.Collections.unmodifiableList(pedidos); }

    public void listarPedidos() {
        if (pedidos.isEmpty()) System.out.println("No hay pedidos realizados.");
        else for (Pedido pedido : pedidos) System.out.println(pedido);
    }
}
