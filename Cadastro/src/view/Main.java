package view;

import dao.CadastroDao;
import model.Cadastro;
import model.Pessoa;

import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

      CadastroDao cadastroDao = new CadastroDao();
      Scanner scanner = new Scanner(System.in);
      Cadastro cadastro = new Cadastro();
        // Carrega os cadastros existentes do arquivo
        Set<Cadastro> cadastros = cadastroDao.getCadastros();
        if (!cadastros.isEmpty()) {
            cadastro = cadastros.iterator().next(); // Assume que há apenas um Cadastro no arquivo
        }

      while (true){
          System.out.println("\n1. Salvar uma pessoa");
          System.out.println("2. Listar todas as pessoas");
          System.out.println("3. Deletar uma pessoa pelo e-mail");
          System.out.println("4. Sair");
          System.out.print("Escolha uma opção: ");

          int opcao = scanner.nextInt();
          scanner.nextLine();
          switch (opcao){
              case 1:
                  System.out.print("Digite o nome: ");
                  String nome = scanner.nextLine();
                  System.out.println("Digite  o email");
                  String email = scanner.nextLine();

                  if(cadastro.buscarPorEmail(email)){
                      System.out.println("Email ja existe");
                  }else{
                      Pessoa pessoa = new Pessoa(nome, email);
                      cadastro.addPessoa(pessoa);
                      if(cadastroDao.salvar(cadastro)){
                          System.out.println("Pessoa salva com sucesso");
                      }
                      else{
                          System.out.println("Erro ao salvar Pessoa");
                      }
                  }
                  break;
              case 2:
                 Set<Pessoa> pessoas=  cadastro.getPessoas();
                 for(Pessoa p:pessoas){
                     System.out.println(p);
                 }
                  break;
              case 3:
                  System.out.println("Digite o nome a ser deletado");
                  String nomeDeletar = scanner.nextLine();
                  System.out.print("Digite o e-mail para deletar: ");
                  String emailParaDeletar = scanner.nextLine();
                  Pessoa pessoaParaDeletar = new Pessoa(nomeDeletar, emailParaDeletar);

                  if (cadastro.buscarPorEmail(emailParaDeletar)) {
                      cadastro.removerPessoa(pessoaParaDeletar);
                      if (cadastroDao.salvar(cadastro)) {
                          System.out.println("Pessoa deletada com sucesso!");
                      } else {
                          System.out.println("Erro ao deletar a pessoa.");
                      }
                  } else {
                      System.out.println("Nenhuma pessoa encontrada com esse e-mail.");
                  }
                  break;
              case 4:
                  System.out.println("Saindo...");
                  scanner.close();
                  return;

              default:
                  System.out.println("Opção inválida, tente novamente.");
          }

      }

    }

}