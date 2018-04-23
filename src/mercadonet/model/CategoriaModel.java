package mercadonet.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opet.util.Reader;

public class CategoriaModel {
	public int idcategoria;
	public String nome;

	public CategoriaModel(int idcategoria, String nome) {
		this.idcategoria = idcategoria;
		this.nome = nome;

	}

	public static boolean cadastrarCategoria() throws Exception {

		System.out.println("QUAL ID_CATEGORIA? ");
		int idcategoria = Reader.readInt();
		System.out.println("QUAL O NOME CATEGORIA? ");
		String nome = Reader.readString();

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("INSERT INTO CATEGORIA (ID_CATEGORIA, NOME) VALUES (?,?)");
			stmt.setInt(1, idcategoria);
			stmt.setString(2, nome);

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
			System.out.println("ERRO AO CADASTRAR");
			Except.printStackTrace();
		}
		return false;
	}

	public static void listarCategorias() {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM CATEGORIA");

			rs = stmt.executeQuery();
			while (rs.next()) {
				System.out.println("CATEGORIA: " + rs.getString("NOME"));

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

	public static CategoriaModel pesquisarCategoriaid(int categoriaid) throws SQLException {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM CATEGORIA WHERE ID_CATEGORIA = ?");
			stmt.setInt(1, categoriaid);

			rs = stmt.executeQuery();
			CategoriaModel categoria = null;
			while (rs.next()) {
				categoria = new CategoriaModel(rs.getInt("ID_CATEGORIA"), rs.getString("NOME"));
			}
			return categoria;
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}

		return null;

	}

	public static void alterarCategoria() throws Exception {
		System.out.println("QUAL ID_CATEGORIA DESEJAR ALTERAR?");
		int idcategoria = Reader.readInt();
		System.out.println("QUAL O NOVO NOME DESSA CATEGORIA?");
		String nome = Reader.readString();
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("UPDATE CATEGORIA SET NOME = ? WHERE ID_CATEGORIA = ?");
			stmt.setString(1, nome);
			stmt.setInt(2, idcategoria);

			int rowsAffected = stmt.executeUpdate();

		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}
	}

	public static void deletarCategoria() throws Exception {
		System.out.println("QUAL ID_CATEGORIA DE CATEGORIA DESEJA DELETAR?");
		int idcategoria = Reader.readInt();

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("DELETE CATEGORIA WHERE ID_CATEGORIA = ? ");
			stmt.setInt(1, idcategoria);

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
