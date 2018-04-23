package mercadonet.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opet.util.Reader;

public class ProdutoController {
	public int idproduto;
	public String nome;
	public double preco;
	public CategoriaController categoria;
	public String marcaprod;
	public Administrador administrdor;

	// Construtor com objetos com algumas informações pré definidas
	public ProdutoController(int idproduto, String nome, String marcaprod, double preco, CategoriaController categoria) {
		this.idproduto = idproduto;
		this.nome = nome;
		this.marcaprod = marcaprod;
		this.preco = preco;
		this.categoria = categoria;
	}
	public static void listarProduto() {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM PRODUTO");

			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("- " + rs.getString("NOME"));

			}
			System.out.println("");
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}
	}

	public static boolean cadastrarProduto() throws Exception {

		System.out.println("QUAL ID_PRODUTO? ");
		int idproduto = Reader.readInt();
		System.out.println("QUAL O NOME PRODUTO? ");
		String nome = Reader.readString();
		System.out.println("QUAL O PRECO? ");
		Double preco = Reader.readDouble();
		System.out.println("QUAL O NOME ID_CATEGORIA? ");
		int idcategoria = Reader.readInt();
		System.out.println("QUAL O NOME MARCAPROD? ");
		String marcaprod = Reader.readString();
		System.out.println("QUAL O NOME ID_ADM? ");
		int idadm = Reader.readInt();
		
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("INSERT INTO PRODUTO (ID_PRODUTO, NOME, PRECO, ID_CATEGORIA, MARCAPROD, ID_ADM) VALUES (?,?,?,?,?,?)");
			stmt.setInt(1, idproduto);
			stmt.setString(2, nome);
			stmt.setDouble(3, preco);
			stmt.setInt(4, idcategoria);
			stmt.setString(5, marcaprod);
			stmt.setInt(6, idadm);

			int rowsAffected = stmt.executeUpdate();

			if (rowsAffected > 0) {
				return true;
			} else {
				return false;
			}
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			Except.printStackTrace();
		}
		return false;
	}

	public static ProdutoController pesquisarProduto(String produtonome) throws SQLException {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM PRODUTO WHERE NOME = ?");
			stmt.setString(1, produtonome);

			rs = stmt.executeQuery();
			ProdutoController produto = null;
			while (rs.next()) {
				produto = new ProdutoController(rs.getInt("ID_PRODUTO"), rs.getString("NOME"), rs.getString("MARCAPROD"),
						rs.getDouble("PRECO"), CategoriaController.pesquisarCategoriaid(rs.getInt("ID_CATEGORIA")));
			}
			return produto;
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}

		return null;

	}

	public static ProdutoController pesquisarProdutoid(int produtoid) throws SQLException {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM PRODUTO WHERE ID_PRODUTO = ?");
			stmt.setInt(1, produtoid);

			rs = stmt.executeQuery();
			ProdutoController produto = null;
			while (rs.next()) {
				produto = new ProdutoController(rs.getInt("ID_PRODUTO"), rs.getString("NOME"), rs.getString("MARCAPROD"),
						rs.getDouble("PRECO"), CategoriaController.pesquisarCategoriaid(rs.getInt("ID_CATEGORIA")));
			}
			return produto;
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}

		return null;

	}

	public static void alterarProduto() throws Exception {
		System.out.println("QUAL ID_PRODUTO DESEJAR ALTERAR?");
		int idproduto = Reader.readInt();

		ProdutoController p = pesquisarProdutoid(idproduto);

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("UPDATE CATEGORIA SET NOME = ? WHERE ID_CATEGORIA = ?");
			stmt.setString(1, p.nome);
			stmt.setInt(2, p.idproduto);

			int rowsAffected = stmt.executeUpdate();

		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}
	}

	public static void deletarProduto() throws Exception {
		System.out.println("QUAL ID_PRODUTO DO PRODUTO QUE DESEJA DELETAR?");
		int idproduto = Reader.readInt();

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("DELETE PRODUTO WHERE ID_PRODUTO = ? ");
			stmt.setInt(1, idproduto);

			boolean rowsAffected = stmt.execute();

		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}
	}

}
