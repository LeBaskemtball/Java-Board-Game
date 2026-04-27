package MODEL.Players;

import MODEL.Cards.Card;
import MODEL.Findings.Finding;
import MODEL.Findings.Fresco;
import MODEL.Findings.Statue;
import MODEL.Pawns.Pawn;
import MODEL.Table.Position;

import java.util.List;
import java.util.ArrayList;

/**
 * The Player class represents a player in the game, with attributes for the player's name, score,
 * pawns, hand cards, and collected findings. The player can calculate their score based on collected
 * findings, statues, frescoes, and pawn positions.
 */
public class Player{
    private String name;
    private int score;
    private int ID;
    private List<Pawn> pawns;           //List of the Player's pawns
    private List<Card> handCards;       //List of the Player's card
    private List<Finding> findings;     //List of Rare Findings
    private List<Statue> statues;       //List of Statues
    private List<Fresco> wallPaintings; //List of Wall Paintings

    /**
     * Constructor for creating a new player with an ID, name, and other details.
     *
     * @param name The player's name.
     */
    public Player(String name){
        this.name = name;
        this.score = 0;
        this.ID = 0;
        this.pawns = new ArrayList<>();
        this.handCards = new ArrayList<>();
        this.findings = new ArrayList<>();
        this.statues = new ArrayList<>();
        this.wallPaintings = new ArrayList<>();
    }

    // Getters and setters for the attributes

    public String getName(){
        return name;
    }
    public List<Pawn> getPawns(){
        return pawns;
    }
    public List<Card> getHandCards(){
        return handCards;
    }
    public void addPawn(Pawn pawn){
        pawns.add(pawn);
    }
    public void collectFindings(Finding finding){
        if(!finding.isCollected()){
            finding.collect();
        }
    }
    public int statueCount(){
        int statuecount = 0;
        for(Statue statue : statues){
            statuecount++;
        }
        return statuecount;
    }

    /**
     * Calculates the player's score based on findings, statues, frescoes, and pawns.
     *
     * @return The calculated score.
     */
    public int calculateScore(){
        //Calculate the score based on collected findings, statues and wall paintings and pawn position
        int count = 0;

        //1. Findings
        for(Finding finding : findings){
            score += finding.getPoints();
        }

        //2. Statues
        for(Statue statue : statues){
            count += statue.getPoints();
            if(count == 0){
                score = score + 0;
            }else if(count == 1){
                score = score - 20;
            }else if(count == 2){
                score = score - 15;
            }else if(count == 3){
                score = score + 10;
            }else if(count == 4){
                score = score + 15;
            }else if(count == 5){
                score = score + 30;
            }else if(count == 6){
                score = score + 50;
            }else{
                score = score + 60;
            }
        }

        //3. Wall Paintings (Fresco)
        for(Fresco fresco : wallPaintings){
            score += fresco.getPoints();
        }

        //4. Pawns
        for(Pawn pawn : getPawns()){
            Position currentPosition = pawn.getCurrentPosition();
            int positionScore = currentPosition.getPoints();
            if(pawn.getPawnName().equals("Theseus")){
                positionScore *= 2; //Double score
            }
            score += positionScore;
        }

        return score;
    }
}
