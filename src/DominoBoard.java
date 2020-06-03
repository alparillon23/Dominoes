public class DominoBoard {
    private int left;
    private int right;
    private DominoLinkedList head;
    //private DominoLinkedList tail;

    public DominoBoard(Domino db){
        left = db.getTop();
        right = db.getBottom();
        head = new DominoLinkedList(db);
        //tail = new DominoLinkedList(db);
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
            head.addDominoLeft(new Domino(d.getBottom(), d.getTop()));
        } else {
            left = d.getTop();
            head.addDominoLeft(d);
        }
        head = head.getLeft();
    }

    public void playRight(Domino d){
        if(d.getTop() == right){
            right = d.getBottom();
            head.addDominoRight(d);
        } else {
            right = d.getTop();
            head.addDominoRight(new Domino(d.getBottom(), d.getTop()));
        }
        head = head.getLeft();
    }

    public void showBoard(){
        System.out.println("The left end is: " + left + "\nThe right end is: " + right);
        head.showDominoChain();
    }
}
