import java.util.Scanner;

public class CLTechLogistica {

    public static void main(String[] args) {
        ProdutoDados produtoDados = new ProdutoDados();
        EntregaDados entregaDados = new EntregaDados();
        CargaDados cargaDados = new CargaDados();
        UsuarioDados usuarioDados = new UsuarioDados();

        ProdutoServico produtoServico = new ProdutoServico(produtoDados);
        EntregaServico entregaServico = new EntregaServico(entregaDados);
        CargaServico cargaServico = new CargaServico(cargaDados);
        UsuarioServico usuarioServico = new UsuarioServico(usuarioDados);

        Scanner scanner = new Scanner(System.in);

        ConsoleSistema console = new ConsoleSistema(produtoServico, entregaServico, cargaServico, usuarioServico, scanner);

        int opcao = 0;

        while (opcao != 9) {
            imprimirCabecalho();

            System.out.println("1 - Validação do Usuário " + (console.isUsuarioLogado() ? "[LOGADO]" : "[DESLOGADO]"));
            System.out.println("2 - Cadastrar Produtos");
            System.out.println("3 - Cadastrar Entregas (Destinatários)");
            System.out.println("4 - Cadastrar Caminhões/Cargas (Rotas)");
            System.out.println("5 - Vender Produto (Programar na Carga)");
            System.out.println("6 - Relatórios do Sistema");
            System.out.println("7 - Excluir Carga");
            System.out.println("8 - Excluir Entrega");
            System.out.println("9 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                if (opcao > 1 && opcao < 9 && !console.isUsuarioLogado()) {
                    System.out.println("\n[ERRO] Acesso negado. Por favor, faça login primeiro (Opção 1).");
                    continuar(scanner);
                    continue;
                }

                switch (opcao) {
                    case 1:
                        console.validarUsuario();
                        break;
                    case 2:
                        console.cadastrarProduto();
                        break;
                    case 3:
                        console.cadastrarEntrega();
                        break;
                    case 4:
                        console.cadastrarCarga();
                        break;
                    case 5:
                        console.venderEProgramarCarga();
                        break;
                    case 6:
                        console.imprimirRelatorios();
                        break;
                    case 7:
                        console.excluirCarga();break;
                    case 8:
                        console.excluirEntrega();
                        break;
                    case 9:
                        System.out.println("\nFinalizando o sistema CLTech. Até logo!");
                        break;
                    default:
                        System.out.println("\n[ERRO] Opção inválida!");
                }
            } catch (NumberFormatException e) {
                System.out.println("\n[ERRO] Por favor, digite apenas números válidos.");
            } catch (Exception e) {
                System.out.println("\n[ERRO DE VALIDAÇÃO] " + e.getMessage());
            }

            if (opcao != 9) continuar(scanner);
        }
        scanner.close();
    }

    private static void continuar(Scanner scanner) {
        System.out.print("\nPressione [ENTER] para continuar...");
        scanner.nextLine();
    }

    private static void imprimirCabecalho() {
        System.out.println("\n=======================================================");
        System.out.println("  ██████╗ ██╗     ████████╗███████╗ ██████╗██╗  ██╗");
        System.out.println(" ██╔════╝ ██║     ╚══██╔══╝██╔════╝██╔════╝██║  ██║");
        System.out.println(" ██║      ██║        ██║   █████╗  ██║     ███████║");
        System.out.println(" ██║      ██║        ██║   ██╔══╝  ██║     ██╔══██║");
        System.out.println(" ╚██████╗ ███████╗   ██║   ███████╗╚██████╗██║  ██║");
        System.out.println("  ╚═════╝ ╚══════╝   ╚═╝   ╚══════╝ ╚═════╝╚═╝  ╚═╝");
        System.out.println("           SISTEMA LOGÍSTICO INTEGRADO");
        System.out.println("=======================================================");
    }
}