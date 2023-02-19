import java.io.*; 
import java.lang.*; 
import java.util.*;
import java.util.Scanner;

public class Chess{

    static ArrayList<String> chessboard = new ArrayList<String>();
    static HashMap<String, String> whitePieces = new HashMap<String, String>();
    static HashMap<String, String> blackPieces = new HashMap<String, String>();
    static HashMap<String, String> capturedPiece = new HashMap<String, String>();

    //static HashMap<String, String> 

    Scanner input = new Scanner(System.in);

    String figure; String from; String to;
    boolean whiteMove;

    public Chess(){
        chessboard();
        setPieces();
        whiteMove = true;
        game();
    }

    public void chessboard(){                //---------initialize board-----------
        for(char x = 'a'; x <= 'h'; x++){
            for(char y = '1'; y <= '8'; y++){
                String sq = x+""+y;
                chessboard.add(sq);
            }
        }
    }
    public void setPieces(){
        whitePieces.put("a7", "rook");      blackPieces.put("a8", "rook");
        whitePieces.put("d6", "knight");    blackPieces.put("b8", "knight");
        whitePieces.put("c1", "bishop");    blackPieces.put("c8", "bishop");
        whitePieces.put("d1", "queen");     blackPieces.put("d8", "queen");
        whitePieces.put("e1", "king");      blackPieces.put("e8", "king");
        whitePieces.put("f1", "bishop");    blackPieces.put("f8", "bishop");
        whitePieces.put("g1", "knight");    blackPieces.put("g8", "knight");
        whitePieces.put("h1", "rook");      blackPieces.put("h8", "rook");

        //whitePieces.put("a2", "rook");

        //whitePieces.put("b6", "pawn");      blackPieces.put("a7", "pawn");
        //whitePieces.put("b2", "pawn");      blackPieces.put("b7", "pawn");
        //whitePieces.put("c2", "pawn");      blackPieces.put("c7", "pawn");
        //whitePieces.put("d2", "pawn");      blackPieces.put("d7", "pawn");
        //whitePieces.put("e2", "pawn");      blackPieces.put("e7", "pawn");
        //whitePieces.put("f2", "pawn");      blackPieces.put("f7", "pawn");
        //whitePieces.put("g2", "pawn");      blackPieces.put("g7", "pawn");
        //whitePieces.put("h2", "pawn");      blackPieces.put("h7", "pawn");
    }

    public boolean moveValidation(String piece, String first, String second){

        char x1 = first.charAt(0); char x2 = second.charAt(0); char y1 = first.charAt(1); char y2 = second.charAt(1);

        if (((x1==x2) && (y1==y2))) {
            System.out.println("Squares are the same");
            return false;
        }
        else {

        if ((piece=="queen") && (((x1==x2) || (y1==y2)) || (((x1!=x2) && (y1!=y2)) && (Math.abs(x1-x2) == Math.abs(y1-y2))))) {
            return true;
        }
        else if ((piece=="rook") && ((x1==x2) || (y1==y2))) {
            return true;
        }
        else if ((piece=="bishop") && ((x1!=x2) && (y1!=y2)) && (Math.abs(x1-x2) == Math.abs(y1-y2))) { 
            return true;
        }
        else if ((piece=="king") && (((x1==x2) && (Math.abs(y1-y2)==1)) || ((y1==y2) && (Math.abs(x1-x2)==1)) || ((Math.abs(x1-x2)==1 && Math.abs(y1-y2)==1)))) {
            return true;
        }
        else if ((piece=="knight") && ((((Math.abs(x1-x2)==2) && Math.abs(y1-y2)==1)) || ((Math.abs(x1-x2)==1) && Math.abs(y1-y2)==2))) {
            return true;
        }
        else if ((piece=="pawn") && ((((whiteMove==true) && ((y2-y1)==1) && (x1==x2) && (isEmpty(second)==true))) || ((Math.abs(y1-y2)==1) && (Math.abs(x1-x2)==1) && isEmpty(second)==false)))  {
            return true;
            //piony do ustawienia - ruch w tyl vs w przod
        }
        else {
            //System.out.println("move is not valid: " + piece + " from " + first + " to " + second); 
        return false;}
        }
    }

