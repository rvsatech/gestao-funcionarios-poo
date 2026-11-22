package empresa.modelo;

/** Gerente: responsavel por um setor e recebe 20% de bonus. */
public class Gerente extends Funcionario {

    public static final double PERCENTUAL_BONUS = 0.20;

    private String setor;

    public Gerente(int id, String nome, double salario, String setor) {
        super(id, nome, salario);
        this.setor = setor;
    }

    @Override
    public double calcularBonus() {
        return getSalario() * PERCENTUAL_BONUS;
    }

    @Override
    public String getCargo() {
        return "Gerente";
    }

    @Override
    public String getDetalhe() {
        return "Setor: " + setor;
    }

    public String getSetor() {
        return setor;
    }
}
