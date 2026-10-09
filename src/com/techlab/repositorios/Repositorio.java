/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 *
 * Repositorio genérico en memoria. Uso ArrayList para mantener el contenido visto en clase.
 */
package com.techlab.repositorios;

import com.techlab.interfaces.Identificable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Repositorio<T extends Identificable> {
    private final ArrayList<T> datos = new ArrayList<>();

    public void guardar(T elemento) {
        if (elemento == null) throw new IllegalArgumentException("Elemento nulo.");
        if (existe(elemento.getId())) throw new IllegalArgumentException("Ya existe un elemento con ID/código " + elemento.getId() + ".");
        datos.add(elemento);
    }

    public Optional<T> buscar(int id) {
        for (T elemento : datos) if (elemento.getId() == id) return Optional.of(elemento);
        return Optional.empty();
    }

    public List<T> listar() {
        return Collections.unmodifiableList(new ArrayList<>(datos));
    }

    public boolean eliminar(int id) {
        return datos.removeIf(elemento -> elemento.getId() == id);
    }

    public boolean existe(int id) {
        return buscar(id).isPresent();
    }
}
