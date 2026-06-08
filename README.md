# Diplomatura-UTN: API Fintech - Gestión de Clientes y Cuentas

## 📘 Descripción del Proyecto
Este proyecto consiste en el desarrollo de una **API REST** orientada a la simulación de un sistema básico Fintech, construida sobre **Java 21** y el framework **Spring Boot**. La aplicación permite la gestión integral de cuentas y clientes, implementando un diseño de dominio basado en Programación Orientada a Objetos (POO) mediante el uso de herencia y polimorfismo para modelar de forma flexible distintos tipos de clientes (Personas Físicas y Empresas).

Asimismo, el sistema incorpora **lógica defensiva y manejo de excepciones customizada**, persistencia de datos relacional en **MySQL** e integración autónoma con el servicio externo de **DolarAPI** para el cálculo dinámico y exposición de balances pesificados utilizando la cotización de compra del dólar MEP.

---

## 🚀 Tecnologías Utilizadas
* **Java 21** (Versión LTS con soporte moderno de rendimiento)
* **Spring Boot 3.x** (Spring Web, Spring Data JPA)
* **MySQL Database** (Motor de persistencia relacional)
* **Lombok** (Optimización de código repetitivo mediante anotaciones)
* **JUnit 5 & Mockito** (Suite de testing unitario y simulación de componentes)
* **Springdoc-openapi (Swagger)** (Documentación interactiva de endpoints)

---

## 🛠️ Arquitectura y Estructura de Capas
La aplicación adopta las buenas prácticas de diseño de software dividiendo las responsabilidades en una arquitectura limpia y modularizada por capas:

* **`controller`**: Exposición de los endpoints REST y control de peticiones HTTP.
* **`service`**: Capa de negocio centralizada donde residen los algoritmos de cálculo, la validación de reglas de negocio y el consumo de servicios.
* **`repository`**: Interfaces `JpaRepository` encargadas de la abstracción de consultas y operaciones CRUD con la base de datos MySQL.
* **`model` / `entity`**: Modelado del dominio aplicando herencia (Clase madre `Cliente` y subclases `ClientePersonaFisica` y `ClienteEmpresa`) y la entidad `Account` con relaciones mapeadas.
* **`exception`**: Centralización y mapeo de excepciones personalizadas para el control defensivo del flujo (`ClientNotFoundException` y `AccountNotFoundException`).
* **`dolar`**: Componentes específicos para el consumo e interpretación de la API externa de cotizaciones.

---

## ⚙️ Requisitos Previos e Instalación

### 1. Clonar el repositorio
```bash
git clone [https://github.com/melurodriguez/Diplomatura-UTN.git](https://github.com/melurodriguez/Diplomatura-UTN.git)
cd Diplomatura-UTN
2. Configurar la Base de Datos (MySQL)
Crea una base de datos en tu entorno local llamada fintech_db. Luego, configura tus credenciales de acceso dentro del archivo src/main/resources/application.properties:

Properties
spring.datasource.url=jdbc:mysql://localhost:3307/fintech_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=mysqlcontainer
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
3. Compilar y Ejecutar la Aplicación
Bash
./mvnw clean spring-boot:run
🧪 Ejecución de Tests Unitarios
Para validar rigurosamente la lógica de negocio, el mapeo de la herencia polimórfica y la robustez del manejo de excepciones ante fallos o llamadas a la API externa:

Bash
./mvnw test
📌 Endpoints de la API (Catálogo de Rutas)
Clientes (/clientes)
GET /clientes/ - Obtener el listado completo de clientes persistidos.

GET /clientes/{id} - Obtener el detalle de un cliente específico por su ID.

POST /clientes/ - Registrar un nuevo cliente (recibe polimórficamente Persona Física o Empresa en el @RequestBody).

PUT /clientes/{id} - Actualizar los datos de un cliente existente de manera condicional.

DELETE /clientes/{id} - Eliminar un cliente del sistema.

Cuentas (/accounts)
GET /accounts - Listar todas las cuentas registradas en el sistema.

GET /accounts/{accountId} - Obtener el detalle de una cuenta por su identificador exclusivo.

POST /accounts - Crear una cuenta asociada obligatoriamente a un cliente existente (Validación defensiva IllegalArgumentException incluida).

PUT /accounts/{accountId} - Actualizar los datos generales de una cuenta.

DELETE /accounts/{accountId} - Eliminar una cuenta del sistema.

Requerimiento Especial: GET /accounts/{accountId}/pesificado - Consume de manera dinámica DolarAPI, extrae el valor de "compra" correspondiente al Dólar MEP (casa: bolsa) y expone el saldo equivalente en pesos argentinos si la cuenta opera bajo la moneda Currency.USD.

📄 Documentación Interactive (Swagger UI)
Una vez que la aplicación se encuentre corriendo localmente, se puede acceder a la interfaz interactiva de Swagger para testear cada endpoint de forma visual desde el navegador web en la siguiente dirección:

http://localhost:8080/swagger-ui/index.html
