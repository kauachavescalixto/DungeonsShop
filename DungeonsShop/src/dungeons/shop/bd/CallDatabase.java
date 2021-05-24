package dungeons.shop.bd;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class CallDatabase{

	public static ArrayList<Integer> IDdositensdobd;
	public static ArrayList<Integer> precos;
	public static ArrayList<String> nomes;
	public static ArrayList<String> listararos;
	public static ArrayList<String> listacomuns;
	public static ArrayList<String> listaunicos;
	public static ArrayList<String> condicoesdobd;
	public static ArrayList<Integer> selected;
	public static int Nresultados;
	
	static String texto = "";
	
	public static ArrayList<Integer> idbanidas;
	
	private static BD bd;
	private static PreparedStatement st;
	private static ResultSet rs;
	
	public CallDatabase() {
		bd = new BD();
		if (!bd.getConnection()) {
			JOptionPane.showMessageDialog(null, "Falha na  conexão!");
			System.exit(0);
		}
		execute();
		start();
		init();
	}
	
	public static void execute() {
		try {
			escrever();

			st = bd.c.prepareStatement(texto);

			rs = st.executeQuery();

			init();

		} catch (SQLException e) {
			System.out.println(e.toString());
		}

	}
	
	public static void escrever() {

		texto = "select * from itens2";

		for (int i = 0; i < condicoesdobd.size(); i++) {

			if (i > 0) {
				texto += " and " + condicoesdobd.get(i);
			} else {
				texto += " where " + condicoesdobd.get(i);
			}

		}
	}
	
	public void start() {
		IDdositensdobd = new ArrayList<>();
		precos = new ArrayList<Integer>();
		nomes = new ArrayList<String>();
		listararos = new ArrayList<String>();
		listacomuns = new ArrayList<String>();
		listaunicos = new ArrayList<String>();
		idbanidas = new ArrayList<Integer>();
		condicoesdobd = new ArrayList<>();
		selected = new ArrayList<>();
	}
	
	public static ArrayList<Integer> getIDDosItensDoBD(){
		return IDdositensdobd;
	}
	
	public static ArrayList<Integer> getPrecos(){
		return precos;
	}
	
	public static ArrayList<String> getNomes(){
		return nomes;
	}
	
	public static ArrayList<String> getListaRaros(){
		return listararos;
	}
	
	public static ArrayList<String> getListaComuns(){
		return listacomuns;
	}
	
	public static ArrayList<String> getListaUnicos(){
		return listaunicos;
	}
	
	public static ArrayList<Integer> getIDSBanidas(){
		return idbanidas;
	}
	
	public static ArrayList<String> getCondicoesDoBD(){
		return condicoesdobd;
	}
	
	public static String getTexto() {
		return texto;
	}
	
	public static void DatabaseClose() {
		try {
			rs.close();
			st.close();
			bd.close();
		} catch (SQLException e) {
			System.out.println(e.toString());
		}
		
	}
	
	public static void init() {
		try {
			int qtdeColunas = rs.getMetaData().getColumnCount();

			IDdositensdobd.clear();

			while (rs.next()) {
				try {
					String[] dados = new String[qtdeColunas];
					for (int i = 1; i <= qtdeColunas; i++) {
						dados[i - 1] = rs.getString(i);
					}
					IDdositensdobd.add(Integer.parseInt(rs.getString("id")));
					precos.add(Integer.parseInt(rs.getString("preco")));
					nomes.add(rs.getString("nome"));
					listacomuns.add(rs.getString("comum"));
					listararos.add(rs.getString("raro"));
					listaunicos.add(rs.getString("unico"));
				} catch (SQLException erro) {
					System.out.println(erro.toString());
				}

			}

			for (int i = 0; i < idbanidas.size(); i++) {
				for (int j = 0; j < IDdositensdobd.size(); j++) {
					if (IDdositensdobd.get(j) == (idbanidas.get(i) + 2)) {

						IDdositensdobd.remove(j);
					}
				}
			}

			Nresultados = IDdositensdobd.size();
			

		} catch (Exception erro) {
			JOptionPane.showMessageDialog(null, "Comando Inválido" + erro.toString());
		}
	}
}
