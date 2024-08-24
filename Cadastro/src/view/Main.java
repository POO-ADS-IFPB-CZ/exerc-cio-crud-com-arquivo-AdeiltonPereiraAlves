package view;

import dao.CadastroDao;
import model.Pessoa;

import java.util.Scanner;
import java.util.Set;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

      CadastroDao cadastroDao = new CadastroDao();
      Scanner scanner = new Scanner(System.in);

        // Carrega os cadastros existentes do arquivo
        Set<Pessoa> pessoas = cadastroDao.getPessoas();


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

                  if(cadastroDao.buscarPorEmail(email)){
                      System.out.println("Email ja existe");
                  }else{
                      Pessoa pessoa = new Pessoa(nome, email);

                      if(cadastroDao.salvar(pessoa)){
                          System.out.println("Pessoa salva com sucesso");
                      }
                      else{
                          System.out.println("Erro ao salvar Pessoa");
                      }
                  }
                  break;
              case 2:
                  Set<Pessoa> pessoaAs = cadastroDao.getPessoas();
                 for(Pessoa p : pessoaAs){
                     System.out.println(p);
                 }
                  break;
              case 3:

                  System.out.print("Digite o e-mail para deletar: ");
                  String emailParaDeletar = scanner.nextLine();


                  if (cadastroDao.deletarPessoaPorEmail(emailParaDeletar)) {


                          System.out.println("Pessoa deletada com sucesso!");

                 }else {
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