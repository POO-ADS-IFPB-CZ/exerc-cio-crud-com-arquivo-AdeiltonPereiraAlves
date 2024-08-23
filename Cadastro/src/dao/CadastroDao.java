package dao;

import model.Cadastro;

import java.io.*;
import java.util.HashSet;
import java.util.Set;

public class CadastroDao{

    private File arquivo;

    public CadastroDao(){
        arquivo = new File("cadastro.ser");
        if(!arquivo.exists()){
            try {
                arquivo.createNewFile();

            }
            catch (IOException e){
                System.out.println("Falha ao criar arquivo");
            }
        }
    }
    public Set<Cadastro> getCadastros(){
        if(arquivo.length()>0){
            try{
                FileInputStream inputStream = new FileInputStream(arquivo);
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                Set<Cadastro> cadastros = (Set<Cadastro>) objectInputStream.readObject();
                return cadastros;
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

    public boolean salvar(Cadastro cadastro){
        Set<Cadastro> cadastros = getCadastros();
        if(cadastros.add(cadastro)){
            try {
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(cadastros);
                return true;
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }

    public boolean deletar(Cadastro cadastro) {
        Set<Cadastro> cadastros = getCadastros();
        if(cadastros.remove(cadastro)){
            try{
                FileOutputStream outputStream = new FileOutputStream(arquivo);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(cadastros);
                return true;
            } catch (FileNotFoundException e) {
                System.out.println("Arquivo não encontrado");
            } catch (IOException e) {
                System.out.println("Falha ao escrever no arquivo");
            }

        }
        return false;
    }

}
