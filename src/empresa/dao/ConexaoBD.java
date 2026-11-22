package empresa.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Conexao com o banco relacional Apache Derby (embutido, salvo na pasta "dados").
 * Usa apenas a API nativa do JDBC (java.sql).
 */
public class ConexaoBD {

    private static final String URL = "jdbc:derby:dados/empresa;create=true";

    static {
        System.setProperty("derby.stream.error.file", "dados/derby.log");
    }

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    /** Cria a tabela na primeira execucao. */
    public static void criarTabela() throws SQLException {
        try (Connection conn = conectar()) {
            ResultSet tabelas = conn.getMetaData().getTables(null, null, "FUNCIONARIOS", null);
            if (tabelas.next()) {
                return;
            }
            try (Statement st = conn.createStatement()) {
                st.executeUpdate(
                    "CREATE TABLE funcionarios ("
                    + " id INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,"
                    + " cargo VARCHAR(20) NOT NULL,"
                    + " nome VARCHAR(100) NOT NULL,"
                    + " salario DOUBLE NOT NULL,"
                    + " setor VARCHAR(50),"
                    + " numero_caixa INTEGER)");
            }
        }
    }

    /** Fecha o banco corretamente ao sair do sistema. */
    public static void desligar() {
        try {
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        } catch (SQLException e) {
            // O Derby sempre sinaliza o desligamento com uma SQLException (comportamento esperado).
        }
    }
}
