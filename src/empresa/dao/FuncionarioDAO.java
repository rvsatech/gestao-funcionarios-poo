package empresa.dao;

import empresa.modelo.Caixa;
import empresa.modelo.Funcionario;
import empresa.modelo.Gerente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

/** Operacoes de banco (CRUD) da tabela funcionarios, via JDBC. */
public class FuncionarioDAO {

    public void salvar(Funcionario f) throws SQLException {
        String sql = "INSERT INTO funcionarios (cargo, nome, salario, setor, numero_caixa) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, f.getCargo());
            ps.setString(2, f.getNome());
            ps.setDouble(3, f.getSalario());

            if (f instanceof Gerente) {
                ps.setString(4, ((Gerente) f).getSetor());
                ps.setNull(5, Types.INTEGER);
            } else if (f instanceof Caixa) {
                ps.setNull(4, Types.VARCHAR);
                ps.setInt(5, ((Caixa) f).getNumeroCaixa());
            }
            ps.executeUpdate();

            try (ResultSet chaves = ps.getGeneratedKeys()) {
                if (chaves.next()) {
                    f.setId(chaves.getInt(1));
                }
            }
        }
    }

    public List<Funcionario> listar() throws SQLException {
        List<Funcionario> lista = new ArrayList<>();
        String sql = "SELECT id, cargo, nome, salario, setor, numero_caixa FROM funcionarios ORDER BY id";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                double salario = rs.getDouble("salario");

                if ("Gerente".equals(rs.getString("cargo"))) {
                    lista.add(new Gerente(id, nome, salario, rs.getString("setor")));
                } else {
                    lista.add(new Caixa(id, nome, salario, rs.getInt("numero_caixa")));
                }
            }
        }
        return lista;
    }

    /** Retorna true se algum funcionario foi excluido. */
    public boolean excluir(int id) throws SQLException {
        String sql = "DELETE FROM funcionarios WHERE id = ?";
        try (Connection conn = ConexaoBD.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }
}
