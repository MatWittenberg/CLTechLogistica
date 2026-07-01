import java.util.Scanner;

public class ConsoleSistema {

    private ProdutoServico produtoServico;
    private EntregaServico entregaServico;
    private CargaServico cargaServico;
    private UsuarioServico usuarioServico;
    private Scanner scanner;

    private Usuario usuarioAtivo = null;

    public ConsoleSistema(ProdutoServico produtoServico, EntregaServico entregaServico, CargaServico cargaServico, UsuarioServico usuarioServico, Scanner scanner) {
        this.produtoServico = produtoServico;
        this.entregaServico = entregaServico;
        this.cargaServico = cargaServico;
        this.usuarioServico = usuarioServico;
        this.scanner = scanner;
    }

    public boolean isUsuarioLogado() {
        return usuarioAtivo != null;
    }

    public void validarUsuario() {
        System.out.print("\nDigite o login: ");
        String login = scanner.nextLine();
        System.out.print("Digite a senha: ");
        String senha = scanner.nextLine();

        Usuario usuarioEncontrado = usuarioServico.fazerLogin(login, senha);

        if (usuarioEncontrado != null) {
            usuarioAtivo = usuarioEncontrado;
            System.out.println("[SUCESSO] Bem-vindo, " + usuarioAtivo.getNome() + "!");
        } else {
            System.out.println("[ERRO] Login ou senha incorretos.");
        }
    }

