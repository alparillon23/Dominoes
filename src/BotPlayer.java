import java.util.Random;

public class BotPlayer extends DominoPlayer {
    public BotPlayer(String name) {
        super(name);
    }

    @Override
    public void play(DominoBoard db) {
        removeDomino(db);
    }

    @Override
    protected void removeDomino(DominoBoard db) {
        int tryD = returnGoodDomino(db);
        if(tryD == 0){
            System.out.println("Player "+ name + " can't play this turn");
        } else {
            playDomino(tryD - 1, db);
            dominos.remove(tryD - 1);
            dominos.trimToSize();
        }
    }

    @Override
    public void playDomino(int index, DominoBoard db) {
        Domino d = dominos.get(index);
        if(db.worksBothEnds(d)){
            Random e = new Random();
            switch(e.nextInt(1)){
                case 0:
                    db.playLeft(d);
                    break;
                default:
                    db.playRight(d);
                    break;
            }
        } else {
            if(db.worksOnLeft(d))
                db.playLeft(d);
            else
                db.playRight(d);
        }

    }
}
