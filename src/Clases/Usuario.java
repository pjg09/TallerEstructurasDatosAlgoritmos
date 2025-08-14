public class Usuario {

    // Constantes
    private final byte longitudMinimaNombre = 3;

    // Atributos
    private String nombreUsuario;
    private Lista listaSeguidores;
    private Muro muro;

    // Constructor
    public Usuario(String nombreUsuario) {

        setNombre(nombreUsuario);
        this.listaSeguidores = new Lista();
        this.muro = new Muro();

    }

    // Accesores
    public String getNombre () {

        return nombreUsuario;

    }

    public void setNombre (String nombreUsuario) {

        if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {

            throw new IllegalArgumentException("Error: El nombre no puede estar vacío");

        } else if (nombreUsuario.startsWith(" ")) {

            throw new IllegalArgumentException("Error: El nombre no puede comenzar con un espacio en blanco");

        } else if (nombreUsuario.length() < longitudMinimaNombre) {

            throw new IllegalArgumentException("Error: El nombre de usuario debe contener más de " + longitudMinimaNombre + " caracteres");

        } else {

            this.nombreUsuario = nombreUsuario;

        }

    }

    // Métodos
    public String seguir(Usuario usuario) {
        usuario.listaSeguidores.add(this);
        return "Ahora sigues a " + usuario.nombreUsuario;
    }

    public String postear(String mensaje) {
        Post post = new Post(mensaje);
        for (Usuario seguidor : listaSeguidores) {
            seguidor.muro.push(post);
        }
        return "Post publicado: " + mensaje;
    }

    @Override
    public String toString() {
        return muro.toString();
    }

    @Override
    public boolean equals(Object objeto) {
        if (!(objeto instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) objeto;
        if (listaSeguidores.size() != other.listaSeguidores.size()) {
            return false;
        }
        for (Usuario u : listaSeguidores) {
            if (!other.listaSeguidores.contains(u)) {
                return false;
            }
        }
        for (Usuario u : other.listaSeguidores) {
            if (!listaSeguidores.contains(u)) {
                return false;
            }
        }
        return true;
    }

}