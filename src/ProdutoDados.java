import java.util.ArrayList;
import java.util.List;

public class ProdutoDados {

    private List<Produto> produtos = new ArrayList<>();

    public void salvar(Produto produto) {
        produtos.add(produto);
    }

    public List<Produto> buscarTodos() {
        return produtos;
    }

    public Produto buscarPorId(int id) {
        for (Produto produto : produtos) {
            if (produto.getIdProduto() == id) {
                return produto;
            }
        }
        return null;
    }

    public boolean excluirPorId(int id) {
        return produtos.removeIf(produto -> produto.getIdProduto() == id);
    }
}