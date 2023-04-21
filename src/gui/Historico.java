package gui;
import classes.Estacionamento;
import operadorCSV.OperadorArquivoCSV;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

public class Historico extends JPanel{
    private static final long serialVersionUID = 1L;
	private int page = 1;
    private ArrayList<String> dados = new ArrayList<String>();
    private JTable table = new JTable();
	private  JButton recarregar = new JButton("R");
	private JButton nextButton = new JButton(">");
	private JButton backButton = new JButton("<");
	private JLabel lblHistrico = new JLabel("Página "+page);

    public Historico(Estacionamento estacionamento){
        updateData(estacionamento);
        initComponents(estacionamento);
    }
    public void updateData(Estacionamento estacionamento){
        dados = (ArrayList<String>) OperadorArquivoCSV.lerArquivo("historico.csv");
		Collections.reverse(dados);
        dados.remove(dados.size()-1);
    }
    //verifica se há conteudo para a próxima página, caso não haja, irá broquear o botão
    public boolean nextPage(){
        try{
            int num = page*13;
            dados.get(num);
            return true;
        }catch(Exception e){
            return false;
        }
        
    };
    public Object[] getLine(int num) {
		
		try {
            String aux = (String) dados.get(num);
            String[] componentes= aux.split(";");
			Object[] line = {componentes[0],componentes[1],componentes[2],componentes[3]};
			return line;
		}catch(Exception e) {
			Object[] line = {null,null,null,null};
			return line;
		}
		
		
	}

    public  void getHistorico(int num) {
		int aux = (num-1)*13;
		Object[][] algo = {
				getLine(aux+0),
				getLine(aux+1),
				getLine(aux+2),
				getLine(aux+3),
				getLine(aux+4),
				getLine(aux+5),
				getLine(aux+6),
				getLine(aux+7),
				getLine(aux+8),
				getLine(aux+9),
				getLine(aux+10),
				getLine(aux+11),
				getLine(aux+12)
		};
		table.setModel(new DefaultTableModel(
						(Object[][]) algo,
						new String[] {
							"data", "vaga", "placa", "situa\u00E7\u00E3o"
						}
					));
				table.getColumnModel().getColumn(0).setPreferredWidth(145);
				table.getColumnModel().getColumn(0).setMinWidth(145);
				table.getColumnModel().getColumn(1).setPreferredWidth(50);
				table.getColumnModel().getColumn(1).setMinWidth(50);
				table.getColumnModel().getColumn(3).setPreferredWidth(85);
				table.getColumnModel().getColumn(3).setMinWidth(75);
	}


    public void initComponents(Estacionamento estacionamento){
        setLayout(null);
        setBounds(0, 0, 450, 300);
		table.setBorder(new LineBorder(Color.BLACK));
		table.setFont(new Font("Dialog", Font.BOLD, 12));
		getHistorico(page);

		backButton.setBounds(284, 3, 55, 25);
		table.setBounds(39, 31, 357, 227);
		nextButton.setBounds(342, 3, 55, 25);
		lblHistrico.setBounds(39, 10, 100, 15);
		recarregar.setBounds(230, 3, 50, 25);

		add(backButton);
		add(nextButton);
		add(recarregar);
		add(table);
		add(lblHistrico);

		table.setEnabled(false);
		backButton.setEnabled(false);

		backButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page -=1;
                updateData(estacionamento);
				lblHistrico.setText("Página "+page);
				getHistorico(page);
                nextButton.setEnabled(nextPage());
				if(page==1) {
					backButton.setEnabled(false);
				}
            }
           
		});

		nextButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				page +=1;
                updateData(estacionamento);
				getHistorico(page);
				lblHistrico.setText("Página "+page);
				backButton.setEnabled(true);
                nextButton.setEnabled(nextPage());
			}
		});

		recarregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
                updateData(estacionamento);
				lblHistrico.setText("Página "+page);
				getHistorico(page);
                nextButton.setEnabled(nextPage());
				if(page==1) {
					backButton.setEnabled(false);
				}
			}
		});

    }
}
