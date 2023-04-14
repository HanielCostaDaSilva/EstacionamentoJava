package gui;

import classes.Estacionamento;

import javax.annotation.processing.FilerException;
import javax.swing.JFrame;

public class GUIteste {

    public static void main(String[] args){
		try {
			Estacionamento estac = new Estacionamento(10);
			Historico historic = new Historico(estac);
			JFrame frmEntrada = new JFrame();
			frmEntrada.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frmEntrada.setResizable(false);
			frmEntrada.setTitle("TESTE");
			frmEntrada.setBounds(100, 100, 550, 400);
			frmEntrada.setVisible(true);
			frmEntrada.add(historic);
			frmEntrada.setLayout(null);
		} catch (FilerException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		

    }
}