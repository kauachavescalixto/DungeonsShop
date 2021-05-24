package com.tabela.kaua;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Mytable extends JScrollPane{
	
	JTable tb;
	public static int index;
	

	public Mytable() {
		init();
		
		setSize(647,623);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		getVerticalScrollBar().setUI(new CustomizarScroll());
		setViewportView(tb);
		
	}
	
	public void init() {
		
		DefaultTableCellRenderer alinhaDireita = new DefaultTableCellRenderer();
		alinhaDireita.setHorizontalAlignment(SwingConstants.RIGHT);
		
		tb = new JTable(new DefaultTableModel(new String[] {"","",""},0));
		
		DefaultTableModel dtm = (DefaultTableModel) tb.getModel();
		dtm.addRow(new String[] {"Aloha",String.valueOf(index),String.valueOf(index)});
		System.out.println("index:"+index);
		tb.setFont(new Font("Arial",0,20));
		tb.setRowHeight(25);
		tb.getColumnModel().getColumn(1).setCellRenderer(alinhaDireita);
		tb.getColumnModel().getColumn(2).setCellRenderer(alinhaDireita);
		tb.getTableHeader().setPreferredSize(new Dimension(0,10));
		tb.getColumnModel().getColumn(0).setPreferredWidth(200);
		tb.getColumnModel().getColumn(1).setPreferredWidth(15);
		tb.getColumnModel().getColumn(2).setPreferredWidth(15);
		
	}

	
	
	public class CustomizarScroll extends BasicScrollBarUI {
        @Override
        protected void configureScrollBarColors() {
            this.thumbColor = new Color(163,184,204);
        }
	}
	
}
