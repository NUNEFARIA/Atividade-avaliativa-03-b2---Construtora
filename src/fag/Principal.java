package fag;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import fag.objetos.Construcao;
import fag.objetos.Funcionario;

public class Principal {

    public static ArrayList<Construcao> listConstru = new ArrayList<>();
    public static ArrayList<Funcionario> listFunci = new ArrayList<>();

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        mostraMenuInicial();

        System.out.println("\n\nSAINDO...");
    }

    // =========================== MENU INICIAL =============================

    public static void mostraMenuInicial() {
        while (true) {
            System.out.printf("\n==================================================");
            System.out.printf("\n===================== INICIO =====================");
            System.out.printf("\n==================================================");
            System.out.println("\n1 - Menu Construções\n2 - Menu Funcionarios\n0 - Para Sair\n\nOpção: ");

            int op = lerInt();

            switch (op) {
            case 1:
                mostraMenuConst();
                break;
            case 2:
                mostraMenuFunc();
                break;
            case 0:
                return;
            default:
                System.out.println("\nOPÇÃO INVALIDA! TENTE NOVAMENTE!\n");
            }
        }
    }

    private static int lerInt() {
        while (true) {
            try {
                int v = Integer.parseInt(scanner.nextLine());
                return v;
            } catch (Exception e) {
                System.out.println("Valor inválido! Digite novamente:");
            }
        }
    }

    // =====================================================================
    // ========================= CONSTRUÇÕES ===============================
    // =====================================================================

    private static void mostraMenuConst() {
        while (true) {
            System.out.printf("\n==================================================");
            System.out.printf("\n================== CONSTRUÇÕES ===================");
            System.out.printf("\n==================================================");
            System.out.println(
                    "\n0 - Voltar\n1 - Adicionar Construção\n2 - Listar Construções\n3 - Editar construção\n4 - Excluir Construção\n\nOpção: ");

            int op = lerInt();

            switch (op) {
            case 0:
                return;
            case 1:
                adicionaConst();
                break;
            case 2:
                listaConst();
                break;
            case 3:
                editaConst();
                break;
            case 4:
                excluiConst();
                break;
            default:
                System.out.println("\nOPÇÃO INVALIDA! TENTE NOVAMENTE!\n");
            }
        }
    }

    // ADICIONAR CONSTRUÇÃO
    public static void adicionaConst() {
        System.out.println("\nAdicionar Construção ==============================");
        System.out.println("\nNome: ");
        String nome = scanner.nextLine();
        System.out.println("\nEndereço: ");
        String endereco = scanner.nextLine();
        System.out.println("\nData de inicio: ");
        String dataInicio = scanner.nextLine();

        listConstru.add(new Construcao(nome, endereco, dataInicio));
        System.out.println("\nConstrução adicionada com sucesso!");
    }

    // LISTAR
    private static void listaConst() {
        if (listConstru.isEmpty()) {
            System.out.println("\nNenhuma construção cadastrada.");
            return;
        }

        System.out.println("\nLISTA DE CONSTRUÇÕES:");
        for (Construcao c : listConstru) {
            c.exibirConst();
        }
    }

    // EDITAR
    private static void editaConst() {
        listaConst();
        if (listConstru.isEmpty()) return;

        System.out.println("\nNome da construção para editar:");
        String nome = scanner.nextLine();

        Construcao alvo = buscarConstrucao(nome);

        if (alvo == null) {
            System.out.println("Construção não encontrada!");
            return;
        }

        mostraMenuEditConst(alvo);
    }

    private static Construcao buscarConstrucao(String nome) {
        for (Construcao c : listConstru) {
            if (c.getNomeConst().equalsIgnoreCase(nome)) {
                return c;
            }
        }
        return null;
    }

    private static void mostraMenuEditConst(Construcao c) {
        while (true) {
            System.out.printf("\nEditar Construção ===============================");
            System.out.println("\n0 - Voltar\n1 - Vincular funcionário\n2 - Desvincular funcionário\nOpção:");

            int op = lerInt();

            switch (op) {
            case 0:
                return;
            case 1:
                vincularFunc(c);
                break;
            case 2:
                desVincularFunc(c);
                break;
            default:
                System.out.println("Opção inválida.");
            }
        }
    }

    // Vincular funcionário
    private static void vincularFunc(Construcao c) {
        listaFunc();
        if (listFunci.isEmpty()) return;

        System.out.println("\nNome do funcionário para vincular:");
        String nome = scanner.nextLine();

        Funcionario f = buscarFuncionario(nome);

        if (f == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        c.vincula(f);
        f.vincula(c);

        System.out.println("Vinculação realizada!");
    }

    // Desvincular
    private static void desVincularFunc(Construcao c) {
        c.exibirConst();

        System.out.println("\nNome do funcionário para desvincular:");
        String nome = scanner.nextLine();

        Funcionario f = buscarFuncionario(nome);

        if (f == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        c.desVincula(f);
        f.desVincula(c);

        System.out.println("Desvinculado com sucesso!");
    }

    // EXCLUIR
    private static void excluiConst() {
        listaConst();
        if (listConstru.isEmpty()) return;

        System.out.println("\nNome da construção para excluir:");
        String nome = scanner.nextLine();

        Iterator<Construcao> it = listConstru.iterator();

        while (it.hasNext()) {
            Construcao c = it.next();
            if (c.getNomeConst().equalsIgnoreCase(nome)) {

                // desvincular todos os envolvidos
                for (Funcionario f : listFunci) {
                    if (f.getConstrucao() == c) {
                        f.desVincula(c);
                    }
                }

                it.remove();
                System.out.println("Construção removida!");
                return;
            }
        }

        System.out.println("Construção não encontrada!");
    }

    // =====================================================================
    // =========================== FUNCIONÁRIOS ============================
    // =====================================================================

    private static void mostraMenuFunc() {
        while (true) {
            System.out.printf("\n==================================================");
            System.out.printf("\n================== FUNCIONÁRIOS ==================");
            System.out.printf("\n==================================================");
            System.out.println(
                    "\n0 - Voltar\n1 - Adicionar funcionário\n2 - Listar funcionários\n3 - Editar funcionário\n4 - Excluir funcionário\n\nOpção: ");

            int op = lerInt();

            switch (op) {
            case 0:
                return;
            case 1:
                adicionaFunc();
                break;
            case 2:
                listaFunc();
                break;
            case 3:
                editaFunc();
                break;
            case 4:
                excluiFunc();
                break;
            default:
                System.out.println("\nOPÇÃO INVÁLIDA!\n");
            }
        }
    }

    // ADICIONAR FUNCIONÁRIO
    private static void adicionaFunc() {
        System.out.println("\nAdicionar Funcionário ==============================");
        System.out.println("\nNome: ");
        String nome = scanner.nextLine();
        System.out.println("\nCargo: ");
        String cargo = scanner.nextLine();
        System.out.println("\nSalário: ");
        double salario = Double.parseDouble(scanner.nextLine());

        listFunci.add(new Funcionario(nome, cargo, salario));
        System.out.println("\nFuncionário adicionado!");
    }

    // LISTAR
    private static void listaFunc() {
        if (listFunci.isEmpty()) {
            System.out.println("\nNenhum funcionário cadastrado.");
            return;
        }

        System.out.println("\nLISTA DE FUNCIONÁRIOS:");
        for (Funcionario f : listFunci) {
            f.exibirFunc();
        }
    }

    // EDITAR FUNCIONÁRIO
    private static void editaFunc() {
        listaFunc();
        if (listFunci.isEmpty()) return;

        System.out.println("\nNome do funcionário para editar:");
        String nome = scanner.nextLine();

        Funcionario f = buscarFuncionario(nome);

        if (f == null) {
            System.out.println("Funcionário não encontrado!");
            return;
        }

        System.out.println("\nNovo nome (enter para manter):");
        String novoNome = scanner.nextLine();
        if (!novoNome.isBlank()) f.setNome(novoNome);

        System.out.println("Novo cargo (enter para manter):");
        String novoCargo = scanner.nextLine();
        if (!novoCargo.isBlank()) f.setCargo(novoCargo);

        System.out.println("Novo salário (enter para manter):");
        String novoSal = scanner.nextLine();
        if (!novoSal.isBlank()) {
            try {
                double val = Double.parseDouble(novoSal);
                f.setSalario(val);
            } catch (Exception e) {
                System.out.println("Salário inválido, mantendo o anterior.");
            }
        }

        System.out.println("Funcionário atualizado!");
    }

    private static Funcionario buscarFuncionario(String nome) {
        for (Funcionario f : listFunci) {
            if (f.getNomeFun().equalsIgnoreCase(nome)) {
                return f;
            }
        }
        return null;
    }

    // EXCLUIR
    private static void excluiFunc() {
        listaFunc();
        if (listFunci.isEmpty()) return;

        System.out.println("\nNome do funcionário para excluir:");
        String nome = scanner.nextLine();

        Iterator<Funcionario> lista = listFunci.iterator();

        while (lista.hasNext()) {
            Funcionario f = lista.next();
            if (f.getNomeFun().equalsIgnoreCase(nome)) {

                if (f.getConstrucao() != null) {
                    f.getConstrucao().desVincula(f);
                }

                lista.remove();

                System.out.println("Funcionário removido!");
                return;
            }
        }

        System.out.println("Funcionário não encontrado!");
    }

}
