/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * La comida reutiliza los datos de Producto y agrega su fecha de vencimiento.
 */
package com.techlab.productos;
import java.time.LocalDate;
public class Comida extends Producto {
 private LocalDate fechaVencimiento;
 public Comida(String nombre,double precio,int stock,Categoria categoria,LocalDate fecha){super(nombre,precio,stock,categoria);setFechaVencimiento(fecha);}
 public LocalDate getFechaVencimiento(){return fechaVencimiento;} public void setFechaVencimiento(LocalDate f){if(f==null)throw new IllegalArgumentException("Fecha obligatoria.");fechaVencimiento=f;}
 @Override public String getTipo(){return "Comida";} @Override public String getDetalleEspecifico(){return "Vencimiento: "+fechaVencimiento;}
 @Override public String toString(){return super.toString()+" [subtipo comida]";}
}
