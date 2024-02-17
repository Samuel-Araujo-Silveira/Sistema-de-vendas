package com.entidades.sistema;
import com.entidades.dao.*;
import com.entidades.conexao.*;
import javax.sound.midi.MidiChannel;
import java.util.Scanner;
import java.util.ArrayList;

public class TesteSisteminha
{
    public static ArrayList<Produto> produtos;
    public static ArrayList<ItensVenda> itens;
    public static ArrayList<Cliente> clientes;
    public static Scanner scanner;
    public static DaoCliente daoCliente;
    public static DaoProduto daoProduto;
    public static DaoItemVenda daoItemVenda;
    public static DaoVenda daoVenda;
    public static DaoCaixa daoCaixa;
    public static Cliente clienteAuxiliar;
    public static ProdutoNaoPerecivel produtoNaoPerecivelAuxiliar;
    public static ProdutoPerecivel produtoPerecivelAuxiliar;
    public static ItensVenda itensVendaAuxiliar;
    public static double subtotal;
    public static Caixa caixa;

    public static void main(String[] args)
    {
        iniciarSistema();
    }

    public static void iniciarSistema()
    {
        inicializarVariaveis();
        menu();
    }

    public static void inicializarVariaveis()
    {
        clientes = new ArrayList<>();
        produtos = new ArrayList<>();
        itens = new ArrayList<>();
        scanner = new Scanner(System.in);
        daoCliente = new DaoCliente();
        daoProduto = new DaoProduto();
        daoItemVenda = new DaoItemVenda();
        daoVenda = new DaoVenda();
        daoCaixa = new DaoCaixa();
        caixa = new Caixa(daoVenda.getValorDeTodasAsCompras());
        daoCaixa.cadastrarCaixa(caixa);
        subtotal = 0;
        preencherArrayLists(produtos,clientes);
    }

    public static void menu()
    {
        int opcaoMenuGeral=0;
        while(opcaoMenuGeral != 5)
        {
            System.out.println("========MENU=========");
            System.out.println("1 - GERENCIAR CLIENTES");
            System.out.println("2 - GERENCIAR PRODUTOS");
            System.out.println("3 - GERENCIAR VENDAS");
            System.out.println("4 - MOSTRAR CAIXA");
            System.out.println("5 - SAIR");
            opcaoMenuGeral = scanner.nextInt();
            scanner.nextLine();


            if(opcaoMenuGeral==1)
            {
                gerenciarClientes();
            }
            else if(opcaoMenuGeral==2)
            {
                gerenciarProdutos();
            }
            else if(opcaoMenuGeral==3)
            {
                gerenciarVendas();
            }
            else if(opcaoMenuGeral==4)
            {
                consultarCaixa();
            }
            else if(opcaoMenuGeral==5)
            {
                break;
            }

        }

    }

    public static void gerenciarClientes()
    {
        int opcaoMenuCliente=0;
        while(opcaoMenuCliente != 5)
        {
            System.out.println("========MENU=========");
            System.out.println("1 - ADICIONAR CLIENTE");
            System.out.println("2 - MOSTRAR CLIENTES");
            System.out.println("3 - ATUALIZAR CLIENTE");
            System.out.println("4 - DELETAR CLIENTE");
            System.out.println("5 - SAIR");
            opcaoMenuCliente = scanner.nextInt();
            scanner.nextLine();

            if(opcaoMenuCliente==1)
            {
                adicionarCliente();
                reorganizaArrayDeCliente(clientes);
            }
            else if(opcaoMenuCliente==2)
            {
                visualizarClientes();
            }
            else if(opcaoMenuCliente==3)
            {
                atualizarCliente();
                reorganizaArrayDeCliente(clientes);

            }
            else if(opcaoMenuCliente==4)
            {
                deletarCliente();
                reorganizaArrayDeCliente(clientes);

            }
            else if(opcaoMenuCliente==5)
            {
                break;
            }
        }
    }

    public static void adicionarCliente()
    {
        String nome,endereco;

        System.out.println("DIGITE O NOME: ");
        nome = scanner.nextLine();

        System.out.println("DIGITE O ENDEREÇO: ");
        endereco = scanner.nextLine();

        clienteAuxiliar = new Cliente(nome,endereco);
        daoCliente.cadastrarCliente(clienteAuxiliar);
    }

    public static void visualizarClientes()
    {
        daoCliente.mostraClientes();
    }

    public static void atualizarCliente()
    {
        int idClinteAtualizar=0;
        String nome,endereco;

        visualizarClientes();
        System.out.println("ESCOLHA O CLIENTE PARA EDITAR: ");
        idClinteAtualizar = scanner.nextInt();
        scanner.nextLine();

        System.out.println("DIGITE NOVO NOME: ");
        nome = scanner.nextLine();

        System.out.println("DIGITE NOVO ENDERECO: ");
        endereco = scanner.nextLine();

        clienteAuxiliar = new Cliente(nome,endereco);

        daoCliente.editarCliente(clienteAuxiliar, idClinteAtualizar);
    }

