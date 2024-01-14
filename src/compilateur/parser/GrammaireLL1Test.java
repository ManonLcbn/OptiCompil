package compilateur.parser;

import java.util.*;
import compilateur.lexer.*;
import compilateur.parse_tree.*;
import compilateur.abstractTree.*;

public class GrammaireLL1Test extends Grammaire{
    private static int[] tableTag = {Tag.WITH,Tag.ADATEXTIO,Tag.POINTV,Tag.USE,Tag.PROCEDURE,Tag.IDENT,Tag.IS,Tag.BEGIN,Tag.END,(int)'$',Tag.TYPE,Tag.DPOINTS,Tag.FUNCTION,Tag.RETURN,Tag.ACCESS,Tag.RECORD,Tag.VIRGULE,Tag.AFFECT,Tag.PO,Tag.PF,Tag.IN,Tag.OUT,Tag.OR,Tag.ELSE,Tag.AND,Tag.THEN,Tag.NOT,Tag.EGALE,Tag.NEGALE,Tag.SUP,Tag.SUPEG,Tag.INF,Tag.INFEG,Tag.PLUS,Tag.MOINS,Tag.ETOILE,Tag.DIV,Tag.REM,Tag.ENTIER,Tag.CHAR,Tag.TRUE,Tag.FALSE,Tag.NULL,Tag.NEW,Tag.CARACTEREVAL,Tag.POINT,Tag.IF,Tag.FOR,Tag.POINTPOINT,Tag.LOOP,Tag.WHILE,Tag.ELSIF,Tag.REVERSE};
    private static int[][] table = 
                        {{0,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,2,2,-1,1,-1,-1,2,-1,2,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,4,-1,4,3,-1,-1,-1,-1,4,-1,-1,-1,-1,4,-1,-1,-1,-1,3,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,4,4,4,4,4,4,4,-1,4,4,-1,-1,4,3,-1,-1,-1},
                        {-1,-1,5,-1,-1,6,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,9,8,-1,-1,-1,-1,7,-1,10,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,11,-1,-1,-1,12,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,13,14,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,16,-1,-1,15,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,18,-1,-1,-1,-1,17,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,19,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,20,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,22,-1,-1,-1,-1,-1,-1,22,-1,-1,-1,-1,21,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,23,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,24,-1,-1,-1,-1,-1,-1,-1,-1,25,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,26,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,28,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,27,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,29,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,-1,-1,-1,30,-1,-1,-1,-1,-1,31,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,32,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,33,-1,-1,-1,-1,-1,-1,-1,-1,33,-1,-1,-1,-1,-1,-1,34,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,35,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,35,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,35,-1,-1,-1,35,35,35,35,35,35,35,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,36,-1,-1,-1,36,36,36,36,36,36,36,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,38,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,38,-1,-1,38,-1,-1,37,-1,-1,38,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,38,38,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,39,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,39,-1,-1,-1,-1,40,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,39,-1,-1,-1,39,39,39,39,39,39,39,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,41,-1,-1,-1,41,41,41,41,41,41,41,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,43,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,43,-1,-1,43,-1,-1,43,-1,42,43,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,43,43,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,44,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,44,-1,-1,-1,-1,-1,-1,45,-1,-1,-1,-1,-1,-1,-1,-1,44,-1,-1,-1,44,44,44,44,44,44,44,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,46,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,46,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,46,-1,-1,-1,46,46,46,46,46,46,46,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,48,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,48,-1,-1,48,-1,-1,48,-1,48,48,47,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,48,48,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,49,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,49,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,49,-1,-1,-1,49,49,49,49,49,49,49,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,52,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,52,-1,-1,52,-1,-1,52,-1,52,52,52,50,51,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,52,52,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,53,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,53,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,53,-1,-1,-1,53,53,53,53,53,53,53,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,58,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,58,-1,-1,58,-1,-1,58,-1,58,58,58,58,58,54,55,56,57,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,58,58,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,59,-1,-1,-1,59,59,59,59,59,59,59,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,62,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,-1,-1,62,-1,-1,62,-1,62,62,62,62,62,62,62,62,62,60,61,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,62,62,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,63,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,63,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,63,-1,-1,-1,63,63,63,63,63,63,63,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,67,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,67,-1,-1,67,-1,-1,67,-1,67,67,67,67,67,67,67,67,67,67,67,64,65,66,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,67,67,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,69,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,68,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,70,-1,-1,-1,68,68,68,68,68,68,68,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,76,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,71,72,73,74,75,78,79,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,77,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,85,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,80,81,82,83,84,86,87,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,88,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,88,-1,89,88,-1,-1,88,-1,88,88,88,88,88,88,88,88,88,88,88,88,88,88,-1,-1,-1,-1,-1,-1,-1,88,-1,-1,88,88,-1,-1,-1,-1,-1},
                        {-1,-1,91,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,91,91,-1,91,-1,-1,91,-1,91,91,91,91,91,91,91,91,91,91,91,91,91,91,-1,-1,-1,-1,-1,-1,-1,90,-1,-1,91,91,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,92,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,94,-1,-1,93,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,95,-1,98,-1,-1,-1,-1,-1,97,-1,-1,-1,-1,96,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,96,96,96,96,96,96,96,-1,99,100,-1,-1,101,-1,-1,-1,-1},
                        {-1,-1,103,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,102,104,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,105,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,106,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,107,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,108,-1,-1,109,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,109,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,109,-1,-1,-1,109,109,109,109,109,109,109,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,110,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,110,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,111,-1,-1,-1},
                        {-1,-1,-1,-1,-1,-1,-1,-1,112,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,113,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1},
                        {-1,-1,-1,-1,-1,114,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,114,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,114,-1,-1,-1,114,114,114,114,114,114,114,-1,-1,-1,-1,-1,-1,-1,115,-1,-1}};


