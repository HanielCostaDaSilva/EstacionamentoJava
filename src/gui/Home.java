package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import classes.Estacionamento;

public class Home extends JPanel{
    private TelaCadastrarPlaca telaEntrada;
	private TelaRemoverPlaca telaSaida;
	private TelaTransferirPlaca telaTransferir;
    private JTextField pesquisarPlacaInput = new JTextField();
    private JPanel PanelOperacional = new JPanel();
    private JPanel PanelPesquisa = new JPanel();
    private JButton registrarTransferenciaBtn = new JButton("Registrar transferência");
    private JButton pesquisarplaca = new JButton("Ir");
    private JButton registrarEntradaBtn = new JButton("Registrar Entrada");
    private JButton registrarSaidaBtn = new JButton("Registrar Saída");
    private final JLabel lblNewPlaca = new JLabel("Placa :");
	private final JLabel lblNewVaga = new JLabel("Vaga :");
    private JLabel pesquisalabel = new JLabel("Pesquisar Placa");



    public Home(Estacionamento estacionamento){
        initComponents(estacionamento);
    }

    public void pesquisarPlaca(Estacionamento estacionamento){
		int vaga = estacionamento.consultarPlaca(pesquisarPlacaInput.getText());
		lblNewPlaca.setText("Placa : "+((pesquisarPlacaInput.getText().equals(""))?"vazio":pesquisarPlacaInput.getText()));
		lblNewVaga.setText("Vaga : "+((vaga>0)?vaga:"inexistente"));
		pesquisarPlacaInput.setText("");
	}

    private void initComponents(Estacionamento estacionamento) {
        setLayout(null);
        setBounds(0, 0, 450, 300);
        add(PanelOperacional);
		add(PanelPesquisa);
        
        PanelOperacional.setLayout(null);
		PanelPesquisa.setLayout(null);
		
		pesquisarPlacaInput.setColumns(10);
		
		lblNewPlaca.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPlaca.setForeground(new Color(60,60,60));
		lblNewPlaca.setBackground(new Color(36, 31, 49));
		
		lblNewVaga.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblNewVaga.setForeground(new Color(60,60,60));
		lblNewVaga.setBackground(new Color(36, 31, 49));
			
		registrarEntradaBtn.setBackground(new Color(45, 132, 32));
		registrarEntradaBtn.setForeground(new Color(255, 255, 255));
		registrarSaidaBtn.setBackground(new Color(138, 26, 37));
		registrarSaidaBtn.setForeground(new Color(255, 255, 255));
		registrarTransferenciaBtn.setForeground(new Color(255, 255, 255));
		registrarTransferenciaBtn.setBackground(new Color(187, 194, 54));
		registrarTransferenciaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));

        PanelOperacional.add(registrarEntradaBtn);
        PanelOperacional.add(registrarTransferenciaBtn);
        PanelOperacional.add(registrarSaidaBtn);
        PanelPesquisa.add(pesquisalabel);
		PanelPesquisa.add(pesquisarPlacaInput);
        PanelPesquisa.add(lblNewVaga);
        PanelPesquisa.add(lblNewPlaca);
        PanelPesquisa.add(pesquisarplaca);

        registrarSaidaBtn.setBounds(35, 65, 327, 43);
        registrarEntradaBtn.setBounds(35, 11, 327, 43);
        registrarTransferenciaBtn.setBounds(35, 119, 327, 43);
        PanelOperacional.setBounds(10, 96, 414, 176);
        lblNewVaga.setBounds(222, 51, 192, 23);
        lblNewPlaca.setBounds(10, 51, 146, 23);
        pesquisalabel.setBounds(0, 20, 150, 18);
        PanelPesquisa.setBounds(10, 0, 414, 85);
        pesquisarPlacaInput.setBounds(120, 17, 153, 28);
        pesquisarplaca.setBounds(275, 17, 57, 28);

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
		
    }
}