    public static void deletarCliente()
    {
        int idClienteDeletar=0;
        visualizarClientes();
        System.out.println("ESCOLHA O CLIENTE PARA DELETAR: ");
        idClienteDeletar = scanner.nextInt();
        scanner.nextLine();

        daoCliente.deletarCliente(idClienteDeletar);
    }

    public static void gerenciarProdutos()
    {
        int opcaoMenuProduto=0;
        while(opcaoMenuProduto != 5)
        {
            System.out.println("========MENU=========");
            System.out.println("1 - ADICIONAR PRODUTO");
            System.out.println("2 - MOSTRAR PRODUTOS");
            System.out.println("3 - ATUALIZAR PRODUTOS");
            System.out.println("4 - DELETAR PRODUTO");
            System.out.println("5 - SAIR");
            opcaoMenuProduto = scanner.nextInt();
            scanner.nextLine();

            if(opcaoMenuProduto==1)
            {
                adicionarProduto();
                reorganizaArrayDeProduto(produtos);
            }
            else if(opcaoMenuProduto==2)
            {
                visualizarProdutos();
            }
            else if(opcaoMenuProduto==3)
            {
                atualizarProduto();
                reorganizaArrayDeProduto(produtos);
            }
            else if(opcaoMenuProduto==4)
            {
                deletarProduto();
                reorganizaArrayDeProduto(produtos);
            }
            else if(opcaoMenuProduto==5)
            {
                break;
            }
        }
    }

    public static void adicionarProduto()
    {
        String nome,material,validade;
        double preco;
        int quant, opcaoEscolherTipoProduto=0,codigo=0;

        while(opcaoEscolherTipoProduto != 3)
        {
            System.out.println("======ESCOLHA======");
            System.out.println("1 - Adicionar roduto não perecível");
            System.out.println("2 - Adicionar produto perecível");
            System.out.println("3 - SAIR");
            opcaoEscolherTipoProduto = scanner.nextInt();
            scanner.nextLine();

            if(opcaoEscolherTipoProduto==3){break;};

            System.out.println("DIGITE O CÓDIGO: ");
            codigo = scanner.nextInt();
            scanner.nextLine();

            while(!verificaSeCodigoJaExiste(codigo))
            {
                System.out.println("DIGITE OUTRO CÓDIGO: ");
                codigo = scanner.nextInt();
                scanner.nextLine();
            }

            System.out.println("DIGITE O NOME: ");
            nome = scanner.nextLine();

            System.out.println("DIGITE O PRECO: ");
            preco = scanner.nextDouble();
            scanner.nextLine();

            System.out.println("DIGITE A QUANTIDADE EM ESTOQUE: ");
            quant = scanner.nextInt();
            scanner.nextLine();

            if(opcaoEscolherTipoProduto==1)
            {
                System.out.println("DIGITE O MATERIAL: ");
                material = scanner.nextLine();
                produtoNaoPerecivelAuxiliar = new ProdutoNaoPerecivel(codigo,nome,preco,quant,material);
                daoProduto.adicionarProduto(produtoNaoPerecivelAuxiliar);
                produtos.add(produtoNaoPerecivelAuxiliar);

            }
            else
            {
                System.out.println("DIGITE A VALIDADE: ");
                validade = scanner.nextLine();
                produtoPerecivelAuxiliar = new ProdutoPerecivel(codigo,nome,preco,quant,validade);
                daoProduto.adicionarProduto(produtoPerecivelAuxiliar);
                produtos.add(produtoPerecivelAuxiliar);

            }
        }
    }

    public static void visualizarProdutos()
    {
        daoProduto.mostrarProdutos();
    }

    public static void atualizarProduto()
    {
        double novo_preco=0;
        int nova_quant=0,codigo=0;

        visualizarProdutos();
        System.out.println("SELECIONE UM PRODUTO PARA EDITAR: ");
        codigo = scanner.nextInt();
        scanner.nextLine();

        System.out.println("DIGITE A NOVA QUANTIDADE: ");
        nova_quant = scanner.nextInt();
        scanner.nextLine();

        System.out.println("DIGITE O NOVO PREÇO: ");
        novo_preco = scanner.nextDouble();
        scanner.nextLine();

        daoProduto.atualizarProduto(nova_quant,novo_preco,codigo);
    }

    public static void deletarProduto()
    {
        int codigo=0;
        visualizarProdutos();
        System.out.println("SELECIONE UM PRODUTO PARA EDITAR: ");
        codigo = scanner.nextInt();
        scanner.nextLine();

        daoProduto.deletarProduto(codigo);

    }

