import java.util.ArrayList;
import java.util.List;

public class UsuarioDados {

    private List<Usuario> usuarios = new ArrayList<>();

    public UsuarioDados() {
        usuarios.add(new Usuario("Administrador CLTech", "000", "000", "admin", "1234"));
    }

    public List<Usuario> buscarTodos() {
        return usuarios;
    }
}