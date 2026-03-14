# Pizza-Track: Sistema de Gestión de Pedidos

## Datos del Estudiante
* **Nombre:** ANA MELISSA SANCHEZ RAMIREZ
* **Institución:** IU Digital de Antioquia
* **Programa:** Ingeniería de Software
* **Semestre:** Segundo Semestre

---
##  1. Comprensión Teórica

### ¿Qué es una Pila (Stack)?
Una pila es una estructura de datos de tipo **LIFO** (Last In, First Out), lo que significa que el último elemento en entrar es el primero en salir. Es ideal para procesos donde necesitamos revertir acciones.

### Aplicación al Sistema Undo/Redo
Para este simulador, utilizamos **dos pilas manuales**:
1. **Pila Principal (Undo):** Guarda cada pizza que registramos. Al darle a "Deshacer", sacamos el tope de esta pila.
2. **Pila Secundaria (Redo):** Recibe las pizzas que sacamos de la principal. Si el usuario quiere "Rehacer", simplemente devolvemos la pizza de esta pila a la principal.

---

## 2. Detalles de Implementación (Lógica de Punteros)

El sistema fue desarrollado desde cero utilizando **Listas Ligadas** para cumplir con la restricción de no usar librerías automáticas de Java.

* **El Nodo:** Cada pedido es un "Nodo" que guarda la información de la pizza y un **puntero (referencia)** al siguiente elemento.
* **Método Push:** Creamos un nodo y conectamos su puntero al `tope` actual. Luego, el `tope` pasa a ser el nuevo nodo.
* **Método Pop:** El puntero del `tope` salta al siguiente nodo en la lista (`tope.siguiente`), desconectando el elemento superior y reduciendo la pila.

---

## 3. Sustentación Individual (Video)
Puedes ver la explicación del código y la prueba de funcionamiento en el siguiente enlace:

👉 ** https://www.loom.com/share/47288d1d3dee4769a7d946d250dde8ee **

---
## 4. Guía de Uso
1. **Registrar:** Ingresa el nombre y 3 ingredientes.
2. **Deshacer:** Elimina la última pizza y la guarda en la papelera temporal (Pila Secundaria).
3. **Rehacer:** Recupera la pizza de la papelera y la pone de nuevo en producción.
4. **Mostrar Actual:** Permite ver qué pizza está en el tope de la pila sin borrarla.
