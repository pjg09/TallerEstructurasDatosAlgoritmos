import java.util.Iterator;
import java.util.NoSuchElementException;

public class Usuario {

    private String nombreUsuario;
    private Lista listaSeguidores;
    private Muro muro;

    public Usuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.listaSeguidores = new Lista();
        this.muro = new Muro();
    }

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
