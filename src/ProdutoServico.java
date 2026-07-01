import java.util.List;

public class ProdutoServico {

    private ProdutoDados dados;

    public ProdutoServico(ProdutoDados dados) {
        this.dados = dados;
    }

    public void cadastrarProduto(int id, String nome, String desc, double valor, double peso) {
        if (dados.buscarPorId(id) != null) {
            throw new IllegalArgumentException("Já existe um produto com o ID " + id + ".");
        }

        Produto novoProduto = new Produto(id, nome, desc, valor, peso);
        dados.salvar(novoProduto);
    }

    public List<Produto> listarProdutos() {
        return dados.buscarTodos();
    }

    public void excluirProduto(int id) {
        if (dados.buscarPorId(id) == null) {
            throw new IllegalArgumentException("Produto com ID " + id + " não encontrado.");
        }

        dados.excluirPorId(id);
    }
}