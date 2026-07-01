import java.util.List;

public class CargaServico {

    private CargaDados repositorio;

    public CargaServico(CargaDados  repositorio) {
        this.repositorio = repositorio;
    }

    public void cadastrarCarga(int id, String data, double capacidade) {
        if (repositorio.buscarPorId(id) != null) {
            throw new IllegalArgumentException("Já existe uma carga com o ID " + id + ".");
        }

        Carga novaCarga = new Carga(id, data, capacidade);
        repositorio.salvar(novaCarga);
    }

    public List<Carga> listarCargas() {
        return repositorio.buscarTodas();
    }

    public void excluirCarga(int id) {
        Carga cargaEncontrada = repositorio.buscarPorId(id);
        if (cargaEncontrada == null) {
            throw new IllegalArgumentException("Carga com ID " + id + " não encontrada.");
        }

        if (!cargaEncontrada.getListaDeEntregas().isEmpty()) {
            throw new IllegalStateException("Não é possível excluir a carga " + id + " pois ela já possui entregas programadas!");
        }

        repositorio.excluirPorId(id);
    }
}