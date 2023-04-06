package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.annotation.processing.FilerException;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle.ComponentPlacement;

import classes.Estacionamento;

public class JanelaPrincipal {

	private JFrame frmMenuPrincipal;

	private Estacionamento estacionamento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaPrincipal window = new JanelaPrincipal();
					window.frmMenuPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaPrincipal() {
		try {
			this.estacionamento = new Estacionamento(10);
			initialize();
		} catch (FilerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/*
	 * Initialize the contents of the frame.
	 */

	private void initialize() {

		frmMenuPrincipal = new JFrame();
		frmMenuPrincipal.setBackground(Color.LIGHT_GRAY);
		frmMenuPrincipal.setTitle("Menu Principal");
		frmMenuPrincipal.getContentPane().setBackground(Color.LIGHT_GRAY);
		frmMenuPrincipal.setBounds(100, 100, 777, 478);
		frmMenuPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMenuPrincipal.getContentPane().setLayout(null);

		JPanel operacoesBasicasPanel = new JPanel();
		operacoesBasicasPanel.setBackground(new Color(240, 240, 240, 0));
		// operacoesBasicasPanel.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new
		// Color(0, 0, 0)));
		operacoesBasicasPanel.setBounds(217, 55, 314, 237);
		frmMenuPrincipal.getContentPane().add(operacoesBasicasPanel);

		JButton registrarSaidaBtn = new JButton("Registrar Saida");
		registrarSaidaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 14));
		registrarSaidaBtn.setBackground(new Color(138, 26, 37));
		registrarSaidaBtn.setForeground(new Color(255, 255, 255));

		registrarSaidaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				TelaRemoverPlaca telaSairPlaca = new TelaRemoverPlaca(estacionamento);
				telaSairPlaca.setVisible();
			}
		});

		JButton registrarEntradaBtm = new JButton("Registrar entrada");
		registrarEntradaBtm.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		registrarEntradaBtm.setBackground(new Color(45, 132, 32));
		registrarEntradaBtm.setForeground(new Color(255, 255, 255));

		registrarEntradaBtm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				TelaCadastrarPlaca telaEntrarPlaca = new TelaCadastrarPlaca(estacionamento);
				telaEntrarPlaca.setVisible();
			}
		});

		JButton btnRegistrarTransferncia = new JButton("Registrar Transferência");
		btnRegistrarTransferncia.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		btnRegistrarTransferncia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegistrarTransferncia.setForeground(new Color(255, 255, 255));
		btnRegistrarTransferncia.setBackground(new Color(187, 194, 54));

		GroupLayout gl_operacoesBasicasPanel = new GroupLayout(operacoesBasicasPanel);
		gl_operacoesBasicasPanel.setHorizontalGroup(
				gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_operacoesBasicasPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(btnRegistrarTransferncia, Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
										.addComponent(registrarSaidaBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												273, Short.MAX_VALUE)
										.addComponent(registrarEntradaBtm, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												273, Short.MAX_VALUE))
								.addContainerGap()));
		gl_operacoesBasicasPanel.setVerticalGroup(
				gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_operacoesBasicasPanel.createSequentialGroup()
								.addGap(5)
								.addComponent(registrarEntradaBtm, GroupLayout.PREFERRED_SIZE, 64,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(registrarSaidaBtn, GroupLayout.PREFERRED_SIZE, 62,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(btnRegistrarTransferncia, GroupLayout.PREFERRED_SIZE, 72,
										GroupLayout.PREFERRED_SIZE)
								.addContainerGap(64, Short.MAX_VALUE)));
		operacoesBasicasPanel.setLayout(gl_operacoesBasicasPanel);

		JTextArea vagaLivreTextArea = new JTextArea();
		vagaLivreTextArea.setLineWrap(true);
		vagaLivreTextArea.setEditable(false);
		vagaLivreTextArea.setEnabled(false);
		vagaLivreTextArea.setBounds(10, 147, 197, 293);
		frmMenuPrincipal.getContentPane().add(vagaLivreTextArea);

		JTextArea vagasGeralTextArea = new JTextArea();
		vagasGeralTextArea.setLineWrap(true);
		vagasGeralTextArea.setEnabled(false);
		vagasGeralTextArea.setEditable(false);
		vagasGeralTextArea.setBounds(541, 147, 213, 293);
		frmMenuPrincipal.getContentPane().add(vagasGeralTextArea);

		JButton consultarHistoricoBtn = new JButton("Consultar Histórico");
		consultarHistoricoBtn.setForeground(Color.DARK_GRAY);
		consultarHistoricoBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		consultarHistoricoBtn.setBackground(Color.PINK);
		consultarHistoricoBtn.setBounds(233, 385, 279, 55);
		frmMenuPrincipal.getContentPane().add(consultarHistoricoBtn);

		JLabel vagasDisponiveisLabel = new JLabel("Vagas Disponíveis");
		vagasDisponiveisLabel.setLabelFor(vagaLivreTextArea);
		vagasDisponiveisLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		vagasDisponiveisLabel.setBounds(10, 119, 197, 30);
		frmMenuPrincipal.getContentPane().add(vagasDisponiveisLabel);

		JLabel vagasEmGeralLabel = new JLabel("Vagas em Geral");
		vagasEmGeralLabel.setLabelFor(vagasGeralTextArea);
		vagasEmGeralLabel.setFont(new Font("Yu Gothic Medium", Font.BOLD, 15));
		vagasEmGeralLabel.setBounds(543, 119, 197, 30);
		frmMenuPrincipal.getContentPane().add(vagasEmGeralLabel);
		frmMenuPrincipal.setVisible(true);
		String[] livres = estacionamento.listarGeral();
		for (int i = 0; i < livres.length; i++) {
		}

	}
}
