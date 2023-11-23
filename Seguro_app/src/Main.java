import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        // Crie uma instância de Pessoa
        Pessoa p1 = new Pessoa();
        p1.setIdPessoa(2);
        p1.setNomeCompleto("manel");
        p1.setCpf("7111111110");
        p1.setTelefone("8399966696");
        p1.setEmail("eu@gmail.com");

        /* Crie uma instância de PessoaDAO */
        PessoaDAO pessoaDAO;
        pessoaDAO = new PessoaDAO();
        boolean adiciona = pessoaDAO.adiciona(p1);


        // Liste todas as pessoas no banco de dados
        ArrayList<Pessoa> pessoa = pessoaDAO.listar();

        // Itere sobre a lista e imprima os detalhes de cada pessoa
        for (Pessoa pessoa2 : pessoa) {
            System.out.println("ID: " + pessoa2.getIdPessoa() +
                    ", Nome: " + pessoa2.getNomeCompleto() +
                    ", CPF: " + pessoa2.getCpf() +
                    ", Email: " + pessoa2.getEmail() +
                    ", Telefone: " + pessoa2.getTelefone());
        }
    }
}
