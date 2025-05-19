public class Cliente {
    private String nome, endereco, cpf, telefone;

    public Cliente(String nome, String endereco, String cpf, String telefone) {
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public void cadastrar(String nome, String endereco, String cpf, String telefone) {}

    public void editar(String nome, String endereco, String cpf, String telefone) {}

    public void excluir(String cpf) {}
}
