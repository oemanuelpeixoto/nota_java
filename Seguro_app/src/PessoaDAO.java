import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PessoaDAO {
    private Connection connection = new Conexao().GeraConexao();

    public PessoaDAO() {
    }

    // Create
    public boolean adiciona(Pessoa pessoa) {
        String sql = "INSERT INTO pessoa(idPessoa, cpf, nomeCompleto, telefone, email) VALUES(?,?,?,?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, pessoa.getIdPessoa());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getNomeCompleto());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setString(5, pessoa.getEmail());
            stmt.execute();
            System.out.println("Usuário cadastrado com sucesso.");
            return true;
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar usuário: " + e.getMessage());
            return false;
        }
    }

    // Read
    public ArrayList<Pessoa> listar() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        String sql = "SELECT idPessoa, cpf, nomeCompleto, telefone, email FROM pessoa";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(resultSet.getInt("idPessoa"));
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setNomeCompleto(resultSet.getString("nomeCompleto"));
                pessoa.setTelefone(resultSet.getString("telefone"));
                pessoa.setEmail(resultSet.getString("email"));
                pessoas.add(pessoa);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return pessoas;
    }

    // Read por ID
    public Pessoa buscarPorId(int idPessoa) {
        String sql = "SELECT id_pessoa, nome_pessoa, cidade, estado, email, whatsapp FROM pessoas WHERE id_pessoa = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Pessoa pessoa = new Pessoa();
                pessoa.setIdPessoa(resultSet.getInt("id_pessoa"));
                pessoa.setNomeCompleto(resultSet.getString("nome_pessoa"));
                pessoa.setCpf(resultSet.getString("cpf"));
                pessoa.setTelefone(resultSet.getString("telefone"));
                pessoa.setEmail(resultSet.getString("email"));
                stmt.close();
                return pessoa;
            } else {
                stmt.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update
    public void atualizar(Pessoa pessoa) {
        String sql = "UPDATE pessoas SET nome_pessoa=?, cpf=?, email=?, telefone=? WHERE id_pessoa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, pessoa.getNomeCompleto());
            stmt.setString(2, pessoa.getCpf());
            stmt.setString(3, pessoa.getEmail());
            stmt.setString(4, pessoa.getTelefone());
            stmt.setInt(5, pessoa.getIdPessoa());
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário atualizado com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID " + pessoa.getIdPessoa());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void excluir(int idPessoa) {
        String sql = "DELETE FROM pessoa WHERE idPessoa=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idPessoa);
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário excluído com sucesso.");
            } else {
                System.out.println("Nenhum usuário encontrado com o ID " + idPessoa);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
