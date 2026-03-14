import java.util.Scanner;

// Esta es la clase principal que VS Code busca para ejecutar
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        // Creamos las dos pilas manuales. 
        // La 'principal' es para los pedidos que estamos haciendo.
        // La 'secundaria' es como una papelera temporal para lo que borramos.
        PilaManual principal = new PilaManual(); 
        PilaManual secundaria = new PilaManual(); 
        
        int opcion = -1;

        System.out.println("=== PIZZA-TRACK (IU DIGITAL) ===");

        while (opcion != 0) {
            System.out.println("\n--- MENU ---");
            System.out.println("1. Registrar Pizza");
            System.out.println("2. Deshacer (Undo)");
            System.out.println("3. Rehacer (Redo)");
            System.out.println("4. Mostrar Actual");
            System.out.println("0. Salir");
            System.out.print("Opcion: ");
            
            try {
                opcion = Integer.parseInt(sc.nextLine());
            } catch (Exception e) {
                System.out.println("Por favor, ingrese un numero.");
                continue;
            }

            switch (opcion) {
                case 1:
                    System.out.print("Nombre pizza: ");
                    String nombre = sc.nextLine();
                    String[] ingredientes = new String[3];
                    for (int i = 0; i < 3; i++) {
                        System.out.print("Ingrediente " + (i + 1) + ": ");
                        ingredientes[i] = sc.nextLine();
                    }
                    // Guardamos la pizza nueva en la pila de arriba
                    principal.push(new Pizza(nombre, ingredientes));
                    
                    // Si el usuario mete una pizza nueva, ya no puede "rehacer" lo anterior, 
                    // por eso vaciamos la pila secundaria.
                    secundaria = new PilaManual(); 
                    System.out.println(">> Pedido guardado.");
                    break;

                case 2:
                    // Si hay pizzas para borrar:
                    if (!principal.isEmpty()) {
                        // Sacamos la ultima pizza de la principal y la pasamos a la secundaria
                        // por si el usuario se arrepiente y quiere darle a "Rehacer".
                        Pizza eliminada = principal.pop();
                        secundaria.push(eliminada);
                        System.out.println(">> Deshecho: " + eliminada);
                    } else {
                        System.out.println(">> Nada que deshacer.");
                    }
                    break;

                case 3:
                    // Si hay algo en la "papelera" (secundaria):
                    if (!secundaria.isEmpty()) {
                        // Rescatamos la pizza de la secundaria y la devolvemos a la principal.
                        Pizza recuperada = secundaria.pop();
                        principal.push(recuperada);
                        System.out.println(">> Rehecho: " + recuperada);
                    } else {
                        System.out.println(">> Nada que rehacer.");
                    }
                    break;

                case 4:
                    // Solo miramos la pizza que esta arriba sin quitarla de la lista.
                    Pizza actual = principal.peek();
                    if (actual != null) {
                        System.out.println(">> Actual: " + actual);
                    } else {
                        System.out.println(">> Sin pedidos.");
                    }
                    break;
            }
        }
        sc.close();
        System.out.println("Programa finalizado.");
    }
}

// --- CLASE PIZZA (Modelo de datos) ---
class Pizza {
    private String nombre;
    private String[] ingredientes;

    public Pizza(String nombre, String[] ingredientes) {
        this.nombre = nombre;
        this.ingredientes = ingredientes;
    }

    @Override
    public String toString() {
        return "Pizza: " + nombre + " [" + ingredientes[0] + ", " + ingredientes[1] + ", " + ingredientes[2] + "]";
    }
}

// --- CLASE PILA (Implementacion Manual con Nodos) ---
class PilaManual {
    // El Nodo es como un "vagon" que guarda la pizza y sabe quien es el vagon de abajo.
    private class Nodo {
        Pizza pizza;
        Nodo siguiente; // Este es el puntero que conecta con la pizza de abajo
        public Nodo(Pizza pizza) {
            this.pizza = pizza;
            this.siguiente = null;
        }
    }

    // El 'tope' es la variable que siempre nos dice cual es la pizza de arriba.
    private Nodo tope = null;

    // Metodo para agregar (Push)
    public void push(Pizza p) {
        Nodo nuevo = new Nodo(p); // Creamos el vagon nuevo
        
        // El vagon nuevo "se agarra" del que estaba antes en el tope.
        // Asi no perdemos la conexion con el resto de la pila.
        nuevo.siguiente = tope; 
        
        // Ahora el tope es el vagon que acabamos de meter.
        tope = nuevo;
    }

    // Metodo para sacar (Pop)
    public Pizza pop() {
        if (isEmpty()) return null;
        
        // Guardamos la pizza de arriba para poder devolverla al menu.
        Pizza p = tope.pizza;
        
        // El tope "salta" al siguiente nodo que esta abajo.
        // Con esto, la pizza que estaba arriba queda fuera de la lista.
        tope = tope.siguiente; 
        return p;
    }

    // Metodo para ver el de arriba (Peek)
    public Pizza peek() {
        // Solo devolvemos la pizza del tope sin mover ningun puntero.
        return (tope != null) ? tope.pizza : null;
    }

    // Metodo para saber si esta vacio
    public boolean isEmpty() {
        // Si el tope no apunta a nada, es que no hay pizzas.
        return tope == null;
    }
}
