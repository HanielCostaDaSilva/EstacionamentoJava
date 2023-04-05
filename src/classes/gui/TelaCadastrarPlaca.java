package classes.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import classes.Estacionamento;
import classes.gui.ferramentas.IntegerOnlyFilter;

public class TelaCadastrarPlaca {

	private JFrame frmEntrada;
	private JTextField PlacaInput;
	private JTextField VagaInput;
	private JTextField mensagemCode;

	private Estacionamento estacionamento;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//teste			
					Estacionamento E1=new Estacionamento(10);

					TelaCadastrarPlaca window = new TelaCadastrarPlaca(E1);
					window.frmEntrada.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaCadastrarPlaca(Estacionamento estacionamento) {
		//só para teste
		try{
			this.estacionamento= estacionamento;
		}catch (Exception e){
			mensagemCode.setText(e.getMessage());
		};

		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmEntrada = new JFrame();
		frmEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmEntrada.setResizable(false);
		frmEntrada.setTitle("Entrada");
		frmEntrada.setBackground(Color.LIGHT_GRAY);
		frmEntrada.setBounds(100, 100, 450, 300);
		
		JButton EntradaPlacaBtn = new JButton("Entrar");
		EntradaPlacaBtn.setForeground(Color.WHITE);
		EntradaPlacaBtn.setBounds(98, 204, 243, 57);
		EntradaPlacaBtn.setBackground(new Color(0, 255, 0));
		
		EntradaPlacaBtn.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			String placa;
			int vaga;
			try{
				placa= PlacaInput.getText().toUpperCase();
				vaga= Integer.parseInt(VagaInput.getText());
			}

			catch(Exception E){
			mensagemCode.setText("Ops... Algum campo ficou em branco");
			return;
		};
			
			String msg= RegistrarNovaPlaca(placa, vaga);
			VagaInput.setText("");
			PlacaInput.setText("");
			mensagemCode.setText(msg);
			}
		});
		frmEntrada.getContentPane().setLayout(null);
		frmEntrada.getContentPane().add(EntradaPlacaBtn);
		
		JPanel FormularioPainel = new JPanel();
		FormularioPainel.setBounds(10, 11, 414, 192);
		frmEntrada.getContentPane().add(FormularioPainel);
		FormularioPainel.setLayout(null);
		
		JLabel PlacaLabel = new JLabel("Placa");
		PlacaLabel.setLabelFor(PlacaLabel);
		PlacaLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));
		PlacaLabel.setBounds(10, 31, 77, 40);
		FormularioPainel.add(PlacaLabel);
		
		JLabel VagaLabel = new JLabel("Vaga");
		VagaLabel.setLabelFor(VagaLabel);
		VagaLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));
		VagaLabel.setBounds(10, 95, 77, 40);
		FormularioPainel.add(VagaLabel);
		
		PlacaInput = new JTextField();
		PlacaInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		PlacaInput.setBounds(109, 35, 264, 40);
		FormularioPainel.add(PlacaInput);
		PlacaInput.setColumns(10);
		
		VagaInput = new JTextField();
		VagaInput.setToolTipText("");
		VagaInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		VagaInput.setColumns(10);
		VagaInput.setBounds(109, 95, 264, 40);
		((AbstractDocument) VagaInput.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

		FormularioPainel.add(VagaInput);
		
		mensagemCode = new JTextField();
		mensagemCode.setForeground(new Color(36, 34, 34));
		mensagemCode.setEnabled(false);
		mensagemCode.setEditable(false);
		mensagemCode.setBackground(new Color(36, 34, 34));
		mensagemCode.setBounds(10, 161, 394, 20);
		mensagemCode.setText("Mensagem de Status");

		FormularioPainel.add(mensagemCode);
		mensagemCode.setColumns(10);
	}

	private String RegistrarNovaPlaca(String placa, int vaga){
		try{
			this.estacionamento.entrar(placa, vaga);
			return "Placa: "+placa +" inserido na Vaga "+ vaga;

		}catch(Exception e){
			return e.getMessage();
		}
	}
}
