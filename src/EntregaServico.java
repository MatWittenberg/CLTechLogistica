import java.util.List;

public class EntregaServico {

    private EntregaDados dados;

    public EntregaServico(EntregaDados dados) {
        this.dados = dados;
    }

    public void cadastrarEntrega(int id, String nome, String cpf, String tel, String endereco) {
        if (dados.buscarPorId(id) != null) {
            throw new IllegalArgumentException("Já existe uma entrega com o ID " + id + ".");
        }

        Cliente cliente = new Cliente(nome, cpf, tel, endereco);
        Entrega novaEntrega = new Entrega(id, cliente);
        dados.salvar(novaEntrega);
    }

    public List<Entrega> listarEntregas() {
        return dados.buscarTodas();
    }

    public void excluirEntrega(int id) {
        if (dados.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Entrega com ID " + id + " não encontrada.");
        }
        dados.excluirPorId(id);
    }
}