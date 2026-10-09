# TechLab - Preentrega Java

**Desarrollador:** Pablo De Lillo  
**Lenguaje:** Java (JDK 17 o superior)

Este es mi proyecto de preentrega para gestionar productos y pedidos desde la consola. Lo organicé en clases y paquetes para aplicar lo visto en las clases. Los comentarios del código explican las decisiones principales sin repetir lo que ya se entiende al leer Java.

## Ejecución

Desde la raíz del repositorio (Linux/macOS):

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.techlab.app.Main
```

En Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
$fuentes = Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName
javac -encoding UTF-8 -d out $fuentes
java -cp out com.techlab.app.Main
```

También se puede abrir la carpeta en IntelliJ IDEA o VS Code y ejecutar `com.techlab.app.Main`.

## Funcionalidades

Menú para agregar, listar, buscar, actualizar y eliminar productos; crear y listar pedidos. Los pedidos validan el stock **acumulado** por producto antes de confirmar y solo entonces descuentan existencias. Los importes de cada línea quedan registrados al precio vigente al crear el pedido. Los datos se guardan **en memoria** (se reinician al cerrar). Incluye tres productos de ejemplo.

## Organización

`src/com/techlab/productos`: modelos y contrato de descuento. `pedidos`: pedidos y líneas. `servicios`: reglas de negocio. `excepciones`: errores propios. `app`: menú.

