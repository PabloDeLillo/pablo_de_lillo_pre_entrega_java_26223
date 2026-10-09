/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Representa la categoría de un producto y evita guardar esa información suelta.
 */
package com.techlab.productos;
public class Categoria implements com.techlab.interfaces.Identificable {
 private final int codigo; private String nombre; private String descripcion;
 public Categoria(int codigo,String nombre,String descripcion){if(codigo<=0)throw new IllegalArgumentException("Código inválido");this.codigo=codigo;com.techlab.utilidades.Secuencias.registrarCategoria(codigo);setNombre(nombre);setDescripcion(descripcion);}
 public int getId(){return codigo;}
 public int getCodigo(){return codigo;} public String getNombre(){return nombre;} public String getDescripcion(){return descripcion;}
 public void setNombre(String nombre){if(nombre==null||nombre.isBlank())throw new IllegalArgumentException("Nombre de categoría vacío");this.nombre=nombre.trim();}
 public void setDescripcion(String descripcion){this.descripcion=descripcion==null?"":descripcion.trim();}
 @Override public String toString(){return "Categoría "+codigo+": "+nombre+" ("+descripcion+")";}
}
