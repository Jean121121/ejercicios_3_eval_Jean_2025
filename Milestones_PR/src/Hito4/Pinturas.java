package Hito4;

import java.util.Date;

public class Pinturas {
        private int idPintura;
        private String titulo;
        private String archivo;
        private Date fecha;
        private int visitas;

        public Pinturas(int id, String titulo, String archivo, Date fecha, int visitas) {
            this.idPintura = id;
            this.titulo = titulo;
            this.archivo = archivo;
            this.fecha = fecha;
            this.visitas = visitas;
        }

        public int getIdPintura() {
            return idPintura;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getArchivo() {
            return archivo;
        }

        public Date getFecha() {
            return fecha;
        }

        public int getVisitas() {
            return visitas;
        }

        @Override
        public String toString() {
            return titulo;
        }
    }


