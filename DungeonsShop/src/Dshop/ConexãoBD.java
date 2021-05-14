package Dshop;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class ConexãoBD extends JFrame {

	private bd bd;
	private PreparedStatement st;
	private ResultSet rs;

	public static void main(String args[]) {
		JFrame frame = new ConexãoBD();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public ConexãoBD() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		setLayout(null);
		setTitle("Tabela de consultas");
		setBounds(200, 200, 600, 500);
		setResizable(false);
		
		bd = new bd();
	}

	public void definirEventos() {
//

				try{
					if(!bd.getConnection()){
						JOptionPane.showMessageDialog(null,"Falha na  conexão!");
						System.exit(0);
					}
					st = bd.c.prepareStatement("select * from itens where raridade=?");
					st.setString(1, "comum");
					
					rs = st.executeQuery();
					
					int qtdeColunas = rs.getMetaData().getColumnCount();

					
					while(rs.next()){
						try{
							String[] dados = new String[qtdeColunas];
							for(int i = 1; i<=qtdeColunas; i++){
								dados[i-1] = rs.getString(i);
								System.out.println(rs.getString(i));
							}
							System.out.println();
						}catch (SQLException erro){
							
						}
					}
					rs.close();
					st.close();
					bd.close();
				}catch (Exception erro){
					JOptionPane.showMessageDialog(null,"Comando Inválido"+erro.toString());
				}
			
//	
	}
}