public class Post {

    // Constante
    private final short longitudMaximaPost = 1000;

    // Atributo
    private String contenido;

    // Constructor
    public Post(String mensaje) {

        this.contenido = mensaje;

    }

    // Accesor
    public String getContenido () {

        return contenido;

    }

    public void setContenido () {

        if (contenido == null || contenido.trim().isEmpty()) {

            throw new IllegalArgumentException("Error: El contenido del post no puede estar vacío");

        } else if (contenido.startsWith(" ")) {

            throw new IllegalArgumentException("Error: El contenido del post no puede comenzar con un espacio en blanco");

        } else if (contenido.length() < longitudMaximaPost) {

            throw new IllegalArgumentException("Error: El contenido del post no puede contener más de " + longitudMaximaPost + " caracteres");

        } else {

            this.contenido = contenido;

        }


    }

    // Método
    @Override
    public String toString() {

        return contenido;

    }

}