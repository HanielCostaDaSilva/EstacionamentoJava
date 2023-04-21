package gui;

import java.awt.EventQueue;

import javax.annotation.processing.FilerException;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import classes.Estacionamento;


public class JanelaPrincipalV2 {
	private JFrame frmPrincipal;
    private Historico historico;
    private Vagas vagas;
    private Home home;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
                    
					JanelaPrincipalV2 window = new JanelaPrincipalV2();
					window.frmPrincipal.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
    

    
	/**
	 * Create the application.
	 */
	public JanelaPrincipalV2() {
        try {
            Estacionamento estacionamento = new Estacionamento(10);
            historico = new Historico(estacionamento);
            vagas = new Vagas(estacionamento);
            home = new Home(estacionamento);
            historico.updateData(estacionamento);
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
		frmPrincipal.setResizable(false);
		frmPrincipal.setTitle("Principal");
		frmPrincipal.setBounds(50, 50, 450, 330);
		frmPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmPrincipal.getContentPane().setLayout(null);	

        /*
         * parte da home, no qual ficam os botões de inserção e remoção dos veículos do estacionamento
         */;

        //Vagas
	

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 0, 450, 349);
		frmPrincipal.getContentPane().add(tabbedPane);
        tabbedPane.setSize(450, 300);
		
		//criará as abas

		
		tabbedPane.addTab("Home", null, home, null);

		tabbedPane.addTab("Histórico", null, historico, null);
		
		tabbedPane.addTab("Vagas", null, vagas, null);

        

	}
}
