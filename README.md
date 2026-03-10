# Microservicio de Productos - FS3T1

Prototipo de microservicio REST desarrollado con **Spring Boot 4** y **Java 21**, como parte de la actividad de patrones de arquitectura de microservicios.

## Descripción

Este microservicio implementa el **Servicio de Productos** de una plataforma de e-commerce de servicios tecnológicos. Expone una API REST para listar y agregar productos (servicios por hora), usando una base de datos H2 en memoria.

### Productos de ejemplo

| Servicio                  | Precio   | Duración |
|---------------------------|----------|----------|
| Hora de programacion      | $15.000  | 1 hora   |
| Asesoria base de datos    | $20.000  | 1 hora   |
| Clase de Java             | $18.000  | 1 hora   |

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
