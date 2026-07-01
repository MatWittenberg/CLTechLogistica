import java.util.ArrayList;
import java.util.List;

public class EntregaDados {

    private List<Entrega> entregas = new ArrayList<>();

    public void salvar(Entrega entrega) {
        entregas.add(entrega);
    }

    public List<Entrega> buscarTodas() {
        return entregas;
    }

    public Entrega buscarPorId(int id) {
        for (Entrega entrega : entregas) {
            if (entrega.getIdEntrega() == id) {
                return entrega;
            }
        }
        return null;
    }

    public boolean excluirPorId(int id) {
        return entregas.removeIf(entrega -> entrega.getIdEntrega() == id);
    }
}