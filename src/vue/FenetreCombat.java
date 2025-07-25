package vue;

import creature.AbstractCreature;
import creature.Araigne;
import creature.Dragon;
import creature.Minotaure;
import equipements.Arme;
import equipements.Armure;
import equipements.Casque;
import joueur.Joueur;
import modele.GestionnaireCombat;
import observer.MonObserver;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FenetreCombat extends JFrame implements MonObserver {

    private Joueur joueur;
    private AbstractCreature creature;
    private GestionnaireCombat gestionCombat;

    private JTextArea zoneTexte;
    private JScrollPane scrollPane;
    private JPanel panneauPrincipal;


    public FenetreCombat( Joueur joueur, AbstractCreature creature, GestionnaireCombat gestionnaireCombat) {
        this.joueur = joueur;
        this.creature = creature;
        this.gestionCombat = gestionnaireCombat;

        configurerFrame();
        configurerHero();
        configurerMessage();
        configurerCreature();

        requestFocus();
        setVisible(true);
    }

    private void configurerFrame(){
        panneauPrincipal = (JPanel) getContentPane();
        setLocation(600,300);
        setSize(800,400);
        setLayout(new GridLayout(0,3));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestionCombat.combatTermine();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void configurerHero() {
        BufferedImage image = null;
        try {
            image = ImageIO.read(new File("images/hero.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        panneauPrincipal.add(new JLabel(new ImageIcon(image)));
    }

    private void configurerMessage(){
        zoneTexte = new JTextArea(16, 20);
        zoneTexte.setEditable(false);
        scrollPane = new JScrollPane(zoneTexte);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panneauPrincipal.add(scrollPane);
    }

    private void configurerCreature() {
        try {
            if (creature instanceof Araigne) {
                BufferedImage image = ImageIO.read(new File("images/spider.png"));
                panneauPrincipal.add(new JLabel(new ImageIcon(image)));

            } else if (creature instanceof Minotaure) {
                BufferedImage image = ImageIO.read(new File("images/minotaur.png"));
                panneauPrincipal.add(new JLabel(new ImageIcon(image)));

            } else if (creature instanceof Dragon) {
                BufferedImage image = ImageIO.read(new File("images/dragon.png"));
                panneauPrincipal.add(new JLabel(new ImageIcon(image)));
            }
        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image de la créature : " + e.getMessage());
            panneauPrincipal.add(new JLabel("Image créature non trouvée"));
        }
    }

    @Override
    public void avertir() {
        zoneTexte.append(gestionCombat.getMsg());
    }
}
