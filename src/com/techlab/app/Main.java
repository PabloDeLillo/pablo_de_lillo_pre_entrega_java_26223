/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.app;

import com.techlab.excepciones.ArticuloNoEncontradoException;
import com.techlab.pedidos.LineaPedido;
import com.techlab.pedidos.Pedido;
import com.techlab.productos.Articulo;
import com.techlab.productos.ArticuloAlimenticio;
import com.techlab.productos.ArticuloElectronico;
import com.techlab.productos.Categoria;
import com.techlab.servicios.ArticuloService;
import com.techlab.servicios.CategoriaService;
import com.techlab.servicios.PedidoService;
import com.techlab.utilidades.Validaciones;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    private static final CategoriaService categorias = new CategoriaService();
    private static final ArticuloService articulos = new ArticuloService();
    private static final PedidoService pedidos = new PedidoService();

    public static void main(String[] args) {
        cargarCategoriasIniciales();

        try {
            int opcion;
            do {
                System.out.println("\n===== TECHLAB =====");
                System.out.println("1) Artículos / productos");
                System.out.println("2) Categorías");
                System.out.println("3) Pedidos");
                System.out.println("0) Salir");
                opcion = Validaciones.entero(sc, "Opción: ");

                switch (opcion) {
                    case 1 -> menuArticulos();
                    case 2 -> menuCategorias();
                    case 3 -> menuPedidos();
                    case 0 -> System.out.println("Hasta luego.");
                    default -> System.out.println("Opción inválida.");
                }
            } while (opcion != 0);
        } catch (IllegalStateException e) {
            System.out.println("Entrada finalizada.");
        } finally {
            // Cierro el Scanner al terminar la aplicación.
            sc.close();
        }
    }

    private static void menuCategorias() {
        int opcion;
        do {
            System.out.println("\n--- CATEGORÍAS ---");
            System.out.println("1) Crear");
            System.out.println("2) Listar");
            System.out.println("3) Buscar");
            System.out.println("4) Actualizar");
            System.out.println("5) Eliminar");
            System.out.println("0) Volver");
            opcion = Validaciones.entero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1 -> {
                        Categoria categoria = categorias.crear(
                                Validaciones.texto(sc, "Nombre: "),
                                Validaciones.texto(sc, "Descripción: "));
                        System.out.println("Creada: " + categoria);
                    }
                    case 2 -> listarCategorias();
                    case 3 -> System.out.println(categorias.buscar(Validaciones.positivo(sc, "Código: ")));
                    case 4 -> {
                        int codigo = Validaciones.positivo(sc, "Código: ");
                        categorias.actualizar(
                                codigo,
                                Validaciones.texto(sc, "Nuevo nombre: "),
                                Validaciones.texto(sc, "Nueva descripción: "));
                        System.out.println("Categoría actualizada.");
                    }
                    case 5 -> {
                        int codigo = Validaciones.positivo(sc, "Código: ");
                        System.out.println(categorias.buscar(codigo));
                        if (Validaciones.confirmar(sc, "¿Eliminar? (S/N): ")) {
                            categorias.eliminar(codigo, articulos.getArticulos());
                            System.out.println("Categoría eliminada.");
                        }
                    }
                    case 0 -> { }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void menuArticulos() {
        int opcion;
        do {
            System.out.println("\n--- ARTÍCULOS / PRODUCTOS ---");
            System.out.println("1) Agregar artículo");
            System.out.println("2) Listar artículos");
            System.out.println("3) Buscar artículo");
            System.out.println("4) Modificar artículo");
            System.out.println("5) Eliminar artículo");
            System.out.println("0) Volver");
            opcion = Validaciones.entero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1 -> agregarArticulo();
                    case 2 -> articulos.listarArticulos();
                    case 3 -> buscarArticulo();
                    case 4 -> modificarArticulo();
                    case 5 -> eliminarArticulo();
                    case 0 -> { }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void agregarArticulo() {
        System.out.println("1) Artículo electrónico");
        System.out.println("2) Artículo alimenticio");
        int tipo = Validaciones.entero(sc, "Tipo: ");
        if (tipo != 1 && tipo != 2) {
            System.out.println("Tipo inválido.");
            return;
        }

        int codigo = Validaciones.positivo(sc, "Código del artículo: ");
        if (codigoArticuloExistente(codigo)) {
            System.out.println("Ya existe un artículo con ese código.");
            return;
        }

        String nombre = Validaciones.texto(sc, "Nombre: ");
        double precio = Validaciones.decimal(sc, "Precio: ", false);
        int stock = Validaciones.noNegativo(sc, "Stock: ");
        Categoria categoria = pedirCategoriaExistente();

        Articulo articulo;
        if (tipo == 1) {
            int garantia = Validaciones.noNegativo(sc, "Garantía en meses: ");
            articulo = new ArticuloElectronico(codigo, nombre, precio, stock, categoria, garantia);
        } else {
            int dias = Validaciones.noNegativo(sc, "Días para vencimiento: ");
            articulo = new ArticuloAlimenticio(codigo, nombre, precio, stock, categoria, dias);
        }

        articulos.agregarArticulo(articulo);
        System.out.println("Artículo agregado: " + articulo);
    }

    private static void buscarArticulo() throws ArticuloNoEncontradoException {
        System.out.println("1) Buscar por código");
        System.out.println("2) Buscar por nombre");
        int opcion = Validaciones.entero(sc, "Opción: ");

        if (opcion == 1) {
            System.out.println(articulos.buscarArticuloPorCodigo(Validaciones.positivo(sc, "Código: ")));
        } else if (opcion == 2) {
            List<Articulo> encontrados = articulos.buscarPorNombre(Validaciones.texto(sc, "Nombre: "));
            for (Articulo articulo : encontrados) System.out.println(articulo);
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private static void modificarArticulo() throws ArticuloNoEncontradoException {
        articulos.listarArticulos();
        Articulo articulo = articulos.buscarArticuloPorCodigo(Validaciones.positivo(sc, "Código a modificar: "));
        System.out.println("Seleccionado: " + articulo);
        System.out.println("1) Nombre");
        System.out.println("2) Precio");
        System.out.println("3) Stock");
        System.out.println("4) Categoría");
        System.out.println("5) Dato específico del subtipo");
        System.out.println("0) Cancelar");

        int opcion = Validaciones.entero(sc, "Opción: ");
        switch (opcion) {
            case 1 -> articulo.setNombre(Validaciones.texto(sc, "Nuevo nombre: "));
            case 2 -> articulo.setPrecio(Validaciones.decimal(sc, "Nuevo precio: ", false));
            case 3 -> articulo.setStock(Validaciones.noNegativo(sc, "Nuevo stock: "));
            case 4 -> articulo.setCategoria(pedirCategoriaExistente());
            case 5 -> modificarDatoEspecifico(articulo);
            case 0 -> { return; }
            default -> {
                System.out.println("Opción inválida.");
                return;
            }
        }
        System.out.println("Artículo actualizado: " + articulo);
    }

    private static void modificarDatoEspecifico(Articulo articulo) {
        // Este bloque usa instanceof y casting de manera explícita, como se trabaja en el material.
        if (articulo instanceof ArticuloElectronico) {
            ArticuloElectronico electronico = (ArticuloElectronico) articulo;
            electronico.setGarantiaMeses(Validaciones.noNegativo(sc, "Nueva garantía en meses: "));
        } else if (articulo instanceof ArticuloAlimenticio) {
            ArticuloAlimenticio alimenticio = (ArticuloAlimenticio) articulo;
            alimenticio.setDiasParaVencimiento(
                    Validaciones.noNegativo(sc, "Nuevos días para vencimiento: "));
        }
    }

    private static void eliminarArticulo() throws ArticuloNoEncontradoException {
        articulos.listarArticulos();
        int codigo = Validaciones.positivo(sc, "Código a eliminar: ");
        Articulo articulo = articulos.buscarArticuloPorCodigo(codigo);
        System.out.println(articulo);
        if (Validaciones.confirmar(sc, "¿Eliminar? (S/N): ")) {
            articulos.eliminarPorCodigo(codigo);
            System.out.println("Artículo eliminado.");
        }
    }

    private static Categoria pedirCategoriaExistente() {
        while (true) {
            listarCategorias();
            int codigo = Validaciones.positivo(sc, "Código de categoría: ");
            try {
                return categorias.buscar(codigo);
            } catch (IllegalArgumentException e) {
                System.out.println("La categoría indicada no existe.");
            }
        }
    }

    private static boolean codigoArticuloExistente(int codigo) {
        try {
            articulos.buscarArticuloPorCodigo(codigo);
            return true;
        } catch (ArticuloNoEncontradoException e) {
            return false;
        }
    }

    private static void listarCategorias() {
        if (categorias.listar().isEmpty()) {
            System.out.println("No hay categorías.");
        } else {
            for (Categoria categoria : categorias.listar()) System.out.println(categoria);
        }
    }

    private static void menuPedidos() {
        int opcion;
        do {
            System.out.println("\n--- PEDIDOS ---");
            System.out.println("1) Crear pedido");
            System.out.println("2) Listar pedidos");
            System.out.println("0) Volver");
            opcion = Validaciones.entero(sc, "Opción: ");

            try {
                switch (opcion) {
                    case 1 -> crearPedido();
                    case 2 -> pedidos.listarPedidos();
                    case 0 -> { }
                    default -> System.out.println("Opción inválida.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (opcion != 0);
    }

    private static void crearPedido() throws Exception {
        if (articulos.getArticulos().isEmpty()) {
            System.out.println("No hay artículos para agregar a un pedido.");
            return;
        }

        articulos.listarArticulos();
        int cantidadLineas = Validaciones.positivo(sc, "Cantidad de líneas del pedido: ");
        List<LineaPedido> lineas = new ArrayList<>();

        for (int i = 0; i < cantidadLineas; i++) {
            System.out.println("Línea " + (i + 1) + " de " + cantidadLineas);
            Articulo articulo = articulos.buscarArticuloPorCodigo(
                    Validaciones.positivo(sc, "Código del artículo: "));
            int cantidad = Validaciones.positivo(sc, "Cantidad: ");
            lineas.add(new LineaPedido(articulo, cantidad));
        }

        double total = 0;
        for (LineaPedido linea : lineas) {
            System.out.println(linea);
            total += linea.calcularSubtotal();
        }
        System.out.printf("TOTAL: $%.2f%n", total);

        if (!Validaciones.confirmar(sc, "¿Confirmar pedido? (S/N): ")) {
            System.out.println("Pedido cancelado. No se modificó el stock.");
            return;
        }

        Pedido pedido = pedidos.crearPedido(lineas);
        System.out.printf("Pedido #%d confirmado. Total: $%.2f%n", pedido.getId(), pedido.calcularTotal());
    }

    private static void cargarCategoriasIniciales() {
        categorias.guardar(new Categoria(1, "Electrónica", "Artículos electrónicos"));
        categorias.guardar(new Categoria(2, "Periféricos", "Accesorios y periféricos"));
        categorias.guardar(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.guardar(new Categoria(4, "Limpieza", "Artículos de limpieza"));
    }
}