    public boolean isEmpty(String square){
        if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
            return false; }
        else {
        return true; }
    }

    public boolean freeWay(String first, String second, String piece){

        int vector; String square;

        if (piece=="knight") {return true;}
        
        else if ((first.charAt(0) == second.charAt(0)) && (first.charAt(1) < second.charAt(1))){
            vector = 1;
            for(char x = (char)(first.charAt(1) + vector); x != second.charAt(1); x+=vector){
                square = (first.charAt(0) + "" + x);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) == second.charAt(0)) && (first.charAt(1) > second.charAt(1))){
            vector = -1;
            for(char x = (char)(first.charAt(1) + (char)vector); x != second.charAt(1); x+=vector){
                square = (first.charAt(0) + "" + x);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) < second.charAt(0)) && (first.charAt(1) == second.charAt(1))){
            vector = 1;
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                square = (x + "" + first.charAt(1));
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) > second.charAt(0)) && (first.charAt(1) == second.charAt(1))){
            vector = -1;
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                square = (x + "" + first.charAt(1));
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) < second.charAt(0)) && (first.charAt(1) < second.charAt(1))){
            vector = 1;
            char y = first.charAt(1);
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                y++;
                square = (x + "" + y);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) < second.charAt(0)) && (first.charAt(1) > second.charAt(1))){
            vector = 1;
            char y = first.charAt(1);
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                y--;
                square = (x + "" + y);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) > second.charAt(0)) && (first.charAt(1) > second.charAt(1))){
            vector = -1;
            char y = first.charAt(1);
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                y--;
                square = (x + "" + y);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        else if ((first.charAt(0) > second.charAt(0)) && (first.charAt(1) < second.charAt(1))){
            vector = -1;
            char y = first.charAt(1);
            for(char x = (char)(first.charAt(0) + vector); x != second.charAt(0); x+=vector){
                y++;
                square = (x + "" + y);
                if ((whitePieces.containsKey(square)) || (blackPieces.containsKey(square))) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getPiece(boolean whiteMove, String square){
        String piece;
            if (whiteMove == true) {piece = whitePieces.get(square);}
            else {piece = blackPieces.get(square);}
        return piece;
    }

    public String getKing(boolean whiteMove){
        String square = ""; Iterator it;
        
        if (whiteMove == true) {
            it = whitePieces.entrySet().iterator(); }
        else {
            it = blackPieces.entrySet().iterator(); }

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            if ((String)(pair.getValue()) == "king") { square = (String)(pair.getKey()); break; }
        }
        return square;
    }

    public boolean isCheck(boolean whiteMove){
        String piece; String square; String target; Iterator it;
        
        if (whiteMove == true) {
            target = getKing(whiteMove);
            it = blackPieces.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                    square = (String)(pair.getKey());
                    piece = (String)(pair.getValue());
                    if ((moveValidation(piece, square, target)) && (freeWay(square, target, piece))) {
                        //System.out.println("white is under check from " + square + " by " + piece);
                        return true;
                    }
            }
        }
        else if (whiteMove == false) {
            target = getKing(whiteMove);
            it = whitePieces.entrySet().iterator();

            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry)it.next();
                    square = (String)(pair.getKey());
                    piece = (String)(pair.getValue());
                    if ((moveValidation(piece, square, target)) && (freeWay(square, target, piece))) {
                        //System.out.println("black is under check from " + square + " by " + piece);
                        return true;
                    }
            }
        }
        return false;
    }

    public boolean move(boolean whiteMove, String from, String to, String piece){

        if ((whiteMove == true) && (!whitePieces.containsKey(to)) && (moveValidation(piece, from, to)) && (freeWay(from, to, piece))) {

            if (blackPieces.containsKey(to)){
                capture(whiteMove, to);
            }
            whitePieces.put(to, piece);
            whitePieces.remove(from);
            return true;
        }
        else if ((whiteMove == false) && (!blackPieces.containsKey(to)) && (moveValidation(piece, from, to)) && (freeWay(from, to, piece))) {

            if ((whitePieces.containsKey(to))) {
                capture(whiteMove, to);
            }
            blackPieces.put(to, piece);
            blackPieces.remove(from);
            return true;
        }
        else return false;                               
    }

    public boolean tryPosition(boolean whiteMove, String from, String to){

        if (isLegal(whiteMove) == false) {
            if (whiteMove == true) {
                whitePieces.put(from, whitePieces.remove(to));
                if (capturedPiece.size() > 0) {
                    blackPieces.put(to, capturedPiece.remove(to));
                }
            }
            else if (whiteMove == false){
                blackPieces.put(from, blackPieces.remove(to));
                if (capturedPiece.size() > 0) {
                    whitePieces.put(to, capturedPiece.remove(to));
                }
            }
            return false;
    }
    else return true; 
    }
    
    public boolean isLegal(boolean whiteMove){

        if (isCheck(whiteMove) == true) {
            return false;
        }
        else return true; 
    }

    public void capture(boolean whiteMove, String square){
        if (whiteMove == true) {
            capturedPiece.put(square, blackPieces.remove(square));
        }
        else if (whiteMove == false) {
            capturedPiece.put(square, whitePieces.remove(square));
        }
    }

    public void game(){

        for (int i = 0; i < 20; i++){

            from = input.nextLine();
            to = input.nextLine();
            figure = getPiece(whiteMove, from);

            if (((whiteMove == true) && (!whitePieces.containsKey(from))) || ((whiteMove == false) && (!blackPieces.containsKey(from)))) {
                System.out.println("Wrong side or wrong move");
                continue;
            }
            else if ((move(whiteMove, from, to, figure) == true) && (tryPosition(whiteMove, from, to)) == true){
                whiteMove = !whiteMove;
                capturedPiece.clear();
            }
            piecesIteration();

            isCheckmate(whiteMove);
            
        }
        input.close();
    }

    public void isCheckmate(boolean whiteMove){
        Iterator it; String piece;

        ArrayList<String> checkers = new ArrayList<String>();
        
        if ((whiteMove) == true) {it = whitePieces.entrySet().iterator();}
        else {it = blackPieces.entrySet().iterator();}

        if((isCheck(whiteMove) == true) && (whiteMove == false)) {
            
            System.out.println("wszystkie mozliwe ruchy czarnych");

            for (String key : blackPieces.keySet()) {
                //System.out.println(key);
                checkers.add(key);
                //System.out.println(checkers.size());
            }

            for (String from : checkers){
                piece = blackPieces.get(from);

                for (String to : chessboard){
                        //System.out.println(from + " --> " + to);
                        //piecesIteration();
                        //System.out.println("-------------------");
                        if ((move(whiteMove, from, to, piece) == true)) {
                            
                            if (isLegal(whiteMove)){
                                System.out.println(piece + from + to + " saves checkmate");
                            }

                                if (whiteMove == false) {
                                    blackPieces.put(from, blackPieces.remove(to));
                                    if (capturedPiece.size() > 0) {
                                        whitePieces.put(to, capturedPiece.remove(to));
                                    }
                                }
                                else if (whiteMove == true) {
                                    whitePieces.put(from, whitePieces.remove(to));
                                    if (capturedPiece.size() > 0) {
                                        blackPieces.put(to, capturedPiece.remove(to));
                                    }
                                }
                        }
                        else {
                            //System.out.println("not legal");
                        }
                }
            }

        }

        else if((isCheck(whiteMove) == true) && (whiteMove == true)) {
            
            System.out.println("wszystkie mozliwe ruchy bialych");

            for (String key : whitePieces.keySet()) {
                //System.out.println(key);
                checkers.add(key);
                //System.out.println(checkers.size());
            }

            for (String from : checkers){
                piece = whitePieces.get(from);

                for (String to : chessboard){
                        //System.out.println(from + " --> " + to);
                        //piecesIteration();
                        //System.out.println("-------------------");
                        if ((move(whiteMove, from, to, piece) == true)) {
                            
                            if (isLegal(whiteMove)){
                                System.out.println(piece + from + to + " saves checkmate");
                            }

                                if (whiteMove == true) {
                                    whitePieces.put(from, whitePieces.remove(to));
                                    if (capturedPiece.size() > 0) {
                                        blackPieces.put(to, capturedPiece.remove(to));
                                    }
                                }
                                else if (whiteMove == false) {
                                    blackPieces.put(from, blackPieces.remove(to));
                                    if (capturedPiece.size() > 0) {
                                        whitePieces.put(to, capturedPiece.remove(to));
                                    }
                                }
                        }
                        else {
                            //System.out.println("not legal");
                        }
                }
            }

        }

        else {System.out.println("nie ma szacha");}

    }

public static void main(String[] args){
    
    new Chess();

}
    public void piecesIteration(){

        Iterator it; String square; String piece;
        it = whitePieces.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
                square = (String)(pair.getKey());
                piece = (String)(pair.getValue());
                    System.out.println("|white| " + square + " = " + piece);
            //it.remove();
        }

        it = blackPieces.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
                square = (String)(pair.getKey());
                piece = (String)(pair.getValue());
                    System.out.println("|black| " + square + " = " + piece);
        }
    }
}

            //it.remove(); // avoids a ConcurrentModificationException