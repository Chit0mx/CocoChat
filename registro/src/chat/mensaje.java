package chat;

public class mensaje {
        int idUsuario;
        String nombre;
        String mensaje;
        
        
        public void setIdUsuario(int idUsuario) {
            this.idUsuario = idUsuario;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

        public void setMensaje(String mensaje) {
            this.mensaje = mensaje;
        }

        public int getIdUsuario() {
            return idUsuario;
        }

        public String getNombre() {
            return nombre;
        }

        public String getMensaje() {
            return mensaje;
        }
      
        
        public mensaje (int idUsuario, String nombre, String mensaje) {
            this.idUsuario = idUsuario;
            this.nombre = nombre;
            this.mensaje = mensaje;
        }

}
