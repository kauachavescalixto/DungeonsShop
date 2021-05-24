package Dshop;

import java.sql.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

public class Gui extends JFrame {

	private JLabel label1, label2, label3, label4;
	private JButton btGravar, btExcluir, btAlterar, btNovo, btLocalizar, btCancelar, btSair;
	private JTextField tfCodigo, tfTitulo, tfGenero, tfAno;
	private itensDAO f;

	public static void main(String args[]) {
		JFrame frame = new Gui();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public Gui() {
		inicializarComponentes();
		definirEventos();
	}

	public void inicializarComponentes() {
		setLayout(new FlowLayout(FlowLayout.LEFT));
		label1 = new JLabel("C�digo");
		label2 = new JLabel("Titulo");
		label3 = new JLabel("G�nero");
		label4 = new JLabel("Ano");
		tfCodigo = new JTextField(10);
		tfTitulo = new JTextField(35);
		tfGenero = new JTextField(15);
		tfAno = new JTextField(10);
		btGravar = new JButton("Gravar");
		btExcluir = new JButton("Excluir");
		btAlterar = new JButton("Alterar");
		btNovo = new JButton("Novo");
		btLocalizar = new JButton("Localizar");
		btCancelar = new JButton("Cancelar");
		btSair = new JButton("Sair");
		add(label1);
		add(tfCodigo);
		add(label2);
		add(tfTitulo);
		add(label3);
		add(tfGenero);
		add(label4);
		add(tfAno);
		add(btAlterar);
		add(btLocalizar);
		add(btExcluir);
		add(btGravar);
		add(btNovo);
		add(btSair);
		add(btSair);
		setTitle("Consulta de filmes");
		setBounds(200, 300, 620, 150);
		setResizable(false);
		setBotoes(true, true, false, false, false, false); // m�todo criado para ativar/desativar os bot�es
		f = new itensDAO(); // orienta��o do objeto da classe filmesMetodos
		if (!f.bd.getConnection()) { // verifica��o da conex�o com o bd.
			JOptionPane.showMessageDialog(null, "Falha na conex�o!");
			System.exit(0);
		}
	}

	public void definirEventos() {
		btSair.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.bd.close();
				System.exit(0);
			}
		});

		btNovo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
				setBotoes(false, false, true, false, false, true);
			}
		});

		btCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				limparCampos();
			}
		});

		btGravar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tfCodigo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O c�digo n�o pode ser vazio!");
					tfCodigo.requestFocus();
					return;
				}
				if (tfTitulo.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O t�tulo n�o pode ser vazio!");
					tfTitulo.requestFocus();
					return;
				}
				if (tfGenero.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O g�nero n�o pode ser vazio!");
					tfGenero.requestFocus();
					return;
				}
				if (tfAno.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "O ano n�o pode ser vazio!");
					tfAno.requestFocus();
					return;
				}

				f.itens.setId(tfCodigo.getText());
				f.itens.setNome(tfTitulo.getText());

				JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.INCLUSAO));
				limparCampos();
			}
		});

		btAlterar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				f.itens.setId(tfCodigo.getText());
				f.itens.setNome(tfTitulo.getText());

				JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.ALTERACAO));
				limparCampos();
			}
		});
		btExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				f.itens.setId(tfCodigo.getText());
				f.localizar();
				int n = JOptionPane.showConfirmDialog(null, f.itens.getNome(), "Excluir o filme?",
						JOptionPane.YES_OPTION);
				if (n == JOptionPane.YES_OPTION) {
					JOptionPane.showMessageDialog(null, f.atualizar(itensDAO.EXCLUSAO));
					limparCampos();
				}
			}
		});

		btLocalizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizarCampos();
			}
		});
	}

	public void limparCampos() {
		tfCodigo.setText("");
		tfTitulo.setText("");
		tfGenero.setText("");
		tfAno.setText("");
		tfCodigo.requestFocus();
		setBotoes(true, true, false, false, false, false);
	}

	public void atualizarCampos() {
		f.itens.setId(tfCodigo.getText());
		if (f.localizar()) {
			tfCodigo.setText(f.itens.getId());
			tfTitulo.setText(f.itens.getNome());
			setBotoes(true, true, false, true, true, true);

		} else {
			JOptionPane.showMessageDialog(null, "item n�o encontrado");
			limparCampos();
		}
	}

	public void setBotoes(boolean bNovo, boolean bLocalizar, boolean bGravar, boolean bAlterar, boolean bExcluir,
			boolean bCancelar) {
		btNovo.setEnabled(bNovo);
		btLocalizar.setEnabled(bLocalizar);
		btGravar.setEnabled(bGravar);
		btAlterar.setEnabled(bAlterar);
		btExcluir.setEnabled(bExcluir);
		btCancelar.setEnabled(bCancelar);
	}
}