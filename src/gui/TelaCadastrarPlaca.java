package gui;

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
import gui.tools.IntegerOnlyFilter;
import javax.swing.SwingConstants;

public class TelaCadastrarPlaca {

	private JFrame frmEntrada;
	private JTextField PlacaInput;
	private JTextField VagaInput;

	private Estacionamento estacionamento;
	private JTextField mensagemCode;

	/**
	 * Launch the application.
	 */

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Estacionamento E1 = new Estacionamento(10);
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
		try {
			this.estacionamento = estacionamento;
		} catch (Exception e) {
			mensagemCode.setText(e.getMessage());
		}
		;

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
		frmEntrada.setBackground(new Color(192, 192, 192));
		frmEntrada.setBounds(550, 100, 427, 299);

		JButton EntradaPlacaBtn = new JButton("Entrar");
		EntradaPlacaBtn.setForeground(Color.WHITE);
		EntradaPlacaBtn.setBounds(10, 204, 391, 57);
		EntradaPlacaBtn.setBackground(new Color(45, 135, 32));

		EntradaPlacaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String placa;
				int vaga;
				try {
					placa = PlacaInput.getText().toUpperCase();
					vaga = Integer.parseInt(VagaInput.getText());
				}

				catch (Exception E) {
					mensagemCode.setText("Ops... Algum campo ficou em branco");
					return;
				};
				
				String msg = RegistrarNovaPlaca(placa, vaga);
				mensagemCode.setText(msg);
			}
		});
		frmEntrada.getContentPane().setLayout(null);
		frmEntrada.getContentPane().add(EntradaPlacaBtn);

		JPanel FormularioPainel = new JPanel();
		FormularioPainel.setBounds(10, 11, 391, 192);
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
		mensagemCode.setHorizontalAlignment(SwingConstants.CENTER);
		mensagemCode.setText("Mensagem de Status");
		mensagemCode.setForeground(Color.ORANGE);
		mensagemCode.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
		mensagemCode.setEnabled(false);
		mensagemCode.setEditable(false);
		mensagemCode.setColumns(10);
		mensagemCode.setBackground(new Color(36, 34, 34));
		mensagemCode.setBounds(0, 157, 391, 35);
		FormularioPainel.add(mensagemCode);
	}

	public void setVisible() {
		this.frmEntrada.setVisible(true);
	}

	private String RegistrarNovaPlaca(String placa, int vaga) {
		try {
			if (!placa.equals("")){
				this.estacionamento.entrar(placa, vaga);
		
				PlacaInput.setText("");
				VagaInput.setText("");

				return "Placa: " + placa + " inserido na Vaga " + vaga;
			}else{
				return "Espaço vazio";
			}
			

		} catch (Exception e) {
			VagaInput.setText("");
			return e.getMessage();
		}
	}
}