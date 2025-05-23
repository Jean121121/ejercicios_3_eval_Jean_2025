package Hito4;

import java.sql.Connection;
import java.util.List;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PintoresBD {
        private Connection conexion;

        public PintoresBD(Connection conexion) {
            this.conexion = conexion;
        }

        public List<Pintor> obtenerTodos() {
            List<Pintor> lista = new ArrayList<>();
            String sql = "SELECT * FROM Pintores";

            try (PreparedStatement ps = conexion.prepareStatement(sql);
                 ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {
                    int id = rs.getInt("IdPintor");
                    String nombre = rs.getString("Nombre");
                    boolean premiado = rs.getBoolean("Premiado");

                    lista.add(new Pintor(id, nombre, premiado));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista;
        }
    }


