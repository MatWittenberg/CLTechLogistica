import java.util.ArrayList;
import java.util.List;

public class Entrega {
    private int idEntrega;
    private Cliente destinatario;
    private List<Produto> listaDeProdutos;

    public Entrega() {
        this.listaDeProdutos = new ArrayList<>();
    }

    public Entrega(int idEntrega, Cliente destinatario) {
        setIdEntrega(idEntrega);
        setDestinatario(destinatario);
        this.listaDeProdutos = new ArrayList<>();
    }

    public void adicionarProduto(Produto produto) {
        if (produto == null) {
            throw new IllegalArgumentException("Não é possível adicionar um produto vazio (null) à entrega.");
        }
        this.listaDeProdutos.add(produto);
    }

    public double calcularPesoTotal() {
        double pesoTotal = 0.0;
        for (Produto p : listaDeProdutos) {
            pesoTotal += p.getPeso();
        }
        return pesoTotal;
    }

    public int getIdEntrega() {
        return idEntrega;
    }

    public void setIdEntrega(int idEntrega) {
        if (idEntrega <= 0) {
            throw new IllegalArgumentException("O ID da entrega deve ser maior que zero.");
        }
        this.idEntrega = idEntrega;
    }

    public Cliente getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Cliente destinatario) {
        if (destinatario == null) {
            throw new IllegalArgumentException("A entrega precisa obrigatoriamente de um destinatário válido.");
        }
        this.destinatario = destinatario;
    }

    public List<Produto> getListaDeProdutos() {
        return listaDeProdutos;
    }

    public void setListaDeProdutos(List<Produto> listaDeProdutos) {
        if (listaDeProdutos == null) {
            this.listaDeProdutos = new ArrayList<>();
        } else {
            this.listaDeProdutos = listaDeProdutos;
        }
    }

    @Override
    public String toString() {
        return "Entrega ID: " + idEntrega + "\n" +
                "Destinatário: " + destinatario.getNome() + " (" + destinatario.getEndereco() + ")\n" +
                "Quantidade de Produtos: " + listaDeProdutos.size() + "\n" +
                "Peso Total da Entrega: " + calcularPesoTotal() + "kg";
    }
}