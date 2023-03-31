
package classes.gui;

import java.awt.*;

import javax.annotation.processing.FilerException;
import javax.swing.*;

import classes.Estacionamento;


public class Gui{
    void captarError(FilerException e){
        JFrame error = new JFrame("Teste");
        error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel error_Msg = new JLabel(e.getMessage());
        error_Msg.setBounds(0, 0, 400, 400);
        error.add(error_Msg);
        error.setResizable(false);
        error.setSize(400, 400);
        error.setLayout(null);  
        error.setVisible(true);

    }
    void captarError(Exception e){
        JFrame error = new JFrame("Teste");
        error.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel error_Msg = new JLabel(e.getMessage());
        error_Msg.setBounds(0, 0, 400, 400);
        error.add(error_Msg);
        error.setResizable(false);
        error.setSize(400, 400);
        error.setLayout(null);  
        error.setVisible(true);

    }


public  Gui() {
        //criar uma função para envocar uma mensagem de erro, personalizada e generalista
    

        try {
            Estacionamento estac = new Estacionamento(10);
            JFrame frame = new JFrame("Teste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        JPanel panel2 = new JPanel();

        JButton button = new JButton("OK");
        JLabel placa = new JLabel("placa:");
        JLabel vaga = new JLabel("vaga:");
      
        JTextField placa_tf = new JTextField(10);
        JTextField vaga_tf = new JTextField(10);


        panel.add(placa);
        panel.add(placa_tf);
        panel2.add(vaga);
        panel2.add(vaga_tf);


        
        /*
        //setBounds
        componentsList.setBounds(100, 60, 80, 40);
        comp.setBounds(10, 50, 100, 50);
        //add Frame
        frame.add(comp);
        frame.add(componentsList);*/
        button.setFocusable(false);
        
        panel.setBounds(0, 0, 200, 25);
        panel2.setBounds(2, 25, 200, 25);

        frame.getContentPane().add(panel);
		frame.getContentPane().add(panel2);
        frame.setResizable(false);
        frame.setSize(480, 200);
        frame.setLayout(null);  
        
        frame.setVisible(true);
        } catch (FilerException e) {
            captarError(e);
        } catch (Exception e) {
            captarError(e);
        }


		
        
	}
}