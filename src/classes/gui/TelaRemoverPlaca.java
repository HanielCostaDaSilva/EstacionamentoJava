package classes.gui;

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
import classes.gui.ferramentas.IntegerOnlyFilter;

public class TelaRemoverPlaca {

	private JFrame frmSaida;
	private JTextField SaidaInput;

	private Estacionamento estacionamento;

	/**
	 * Launch the application.
	 */
	

	
	 	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//só para teste
					Estacionamento E1=new Estacionamento(10);
					TelaRemoverPlaca window = new TelaRemoverPlaca(E1);
					window.frmSaida.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}



	/**
	 * Create the application.
	 */
	public TelaRemoverPlaca(Estacionamento est){
		this.estacionamento=est;
		initialize();
	}
	
	/**
	 * Remove a license plate from the parking.
	*/
	
	private String RemovePlate(int vaga){
		try{
			this.estacionamento.sair(vaga);
			return "Vaga: "+ vaga + " foi liberada com sucesso";
		}catch(Exception e){
			return e.getMessage();
		}

	}
	/**
	 * Initialize the contents of the frame.
	*/
	private void initialize() {
		frmSaida = new JFrame();
		frmSaida.setTitle("Saída");
		frmSaida.setBackground(Color.LIGHT_GRAY);
		frmSaida.setResizable(false);
		frmSaida.setBounds(100, 100, 386, 240);
		frmSaida.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSaida.getContentPane().setLayout(null);
		
		SaidaInput = new JTextField();
		SaidaInput.setBounds(92, 56, 233, 54);
		SaidaInput.setToolTipText("");
		SaidaInput.setFont(new Font("Yu Gothic Medium", Font.BOLD, 16));
		SaidaInput.setColumns(10);

		((AbstractDocument) SaidaInput.getDocument()).setDocumentFilter(new IntegerOnlyFilter());

		frmSaida.getContentPane().add(SaidaInput);
		
		JLabel VagaLabel = new JLabel("Vaga");
		VagaLabel.setBounds(31, 61, 51, 31);
		VagaLabel.setFont(new Font("Source Serif Pro Light", Font.BOLD, 22));
		frmSaida.getContentPane().add(VagaLabel);


		JTextField mensagemCode = new JTextField();
		mensagemCode.setForeground(Color.ORANGE);
		mensagemCode.setEnabled(false);
		mensagemCode.setEditable(false);
		mensagemCode.setBackground(new Color(36, 34, 34));
		mensagemCode.setBounds(10, 120, 350, 20);
		mensagemCode.setText("Mensagem de Status");

		frmSaida.add(mensagemCode);
		mensagemCode.setColumns(10);

		JButton RegistrarSaidaBtn = new JButton("Registrar Saída");
		RegistrarSaidaBtn.setForeground(Color.WHITE);
		RegistrarSaidaBtn.setBackground(Color.RED);
		RegistrarSaidaBtn.setBounds(10, 136, 350, 54);

		RegistrarSaidaBtn.addActionListener(new ActionListener() {
			
			public void   actionPerformed(ActionEvent e){
				int VagaSaida;
				try{
					VagaSaida= Integer.parseInt(SaidaInput.getText());
					
				}catch(Exception E){
					mensagemCode.setText("Ops... Algum campo ficou em branco");
					return;
				}

				String msg=RemovePlate(VagaSaida);
				mensagemCode.setText(msg);
				SaidaInput.setText("");
			}
		});

		frmSaida.getContentPane().add(RegistrarSaidaBtn);
	}
}
