package classes.gui;
import classes.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.annotation.processing.FilerException;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;


public class Gui {

	private JFrame frame;
	private JTextField placaField;
	private JTextField vagaField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Gui() {
        try {
            Estacionamento estacionamento = new Estacionamento(10);
            initialize(estacionamento);
        } catch (FilerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
    
	private void initialize(Estacionamento estacionamento) {
        
		frame = new JFrame();
		frame.getContentPane().setBackground(UIManager.getColor("Button.darkShadow"));
		frame.setBounds(100, 100, 350, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
        frame.setVisible(true);
		
		JLabel textplaca = new JLabel("placa:");
		textplaca.setBounds(38, 12, 51, 32);
		frame.getContentPane().add(textplaca);
		
		JLabel textvaga = new JLabel("vaga:");
		textvaga.setBounds(43, 46, 70, 15);
		frame.getContentPane().add(textvaga);
		
		placaField = new JTextField();
		placaField.setBounds(100, 19, 114, 19);
		frame.getContentPane().add(placaField);
		placaField.setColumns(10);
		
		vagaField = new JTextField();
		vagaField.setBounds(100, 44, 114, 19);
		frame.getContentPane().add(vagaField);
		vagaField.setColumns(10);
		
		JTextArea vagasLivres = new JTextArea();
		JScrollPane scrollPane = new JScrollPane(vagasLivres);
		scrollPane.setBounds(12, 144, 139, 194);
		frame.getContentPane().add(scrollPane);
		
		JLabel lblVagas = new JLabel("vagas disponiveis");
		lblVagas.setBounds(20, 114, 139, 30);
		frame.getContentPane().add(lblVagas);
		
        JTextArea listaPlacas = new JTextArea();
        listaPlacas.setEditable(false);
		JScrollPane scrollPane2 = new JScrollPane(listaPlacas);
		scrollPane2.setBounds(180, 144, 139, 194);
		frame.getContentPane().add(scrollPane2);
		
		JLabel idk = new JLabel("vagas e placas");
		idk.setBounds(195, 114, 139, 30);
		frame.getContentPane().add(idk);
		
		JButton btnAdicionar = new JButton("Adicionar");
		btnAdicionar.setBackground(new Color(83, 120, 62));
		btnAdicionar.setBounds(221, 16, 117, 25);
		frame.getContentPane().add(btnAdicionar);
		
		JButton btnRemover = new JButton("Remover");
		btnRemover.setBackground(new Color(237, 51, 59));
		btnRemover.setBounds(221, 41, 117, 25);
		frame.getContentPane().add(btnRemover);
		

		btnAdicionar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
               try {

                estacionamento.entrar(placaField.getText(),Integer.parseInt(vagaField.getText()));
                vagaField.setText("");
                placaField.setText("");
                String[] livres = estacionamento.listarGeral();
                String s="";
                for(int i=0; i < livres.length; i++){
                    s+=" "+livres[i]+"\n";
                }
                listaPlacas.setText(s);
            } catch (FilerException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
                
			}
		});
		
		btnRemover.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                try {

                    estacionamento.sair(Integer.parseInt(vagaField.getText()));
                    vagaField.setText("");
                    placaField.setText("");
                    String[] livres = estacionamento.listarGeral();
                    String s="";
                    for(int i=0; i < livres.length; i++){
                        s+=" "+livres[i]+"\n";
                    }
                    listaPlacas.setText(s);
                } catch (FilerException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
			}
		});
		
	}
}
