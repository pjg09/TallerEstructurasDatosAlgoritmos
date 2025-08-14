
public class Muro {

    // Atributo
    private PilaConLista<Post> pila;

    // Constructor
    public Muro() {

        pila = new PilaConLista<>();

    }

    // Métodos
    public void push(Post post) {

        pila.push(post);

    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (Post post : pila) {

            sb.append(post.toString()).append("\n");

        }

        return sb.toString();

    }

}