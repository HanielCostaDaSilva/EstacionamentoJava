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
import javax.swing.JTextField;

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

public class JanelaPrincipal {
    private int page = 1;
	private JFrame frmPrincipal;
	private JTable table;
    JTextArea VagasVisor = new JTextArea();
	JComboBox comboBox = new JComboBox();
	private final JLabel lblNewPlaca = new JLabel("Placa :");
	private final JLabel lblNewVaga = new JLabel("Vaga :");
	private JTextField pesquisarPlacaInput;
    private TelaCadastrarPlaca telaEntrada = null;
	private TelaRemoverPlaca telaSaida = null;
	private TelaTransferirPlaca telaTransferir = null;

    private ArrayList<String> dados = new ArrayList<String>();
	

    /**
     * funções para o funcionamento do histórico
     * 
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    
					JanelaPrincipal window = new JanelaPrincipal();
					window.frmPrincipal.setVisible(true);
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
	//pesquisa pela placa e fornece sua posição ou a ausencia dela
	public void pesquisarPlaca(Estacionamento estacionamento){
		int vaga = estacionamento.consultarPlaca(pesquisarPlacaInput.getText());
		lblNewPlaca.setText("Placa : "+((pesquisarPlacaInput.getText().equals(""))?"vazio":pesquisarPlacaInput.getText()));
		lblNewVaga.setText("Vaga : "+((vaga>0)?vaga:"inexistente"));
		pesquisarPlacaInput.setText("");
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
		
		switch(comboBox.getSelectedIndex()){
			case 0:
			for(String placa:placas){
				aux+=placa+"\n";
			}
			break;
			case 1:
			for(String placa:placas){
				if(!placa.contains("livre")){
					aux+=placa+"\n";
				}
			}
			break;
			case 2:
			for(String placa:placas){
				if(placa.contains("livre")){
					aux+=placa+"\n";
				}
			}
			break;
		}
        VagasVisor.setText(aux);
    }
	/**
	 * Create the application.
	 */
	public JanelaPrincipal() {
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
		frmPrincipal = new JFrame();
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(100, 100, 459, 333);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);
		
        JPanel historico = new JPanel();
        JPanel home = new JPanel();
        JPanel vagas = new JPanel();
        historico.setLayout(null);
        home.setLayout(null);
        vagas.setLayout(null);

        /*
         * parte da home, no qual ficam os botões de inserção e remoção dos veículos do estacionamento
         */;

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
		tabbedPane.setBounds(0, 0, 450, 349);
		frmPrincipal.getContentPane().add(tabbedPane);
        tabbedPane.setSize(450, 300);
		
		//criará as abas
		tabbedPane.addTab("Home", null, home, null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 0, 414, 85);
		home.add(panel);
		panel.setLayout(null);
		JLabel pesquisalabel = new JLabel("Pesquisar Placa");
		pesquisalabel.setBounds(0, 17, 100, 18);
		panel.add(pesquisalabel);
		pesquisarPlacaInput = new JTextField();
		pesquisarPlacaInput.setFont(new Font("Tahoma", Font.BOLD, 15));
		pesquisarPlacaInput.setBounds(98, 11, 153, 28);
		panel.add(pesquisarPlacaInput);
		pesquisarPlacaInput.setColumns(10);
		JButton pesquisarplaca = new JButton("Ir");
		pesquisarplaca.setBounds(249, 12, 57, 28);
		panel.add(pesquisarplaca);
		lblNewPlaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPlaca.setBounds(10, 51, 146, 23);
		panel.add(lblNewPlaca);
		lblNewPlaca.setForeground(new Color(60,60,60));
		lblNewPlaca.setBackground(new Color(36, 31, 49));
		lblNewVaga.setBounds(222, 51, 192, 23);
		panel.add(lblNewVaga);
		lblNewVaga.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewVaga.setForeground(new Color(60,60,60));
		lblNewVaga.setBackground(new Color(36, 31, 49));
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBounds(10, 96, 414, 176);
		home.add(panel_1);
		
		JButton registrarEntradaBtn = new JButton("Registrar Entrada");
		registrarEntradaBtn.setBounds(35, 11, 327, 43);
		panel_1.add(registrarEntradaBtn);
		registrarEntradaBtn.setBackground(new Color(45, 132, 32));
		registrarEntradaBtn.setForeground(new Color(255, 255, 255));
		
		JButton registrarSaidaBtn = new JButton("Registrar Saída");
		registrarSaidaBtn.setBounds(35, 65, 327, 43);
		panel_1.add(registrarSaidaBtn);
		registrarSaidaBtn.setBackground(new Color(138, 26, 37));
		registrarSaidaBtn.setForeground(new Color(255, 255, 255));
		
				JButton registrarTransferenciaBtn = new JButton("Registrar transferência");
				registrarTransferenciaBtn.setBounds(35, 119, 327, 43);
				panel_1.add(registrarTransferenciaBtn);
				registrarTransferenciaBtn.setForeground(new Color(255, 255, 255));
				registrarTransferenciaBtn.setBackground(new Color(187, 194, 54));
				
						registrarTransferenciaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
						registrarTransferenciaBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {

								telaTransferir = new TelaTransferirPlaca(estacionamento);
								telaTransferir.setVisible();
							}
						});
		
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
		pesquisarplaca.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				pesquisarPlaca(estacionamento);

			}
		});
		
		
		tabbedPane.addTab("Histórico", null, historico, null);
		

	
		tabbedPane.addTab("Vagas", null, vagas, null);
	}
}
