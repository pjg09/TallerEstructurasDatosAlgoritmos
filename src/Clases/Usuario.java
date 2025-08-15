public class Usuario {

    // Constante
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

    // Accesor
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

    public static void main(String[] args) {

        // Crear usuarios
        Usuario usuario1 = new Usuario("Juan");
        Usuario usuario2 = new Usuario("Maria");
        Usuario usuario3 = new Usuario("Pedro");

        // Prueba 1: Verificar creación de usuarios
        assert usuario1.getNombre().equals("Juan") : "Error: Nombre de usuario1 incorrecto";
        assert usuario2.getNombre().equals("Maria") : "Error: Nombre de usuario2 incorrecto";
        assert usuario3.getNombre().equals("Pedro") : "Error: Nombre de usuario3 incorrecto";

        // Prueba 2: Verificar que las listas de seguidores inician vacías
        assert usuario1.listaSeguidores.isEmpty() : "Error: Lista de seguidores de usuario1 no está vacía";
        assert usuario2.listaSeguidores.isEmpty() : "Error: Lista de seguidores de usuario2 no está vacía";
        assert usuario3.listaSeguidores.isEmpty() : "Error: Lista de seguidores de usuario3 no está vacía";

        // Prueba 3: Hacer que usuario2 y usuario3 sigan a usuario1
        usuario2.seguir(usuario1);
        usuario3.seguir(usuario1);
        assert usuario1.listaSeguidores.size() == 2 : "Error: usuario1 debería tener 2 seguidores";
        assert usuario1.listaSeguidores.contains(usuario2) : "Error: usuario2 no está en la lista de seguidores de usuario1";
        assert usuario1.listaSeguidores.contains(usuario3) : "Error: usuario3 no está en la lista de seguidores de usuario1";

        // Prueba 4: Verificar que usuario2 y usuario3 no tienen seguidores
        assert usuario2.listaSeguidores.size() == 0 : "Error: usuario2 no debería tener seguidores";
        assert usuario3.listaSeguidores.size() == 0 : "Error: usuario3 no debería tener seguidores";

        // Prueba 5: Hacer que usuario1 publique un post
        String mensaje1 = "Hola este es mi primer post";
        usuario1.postear(mensaje1);
        assert usuario2.toString().contains(mensaje1) : "Error: El post de usuario1 no aparece en el muro de usuario2";
        assert usuario3.toString().contains(mensaje1) : "Error: El post de usuario1 no aparece en el muro de usuario3";
        assert !usuario1.toString().contains(mensaje1) : "Error: El post de usuario1 no debería aparecer en su propio muro";

        // Prueba 6: Hacer que usuario1 publique otro post
        String mensaje2 = "Segundo post de prueba";
        usuario1.postear(mensaje2);
        assert usuario2.toString().contains(mensaje2) : "Error: El segundo post de usuario1 no aparece en el muro de usuario2";
        assert usuario3.toString().contains(mensaje2) : "Error: El segundo post de usuario1 no aparece en el muro de usuario3";
        assert usuario2.toString().indexOf(mensaje2) < usuario2.toString().indexOf(mensaje1) : "Error: Los posts no están en orden cronológico inverso en el muro de usuario2";

        // Prueba 7: Verificar igualdad de usuarios con mismos seguidores
        Usuario usuario4 = new Usuario("Ana");
        Usuario usuario5 = new Usuario("Luis");
        usuario4.seguir(usuario1);
        usuario5.seguir(usuario1);
        assert usuario4.equals(usuario5) : "Error: usuario4 y usuario5 deberían ser iguales porque siguen al mismo usuario";


        System.out.println("Si no hay mensaje antes de este, todas las pruebas unitarias se ejecutaron correctamente");

    }


}
