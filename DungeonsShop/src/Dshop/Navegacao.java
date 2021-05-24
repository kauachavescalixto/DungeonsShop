package Dshop;
import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Navegacao extends JFrame{
	private JLabel label1, label2, label3, label4;
	private JButton btProx, btAnt, btPrimeiro,btUltimo,btMais10,btMenos10,btSair;
	private JTextField tfCodigo, tfTitulo, tfGenero, tfAno;
	private bd bd;
	private PreparedStatement st;
	private ResultSet resultSet;
	
	public static void main(String args[]) {
		JFrame frame = new Navegacao();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	public Navegacao(){
		inicializarComponentes();
		definirEventos();
	}
	
	public void inicializarComponentes(){
		setLayout(new FlowLayout(FlowLayout.LEFT));
		label1 = new JLabel("Código");
		label2 = new JLabel("Titulo");
		label3 = new JLabel("Gênero");
		label4 = new JLabel("Ano");
		tfCodigo = new JTextField(10);
		tfTitulo = new JTextField(35);
		tfGenero = new JTextField(15);
		tfAno = new JTextField(10);
		btProx = new JButton("Próximo");
		btAnt = new JButton("Anterior");
		btPrimeiro = new JButton("Primeiro");
		btUltimo = new JButton("Ultimo");
		btMais10 = new JButton("Avança 10");
		btMenos10 = new JButton("Volta 10");
		btSair = new JButton("Sair");
		add(label1);
		add(tfCodigo);
		add(label2);
		add(tfTitulo);
		add(label3);
		add(tfGenero);
		add(label4);
		add(tfAno);
		add(btPrimeiro);
		add(btAnt);
		add(btProx);
		add(btMais10);
		add(btMenos10);
		add(btSair);
		setTitle("Consulta de filmes");
		setBounds(200,300,620,150 );
		setResizable(false);
		bd = new bd();
		if(!bd.getConnection()){
			JOptionPane.showMessageDialog(null,"Falha na conexão!");
			System.exit(0);
		}
		carregarTabela();
		atualizarCampos();	
	}
	
	public void definirEventos(){
		btProx.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					resultSet.next();
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btAnt.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					resultSet.previous();
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btPrimeiro.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					resultSet.first();
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btUltimo.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					resultSet.last();
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btMais10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					resultSet.relative(10);
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btMenos10.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
					if(resultSet.getRow()>10){
						resultSet.relative(-10);
					}else{
						resultSet.first();
					}
					atualizarCampos();
				}catch(SQLException erro){
			}
			}
		});
		
		btSair.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try{
				resultSet.close();
				st.close();
				}catch(SQLException erro){
				}
				bd.close();
				System.exit(0);
			}
		});
	}
	public void carregarTabela(){
		String sql = "select * from itens2";
		try{
			st = bd.c.prepareStatement(sql);
			resultSet = st.executeQuery();
		}catch (SQLException erro){
			JOptionPane.showMessageDialog(null,"Erro! " +erro.toString() );
		}
	}
	public void atualizarCampos(){
		try{
			if(resultSet.isAfterLast()){
				resultSet.last();
			}
			if(resultSet.isBeforeFirst()){
				resultSet.first();
			}
			tfCodigo.setText(resultSet.getString("id"));
			tfTitulo.setText(resultSet.getString("nome"));
			tfGenero.setText(resultSet.getString("arma"));
			tfAno.setText(resultSet.getString("raro"));
		} catch(SQLException erro){
			
		}
		
	}
	}