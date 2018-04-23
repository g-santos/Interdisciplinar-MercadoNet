package mercadonet.view;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opet.util.Reader;

public class MercadoNetView {

	public static void main(String[] args) throws Exception {
		while (MENU()) {
		}

	}

	public static boolean MENU() throws Exception {
		int op;

		System.out.println("--Mercado.Net--");
		System.out.println("1 - CATEGORIAS");
		System.out.println("2 - PRODUTOS");
		System.out.println("3 - CLIENTE");
		System.out.println("4 - SAIR");
		op = Reader.readInt();

		switch (op) {
		case 1:
			while (submenu(1));
			break;

		case 2:
			while (submenu(2));
				break;
		case 3:
			while (submenu(3));
				break;

		case 4:

			System.out.println("Saindo...");
			return false;
		}
		return true;
	}

	public static boolean submenu(int opcao) throws Exception {
		int op;
		System.out.println("1 - CADASTRAR");
		System.out.println("2 - LISTAR");
		System.out.println("3 - PESQUISAR");
		System.out.println("4 - ALTERAR");
		System.out.println("5 - EXCLUIR");
		System.out.println("6 - VOLTAR AO MENU PRINCIPAL");

		op = Reader.readInt();

		switch (op) {

		case 1:
			if (opcao == 1) {
				if (Categoria.cadastrarCategoria()) {
					System.out.println("CATEGORIA CADASTRADA");
				} else {
					System.out.println("CATEGORIA NÃO CADASTRADA");
				}
				System.out.println("");
			}
			if (opcao == 2) {
				if (Produto.cadastrarProduto()) {
					System.out.println("\n PRODUTO CADASTRADO");
				} else {
					System.out.println("\n PRODUTO NÃO CADASTRADO (JÁ EXISTE PRODUTO COM O MESMO ID)");
				}
				System.out.println("");
			}
			if (opcao == 3) {
				if (Cliente.cadastrarCliente()) {
					System.out.println("\n CLIENTE CADASTRADO");
				} else {
					System.out.println("\n CLIENTE NÃO CADASTRADO (JÁ EXISTE CLIENTE COM O MESMO ID)");
				}
				System.out.println("");
			}
			break;

		case 2:
			if (opcao == 1) {
				Categoria.listarCategorias();
			} else if (opcao == 2) {
					Produto.listarProduto();
			}
			else if (opcao == 3) {
				Cliente.listarCliente();
			}
			break;

		case 3:

			if (opcao == 1) {
				System.out.println("QUAL ID_CATEGORIA DESEJA PESQUISAR?");
				int idcategoria = Reader.readInt();
				Categoria categoria = Categoria.pesquisarCategoriaid(idcategoria);
				if (Categoria.pesquisarCategoriaid(idcategoria) != null) {
					System.out.println("NOME: " + categoria.nome + "\n");
				} else {
					System.out.println("Produto nao encontrado!\n");
				}

			} else {
				if (opcao == 2) {
					System.out.println("QUAL ID_PRODUTO DESEJA PESQUISAR?");
					int idproduto = Reader.readInt();
					Produto produto = Produto.pesquisarProdutoid(idproduto);
					if (Produto.pesquisarProdutoid(idproduto) != null) {
						System.out.println("NOME: " + produto.nome + ", PRECO: " + produto.preco + " MARCA: "
								+ produto.marcaprod + "\n");
					} else {
						System.out.println("Produto nao encontrado!\n");
					}

				}
				if (opcao == 3) {
					System.out.println("QUAL ID_CLIENTE DESEJA PESQUISAR?");
					int idcliente = Reader.readInt();
					Cliente.pesquisarClienteid(idcliente);
				}
			}
			break;

		case 4:
			if (opcao == 1) {
				Categoria.alterarCategoria();
			} else {
				if (opcao == 2) {
					Produto.alterarProduto();
				}
				if (opcao == 3) {
					Cliente.alterarCliente();
				}
			}
			break;

		case 5:
			if (opcao == 1) {
				Categoria.deletarCategoria();
			} else {
				if (opcao == 2) {
					Produto.deletarProduto();
				}
				if (opcao == 3) {
					Cliente.deletarCliente();
				}
			}

			break;

		case 6:

			System.out.println("");
			return false;

		}

		return true;
	}

}
