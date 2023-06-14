import java.util.List;

public class ContaPJ extends Conta {
    private List<Conta> contas;
    private String cnpj;

    public ContaPJ(String nomeTitular, String cnpj) {
        super(cnpj, nomeTitular);
        this.cnpj = cnpj;
    }

    public String getCNPJ() {
        return cnpj;
    }

    public boolean sacar(double valor) {
        if (valor > getSaldo()) {
            return false;
        }
        setSaldo(getSaldo() - valor);
        return true;
    }

    private double divida;

    public double getDivida() {
        return divida;
    }

    public boolean emprestar(double valor) {
        double valorMaximoEmprestimo = ((getSaldo() - divida) + 1) * 1.5;

        if (valor > (valorMaximoEmprestimo - divida * 1.5)) {
            return false;
        }

        divida += valor;

        return true;
    }

    public void pagarDivida(double valor) {
        if (valor >= divida) {
            divida = 0;
        } else {
            divida -= valor;
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

}
