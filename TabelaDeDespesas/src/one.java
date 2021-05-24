import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class one extends JPanel{
	
	int wich_is_visible = 0;
	DecimalFormat format = new DecimalFormat("0,00");
	float valor_final = 0;
	
	String Save = "";
	
	JPanel pnmes = new JPanel();
	JPanel pntabelas = new JPanel();
	JPanel pnbotoes = new JPanel();
	
	JButton btvermelho = new JButton("");
	JButton btverde = new JButton("");
	JButton btlaranja = new JButton("");
	Color vermelho = new Color(255,99,83);
	Color verde = new Color(160,255,113);
	Color laranja = new Color(255,184,80);
	
	JButton[] btmeses = new JButton[12];	
	ArrayList<JTable> table_devendo = new ArrayList<>();
	ArrayList<JTable> table_pagas = new ArrayList<>();

	JSearchField nome_sf = new JSearchField();
	JSearchField vencimento_sf = new JSearchField();
	JSearchField valor_sf = new JSearchField();
	JTextArea total = new JTextArea();
	JTextArea totalFake = new JTextArea();
	JLabel totallb = new JLabel("Total:");
	
	JButton paga = new JButton("");
	JButton remover = new JButton("");
	JButton add = new JButton("");
	JScrollPane scroll = new JScrollPane();
	ArrayList<JScrollPane> scroll_pag = new ArrayList<>();
	ArrayList<JScrollPane> scroll_dev = new ArrayList<>();
	
	ArrayList<DefaultTableModel> tbmode_dev = new ArrayList<>();
	ArrayList<DefaultTableModel> tbmode_pag = new ArrayList<>();
	ArrayList<DefaultTableModel> dtmdev = new ArrayList<>();
	ArrayList<DefaultTableModel> dtmpagas = new ArrayList<>();
	
	ImageIcon BGimg = new ImageIcon(getClass().getResource("BG1280X720.png"));
	JLabel BGlb = new JLabel(BGimg);
	JLabel name = new JLabel("Kauã Chaves Calixto - 2021");
	
	public one(){
		mk_dir();
		init();
		def_eventos();
	}
	public void init() {
		/*int sz = 25;
		btvermelho.setBounds(865,70,sz,sz);
		btverde.setBounds(865,105,sz,sz);
		btlaranja.setBounds(865,140,sz,sz);
		btvermelho.setBackground(vermelho);
		btverde.setBackground(verde);
		btlaranja.setBackground(laranja);
		btvermelho.setBorder(null);
		btverde.setBorder(null);
		btlaranja.setBorder(null);
		add(btvermelho);
		add(btverde);
		add(btlaranja);*/
		
		
		total.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
		total.setBounds(283,344,546,25);
		//totalFake.setBackground(Color.black);
		total.setBackground(new Color(0,0,0,0));
		total.setOpaque(false);
		totallb.setBounds(210,344,645,25);
		totallb.setFont(new Font("arial",0,22));
		total.setFont(new Font("arial",0,22));
		total.setEditable(false);
		add(totallb);
		add(total);
		
		setLayout(null);
		setBackground(new Color(82,81,78));
		
		
		pnbotoes.setBounds(900,25,340,620);
		
		//pnMes
		pnmes.setBounds(14,26,170,675);
		for (int i = 0; i < btmeses.length; i++) {
			btmeses[i] = new JButton();
			btmeses[i].setContentAreaFilled(false);
			repaint();
		}
		GridLayout layout = new GridLayout(0,1);
		layout.setVgap(20);
		pnmes.setLayout(layout);
		pnmes.setBackground(new Color(0,0,0,0));
		pnmes.setOpaque(false);
		for (JButton x : btmeses) {
			pnmes.add(x);
		}
		//pnMes
		
		//pnTabelas	
		
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		
		for (int i = 0; i <= btmeses.length; i++) {
			tbmode_dev.add(new DefaultTableModel(new String[]{"","",""},0));
			tbmode_pag.add(new DefaultTableModel(new String[]{"","",""},0));
			table_devendo.add(new JTable(tbmode_dev.get(i)));
			table_pagas.add(new JTable(tbmode_pag.get(i)));
			
			dtmdev.add((DefaultTableModel) table_devendo.get(i).getModel());
			dtmpagas.add((DefaultTableModel) table_pagas.get(i).getModel());
			
			Font fonte = new Font("Arial",0,20);
			table_devendo.get(i).setFont(fonte);
			table_pagas.get(i).setFont(fonte);
			table_devendo.get(i).setRowHeight(25);
			table_pagas.get(i).setRowHeight(25);
			
			scroll_dev.add(new JScrollPane(table_devendo.get(i)));
			scroll_pag.add(new JScrollPane(table_pagas.get(i)));
			
			table_devendo.get(i).getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
			table_devendo.get(i).getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
			table_pagas.get(i).getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
			table_pagas.get(i).getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
			
			//table_devendo.get(i).setTableHeader(null);
			//table_pagas.get(i).setTableHeader(null);

			table_devendo.get(i).getTableHeader().setPreferredSize(new Dimension(0,10));
			table_pagas.get(i).getTableHeader().setPreferredSize(new Dimension(0,10));
			
			table_devendo.get(i).getColumnModel().getColumn(0).setPreferredWidth(200);
			table_devendo.get(i).getColumnModel().getColumn(1).setPreferredWidth(15);
			table_devendo.get(i).getColumnModel().getColumn(2).setPreferredWidth(15);
			
			
			
			table_pagas.get(i).getColumnModel().getColumn(0).setPreferredWidth(200);
			table_pagas.get(i).getColumnModel().getColumn(1).setPreferredWidth(15);
			table_pagas.get(i).getColumnModel().getColumn(2).setPreferredWidth(15);
			
			scroll_dev.get(i).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll_pag.get(i).setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
			scroll_dev.get(i).getVerticalScrollBar().setUI(new CustomizarScroll());
			scroll_pag.get(i).getVerticalScrollBar().setUI(new CustomizarScroll());
			
		}
		GridLayout layout2 = new GridLayout(0,1);
		layout2.setVgap(85);
		pntabelas.setOpaque(false);
		pntabelas.setLayout(layout2);
		pntabelas.setBounds(202,69,647,623);
		//pntabelas.setBorder(BorderFactory.createDashedBorder(Color.black));
		
		
		//pnTabelas
		
		//pnBotoes
		pnbotoes.setLayout(null);

		add.setBounds(47,235,247,62);
		paga.setBounds(47,422,247,62);
		remover.setBounds(47,557,247,62);
		remover.setContentAreaFilled(false);
		paga.setContentAreaFilled(false);
		add.setContentAreaFilled(false);
		nome_sf.setFont(new Font("arial",0,24));
		vencimento_sf.setFont(new Font("arial",0,24));
		valor_sf.setFont(new Font("arial",0,24));
		
		nome_sf.setEmptyText("Nome");
		nome_sf.setBounds(3,0,335,40);
		
		vencimento_sf.setEmptyText("Data de Vencimento");
		vencimento_sf.setBounds(3,157,335,40);

		valor_sf.setEmptyText("Valor");
		valor_sf.setBounds(3,73,335,40);
		pnbotoes.add(nome_sf);
		pnbotoes.add(valor_sf);
		pnbotoes.add(vencimento_sf);
		pnbotoes.add(add);
		pnbotoes.add(paga);
		pnbotoes.add(remover);
		pnbotoes.setOpaque(false);
		//pnBotoes
		
		//adds
		add(pntabelas);
		BGlb.setBounds(0,0,1280,720);
		name.setForeground(Color.white);
		name.setBounds(550,740,200,25);
		add(pnmes);
		add(pnbotoes);
		add(name);
		add(BGlb);
		
		add.doClick();
		vencimento_sf.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent e) {}
			@Override
			public void keyTyped(KeyEvent e) {}
			
			@Override
			public void keyReleased(KeyEvent e) {
				 if (e.getKeyCode() == KeyEvent.VK_ENTER) {
	                    add.requestFocus();
	                    add.doClick();
				 		
				 }
			}

		});
	    add.addKeyListener(new KeyListener() {

	            //Quando soltar a tecla
	            public void keyReleased(KeyEvent e) {

	                //Se a tecla pressionada for igual a F2
	                if (e.getKeyCode() == KeyEvent.VK_ENTER)
	                	add.doClick();
	                	repaint();
	            }

	            public void keyTyped(KeyEvent e) {}
	            public void keyPressed(KeyEvent e) {}
	        });
	    remover.addKeyListener(new KeyListener() {
				
				@Override
				public void keyTyped(KeyEvent e) {}
				
				@Override
				public void keyReleased(KeyEvent e) {
					if(e.getKeyCode()==KeyEvent.VK_DELETE) {
						remover.doClick();
						repaint();
					}
				}
				
				@Override
				public void keyPressed(KeyEvent e) {}
			});  
	    nome_sf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					valor_sf.requestFocus();
				}
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	    valor_sf.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER) {
					vencimento_sf.requestFocus();
				}
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	public void def_eventos() {
		rodar(btmeses[0],0); 
		rodar(btmeses[1],1);
		rodar(btmeses[2],2);
		rodar(btmeses[3],3);
		rodar(btmeses[4],4);
		rodar(btmeses[5],5);
		rodar(btmeses[6],6);
		rodar(btmeses[7],7);
		rodar(btmeses[8],8);
		rodar(btmeses[9],9);
		rodar(btmeses[10],10);
		rodar(btmeses[11],11);
		
		/*btvermelho.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				table_devendo.get(wich_is_visible).setSelectionBackground(Color.red);							
			}
		});*/
		
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String newValor = "";
				boolean found = false;
				for (int i = 0; i < (valor_sf.getText()).length(); i++) {
					char verify = (valor_sf.getText()).charAt(i);
					if (verify==',') {
						newValor = valor_sf.getText().replace(",", ".");
						found = true;
					}
				}
				if(!found) {
					newValor= valor_sf.getText()+".00";
				}
				if(vencimento_sf.getText().length()==1) {
					vencimento_sf.setText("0"+vencimento_sf.getText());
				}
				dtmdev.get(wich_is_visible).addRow(new String[] {nome_sf.getText(),vencimento_sf.getText(),newValor});
				addtotal();
				jButtonExportActionPerformed();
		 		nome_sf.setText("");
		 		vencimento_sf.setText("");
		 		valor_sf.setText("");
		 		nome_sf.requestFocus();
				repaint();
			}
		});	
		remover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int[] linhas = table_devendo.get(wich_is_visible).getSelectedRows();
				int[] linhasPag = table_pagas.get(wich_is_visible).getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table_devendo.get(wich_is_visible).getModel();
				DefaultTableModel dtm_pag = (DefaultTableModel) table_pagas.get(wich_is_visible).getModel();
				for(int i = (linhas.length -1 ); i>=0; --i){
					dtm.removeRow(linhas[i]);
				}
				for(int i = (linhasPag.length -1 ); i>=0; --i){
					dtm_pag.removeRow(linhasPag[i]);
				}
				jButtonExportActionPerformed();
				jButtonExportActionPerformed_Pag();
				addtotal();
			}
		});
		paga.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				int[] linhas = table_devendo.get(wich_is_visible).getSelectedRows();
				DefaultTableModel dtm = (DefaultTableModel) table_devendo.get(wich_is_visible).getModel();
				DefaultTableModel dtm2 = (DefaultTableModel) table_pagas.get(wich_is_visible).getModel();
				int[] LINHASELECIONADA = table_devendo.get(wich_is_visible).getSelectedRows();
				for(int i = (linhas.length -1 ); i>=0; --i){
					String TBNAME = String.valueOf(table_devendo.get(wich_is_visible).getValueAt(LINHASELECIONADA[i], 0));
					String TBVENC = String.valueOf(table_devendo.get(wich_is_visible).getValueAt(LINHASELECIONADA[i], 1));
					String TBVALOR = String.valueOf(table_devendo.get(wich_is_visible).getValueAt(LINHASELECIONADA[i], 2));
					dtm.removeRow(linhas[i]);
					dtm2.addRow(new String[] {TBNAME,TBVENC,TBVALOR});
				}
				jButtonExportActionPerformed();
				jButtonExportActionPerformed_Pag();
				addtotal();
			}
		});
	}
	private void jButtonExportActionPerformed() {                                          
        
        String filePath = "C:/APP_Mãe/Log/"+"DevLog-"+wich_is_visible+".txt";
        File file = new File(filePath);
        try {     	
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < table_devendo.get(wich_is_visible).getRowCount(); i++){//rows
                for(int j = 0; j < table_devendo.get(wich_is_visible).getColumnCount(); j++){//columns
                    bw.write(table_devendo.get(wich_is_visible).getValueAt(i, j).toString().replaceAll(" ", "_")+" ");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
    private void jButtonImportActionPerformed() {                                           
        String filePath = "C:/APP_Mãe/Log/"+"DevLog-"+wich_is_visible+".txt";
        File file = new File(filePath);
        
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            DefaultTableModel model = (DefaultTableModel)table_devendo.get(wich_is_visible).getModel();
            Object[] lines = br.lines().toArray();

            
            for(int i = 0; i < lines.length; i++){
                String[] row = lines[i].toString().split(" ");
                model.addRow(row);
                }        
            br.close();
        } catch (FileNotFoundException ex) {
        	System.out.println(ex.toString());
        } catch (IOException e) {
        	System.out.println(e.toString());
		}
        addtotal();
    }
	private void jButtonExportActionPerformed_Pag() {                                             
        
        String filePath = "C:/APP_Mãe/Log/"+"PagLog-"+wich_is_visible+".txt";
        File file = new File(filePath);
        try {
            FileWriter fw = new FileWriter(file);
            BufferedWriter bw = new BufferedWriter(fw);
            for(int i = 0; i < table_pagas.get(wich_is_visible).getRowCount(); i++){//rows
                for(int j = 0; j < table_pagas.get(wich_is_visible).getColumnCount(); j++){//columns
                    bw.write(table_pagas.get(wich_is_visible).getValueAt(i, j).toString().replaceAll(" ", "_")+" ");
                }
                bw.newLine();
            }
            bw.close();
            fw.close();
            
        } catch (IOException ex) {
            System.out.println(ex.toString());
        }
        
    }
    private void jButtonImportActionPerformed_Pag() {                                           
        
        String filePath = "C:/APP_Mãe/Log/"+"PagLog-"+wich_is_visible+".txt";
        File file = new File(filePath);
        
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            
            DefaultTableModel model = (DefaultTableModel)table_pagas.get(wich_is_visible).getModel();
            Object[] lines = br.lines().toArray();
            
            for(int i = 0; i < lines.length; i++){
                String[] row = lines[i].toString().split(" ");
                model.addRow(row);
            }
            br.close();
        } catch (FileNotFoundException ex) {
            System.out.println(ex.toString());
        } catch (IOException e) {
        	System.out.println(e.toString());
		}
        
    }
	public void mk_dir() {
		File dirMae = new File("C:/APP_Mãe");
		File dirLog = new File("C:/APP_Mãe/Log");
		File dirOk = new File("C:/APP_Mãe/Log/Ok");
		if(dirMae.exists()) {
			if(dirLog.exists()) {
				if(!dirOk.exists()) {
					try {
						for (int i = 0; i <= 11; i++) {
							FileWriter fw = new FileWriter("C:/APP_Mãe/Log/DevLog-"+i+".txt");
							FileWriter fw2 = new FileWriter("C:/APP_Mãe/Log/PagLog-"+i+".txt");
							fw.write("");
							fw2.write("");
							fw.close();
							fw2.close();
						}	
					} catch (IOException e) {
						e.printStackTrace();
					}
					dirOk.mkdir();
				}
			}else {
				dirLog.mkdir();
				mk_dir();
			}
		}else {
			dirMae.mkdir();
			mk_dir();
		}
	}
	public void rodar(JButton x,int a) {
		x.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for (int i = 0; i < table_pagas.size(); i++) {
					pntabelas.removeAll();
					pntabelas.revalidate();
					pntabelas.repaint();
				}			
				pntabelas.add(scroll_dev.get(a));
				pntabelas.add(scroll_pag.get(a));
				wich_is_visible=a;
				dtmdev.get(wich_is_visible).setRowCount(0);
				dtmpagas.get(wich_is_visible).setRowCount(0);
				jButtonImportActionPerformed();
				jButtonImportActionPerformed_Pag();
				
				table_devendo.get(wich_is_visible).setAutoCreateRowSorter(true);
			}
		});
	}
	public void addtotal() {
		valor_final=0;
		
		for (int j = 0; j < dtmdev.get(wich_is_visible).getRowCount(); j++) {
			String rowvalue = String.valueOf(dtmdev.get(wich_is_visible).getValueAt(j, 2));
			valor_final+=Float.parseFloat(rowvalue);
		}
		total.setText(String.valueOf(valor_final));
	}
	public class CustomizarScroll extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(163,184,204);
        }
	}
	public static void main(String args[]) {
		JFrame frame = new JFrame("Organizador de despesas");
		frame.getContentPane().add(new one());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\APP_Mãe\\DespesasPNG.png"));
	}
}
