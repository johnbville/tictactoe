/*
 *  John Bettonville
 *  2002-10-09
 *
 *  TicTacToe.java
 *
 *  This program allows a user to play
 *  Tic Tac Toe against a computer opponent.
 */

import java.io.*;         //import the library java.io.*
import java.util.Random;  //import the class Random

//  Define the class "TicTacToe".
class TicTacToe {
  public static void main(String[] args) {

    /*  This array represents the positions on the board,
     *  starting from the top left, moving down through the bottom 
     *  right.  For example: m[4] represents the center space,
     *  m[8] is lower row, righthand side.
     */
    int[] m = {0, 0, 0, 0, 0, 0, 0, 0, 0};

    /*  This array determines whether a space has been taken.
     *  A "true" value indicates that it is still available.
     */
    boolean[] mt = {true, true, true, true, true, true, true, true, true};

    /*  These variables are various operators throught the program.
     *  n and q have to do with player and computer moves.
     *  numberOfTurns refers to the amount of turns that have passed,
     *  and cannot ever go higher than 9 without causing the main
     *  method to end in a stalemate.  The booleans "win" and "turn"
     *  determine whether the game has ended and who's turn it is.
     */
    int numberOfTurns = 0;
    int n = 0;
    int q = 0;
    boolean win = false;
    boolean turn = true;

    //  This is the welcome message and gameplay instructions.
    System.out.println("");
    System.out.println("Welcome to Tic Tac Toe.");
    System.out.println("Type a number to place an x.");
    System.out.println("You can't put an x in an occupied spot.");
    System.out.println("Three x's in a row and you win.");
    System.out.println("Three o's in a row, and I win!\n");
 
    /*  This is the main body of the program.  It contains
     *  conditions for continuing play, a record of which
     *  player has moved where, and whether or not the game
     *  has ended.  If at any point a player enters an invalid
     *  input, such as a string or an out-of-bounds integer,
     *  an error message is displayed and the while loop
     *  handling turns will restart.
     */
    while(win == false) {

      //  This while loop handles the human player's turns.
      while(turn == true) {
        n = playerMove(m, mt);
        try {
            m[n] = 1;
        } catch(ArrayIndexOutOfBoundsException a) {
            System.out.println("Not a valid entry.\n");
            continue;
        }
        if(n < 0 && n > 8) {
          System.out.println("Not a valid entry.\n");
        } else {
            mt[n] = false;
        }
        numberOfTurns++;
        turn = !turn;
      }

      //  This contains the win conditions for the human player.
      if (m[0]+m[1]+m[2]==3 ||
          m[3]+m[4]+m[5]==3 ||
          m[6]+m[7]+m[8]==3 ||
          m[0]+m[3]+m[6]==3 ||
          m[1]+m[4]+m[7]==3 ||
          m[2]+m[5]+m[8]==3 ||
          m[0]+m[4]+m[8]==3 ||
          m[2]+m[4]+m[6]==3) {
        System.out.println("");
        printBoard(m);
        System.out.println("You win!\n");
        win = true;
        continue;
      }

      //  This handles tie games.
      if(numberOfTurns > 8) {
        System.out.println("");
        printBoard(m);
        System.out.println("It's a draw.");
        System.out.println("Game over.\n");
        win = true;
        continue;
      }

      //  This handles computer moves and win conditions.
      while(turn == false) {
        q = computerMove(m, mt);
        m[q] = 4;
        numberOfTurns++;
        turn = !turn;
      }
      if (m[0]+m[1]+m[2]==12 ||
          m[3]+m[4]+m[5]==12 ||
          m[6]+m[7]+m[8]==12 ||
          m[0]+m[3]+m[6]==12 ||
          m[1]+m[4]+m[7]==12 ||
          m[2]+m[5]+m[8]==12 ||
          m[0]+m[4]+m[8]==12 ||
          m[2]+m[4]+m[6]==12) {
        printBoard(m);
        System.out.println("I win!\n");
        win = true;
        continue;
      }
    }
  }


  //  METHODS


  //  This method allows the player to move.
  static int playerMove(int[] m, boolean[] mt) {
    int pMove = 0;
    printBoard(m);
    System.out.println("Your move:");
    pMove = getInput();
    if(pMove > 0 && pMove < 10) {
      if(mt[pMove - 1] == true)
        return (pMove - 1);
      else
        return -1;
    } else
        return -1;
  }


