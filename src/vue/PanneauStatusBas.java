package vue;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;

import modele.PlanDeJeu;

public class PanneauStatusBas extends JPanel {

	Dimension tailleEcran = Toolkit.getDefaultToolkit().getScreenSize();
	
	 private JTextArea zoneTexte;
	 private JScrollPane scrollPane;
	 
	 PlanDeJeu planDeJeu = PlanDeJeu.getInstance();
	
	public PanneauStatusBas() {
		configurerPanneau();
		configurerContenu();
	}

	private void configurerPanneau() {
		setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
	}
	
	private void configurerContenu() {
		try {
			Font policeArsenal = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Arsenal-Regular.ttf")).deriveFont(16f);
			zoneTexte = new JTextArea();
			zoneTexte.setFont(policeArsenal);
		}catch (IOException | FontFormatException e) {
			e.printStackTrace();
		}
		setLayout(new BorderLayout());
		
		setBorder(new EmptyBorder(15, 15, 15, 15));

	    zoneTexte.setEditable(false);

	    scrollPane = new JScrollPane(zoneTexte);
	    scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

	    add(scrollPane, BorderLayout.CENTER);
		
	}
	
	public void mettreAJoursInfo() {
		zoneTexte.setText(planDeJeu.getConsoleMessage());
	}

	
	
}
