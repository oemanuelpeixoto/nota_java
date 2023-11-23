
import java.util.Date;

public class Seguro {
    private int seguroId;
    private String tipoSeguro;
    private double valor;
    private Date dataContratacao;
    private int pessoaId; // Referência à tabela Pessoa
    private int veiculoId; // Referência à tabela Veiculo;

    public int getSeguroId() {
        return seguroId;
    }

    public void setSeguroId(int seguroId) {
        this.seguroId = seguroId;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public Date getDataContratacao() {
        return dataContratacao;
    }

    public void setDataContratacao(Date dataContratacao) {
        this.dataContratacao = dataContratacao;
    }

    public int getPessoaId() {
        return pessoaId;
    }

    public void setPessoaId(int pessoaId) {
        this.pessoaId = pessoaId;
    }

    public int getVeiculoId() {
        return veiculoId;
    }

    public void setVeiculoId(int veiculoId) {
        this.veiculoId = veiculoId;
    }
}

