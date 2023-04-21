package gui;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import classes.Estacionamento;

public class Vagas extends JPanel{
    private JTextArea VagasVisor = new JTextArea();
	private JComboBox<String> comboBox = new JComboBox<>();
    private JScrollPane scrollPane = new JScrollPane();
	private DefaultComboBoxModel<String> lista = new DefaultComboBoxModel<>(new String[] {"todas", "ocupadas", "livres"});

    public Vagas(Estacionamento estacionamento){
        initComponents(estacionamento);
		atualizaPlacas(estacionamento);
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

		setLayout(null);
        setBounds(0, 0, 450, 300);

		scrollPane.setViewportView(VagasVisor);
        VagasVisor.setEditable(false);
		VagasVisor.setText("");
		
		
		
		//Create the combo box, select item at index 4.
		//Indices start at 0, so 4 specifies the pig.
		comboBox.setModel(lista);
		comboBox.setBounds(188, 12, 118, 25);
		add(comboBox);
		scrollPane.setBounds(10, 12, 168, 250);
		add(scrollPane);
        comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				atualizaPlacas(estacionamento);
			}
		});

    }
}
