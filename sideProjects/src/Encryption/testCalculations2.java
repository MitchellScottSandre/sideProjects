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
public class testCalculations2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //playfairEncryptionCode 
        //if the code is EFGHIKL do ABCD
        String playfairEncryptionCode = "ENZA";
        String s = "";
        int[] usedLetters = new int[25];
        for (int i = 0; i < 25; i++) {
            usedLetters[i] = 0;//empty, that letter is not used
        }
        for (int i = 0; i <playfairEncryptionCode.length() ; i++) {
            System.out.println("--> " + (int) playfairEncryptionCode.charAt(i));
            if ((int) playfairEncryptionCode.charAt(i) > 74){
                usedLetters[ (int) playfairEncryptionCode.charAt(i) - 66] = 1;
            } else {
                usedLetters[ (int) playfairEncryptionCode.charAt(i) - 65] = 1;
            }
            
        }
        
        for (int i = 0; i < 25; i++){
            if (usedLetters[i] == 0){
                s += (char) (i + 65) + "";
            }
        }
        System.out.println("s is " + s);
        
    }
    
}
