package CONTROLLER;

import MODEL.Cards.*;
import MODEL.Findings.*;
import MODEL.Pawns.Archaeologist;
import MODEL.Pawns.Pawn;
import MODEL.Pawns.Theseus;
import MODEL.Players.Player;
import MODEL.Table.Board;
import MODEL.Table.Path;
import MODEL.Table.Position;
import VIEW.GUI;
import java.awt.*;
import java.util.*;
import java.util.List;
import javax.sound.sampled.Clip;
import java.util.concurrent.ScheduledExecutorService;


/**
 * Controller class for managing the game logic and interaction between the model and view.
 *
 * Invariants:
 * - The board is always initialized before any gameplay starts.
 * - Each player has a valid hand of cards during the game.
 * - The game alternates turns between players.
 */
public class Controller {
    private Board board;
    private List<Player> players;
    private int cards = 100;
    private int pawns;
    private int currentPlayerIndex;
    private GUI gui;
    private int score = 0;
    private String[] palaceNames;
    private Timer turnTimer;
    private ScheduledExecutorService timerExecutor;
    private Clip currentMusicClip;


    /**
     * Constructs a Controller instance with the given players.
     *
     * Preconditions:
     * - players must not be null and must contain at least two players.
     *
     * Postconditions:
     * - The game is initialized with a board, players, paths, and cards.
     */
    public Controller(List<Player> players) {
        this.board = new Board();
        this.players = players;
        this.currentPlayerIndex = 0;
        initializeGame();
    }

    /**
     * Initializes the game setup.
     *
     * Postconditions:
     * - Paths, deck, and pawns are set up.
     * - Cards are shuffled and dealt to players.
     * - Random findings are generated.
     * - The first player is assigned randomly.
     */
    private void initializeGame() {
        setupPaths(palaceNames);
        setupDeck();
        setupPawns();
        shuffleDeck();
        dealInitialCards();
        generateRandomFinding("Knossos");
        generateRandomFinding("Malia");
        generateRandomFinding("Phaistos");
        generateRandomFinding("Zakros");
        randomlyAssignFirstPlayer();
        updateView();
        //startTurnTimer();
    }

    /**
     * Sets the GUI view for the controller.
     *
     * Preconditions:
     * - gui must not be null.
     *
     * Postconditions:
     * - The GUI view is linked to the controller.
     */
    public void setView(GUI gui){
        this.gui = gui;
    }

    /**
     * Notifies the view to update its display.
     *
     * Postconditions:
     * - The view is updated if a GUI is set.
     */
    //Notify the view to update
    public void updateView(){
        if(gui != null){
            gui.updateCheckpoints();
            gui.updateTurn();
            gui.updateDeck();
        }
    }


    /**
     * Sets up paths on the board based on palace names.
     *
     * Preconditions:
     * - palaceNames must contain valid palace names.
     *
     * Postconditions:
     * - Paths with positions are added to the board.
     */
    //Sets up Paths
    private void setupPaths(String[] palaceNames){
        palaceNames = new String[]{"Knossos", "Malia", "Phaistos", "Zakros"};

        //Iterate over each palace
        for(String palace : palaceNames){
            List<Position> positions = new ArrayList<>();

            //For each path, place the rare findings randomly in one of the tiles
            List<Integer> availableTiles = new ArrayList<>();
            for(int i = 1; i <= 9; i++){
                availableTiles.add(i);
            }

            //Create a random generator to get the random findings
            Random random = new Random();

            //Randomly generate the rare findings for this path using generateRandomFinding
            for(int i = 1; i <= 9; i++){
                Finding finding = generateRandomFinding(palace);

                if(finding instanceof RareFinding){
                    //Assign a random tile for each rare finding in the path
                    int randomTile = availableTiles.remove(random.nextInt(availableTiles.size()));
                    positions.add(new Position(palace + " Step " + randomTile, calculatePointsForStep(randomTile), true, finding, randomTile));
                }else{
                    //For frescoes/statues, assign them to available tiles
                    int randomTile = availableTiles.remove(random.nextInt(availableTiles.size()));
                    positions.add(new Position(palace + " Step " + randomTile, calculatePointsForStep(randomTile), true, finding, randomTile));
                }
            }

            //Add the regular positions (those that don't have a rare finding)
            for(int i = 1; i <= 9; i++){
                if(!availableTiles.contains(i)){  //Skip the tiles that have a rare finding
                    positions.add(new Position(palace + " Step " + i, calculatePointsForStep(i), false, null, i));
                }
            }

            //Create the Path object for the palace and add it to the board
            board.addPath(new Path(palace, positions));
        }
    }


    /**
     * Calculates the points for a given step number.
     *
     * Preconditions:
     * - step must be between 1 and 9 inclusive.
     *
     * Postconditions:
     * - Returns the point value for the specified step.
     */
    //Points for steps
    public int calculatePointsForStep(int step){
        switch(step){
            case 1:
                return -20;
            case 2:
                return -15;
            case 3:
                return -10;
            case 4:
                return 5;
            case 5:
                return 10;
            case 6:
                return 15;
            case 7:
                return 30; //Checkpoint
            case 8:
                return 35;
            case 9:
                return 50; //Final tile
            default:
                return 0;
        }
    }


