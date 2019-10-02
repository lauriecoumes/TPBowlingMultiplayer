/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import bowling.MultiPlayerGame;
import bowling.SinglePlayerGame;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 *
 * @author pedago
 */
public class Multiplayer implements MultiPlayerGame {

    private HashMap dic = new HashMap();
    private SinglePlayerGame game;
    private String currentPlayer;

    private boolean gameRunning = false;
    
    public Multiplayer() {
        dic = new LinkedHashMap<>();                
    }

    @Override
    public String startNewGame(String[] playerName) throws Exception {
        if (playerName == null || playerName.length == 0) {
            throw new Exception("Il faut au moins un joueur.");
        }

        for (int i = 0; i<playerName.length; i++) {
            dic.put(i, new SinglePlayerGame());
        }

        gameRunning = true;

        return afficherMessage();
    }

    @Override
    public String lancer(int nombreDeQuillesAbattues) throws Exception {
        if (!gameRunning) {
            throw new Exception("Le jeu est terminé");
        }
        game.lancer(nombreDeQuillesAbattues);

        return "Prochain tir";
    }

    @Override
    public int scoreFor(String playerName) throws Exception {
        if (game == null) {
            throw new Exception("Le joueur est incoonu.");
        }
        return game.score();
    }
    
    private String afficherMessage() {
        if (!gameRunning) {
            return "Partie terminée.";
        }
        else {
            int tour = game.getFrameNumber();
            int balle = game.getNextBallNumber();
            return String.format(
                    "Prochain tir : joueur %s, tour n° %d, boule n° %d",
                    currentPlayer,
                    tour,
                    balle);
        }
    }

    public static void main(String[] args) throws Exception {
        String[] players = {"John", "Paul", "Georges", "Ringo"};
        MultiPlayerGame game = new Multiplayer();
        System.out.println(game.startNewGame(players));
    }

}
