package zona_fit.presentacion;


import zona_fit.datos.ClienteDAO;
import zona_fit.datos.InterfaceClienteDAO;
import zona_fit.dominio.Cliente;

import java.util.Scanner;

public class ZonaFitApp {
    public static void main(String[] args) {
        zonaFitApp();
    }

    public static void zonaFitApp() {
        boolean salir = false;
        Scanner consola = new Scanner(System.in);
        InterfaceClienteDAO clienteDAO = new ClienteDAO();
        while (!salir) {
            try{
                mostrarMenu(consola);
                //salir = ejecutarOpciones(consola, clienteDAO);
            }catch (Exception e) {
                System.out.println("Error al ejecutar opciones: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static int mostrarMenu(Scanner consola) {
        System.out.print("""
                        ***Zona Fit***'
                        1. Listar Clientes
                        2. Agregar Cliente
                        3. Modificar Cliente
                        4. Eliminar Cliente
                        5. Buscar Cliente
                        6. Salir
                        Elija una opcion:
                        """);
        return Integer.parseInt(consola.nextLine());
    }

    private static boolean ejecutarOpciones(Scanner consola, int opcion,
                                            InterfaceClienteDAO clienteDAO){
        boolean salir = false;
        switch (opcion) {
            case 1:
                System.out.println("--- Listado de clientes ---");
                for(Cliente cliente : clienteDAO.listarClientes()){
                    System.out.println(cliente);
                }

            case 2:
                System.out.println("--- Agregar Cliente ---");
                Cliente cliente = new Cliente();
                System.out.print("Ingrese el nombre del cliente: ");
                cliente.setNombre(consola.nextLine());
                System.out.print("Ingrese el apellido del cliente: ");
                cliente.setApellido(consola.nextLine());
                clienteDAO.agregarCliente(cliente);
        }
        return salir;
    }
}
