/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Uso esta clase para los productos que no necesitan datos especiales.
 */
package com.techlab.productos;
public class ProductoGeneral extends Producto {
 public ProductoGeneral(String nombre,double precio,int stock,Categoria categoria){super(nombre,precio,stock,categoria);}
 @Override public String getTipo(){return "General";} @Override public String getDetalleEspecifico(){return "Sin detalle adicional";}
 @Override public String toString(){return super.toString();}
}
