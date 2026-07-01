public class UsuarioServico {
    private UsuarioDados repositorio;

    public UsuarioServico(UsuarioDados repositorio) {
        this.repositorio = repositorio;
    }

    public Usuario fazerLogin(String login, String senha) {
        for (Usuario u : repositorio.buscarTodos()) {
            if (u.validarAcesso(login, senha)) {
                return u;
            }
        }
        return null;
    }
}