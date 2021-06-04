package Dshop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class itensDAO {
	public itens itens;
	public bd bd;
	private PreparedStatement statement;
	private ResultSet resultSet;
	private String men, sql;
	public static final byte INCLUSAO = 1;
	public static final byte ALTERACAO = 2;
	public static final byte EXCLUSAO = 3;

	public itensDAO() {
		bd = new bd();
		itens = new itens();
	}

	public boolean localizar() {
		sql = "select * from itens2 where id =?";
		try {
			statement = bd.c.prepareStatement(sql);
			statement.setInt(1, itens.getId());
			resultSet = statement.executeQuery();
			resultSet.next();

			
			itens.setNome(resultSet.getString(1));
			itens.setComum(resultSet.getString(2));
			itens.setRaro(resultSet.getString(3));
			itens.setUnico(resultSet.getString(4));
			itens.setArma(resultSet.getString(5));
			itens.setArmadura(resultSet.getString(6));
			itens.setPreco(resultSet.getString(7));
			itens.setId(Integer.parseInt(resultSet.getString(8)));
			
			
			return true;
		} catch (SQLException erro) {
			return false;
		}
	}


	public String atualizar(int operacao) {

		men = "Operação realizada com sucesso ";
		try {
			if (operacao == INCLUSAO) {

				sql = "insert into itens2 values (?,?,?,?,?,?,?,?)";
				statement = bd.c.prepareStatement(sql);

				
				statement.setString(1, itens.getNome());
				statement.setString(2, itens.getComum());
				statement.setString(3, itens.getRaro());
				statement.setString(4, itens.getUnico());
				statement.setString(5, itens.getArma());
				statement.setString(6, itens.getArmadura());
				statement.setString(7, itens.getPreco());
				if(itens.getId() == 0) {
					statement.setInt(8, (MineDungeonsShop.IDdositensdobd.size()+1));
					
				}else {
					statement.setInt(8, itens.getId());
				}


			} else if (operacao == ALTERACAO) {
				sql = "update itens2 set nome = ?, comum = ?, raro = ?, unico = ?, arma = ?, armadura = ?, preco = ? where id =?";
				statement = bd.c.prepareStatement(sql);
				statement.setInt(8, itens.getId());
				statement.setString(1, itens.getNome());
				statement.setString(2, itens.getComum());
				statement.setString(3, itens.getRaro());
				statement.setString(4, itens.getUnico());
				statement.setString(5, itens.getArma());
				statement.setString(6, itens.getArmadura());
				statement.setString(7, itens.getPreco());
			} else if (operacao == EXCLUSAO) {
				sql = "delete from itens2 where id = ? ;";
				statement = bd.c.prepareStatement(sql);
				statement.setInt(1, itens.getId() + 1);
				//delete();
				
			}
			if (statement.executeUpdate() == 0) {
				men = "Falha na operação";
			}

		} catch (SQLException erro) {
			men = "Falha na operação - 3" + erro.toString();
		}
		return men;
	}

	public void delete() {
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String mysqlUrl = "jdbc:mysql://localhost:3306/lista_de_itens";
			Connection con = DriverManager.getConnection(mysqlUrl, "root", "");
			Statement stmt = con.createStatement();

			String query = "ALTER TABLE itens2 drop id";
			stmt.executeUpdate(query);
			String query2 = "ALTER TABLE itens2 AUTO_INCREMENT = 1;";
			stmt.executeUpdate(query2);
			String query3 = "ALTER TABLE itens2 ADD `id` int UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY FIRST;";
			stmt.executeUpdate(query3);
			
			
			
		} catch (SQLException e) {
			System.out.println("ERROR - 4: " + e);
		}
	}

}