import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Acoes acoes = new Acoes();

        int opcao = 0;
        while (opcao != 5) {
            System.out.println("----------- Menu -----------");
            System.out.println("1. Criar Conta");
            System.out.println("2. Buscar Conta");
            System.out.println("3. Verificar Saldo");
            System.out.println("4. Realizar Operações");
            System.out.println("5. Sair");
            System.out.println("-----------------------------");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    criarConta(sc, acoes);
                    break;
                case 2:
                    buscarConta(sc, acoes);
                    break;
                case 3:
                    verificarSaldo(sc, acoes);
                    break;
                case 4:
                    realizarOperacoes(sc, acoes);
                    break;
                case 5:
                    System.out.println("Encerrando sistema...");
                    break;
                default:
                    System.out.println("Opção inválida.");
                    break;
            }
        }

        sc.close();
    }

    private static void criarConta(Scanner sc, Acoes acoes) {
        System.out.println("----------- Criar Conta -----------");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.println("-----------------------------------");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        System.out.print("Informe o nome do titular: ");
        String nomeTitular = sc.nextLine();

        switch (opcao) {
            case 1:
                System.out.print("Informe o CPF: ");
                String cpf = sc.nextLine();
                acoes.criarContaPF(nomeTitular, cpf);
                break;
            case 2:
                System.out.print("Informe o CNPJ: ");
                String cnpj = sc.nextLine();
                acoes.criarContaPJ(nomeTitular, cnpj);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void buscarConta(Scanner sc, Acoes acoes) {
        System.out.println("----------- Buscar Conta -----------");
        System.out.println("1. Pessoa Física");
        System.out.println("2. Pessoa Jurídica");
        System.out.println("-------------------------------------");

        System.out.print("Escolha uma opção: ");
        int opcao = sc.nextInt();
        sc.nextLine();

        System.out.print("Informe o número da conta ou o nome do titular: ");
        String chaveBusca = sc.nextLine();

        switch (opcao) {
            case 1:
                acoes.buscarContaPF(chaveBusca);
                break;
            case 2:
                acoes.buscarContaPJ(chaveBusca);
                break;
            default:
                System.out.println("Opção inválida.");
                break;
        }
    }

    private static void verificarSaldo(Scanner sc, Acoes acoes) {
        System.out.println("----------- Verificar Saldo -----------");
        System.out.print("Informe o número da conta ou nome do titular: ");
        String input = sc.nextLine();
        acoes.verificarSaldo(input);
    }

    private static void realizarOperacoes(Scanner sc, Acoes acoes) {
        System.out.println("----------- Realizar Operações -----------");
        System.out.print("Informe o número da conta ou nome do titular: ");
        String input = sc.nextLine();
        acoes.realizarOperacoes(input);
    }
    
}
