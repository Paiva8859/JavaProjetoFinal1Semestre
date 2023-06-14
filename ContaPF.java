public class ContaPF extends Conta {
    private String cpf;

    public ContaPF(String nomeTitular, String cpf) {
        super(cpf, nomeTitular);
        this.cpf = cpf;
    }

    public String getCPF() {
        return cpf;
    }

    public boolean sacar(double valor) {
        if (valor > getSaldo()) {
            return false;
        }
        setSaldo(getSaldo() - valor);
        return true;
    }
}