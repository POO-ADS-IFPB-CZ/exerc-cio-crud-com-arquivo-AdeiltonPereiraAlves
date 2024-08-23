package view;

import dao.CadastroDao;
import model.Cadastro;
import model.Pessoa;

import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
       CadastroDao cadastroDao = new CadastroDao();

        Set<Cadastro> cadastros = cadastroDao.getCadastros();
         Cadastro cadastro;
        if(cadastros.isEmpty()){
            cadastro = new Cadastro();
        }
        else{
            cadastro = cadastros.iterator().next();
        }
        Pessoa pessoa = new Pessoa("Otimus Prime", "otimus@email.com");
        Pessoa pessoa2 = new Pessoa("Lango Lango", "otimus@email.com");

        String email = pessoa.getEmail();
        if(cadastro.buscarPorEmail(email)){

            System.out.println("pessoa ja cadastrada");

        }else{

            cadastro.addPessoa(pessoa);
            cadastro.addPessoa(pessoa2);
            if(cadastroDao.salvar(cadastro)){
                System.out.println("cadastro realizado com sucesso");
            }else {
                System.out.println("Erro");
            }



        }



    }

}