    public static void gerenciarVendas()
    {
        int opcaoGerenciarVenda=0;
        System.out.println("=====MENU=====");
        System.out.println("1 - VER VENDAS");
        System.out.println("2 - REALIZAR VENDA");
        opcaoGerenciarVenda = scanner.nextInt();
        scanner.nextLine();

        if(opcaoGerenciarVenda==1)
        {
            daoVenda.mostraVendas();
        }
        else
        {
            int opcaoCliente=0,opcaoProduto=0,quantProduto=0;
            boolean verificaQuantidade=false;

            visualizarClientes();
            System.out.println("ESCOLHA UM CLIENTE PARA REALIZAR COMPRA");
            opcaoCliente = scanner.nextInt();
            scanner.nextLine();

            visualizarProdutos();
            System.out.println("ESCOLHA UM PRODUTO PARA ADICIONAR AO CARRINHO");
            opcaoProduto = scanner.nextInt();
            scanner.nextLine();

            while(verificaQuantidade==false)
            {
                System.out.println("DIGITE A QUANTIDADE PARA SER COMPRADA: ");
                quantProduto = scanner.nextInt();
                scanner.nextLine();
                verificaQuantidade = daoProduto.retornarQuantEmEstoque(opcaoProduto, quantProduto);

            }
            itensVendaAuxiliar = new ItensVenda(pegaProduto(opcaoProduto),quantProduto,0);
            itens.add(itensVendaAuxiliar);
            subtotal += pegaProduto(opcaoProduto).getPreco() * quantProduto;
            System.out.println(subtotal);
            adicionarAoCarrinho(opcaoCliente);
        }

    }

    public static void adicionarAoCarrinho(int idCliente)
    {
        int opcaoProduto=0,quantProduto=0;
        boolean verificaQuantidade=false;
        int opcaoCarrinho=0;

        while(opcaoCarrinho != 2)
        {
            System.out.println("1 - ADICIONAR OUTRO PRODUTO AO CARRINHO");
            System.out.println("2 - FECHAR CARRINHO");
            System.out.println(subtotal);

            opcaoCarrinho = scanner.nextInt();

            if(opcaoCarrinho==1)
            {
                visualizarProdutos();
                System.out.println("ESCOLHA UM PRODUTO PARA ADICIONAR AO CARRINHO");
                opcaoProduto = scanner.nextInt();
                scanner.nextLine();

                while(verificaQuantidade==false)
                {
                    System.out.println("DIGITE A QUANTIDADE PARA SER COMPRADA: ");
                    quantProduto = scanner.nextInt();
                    scanner.nextLine();
                    verificaQuantidade = daoProduto.retornarQuantEmEstoque(opcaoProduto, quantProduto);
                    subtotal += pegaProduto(opcaoProduto).getPreco() * quantProduto;

                }
                itensVendaAuxiliar = new ItensVenda(pegaProduto(opcaoProduto),quantProduto,0);
                itens.add(itensVendaAuxiliar);
                adicionarAoCarrinho(idCliente);
            }
            else
            {
                realizarVenda(idCliente);
            }
        }
    }

    public static void realizarVenda(int IdCliente)
    {
        Venda venda = new Venda(itens, pegaCliente(IdCliente));
        daoVenda.cadastrarVenda(venda);
        System.out.println("VENDA REALIZADA! ");
        System.out.println("CLIENTE: " + pegaCliente(IdCliente).getNome());
        System.out.println("VALOR TOTAL: " + venda.getValorTotal());

        System.out.println("PRODUTOS COMPRADOS: ");
        for(ItensVenda i : itens)
        {
            i.setId_venda(venda.getId_venda());
            daoItemVenda.adicionarItemVenda(i, i.getQuantidade());
            System.out.println(i.getP().getNome());
        }
        caixa.setDinheiroEmCaixa(venda.getValorTotal());
        daoCaixa.atualizarCaixa(caixa);
        itens.clear();
        subtotal = 0;
    }

    public static void preencherArrayLists (ArrayList<Produto> produtos, ArrayList<Cliente> clientes)
    {
        daoCliente.preencherClientes(clientes);
        daoProduto.preencherProdutos(produtos);
    }

    public static  Produto pegaProduto(int codigoProduto)
    {
        for(Produto i : produtos)
        {
            if(i.getCodigo() == codigoProduto)
            {
                return i;
            }
        }
        return null;
    }

    public static Cliente pegaCliente(int idCliente)
    {
        for(Cliente i : clientes)
        {
            if(i.getId() == idCliente)
            {
                return i;
            }
        }
        return null;
    }

    public static void consultarCaixa()
    {
        daoCaixa.mostrarCaixa();
    }

    public static void reorganizaArrayDeCliente(ArrayList<Cliente> clientes)
    {
        clientes.clear();
        daoCliente.preencherClientes(clientes);
    }

    public static void reorganizaArrayDeProduto(ArrayList<Produto> produtos)
    {
        produtos.clear();
        daoProduto.preencherProdutos(produtos);
    }

    public static boolean verificaSeCodigoJaExiste(int codigo)
    {
        for(Produto i : produtos)
        {
            if(i.getCodigo() == codigo)
            {
                return false;
            }
        }
        return true;
    }
}

