import java.util.*;
class Launch {
    static char[][] board = new char[3][3];

    void initBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    static void display() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }
    static void placemark(int row, int col, char mark){
        if(row >=0 && row <=2 && col>=0 && col <=2){
            board[row][col] = mark;
        }
        else{
            System.out.println("Invalid input");
        }

    }
    static boolean colwin(){
        for(int j=0;j<=2;j++){
            if(board[0][j]!= ' ' && board[0][j]==board[1][j] && board[1][j] == board[2][j]){
                return true;
            }
        }
        return false;
    }

    static boolean rowwin(){
        for(int i=0 ;i<=2;i++){
            if(board[i][0]!=' ' && board[i][0] == board[i][1] && board[i][1] == board[i][2]){
                return true;
            }
        }
        return false;
    }
    static boolean diagwin(){
        if(board[0][0]!=' ' && board[0][0] == board[1][1] && board[1][1] == board[2][2] || board[0][2]!=' '&& board[0][2]==board[1][1] && board[1][1] == board[2][0]){
            return true;
        }
        else{
            return false;
        }
    }

}

abstract class Player{
    String name;
    char mark;
    abstract void move();
    boolean validmove(int row, int col){
        if(row >=0 && row <=2 && col>=0 && col<=2){
            if(Launch.board[row][col]==' '){
                return true;
            }
        }
        return false;
    }

}

class HumanPlayer extends Player{
    HumanPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }

    void move(){
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do{
            System.out.println("Enter the row and the column: ");
             row=sc.nextInt();
            col = sc.nextInt();
        }while(!validmove(row,col));
        Launch.placemark(row,col,mark);
    }
}
class AIPlayer extends Player{
    AIPlayer(String name, char mark){
        this.name = name;
        this.mark = mark;
    }
    void move(){
        Scanner sc = new Scanner(System.in);
        int row;
        int col;
        do{
            Random r = new Random();
            row = r.nextInt(3);
            col = r.nextInt(3);
        }while(!validmove(row,col));
        Launch.placemark(row,col,mark);
    }
}



public class Main{
    public static void main(String[] args) {
        Launch l = new Launch();
        l.initBoard();
        HumanPlayer p1 = new HumanPlayer("Rimi",'X');
        AIPlayer p2 = new AIPlayer("Computer" , 'O');
        Player cp;
        cp = p1;
        while(true){
            System.out.println(cp.name+"'s turn");
            cp.move();
            Launch.display();
            if(Launch.rowwin() || Launch.colwin() || Launch.diagwin()){
                System.out.println(cp.name +" has won");
                break;
            }
            else{
                if(cp == p1){
                    cp = p2;
                }
                else{
                    cp = p1;
                }
            }
        }
    }
}
