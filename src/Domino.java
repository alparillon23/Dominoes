public class Domino {
    private int top;
    private int bottom;

    public Domino(int top, int bottom){
        this.top = top;
        this.bottom = bottom;
    }

    public int getBottom() {
        return bottom;
    }

    public int getTop() {
        return top;
    }

    public int getTotal(){
        return top + bottom;
    }
}
