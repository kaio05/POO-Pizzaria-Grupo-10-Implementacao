package br.edu.ufersa.poo.pizzaria.entity;
import java.util.UUID;
import br.edu.ufersa.poo.pizzaria.db.BancoFake;

public class Cliente {
    private UUID id;
    private String nome, endereco, cpf, telefone;

    public Cliente(String nome, String endereco, String cpf, String telefone) {
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public Cliente(UUID id, String nome, String endereco, String cpf, String telefone) {
        setId(id);        
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public Cliente(UUID id) {
        setId(id);
    }

    public Cliente() {}

    public void cadastrar(String nome, String endereco, String cpf, String telefone) {
        if(!(validarNull(nome) && validarNull(endereco) && validarNull(cpf) && validarNull(telefone)))
            throw new Error("Preencha todos os campos corretamente!");
        Cliente cliente = new Cliente(nome, endereco, cpf, telefone);
        if (BancoFake.exists(cliente)) {
            throw new Error("Cliente já cadastrado!");
        }
        cliente.id = UUID.randomUUID();
        BancoFake.save(cliente);
    }

    public void editar(UUID id, String nome, String endereco, String cpf, String telefone) {
        Cliente cliente = new Cliente(id, nome, endereco, cpf, telefone);
        if (!BancoFake.exists(cliente)) {
            throw new Error("Cliente não existe!");
        }
        BancoFake.update(cliente);
    }

    public void excluir(UUID id) {
        Cliente cliente = new Cliente(id);
        if (!BancoFake.exists(cliente)) {
            throw new Error("Cliente não existe!");
        }
        BancoFake.remove(cliente);
    }
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        if(!id.equals(""))
            this.id = id;
        else
            throw new Error("Argumento inválido do tipo UUID!");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if(nome != "")
            this.nome = nome;
        else
            throw new Error("Argumento inválido do tipo String!");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if(endereco != "")
            this.endereco = endereco;
        else
            throw new Error("Argumento inválido do tipo String!");
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if(cpf != "")
            this.cpf = cpf;
        else
            throw new Error("Argumento inválido do tipo String!");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if(telefone != "")
            this.telefone = telefone;
        else
            throw new Error("Argumento inválido do tipo String!");
    }

    public boolean validarNull(String campo) {
        return campo != null;
    }
}
