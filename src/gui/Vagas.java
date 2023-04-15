package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Estacionamento;

public class Vagas extends JPanel{
    private JTextArea VagasVisor = new JTextArea();
	private JComboBox comboBox = new JComboBox();
    private JScrollPane scrollPane = new JScrollPane();


    public Vagas(Estacionamento estacionamento){
        initComponents(estacionamento);
    }

    public void atualizaPlacas(Estacionamento estacionamento){
        String[] placas = estacionamento.listarGeral();
        String aux="";
		
		switch(comboBox.getSelectedIndex()){
			case 0:
			for(String placa:placas){
				aux+=placa+"\n";
			}
			break;
			case 1:
			for(String placa:placas){
				if(!placa.contains("livre")){
					aux+=placa+"\n";
				}
			}
			break;
			case 2:
			for(String placa:placas){
				if(placa.contains("livre")){
					aux+=placa+"\n";
				}
			}
			break;
		}
        VagasVisor.setText(aux);
    }

    

    private void initComponents(Estacionamento estacionamento) {


		scrollPane.setBounds(10, 12, 168, 250);
		add(scrollPane);
		
		
		scrollPane.setViewportView(VagasVisor);
        VagasVisor.setEditable(false);
		VagasVisor.setText("");
		
		
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"todas", "ocupadas", "livres"}));
		comboBox.setBounds(188, 12, 118, 25);
		add(comboBox);
        comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				atualizaPlacas(estacionamento);
			}
		});

    }
}
