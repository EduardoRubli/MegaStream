# MegaStream

## Descripción
MegaStream es un proyecto Maven con SpringBoot.
Usa Spring Security (con roles ADMIN y CLIENTE).
Dependencias en pom.xml.

## Estructura del Proyecto
- `src/main/resources/templates/`: interfaz HTML. 
- `src/main/java/es/fempa/acd/MegaStream/Controllers/`: controladores.

## Base de Datos.
Ya no se utiliza H2, sino MySQL.
Nombre de la base de datos: MegaStream
Usuario de la base de datos: root
Contraseña de la base de datos: 123456

## ¿Cómo Ejecutar el Script SQL?
1. CREATE DATABASE MegaStream;
2. mysql -u root -p MegaStream < C:\ruta\db.sql
3. USE MegaStream;
