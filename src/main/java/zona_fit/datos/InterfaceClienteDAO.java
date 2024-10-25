package zona_fit.datos;
import zona_fit.dominio.Cliente;
import java.util.List;

public interface InterfaceClienteDAO {

    List<Cliente> listarClientes();
    boolean buscarCLientePorId(Integer id);
    boolean agregarCliente(Cliente cliente);
    boolean modificarCliente(Cliente cliente);
    boolean eliminarCliente(Cliente cliente);
}
