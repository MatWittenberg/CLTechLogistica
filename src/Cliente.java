public class Cliente extends Pessoa {
    private String endereco;

    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf, String telefone, String endereco) {
        super(nome, cpf, telefone);
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Cliente: " + getNome() + " | CPF: " + getCpf() + " | Endereço: " + endereco;
    }
}