    /**
     * Generates a random finding for a specified path.
     *
     * Preconditions:
     * - pathName must be a valid palace name.
     *
     * Postconditions:
     * - Returns a Finding object associated with the given path.
     */
    private Finding generateRandomFinding(String pathName){
        Random random = new Random();

        //Rare findings tied to specific paths
        Map<String, List<RareFinding>> pathToRareFindings = new HashMap<>();
        pathToRareFindings.put("Knossos", Arrays.asList(new RingOfMinoa()));
        pathToRareFindings.put("Malia", Arrays.asList(new HairJewel()));
        pathToRareFindings.put("Phaistos", Arrays.asList(new FaistosDisc()));
        pathToRareFindings.put("Zakros", Arrays.asList(new RytoZakro()));

        //For Fresco
        List<Fresco> frescoes = Arrays.asList(
                new TheBullEaters(),
                new TheDolphins(),
                new TheGalaceLadies(),
                new TheLiliesPrince(),
                new TheParisian(),
                new YouthProcession()
        );

        //For Statue
        List<Statue> statues = Arrays.asList(
                new GoddessOfSnakes()
        );

        if(pathToRareFindings.containsKey(pathName)){
            List<RareFinding> rareFindings = pathToRareFindings.get(pathName);
            if(!rareFindings.isEmpty()){
                return rareFindings.get(random.nextInt(rareFindings.size()));
            }
        }

        //If no rare finding tied to the path, pick from frescoes or statues
        int category = random.nextInt(2); // 0 = Fresco, 1 = Statue
        switch(category){
            case 0: //Fresco
                return frescoes.get(random.nextInt(frescoes.size()));
            case 1: //Statue
                return statues.get(random.nextInt(statues.size()));
            default:
                throw new IllegalStateException("Unexpected category: " + category);
        }
    }


    /**
     * Sets up the deck with cards for each palace.
     *
     * Postconditions:
     * - Cards are added to the deck on the board.
     */
    //Sets up the deck
    private void setupDeck(){
        String[] palaceNames = {"Κνωσσός", "Μάλια", "Φαιστός", "Ζάκρος"};
        for(String palace : palaceNames){
            for(int i = 1; i <= 10; i++){
                board.addCard(new Card(palace, i));
                board.addCard(new Card(palace, i));
            }
            for(int i = 1; i <= 3; i++){
                Ariadne ariadne = new Ariadne(palace + " Μίτου Αριάδνης");
                board.addCard(ariadne);
            }
            for(int i = 1; i <= 2; i++){
                Minotaur minotaur = new Minotaur(palace + " Μινώταυρου");
                board.addCard(minotaur);
            }
        }
    }


    /**
     * Sets up pawns for each player.
     *
     * Postconditions:
     * - Pawns are initialized and assigned to players.
     */
    //Sets up the pawns
    private void setupPawns(){
        String[] pawnNames = {"Αρχαιολόγος", "Θησέας"};
        for(String pawn : pawnNames){
            for(int i = 1; i < 4; i++){
                Pawn Archeologist = new Archaeologist("Αρχαιολόγος", getCurrentPlayer(), null, null, true);
                pawns = pawns + 1;
            }
            Pawn Theseus = new Theseus("Θησέας", getCurrentPlayer(), null, null, true);
            pawns = pawns + 1;
        }
    }


    /**
     * Returns the number of pawns in the game.
     *
     * Preconditions:
     * - pawns must be initialized and represent the actual pawns in the game.
     *
     * Postconditions:
     * - Returns the number of pawns in the game.
     */
    public int getPawns(){
        return pawns;
    }


    /**
     * Shuffles the deck of cards on the board.
     *
     * Preconditions:
     * - The deck of cards must be initialized on the board.
     *
     * Postconditions:
     * - The deck is shuffled, ensuring a random order of cards.
     */
    //Shuffles card deck
    private void shuffleDeck(){
        board.shuffleDeck();
    }


    /**
     * Deals initial cards to each player at the start of the game.
     *
     * Preconditions:
     * - The deck must be initialized and contain enough cards (at least 8 cards per player).
     * - Players must be initialized.
     *
     * Postconditions:
     * - Each player receives 8 cards, and the deck is reduced by the number of cards dealt.
     */
    //Deals Players' cards
    private void dealInitialCards(){
        List<Card> deck = board.getDeck();
        for(Player player : players){
            for(int i = 0; i < 8; i++){
                if(!deck.isEmpty()){
                    player.getHandCards().add(deck.remove(0));
                    cards = cards - 1;
                }
            }
        }
    }


