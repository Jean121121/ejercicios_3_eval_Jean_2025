package Hito4;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

    public class ConexionDB {
        private static final String URL = "jdbc:mysql://localhost:3306/GaleriaDeArte";
        private static final String USUARIO = "root";         // Cámbialo si usas otro usuario
        private static final String CLAVE = "";               // Pon tu contraseña aquí

        public static Connection conectar() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                return DriverManager.getConnection(URL, USUARIO, CLAVE);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
                return null;
            }
        }
    }


