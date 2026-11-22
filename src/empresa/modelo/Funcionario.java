package empresa.modelo;

/**
 * Superclasse abstrata com os atributos comuns a todos os funcionarios.
 * Cada cargo (subclasse) define a propria regra de bonificacao.
 */
public abstract class Funcionario {

    private int id;
    private String nome;
    private double salario;

    public Funcionario(int id, String nome, double salario) {
        this.id = id;
        this.nome = nome;
        this.salario = salario;
    }

    /** Regra de bonificacao: cada subclasse implementa a sua (polimorfismo). */
    public abstract double calcularBonus();

    /** Nome do cargo, usado na listagem e no banco de dados. */
    public abstract String getCargo();

    /** Informacao especifica de cada cargo (setor do gerente, numero do caixa). */
    public abstract String getDetalhe();

    /** Salario final = salario base + bonus do cargo. */
    public double calcularSalarioFinal() {
        return salario + calcularBonus();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public double getSalario() {
        return salario;
    }

    @Override
    public String toString() {
        return String.format("#%d | %-8s | %-20s | R$ %10.2f | %s",
                id, getCargo(), nome, salario, getDetalhe());
    }
}
