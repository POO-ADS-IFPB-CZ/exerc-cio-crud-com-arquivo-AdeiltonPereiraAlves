package model;

import java.io.Serializable;
import java.util.Objects;

public class Pessoa implements Serializable {
    private String nome;
    private String email;
    private long serialVersionUID;

    public Pessoa(String nome, String email) {
        this.nome = nome;
        this.email = email;
        serialVersionUID = 1L;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return Objects.equals(email, pessoa.email);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
