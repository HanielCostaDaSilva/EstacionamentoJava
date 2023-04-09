package gui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;

import classes.Estacionamento;
import operadorCSV.OperadorArquivoCSV;


import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.processing.FilerException;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;

import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Principal {
    private int page = 1;
	private JFrame frame;
	private JTable table;
    JTextArea VagasVisor = new JTextArea();

    private TelaCadastrarPlaca telaEntrada = null;
	private TelaRemoverPlaca telaSaida = null;
	private TelaTransferirPlaca telaTransferir = null;

    private ArrayList<String> dados = new ArrayList<String>();
	

	/**
	 * Launch the application.
	 */



    /**
     * funções para o funcionamento do histórico
     * 
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    
					Principal window = new Principal();
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
    //sincroniza os dados do arquivo historico com os dados dentro do Estacionamento
    public void updateData(Estacionamento estacionamento){
        dados = (ArrayList<String>) OperadorArquivoCSV.lerArquivo("historico.csv");
		Collections.reverse(dados);
        dados.remove(dados.size()-1);
    }
    //verifica se há conteudo para a próxima página, caso não haja, irá broquear o botão
    public boolean nextPage(){
        try{
            int num = page*13;
            dados.get(num);
            return true;
        }catch(Exception e){
            return false;
        }
        
    };
	//fornece o histórico
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


    //atualizar a lista de placas livres e ocupadas
    public void atualizaPlacas(Estacionamento estacionamento){
        String[] placas = estacionamento.listarGeral();
        String aux="";
        for(String placa:placas){
            aux+=placa+"\n";
        }
        VagasVisor.setText(aux);
    }
	/**
	 * Create the application.
	 */
	public Principal() {
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
	 * @param estacionamento
	 */
	private void initialize(Estacionamento estacionamento) {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 340);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
        JPanel historico = new JPanel();
        JPanel home = new JPanel();
        JPanel vagas = new JPanel();
        historico.setLayout(null);
        home.setLayout(null);
        vagas.setLayout(null);

        /*
         * parte da home, no qual ficam os botões de inserção e remoção dos veículos do estacionamento
         */


		JButton registrarTransferenciaBtn = new JButton("Registrar transferência");
		registrarTransferenciaBtn.setForeground(new Color(0, 0, 0));
		registrarTransferenciaBtn.setBackground(new Color(255, 204, 0));
		registrarTransferenciaBtn.setBounds(125, 169, 200, 52);
		home.add(registrarTransferenciaBtn);
		
		JButton registrarSaidaBtn = new JButton("Registrar Saída");
		registrarSaidaBtn.setForeground(new Color(0, 0, 0));
		registrarSaidaBtn.setBackground(new Color(153, 0, 0));
		registrarSaidaBtn.setBounds(125, 101, 200, 52);
		home.add(registrarSaidaBtn);
		
		JButton registrarEntradaBtn = new JButton("Registrar Entrada");
		registrarEntradaBtn.setForeground(new Color(0, 0, 0));
		registrarEntradaBtn.setBackground(new Color(34, 139, 34));
		registrarEntradaBtn.setBounds(125, 37, 200, 52);
		home.add(registrarEntradaBtn);
		
        registrarSaidaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
                
				telaSaida = new TelaRemoverPlaca(estacionamento);
				telaSaida.setVisible();
			}
		});

        registrarEntradaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaEntrada = new TelaCadastrarPlaca(estacionamento);
				telaEntrada.setVisible();

			}
		});

		registrarTransferenciaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		registrarTransferenciaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaTransferir = new TelaTransferirPlaca(estacionamento);
				telaTransferir.setVisible();
			}
		});

        //Vagas

        JButton btnMostrar = new JButton("Mostrar");
		btnMostrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                atualizaPlacas(estacionamento);
			}
		});
		btnMostrar.setBounds(318, 12, 90, 25);
		vagas.add(btnMostrar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 12, 168, 250);
		vagas.add(scrollPane);
		
		
		scrollPane.setViewportView(VagasVisor);
        VagasVisor.setEditable(false);
		VagasVisor.setText("");
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"todas", "ocupadas", "livres"}));
		comboBox.setBounds(188, 12, 118, 25);
		vagas.add(comboBox);




        //Histórico
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
		historico.add(table);
		table.setEnabled(false);
		JLabel lblHistrico = new JLabel("Histórico "+page);
		lblHistrico.setBounds(39, 10, 100, 15);
		historico.add(lblHistrico);
		JButton btnNewButton_1 = new JButton(">");
		JButton btnNewButton = new JButton("<");
		btnNewButton.setEnabled(false);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page -=1;
                updateData(estacionamento);
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
		historico.add(btnNewButton);
		
		
		btnNewButton_1.setBounds(342, 3, 55, 25);
		historico.add(btnNewButton_1);
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page +=1;
                updateData(estacionamento);
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







		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 263);
		frame.getContentPane().add(tabbedPane);
        tabbedPane.setSize(450, 300);
		
		
		tabbedPane.addTab("Home", null, home, null);
		
		
		tabbedPane.addTab("Histórico", null, historico, null);
		

	
		tabbedPane.addTab("Vagas", null, vagas, null);
	}
}
