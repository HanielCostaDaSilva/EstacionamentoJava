package gui.tools;
import operadorCSV.*;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.annotation.processing.FilerException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import classes.Estacionamento;

public class Historico {
	private int page = 1;
	private JFrame frame;
	private JTable table;
    private ArrayList<String> dados = new ArrayList<String>();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Historico window = new Historico();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Object[] getLine(int num) {
		
		try {
            String aux = (String) dados.get(num);
            String[] componentes= aux.split(";");
			Object[] line = {componentes[0],componentes[1],componentes[2],componentes[3]};
			return line;
		}catch(Exception e) {
			Object[] line = {null,null,null,null};
			return line;
		}
		
		
	}
    public void updateData(Estacionamento estacionamento){
        dados = (ArrayList<String>) OperadorArquivoCSV.lerArquivo("historico.csv");
		Collections.reverse(dados);
        dados.remove(dados.size()-1);
    }

    public boolean nextPage(){
        try{
            int num = page*13;
            dados.get(num);
            return true;
        }catch(Exception e){
            return false;
        }
        
    };


	public  Object[][] getHistorico(int num) {
		int aux = (num-1)*13;
		Object[][] algo = {
				getLine(aux+0),
				getLine(aux+1),
				getLine(aux+2),
				getLine(aux+3),
				getLine(aux+4),
				getLine(aux+5),
				getLine(aux+6),
				getLine(aux+7),
				getLine(aux+8),
				getLine(aux+9),
				getLine(aux+10),
				getLine(aux+11),
				getLine(aux+12)
		};
		return algo;
	}

	/**
	 * Create the application.
	 */
	public Historico() {
		try {
            Estacionamento estacionamento = new Estacionamento(10);
            updateData(estacionamento);
            initialize(estacionamento);
        } catch (FilerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize(Estacionamento estacionamento) {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.PINK);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setVisible(true);
		
	
		
		
		table = new JTable();
		table.setBorder(new LineBorder(Color.BLACK));
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		table.setModel(new DefaultTableModel(
				(Object[][]) getHistorico(page),
			new String[] {
				"data", "vaga", "placa", "situa\u00E7\u00E3o"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(145);
		table.getColumnModel().getColumn(0).setMinWidth(145);
		table.getColumnModel().getColumn(1).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(85);
		table.getColumnModel().getColumn(3).setMinWidth(75);
		table.setBounds(39, 31, 357, 227);
		frame.getContentPane().add(table);
		table.setEnabled(false);
		JLabel lblHistrico = new JLabel("Histórico "+page);
		lblHistrico.setBounds(39, 10, 100, 15);
		frame.getContentPane().add(lblHistrico);
		JButton btnNewButton_1 = new JButton(">");
		JButton btnNewButton = new JButton("<");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				page -=1;
				lblHistrico.setText("Histórico "+page);
				table.setModel(new DefaultTableModel(
						(Object[][]) getHistorico(page),
						new String[] {
							"data", "vaga", "placa", "situa\u00E7\u00E3o"
						}
					));
				table.getColumnModel().getColumn(0).setPreferredWidth(145);
				table.getColumnModel().getColumn(0).setMinWidth(145);
				table.getColumnModel().getColumn(1).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setMinWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(85);
				table.getColumnModel().getColumn(3).setMinWidth(75);
                btnNewButton_1.setEnabled(nextPage());
				if(page==1) {
					btnNewButton.setEnabled(false);
				}
			}
		});
		
		btnNewButton.setBounds(284, 3, 55, 25);
		frame.getContentPane().add(btnNewButton);
		
		
		btnNewButton_1.setBounds(342, 3, 55, 25);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page +=1;
				lblHistrico.setText("Histórico "+page);
				table.setModel(new DefaultTableModel(
						(Object[][]) getHistorico(page),
						new String[] {
							"data", "vaga", "placa", "situa\u00E7\u00E3o"
						}
					));
				table.getColumnModel().getColumn(0).setPreferredWidth(145);
				table.getColumnModel().getColumn(0).setMinWidth(145);
				table.getColumnModel().getColumn(1).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setMinWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(85);
				table.getColumnModel().getColumn(3).setMinWidth(75);
				btnNewButton.setEnabled(true);
                btnNewButton_1.setEnabled(nextPage());
			}
		});
        
	}
}