  /*  This contains the instructions for the computer's moves.
   *  The set of instructions will tell the computer to block
   *  when the player has two in a row and make a winning
   *  move when the computer has two in a row.  If the center
   *  space is not occupied, the computer will move there.
   *  If none of these conditions is true, the computer will
   *  randomly choose an available move.
   */
  static int computerMove(int[] m, boolean[] mt) {
    System.out.println("");
    printBoard(m);
    int cMove = 4;
    if(mt[4] == true) {
      cMove = 4;
      mt[cMove] = false;
    } else {
        while(mt[cMove] == false) {
          if ((m[0] + m[1] == 2) ||
              (m[0] + m[1] == 8) ||
              (m[6] + m[4] == 2) ||
              (m[6] + m[4] == 8) ||
              (m[5] + m[8] == 2) ||
              (m[5] + m[8] == 8)) {
            cMove = 2;
            if(mt[cMove] == true)
                continue;
          }
          if ((m[1] + m[2] == 2) ||
              (m[1] + m[2] == 8) ||
              (m[4] + m[8] == 2) ||
              (m[4] + m[8] == 8) ||
              (m[3] + m[6] == 2) ||
              (m[3] + m[6] == 8)) {
            cMove = 0;
            if(mt[cMove] == true)
              continue;
          }
          if ((m[0] + m[3] == 2) ||
              (m[0] + m[3] == 8) ||
              (m[2] + m[4] == 2) ||
              (m[2] + m[4] == 8) ||
              (m[7] + m[8] == 2) ||
              (m[7] + m[8] == 8)) {
            cMove = 6;
            if(mt[cMove] == true)
              continue;
          }
          if ((m[2] + m[5] == 2) ||
              (m[2] + m[5] == 8) ||
              (m[0] + m[4] == 2) ||
              (m[0] + m[4] == 8) ||
              (m[6] + m[7] == 2) ||
              (m[6] + m[7] == 8)) {
            cMove = 8;                   
            if(mt[cMove] == true)
              continue;
          }
          if ((m[0] + m[2] == 2) ||
              (m[0] + m[2] == 8) ||
              (m[4] + m[7] == 2) ||
              (m[4] + m[7] == 8)) {
            cMove = 1;
            if(mt[cMove] == true)
              continue;
          }
          if ((m[0] + m[6] == 2) ||
              (m[0] + m[6] == 8) ||
              (m[4] + m[5] == 2) ||
              (m[4] + m[5] == 8)) {
            cMove = 3;
            if(mt[cMove] == true)
              continue;
          }
          if ((m[2] + m[8] == 2) ||
              (m[2] + m[8] == 8) ||
              (m[3] + m[4] == 2) ||
              (m[3] + m[4] == 8)) {
            cMove = 5;
            if(mt[cMove] == true)
              continue;
          }
          if ((m[1] + m[4] == 2) ||
              (m[1] + m[4] == 8) ||
              (m[6] + m[8] == 2) ||
              (m[6] + m[8] == 8)) {
            cMove = 7;
            if(mt[cMove] == true)
              continue;
          }
          Random r = new Random();
          int aRandomNumber = r.nextInt(9);
          cMove = aRandomNumber;
      }
    }
    System.out.println("My move:");
    System.out.println("");
    mt[cMove] = false;
    return cMove;
  }


  //  This method gets integers from user input.
  static int getInput() {
    BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
    String line = "";
    int input = 0;
    Integer I = new Integer(0);
    try {
        line = buf.readLine();
    } catch(IOException i) {
        i.printStackTrace();
    }
    try {
        I = new Integer(line);
    } catch(NumberFormatException n) {
        ;
    }
    input = I.intValue();
    return input;
  }


  //  This method prints the current state of the board.
  static void printBoard(int[] m) {
    System.out.println(xando(m[0], '1') + " " + xando(m[1], '2')
      + " " + xando(m[2], '3'));
    System.out.println(xando(m[3], '4') + " " + xando(m[4], '5')
      + " " + xando(m[5], '6'));
    System.out.println(xando(m[6], '7') + " " + xando(m[7], '8')
      + " " + xando(m[8], '9'));
    System.out.println();
  }


  //  This method helps in printing x's and o's on the board.
  static char xando(int printout, char value) {
    if (printout == 1)
      return 'x';
    if (printout == 4)
      return 'o';
    else return value;
  }
}
