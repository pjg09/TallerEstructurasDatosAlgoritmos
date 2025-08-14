import java.util.Iterator;
import java.util.NoSuchElementException;
public class Muro {

    private PilaConLista<Post> pila;

    public Muro() {
        pila = new PilaConLista<>();
    }

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
