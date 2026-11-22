package empresa;

import empresa.dao.ConexaoBD;
import empresa.dao.FuncionarioDAO;
import empresa.modelo.Caixa;
import empresa.modelo.Funcionario;
import empresa.modelo.Gerente;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

/** Sistema de Gestao de Funcionarios (console). */
public class Main {

    private static final Scanner entrada = new Scanner(System.in);
    private static final FuncionarioDAO dao = new FuncionarioDAO();

    public static void main(String[] args) {
        try {
            ConexaoBD.criarTabela();
        } catch (SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            return;
        }

        int opcao;
        do {
            System.out.println();
            System.out.println("===== GESTAO DE FUNCIONARIOS =====");
            System.out.println("1 - Cadastrar gerente");
            System.out.println("2 - Cadastrar caixa");
            System.out.println("3 - Listar funcionarios");
            System.out.println("4 - Calcular bonus e salario final");
            System.out.println("5 - Excluir funcionario");
            System.out.println("0 - Sair");
            opcao = lerInteiro("Opcao: ");

            try {
                switch (opcao) {
                    case 1: cadastrarGerente(); break;
                    case 2: cadastrarCaixa(); break;
                    case 3: listar(); break;
                    case 4: calcularSalarios(); break;
                    case 5: excluir(); break;
                    case 0: System.out.println("Encerrando o sistema..."); break;
                    default: System.out.println("Opcao invalida.");
                }
            } catch (SQLException e) {
                System.out.println("Erro no banco de dados: " + e.getMessage());
            }
        } while (opcao != 0);

        ConexaoBD.desligar();
    }

    private static void cadastrarGerente() throws SQLException {
        String nome = lerTexto("Nome: ");
        double salario = lerDecimal("Salario base: ");
        String setor = lerTexto("Setor: ");
        Gerente g = new Gerente(0, nome, salario, setor);
        dao.salvar(g);
        System.out.println("Gerente cadastrado com ID " + g.getId() + ".");
    }

    private static void cadastrarCaixa() throws SQLException {
        String nome = lerTexto("Nome: ");
        double salario = lerDecimal("Salario base: ");
        int numero = lerInteiro("Numero do caixa: ");
        Caixa c = new Caixa(0, nome, salario, numero);
        dao.salvar(c);
        System.out.println("Caixa cadastrado com ID " + c.getId() + ".");
    }

    private static void listar() throws SQLException {
        List<Funcionario> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        System.out.println("--- Funcionarios cadastrados ---");
        for (Funcionario f : lista) {
            System.out.println(f);
        }
    }

    /** Polimorfismo: o mesmo metodo calcularBonus() tem regra diferente em cada cargo. */
    private static void calcularSalarios() throws SQLException {
        List<Funcionario> lista = dao.listar();
        if (lista.isEmpty()) {
            System.out.println("Nenhum funcionario cadastrado.");
            return;
        }
        double totalFolha = 0;
        System.out.println("--- Bonus e salario final ---");
        for (Funcionario f : lista) {
            System.out.printf("%-8s | %-20s | Base: R$ %10.2f | Bonus: R$ %9.2f | Final: R$ %10.2f%n",
                    f.getCargo(), f.getNome(), f.getSalario(), f.calcularBonus(), f.calcularSalarioFinal());
            totalFolha += f.calcularSalarioFinal();
        }
        System.out.printf("Total da folha de pagamento: R$ %.2f%n", totalFolha);
    }

    private static void excluir() throws SQLException {
        int id = lerInteiro("ID do funcionario a excluir: ");
        if (dao.excluir(id)) {
            System.out.println("Funcionario " + id + " excluido.");
        } else {
            System.out.println("Nenhum funcionario com o ID " + id + ".");
        }
    }

    // ---------- Leitura de dados com validacao ----------

    private static String lerTexto(String rotulo) {
        String texto;
        do {
            System.out.print(rotulo);
            texto = entrada.nextLine().trim();
        } while (texto.isEmpty());
        return texto;
    }

    private static int lerInteiro(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = entrada.nextLine().trim();
            try {
                return Integer.parseInt(linha);
            } catch (NumberFormatException e) {
                System.out.println("Digite um numero inteiro.");
            }
        }
    }

    private static double lerDecimal(String rotulo) {
        while (true) {
            System.out.print(rotulo);
            String linha = entrada.nextLine().trim().replace(",", ".");
            try {
                double valor = Double.parseDouble(linha);
                if (valor >= 0) {
                    return valor;
                }
            } catch (NumberFormatException e) {
                // repete a pergunta
            }
            System.out.println("Digite um valor valido (ex: 3500.00).");
        }
    }
}
