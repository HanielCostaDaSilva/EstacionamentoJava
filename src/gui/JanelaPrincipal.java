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
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;

public class JanelaPrincipal {

	private JFrame frmMenuPrincipal;
	private Estacionamento estacionamento;

	private TelaCadastrarPlaca telaEntrada = null;
	private TelaRemoverPlaca telaSaida = null;

	private TelaTransferirPlaca telaTransferir = null;
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
		operacoesBasicasPanel.setBackground(new Color(0, 0, 0, 0));
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
				telaSaida = new TelaRemoverPlaca(estacionamento);
				telaSaida.setVisible();
			}
		});

		JButton registrarEntradaBtn = new JButton("Registrar entrada");
		registrarEntradaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		registrarEntradaBtn.setBackground(new Color(45, 132, 32));
		registrarEntradaBtn.setForeground(new Color(255, 255, 255));

		registrarEntradaBtn.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				telaEntrada = new TelaCadastrarPlaca(estacionamento);
				telaEntrada.setVisible();

			}
		});

		JButton registrarTransferenciaBtn = new JButton("Registrar Transferência");
		registrarTransferenciaBtn.setFont(new Font("Yu Gothic Medium", Font.BOLD, 13));
		registrarTransferenciaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				telaTransferir = new TelaTransferirPlaca(estacionamento);
				telaTransferir.setVisible();
			}
		});
		registrarTransferenciaBtn.setForeground(new Color(255, 255, 255));
		registrarTransferenciaBtn.setBackground(new Color(187, 194, 54));

		GroupLayout gl_operacoesBasicasPanel = new GroupLayout(operacoesBasicasPanel);
		gl_operacoesBasicasPanel.setHorizontalGroup(
				gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_operacoesBasicasPanel.createSequentialGroup()
								.addContainerGap()
								.addGroup(gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(registrarTransferenciaBtn, Alignment.TRAILING,
												GroupLayout.DEFAULT_SIZE, 273, Short.MAX_VALUE)
										.addComponent(registrarSaidaBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												273, Short.MAX_VALUE)
										.addComponent(registrarEntradaBtn, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE,
												273, Short.MAX_VALUE))
								.addContainerGap()));
		gl_operacoesBasicasPanel.setVerticalGroup(
				gl_operacoesBasicasPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_operacoesBasicasPanel.createSequentialGroup()
								.addGap(5)
								.addComponent(registrarEntradaBtn, GroupLayout.PREFERRED_SIZE, 64,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(registrarSaidaBtn, GroupLayout.PREFERRED_SIZE, 62,
										GroupLayout.PREFERRED_SIZE)
								.addGap(18)
								.addComponent(registrarTransferenciaBtn, GroupLayout.PREFERRED_SIZE, 72,
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

		String[] livres = estacionamento.listarGeral();
		for (String i : livres) {
			vagasGeralTextArea.setText(vagasGeralTextArea.getText() + i + "\n");
		};

		frmMenuPrincipal.getContentPane().add(vagasGeralTextArea);

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

		JMenuBar menuPrincipal = new JMenuBar();
		frmMenuPrincipal.setJMenuBar(menuPrincipal);

		JMenu RegistrarMenu = new JMenu("Registrar");
		menuPrincipal.add(RegistrarMenu);

		JMenuItem registrarEntradaMenuItem = new JMenuItem("Entrada");

		registrarEntradaMenuItem.setBackground(new Color(45, 132, 32));
		registrarEntradaMenuItem.setForeground(new Color(255, 255, 255));

		registrarEntradaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarEntradaBtn.doClick(); // Executa a ação do botão
			}
		});

		RegistrarMenu.add(registrarEntradaMenuItem);

		JMenuItem registrarSaidaMenuItem = new JMenuItem("Saída");
		registrarSaidaMenuItem.setBackground(new Color(132, 45, 32));
		registrarSaidaMenuItem.setForeground(new Color(255, 255, 255));

		registrarSaidaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarSaidaBtn.doClick(); // Executa a ação do botão
			}
		});

		RegistrarMenu.add(registrarSaidaMenuItem);

		JMenuItem registrarTransferenciaMenuItem = new JMenuItem("Transferência");
		registrarTransferenciaMenuItem.setBackground(new Color(187, 194, 54));
		registrarTransferenciaMenuItem.setForeground(new Color(255, 255, 255));
		registrarTransferenciaMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				registrarTransferenciaBtn.doClick(); // Executa a ação do botão
			}
		});
		RegistrarMenu.add(registrarTransferenciaMenuItem);

		JMenu consultarMenu = new JMenu("Consultar");
		menuPrincipal.add(consultarMenu);

		JMenuItem consultarHistoricoMenuItem = new JMenuItem("Histórico");
		consultarMenu.add(consultarHistoricoMenuItem);

		JMenuItem consultarVagaGeralMenuItem = new JMenuItem("Todas as Vagas");
		consultarMenu.add(consultarVagaGeralMenuItem);

		JMenuItem consultarVagasLivresMenuItem = new JMenuItem("Vagas Livres");

		consultarMenu.add(consultarVagasLivresMenuItem);
		frmMenuPrincipal.setVisible(true);

	};

	public void getJanela(String janela) {

		switch (janela) {
			case "ENTRADA":
				if (telaEntrada == null) {
					telaEntrada = new TelaCadastrarPlaca(estacionamento);
				}

			case "SAIDA":
				if (telaSaida == null) {
					telaSaida = new TelaRemoverPlaca(estacionamento);
				}
				/*
				 * case "TRANSFERENCIA":
				 * if (telaTransferencia == null) {
				 * telaTransferencia = new TelaCadastrarPlaca(estacionamento);
				 * }
				 * 
				 */
		}
	}
}
