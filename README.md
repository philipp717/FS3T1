# HorasPro - Plataforma de Servicios Tecnológicos

Aplicación web full-stack desarrollada con **Spring Boot 4**, **Java 21**, **Thymeleaf** y **MySQL**, como parte de la asignatura FS3T1.

Permite comprar horas profesionales de asesoría tecnológica (programación, bases de datos, Java, APIs REST, etc.), con carrito de compras, medios de pago simulados, panel de administración y gestión de usuarios.

---

## Requisitos previos

- **Java JDK 21** — [Descargar](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven** (incluido vía `mvnw`)
- **XAMPP** (Apache + MySQL corriendo en el puerto 3306)
- **Git**

---

## Configuración de la base de datos

1. Abrir **phpMyAdmin** (`http://localhost/phpmyadmin`) o la consola MySQL
2. Crear la base de datos:

```sql
CREATE DATABASE fs3t1;
```

3. Importar el schema (o dejarlo vacío — Hibernate lo crea solo con `ddl-auto=update`)

Los datos iniciales (servicios y usuarios) se cargan automáticamente desde `data.sql` al arrancar la app.

---

## Pasos para ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/philipp717/FS3T1.git
cd FS3T1/fs3t1
```

### 2. Configurar JAVA_HOME (Windows PowerShell)

```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21.0.10"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

### 3. Ejecutar la aplicación

```powershell
.\mvnw.cmd spring-boot:run
```

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

## API REST — Endpoints para Postman

### Servicios (`/productos`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/productos` | Listar todos los servicios |
| GET | `/productos/{id}` | Obtener servicio por ID |
| POST | `/productos` | Crear servicio |
| PUT | `/productos/{id}` | Actualizar servicio |
| DELETE | `/productos/{id}` | Eliminar servicio |

**Body POST/PUT:**
```json
{
  "nombre": "Clase de Python",
  "descripcion": "Aprende Python desde cero con ejemplos prácticos.",
  "precio": 14000,
  "duracion": 60
}
```

---

### Usuarios (`/usuarios`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/usuarios` | Listar todos los usuarios |
| GET | `/usuarios/{id}` | Obtener usuario por ID |
| POST | `/usuarios` | Crear usuario |
| PUT | `/usuarios/{id}` | Actualizar usuario |
| DELETE | `/usuarios/{id}` | Eliminar usuario |

**Body POST/PUT:**
```json
{
  "nombre": "Juan Pérez",
  "email": "juan@ejemplo.cl",
  "password": "clave123",
  "rol": "USUARIO"
}
```

---

### Órdenes (`/ordenes`)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | `/ordenes` | Listar todas las órdenes (por fecha desc) |
| GET | `/ordenes/{id}` | Obtener orden por ID |
| GET | `/ordenes/usuario/{idUsuario}` | Órdenes de un usuario |
| POST | `/ordenes` | Crear orden |
| PUT | `/ordenes/{id}` | Actualizar estado/datos de orden |
| DELETE | `/ordenes/{id}` | Eliminar orden |

**Body POST:**
```json
{
  "idUsuario": 1,
  "estado": "PAGADA",
  "total": 33000,
  "metodoPago": "FLOW"
}
```

**Body PUT (actualizar estado):**
```json
{
  "estado": "PAGADA"
}
```

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


---

## Requisitos previos

- **Java JDK 21** — [Descargar](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven** (incluido vía `mvnw`)
- **Git**

---

## Pasos para ejecutar el proyecto

### 1. Clonar el repositorio

```bash
git clone https://github.com/philipp717/FS3T1.git
cd FS3T1
```

### 2. Configurar JAVA_HOME (si no está configurado)

**Windows (PowerShell):**
```powershell
$env:JAVA_HOME = "C:\Program Files\Java\jdk-21"
$env:PATH = "$env:JAVA_HOME\bin;$env:PATH"
```

**Linux / macOS:**
```bash
export JAVA_HOME=/usr/lib/jvm/java-21
export PATH=$JAVA_HOME/bin:$PATH
```

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

> En Windows usar: `.\mvnw.cmd spring-boot:run`

La aplicación estará disponible en: **http://localhost:8080**

---

## Endpoints disponibles

### GET /productos
Retorna la lista de todos los productos disponibles.

```bash
curl http://localhost:8080/productos
```

**Respuesta:**
```json
[
  { "id": 1, "nombre": "Hora de programacion", "precio": 15000, "duracion": "1 hora" },
  { "id": 2, "nombre": "Asesoria base de datos", "precio": 20000, "duracion": "1 hora" },
  { "id": 3, "nombre": "Clase de Java", "precio": 18000, "duracion": "1 hora" }
]
```

### POST /productos
Agrega un nuevo producto.

```bash
curl -X POST http://localhost:8080/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Clase de Python","precio":16000,"duracion":"1 hora"}'
```

**Respuesta (HTTP 201 Created):**
```json
{ "id": 4, "nombre": "Clase de Python", "precio": 16000, "duracion": "1 hora" }
```

---

## Consola H2 (base de datos en memoria)

Disponible en: **http://localhost:8080/h2-console**

| Campo    | Valor                    |
|----------|--------------------------|
| JDBC URL | `jdbc:h2:mem:productosdb` |
| Usuario  | `sa`                     |
| Contraseña | *(vacía)*              |

---

## Estructura del proyecto

```
src/main/java/com/example/fs3t1/
├── fs3t1Application.java          # Clase principal
├── model/
│   └── Producto.java              # Entidad JPA
├── repository/
│   └── ProductoRepository.java    # Acceso a datos
├── service/
│   └── ProductoService.java       # Lógica de negocio
└── controller/
    └── ProductoController.java    # Endpoints REST
```

---

## Tecnologías utilizadas

- Spring Boot 4.0.3
- Spring Data JPA
- Spring Web MVC
- H2 Database (en memoria)
- Lombok
- Java 21
