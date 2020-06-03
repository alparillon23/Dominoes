import java.util.Scanner;

public class RealPlayer extends DominoPlayer {
    public RealPlayer(String name) {
        super(name);
    }

    @Override
    public void play(DominoBoard db) {
        System.out.println("Player "+ name + " make a choice ");
        System.out.println("\nSee Board\nSee Hand\nPlay");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        while(true){
            if(choice.equalsIgnoreCase("see board")){
                showBoard(db);
            } else if(choice.equalsIgnoreCase("see hand")){
                showHand();
            } else if(choice.equalsIgnoreCase("play")){
                removeDomino(db);
                break;
            } else{
                System.out.println("Invalid Command, Try Again");
            }
            System.out.println("Player "+ name + " make a choice ");
            System.out.println("\nSee Board\nSee Hand\nPlay");
            sc = new Scanner(System.in);
            choice = sc.nextLine();
        }
    }

    @Override
    protected void removeDomino(DominoBoard db) {
        int tryD = returnGoodDomino(db);
        if(tryD == 0){
            System.out.println("Player "+ name + " can't play this turn");
        } else {
            System.out.println(name + " select your domino 1 - "+ dominos.size());
            Scanner sc = new Scanner(System.in);
            int index = sc.nextInt();
            playDomino(index - 1, db);
        }
    }

    @Override
    public void playDomino(int index, DominoBoard db) {
        while(true){
                if(index < 0 || index >= dominos.size()){
                    System.out.println("Invalid Domino");
                } else{
                    Domino d = dominos.get(index);
                    System.out.println("The Domino You Chose is: ["+ d.getTop()
                    + "/" + d.getBottom() + "]");
                    if(db.worksBothEnds(d)){
                        Scanner sc = new Scanner(System.in);
                        System.out.println("Which side: [L/R]");
                        String choice = sc.nextLine();
                        if(choice.equalsIgnoreCase("L"))
                            db.playLeft(d);
                        else
                            db.playRight(d);
                        dominos.remove(index);
                        dominos.trimToSize();
                        return;
                    } else {
                        if(db.worksOnLeft(d)){
                            db.playLeft(d);
                            dominos.remove(index);
                            dominos.trimToSize();
                            return;
                        } else if (db.worksOnRight(d)){
                            db.playRight(d);
                            dominos.remove(index);
                            dominos.trimToSize();
                            return;
                        } else {
                            System.out.println("This Domino Cannot be Played! Pick Again");
                            System.out.println(name + " select your domino 1 - "+ dominos.size());
                            Scanner sc = new Scanner(System.in);
                            index = sc.nextInt() - 1;
                        }
                    }
                }

        }
    }

    public void showBoard(DominoBoard db){
        db.showBoard();
    }


}
