/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Clase base de los productos. Centralizo los datos comunes y valido los cambios para evitar precios o stocks incorrectos.
 */
package com.techlab.productos;
public abstract class Producto implements Descontable {
 // El contador es compartido por todos los productos y me ayuda a asignar IDs.
 private static int contadorProductos=0; private final int id; private String nombre; private double precio; private int stock; private Categoria categoria;
 protected Producto(String nombre,double precio,int stock,Categoria categoria){setNombre(nombre);setPrecio(precio);setStock(stock);setCategoria(categoria);this.id=++contadorProductos;}
 public static int getContadorProductos(){return contadorProductos;} public int getId(){return id;} public String getNombre(){return nombre;} public double getPrecio(){return precio;} public int getStock(){return stock;} public Categoria getCategoria(){return categoria;}
 public void setNombre(String n){if(n==null||n.isBlank())throw new IllegalArgumentException("El nombre no puede estar vacío.");nombre=n.trim();}
 // Valido el precio antes de guardarlo para que el producto siempre tenga datos coherentes.
 public void setPrecio(double p){if(!Double.isFinite(p)||p<0)throw new IllegalArgumentException("Precio inválido.");precio=p;}
 public void setStock(int s){if(s<0)throw new IllegalArgumentException("El stock no puede ser negativo.");stock=s;}
 public void setCategoria(Categoria c){if(c==null)throw new IllegalArgumentException("Categoría obligatoria.");categoria=c;}
 public abstract String getTipo(); public abstract String getDetalleEspecifico();
 public double calcularPrecioFinal(){return precio;}
 @Override public double aplicarDescuento(double porcentaje){if(!Double.isFinite(porcentaje)||porcentaje<0||porcentaje>100)throw new IllegalArgumentException("Descuento entre 0 y 100.");return calcularPrecioFinal()*(1-porcentaje/100);}
 // toString permite mostrar el producto sin repetir el formato en el menú.
 @Override public String toString(){return String.format("ID: %d | Tipo: %s | Nombre: %s | Categoría: %s | Precio: $%.2f | Stock: %d | %s",id,getTipo(),nombre,categoria.getNombre(),precio,stock,getDetalleEspecifico());}
}
