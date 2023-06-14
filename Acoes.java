import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Acoes {
    Scanner sc = new Scanner(System.in);
    private List<Conta> contas;

    public Acoes() {
        this.contas = new ArrayList<>();
    }

    public void criarContaPF(String nomeTitular, String cpf) {
        ContaPF conta = new ContaPF(nomeTitular, cpf);
        contas.add(conta);
        System.out.println("Conta PF criada com sucesso. Número da conta: " + conta.getNumeroConta());
    }

    public void criarContaPJ(String nomeTitular, String cnpj) {
        ContaPJ conta = new ContaPJ(nomeTitular, cnpj);
        contas.add(conta);
        System.out.println("Conta PJ criada com sucesso. Número da conta: " + conta.getNumeroConta());
    }

    public void buscarContaPF(String cpfOuNomeTitular) {
        ContaPF contaEncontrada = null;
        for (Conta conta : contas) {
            if (conta instanceof ContaPF) {
                ContaPF contaPF = (ContaPF) conta;
                if (contaPF.getCPF().equals(cpfOuNomeTitular) || contaPF.getNomeTitular().equals(cpfOuNomeTitular)) {
                    contaEncontrada = contaPF;
                    break;
                }
            }
        }

        if (contaEncontrada != null) {
            System.out.println("Conta encontrada:");
            System.out.println("Número da conta: " + contaEncontrada.getNumeroConta());
            System.out.println("Titular: " + contaEncontrada.getNomeTitular());
            System.out.println("Saldo: " + contaEncontrada.getSaldo());
        } else {
            System.out.println("Conta PF não encontrada.");
        }
    }

    public void buscarContaPJ(String cnpjOuNomeTitular) {
        ContaPJ contaEncontrada = null;
        for (Conta conta : contas) {
            if (conta instanceof ContaPJ) {
                ContaPJ contaPJ = (ContaPJ) conta;
                if (contaPJ.getCNPJ().equals(cnpjOuNomeTitular) || contaPJ.getNomeTitular().equals(cnpjOuNomeTitular)) {
                    contaEncontrada = contaPJ;
                    break;
                }
            }
        }

        if (contaEncontrada != null) {
            System.out.println("Conta encontrada:");
            System.out.println("Número da conta: " + contaEncontrada.getNumeroConta());
            System.out.println("Titular: " + contaEncontrada.getNomeTitular());
            System.out.println("Saldo: " + contaEncontrada.getSaldo());
        } else {
            System.out.println("Conta PJ não encontrada.");
        }
    }

    public void verificarSaldo(String input) {
        Conta contaEncontrada = null;
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(input) || conta.getNomeTitular().equals(input)) {
                contaEncontrada = conta;
                break;
            }
        }
        if (contaEncontrada != null) {
            System.out.println("Saldo da conta " + contaEncontrada.getNumeroConta() + ": " + contaEncontrada.getSaldo());
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
    
    public void realizarOperacoes(String input) {
        Conta contaEncontrada = null;
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(input) || conta.getNomeTitular().equals(input)) {
                contaEncontrada = conta;
                break;
            }
        }
        if (contaEncontrada != null) {
            String numeroConta = contaEncontrada.getNumeroConta();
    
            int opcao = 0;
            while (opcao != 5) {
                System.out.println("------------------");
                System.out.println("1. Saque");
                System.out.println("2. Depósito");
                System.out.println("3. Empréstimo");
                System.out.println("4. Pagar Dívida");
                System.out.println("5. Voltar");
                System.out.println("------------------");
                System.out.print("Escolha uma opção: ");
                opcao = sc.nextInt();
                sc.nextLine();
    
                switch (opcao) {
                    case 1:
                        System.out.print("Informe o valor do saque: ");
                        double valorSaque = sc.nextDouble();
                        realizarSaque(numeroConta, valorSaque);
                        break;
                    case 2:
                        System.out.print("Informe o valor do depósito: ");
                        double valorDeposito = sc.nextDouble();
                        realizarDeposito(numeroConta, valorDeposito);
                        break;
                    case 3:
                        System.out.print("Informe o valor do empréstimo: ");
                        double valorEmprestimo = sc.nextDouble();
                        realizarEmprestimo(numeroConta, valorEmprestimo);
                        break;
                    case 4:
                        if (contaEncontrada instanceof ContaPJ) {
                            ContaPJ contaPJ = (ContaPJ) contaEncontrada;
                            double valorDivida = contaPJ.getDivida();
                            System.out.println("Valor da dívida: " + valorDivida);
                            pagarDivida(numeroConta, valorDivida);
                        } else {
                            System.out.println("Apenas contas PJ podem pagar dívidas.");
                        }
                        break;
                    case 5:
                        System.out.println("Voltando ao menu principal...");
                        break;
                    default:
                        System.out.println("Opção inválida.");
                        break;
                }
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }
    
    

    public void realizarSaque(String numeroConta, double valor) {
        Conta contaEncontrada = buscarConta(numeroConta);
        if (contaEncontrada != null) {
            if (contaEncontrada.sacar(valor)) {
                System.out.println("Saque realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para realizar o saque.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void realizarDeposito(String numeroConta, double valor) {
        Conta contaEncontrada = buscarConta(numeroConta);
        if (contaEncontrada != null) {
            contaEncontrada.depositar(valor);
            System.out.println("Depósito realizado com sucesso.");
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public void realizarEmprestimo(String numeroConta, double valor) {
        Conta contaEncontrada = buscarConta(numeroConta);
        if (contaEncontrada != null && contaEncontrada instanceof ContaPJ) {
            ContaPJ contaPJ = (ContaPJ) contaEncontrada;
            if (contaPJ.emprestar(valor)) {
                System.out.println("Empréstimo realizado com sucesso.");
                contaEncontrada.depositar(valor);
            } else {
                System.out.println("Não é possível realizar o empréstimo.");
            }
        } else if (contaEncontrada != null && contaEncontrada instanceof ContaPF) {
            System.out.println("Contas PF não podem realizar empréstimos");
        } else {
            System.out.println("Conta PJ não encontrada.");
        }
    }

    public void pagarDivida(String numeroConta, double valor) {
        Conta contaEncontrada = buscarConta(numeroConta);
        if (contaEncontrada != null) {
            if (contaEncontrada instanceof ContaPJ) {
                ContaPJ contaPJ = (ContaPJ) contaEncontrada;
                double divida = contaPJ.getDivida();
                if (divida > 0) {
                    System.out.print("Informe o valor do pagamento: R$");
                    double valorPagamento = sc.nextDouble();
                    if (valorPagamento > 0 && valorPagamento <= divida) {
                        contaPJ.pagarDivida(valorPagamento);
                        System.out.println("Pagamento de R$" + valorPagamento + " realizado com sucesso.");
                        System.out.println("Dívida restante: R$" + contaPJ.getDivida());
                    } else {
                        System.out.println(
                                "Valor de pagamento inválido. O valor deve ser maior que 0 e menor ou igual à dívida.");
                    }
                } else {
                    System.out.println("Não há dívidas a serem pagas.");
                }
            } else {
                System.out.println("Apenas contas PJ podem realizar empréstimos e pagar dívidas.");
            }
        } else {
            System.out.println("Conta não encontrada.");
        }
    }

    public Conta buscarConta(String numeroConta) {
        for (Conta conta : contas) {
            if (conta.getNumeroConta().equals(numeroConta)) {
                return conta;
            }
        }
        return null;
    }
}
