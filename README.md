# TechLab - Gestión de artículos, categorías y pedidos

**Desarrollador:** Pablo De Lillo

Aplicación de consola desarrollada en Java 

## Requisitos

Java JDK 17 o superior. No requiere librerías externas ni base de datos. Los datos cargados durante la ejecución se mantienen en memoria.

## Compilar y ejecutar

Desde la carpeta `TechLab`:

```bash
mkdir -p out
javac -encoding UTF-8 -d out $(find src -name "*.java")
java -cp out com.techlab.app.Main
```

En Windows PowerShell:

```powershell
New-Item -ItemType Directory -Force out | Out-Null
Get-ChildItem -Recurse src -Filter *.java | ForEach-Object FullName | Set-Content fuentes.txt
javac -encoding UTF-8 -d out '@fuentes.txt'
java -cp out com.techlab.app.Main
Remove-Item fuentes.txt
```

## Uso

Al iniciar la aplicación se cargan cuatro categorías base. Los artículos se agregan desde el menú y su código se ingresa manualmente, por lo que el sistema valida que no esté repetido. No se puede eliminar una categoría que tenga artículos asociados. En los pedidos, el sistema valida el stock total antes de descontar unidades.
