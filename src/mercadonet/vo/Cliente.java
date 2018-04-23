package mercadoNet;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.opet.util.Reader;

public class Cliente {
	int idcliente;
	String nome;
	String cpf;
	String endereco;
	String cep;
	String email;
	String telefone;

	// Construtor com objetos com algumas informações pré definidas
	public Cliente(int idcliente, String nome, String cpf, String endereco, String cep, String email, String telefone) {
		this.idcliente = idcliente;
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.cep = cep;
		this.email = email;
		this.telefone = telefone;
	}

	public static void listarCliente() {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM CLIENTE");

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

	public static boolean cadastrarCliente() throws Exception {

		System.out.println("QUAL ID_CLIENTE? ");
		int idcliente = Reader.readInt();
		System.out.println("QUAL O NOME? ");
		String nome = Reader.readString();
		System.out.println("QUAL O CPF? ");
		String cpf = Reader.readString();
		System.out.println("QUAL O ENDEREÇO? ");
		String endereco = Reader.readString();
		System.out.println("QUAL O CEP? ");
		String cep = Reader.readString();
		System.out.println("QUAL O EMAIL? ");
		String email = Reader.readString();
		System.out.println("QUAL O Nº TELEFONE? ");
		String telefone = Reader.readString();

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement(
					"INSERT INTO CLIENTE (ID_CLIENTE, NOME, CPF, ENDERECO, CEP, EMAIL, TELEFONE) VALUES (?,?,?,?,?,?,?)");
			stmt.setInt(1, idcliente);
			stmt.setString(2, nome);
			stmt.setString(3, cpf);
			stmt.setString(4, endereco);
			stmt.setString(5, cep);
			stmt.setString(6, email);
			stmt.setString(7, telefone);

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

	public static Cliente pesquisarCliente(String clientenome) throws SQLException {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM CLIENTE WHERE NOME = ?");
			stmt.setString(1, clientenome);

			rs = stmt.executeQuery();
			Cliente cliente = null;
			while (rs.next()) {
				cliente = new Cliente(rs.getInt("ID_CLIENTE"), rs.getString("NOME"), rs.getString("CPF"),
						rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("EMAIL"), rs.getString("TELEFONE"));
			}
			return cliente;
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}

		return null;

	}

	public static Cliente pesquisarClienteid(int clienteid) throws SQLException {
		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("SELECT * FROM CLIENTE WHERE ID_CLIENTE = ?");
			stmt.setInt(1, clienteid);

			rs = stmt.executeQuery();
			Cliente cliente = null;
			while (rs.next()) {
				cliente = new Cliente(rs.getInt("ID_CLIENTE"), rs.getString("NOME"), rs.getString("CPF"),
						rs.getString("ENDERECO"), rs.getString("CEP"), rs.getString("EMAIL"), rs.getString("TELEFONE"));
			}
			return cliente;
		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}

		return null;

	}

	public static void alterarCliente() throws Exception {
		System.out.println("QUAL ID_CLIENTE DESEJAR ALTERAR?");
		int idcliente = Reader.readInt();

		Cliente c = pesquisarClienteid(idcliente);

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("UPDATE CLIENTE SET NOME = ? WHERE ID_CLIENTE = ?");
			stmt.setString(1, c.nome);
			stmt.setInt(2, c.idcliente);

			int rowsAffected = stmt.executeUpdate();

		} catch (ClassNotFoundException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		} catch (SQLException Except) {
			// TODO Auto-generated catch block
			Except.printStackTrace();
		}
	}

	public static void deletarCliente() throws Exception {
		System.out.println("QUAL ID_CLIENTE QUE DESEJA DELETAR?");
		int idcliente = Reader.readInt();

		Connection conn;
		try {
			conn = Conexao.getConnection();
			PreparedStatement stmt;
			ResultSet rs;
			stmt = conn.prepareStatement("DELETE CLIENTE WHERE ID_CLIENTE = ? ");
			stmt.setInt(1, idcliente);

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
