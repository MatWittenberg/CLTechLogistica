public class Usuario extends Pessoa {

    private String login;
    private String senha;

    public Usuario() {
        super();
    }

    public Usuario(String nome, String cpf, String telefone, String login, String senha) {
        super(nome, cpf, telefone);
        this.login = login;
        this.senha = senha;
    }

    public boolean validarAcesso(String loginDigitado, String senhaDigitada) {
        return this.login.equals(loginDigitado) && this.senha.equals(senhaDigitada);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}