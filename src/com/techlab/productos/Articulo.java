/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Clase base abstracta. Acá dejo los datos que comparten todos los artículos.
 */
package com.techlab.productos;

import com.techlab.interfaces.Identificable;

public abstract class Articulo implements Descontable, Identificable {
    private final int codigo;
    private String nombre;
    private double precio;
    private int stock;
    private Categoria categoria;

    protected Articulo(int codigo, String nombre, double precio, int stock, Categoria categoria) {
        if (codigo <= 0) throw new IllegalArgumentException("El código debe ser mayor que cero.");
        this.codigo = codigo;
        setNombre(nombre);
        setPrecio(precio);
        setStock(stock);
        setCategoria(categoria);
    }

    @Override
    public int getId() { return codigo; }

    public int getCodigo() { return codigo; }
    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
    public int getStock() { return stock; }
    public Categoria getCategoria() { return categoria; }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) throw new IllegalArgumentException("El nombre no puede estar vacío.");
        this.nombre = nombre.trim();
    }

    public void setPrecio(double precio) {
        if (!Double.isFinite(precio) || precio < 0) throw new IllegalArgumentException("El precio no puede ser negativo.");
        this.precio = precio;
    }

    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("El stock no puede ser negativo.");
        this.stock = stock;
    }

    public void setCategoria(Categoria categoria) {
        if (categoria == null) throw new IllegalArgumentException("La categoría es obligatoria.");
        this.categoria = categoria;
    }

    // Cada subtipo explica qué artículo es y cuál es su dato particular.
    public abstract String getTipoArticulo();
    public abstract String getDetalleEspecifico();

    public double calcularPrecioFinal() { return precio; }

    @Override
    public double aplicarDescuento(double porcentaje) {
        if (!Double.isFinite(porcentaje) || porcentaje < 0 || porcentaje > 100) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 100.");
        }
        return calcularPrecioFinal() * (1 - porcentaje / 100);
    }

    @Override
    public String toString() {
        return String.format(
                "Código: %d | Tipo: %s | Nombre: %s | Categoría: %s | Precio: $%.2f | Stock: %d | %s",
                codigo, getTipoArticulo(), nombre, categoria.getNombre(), precio, stock, getDetalleEspecifico());
    }
}
