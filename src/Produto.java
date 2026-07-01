public class Produto {
    private int idProduto;
    private String nome;
    private String descricao;
    private double valor;
    private double peso;

    public Produto() {
    }

    public Produto(int idProduto, String nome, String descricao, double valor, double peso) {
        setIdProduto(idProduto);
        setNome(nome);
        setDescricao(descricao);
        setValor(valor);
        setPeso(peso);
    }

    public int getIdProduto() {
        return idProduto;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }

    public double getPeso() {
        return peso;
    }

    public void setIdProduto(int idProduto) {
        if (idProduto <= 0) {
            throw new IllegalArgumentException("O ID do produto deve ser maior que zero.");
        }
        this.idProduto = idProduto;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do produto não pode ser vazio.");
        }
        this.nome = nome;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do produto não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public void setValor(double valor) {
        if (valor < 0) {
            throw new IllegalArgumentException("O valor do produto não pode ser negativo.");
        }
        this.valor = valor;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("O peso do produto deve ser maior que zero.");
        }
        this.peso = peso;
    }

    @Override
    public String toString() {
        return "ID: " + idProduto + " | Produto: " + nome + " | Peso: " + peso + "kg | Valor: R$ " + valor;
    }
}