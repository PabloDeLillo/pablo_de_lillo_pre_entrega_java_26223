/**
 * Proyecto: TechLab - Sistema de gestión de productos y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Error propio del sistema para indicar que una búsqueda no encontró el producto.
 */
package com.techlab.excepciones;
public class ProductoNoEncontradoException extends Exception {public ProductoNoEncontradoException(String mensaje){super(mensaje);}}
