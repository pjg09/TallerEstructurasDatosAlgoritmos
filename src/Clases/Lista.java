import java.util.Iterator;
import java.util.NoSuchElementException;

public class Lista implements Iterable<Usuario>{

    private Nodo primero;
    private int n;

    private class Nodo {
        Usuario item;
        Nodo siguiente;
    }

    public Lista() {
        primero = null;
        n = 0;
    }

    public void add(Usuario seguidor) {
        Nodo anterior = primero;
        primero = new Nodo();
        primero.item = seguidor;
        primero.siguiente = anterior;
        n++;
    }

    public boolean contains(Usuario usuario) {
        for (Nodo current = primero; current != null; current = current.siguiente) {
            if (current.item.equals(usuario)) {
                return true;
            }
        }
        return false;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public Iterator<Usuario> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Usuario> {
        private Nodo current = primero;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Usuario next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Usuario item = current.item;
            current = current.siguiente;
            return item;
        }
    }

}