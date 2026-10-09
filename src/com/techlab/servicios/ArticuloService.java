/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.servicios;

import com.techlab.excepciones.ArticuloNoEncontradoException;
import com.techlab.productos.Articulo;
import com.techlab.repositorios.Repositorio;
import java.util.ArrayList;
import java.util.List;

public class ArticuloService {
    private final Repositorio<Articulo> repo = new Repositorio<>();

    public void agregarArticulo(Articulo articulo) {
        if (articulo == null) throw new IllegalArgumentException("Artículo nulo.");
        if (repo.existe(articulo.getCodigo())) {
            throw new IllegalArgumentException("Ya existe un artículo con código " + articulo.getCodigo() + ".");
        }
        repo.guardar(articulo);
    }

    public List<Articulo> getArticulos() { return repo.listar(); }

    public Articulo buscarArticuloPorCodigo(int codigo) throws ArticuloNoEncontradoException {
        return repo.buscar(codigo).orElseThrow(
                () -> new ArticuloNoEncontradoException("No existe un artículo con código " + codigo + "."));
    }

    public List<Articulo> buscarPorNombre(String nombre) throws ArticuloNoEncontradoException {
        ArrayList<Articulo> encontrados = new ArrayList<>();
        String buscado = nombre.trim().toLowerCase();
        for (Articulo articulo : repo.listar()) {
            if (articulo.getNombre().toLowerCase().contains(buscado)) encontrados.add(articulo);
        }
        if (encontrados.isEmpty()) throw new ArticuloNoEncontradoException("No se encontró el artículo: " + nombre);
        return encontrados;
    }

    public void eliminarPorCodigo(int codigo) throws ArticuloNoEncontradoException {
        buscarArticuloPorCodigo(codigo);
        repo.eliminar(codigo);
    }

    public void listarArticulos() {
        if (repo.listar().isEmpty()) {
            System.out.println("No hay artículos registrados.");
            return;
        }
        System.out.println("--- ARTÍCULOS ---");
        for (Articulo articulo : repo.listar()) System.out.println(articulo);
    }
}
