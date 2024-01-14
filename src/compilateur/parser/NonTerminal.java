package compilateur.parser;

public class NonTerminal extends Symbole{

    private static int nextId = -1;
    private int id;

    public NonTerminal(){
        this.id=NonTerminal.nextId;
        NonTerminal.nextId++;
    }

    public int getId(){
        return(this.id);
    }

    public static int getNextId(){
        return NonTerminal.nextId;
    }

}