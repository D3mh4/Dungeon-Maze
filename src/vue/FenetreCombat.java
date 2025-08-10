package vue;

import creature.AbstractCreature;
import creature.Araigne;
import creature.Dragon;
import creature.Minotaure;
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

/**
 * Fenêtre affichant l'interface de combat entre le joueur et une créature.
 * Affiche les images des combattants, les messages de combat et met à jour
 * l'affichage selon l'évolution du duel.
 *
 * @author Ahmed El Moudden
 * @author Marie-Claire Lajeunesse
 * @version ÉTÉ 2025 - Devoir 2
 */

@SuppressWarnings("serial")
public class FenetreCombat extends JFrame implements MonObserver {

    private AbstractCreature creature;
    private GestionnaireCombat gestionCombat;

    private JTextArea zoneTexte;
    private JScrollPane scrollPane;
    private JPanel panneauPrincipal;

    private JLabel heroLabel;
    private JLabel creatureLabel;

    
    /**
     * Constructeur qui initialise la fenêtre de combat avec les créatures à afficher.
     *
     * @param joueur le joueur participant au combat
     * @param creature la créature affrontée
     * @param gestionnaireCombat le gestionnaire qui gère le combat
     */
    public FenetreCombat(Joueur joueur, AbstractCreature creature, GestionnaireCombat gestionnaireCombat) {
        this.creature = creature;
        this.gestionCombat = gestionnaireCombat;

        configurerFrame();
        configurerHero(false);
        configurerMessage();
        configurerCreature(false);

        requestFocus();
        setVisible(true);
    }

    
    /**
     * Configure la fenêtre principale (taille, position, écouteurs).
     */
    private void configurerFrame() {
        panneauPrincipal = (JPanel) getContentPane();
        setLocation(600, 300);
        setSize(800, 400);
        setLayout(new GridLayout(0, 3));

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                gestionCombat.combatTermine();
            }
        });

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);
    }

    
    /**
     * Configure l'affichage du héros.
     *
     * @param estVaincu true si le héros est vaincu, false sinon
     */
    private void configurerHero(boolean estVaincu) {
        try {
            BufferedImage image = ImageIO.read(new File(estVaincu ? "images/hero_vaincu.png" : "images/hero.png"));
            if (heroLabel == null) {
                heroLabel = new JLabel(new ImageIcon(image));
                panneauPrincipal.add(heroLabel);
            } else {
                heroLabel.setIcon(new ImageIcon(image));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
    /**
     * Configure la zone de texte pour afficher les messages de combat.
     */
    private void configurerMessage() {
        try {
            Font policeArsenal = Font.createFont(Font.TRUETYPE_FONT, new File("polices/Arsenal-Regular.ttf")).deriveFont(16f);
            zoneTexte = new JTextArea(16, 20);
            zoneTexte.setFont(policeArsenal);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        zoneTexte.setEditable(false);
        scrollPane = new JScrollPane(zoneTexte);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        panneauPrincipal.add(scrollPane);
    }

    
    /**
     * Configure l'affichage de la créature.
     *
     * @param estVaincu true si la créature est vaincue, false sinon
     */
    private void configurerCreature(boolean estVaincu) {
        try {
            String cheminImage = "";

            if (creature instanceof Araigne) {
                cheminImage = estVaincu ? "images/spider_vaincu.png" : "images/spider.png";
            } else if (creature instanceof Minotaure) {
                cheminImage = estVaincu ? "images/minotaur_vaincu.png" : "images/minotaur.png";
            } else if (creature instanceof Dragon) {
                cheminImage = estVaincu ? "images/dragon_vaincu.png" : "images/dragon.png";
            }

            BufferedImage image = ImageIO.read(new File(cheminImage));
            if (creatureLabel == null) {
                creatureLabel = new JLabel(new ImageIcon(image));
                panneauPrincipal.add(creatureLabel);
            } else {
                creatureLabel.setIcon(new ImageIcon(image));
            }

        } catch (IOException e) {
            System.err.println("Erreur lors du chargement de l'image de la créature : " + e.getMessage());
            if (creatureLabel == null) {
                creatureLabel = new JLabel("Image créature non trouvée");
                panneauPrincipal.add(creatureLabel);
            } else {
                creatureLabel.setText("Image créature non trouvée");
            }
        }
    }

    
    /**
     * Méthode appelée par l'observable pour mettre à jour l'affichage
     * en fonction du message reçu.
     */
    @Override
    public void avertir() {
        String msg = gestionCombat.getMsg();
        zoneTexte.setText(msg);

        if (msg.contains("Joueur vaincu")) {
            configurerHero(true);
        }

        if (msg.contains("Creature vaincu")) {
            configurerCreature(true);
        }
    }
}