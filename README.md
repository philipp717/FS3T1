# HorasPro - Plataforma de Servicios Tecnológicos

Aplicación web full-stack desarrollada con **Spring Boot 4**, **Java 21**, **Thymeleaf** y **MySQL**, como parte de la asignatura FS3T1.

Permite comprar horas profesionales de asesoría tecnológica (programación, bases de datos, Java, APIs REST, etc.), con carrito de compras, medios de pago simulados, panel de administración y gestión de usuarios.

---

## Requisitos previos

Antes de ejecutar el proyecto, asegúrate de tener instalado lo siguiente:

- **Visual Studio Code** — [Descargar](https://code.visualstudio.com/)
- **Extension Pack for Java** (VS Code) — incluye soporte para Java y Spring Boot
- **Java JDK 21** — [Descargar](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven** (incluido en el proyecto vía `mvnw`, no necesitas instalarlo)
- **XAMPP** — [Descargar](https://www.apachefriends.org/) (para correr MySQL localmente)
- **Git** — [Descargar](https://git-scm.com/)

---

## Pasos para ejecutar el proyecto en VS Code

### Paso 1 — Clonar el repositorio

Abre una terminal y ejecuta:

```bash
git clone https://github.com/philipp717/FS3T1.git
```

Luego abre la carpeta del proyecto en VS Code:
- Ve a **File → Open Folder** y selecciona la carpeta `fs3t1`

---

### Paso 2 — Iniciar MySQL con XAMPP

1. Abre el **XAMPP Control Panel**
2. Haz clic en **Start** junto a **MySQL**
3. Una vez iniciado, abre el navegador y ve a `http://localhost/phpmyadmin`
4. Crea la base de datos ejecutando:

```sql
CREATE DATABASE fs3t1;
```

> El schema de tablas y los datos iniciales se crean automáticamente al arrancar la aplicación.

---

### Paso 3 — Verificar Java 21

Asegúrate de tener Java 21 configurado. Puedes verificarlo en la terminal integrada de VS Code (**Terminal → New Terminal**):

```powershell
java -version
```

Debe mostrar `java version "21.x.x"`. Si no, configura `JAVA_HOME`:

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.10"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

---

### Paso 4 — Ejecutar la aplicación

En la terminal integrada de VS Code, desde la carpeta `fs3t1`:

```powershell
.\mvnw.cmd spring-boot:run
```

Espera hasta ver el mensaje:

```
Started fs3t1Application in X.XXX seconds
```

---

### Paso 5 — Abrir en el navegador

La aplicación estará disponible en: **http://localhost:8080**

---

## Credenciales de prueba

| Rol | Email | Contraseña |
|-----|-------|-----------|
| Administrador | `admin@horaspro.cl` | `admin123` |
| Usuario | `usuario@test.cl` | `user123` |

---

## Páginas de la aplicación

| Ruta | Descripción |
|------|-------------|
| `/` | Catálogo de servicios |
| `/carrito` | Carrito de compras |
| `/checkout` | Resumen y elección de medio de pago |
| `/pago-exitoso` | Confirmación de pago |
| `/login` | Inicio de sesión |
| `/registro` | Registro de usuario |
| `/mi-cuenta` | Historial de órdenes del usuario |
| `/admin` | Panel de administración (solo ADMIN) |
| `/admin/servicios` | Gestión de servicios (solo ADMIN) |

---

## Catálogo de servicios disponibles

| # | Servicio | Precio | Duración |
|---|----------|--------|----------|
| 1 | Hora de programacion | $15.000 | 60 min |
| 2 | Asesoría base de datos | $20.000 | 60 min |
| 3 | Clase de Java | $18.000 | 60 min |
| 4 | Introducción a Spring Boot | $16.000 | 60 min |
| 5 | Diseño de APIs REST | $22.000 | 60 min |
| 6 | Fundamentos de Git y GitHub | $12.000 | 60 min |
| 7 | Seguridad en aplicaciones web | $25.000 | 60 min |

---

## Estructura del proyecto

```
src/main/java/com/example/fs3t1/
├── fs3t1Application.java
├── model/
│   ├── Producto.java              # Entidad → tabla servicios
│   ├── Orden.java                 # Entidad → tabla orden
│   ├── OrdenItem.java             # Entidad → tabla orden_detalle
│   ├── Usuario.java               # Entidad → tabla usuarios
│   └── CarritoItem.java           # Objeto de sesión (no entidad)
├── repository/
│   ├── ProductoRepository.java
│   ├── OrdenRepository.java
│   └── UsuarioRepository.java
├── service/
│   ├── ProductoService.java
│   ├── CarritoService.java
│   └── UsuarioService.java
├── controller/
│   ├── ProductoController.java    # REST /productos
│   ├── UsuarioController.java     # REST /usuarios
│   ├── OrdenController.java       # REST /ordenes
│   ├── CarritoController.java     # Web /carrito
│   ├── WebController.java         # Web páginas principales
│   ├── LoginController.java       # Web /login /registro
│   ├── AdminController.java       # Web /admin
│   └── PagoController.java        # Web /pago
├── interceptor/
│   └── AuthInterceptor.java       # Protege rutas /admin y /mi-cuenta
└── config/
    └── WebMvcConfig.java
src/main/resources/
├── application.properties
├── data.sql                       # Datos iniciales
├── static/
│   ├── css/styles.css
│   ├── js/main.js
│   └── img/
└── templates/
    ├── index.html
    ├── carrito.html
    ├── checkout.html
    ├── pago-exitoso.html
    ├── pago-error.html
    ├── login.html
    ├── registro.html
    ├── mi-cuenta.html
    └── admin/
        ├── index.html
        └── servicios.html
```

---

## Tecnologías utilizadas

- **Spring Boot 4.0.3**
- **Spring Data JPA** + **Hibernate**
- **Spring Web MVC** + **Thymeleaf**
- **MySQL** (vía XAMPP)
- **Lombok**
- **Java 21**

---

## Repositorio

[https://github.com/philipp717/FS3T1](https://github.com/philipp717/FS3T1)