    private Map<Integer, Terminal> terminaux = new HashMap<>();

    private NonTerminal fichier = createNonTerminal();
    private NonTerminal declEtoile = createNonTerminal();
    private NonTerminal instrEtoile = createNonTerminal();
    private NonTerminal identOpt = createNonTerminal();
    private NonTerminal decl = createNonTerminal();
    private NonTerminal A1 = createNonTerminal();
    private NonTerminal B1 = createNonTerminal();
    private NonTerminal champsEtoile = createNonTerminal();
    private NonTerminal identVE = createNonTerminal();
    private NonTerminal exprOptEgal = createNonTerminal();
    private NonTerminal paramsOpt = createNonTerminal();
    private NonTerminal champs = createNonTerminal();
    private NonTerminal type = createNonTerminal();
    private NonTerminal params = createNonTerminal();
    private NonTerminal paramsPVE = createNonTerminal();
    private NonTerminal param = createNonTerminal();
    private NonTerminal modeOpt = createNonTerminal();
    private NonTerminal mode = createNonTerminal();
    private NonTerminal C1 = createNonTerminal();
    private NonTerminal expr = createNonTerminal();
    private NonTerminal exprOperateur = createNonTerminal();
    private NonTerminal EXPROPERATEUR = createNonTerminal();
    private NonTerminal D1 = createNonTerminal();
    private NonTerminal exprAnd = createNonTerminal();
    private NonTerminal EXPRAND = createNonTerminal();
    private NonTerminal E1 = createNonTerminal();
    private NonTerminal exprNot = createNonTerminal();
    private NonTerminal EXPRNOT = createNonTerminal();
    private NonTerminal exprEgal = createNonTerminal();
    private NonTerminal EXPREGAL = createNonTerminal();
    private NonTerminal exprComparaison = createNonTerminal();
    private NonTerminal EXPRCOMPARAISON = createNonTerminal();
    private NonTerminal exprSomme = createNonTerminal();
    private NonTerminal EXPRSOMME = createNonTerminal();
    private NonTerminal exprMult = createNonTerminal();
    private NonTerminal EXPRMULT = createNonTerminal();
    private NonTerminal exprUnaire = createNonTerminal();
    private NonTerminal exprTerm1 = createNonTerminal();
    private NonTerminal exprTerm2 = createNonTerminal();
    private NonTerminal exprTerm3 = createNonTerminal();
    private NonTerminal G1 = createNonTerminal();
    private NonTerminal EXPRTERM = createNonTerminal();
    private NonTerminal EXPRTERM2 = createNonTerminal();
    private NonTerminal EXPRVE = createNonTerminal();
    private NonTerminal instr = createNonTerminal();
    private NonTerminal H1 = createNonTerminal();
    private NonTerminal I1 = createNonTerminal();
    private NonTerminal EXPROPT = createNonTerminal();
    private NonTerminal ELSIF = createNonTerminal();
    private NonTerminal ELSE = createNonTerminal();
    private NonTerminal reverseOpt = createNonTerminal();

    private Deque<Component> pile = new ArrayDeque<Component>();
    
