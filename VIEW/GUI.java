package VIEW;

import CONTROLLER.Controller;
import MODEL.Cards.Ariadne;
import MODEL.Cards.Card;
import MODEL.Cards.Minotaur;
import MODEL.Players.Player;
import javax.swing.*;
import java.awt.*;
import java.util.Objects;


/**
 * Represents the graphical user interface (GUI) for the Lost Cities game.
 * Provides visual elements such as the game map, player sections, and menus for starting, saving, and loading a game.
 */
public class GUI extends JFrame{
    private Controller controller;
    JMenuBar menuBar = new JMenuBar();
    JLayeredPane mapPane = new JLayeredPane();
    JLayeredPane[] playerPane = new JLayeredPane[2];
    private JLabel deckLabel;
    private JLabel checkpointLabel;
    private JLabel turnLabel;
    private boolean flag;
    JLabel pointsLabel = new JLabel();
    JLabel statuesLabel = new JLabel();
    JLabel nameAndPawnsLabel = new JLabel();
    JButton wallPaintingsButton = new JButton();
    JButton[] cardButton = new JButton[8];
    private Card[][] LastCard = new Card[2][4];


    /**
     * Initializes the GUI for the Lost Cities game.
     *
     * Preconditions:
     * - A valid controller instance must be passed.
     *
     * Postconditions:
     * - The GUI is initialized and displayed with all necessary components.
     */
    public GUI(Controller controller){
        this.controller = controller;
        this.flag = false;
        initializeGUI();
    }


    /**
     * Sets up the main window and components of the GUI.
     *
     * Preconditions:
     * - The JFrame must be initialized and ready to display.
     * - A controller instance must be available.
     *
     * Postconditions:
     * - The GUI layout is set up with the main window, player sections, and game map.
     * - The JFrame is displayed.
     */
    private void initializeGUI(){
        //Set up the main window
        setTitle("Lost Cities");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(new BorderLayout());

        //Add menu bar
        setJMenuBar(createMenuBar());

        //Add the three main sections
        playerPane[0] = createPlayerSection("Παίκτης Α", Color.RED);
        add(playerPane[0], BorderLayout.NORTH);
        add(createGameMapSection(), BorderLayout.CENTER);
        playerPane[1] = createPlayerSection("Παίκτης Β", Color.GREEN);
        add(playerPane[1], BorderLayout.SOUTH);

        setVisible(true);
    }


    /**
     * Creates the menu bar for the game, including options for starting, saving, loading, and exiting the game.
     *
     * Preconditions:
     * - The JFrame must be initialized.
     *
     * Postconditions:
     * - A JMenuBar is created with the necessary game options.
     * - The menu is added to the JFrame.
     *
     * @return JMenuBar the created menu bar with game options.
     */
    private JMenuBar createMenuBar(){
        JMenu menu = new JMenu("Game Menu");

        JMenuItem newGame = new JMenuItem("New Game");
        newGame.addActionListener(e -> {
            controller.startNewGame();
            JOptionPane.showMessageDialog(this, "New Game Started!");
        });

        JMenuItem saveGame = new JMenuItem("Save Game");
        saveGame.addActionListener(e -> controller.saveGame());

        JMenuItem continueGame = new JMenuItem("Continue Saved Game");
        continueGame.addActionListener(e -> {
            controller.loadSavedGame();
            JOptionPane.showMessageDialog(this, "Game Loaded Successfully!");
        });

        JMenuItem exitGame = new JMenuItem("Exit Game");
        exitGame.addActionListener(e -> System.exit(0));

        menu.add(newGame);
        menu.add(saveGame);
        menu.add(continueGame);
        menu.add(exitGame);
        menuBar.add(menu);

        return menuBar;
    }


