import java.util.ArrayList;
import java.util.List;

public class CargaDados {

    private List<Carga> cargas = new ArrayList<>();

    public void salvar(Carga carga) {
        cargas.add(carga);
    }

    public List<Carga> buscarTodas() {
        return cargas;
    }

    public Carga buscarPorId(int id) {
        for (Carga carga : cargas) {
            if (carga.getIdCarga() == id) {
                return carga;
            }
        }
        return null;
    }

    public boolean excluirPorId(int id) {
        return cargas.removeIf(carga -> carga.getIdCarga() == id);
    }
}