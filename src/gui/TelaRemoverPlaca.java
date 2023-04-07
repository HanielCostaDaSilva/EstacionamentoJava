package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.text.AbstractDocument;

import classes.Estacionamento;
import gui.tools.IntegerOnlyFilter;
import javax.swing.JPanel;

public class TelaRemoverPlaca {

	private JFrame saidaPlacaFrame;
	private JTextField SaidaInput;

	private Estacionamento estacionamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// só para teste
					Estacionamento E1 = new Estacionamento(10);
					TelaRemoverPlaca window = new TelaRemoverPlaca(E1);
					window.saidaPlacaFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaRemoverPlaca(Estacionamento est) {
		this.estacionamento = est;
		initialize();
	}

	/**
	 * Remove a license plate from the parking.
	 */

	private String RemovePlate(int vaga) {
		try {
			this.estacionamento.sair(vaga);
			return "Vaga: " + vaga + " foi liberada com sucesso";
		} catch (Exception e) {
			return e.getMessage();
		}

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		saidaPlacaFrame = new JFrame();
		saidaPlacaFrame.setTitle("Saída");

		saidaPlacaFrame.setBackground(new Color(192, 192, 192));
		saidaPlacaFrame.setResizable(false);
		saidaPlacaFrame.setBounds(100, 100, 386, 240);
		saidaPlacaFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		saidaPlacaFrame.getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 350, 137);
		saidaPlacaFrame.getContentPane().add(panel);
		panel.setLayout(null);

		JTextField mensagemCode = new JTextField();
		mensagemCode.setBounds(0, 102, 350, 35);
		panel.add(mensagemCode);
		mensagemCode.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
		mensagemCode.setForeground(Color.ORANGE);
		mensagemCode.setEnabled(false);
		mensagemCode.setEditable(false);
		mensagemCode.setBackground(new Color(36, 34, 34));
		mensagemCode.setText("Mensagem de Status");
		mensagemCode.setColumns(10);

		JLabel VagaLabel = new JLabel("Vaga");
		VagaLabel.setBounds(10, 34, 51, 31);
		panel.add(VagaLabel);
		VagaLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));

		SaidaInput = new JTextField();
		SaidaInput.setBounds(71, 30, 233, 54);
		panel.add(SaidaInput);
		SaidaInput.setToolTipText("");
		SaidaInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		SaidaInput.setColumns(10);

		((AbstractDocument) SaidaInput.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

		JButton RegistrarSaidaBtn = new JButton("Registrar Saída");
		RegistrarSaidaBtn.setForeground(Color.WHITE);
		RegistrarSaidaBtn.setBackground(new Color(138, 26, 37));
		RegistrarSaidaBtn.setBounds(10, 147, 350, 54);

		RegistrarSaidaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int VagaSaida;
				try {
					VagaSaida = Integer.parseInt(SaidaInput.getText());

				} catch (Exception E) {
					mensagemCode.setText("Ops... Algum campo ficou em branco");
					return;
				}

				String msg = RemovePlate(VagaSaida);
				mensagemCode.setText(msg);
				SaidaInput.setText("");
			}
		});

		saidaPlacaFrame.getContentPane().add(RegistrarSaidaBtn);
	}

	public void setVisible() {
		this.saidaPlacaFrame.setVisible(true);
	}
}
