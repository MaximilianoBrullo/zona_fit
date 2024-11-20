package zona_fit.datos;

import zona_fit.conexion.Conexion;
import zona_fit.dominio.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ClienteDAO implements InterfaceClienteDAO {

    @Override
    public List<Cliente> listarClientes() {
        List<Cliente> clientes = new ArrayList<>();
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConecion();
        String sql = "SELECT * FROM cliente ORDER BY id";
        try{
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt("id"));
                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresial"));
                clientes.add(cliente);
            }
        } catch (Exception e) {
            System.out.println("Error al retornar clientes: " + e.getMessage());
        }finally{
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return clientes;
    }

    @Override
    public boolean buscarCLiente(Cliente cliente) {
        PreparedStatement ps;
        ResultSet rs;
        Connection con = Conexion.getConecion();
        String sql = "SELECT * FROM cliente WHERE id = ?";
        try{
            ps = con.prepareStatement(sql);
            ps.setInt(1, cliente.getIdCliente());
            rs = ps.executeQuery();
            if(rs.next()){

                cliente.setNombre(rs.getString("nombre"));
                cliente.setApellido(rs.getString("apellido"));
                cliente.setMembresia(rs.getInt("membresial"));
                return true;
            }
        }catch(Exception e){
            System.out.println("Error al buscar cliente por id: " + e.getMessage());
        }finally {
            try{
                con.close();
            } catch (Exception e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean agregarCliente(Cliente elcliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConecion();
        String sql = "INSERT INTO cliente (nombre, apellido, membresial) VALUES (?, ?, ?)";
        try{
            System.out.println("SQL: " + sql);
            ps = con.prepareStatement(sql);
            System.out.println("Datos a insertar: " + elcliente.getNombre() + ", " +
                    elcliente.getApellido() + ", " + getMembresia());
            ps.setString(1, elcliente.getNombre());
            ps.setString(2, elcliente.getApellido());
            ps.setInt(3, getMembresia());
            ps.execute();
            System.out.println("hasta aca todo bien");
            return true;
        } catch (Exception e) {
            System.out.println("Error al agregar cliente: " + e.getMessage());
            return false;
        } finally {
            try{
                con.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
    }

    @Override
    public boolean modificarCliente(Cliente cliente) {
        PreparedStatement ps;
        Connection con = Conexion.getConecion();
        String sql = "UPDATE cliente SET nombre=?, apellido=?, membresial=? WHERE id=?";
        try{
            ps = con.prepareStatement(sql);
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getApellido());
            ps.setInt(3, getMembresia());
            ps.setInt(4, cliente.getIdCliente());
            ps.execute();
        } catch (Exception e) {
            System.out.println("Error al modificar cliente: " + e.getMessage());
        }finally {
            try{
                con.close();
            }catch (SQLException e) {
                System.out.println("Error al cerrar la conexion: " + e.getMessage());
            }
        }
        return false;
    }

    @Override
    public boolean eliminarCliente(Cliente cliente) {
        return false;
    }

    private int getMembresia() {
        int membresia = 0;
        boolean valido;
        do {
            valido = true;
            Random r = new Random();
            membresia = r.nextInt(99) + 1;
            for (Cliente cliente : listarClientes()) {
                if (membresia == cliente.getMembresia()) {
                    valido = false;
                }
            }
        } while (!valido);
        return membresia;
    }


    public static void main(String[] args) {
        //Listar clientes
        /*System.out.println("+++LISTAR CLIENTES+++");
        InterfaceClienteDAO clienteDAO = new ClienteDAO();
        for(Cliente cliente1 : clienteDAO.listarClientes()){
            System.out.println(cliente1);
        }
        System.out.println("+++AGREGAR CLIENTES+++");
        Cliente cliente = new Cliente("Ruben", "Diaz");
        System.out.println("cliente a agregar: " + cliente.toString());
        boolean agregado = clienteDAO.agregarCliente(cliente);

        if(agregado){
            System.out.println("+++buscar CLIENTES+++");
            for(Cliente cliente1 : clienteDAO.listarClientes()){
                System.out.println(cliente1);
            }
        }else{
            System.out.print("CLIENTE NO AGREGADO!!!!!!");
        }*/

        //modificar cliente
        InterfaceClienteDAO clienteDAO = new ClienteDAO();
        Cliente cliente = new Cliente(4, "Jose", "Castillo");
        boolean modificado = clienteDAO.modificarCliente(cliente);
        if(modificado){
            System.out.println("El cliente se ha actualizado correctamente");
        }else{
            System.out.println("El cliente no se ha actualizado correctamente");
        }
    }
}
