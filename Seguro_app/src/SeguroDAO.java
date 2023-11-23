import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class SeguroDAO {
    public Connection connection = new Conexao().GeraConexao();

    public SeguroDAO() {
    }

    // Create
    public void adicionarSeguro(Seguro seguro) {
        String sql = "INSERT INTO Seguro (seguro_id, tipo_seguro, valor, data_contratacao, pessoa_id, veiculo_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, seguro.getSeguroId());
            stmt.setString(2, seguro.getTipoSeguro());
            stmt.setDouble(3, seguro.getValor());
            stmt.setDate(4, new java.sql.Date(seguro.getDataContratacao().getTime()));
            stmt.setInt(5, seguro.getPessoaId());
            stmt.setInt(6, seguro.getVeiculoId());
            stmt.execute();
            stmt.close();
            System.out.println("Seguro cadastrado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Seguro> listarSeguros() {
        ArrayList<Seguro> seguros = new ArrayList<>();
        String sql = "SELECT seguro_id, tipo_seguro, valor, data_contratacao, pessoa_id, veiculo_id FROM Seguro";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (((ResultSet) resultSet).next()) {
                Seguro seguro = new Seguro();
                seguro.setSeguroId(resultSet.getInt("seguro_id"));
                seguro.setTipoSeguro(resultSet.getString("tipo_seguro"));
                seguro.setValor(resultSet.getDouble("valor"));
                seguro.setDataContratacao(resultSet.getDate("data_contratacao"));
                seguro.setPessoaId(resultSet.getInt("pessoa_id"));
                seguro.setVeiculoId(resultSet.getInt("veiculo_id"));
                seguros.add(seguro);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return seguros;
    }

    // Read por ID
    public Seguro buscarSeguroPorId(int seguroId) {
        String sql = "SELECT seguro_id, tipo_seguro, valor, data_contratacao, pessoa_id, veiculo_id FROM Seguro WHERE seguro_id = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, seguroId);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Seguro seguro = new Seguro();
                seguro.setSeguroId(resultSet.getInt("seguro_id"));
                seguro.setTipoSeguro(resultSet.getString("tipo_seguro"));
                seguro.setValor(resultSet.getDouble("valor"));
                seguro.setDataContratacao(resultSet.getDate("data_contratacao"));
                seguro.setPessoaId(resultSet.getInt("pessoa_id"));
                seguro.setVeiculoId(resultSet.getInt("veiculo_id"));
                stmt.close();
                return seguro;
            } else {
                stmt.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update
    public void atualizarSeguro(Seguro seguro) {
        String sql = "UPDATE Seguro SET tipo_seguro=?, valor=?, data_contratacao=?, pessoa_id=?, veiculo_id=? WHERE seguro_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, seguro.getTipoSeguro());
            stmt.setDouble(2, seguro.getValor());
            stmt.setDate(3, new java.sql.Date(seguro.getDataContratacao().getTime()));
            stmt.setInt(4, seguro.getPessoaId());
            stmt.setInt(5, seguro.getVeiculoId());
            stmt.setInt(6, seguro.getSeguroId());
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Seguro atualizado com sucesso.");
            } else {
                System.out.println("Nenhum seguro encontrado com o ID " + seguro.getSeguroId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void excluirSeguro(int seguroId) {
        String sql = "DELETE FROM Seguro WHERE seguro_id=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, seguroId);
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Seguro excluído com sucesso.");
            } else {
                System.out.println("Nenhum seguro encontrado com o ID " + seguroId);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
