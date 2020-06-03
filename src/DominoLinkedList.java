public class DominoLinkedList {
    Domino main;
    DominoLinkedList left;
    DominoLinkedList right;

    public DominoLinkedList (Domino db) {
        main = db;
        left = null;
        right = null;
    }

    public void showDominoChain(){
        System.out.print("\t["+main.getTop()+"||"+main.getBottom()+"]\t");
        if(right != null)
            right.showDominoChain();
        else
            System.out.println(" ");
    }

    public void addDominoLeft(Domino e){
        if(left != null){
            left.addDominoLeft(e);
        } else {
            makeLeft(e);
        }
    }

    public void addDominoRight(Domino e){
        if(right != null){
            right.addDominoRight(e);
        } else {
            makeRight(e);
        }
    }

    public void makeLeft(Domino e) {
        DominoLinkedList leftE = new DominoLinkedList(e);
        leftE.left = null;
        leftE.right = this;
        this.left = leftE;
    }

    public void makeRight(Domino e) {
        DominoLinkedList rightE = new DominoLinkedList(e);
        rightE.left = this;
        this.right = rightE;
        rightE.right = null;
    }

    public DominoLinkedList getLeft() {
        if(left != null)
            return left.getLeft();
        return this;
    }
}
