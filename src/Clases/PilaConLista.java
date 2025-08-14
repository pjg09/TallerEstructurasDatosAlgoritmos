import java.util.Iterator;
import java.util.NoSuchElementException;

public class PilaConLista<T> implements Iterable<T> {

    private Nodo primero;
    private int n;

    private class Nodo {
        T item;
        Nodo siguiente;
    }

    public PilaConLista() {
        primero = null;
        n = 0;
    }

    public void push(T s) {
        Nodo anterior = primero;
        primero = new Nodo();
        primero.item = s;
        primero.siguiente = anterior;
        n++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new NoSuchElementException("Pila vacía");
        }
        T item = primero.item;
        primero = primero.siguiente;
        n--;
        return item;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private Nodo current = primero;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T item = current.item;
            current = current.siguiente;
            return item;
        }
    }

}