    public JLayeredPane createPlayerSection(String playerName, Color color){
        JLayeredPane newPane = new JLayeredPane();
        newPane.setPreferredSize(new Dimension(1480, 245));
        newPane.setBorder(BorderFactory.createLineBorder(color, 4));


        //Player's Hand
        int handX = 30, handY = 28;
        Player player = getPlayerByName(playerName);
        for(int i = 0; i < player.getHandCards().size(); i++){
            if(cardButton[i] != null){
                newPane.remove(cardButton[i]);
            }
            Card card = player.getHandCards().get(i);
            cardButton[i] = new JButton();
            cardButton[i].setBounds(handX + i * 135, handY, 120, 160);
            int finalI = i;
            cardButton[i].addActionListener(e -> playOrDraw(player, card, finalI));
            newPane.add(cardButton[i], Integer.valueOf(1));
            if(Objects.equals(card.getName(), "Κνωσσός" + " Μίτου Αριάδνης") && Objects.equals(card.getValue(), 0) && card instanceof Ariadne){
                ImageIcon knossosAri = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossosAri.jpg")));
                Image knossosAri2 = knossosAri.getImage();
                knossosAri2 = knossosAri2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosAri = new ImageIcon(knossosAri2);
                cardButton[i].setIcon(knossosAri);
            }
            if(Objects.equals(card.getName(), "Κνωσσός" + " Μινώταυρου") && Objects.equals(card.getValue(), 0) && card instanceof Minotaur){
                ImageIcon knossosMin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/KnossosMin.jpg")));
                Image knossosMin2 = knossosMin.getImage();
                knossosMin2 = knossosMin2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosMin = new ImageIcon(knossosMin2);
                cardButton[i].setIcon(knossosMin);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 1)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos1.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 2)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos2.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 3)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos3.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 4)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos4.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 5)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos5.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 6)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/Knossos6.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 7)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos7.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 8)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos8.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 9)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos9.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Κνωσσός") && Objects.equals(card.getValue(), 10)){
                ImageIcon knossosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/knossos10.jpg")));
                Image knossosC2 = knossosC.getImage();
                knossosC2 = knossosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                knossosC = new ImageIcon(knossosC2);
                cardButton[i].setIcon(knossosC);
            }
            if(Objects.equals(card.getName(), "Μάλια" + " Μίτου Αριάδνης") && Objects.equals(card.getValue(), 0) && card instanceof Ariadne){
                ImageIcon maliaAri = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/maliaAri.jpg")));
                Image maliaAri2 = maliaAri.getImage();
                maliaAri2 = maliaAri2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaAri = new ImageIcon(maliaAri2);
                cardButton[i].setIcon(maliaAri);
            }
            if(Objects.equals(card.getName(), "Μάλια" + " Μινώταυρου") && Objects.equals(card.getValue(), 0) && card instanceof Minotaur){
                ImageIcon maliaMin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/maliaMin.jpg")));
                Image maliaMin2 = maliaMin.getImage();
                maliaMin2 = maliaMin2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaMin = new ImageIcon(maliaMin2);
                cardButton[i].setIcon(maliaMin);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 1)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia1.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 2)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia2.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 3)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia3.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 4)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia4.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 5)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia5.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 6)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia6.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 7)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia7.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 8)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia8.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 9)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia9.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Μάλια") && Objects.equals(card.getValue(), 10)){
                ImageIcon maliaC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/malia10.jpg")));
                Image maliaC2 = maliaC.getImage();
                maliaC2 = maliaC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                maliaC = new ImageIcon(maliaC2);
                cardButton[i].setIcon(maliaC);
            }
            if(Objects.equals(card.getName(), "Φαιστός" + " Μίτου Αριάδνης") && Objects.equals(card.getValue(), 0) && card instanceof Ariadne){
                ImageIcon phaistosAri = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistosAri.jpg")));
                Image phaistosAri2 = phaistosAri.getImage();
                phaistosAri2 = phaistosAri2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosAri = new ImageIcon(phaistosAri2);
                cardButton[i].setIcon(phaistosAri);
            }
            if(Objects.equals(card.getName(), "Φαιστός" + " Μινώταυρου") && Objects.equals(card.getValue(), 0) && card instanceof Minotaur){
                ImageIcon phaistosMin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistosMin.jpg")));
                Image phaistosMin2 = phaistosMin.getImage();
                phaistosMin2 = phaistosMin2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosMin = new ImageIcon(phaistosMin2);
                cardButton[i].setIcon(phaistosMin);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 1)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos1.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 2)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos2.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 3)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos3.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 4)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos4.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 5)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos5.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 6)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos6.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 7)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos7.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 8)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos8.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 9)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos9.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Φαιστός") && Objects.equals(card.getValue(), 10)){
                ImageIcon phaistosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/phaistos10.jpg")));
                Image phaistosC2 = phaistosC.getImage();
                phaistosC2 = phaistosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                phaistosC = new ImageIcon(phaistosC2);
                cardButton[i].setIcon(phaistosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος" + " Μίτου Αριάδνης") && Objects.equals(card.getValue(), 0) && card instanceof Ariadne){
                ImageIcon zakrosAri = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakrosAri.jpg")));
                Image zakrosAri2 = zakrosAri.getImage();
                zakrosAri2 = zakrosAri2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosAri = new ImageIcon(zakrosAri2);
                cardButton[i].setIcon(zakrosAri);
            }
            if(Objects.equals(card.getName(), "Ζάκρος" + " Μινώταυρου") && Objects.equals(card.getValue(), 0) && card instanceof Minotaur){
                ImageIcon zakrosMin = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakrosMin.jpg")));
                Image zakrosMin2 = zakrosMin.getImage();
                zakrosMin2 = zakrosMin2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosMin = new ImageIcon(zakrosMin2);
                cardButton[i].setIcon(zakrosMin);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 1)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros1.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 2)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros2.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 3)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros3.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 4)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros4.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 5)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros5.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 6)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros6.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 7)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros7.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 8)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros8.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 9)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros9.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }
            if(Objects.equals(card.getName(), "Ζάκρος") && Objects.equals(card.getValue(), 10)){
                ImageIcon zakrosC = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/zakros10.jpg")));
                Image zakrosC2 = zakrosC.getImage();
                zakrosC2 = zakrosC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                zakrosC = new ImageIcon(zakrosC2);
                cardButton[i].setIcon(zakrosC);
            }

            JLabel findingLabel = new JLabel(new ImageIcon());
            findingLabel.setBounds(handX - 1 + i * 135, handY - 1, 123, 163);
            findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
        }


        //Player Name and Pawns
        nameAndPawnsLabel = new JLabel(playerName + " - Διαθέσιμα Πιόνια: 3 Αρχαιολόγοι και 1 Θησέας");
        nameAndPawnsLabel.setBounds(handX, handY - 30, 400, 30);
        newPane.add(nameAndPawnsLabel, JLayeredPane.DEFAULT_LAYER);


        //Rare Findings
        handX = 550;
        handY = 25;
        int findingsX = handX + 8 * 70 + 30, findingsY = handY;
        int i = 0;

        if(Objects.equals(controller.getCurrentPlayer().getName(), "Παίκτης Α")){
            while(i >= 0 && i < 4){
                if(i == 0){
                    JLabel lastCardK = new JLabel("Κνωσσός", SwingConstants.CENTER);
                    lastCardK.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardK.setBorder(BorderFactory.createLineBorder(Color.RED));
                    newPane.add(lastCardK, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[0][i] != null){
                        String ImageName = "/project_assets/images/cards/knossos" + LastCard[0][0].getValue() + ".jpg";
                        ImageIcon knossosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image knossosLC2 = knossosLC.getImage();
                        knossosLC2 = knossosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        knossosLC = new ImageIcon(knossosLC2);
                        lastCardK.setIcon(knossosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 37, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Κνωσσός", findingsX + 39, findingsY + 168);
                }else if(i == 1){
                    JLabel lastCardM = new JLabel("Μάλια", SwingConstants.CENTER);
                    lastCardM.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardM.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    newPane.add(lastCardM, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[0][i] != null){
                        String ImageName = "/project_assets/images/cards/malia" + LastCard[0][1].getValue() + ".jpg";
                        ImageIcon maliaLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image maliaLC2 = maliaLC.getImage();
                        maliaLC2 = maliaLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        maliaLC = new ImageIcon(maliaLC2);
                        lastCardM.setIcon(maliaLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 172, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Μάλια", findingsX + 174, findingsY + 168);
                }else if(i == 2){
                    JLabel lastCardF = new JLabel("Φαιστός", SwingConstants.CENTER);
                    lastCardF.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    newPane.add(lastCardF, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[0][i] != null){
                        String ImageName = "/project_assets/images/cards/phaistos" + LastCard[0][2].getValue() + ".jpg";
                        ImageIcon phaistosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image phaistosLC2 = phaistosLC.getImage();
                        phaistosLC2 = phaistosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        phaistosLC = new ImageIcon(phaistosLC2);
                        lastCardF.setIcon(phaistosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 309, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Φαιστός", findingsX + 311, findingsY + 168);
                }else if(i == 3){
                    JLabel lastCardZ = new JLabel("Ζάκρος", SwingConstants.CENTER);
                    lastCardZ.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardZ.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    newPane.add(lastCardZ, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[0][i] != null){
                        String ImageName = "/project_assets/images/cards/zakros" + LastCard[0][3].getValue() + ".jpg";
                        ImageIcon zakrosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image zakrosLC2 = zakrosLC.getImage();
                        zakrosLC2 = zakrosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        zakrosLC = new ImageIcon(zakrosLC2);
                        lastCardZ.setIcon(zakrosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 440, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Ζάκρος", findingsX + 442, findingsY + 168);
                }
                i++;
            }
        }else if(Objects.equals(controller.getCurrentPlayer().getName(), "Παίκτης Β")){
            while(i >= 0 && i < 4){
                if(i == 0){
                    JLabel lastCardK = new JLabel("Κνωσσός", SwingConstants.CENTER);
                    lastCardK.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardK.setBorder(BorderFactory.createLineBorder(Color.RED));
                    newPane.add(lastCardK, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[1][i] != null){
                        String ImageName = "/project_assets/images/cards/knossos" + LastCard[1][0].getValue() + ".jpg";
                        ImageIcon knossosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image knossosLC2 = knossosLC.getImage();
                        knossosLC2 = knossosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        knossosLC = new ImageIcon(knossosLC2);
                        lastCardK.setIcon(knossosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 37, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.RED));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Κνωσσός", findingsX + 39, findingsY + 168);
                }else if(i == 1){
                    JLabel lastCardM = new JLabel("Μάλια", SwingConstants.CENTER);
                    lastCardM.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardM.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    newPane.add(lastCardM, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[1][i] != null){
                        String ImageName = "/project_assets/images/cards/malia" + LastCard[1][1].getValue() + ".jpg";
                        ImageIcon maliaLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image maliaLC2 = maliaLC.getImage();
                        maliaLC2 = maliaLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        maliaLC = new ImageIcon(maliaLC2);
                        lastCardM.setIcon(maliaLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 172, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.YELLOW));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Μάλια", findingsX + 174, findingsY + 168);
                }else if(i == 2){
                    JLabel lastCardF = new JLabel("Φαιστός", SwingConstants.CENTER);
                    lastCardF.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardF.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    newPane.add(lastCardF, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[1][i] != null){
                        String ImageName = "/project_assets/images/cards/phaistos" + LastCard[1][2].getValue() + ".jpg";
                        ImageIcon phaistosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image phaistosLC2 = phaistosLC.getImage();
                        phaistosLC2 = phaistosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        phaistosLC = new ImageIcon(phaistosLC2);
                        lastCardF.setIcon(phaistosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 309, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Φαιστός", findingsX + 311, findingsY + 168);
                }else if(i == 3){
                    JLabel lastCardZ = new JLabel("Ζάκρος", SwingConstants.CENTER);
                    lastCardZ.setBounds(findingsX + i * 135, findingsY, 125, 165);
                    lastCardZ.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    newPane.add(lastCardZ, JLayeredPane.DEFAULT_LAYER);
                    if(LastCard[1][i] != null){
                        String ImageName = "/project_assets/images/cards/zakros" + LastCard[1][3].getValue() + ".jpg";
                        ImageIcon zakrosLC = new ImageIcon(Objects.requireNonNull(getClass().getResource(ImageName)));
                        Image zakrosLC2 = zakrosLC.getImage();
                        zakrosLC2 = zakrosLC2.getScaledInstance(120, 160, Image.SCALE_SMOOTH);
                        zakrosLC = new ImageIcon(zakrosLC2);
                        lastCardZ.setIcon(zakrosLC);
                    }

                    JLabel findingLabel = new JLabel(new ImageIcon());
                    findingLabel.setBounds(findingsX + 440, findingsY + 166, 50, 50);
                    findingLabel.setBorder(BorderFactory.createLineBorder(Color.BLUE));
                    newPane.add(findingLabel, JLayeredPane.DEFAULT_LAYER);
                    addFindingButton(newPane, "Ζάκρος", findingsX + 442, findingsY + 168);
                }
                i++;
            }
        }

        findingsX = 1400;

        //Player's Points
        pointsLabel = new JLabel("Το Σκορ Μου: " + controller.getScore());
        pointsLabel.setBounds(findingsX + 4 * 70 + 20, findingsY + 20, 250, 30);
        newPane.add(pointsLabel, JLayeredPane.PALETTE_LAYER);

        //Wall Paintings Button
        wallPaintingsButton = new JButton("Οι Τοιχογραφίες μου");
        wallPaintingsButton.setBounds(findingsX + 4 * 70 + 20, findingsY + 75, 200, 30);
        wallPaintingsButton.addActionListener(e -> showWallPaintingsWindow());
        newPane.add(wallPaintingsButton, JLayeredPane.DEFAULT_LAYER);

        //Statues
        statuesLabel = new JLabel("Αγαλματάκια: " + player.statueCount());
        statuesLabel.setBounds(findingsX + 4 * 70 + 20, findingsY + 145, 250, 30);
        newPane.add(statuesLabel, JLayeredPane.PALETTE_LAYER);

        addStatueButton(newPane, "Φίδια", findingsX + 4 * 70 + 130, findingsY + 130);

        return newPane;
    }

    /**
     * Prompts the player to decide whether to play or discard a card.
     * If the player decides to play the card, it updates the game state by recording the card
     * and drawing a new card for the player. If the player chooses to discard the card,
     * it updates the game state accordingly and draws a new card.
     *
     * @param player The player who is making the decision to play or discard a card.
     * @param card The card that the player is deciding to play or discard.
     * @param i The index representing the position of the card in the player's hand.
     */
    private void playOrDraw(Player player, Card card, int i){
        int response = JOptionPane.showOptionDialog(
                this,
                "Θες αυτή την κάρτα να την παίξεις ή να την κάψεις?",
                "Card Action",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                new String[]{"Παίξε", "Κάψε"},
                "Παίξε"
        );

        if(response == JOptionPane.YES_OPTION){
            JOptionPane.showMessageDialog(this, "Ο " + player.getName() + " έπαιξε την κάρτα " + card.getName() + " με τιμή " + card.getValue());
            if(Objects.equals(player.getName(), "Παίκτης Α")){
                if (Objects.equals(card.getName(), "Κνωσσός")) {
                    LastCard[0][0] = card;
                }
                if (Objects.equals(card.getName(), "Μάλια")) {
                    LastCard[0][1] = card;
                }
                if (Objects.equals(card.getName(), "Φαιστός")) {
                    LastCard[0][2] = card;
                }
                if (Objects.equals(card.getName(), "Ζάκρος")) {
                    LastCard[0][3] = card;
                }
            }
            if(Objects.equals(player.getName(), "Παίκτης Β")){
                if (Objects.equals(card.getName(), "Κνωσσός")) {
                    LastCard[1][0] = card;
                }
                if (Objects.equals(card.getName(), "Μάλια")) {
                    LastCard[1][1] = card;
                }
                if (Objects.equals(card.getName(), "Φαιστός")) {
                    LastCard[1][2] = card;
                }
                if (Objects.equals(card.getName(), "Ζάκρος")) {
                    LastCard[1][3] = card;
                }
            }

            Card drawnCard = controller.drawCardPlayer(player);
            updatePlayerHand(player, card, drawnCard, i);

            if(drawnCard != null){
                JOptionPane.showMessageDialog(this, "Ο " + player.getName() + " τράβηξε κάρτα " + drawnCard.getName() + " με τιμή " + drawnCard.getValue());
            }else{
                JOptionPane.showMessageDialog(this, "Η στοίβα καρτών τελείωσε!");
            }
        }else if(response == JOptionPane.NO_OPTION){

            controller.discardCard(player, card, i);
            JOptionPane.showMessageDialog(this, "Ο " + player.getName() + " έκαψε την κάρτα " + card.getName() + " με τιμή " + card.getValue());
            Card drawnCard = controller.drawCardPlayer(player);
            updatePlayerHand(player, card, drawnCard, i);

            if(drawnCard != null){
                JOptionPane.showMessageDialog(this, "Ο " + player.getName() + " τράβηξε κάρτα " + drawnCard.getName() + " με τιμή " + drawnCard.getValue());
            }else{
                JOptionPane.showMessageDialog(this, "Η στοίβα καρτών τελείωσε!");
            }
        }

        updateTurn();
        updateDeck();
    }


    private JLayeredPane createGameMapSection(){
        mapPane = new JLayeredPane();
        mapPane.setPreferredSize(new Dimension(1480, 245));
        mapPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));

        ImageIcon background = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/background.jpg")));
        Image backgroundImage = background.getImage().getScaledInstance(1930, 478, Image.SCALE_SMOOTH);
        background = new ImageIcon(backgroundImage);

        JLabel backgroundLabel = new JLabel(background);
        backgroundLabel.setBounds(0, 0, 1930, 478);

        mapPane.add(backgroundLabel, Integer.valueOf(-1));


        //Deck of Cards
        JButton deckButton = new JButton();
        deckButton.setBounds(53, 180, 120, 180);
        mapPane.add(deckButton, JLayeredPane.POPUP_LAYER);
        ImageIcon cardBehind = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/cards/backCard.jpg")));
        Image cardBehind2 = cardBehind.getImage();
        cardBehind2 = cardBehind2.getScaledInstance(125, 180, Image.SCALE_SMOOTH);
        cardBehind = new ImageIcon(cardBehind2);
        deckButton.setIcon(cardBehind);

        int checkpoints = controller.getPlayerCheckpoints(controller.getCurrentPlayer());


        deckLabel = new JLabel("Deck: " + controller.getCards());
        deckLabel.setBounds(50, 380, 300, 30);
        mapPane.add(deckLabel, JLayeredPane.PALETTE_LAYER);
        checkpointLabel = new JLabel("Checkpoints: " + checkpoints);
        checkpointLabel.setBounds(50, 400, 300, 30);
        mapPane.add(checkpointLabel, JLayeredPane.PALETTE_LAYER);
        turnLabel = new JLabel("Turn: " + controller.getCurrentPlayer().getName());
        turnLabel.setBounds(50, 420, 300, 30);
        mapPane.add(turnLabel, JLayeredPane.PALETTE_LAYER);

        JPanel gameInfo = new JPanel();
        gameInfo.setBackground(Color.LIGHT_GRAY);
        gameInfo.setBounds(30,  380, 180, 70);
        gameInfo.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        mapPane.add(gameInfo, JLayeredPane.DEFAULT_LAYER);


        //Paths and Tiles
        int startY = 42;
        for(int i = 0; i < 4; i++){
            int pathY = startY + i * 110;
            if(i == 0){
                for (int j = 0; j < 8; j++){
                    JLabel knossosTile = new JLabel();
                    knossosTile.setBounds(490 + j * 150, pathY, 130, 85);
                    knossosTile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mapPane.add(knossosTile, JLayeredPane.POPUP_LAYER);
                    if(j == 1 || j == 3 || j == 5 || j == 7 ){
                        ImageIcon knossos = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/knossos2.jpg")));
                        Image knossos2 = knossos.getImage();
                        knossos2 = knossos2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        knossos = new ImageIcon(knossos2);
                        knossosTile.setIcon(knossos);
                    }else{
                        ImageIcon knossos = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/knossos.jpg")));
                        Image knossos2 = knossos.getImage();
                        knossos2 = knossos2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        knossos = new ImageIcon(knossos2);
                        knossosTile.setIcon(knossos);
                    }
                }
                JLabel knossosPalace = new JLabel();
                knossosPalace.setBounds(1690, pathY - 10, 180, 100);
                knossosPalace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPane.add(knossosPalace, JLayeredPane.POPUP_LAYER);
                ImageIcon knossosP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/knossosPalace.jpg")));
                Image knossosP2 = knossosP.getImage();
                knossosP2 = knossosP2.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                knossosP = new ImageIcon(knossosP2);
                knossosPalace.setIcon(knossosP);

            }else if(i == 1){
                for (int j = 0; j < 8; j++){
                    JLabel maliaTile = new JLabel();
                    maliaTile.setBounds(490 + j * 150, pathY, 130, 85);
                    maliaTile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mapPane.add(maliaTile, JLayeredPane.POPUP_LAYER);
                    if(j == 1 || j == 3 || j == 5 || j == 7 ){
                        ImageIcon malia = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/malia2.jpg")));
                        Image malia2 = malia.getImage();
                        malia2 = malia2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        malia = new ImageIcon(malia2);
                        maliaTile.setIcon(malia);
                    }else{
                        ImageIcon malia = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/malia.jpg")));
                        Image malia2 = malia.getImage();
                        malia2 = malia2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        malia = new ImageIcon(malia2);
                        maliaTile.setIcon(malia);
                    }
                }
                JLabel maliaPalace = new JLabel();
                maliaPalace.setBounds(1690, pathY - 10, 180, 100);
                maliaPalace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPane.add(maliaPalace, JLayeredPane.POPUP_LAYER);
                ImageIcon maliaP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/maliaPalace.jpg")));
                Image maliaP2 = maliaP.getImage();
                maliaP2 = maliaP2.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                maliaP = new ImageIcon(maliaP2);
                maliaPalace.setIcon(maliaP);

            }else if(i == 2){
                for (int j = 0; j < 8; j++){
                    JLabel phaistosTile = new JLabel();
                    phaistosTile.setBounds(490 + j * 150, pathY, 130, 85);
                    phaistosTile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mapPane.add(phaistosTile, JLayeredPane.POPUP_LAYER);
                    if(j == 1 || j == 3 || j == 5 || j == 7 ){
                        ImageIcon phaistos = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/phaistos2.jpg")));
                        Image phaistos2 = phaistos.getImage();
                        phaistos2 = phaistos2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        phaistos = new ImageIcon(phaistos2);
                        phaistosTile.setIcon(phaistos);
                    }else{
                        ImageIcon phaistos = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/phaistos.jpg")));
                        Image phaistos2 = phaistos.getImage();
                        phaistos2 = phaistos2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        phaistos = new ImageIcon(phaistos2);
                        phaistosTile.setIcon(phaistos);
                    }
                }
                JLabel phaistosPalace = new JLabel();
                phaistosPalace.setBounds(1690, pathY - 10, 180, 100);
                phaistosPalace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPane.add(phaistosPalace, JLayeredPane.POPUP_LAYER);
                ImageIcon phaistosP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/phaistosPalace.jpg")));
                Image phaistosP2 = phaistosP.getImage();
                phaistosP2 = phaistosP2.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                phaistosP = new ImageIcon(phaistosP2);
                phaistosPalace.setIcon(phaistosP);

            }else if(i == 3){
                for (int j = 0; j < 8; j++){
                    JLabel zakrosTile = new JLabel();
                    zakrosTile.setBounds(490 + j * 150, pathY, 130, 85);
                    zakrosTile.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    mapPane.add(zakrosTile, JLayeredPane.POPUP_LAYER);
                    if(j == 1 || j == 3 || j == 5 || j == 7 ){
                        ImageIcon zakros = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/zakros2.jpg")));
                        Image zakros2 = zakros.getImage();
                        zakros2 = zakros2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        zakros = new ImageIcon(zakros2);
                        zakrosTile.setIcon(zakros);
                    }else{
                        ImageIcon zakros = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/zakros.jpg")));
                        Image zakros2 = zakros.getImage();
                        zakros2 = zakros2.getScaledInstance(130, 85, Image.SCALE_SMOOTH);
                        zakros = new ImageIcon(zakros2);
                        zakrosTile.setIcon(zakros);
                    }
                }
                JLabel zakrosPalace = new JLabel();
                zakrosPalace.setBounds(1690, pathY - 10, 180, 100);
                zakrosPalace.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                mapPane.add(zakrosPalace, JLayeredPane.POPUP_LAYER);
                ImageIcon zakrosP = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/paths/zakrosPalace.jpg")));
                Image zakrosP2 = zakrosP.getImage();
                zakrosP2 = zakrosP2.getScaledInstance(180, 100, Image.SCALE_SMOOTH);
                zakrosP = new ImageIcon(zakrosP2);
                zakrosPalace.setIcon(zakrosP);
            }
        }

        for(int i = 1; i < 10; i++){
            int pathY = 10;
            JLabel tilepoint = new JLabel(controller.calculatePointsForStep(i) + " points");
            if(i == 7){
                JLabel checkpoint = new JLabel("Checkpoint!!!");
                checkpoint.setBounds(370 + i * 150, pathY + 18, 200, 15);
                mapPane.add(checkpoint, JLayeredPane.POPUP_LAYER);
            }
            tilepoint.setBounds(370 + i * 150, pathY + 6, 200, 15);
            mapPane.add(tilepoint, JLayeredPane.POPUP_LAYER);
        }

        return mapPane;
    }

    /**
     * Displays a window showing a collection of wall paintings.
     * The window contains buttons representing each painting, and when clicked, a dialog is shown with the painting's image and description.
     */
    private void showWallPaintingsWindow() {
        JFrame paintingsFrame = new JFrame("Τοιχογραφίες");
        paintingsFrame.setSize(900, 600);
        paintingsFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel paintingsPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);

        String[] imageName = {"Φωτογράφισες την Τοιχογραφία: Οι γαλάζεις κυρίες!!!", "Φωτογράφισες την Τοιχογραφία: Τα ταυροκαθάψια!!!", "Φωτογράφισες την Τοιχογραφία: Τα δελφίνια!!!", "Φωτογράφισες την Τοιχογραφία: Ο πρίγκιπας με τα κρίνα!!!", "Φωτογράφισες την Τοιχογραφία: Πομπή νέων!!!", "Φωτογράφισες την Τοιχογραφία: Η παριζιάνα!!!"};
        String[] paintingDescriptions = new String[6];
        String[] paintingImages = new String[6];

        for(int i = 0; i < imageName.length; i++){
            switch(imageName[i]){
                case "Φωτογράφισες την Τοιχογραφία: Οι γαλάζεις κυρίες!!!":
                    paintingDescriptions[i] = "Όμορφες Μινωίτισσες που κουβεντιάζουν. Έχουν ωραία φορέματα, σύμφωνα με τη μόδα της εποχής, όμορφα χτενισμένα μαλλιά και πολύτιμα κοσμήματα.";
                    paintingImages[i] = "/project_assets/images/findings/fresco1_20.jpg";
                    break;
                case "Φωτογράφισες την Τοιχογραφία: Τα ταυροκαθάψια!!!":
                    paintingDescriptions[i] = "Τα ταυροκαθάψια ήταν ένα αγώνισμα που συνηθιζόταν πολύ στα μινωικά χρόνια. Περιλάμβανε το πιάσιμο του ταύρου από τα κέρατα, το επικίνδυνο άλμα στη ράχη του ζώου και τέλος το πήδημα στο έδαφος πίσω του.";
                    paintingImages[i] = "/project_assets/images/findings/fresco2_20.jpg";
                    break;
                case "Φωτογράφισες την Τοιχογραφία: Τα δελφίνια!!!":
                    paintingDescriptions[i] = "Η τοιχογραφία αυτή προέρχεται από τo μέγαρο της βασίλισσας. Δελφίνια κολυμπούν ανάμεσα σε ψάρια, μέσα στα κύματα.";
                    paintingImages[i] = "/project_assets/images/findings/fresco3_15.jpg";
                    break;
                case "Φωτογράφισες την Τοιχογραφία: Ο πρίγκιπας με τα κρίνα!!!":
                    paintingDescriptions[i] = "Εικονίζεται επιβλητική ανδρική μορφή, που βαδίζει προς τα αριστερά σε απροσδιόριστο ερυθρό φόντο. Φοράει το τυπικό μινωικό περίζωμα με φαρδιά ζώνη, περιδέραιο στο λαιμό και πλούσιο κάλυμμα κεφαλής διακοσμημένο με κρίνα και φτερά παγωνιού. Η στάση των χεριών του δείχνει ότι ίσως έσερνε με το αριστερό του χέρι ένα ζώο ή κάποιο μυθικό τέρας, γρύπα ή σφίγγα. Ο νέος ονομάσθηκε από τους ερευνητές «πρίγκηπας», γιατί θεωρήθηκε ότι αποδίδει το βασιλιά-ιερέα, που ζούσε στο ανάκτορο της Κνωσού.!!!";
                    paintingImages[i] = "/project_assets/images/findings/fresco4_20.jpg";
                    break;
                case "Φωτογράφισες την Τοιχογραφία: Πομπή νέων!!!":
                    paintingDescriptions[i] = "Νέοι λαμβάνουν μέρος σε θρησκευτική πομπή και φέρουν αγγεία με δώρα για τη θεότητα ή για το βασιλιά. Η τοιχογραφία αυτή κοσμούσε τον λεγόμενο «διάδρομο της πομπής» του ανακτόρου της Κνωσού.";
                    paintingImages[i] = "/project_assets/images/findings/fresco5_15.jpg";
                    break;
                case "Φωτογράφισες την Τοιχογραφία: Η παριζιάνα!!!":
                    paintingDescriptions[i] = "Εικονίζεται μια γυναίκα αριστοκρατικής καταγωγής σε θέση προφίλ.  Ονομάστηκε «Παριζιάνα» από τον Άρθουρ Έβανς, γιατί το 1903 (έτος που ανακαλύφθηκε) τα μεγάλα μάτια, τα κατσαρά μαλλιά, τα έντονα κόκκινα χείλη και η ανασηκωμένη μύτη θεωρούνταν τα ιδεώδη της γυναικείας ομορφιάς, τα οποία μόνο μια όμορφη γυναίκα από … το Παρίσι μπορούσε να τα ενσαρκώσει.";
                    paintingImages[i] = "/project_assets/images/findings/fresco6_15.jpg";
                    break;
            }
        }

        for (int i = 0; i < imageName.length; i++) {
            JButton button = new JButton();

            try {
                ImageIcon icon = new ImageIcon(getClass().getResource(paintingImages[i]));
                Image scaledImage = icon.getImage().getScaledInstance(300, 280, Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(scaledImage));
            } catch (Exception e) {
                System.err.println("Error loading image for: " + imageName[i]);
            }

            //Add action listener to show painting info on button click
            final int index = i;
            button.addActionListener(e -> {
                JDialog dialog = new JDialog(paintingsFrame, imageName[index], true);
                dialog.setSize(700, 350);
                dialog.setLayout(new BorderLayout(10, 10));
                dialog.setLocationRelativeTo(paintingsFrame);

                // Add image to the left
                JLabel imageLabel = new JLabel();
                try {
                    ImageIcon paintingIcon = new ImageIcon(getClass().getResource(paintingImages[index]));
                    Image scaledImage = paintingIcon.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(scaledImage));
                } catch (Exception e1) {
                    System.err.println("Error loading image for dialog: " + imageName[index]);
                }
                dialog.add(imageLabel, BorderLayout.WEST);

                // Add description to the right
                JTextArea descriptionArea = new JTextArea(paintingDescriptions[index]);
                descriptionArea.setLineWrap(true);
                descriptionArea.setWrapStyleWord(true);
                descriptionArea.setEditable(false);
                descriptionArea.setOpaque(false);
                descriptionArea.setFont(new Font("Arial", Font.PLAIN, 17));

                JScrollPane scrollPane = new JScrollPane(descriptionArea);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
                dialog.add(scrollPane, BorderLayout.CENTER);

                dialog.setVisible(true);
            });

            //Set GridBagConstraints for button placement
            gbc.gridx = i % 3;  // Column
            gbc.gridy = i / 3;  // Row
            gbc.fill = GridBagConstraints.BOTH;
            gbc.weightx = 1.0;
            gbc.weighty = 1.0;
            paintingsPanel.add(button, gbc);
        }

        paintingsFrame.add(paintingsPanel);
        paintingsFrame.setVisible(true);
    }

    /**
     * Updates the checkpoints label to display the current player's checkpoint count.
     * This method retrieves the number of checkpoints from the controller and updates the UI accordingly.
     */
    public void updateCheckpoints(){
        int checkpoints = controller.getPlayerCheckpoints(controller.getCurrentPlayer());
        checkpointLabel.setText("Checkpoints: " + checkpoints);
        checkpointLabel.setBounds(50, 400, 300, 30);
        mapPane.add(checkpointLabel, JLayeredPane.POPUP_LAYER);
        mapPane.revalidate();
        mapPane.repaint();
    }

    /**
     * Updates the turn label to display the current player's name and whose turn it is.
     * The method gets the current player from the controller and updates the turn UI.
     */
    public void updateTurn(){
        String player = controller.updateCurrentPlayerTurn().getName();
        player = controller.getCurrentPlayer().getName();
        turnLabel.setText("Turn: " + player);
        turnLabel.setBounds(50, 420, 300, 30);
        mapPane.add(turnLabel, JLayeredPane.POPUP_LAYER);
        mapPane.revalidate();
        mapPane.repaint();
    }

    /**
     * Updates the deck label to show the number of remaining cards in the deck.
     * This method gets the number of remaining cards from the controller and refreshes the UI.
     */
    public void updateDeck(){
        deckLabel.setText("Deck: " + controller.getCards());
        deckLabel.setBounds(50, 380, 300, 30);
        mapPane.add(deckLabel, JLayeredPane.POPUP_LAYER);
        mapPane.revalidate();
        mapPane.repaint();
    }

    /**
     * Updates the score label for the specified player with the current score.
     * This method retrieves the player's score from the controller and updates the UI accordingly.
     *
     * @param ID The index of the player whose score should be updated.
     */
    public void updateScore(int ID){
        int findingsX = 1400;
        int findingsY = 550;
        pointsLabel.setText("Το σκόρ μου: " + controller.getScore());
        pointsLabel.setBounds(findingsX + 4 * 70 + 20, findingsY + 20, 250, 30);
        playerPane[ID].add(pointsLabel, JLayeredPane.POPUP_LAYER);
        playerPane[ID].revalidate();
        playerPane[ID].repaint();
    }

    /**
     * Updates the statues count label for the specified player with the current number of statues.
     * This method retrieves the number of statues the player has and updates the UI accordingly.
     *
     * @param ID The index of the player whose statues count should be updated.
     */
    public void updateStatueCount(int ID){
        int findingsX = 1400;
        int findingsY = 550;
        int statues = getPlayerByName(controller.getCurrentPlayer().getName()).statueCount();
        statuesLabel.setText("Αγαλματάκια: " + statues);
        statuesLabel.setBounds(findingsX + 4 * 70 + 20, findingsY + 145, 250, 30);
        playerPane[ID].add(statuesLabel, JLayeredPane.POPUP_LAYER);
        playerPane[ID].revalidate();
        playerPane[ID].repaint();
    }

    /**
     * Updates the hand of the player by discarding a card and adding a new card.
     * The player's hand is updated, and the corresponding player section in the UI is refreshed.
     *
     * @param player The player whose hand is being updated.
     * @param removeCard The card to be removed from the player's hand (if any).
     * @param addCard The card to be added to the player's hand (if any).
     * @param i The index to indicate the position of the card being discarded in the player's hand.
     */
    private void updatePlayerHand(Player player, Card removeCard, Card addCard, int i){
        if(removeCard != null){
            controller.discardCard(player, removeCard, i);
        }
        if(addCard != null){
            player.getHandCards().add(addCard);
        }

        if(Objects.equals(controller.getCurrentPlayer().getName(), "Παίκτης Α")){
            remove(playerPane[0]);
            playerPane[0] = createPlayerSection("Παίκτης Α", Color.RED);
            add(playerPane[0], BorderLayout.NORTH);
        }else{
            remove(playerPane[1]);
            playerPane[1] = createPlayerSection("Παίκτης Β", Color.GREEN);
            add(playerPane[1], BorderLayout.SOUTH);
        }

    }

    /**
     * Retrieves a player by their name from the list of players.
     * If no player is found with the given name, an exception is thrown.
     *
     * @param playerName The name of the player to retrieve.
     * @return The Player object matching the specified name.
     * @throws IllegalArgumentException if no player with the given name is found.
     */
    private Player getPlayerByName(String playerName){
        return controller.getPlayers().stream()
                .filter(player -> player.getName().equals(playerName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Player not found: " + playerName));
    }

    /**
     * Adds a button for a palace finding on the map, with an icon representing the finding.
     * The button allows the user to open a detailed palace info window when clicked.
     *
     * @param mapPane The pane where the button will be added.
     * @param palaceName The name of the palace for which the button is being created.
     * @param x The x-coordinate for the position of the button.
     * @param y The y-coordinate for the position of the button.
     */
    private void addFindingButton(JLayeredPane mapPane, String palaceName, int x, int y) {
        if(Objects.equals(palaceName, "Κνωσσός")){
            JButton knossosButton = new JButton();
            knossosButton.setBounds(x, y, 45, 45);
            knossosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            knossosButton.addActionListener(e -> openPalaceInfoWindow(palaceName));
            mapPane.add(knossosButton, JLayeredPane.DEFAULT_LAYER);
            ImageIcon knossosF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/findings/ring.jpg")));
            Image knossosF2 = knossosF.getImage();
            knossosF2 = knossosF2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            knossosF = new ImageIcon(knossosF2);
            knossosButton.setIcon(knossosF);
        }else if(Objects.equals(palaceName, "Μάλια")){
            JButton maliaButton = new JButton();
            maliaButton.setBounds(x, y, 45, 45);
            maliaButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            maliaButton.addActionListener(e -> openPalaceInfoWindow(palaceName));
            mapPane.add(maliaButton, JLayeredPane.DEFAULT_LAYER);
            ImageIcon maliaF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/findings/kosmima.jpg")));
            Image maliaF2 = maliaF.getImage();
            maliaF2 = maliaF2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            maliaF = new ImageIcon(maliaF2);
            maliaButton.setIcon(maliaF);
        }else if(Objects.equals(palaceName, "Φαιστός")){
            JButton phaistosButton = new JButton();
            phaistosButton.setBounds(x, y, 45, 45);
            phaistosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            phaistosButton.addActionListener(e -> openPalaceInfoWindow(palaceName));
            mapPane.add(phaistosButton, JLayeredPane.DEFAULT_LAYER);
            ImageIcon phaistosF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/findings/diskos.jpg")));
            Image phaistosF2 = phaistosF.getImage();
            phaistosF2 = phaistosF2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            phaistosF = new ImageIcon(phaistosF2);
            phaistosButton.setIcon(phaistosF);
        }else if(Objects.equals(palaceName, "Ζάκρος")){
            JButton zakrosButton = new JButton();
            zakrosButton.setBounds(x, y, 45, 45);
            zakrosButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            zakrosButton.addActionListener(e -> openPalaceInfoWindow(palaceName));
            mapPane.add(zakrosButton, JLayeredPane.DEFAULT_LAYER);
            ImageIcon zakrosF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/findings/ruto.jpg")));
            Image zakrosF2 = zakrosF.getImage();
            zakrosF2 = zakrosF2.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            zakrosF = new ImageIcon(zakrosF2);
            zakrosButton.setIcon(zakrosF);
        }
    }

    /**
     * Opens a window displaying detailed information about a specific palace,
     * including an image and description of a significant archaeological finding.
     *
     * @param palaceName The name of the palace for which the information window is opened.
     */
    private void openPalaceInfoWindow(String palaceName) {
        String title = "";
        String description = "";
        String imagePath = "";

        switch(palaceName) {
            case "Κνωσσός":
                title = "Ανακάλυψες το Δαχτυλίδι του Μίνωα !!!";
                description = "Το «Δαχτυλίδι του Μίνωα», ένα από τα μεγαλύτερα και σπανιότερα χρυσά σφραγιδικά στον κόσμο, θεωρείται από τα καλύτερα δείγματα της κρητομυκηναϊκής σφραγιδικής. Φέρει σύνθετη θρησκευτική παράσταση, που απεικονίζει μορφές οι οποίες εντάσσονται στην κρητομυκηναϊκή θεματολογία, δεντρολατρία με καθιστή θεά, ουρανό, γη και θάλασσα, με ιερό πλοίο που έχει μορφή ιππόκαμπου.";
                imagePath = "/project_assets/images/findings/ring.jpg";
                break;
            case "Μάλια":
                title = "Ανακάλυψες το Κόσμημα των Μαλίων!!!";
                description = "Το χρυσό κόσμημα των μελισσών που φιλοξενείται στο Αρχαιολογικό Μουσείο Ηρακλείου, είναι διάσημο αρχαιολογικό εύρημα από τον Χρυσόλακκο, τον ταφικό περίβολο της νεκρόπολης των Μαλίων.";
                imagePath = "/project_assets/images/findings/kosmima.jpg";
                break;
            case "Φαιστός":
                title = "Ανακάλυψες το Δίσκο της Φαιστού!!!";
                description = "Ο Δίσκος της Φαιστού είναι ένα αρχαιολογικό εύρημα από τη Μινωική πόλη της Φαιστού στη νότια Κρήτη και χρονολογείται πιθανώς στον 17ο αιώνα π.Χ.. Αποτελεί ένα από τα γνωστότερα μυστήρια της αρχαιολογίας, αφού ο σκοπός της κατασκευής του και το νόημα των όσων αναγράφονται σε αυτόν παραμένουν άγνωστα. Ο δίσκος ανακαλύφθηκε στις 3 Ιουλίου 1908 από τον Ιταλό αρχαιολόγο Λουίτζι Περνιέ (Luigi Pernier) και φυλάσσεται σήμερα στο Αρχαιολογικό Μουσείο Ηρακλείου.";
                imagePath = "/project_assets/images/findings/diskos.jpg";
                break;
            case "Ζάκρος":
                title = "Ανακάλυψες το Ρυτό της Ζάκρου!!!";
                description = "Το αγγείο βρέθηκε στο θησαυροφυλάκιο του ανακτόρου της Ζάκρου μαζί με άλλα μοναδικά στο είδος τους τελετουργικά σκεύη της νεοανακτορικής εποχής και αποτελεί χαρακτηριστικό παράδειγμα της εφευρετικότητας και καλαισθησίας των Μινωιτών τεχνιτών.";
                imagePath = "/project_assets/images/findings/ruto.jpg";
                break;
            default:
                break;
        }

        JFrame palaceInfoFrame = new JFrame(title);
        palaceInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setPreferredSize(new Dimension(700, 200));

        //Add the image
        JLabel imageLabel = new JLabel();
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(imageIcon);
        }
        contentPanel.add(imageLabel, BorderLayout.WEST);

        //Add the description
        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 17));
        descriptionArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(450, 200));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        //Show the custom panel in a JOptionPane dialog
        JOptionPane.showMessageDialog(
                null,
                contentPanel,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    /**
     * Adds a button for a statue finding to the player's pane. The button displays an image of the statue
     * and opens a detailed information window when clicked.
     *
     * @param playerPane The pane where the button will be added.
     * @param finding The name of the statue to be displayed.
     * @param x The x-coordinate for the position of the button.
     * @param y The y-coordinate for the position of the button.
     */
    public void addStatueButton(JLayeredPane playerPane, String finding, int x, int y){
        if(Objects.equals(finding, "Φίδια")){
          JButton fidiaButton = new JButton();
          fidiaButton.setBounds(x, y, 60, 60);
          fidiaButton.setBorder(BorderFactory.createLineBorder(Color.BLACK));
          fidiaButton.addActionListener(e -> openStatueInfoWindow(finding));
          playerPane.add(fidiaButton, JLayeredPane.DEFAULT_LAYER);
          ImageIcon fidiaF = new ImageIcon(Objects.requireNonNull(getClass().getResource("/project_assets/images/findings/snakes.jpg")));
          Image fidiaF2 = fidiaF.getImage();
          fidiaF2 = fidiaF2.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
          fidiaF = new ImageIcon(fidiaF2);
          fidiaButton.setIcon(fidiaF);
        }
    }

    /**
     * Opens a window displaying detailed information about a statue, including its title, description, and image.
     * The content of the window is based on the name of the statue passed to the method.
     *
     * @param name The name of the statue for which the information window is opened.
     */
    public void openStatueInfoWindow(String name){
        String title = "";
        String description = "";
        String imagePath = "";

        if(name.equals("Φίδια")){
            title = "Βρήκες ένα άγαλμα της Θεάς των Φιδιών!!!";
            description = "Ως η θεά με τα φίδια ονομάζεται ο τύπος αγαλματίδιου που βρέθηκε σε ανασκαφές στους Μινωικούς αρχαιολογικούς τόπους που παρουσιάζει γυναίκα να κρατάει φίδια. Τα αγαλματίδα χρονολογούνται στον 16ο αιώνα π.Χ.. Λίγες πληροφορίες έχουμε για την ερμηνεία των αγαλματιδίων. Ο Έβανς συνδέει την θεά των όφεων με την Αιγυπτιακή θεά Ουατζέτ. Είναι προπομπός της κρητικής Ρέας και παρουσιάζει μεγάλη ομοιότητα με την φρυγική Κυβέλη και την εφεσία Αρτέμιδα.";
            imagePath = "/project_assets/images/findings/snakes.jpg";
        }

        JFrame palaceInfoFrame = new JFrame(title);
        palaceInfoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout(10, 10));
        contentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPanel.setPreferredSize(new Dimension(700, 200));

        //Add the image
        JLabel imageLabel = new JLabel();
        if (imagePath != null && !imagePath.isEmpty()) {
            ImageIcon imageIcon = new ImageIcon(Objects.requireNonNull(getClass().getResource(imagePath)));
            Image scaledImage = imageIcon.getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH);
            imageIcon = new ImageIcon(scaledImage);
            imageLabel.setIcon(imageIcon);
        }
        contentPanel.add(imageLabel, BorderLayout.WEST);

        //Add the description
        JTextArea descriptionArea = new JTextArea(description);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setOpaque(false);
        descriptionArea.setFont(new Font("Arial", Font.PLAIN, 17));
        descriptionArea.setForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(descriptionArea);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.setPreferredSize(new Dimension(450, 200));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        //Show the custom panel in a JOptionPane dialog
        JOptionPane.showMessageDialog(
                null,
                contentPanel,
                title,
                JOptionPane.INFORMATION_MESSAGE
        );
    }
}