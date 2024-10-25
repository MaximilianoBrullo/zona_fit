package zona_fit_conexion.conexion;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexion {
    public static Connection getConecion(){
        Connection connection = null;
        String baseDatos = "zona_fit_db";
        String url = "jdbc:mysql://localhost:3306/" + baseDatos;
        String usuario = "root";
        String password = "admin";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, usuario, password);
        }catch (Exception e){
            System.out.print("Error al conectar a la DB" + e.getMessage());
        }
        return connection;
    }

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
        if(conexion.getConecion() != null){
            System.out.println("Conexion exitosa!!");
        }else{
            System.out.println("No se puede conectar a la DB");
        }
    }
}
