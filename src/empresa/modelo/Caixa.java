package empresa.modelo;

/** Caixa: opera um numero de caixa e recebe 10% de bonus. */
public class Caixa extends Funcionario {

    public static final double PERCENTUAL_BONUS = 0.10;

    private int numeroCaixa;

    public Caixa(int id, String nome, double salario, int numeroCaixa) {
        super(id, nome, salario);
        this.numeroCaixa = numeroCaixa;
    }

    @Override
    public double calcularBonus() {
        return getSalario() * PERCENTUAL_BONUS;
    }

    @Override
    public String getCargo() {
        return "Caixa";
    }

    @Override
    public String getDetalhe() {
        return "Caixa numero " + numeroCaixa;
    }

    public int getNumeroCaixa() {
        return numeroCaixa;
    }
}
