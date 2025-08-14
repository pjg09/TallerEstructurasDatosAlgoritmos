public class Post {

    private String contenido;

    public Post(String mensaje) {
        this.contenido = mensaje;
    }

    @Override
    public String toString() {
        return contenido;
    }

}
