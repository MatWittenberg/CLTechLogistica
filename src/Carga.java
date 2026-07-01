import java.util.ArrayList;
import java.util.List;

public class Carga {
    private int idCarga;
    private String dataSaidaCaminhao;
    private double capacidadeDePesoCaminhao;

    private List<Entrega> listaDeEntregas;

    public Carga() {
        this.listaDeEntregas = new ArrayList<>();
    }

    public Carga(int idCarga, String dataSaidaCaminhao, double capacidadeDePesoCaminhao) {
        setIdCarga(idCarga);
        setDataSaidaCaminhao(dataSaidaCaminhao);
        setCapacidadeDePesoCaminhao(capacidadeDePesoCaminhao);
        this.listaDeEntregas = new ArrayList<>();
    }

    public double calcularPesoAtual() {
        double pesoTotal = 0.0;
        for (Entrega e : listaDeEntregas) {
            pesoTotal += e.calcularPesoTotal();
        }
        return pesoTotal;
    }

    public void adicionarEntrega(Entrega novaEntrega) {
        if (novaEntrega == null) {
            throw new IllegalArgumentException("A entrega não pode ser nula.");
        }

        double pesoDaNovaEntrega = novaEntrega.calcularPesoTotal();

        double pesoAtualDoCaminhao = calcularPesoAtual();

        if ((pesoAtualDoCaminhao + pesoDaNovaEntrega) > this.capacidadeDePesoCaminhao) {
            throw new IllegalStateException("Carga rejeitada! O caminhão atingiu sua capacidade máxima.\n" +
                    "Peso disponível: " + (this.capacidadeDePesoCaminhao - pesoAtualDoCaminhao) + "kg \n" +
                    "Peso da entrega tentada: " + pesoDaNovaEntrega + "kg");
        }

        this.listaDeEntregas.add(novaEntrega);
    }

    public int getIdCarga() {
        return idCarga;
    }

    public String getDataSaidaCaminhao() {
        return dataSaidaCaminhao;
    }

    public double getCapacidadeDePesoCaminhao() {
        return capacidadeDePesoCaminhao;
    }

    public List<Entrega> getListaDeEntregas() {
        return listaDeEntregas;
    }

    public void setIdCarga(int idCarga) {
        if (idCarga <= 0) {
            throw new IllegalArgumentException("O ID da carga deve ser maior que zero.");
        }
        this.idCarga = idCarga;
    }

    public void setDataSaidaCaminhao(String dataSaidaCaminhao) {
        if (dataSaidaCaminhao == null || dataSaidaCaminhao.trim().isEmpty()) {
            throw new IllegalArgumentException("A data de saída não pode ser vazia.");
        }
        this.dataSaidaCaminhao = dataSaidaCaminhao;
    }

    public void setCapacidadeDePesoCaminhao(double capacidadeDePesoCaminhao) {
        if (capacidadeDePesoCaminhao <= 0) {
            throw new IllegalArgumentException("A capacidade do caminhão deve ser maior que zero para transportar cargas.");
        }
        this.capacidadeDePesoCaminhao = capacidadeDePesoCaminhao;
    }

    @Override
    public String toString() {
        return "Carga ID: " + idCarga + " | Data Saída: " + dataSaidaCaminhao +
                "\nCapacidade: " + capacidadeDePesoCaminhao + "kg | Ocupado: " + calcularPesoAtual() + "kg" +
                "\nQuantidade de Entregas no caminhão: " + listaDeEntregas.size() + "\n";
    }
}