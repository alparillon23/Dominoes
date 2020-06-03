public class DominoBoard {
    private int left;
    private int right;

    public DominoBoard(Domino db){
        left = db.getTop();
        right = db.getBottom();
    }

    public DominoBoard(){

    }

    public boolean legalOnField(Domino d){
        return ((d.getBottom() == left) || (d.getBottom() == right)
                || (d.getTop() == left) || (d.getTop() == right) );
    }

    public boolean worksBothEnds(Domino d){
        return ((d.getBottom() == left) && (d.getTop() == right)) ||
                ((d.getBottom() == right) && (d.getTop() == left));
    }

    public boolean worksOnLeft(Domino d){
        return (d.getBottom() == left) || (d.getTop() == left);
    }

    public boolean worksOnRight(Domino d){
        return (d.getBottom() == right) || (d.getTop() == right);
    }

    public void playLeft(Domino d){
        if(d.getTop() == left){
            left = d.getBottom();
        } else {
            left = d.getTop();
        }
    }

    public void playRight(Domino d){
        if(d.getTop() == right){
            right = d.getBottom();
        } else {
            right = d.getTop();
        }
    }

    public void showBoard(){
        System.out.println("The left end is: " + left + "\nThe right end is: " + right);
    }
}
