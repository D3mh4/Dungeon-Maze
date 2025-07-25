package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

public class PanneauStatusBas extends JPanel {

	Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	
	 private JTextArea zoneTexte;
	 private JScrollPane scrollPane;
	
	public PanneauStatusBas() {
		configurerPanneau();
		configurerContenu();
	}

	private void configurerPanneau() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
		setBorder(new EmptyBorder(-5, 0, 0, 0));
	}
	
	private void configurerContenu() {
		
		zoneTexte = new JTextArea();
		zoneTexte.setPreferredSize(new Dimension((tailleEcran.width/3)-20,(tailleEcran.height/3)-25));
        zoneTexte.setEditable(false);
        scrollPane = new JScrollPane(zoneTexte);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);
		
	}
	
	public void mettreAJoursInfo() {
		
	}

	
	
}
