import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VeiculoDAO {
    public Connection connection = new Conexao().GeraConexao();

    public VeiculoDAO() {
    }

    // Create
    public void adicionarVeiculo(Veiculo veiculo) {
        String sql = "INSERT INTO Veiculo (idVeiculo, tipo, placa, seguradora, situacao) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, veiculo.getIdVeiculo());
            stmt.setString(2, veiculo.getTipo());
            stmt.setString(3, veiculo.getPlaca());
            stmt.setString(4, veiculo.getSeguradora());
            stmt.setString(5, veiculo.getSituacao());
            stmt.execute();
            stmt.close();
            System.out.println("Veículo cadastrado com sucesso.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Read
    public ArrayList<Veiculo> listarVeiculos() {
        ArrayList<Veiculo> veiculos = new ArrayList<>();
        String sql = "SELECT idVeiculo, tipo, placa, seguradora, situacao FROM Veiculo";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(resultSet.getInt("idVeiculo"));
                veiculo.setTipo(resultSet.getString("tipo"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setSeguradora(resultSet.getString("seguradora"));
                veiculo.setSituacao(resultSet.getString("situacao"));
                veiculos.add(veiculo);
            }
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return veiculos;
    }

    // Read por ID
    public Veiculo buscarVeiculoPorId(int idVeiculo) {
        String sql = "SELECT idVeiculo, tipo, placa, seguradora, situacao FROM Veiculo WHERE idVeiculo = ?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setIdVeiculo(resultSet.getInt("idVeiculo"));
                veiculo.setTipo(resultSet.getString("tipo"));
                veiculo.setPlaca(resultSet.getString("placa"));
                veiculo.setSeguradora(resultSet.getString("seguradora"));
                veiculo.setSituacao(resultSet.getString("situacao"));
                stmt.close();
                return veiculo;
            } else {
                stmt.close();
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Update
    public void atualizarVeiculo(Veiculo veiculo) {
        String sql = "UPDATE Veiculo SET tipo=?, placa=?, seguradora=?, situacao=? WHERE idVeiculo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, veiculo.getTipo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setString(3, veiculo.getSeguradora());
            stmt.setString(4, veiculo.getSituacao());
            stmt.setInt(5, veiculo.getIdVeiculo());
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Veículo atualizado com sucesso.");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID " + veiculo.getIdVeiculo());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Delete
    public void excluirVeiculo(int idVeiculo) {
        String sql = "DELETE FROM Veiculo WHERE idVeiculo=?";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, idVeiculo);
            int linhasAfetadas = stmt.executeUpdate();
            stmt.close();
            if (linhasAfetadas > 0) {
                System.out.println("Veículo excluído com sucesso.");
            } else {
                System.out.println("Nenhum veículo encontrado com o ID " + idVeiculo);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
