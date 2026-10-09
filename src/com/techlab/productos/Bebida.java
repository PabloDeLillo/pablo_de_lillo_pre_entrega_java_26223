/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Una bebida es un producto, pero también guarda información propia como su volumen.
 */
package com.techlab.productos;
public class Bebida extends Producto {
 private double volumenLitros;
 public Bebida(String nombre,double precio,int stock,Categoria categoria,double volumen){super(nombre,precio,stock,categoria);setVolumenLitros(volumen);}
 public double getVolumenLitros(){return volumenLitros;} public void setVolumenLitros(double v){if(!Double.isFinite(v)||v<=0)throw new IllegalArgumentException("Volumen inválido.");volumenLitros=v;}
 @Override public String getTipo(){return "Bebida";} @Override public String getDetalleEspecifico(){return String.format("Volumen: %.2f L",volumenLitros);}
 @Override public String toString(){return super.toString()+" [subtipo bebida]";}
}
