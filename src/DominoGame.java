import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class DominoGame {
    private static final Domino[] TABLEOFDOMINOS = {new Domino(0,0),new Domino(0,1)
            ,new Domino(0,2),new Domino(0,3),new Domino(0,4)
            ,new Domino(0,5),new Domino(0,6),new Domino(1,1)
            ,new Domino(1,2),new Domino(1,3),new Domino(1,4)
            ,new Domino(1,5),new Domino(1,6),new Domino(2,2)
            ,new Domino(2,3),new Domino(2,4),new Domino(2,5)
            ,new Domino(2,6),new Domino(3,3),new Domino(3,4)
            ,new Domino(3,5),new Domino(3,6),new Domino(4,4)
            ,new Domino(4,5),new Domino(4,6),new Domino(5,5)
            ,new Domino(5,6),new Domino(6,6)};
    private int bestToScore;
    private int noOfHumans;
    private int noOfBots;
    private int noOfCardsEach;
    private ArrayList<DominoPlayer> players;

    public DominoGame(int bestToScore, int noOfHumans, int noOfBots, int noOfCardsEach){
        Scanner sc = new Scanner(System.in);
        while(noOfBots + noOfHumans > 4){
            System.out.println("Too Many Players");
            System.out.println("How Many Bots?");
            noOfBots = sc.nextInt();
            System.out.println("How Many Humans?");
            noOfHumans = sc.nextInt();
        }
        this.noOfHumans = noOfHumans;
        this.noOfBots = noOfBots;
        this.bestToScore = bestToScore;
        if(noOfCardsEach > TABLEOFDOMINOS.length / (noOfHumans + noOfBots)){
            this.noOfCardsEach = TABLEOFDOMINOS.length / (noOfHumans + noOfBots);
        } else{
            this.noOfCardsEach = noOfCardsEach;
        }
        players = new ArrayList<>();
        for(int i = 1; i <=  noOfBots; i++){
            players.add(new BotPlayer("Bot No."+ i));
        }
        for(int i = 1; i <= noOfHumans; i++){
            System.out.println("Name of Player "+ (players.size() + i));
            String name = sc.nextLine();
            players.add(new RealPlayer(name));
        }
    }

    private boolean someoneWon(){
        for(DominoPlayer d : players){
            if(d.getScore() >= bestToScore){
                System.out.println(d.getName()+" has won");
                return true;
            }
        }
        return false;
    }

    private boolean someoneCanPlay(DominoBoard db){
        for(DominoPlayer d : players){
            if(d.returnGoodDomino(db) != 0){
                return true;
            }
        }
        return false;
    }

    private void startRound(){
        ArrayList<Domino> de = new ArrayList<>();
        for(Domino e: TABLEOFDOMINOS){
            de.add(e);
        }
        int load = de.size() - 1;
        //Give the players their dominos
        for(int i=0; i<players.size(); i++){
            for(int j=0; j<noOfCardsEach; j++){
                if(load == 0){
                    players.get(i).addDomino(de.get(0));
                    de.remove(0);
                    de.trimToSize();
                } else {
                    Random rd = new Random();
                    int card = rd.nextInt(load);
                    players.get(i).addDomino(de.get(card));
                    de.remove(card);
                    de.trimToSize();
                    load--;
                }

            }
        }
        //Determine who starts - Only by Lowest Card for Now
        int a = 0;
        int reg = players.get(0).smallestDomino();
        for(int i = 0; i< players.size(); i++){
            if (players.get(i).smallestDomino() < reg){
                reg = players.get(i).smallestDomino();
                a = i;
            }
        }
        DominoBoard db = players.get(a).initDomino();
        //While someone can play do
        while(someoneCanPlay(db)){
            a = (a + 1) % (noOfBots + noOfHumans);
            while(de.size() != 0 && players.get(a).returnGoodDomino(db) == 0){
                Random rd = new Random(load--);
                int card = rd.nextInt();
                players.get(a).addDomino(de.get(card));
                de.remove(card);
                de.trimToSize();
            }
            if(players.get(a).handSize() == 0){
                break;
            }
            players.get(a).play(db);
        }

        //If nobody can play do
        if(players.get(a).handSize() == 0){
            int totalCashIn = 0;
            for(int i = 0; i < players.size(); i++){
                totalCashIn += players.get(i).getRemainder();
            }
            players.get(a).addScore(totalCashIn);
            System.out.println(players.get(a).getName() + " has won the round (" + players.get(a).getScore()+" pts)");
        } else {
            a = 0;
            int totalCashIn = 0;
            int low = players.get(a).getRemainder();
            for(int i = 0; i < players.size(); i++){
                if(players.get(i).getRemainder() < low){
                    a = i;
                    low = players.get(i).getRemainder();
                }
            }
            for(int i = 0; i < players.size(); i++){
                if(i != a)
                    totalCashIn += players.get(i).getRemainder();
            }
            totalCashIn -= players.get(a).getRemainder();
            players.get(a).addScore(totalCashIn);
            System.out.println(players.get(a).getName() + " has won the round (" + players.get(a).getScore()+" pts)");
        }
        System.out.println("The Rankings");
        for(DominoPlayer dp : players){
            dp.wipeHand();
            System.out.println(dp.getName()+" \t....." + dp.getScore());
        }

    }

    public void startGame(){
        while(!someoneWon()){
            startRound();
        }
        for(DominoPlayer dp : players){
            if(dp.getScore() >= bestToScore){
                System.out.println(dp.getName()+" has won the match");
            }
        }
        System.out.println("The Rankings");
        for(DominoPlayer dp : players){
            System.out.println(dp.getName()+" \t....." + dp.getScore());
        }
        System.out.println("Thanks for Playing!");
    }
}