    public void cadastrarProduto() {
        System.out.println("\n--- Cadastro de Produto ---");
        System.out.print("ID do Produto: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String desc = scanner.nextLine();
        System.out.print("Valor (R$): ");
        double valor = Double.parseDouble(scanner.nextLine().replace(",", "."));
        System.out.print("Peso (kg): ");
        double peso = Double.parseDouble(scanner.nextLine().replace(",", "."));

        produtoServico.cadastrarProduto(id, nome, desc, valor, peso);
        System.out.println("[SUCESSO] Produto cadastrado.");
    }

    public void cadastrarEntrega() {
        System.out.println("\n--- Cadastro de Destinatário (Nova Entrega) ---");
        System.out.print("ID da Entrega: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome do Destinatário: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String tel = scanner.nextLine();
        System.out.print("Endereço: ");
        String end = scanner.nextLine();

        entregaServico.cadastrarEntrega(id, nome, cpf, tel, end);
        System.out.println("[SUCESSO] Entrega aberta (Vazia).");
    }

    public void cadastrarCarga() {
        System.out.println("\n--- Cadastro de Caminhão/Carga ---");
        System.out.print("ID da Carga: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Data de Saída (ex: 20/10/2023): ");
        String data = scanner.nextLine();
        System.out.print("Capacidade de peso do caminhão (kg): ");
        double capacidade = Double.parseDouble(scanner.nextLine().replace(",", "."));

        cargaServico.cadastrarCarga(id, data, capacidade);
        System.out.println("[SUCESSO] Caminhão programado.");
    }

    public void excluirCarga() {
        System.out.println("\n--- Excluir Carga ---");
        System.out.print("Digite o ID da carga que deseja excluir: ");
        int id = Integer.parseInt(scanner.nextLine());

        cargaServico.excluirCarga(id);
        System.out.println("[SUCESSO] Carga excluída!");
    }

    public void excluirEntrega() {
        System.out.println("\n--- Excluir Entrega ---");
        System.out.print("Digite o ID da Entrega que deseja excluir: ");
        int idEntrega = Integer.parseInt(scanner.nextLine());

        Entrega entregaAlvo = entregaServico.listarEntregas().stream()
                .filter(e -> e.getIdEntrega() == idEntrega)
                .findFirst().orElse(null);

        if (entregaAlvo == null) {
            System.out.println("[ERRO] Entrega não encontrada.");
            return;
        }

        for (Carga carga : cargaServico.listarCargas()) {
            if (carga.getListaDeEntregas().contains(entregaAlvo)) {
                carga.getListaDeEntregas().remove(entregaAlvo);
                System.out.println("[INFO] A entrega foi retirada da Carga " + carga.getIdCarga() + ".");
            }
        }

        entregaServico.excluirEntrega(idEntrega);
        System.out.println("[SUCESSO] Entrega excluída definitivamente do sistema!");
    }

    public void venderEProgramarCarga() {
        if (produtoServico.listarProdutos().isEmpty() ||
                entregaServico.listarEntregas().isEmpty() ||
                cargaServico.listarCargas().isEmpty()) {
            System.out.println("[ERRO] Você precisa ter cadastrado pelo menos 1 Produto, 1 Entrega e 1 Carga.");
            return;
        }

        System.out.println("\n--- Vender e Embarcar Produto ---");
        System.out.print("Digite o ID do Produto a ser vendido: ");
        int idProd = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o ID da Entrega de destino: ");
        int idEntr = Integer.parseInt(scanner.nextLine());
        System.out.print("Digite o ID do Caminhão (Carga) para transporte: ");
        int idCarg = Integer.parseInt(scanner.nextLine());

        System.out.print("Quantidade desse produto: ");
        int quantidade = Integer.parseInt(scanner.nextLine());

        Produto prodEncontrado = produtoServico.listarProdutos().stream().filter(p -> p.getIdProduto() == idProd).findFirst().orElse(null);
        Entrega entrEncontrada = entregaServico.listarEntregas().stream().filter(e -> e.getIdEntrega() == idEntr).findFirst().orElse(null);
        Carga cargEncontrada = cargaServico.listarCargas().stream().filter(c -> c.getIdCarga() == idCarg).findFirst().orElse(null);

        if (prodEncontrado == null || entrEncontrada == null || cargEncontrada == null) {
            System.out.println("[ERRO] Produto, Entrega ou Carga não encontrados.");
            return;
        }

        double pesoAdicional = prodEncontrado.getPeso() * quantidade;
        double pesoFuturoDoCaminhao = cargEncontrada.calcularPesoAtual() + pesoAdicional;

        if (pesoFuturoDoCaminhao > cargEncontrada.getCapacidadeDePesoCaminhao()) {
            throw new IllegalStateException("Carga rejeitada! O caminhão atingiu sua capacidade máxima.\n" +
                    "Capacidade Total: " + cargEncontrada.getCapacidadeDePesoCaminhao() + "kg\n" +
                    "Peso que você tentou adicionar agora: " + pesoAdicional + "kg");
        }

        for (int i = 0; i < quantidade; i++) {
            entrEncontrada.adicionarProduto(prodEncontrado);
        }

        if (!cargEncontrada.getListaDeEntregas().contains(entrEncontrada)) {
            cargEncontrada.adicionarEntrega(entrEncontrada);
        }

        System.out.println("[SUCESSO] Venda de " + quantidade + " unidade(s) realizada e programada na Carga " + idCarg);
    }

    public void imprimirRelatorios() {
        System.out.println("\n=== RELATÓRIOS CLTECH ===");

        System.out.println("\n[ Produtos Cadastrados ]");
        produtoServico.listarProdutos().forEach(System.out::println);

        System.out.println("\n[ Entregas Cadastradas ]");
        System.out.print("Deseja filtrar as entregas por CPF do cliente? (S/N): ");
        String respostaFiltro = scanner.nextLine().toUpperCase();

        if (respostaFiltro.equals("S")) {
            System.out.print("Digite o CPF do cliente (apenas números): ");
            String cpfFiltro = scanner.nextLine();
            System.out.println("\n-- Entregas encontradas para o CPF " + cpfFiltro + " --");
            entregaServico.listarEntregas().stream()
                    .filter(entrega -> entrega.getDestinatario().getCpf().equals(cpfFiltro))
                    .forEach(System.out::println);
        } else {
            entregaServico.listarEntregas().forEach(System.out::println);
        }

        System.out.println("\n[ Caminhões/Rotas Programadas ]");
        cargaServico.listarCargas().forEach(System.out::println);
    }
}