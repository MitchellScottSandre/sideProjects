/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

/**
 *
 * @author scott
 */
public class testCalculations3 {

    /**
     * @param args the command line arguments
     */
    private static int[][] tableau = new int[5][5];
    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";

    private static void displayTableau() {
        System.out.println();
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print((char) tableau[r][c] + "  ");
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        int j;
        boolean foundGoodSpot = false;
        int[] usedLetters = new int[25];
        for (int i = 0; i < 25; i++) {
            usedLetters[i] = 0;//empty, that letter is not used
        }
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                foundGoodSpot = false;
                while (foundGoodSpot == false) {
                    j = (int) (Math.random() * 25 );
                    if (usedLetters[j] == 0) {
                        usedLetters[j] = 1;
                        foundGoodSpot = true;
                        tableau[r][c] = ALPHABET.charAt(j);
                        break;
                    }
                    System.out.print(". ");
                }

                

            }
        }

        displayTableau();
    }

}
