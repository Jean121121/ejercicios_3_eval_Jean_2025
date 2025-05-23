package Hito4;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PinturasBD {

        private Connection conexion;

        public PinturasBD(Connection conexion) {
            this.conexion = conexion;
        }

        public List<Pinturas> obtenerPorPintorYFecha(int idPintor, java.util.Date fecha) {
            List<Pinturas> lista = new ArrayList<>();
            String sql;

            if (fecha != null) {
                sql = "SELECT * FROM Pinturas WHERE IdPintor = ? AND Fecha >= ?";
            } else {
                sql = "SELECT * FROM Pinturas WHERE IdPintor = ?";
            }

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, idPintor);
                if (fecha != null) {
                    ps.setDate(2, new java.sql.Date(fecha.getTime()));
                }

                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        int id = rs.getInt("IdPintura");
                        String titulo = rs.getString("Titulo");
                        String archivo = rs.getString("Archivo");
                        Date fechaSQL = rs.getDate("Fecha");
                        int visitas = rs.getInt("Visitas");

                        lista.add(new Pinturas(id, titulo, archivo, fechaSQL, visitas));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return lista;
        }

        public void incrementarVisitas(Pinturas p) {
            String sql = "UPDATE Pinturas SET Visitas = Visitas + 1 WHERE IdPintura = ?";

            try (PreparedStatement ps = conexion.prepareStatement(sql)) {
                ps.setInt(1, p.getIdPintura());
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


