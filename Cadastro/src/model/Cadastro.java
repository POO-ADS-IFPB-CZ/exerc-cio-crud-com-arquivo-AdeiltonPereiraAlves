package model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;
public class Cadastro implements Serializable {

    private Set<Pessoa> pessoas;
    private long serialVersionUID;
    public Cadastro(){
        serialVersionUID = 1L;
        this.pessoas = new HashSet<>();
    }
   public Set<Pessoa> getPessoas(){
        return pessoas;
   }
   public boolean addPessoa(Pessoa pessoa){
        return this.pessoas.add(pessoa);
   }
   public boolean removerPessoa(Pessoa pessoa){
        return this.pessoas.remove(pessoa);
   }
   public boolean containsPessoa(Pessoa pessoa){
        return this.pessoas.contains(pessoa);
   }
    public boolean buscarPorEmail(String email ){
        for(Pessoa p : pessoas){
            if(p.getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "pessoas=" + pessoas +
                '}';
    }

}