    /**
     * Randomly assigns the first player to start the game.
     *
     * Preconditions:
     * - Players list must be initialized and non-empty.
     *
     * Postconditions:
     * - A random player is selected to start the game, and currentPlayerIndex is updated.
     */
    //Picks random Player
    private void randomlyAssignFirstPlayer(){
        Random random = new Random();
        currentPlayerIndex = random.nextInt(players.size());
    }


    /**
     * Returns the number of checkpoints reached by the specified player's pawns.
     *
     * Preconditions:
     * - The player must exist and have pawns initialized.
     *
     * Postconditions:
     * - Returns the number of pawns in the player's possession that have reached a checkpoint.
     */
    public int getPlayerCheckpoints(Player player){
        int checkpoints = 0;
        for(Pawn pawn : player.getPawns()){
            if(pawn.inCheckpoint()){
                checkpoints++;
            }
        }
        return checkpoints;
    }


    /**
     * Returns the player whose turn it is currently.
     *
     * Preconditions:
     * - Players list must be initialized and contain players.
     * - currentPlayerIndex must be a valid index in the players list.
     *
     * Postconditions:
     * - Returns the player who is currently taking their turn.
     */
    //Current Player
    public Player getCurrentPlayer(){
        return players.get(currentPlayerIndex);
    }


    /**
     * Discards the specified card from the player's hand.
     *
     * Preconditions:
     * - The player must have a valid hand containing the card at the specified index.
     * - The card must be a valid card from the player's hand.
     *
     * Postconditions:
     * - The card is removed from the player's hand at the specified index.
     */
    //Discard card
    public void discardCard(Player player, Card card, int i){
        player.getHandCards().remove(i);
    }


    /**
     * Checks if the game is over.
     *
     * Preconditions:
     * - The deck of cards must be initialized.
     * - Players must have pawns on the board with positions.
     *
     * Postconditions:
     * - Returns true if the game is over (either the deck is empty or at least 4 pawns have reached the end); otherwise, false.
     */
    //Checks if Game is over
    public boolean isGameOver(){
        boolean noCardsLeft = board.getDeck().isEmpty();
        long pawnsReachedEnd = players.stream()
                .flatMap(p -> p.getPawns().stream())
                .filter(p -> p.getCurrentPosition() != null)
                .filter(p -> p.getCurrentPosition().getStepNumber() >= 7)
                .count();

        return noCardsLeft || pawnsReachedEnd >= 4;
    }


    /**
     * Calculates and returns the total score of the game.
     *
     * Preconditions:
     * - Players should have their scores calculated based on the game rules.
     *
     * Postconditions:
     * - Returns the calculated score for the game.
     */
    //Calculate score
    public int getScore(){
        for(Player player : players){
            score = player.calculateScore();
        }
        return score;
    }


    /**
     * Starts a new game by initializing game state and updating the view.
     *
     * Preconditions:
     * - Players and board must be initialized.
     *
     * Postconditions:
     * - A new game is started with a fresh game state, and the view is updated accordingly.
     */
    //Start new Game
    public void startNewGame(){
        initializeGame();
        updateView();
        //playMusicForCurrentPlayer();
    }

    /**
     * Saves the current game state.
     *
     * Preconditions:
     * - The game must be in a valid state that can be saved.
     *
     * Postconditions:
     * - The current game state is saved to a file or storage.
     */
    public void saveGame(){
        //Add saving logic
    }


    /**
     * Loads a previously saved game state.
     *
     * Preconditions:
     * - A saved game file or state must exist and be accessible.
     *
     * Postconditions:
     * - The game state is restored from the saved file or storage.
     */
    public void loadSavedGame(){
        //Add loading logic
    }

    /**
     * Returns the list of players in the game.
     *
     * Preconditions:
     * - Players list must be initialized and non-null.
     *
     * Postconditions:
     * - Returns the list of players currently in the game.
     */
    public List<Player> getPlayers(){
        return players;
    }

    /**
     * Draws a card for the specified player from the deck.
     *
     * Preconditions:
     * - The board must have an initialized deck.
     * - The player must be valid.
     *
     * Postconditions:
     * - The player receives a drawn card from the deck, and the deck is updated accordingly.
     */
    public Card drawCardPlayer(Player player){
        Card drawnCard = board.drawCard();
        if(drawnCard != null){
            cards = cards - 1;
            return drawnCard;
        }
        return null;
    }

    /**
     * Updates the turn to the next player and updates the GUI accordingly.
     *
     * Preconditions:
     * - Players list must be initialized, and currentPlayerIndex must be valid.
     *
     * Postconditions:
     * - The turn is switched to the next player, and the GUI is updated to reflect the current player's turn.
     */
    public Player updateCurrentPlayerTurn(){
        gui.createPlayerSection(getCurrentPlayer().getName(), Color.RED);
        currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        return players.get(currentPlayerIndex);
    }

    /**
     * Returns the total number of remaining cards in the deck.
     *
     * Preconditions:
     * - The cards variable must be initialized.
     *
     * Postconditions:
     * - Returns the current count of remaining cards in the deck.
     */
    public int getCards(){
        return cards;
    }
}