    public GrammaireLL1Test(){
        super(tableTag, table);

        for(int tag: tableTag){
            terminaux.put(tag, createTerminal(tag)); //t(Tag.jsp)
        }

        //création des règles:
        createRegle(fichier, Arrays.asList(t(Tag.WITH), t(Tag.ADATEXTIO), t(Tag.POINTV), t(Tag.USE), t(Tag.ADATEXTIO), t(Tag.POINTV), t(Tag.PROCEDURE), t(Tag.IDENT), t(Tag.IS), declEtoile, t(Tag.BEGIN), instr, instrEtoile, t(Tag.END), identOpt)); //r0
        createRegle(declEtoile, Arrays.asList()); // r1
        createRegle(declEtoile, Arrays.asList(decl, declEtoile)); //r2
        createRegle(instrEtoile, Arrays.asList()); // r3
        createRegle(instrEtoile, Arrays.asList(instr, instrEtoile)); // r4
        createRegle(identOpt, Arrays.asList(t(Tag.POINTV))); //r5
        createRegle(identOpt, Arrays.asList(t(Tag.IDENT), t(Tag.POINTV))); // r6
        createRegle(decl, Arrays.asList(t(Tag.TYPE), t(Tag.IDENT), A1)); //r7
        createRegle(decl, Arrays.asList(t(Tag.IDENT), identVE, t(Tag.DPOINTS), type, exprOptEgal)); // r8
        createRegle(decl, Arrays.asList(t(Tag.PROCEDURE), t(Tag.IDENT), paramsOpt, t(Tag.IS), declEtoile, t(Tag.BEGIN),instr, instrEtoile, t(Tag.END), identOpt)); // r9
        createRegle(decl, Arrays.asList(t(Tag.FUNCTION), t(Tag.IDENT), paramsOpt, t(Tag.RETURN), type, t(Tag.IS), declEtoile, t(Tag.BEGIN), instr, instrEtoile, t(Tag.END), identOpt)); // r10
        createRegle(A1, Arrays.asList(t(Tag.POINTV))); //r11
        createRegle(A1, Arrays.asList(t(Tag.IS), B1)); // r12
        createRegle(B1, Arrays.asList(t(Tag.ACCESS), t(Tag.IDENT), t(Tag.POINTV))); // r13
        createRegle(B1, Arrays.asList(t(Tag.RECORD),champs, champsEtoile, t(Tag.END), t(Tag.RECORD), t(Tag.POINTV))); // r14
        createRegle(champsEtoile, Arrays.asList());// r15
        createRegle(champsEtoile, Arrays.asList(champs, champsEtoile)); //r16
        createRegle(identVE, Arrays.asList(t(Tag.VIRGULE), t(Tag.IDENT), identVE)); // r17
        createRegle(identVE, Arrays.asList());// r18
        createRegle(exprOptEgal, Arrays.asList(t(Tag.POINTV))); // r19
        createRegle(exprOptEgal, Arrays.asList(t(Tag.AFFECT), expr, t(Tag.POINTV))); // r20
        createRegle(paramsOpt, Arrays.asList(params));   // r21
        createRegle(paramsOpt, Arrays.asList());// r22
        createRegle(champs, Arrays.asList(t(Tag.IDENT), identVE, t(Tag.DPOINTS), type, t(Tag.POINTV))); // r23
        createRegle(type, Arrays.asList(t(Tag.IDENT))); // r24
        createRegle(type, Arrays.asList(t(Tag.ACCESS), t(Tag.IDENT))); //r25
        createRegle(params, Arrays.asList(t(Tag.PO), param, paramsPVE, t(Tag.PF))); // r26
        createRegle(paramsPVE, Arrays.asList()); // r27
        createRegle(paramsPVE, Arrays.asList(t(Tag.POINTV), param, paramsPVE)); // r28
        createRegle(param, Arrays.asList(t(Tag.IDENT), identVE, t(Tag.DPOINTS), modeOpt, type)); // r29
        createRegle(modeOpt, Arrays.asList()); // r30
        createRegle(modeOpt, Arrays.asList(mode)); // r31
        createRegle(mode, Arrays.asList(t(Tag.IN), C1)); //r32
        createRegle(C1, Arrays.asList()); // r33
        createRegle(C1, Arrays.asList(t(Tag.OUT))); // r34
        createRegle(expr, Arrays.asList(exprOperateur)); // r35
        createRegle(exprOperateur, Arrays.asList(exprAnd, EXPROPERATEUR)); // r36
        createRegle(EXPROPERATEUR, Arrays.asList(t(Tag.OR), D1)); // r37
        createRegle(EXPROPERATEUR, Arrays.asList()); // r38
        createRegle(D1, Arrays.asList(exprAnd, EXPROPERATEUR)); // r39
        createRegle(D1, Arrays.asList(t(Tag.ELSE), exprAnd, EXPROPERATEUR)); // r40
        createRegle(exprAnd, Arrays.asList(exprNot, EXPRAND)); //r41
        createRegle(EXPRAND, Arrays.asList(t(Tag.AND), E1)); //r42
        createRegle(EXPRAND, Arrays.asList());//r43
        createRegle(E1, Arrays.asList(exprNot, EXPRAND)); // r44
        createRegle(E1, Arrays.asList(t(Tag.THEN), exprNot, EXPRAND)); //r45
        createRegle(exprNot, Arrays.asList(exprEgal, EXPRNOT));// r46
        createRegle(EXPRNOT, Arrays.asList(t(Tag.NOT), exprEgal, EXPRNOT)); // r47
        createRegle(EXPRNOT, Arrays.asList()); // r48
        createRegle(exprEgal, Arrays.asList(exprComparaison, EXPREGAL)); // r49
        createRegle(EXPREGAL, Arrays.asList(t(Tag.EGALE), exprComparaison, EXPREGAL)); // r50
        createRegle(EXPREGAL, Arrays.asList(t(Tag.NEGALE), exprComparaison, EXPREGAL)); // r51
        createRegle(EXPREGAL, Arrays.asList()); // r52
        createRegle(exprComparaison, Arrays.asList(exprSomme, EXPRCOMPARAISON)); // r53
        createRegle(EXPRCOMPARAISON, Arrays.asList(t(Tag.SUP), exprSomme, EXPRCOMPARAISON)); // r54
        createRegle(EXPRCOMPARAISON, Arrays.asList(t(Tag.SUPEG), exprSomme, EXPRCOMPARAISON)); // r55
        createRegle(EXPRCOMPARAISON, Arrays.asList(t(Tag.INF), exprSomme, EXPRCOMPARAISON)); // r56
        createRegle(EXPRCOMPARAISON, Arrays.asList(t(Tag.INFEG), exprSomme, EXPRCOMPARAISON)); // r57
        createRegle(EXPRCOMPARAISON, Arrays.asList()); //r58
        createRegle(exprSomme, Arrays.asList(exprMult, EXPRSOMME)); // r59
        createRegle(EXPRSOMME, Arrays.asList(t(Tag.PLUS), exprMult, EXPRSOMME)); // r60
        createRegle(EXPRSOMME, Arrays.asList(t(Tag.MOINS), exprMult, EXPRSOMME)); // r61
        createRegle(EXPRSOMME, Arrays.asList()); // r62
        createRegle(exprMult, Arrays.asList(exprUnaire, EXPRMULT)); //r63
        createRegle(EXPRMULT, Arrays.asList(t(Tag.ETOILE), exprUnaire, EXPRMULT)); // r64
        createRegle(EXPRMULT, Arrays.asList(t(Tag.DIV), exprUnaire, EXPRMULT)); // r65
        createRegle(EXPRMULT, Arrays.asList(t(Tag.REM), exprUnaire, EXPRMULT));// r66
        createRegle(EXPRMULT, Arrays.asList());// r67
        createRegle(exprUnaire, Arrays.asList(exprTerm1)); //r68
        createRegle(exprUnaire, Arrays.asList(exprTerm2)); // r69
        createRegle(exprUnaire, Arrays.asList(t(Tag.MOINS), exprUnaire)); //r70
        createRegle(exprTerm1, Arrays.asList(t(Tag.ENTIER), EXPRTERM)); // r71
        createRegle(exprTerm1, Arrays.asList(t(Tag.CHAR), EXPRTERM));// r72
        createRegle(exprTerm1, Arrays.asList(t(Tag.TRUE), EXPRTERM)); //r73
        createRegle(exprTerm1, Arrays.asList(t(Tag.FALSE), EXPRTERM)); //r74
        createRegle(exprTerm1, Arrays.asList(t(Tag.NULL), EXPRTERM)); // r75
        createRegle(exprTerm1, Arrays.asList(t(Tag.PO), expr, t(Tag.PF), EXPRTERM)); // r76
        createRegle(exprTerm2, Arrays.asList(t(Tag.IDENT), G1)); // r77
        createRegle(exprTerm1, Arrays.asList(t(Tag.NEW), t(Tag.IDENT), EXPRTERM)); // r78
        createRegle(exprTerm1, Arrays.asList(t(Tag.CARACTEREVAL), t(Tag.PO), expr, t(Tag.PF), EXPRTERM)); // r79
        createRegle(exprTerm3, Arrays.asList(t(Tag.ENTIER), EXPRTERM2)); // r80
        createRegle(exprTerm3, Arrays.asList(t(Tag.CHAR), EXPRTERM2)); // r81
        createRegle(exprTerm3, Arrays.asList(t(Tag.TRUE), EXPRTERM2)); // r82
        createRegle(exprTerm3, Arrays.asList(t(Tag.FALSE), EXPRTERM2)); // r83
        createRegle(exprTerm3, Arrays.asList(t(Tag.NULL), EXPRTERM2)); // r84
        createRegle(exprTerm3, Arrays.asList(t(Tag.PO), expr, t(Tag.PF), EXPRTERM2)); // r85
        createRegle(exprTerm3, Arrays.asList(t(Tag.NEW), t(Tag.IDENT), EXPRTERM2)); // r86
        createRegle(exprTerm3, Arrays.asList(t(Tag.CARACTEREVAL), t(Tag.PO), expr, t(Tag.PF), EXPRTERM2)); // r87
        createRegle(G1, Arrays.asList(EXPRTERM)); // r88
        createRegle(G1, Arrays.asList(t(Tag.PO), expr, EXPRVE, t(Tag.PF), EXPRTERM));// r89
        createRegle(EXPRTERM, Arrays.asList(t(Tag.POINT), t(Tag.IDENT), EXPRTERM));// r90
        createRegle(EXPRTERM, Arrays.asList()); // r91
        createRegle(EXPRTERM2, Arrays.asList(t(Tag.POINT), t(Tag.IDENT), EXPRTERM));// r92
        createRegle(EXPRVE, Arrays.asList()); // r93
        createRegle(EXPRVE, Arrays.asList(t(Tag.VIRGULE), expr, EXPRVE));// r94
        createRegle(instr, Arrays.asList(t(Tag.IDENT), H1)); //r95
        createRegle(instr, Arrays.asList(exprTerm3, t(Tag.AFFECT), expr, t(Tag.POINTV))); //r96
        createRegle(instr, Arrays.asList(t(Tag.RETURN), EXPROPT, t(Tag.POINTV)));//r97
        createRegle(instr, Arrays.asList(t(Tag.BEGIN), instr, instrEtoile, t(Tag.END),t(Tag.POINTV))); //r98
        createRegle(instr, Arrays.asList(t(Tag.IF), expr, t(Tag.THEN), instr, instrEtoile, ELSIF, ELSE, t(Tag.END), t(Tag.IF))); //r99
        createRegle(instr, Arrays.asList(t(Tag.FOR), t(Tag.IDENT), t(Tag.IN), reverseOpt, expr, t(Tag.POINTPOINT), expr, t(Tag.LOOP), instr, instrEtoile, t(Tag.END), t(Tag.LOOP))); // r100
        createRegle(instr, Arrays.asList(t(Tag.WHILE), expr, t(Tag.LOOP), instr, instrEtoile, t(Tag.END), t(Tag.LOOP)));//r101
        createRegle(H1, Arrays.asList(t(Tag.AFFECT), expr, t(Tag.POINTV))); // r102
        createRegle(H1, Arrays.asList(t(Tag.POINTV))); // r103
        createRegle(H1, Arrays.asList(t(Tag.PO), expr, EXPRVE, t(Tag.PF), I1)); // r104
        createRegle(H1, Arrays.asList(EXPRTERM2, t(Tag.AFFECT), expr, t(Tag.POINTV))); //r105
        createRegle(I1, Arrays.asList(t(Tag.POINTV))); // r106
        createRegle(I1, Arrays.asList(EXPRTERM2, t(Tag.AFFECT), expr, t(Tag.POINTV))); //r107
        createRegle(EXPROPT, Arrays.asList()); //r108
        createRegle(EXPROPT, Arrays.asList(expr)); //r109
        createRegle(ELSIF, Arrays.asList()); //r110
        createRegle(ELSIF, Arrays.asList(t(Tag.ELSIF), expr, t(Tag.THEN), instr, instrEtoile, ELSIF)); //r111
        createRegle(ELSE, Arrays.asList()); // r112
        createRegle(ELSE, Arrays.asList(t(Tag.ELSE), instr, instrEtoile)); //r113
        createRegle(reverseOpt, Arrays.asList()); // r114
        createRegle(reverseOpt, Arrays.asList(t(Tag.REVERSE))); // r115
    }  

