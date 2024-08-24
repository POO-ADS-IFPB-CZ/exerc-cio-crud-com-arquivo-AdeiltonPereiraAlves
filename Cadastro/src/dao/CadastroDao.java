package dao;

import model.Pessoa;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CadastroDao{

    private File arquivo;

    public CadastroDao(){
        arquivo = new File("pessoas.ser");
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();

            }
            catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }
    }
    public Set<Pessoa> getPessoas(){
        if(arquivo.length()>0){
            try{
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Pessoa> pessoas = (Set<Pessoa>) objectInputStream.readObject();
                return pessoas;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
        return new HashSet<>();
    }

    public boolean salvar(Pessoa pessoa){
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.add(pessoa)){
            try {
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
    private void salvarPessoas(Set<Pessoa> pessoas) {
        try (FileOutputStream outputStream = new FileOutputStream(arquivo);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream)) {
            objectOutputStream.writeObject(pessoas);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean buscarPorEmail(String email) {
        Set<Pessoa> pessoas = getPessoas();
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
    public boolean deletar(Pessoa pessoa) {
        Set<Pessoa> pessoas = getPessoas();
        if(pessoas.remove(pessoa)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(pessoas);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }

        }
        return false;
    }
    public boolean deletarPessoaPorEmail(String email) {
        Set<Pessoa> pessoas = getPessoas();
        Pessoa pessoaParaDeletar = null;

        // Buscar a pessoa pelo e-mail
        for (Pessoa pessoa : pessoas) {
            if (pessoa.getEmail().equals(email)) {
                pessoaParaDeletar = pessoa;
                break;
            }
        }

        // Remover a pessoa se encontrada
        if (pessoaParaDeletar != null) {
            pessoas.remove(pessoaParaDeletar);
            salvarPessoas(pessoas);
            return true;
        }

        return false; // Retorna falso se a pessoa não foi encontrada
    }


}
