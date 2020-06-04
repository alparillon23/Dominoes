import java.util.ArrayList;

public abstract class DominoPlayer {
    String name;
    ArrayList<Domino> dominos;
    int score;
    //Dominos will be identified as Integer
    public DominoPlayer(String name){
        this.name = name;
        dominos = new ArrayList<>();
        this.score = 0;
    }
    //adds a single domino to the field
    public void addDomino(Domino domino){
        dominos.add(domino);
    }

    public String getName(){
        return name;
    }

    public int handSize(){
        return dominos.size();
    }

    public abstract void play(DominoBoard db);
    protected abstract void removeDomino(DominoBoard db);

    public int getRemainder(){
        int score = 0;
        for(Domino p : dominos){
            score += p.getTotal();
        }
        return score;
    }

    public void addScore(int score){
        this.score += score;
    }

    public int getScore(){
        return score;
    }
    //Checks the board for a legal play - returns the 1 index Domino if found
    //Returns 0 if none can be found - good to test
    public int returnGoodDomino(DominoBoard db){
        int i = 1;
        for(Domino p: dominos){
            if(db.legalOnField(p)){
                return i;
            }
            i++;
        }
        return 0;
    }

    public abstract void playDomino(int index, DominoBoard db);

    public int smallestDomino(){
        int reg = dominos.get(0).getTop() + dominos.get(0).getBottom();
        for(Domino p: dominos){
            if((p.getTop() + p.getBottom())< reg)
                reg = p.getTop() + p.getBottom();
        }
        return reg;
    }

    public DominoBoard initDomino(){
        Domino d = dominos.get(0);
        int reg = d.getTop() + d.getBottom();
        for(Domino p: dominos){
            if((p.getTop() + p.getBottom())< reg) {
                reg = p.getTop() + p.getBottom();
                d = p;
            }
        }
        Domino e = new Domino(d.getTop(), d.getBottom());
        dominos.remove(d);
        return new DominoBoard(e);
    }

    public void showHand(){
        System.out.println(getName() + ", your current hand is: ");
        for(Domino a: dominos){
            System.out.print("\t["+a.getTop()+"||"+a.getBottom()+"]");
        }
        System.out.println();
    }

    public void wipeHand(){
        dominos.clear();
    }

}
