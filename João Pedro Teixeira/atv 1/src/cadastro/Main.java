package cadastro;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner entrada = new Scanner(System.in);
    static ArrayList<Produto> produtos = new ArrayList<>();

    public static void main(String[] args) {

        int opcao;
        do {
            System.out.println("\n== SISTEMA DE GERENCIAMENTO ==");
            System.out.println("1 - Cadastrar produto");
            System.out.println("2 - Listar todos");
            System.out.println("3 - Buscar por ID");
            System.out.println("4 - Atualizar produto");
            System.out.println("5 - Excluir produto");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opcao: ");

            opcao = lerInteiro();
            switch (opcao) {
                case 1:
                    cadastrar();
                    break;          
                case 2:
                    listar();
                    break;
                case 3:
                    buscarPorId();
                    break;
                case 4:
                    atualizar();
                    break;
                case 5:
                    excluir();
                    break;
                case 0:
                    System.out.println("Sistema encerrado!");
                    break;
                default:
                    System.out.println("Opcao errada!");
            }

        } while (opcao != 0);
        entrada.close();
    }

    public static void cadastrar() {

        System.out.println("\n--- CADASTRAR PRODUTO ---");
        System.out.print("Digite o ID: ");
        int id = lerInteiro();
        if (id <= 0) {
            System.out.println("O ID deve ser maior que zero!");
            return;
        }
        if (buscarProduto(id) != null) {
            System.out.println("Erro: esse ID ja esta cadastrado!");
            return;
        }
        System.out.print("Digite o nome: ");
        String nome = entrada.nextLine();
        if (nome.trim().isEmpty()) {
            System.out.println("O nome nao pode ficar vazio!");
            return;
        }
        System.out.print("Digite o preco: ");
        double preco = lerPreco();

        if (preco < 0) {
            System.out.println("O preco nao pode ser negativo!");
            return;
        }

        Produto produto = new Produto(id, nome, preco);
        produtos.add(produto);

        System.out.println("Produto cadastrado com sucesso!");
    }

    public static void listar() {

        System.out.println("\n--- LISTA DE PRODUTOS ---");

        if (produtos.isEmpty()) {
            System.out.println("Nenhum produto cadastrado.");
            return;
        }

        for (Produto produto : produtos) {
            exibirProduto(produto);
        }
    }

    public static void buscarPorId() {

        System.out.print("\nDigite o ID do produto: ");
        int id = lerInteiro();

        Produto produto = buscarProduto(id);

        if (produto == null) {
            System.out.println("Produto nao encontrado!");
        } else {
            exibirProduto(produto);
        }
    }

    public static void atualizar() {

        System.out.print("\nDigite o ID do produto que deseja atualizar: ");
        int id = lerInteiro();

        Produto produto = buscarProduto(id);

        if (produto == null) {
            System.out.println("Produto nao encontrado!");
            return;
        }

        System.out.print("Digite o novo nome: ");
        String nome = entrada.nextLine();

        if (nome.trim().isEmpty()) {
            System.out.println("O nome nao pode ficar vazio!");
            return;
        }

        System.out.print("Digite o novo preco: ");
        double preco = lerPreco();

        if (preco < 0) {
            System.out.println("O preco nao pode ser negativo!");
            return;
        }

        produto.setNome(nome);
        produto.setPreco(preco);

        System.out.println("Produto atualizado com sucesso!");
    }

    public static void excluir() {

        System.out.print("\nDigite o ID do produto que deseja excluir: ");
        int id = lerInteiro();

        Produto produto = buscarProduto(id);

        if (produto == null) {
            System.out.println("Produto nao encontrado!");
            return;
        }

        produtos.remove(produto);

        System.out.println("Produto excluido com sucesso!");
    }

    public static Produto buscarProduto(int id) {

        for (Produto produto : produtos) {
            if (produto.getId() == id) {
                return produto;
            }
        }

        return null;
    }

    public static void exibirProduto(Produto produto) {

        System.out.println("------------------------------");
        System.out.println("ID: " + produto.getId());
        System.out.println("Nome: " + produto.getNome());
        System.out.printf("Preco: R$ %.2f%n", produto.getPreco());
    }

    public static int lerInteiro() {

        while (true) {
            try {
                return Integer.parseInt(entrada.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Digite um numero inteiro valido: ");
            }
        }
    }

    public static double lerPreco() {

        while (true) {
            try {
                double valor = Double.parseDouble(
                    entrada.nextLine().trim().replace(",", ".")
                );

                if (Double.isNaN(valor) || Double.isInfinite(valor)) {
                    System.out.print("Digite um preco valido: ");
                } else {
                    return valor;
                }

            } catch (NumberFormatException e) {
                System.out.print("Digite um preco valido: ");
            }
        }
    }
}