    public Terminal t(int tag){
        return terminaux.get(tag);
    }

    public String getNonTerminal(int idRegle) {
        //TODO
        return("" + idRegle);
    }

    public String getTerminal(int tag) {
        //TODO
        return("" + tag);
    }

    public void addPile(Component component) {
        if (component != null) {
            this.pile.add(component);
        }
    }

    public Component removePile() {
        if (this.pile.isEmpty()) {
            return null; 
        }
        return this.pile.remove();
    }

    public Component fonctionSemantique(int regle, NonTerminalExpression noeud){
        switch(regle){
            case 0:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                Node node=new Node("Entete");
                node.addChild(noeud.getFils(7).interpret());
                node.addChild(noeud.getFils(9).interpret());
                Node nodeFils1=new Node("block");
                addPile(noeud.getFils(11).interpret());
                if (noeud.getFils(12).interpret() instanceof Node){
                    nodeFils1.addChild(noeud.getFils(12).interpret().getFils(0));
                    nodeFils1.addChild(noeud.getFils(12).interpret().getFils(1));
                }
                else {
                    nodeFils1.addChild(noeud.getFils(12).interpret());
                }
                node.addChild(nodeFils1);
                node.addChild(noeud.getFils(14).interpret());
                if (!this.pile.isEmpty()) {
                    System.out.println(this.pile.size() + " elements restants dans la pile !");
                }
                return node;

            case 1:
                return null;
                
            case 2:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Declarations");
                node.addChild(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret()!=null){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                return node;
                
            case 3:
                return removePile();

            case 4:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Instanciation");
                node.addChild(removePile());
                addPile(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                return node;
                

            case 5:
                return null;

            case 6:
                return noeud.getFils(0).interpret();

            case 7:
                addPile(noeud.getFils(1).interpret());
                return noeud.getFils(2).interpret();
                
            case 8:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Declaration");
                addPile(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                node.addChild(noeud.getFils(3).interpret());
                addPile(node);
                return noeud.getFils(4).interpret();
                
            case 9:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Procedure");
                node.addChild(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                node.addChild(noeud.getFils(4).interpret());
                nodeFils1=new Node("Block");
                addPile(noeud.getFils(6).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    nodeFils1.addChild(noeud.getFils(7).interpret().getFils(0));
                    nodeFils1.addChild(noeud.getFils(7).interpret().getFils(1));
                }
                else {
                    nodeFils1.addChild(noeud.getFils(7).interpret());
                }
                node.addChild(nodeFils1);
                node.addChild(noeud.getFils(9).interpret());
                return node;

            case 10:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Fonction");
                node.addChild(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                node.addChild(noeud.getFils(4).interpret());
                node.addChild(noeud.getFils(6).interpret());
                nodeFils1=new Node("Block");
                addPile(noeud.getFils(8).interpret());
                if (noeud.getFils(9).interpret() instanceof Node){
                    nodeFils1.addChild(noeud.getFils(9).interpret().getFils(0));
                    nodeFils1.addChild(noeud.getFils(9).interpret().getFils(1));
                }
                else {
                    nodeFils1.addChild(noeud.getFils(9).interpret());
                }
                node.addChild(nodeFils1);
                node.addChild(noeud.getFils(11).interpret());
                return node;

            case 11:
                return removePile();

            case 12:
                node=new Node("Is");
                node.addChild(removePile());
                node.addChild(noeud.getFils(1).interpret());
                return node;
                
            case 13:
                node=new Node("Access");
                node.addChild(noeud.getFils(1).interpret());
                return node;
                
            case 14:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Record");
                node.addChild(noeud.getFils(1).interpret());
                if (noeud.getFils(2).interpret() instanceof Node){
                    node.addChild(noeud.getFils(2).interpret().getFils(0));
                    node.addChild(noeud.getFils(2).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(2).interpret());
                }
                return node;
                
            case 15:
                return removePile();
                
            case 16:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Champ");
                node.addChild(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                return node;

            case 17:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Suite d'ident");
                node.addChild(removePile());
                addPile(noeud.getFils(1).interpret());
                if (noeud.getFils(2).interpret() instanceof Node){
                    node.addChild(noeud.getFils(2).interpret().getFils(0));
                    node.addChild(noeud.getFils(2).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(2).interpret());
                }
                return node;

            case 18:
                return removePile();
                
            case 19:
                return removePile();
                
            case 20:
                node=new Node("Expression");
                node.addChild(removePile());
                node.addChild(noeud.getFils(1).interpret());
                return node;
                
            case 21:
                return noeud.getFils(0).interpret();

            case 22:
                return null;
                
            case 23:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Champ");
                addPile(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                node.addChild(noeud.getFils(3).interpret());
                return node;
                
            case 24:
                return noeud.getFils(0).interpret();
                
            case 25:
                node=new Node("Access");
                node.addChild(noeud.getFils(1).interpret());
                return node;

            case 26:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("Parametres");
                node.addChild(noeud.getFils(1).interpret());
                if (noeud.getFils(2).interpret() instanceof Node){
                    node.addChild(noeud.getFils(2).interpret().getFils(0));
                    node.addChild(noeud.getFils(2).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(2).interpret());
                }
                return node;

            case 27:
                return removePile();
                
            case 28:
                node=new Node("tmp");
                node.addChild(noeud.getFils(1).interpret());
                if (noeud.getFils(2).interpret() instanceof Node){
                    node.addChild(noeud.getFils(2).interpret().getFils(0));
                    node.addChild(noeud.getFils(2).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(2).interpret());
                }
                return node;

            case 29:
            //peut etre modifie pour les mettre a la suite (voir 28 et 94)
                node=new Node("parametres");
                addPile(noeud.getFils(0).interpret());
                if (noeud.getFils(1).interpret() instanceof Node){
                    node.addChild(noeud.getFils(1).interpret().getFils(0));
                    node.addChild(noeud.getFils(1).interpret().getFils(1));
                }
                else {
                    node.addChild(noeud.getFils(1).interpret());
                }
                node.addChild(noeud.getFils(3).interpret());
                node.addChild(noeud.getFils(4).interpret());
                return node;
                
            case 30:
                return null;

            case 31:
                return noeud.getFils(0).interpret();

            case 32:
                return noeud.getFils(1).interpret();
                
            case 33:
                return null;

            case 34:
                return null;

            case 35:
                return noeud.getFils(0).interpret();

            case 36:
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 37:
                node=new Node("Ou");
                node.addChild(removePile());
                node.addChild(noeud.getFils(1).interpret());
                return node;

            case 38:
                return removePile();
                
            case 39:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 40:
                //a voir
                addPile(noeud.getFils(1).interpret());
                return noeud.getFils(2).interpret();

            case 41:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 42:
                //modifiee
                node = new Node("And");
                node.addChild(pile.remove());
                node.addChild(noeud.getFils(1).interpret());
                return node;

            case 43:
                return removePile();
                
            case 44:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();
    
            case 45:
                //a voir
                addPile(noeud.getFils(1).interpret());
                return noeud.getFils(2).interpret();

            case 46:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 47:
                //modifiee
                node = new Node("Negation");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;
                

            case 48:
                return removePile();
                
            case 49:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();
   
            case 50:
                //modifiee
                node = new Node("Egalite");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 51:
                //modifiee
                node = new Node("Inegalite");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 52:
                return removePile();

            case 53:
                //a voir
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 54:
                //modifiee
                node = new Node("Sup");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 55:
                //modifiee
                node = new Node("SupEgal");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 56:
                //modifiee
                node = new Node("Inf");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 57:
                //modifiee
                node = new Node("InfEgal");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 58:
                return removePile();


            case 59:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 60:
                //Création du noeud
                node = new Node("Somme");
                //ajout du premier fils (dépile)
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());//pour empiler
                //ajout du deuxième fils
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 61:
                node=new Node("Soustraction");
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());//pour empiler
                //ajout du deuxième fils
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 62:
                return pile.remove();

            case 63:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 64:
                //Création du noeud
                node = new Node("Multiplication");
                //ajout du premier fils (dépile)
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());//pour empiler
                //ajout du deuxième fils
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 65:
                //Création du noeud
                node = new Node("Quotient division");
                //ajout du premier fils (dépile)
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());//pour empiler
                //ajout du deuxième fils
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 66:
                //Création du noeud
                node = new Node("Reste division");
                //ajout du premier fils (dépile)
                node.addChild(pile.remove());
                addPile(noeud.getFils(1).interpret());//pour empiler
                //ajout du deuxième fils
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 67:
                return pile.remove();

            case 68:
                return noeud.getFils(0).interpret();

            case 69:
                return noeud.getFils(0).interpret();

            case 70:
                node=new Node("Opposé");
                node.addChild(noeud.getFils(1).interpret());
                return node;

            case 71:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 72:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 73:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 74:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 75:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 76:
                addPile(noeud.getFils(1).interpret());//pour empiler
                return noeud.getFils(3).interpret();//on interprete la suite

            case 77:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 78:
                node=new Node("new");
                node.addChild(noeud.getFils(1).interpret());
                addPile(node);
                return noeud.getFils(2).interpret();

            case 79:
                node=new Node("val");
                node.addChild(noeud.getFils(2).interpret());
                addPile(node);
                return noeud.getFils(4).interpret();

            case 80:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 81:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 82:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 83:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 84:
                addPile(noeud.getFils(0).interpret());//pour empiler
                return noeud.getFils(1).interpret();//on interprete la suite

            case 85:
                addPile(noeud.getFils(1).interpret());//pour empiler
                return noeud.getFils(3).interpret();//on interprete la suite

            case 86:
                node=new Node("new");
                node.addChild(noeud.getFils(1).interpret());
                addPile(node);
                return noeud.getFils(2).interpret();

            case 87:
                node=new Node("val");
                node.addChild(noeud.getFils(2).interpret());
                addPile(node);
                return noeud.getFils(4).interpret();

            case 88:
                return noeud.getFils(0).interpret();

            case 89:
                node=new Node("Appel methode");
                node.addChild(removePile());
                Node nodeFilsDroit=new Node("param");
                nodeFilsDroit.addChild(noeud.getFils(1).interpret());
                if (noeud.getFils(2)!=null){
                    nodeFilsDroit.addChild(noeud.getFils(2).interpret().getFils(0));
                    nodeFilsDroit.addChild(noeud.getFils(2).interpret().getFils(1));
                }
                else{
                    nodeFilsDroit.addChild(noeud.getFils(2).interpret());
                }
                node.addChild((nodeFilsDroit));
                addPile(node);
                return noeud.getFils(4).interpret();
                
            case 90:
                node=new Node("acces");
                node.addChild(removePile());
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 91:
                return removePile();

            case 92:
                node=new Node("acces");
                node.addChild(removePile());
                addPile(noeud.getFils(1).interpret());
                node.addChild((noeud.getFils(2).interpret()));
                return node; 

            case 93:
                return null;

            case 94:
                node=new Node("tmp");
                node.addChild(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 95:
                addPile(noeud.getFils(0).interpret());
                return noeud.getFils(1).interpret();

            case 96:
                node=new Node("Affectation");
                Node nodeFilsGauche=new Node("Terme gauche");
                nodeFilsDroit=new Node("Terme droit");
                nodeFilsGauche.addChild(noeud.getFils(0).interpret());
                nodeFilsDroit.addChild(noeud.getFils(2).interpret());
                node.addChild(nodeFilsGauche);
                node.addChild(nodeFilsDroit);
                return node;

            case 97:
                node=new Node("Return");
                node.addChild(noeud.getFils(1).interpret());
                return node;

            case 98:
                node=new Node("Block");
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 99:
                node=new Node("If");
                nodeFils1=new Node("Cond");
                nodeFils1.addChild(noeud.getFils(1).interpret());
                node.addChild(nodeFils1);
                Node nodeFils2=new Node("Block");
                addPile(noeud.getFils(3).interpret());
                nodeFils2.addChild(noeud.getFils(4).interpret());
                node.addChild(nodeFils2);
                node.addChild(noeud.getFils(5).interpret());
                node.addChild(noeud.getFils(6).interpret());
                return node;   

            case 100:
            //a verifier avec pierre
                node=new Node("For");
                nodeFils1=new Node("Variable");
                nodeFils1.addChild(noeud.getFils(1).interpret());
                node.addChild(nodeFils1);
                nodeFils2=new Node("Intervalle");
                nodeFils2.addChild(noeud.getFils(3).interpret());
                nodeFils2.addChild(noeud.getFils(4).interpret());
                nodeFils2.addChild(noeud.getFils(7).interpret());
                node.addChild(nodeFils2);
                Node nodeFils3=new Node("Block");
                addPile(noeud.getFils(9).interpret());
                nodeFils3.addChild(noeud.getFils(10).interpret());
                node.addChild(nodeFils3);
                return node;

            case 101:
                node=new Node("While");
                nodeFilsGauche=new Node("Cond");
                nodeFilsGauche.addChild(noeud.getFils(1).interpret());
                node.addChild(nodeFilsGauche);
                nodeFilsDroit=new Node("Block");
                addPile(noeud.getFils(3).interpret());
                nodeFilsDroit.addChild(noeud.getFils(4).interpret());
                node.addChild(nodeFilsDroit);
                return node;

            case 102:
                node=new Node("Affectation");
                nodeFilsGauche=new Node("Terme gauche");
                nodeFilsGauche.addChild(removePile());
                node.addChild(nodeFilsGauche);
                nodeFilsDroit=new Node("Terme droit");
                nodeFilsDroit.addChild(noeud.getFils(1).interpret());
                node.addChild(nodeFilsDroit);
                return node;

            case 103:
                return removePile();

            case 104:
                node=new Node("Appel methode");
                node.addChild(removePile());
                nodeFilsDroit=new Node("Param");
                nodeFilsDroit.addChild(noeud.getFils(1).interpret());
                nodeFilsDroit.addChild(noeud.getFils(2).interpret());
                node.addChild(nodeFilsDroit);
                addPile(node);
                return noeud.getFils(2).interpret();

            case 105:
                node=new Node("Affectation");
                nodeFilsGauche=new Node("Terme gauche");
                nodeFilsGauche.addChild(noeud.getFils(0).interpret());
                node.addChild(nodeFilsGauche);
                nodeFilsDroit=new Node("Terme droit");
                nodeFilsDroit.addChild(noeud.getFils(2).interpret());
                node.addChild(nodeFilsDroit);
                return node;
                
            case 106:
                return removePile();

            case 107:
                node=new Node("Affectation");
                nodeFilsGauche=new Node("Terme gauche");
                nodeFilsGauche.addChild(noeud.getFils(0).interpret());
                node.addChild(nodeFilsGauche);
                nodeFilsDroit=new Node("Terme droit");
                nodeFilsDroit.addChild(noeud.getFils(2).interpret());
                node.addChild(nodeFilsDroit);
                return node;

            case 108:
                return null;

            case 109:
                return noeud.getFils(0).interpret();

            case 110:
                return null;

            case 111:
                node=new Node("Elsif");
                nodeFils1=new Node("Cond");
                nodeFils1.addChild(noeud.getFils(1).interpret());
                node.addChild(nodeFils1);
                nodeFils2=new Node("Block");
                addPile(noeud.getFils(3).interpret());
                nodeFils2.addChild(noeud.getFils(4).interpret());
                node.addChild(nodeFils2);
                node.addChild(noeud.getFils(5).interpret());
                return node;

            case 112:
                return null;

            case 113:
                node=new Node("Else");
                addPile(noeud.getFils(1).interpret());
                node.addChild(noeud.getFils(2).interpret());
                return node;

            case 114:
                return null;

            case 115:
                return noeud.getFils(0).interpret();

            default:
                return null;
            
        }
    }
}