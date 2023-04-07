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

public class TelaTransferirPlaca {

	private JFrame frmTranferir;
	private JTextField vagaOrigemInput;
	private JTextField vagaDestinoInput;

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
					TelaTransferirPlaca window = new TelaTransferirPlaca(E1);
					window.frmTranferir.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaTransferirPlaca(Estacionamento estacionamento) {
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
		frmTranferir = new JFrame();
		frmTranferir.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmTranferir.setResizable(false);
		frmTranferir.setTitle("Tranferência");
		frmTranferir.setBackground(new Color(192, 192, 192));
		frmTranferir.setBounds(100, 100, 450, 300);

		JButton TransferenciaPlacaBtn = new JButton("Transferir");
		TransferenciaPlacaBtn.setForeground(Color.WHITE);
		TransferenciaPlacaBtn.setBounds(10, 204, 414, 57);
		TransferenciaPlacaBtn.setBackground(new Color(187, 194, 54));

		TransferenciaPlacaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int vagaOrigem;
				int vagaDestino;
				try {
					vagaOrigem = Integer.parseInt(vagaOrigemInput.getText());
					vagaDestino = Integer.parseInt(vagaDestinoInput.getText());
				}

				catch (Exception E) {
					mensagemCode.setText("Ops... Algum campo ficou em branco");
					return;
				}
				;
				String msg = TransferirPlacas(vagaOrigem, vagaDestino);
				vagaDestinoInput.setText("");
				vagaOrigemInput.setText("");
				mensagemCode.setText(msg);
			}
		});
		frmTranferir.getContentPane().setLayout(null);
		frmTranferir.getContentPane().add(TransferenciaPlacaBtn);

		JPanel FormularioPainel = new JPanel();
		FormularioPainel.setBounds(10, 11, 414, 192);
		frmTranferir.getContentPane().add(FormularioPainel);
		FormularioPainel.setLayout(null);

		JLabel vagaInicialLabel = new JLabel("Origem");
		vagaInicialLabel.setLabelFor(vagaInicialLabel);
		vagaInicialLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));
		vagaInicialLabel.setBounds(10, 31, 99, 40);
		FormularioPainel.add(vagaInicialLabel);

		JLabel vagaDestinoLabel = new JLabel("Destino");
		vagaDestinoLabel.setLabelFor(vagaDestinoLabel);
		vagaDestinoLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));
		vagaDestinoLabel.setBounds(10, 95, 99, 40);
		FormularioPainel.add(vagaDestinoLabel);

		vagaOrigemInput = new JTextField();
		vagaOrigemInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		vagaOrigemInput.setBounds(109, 35, 264, 40);
		FormularioPainel.add(vagaOrigemInput);
		vagaOrigemInput.setColumns(10);
		((AbstractDocument) vagaOrigemInput.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

		vagaDestinoInput = new JTextField();
		vagaDestinoInput.setToolTipText("");
		vagaDestinoInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		vagaDestinoInput.setColumns(10);
		vagaDestinoInput.setBounds(109, 95, 264, 40);
		((AbstractDocument) vagaDestinoInput.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

		FormularioPainel.add(vagaDestinoInput);

		mensagemCode = new JTextField();
		mensagemCode.setText("Mensagem de Status");
		mensagemCode.setForeground(Color.ORANGE);
		mensagemCode.setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 15));
		mensagemCode.setEnabled(false);
		mensagemCode.setEditable(false);
		mensagemCode.setColumns(10);
		mensagemCode.setBackground(new Color(36, 34, 34));
		mensagemCode.setBounds(0, 152, 414, 40);
		FormularioPainel.add(mensagemCode);
	}

	public void setVisible() {
		this.frmTranferir.setVisible(true);
	}

	private String TransferirPlacas(int origem, int destino) {
		try {
			this.estacionamento.transferir(origem, destino);
			return "Sucesso! O veiculo antes na vaga " + origem + " foi transferido para a vaga " + destino;

		} catch (Exception e) {
			return e.getMessage();
		}
	}
}