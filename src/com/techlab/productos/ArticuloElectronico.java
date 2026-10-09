/**
 * Proyecto: TechLab - Sistema de gestión de artículos, categorías y pedidos
 * Desarrollador: Pablo De Lillo
 */
package com.techlab.productos;

public class ArticuloElectronico extends Articulo {
    private int garantiaMeses;

    public ArticuloElectronico(int codigo, String nombre, double precio, int stock,
                               Categoria categoria, int garantiaMeses) {
        super(codigo, nombre, precio, stock, categoria);
        setGarantiaMeses(garantiaMeses);
    }

    public int getGarantiaMeses() { return garantiaMeses; }

    public void setGarantiaMeses(int garantiaMeses) {
        if (garantiaMeses < 0) throw new IllegalArgumentException("La garantía no puede ser negativa.");
        this.garantiaMeses = garantiaMeses;
    }

    public String nroTelMesaDeAyudaParaReclamos() {
        return "0800-TECHLAB";
    }

    @Override
    public String getTipoArticulo() { return "Electrónico"; }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo electrónico]";
    }
}
