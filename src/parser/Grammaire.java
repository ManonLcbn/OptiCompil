package parser;

import java.io.IOException;
import java.util.List;

import lexer.Token;
import parse_tree.AbstractExpression;
import parse_tree.ArbreSyntaxique;

public abstract class Grammaire {
    public Parser parser;
    public Grammaire(int[] tableTag,int[][] table){
        parser=new Parser((GrammaireLL1Test) this, tableTag, table);
    }
    public ArbreSyntaxique analyse() throws IOException{
        return parser.Analyseur();
    }
    public void createRegle(NonTerminal mGauche,List<Symbole> mDroit){
        parser.table.addRegle(new RegleGrammaire(mGauche,mDroit));
    }
    public Terminal createTerminal(int tag){
        return new Terminal(new Token(tag));
    }
    public NonTerminal createNonTerminal(){
        return new NonTerminal();
    }

    public abstract String getNonTerminal(int idRegle);

    public abstract String getTerminal(int tag);

}
