package br.edu.ufersa.poo.pizzaria.model.entities;
import java.util.UUID;
import jakarta.persistence.*;

@Entity
@Table(name = "clientes")
public class Cliente implements Entidade {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    public Cliente(String nome, String endereco, String cpf, String telefone) {
        setNome(nome);
        setEndereco(endereco);
        setCpf(cpf);
        setTelefone(telefone);
    }

    public Cliente() {}

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String endereco;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false, unique = true)
    private String telefone;

    public UUID getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome != null && !nome.isEmpty())
            this.nome = nome;
        else
            System.out.println("Nome não pode ser nulo");
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        if (endereco != null && !endereco.isEmpty())
            this.endereco = endereco;
        else
            System.out.println("Endereço não pode ser nulo");
    }

    public String getCpf() {
        return cpf;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public void setCpf(String cpf) {
        if (cpf != null && !cpf.isEmpty())
            this.cpf = cpf;
        else
            System.out.println("CPF não pode ser nulo");
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        if (telefone != null && !telefone.isEmpty())
            this.telefone = telefone;
        else
            System.out.println("Telefone não pode ser nulo");
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
