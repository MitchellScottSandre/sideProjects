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
public class testCalculations {

    /**
     * @param args the command line arguments
     */
    public static int decrypt(int encryptedValue, int beta) {
//        if (encryptedValue - beta >= 32){
//            return encryptedValue - beta;
//        }
        return 126 - ((beta - (encryptedValue - 32)) % 95);
    }

    public static int encrypt(int alpha, int beta) {
//        if (alpha + beta <= 126){
//            return alpha + beta;
//        }
        return ((beta - (126 - alpha)) % 95) + 32;
    }

    public static void main(String[] args) {
        String playfairEncryptionCode = "hi my name is scott sandre";
        System.out.println("CODE WAS: " + playfairEncryptionCode);
        StringBuilder sb = new StringBuilder(playfairEncryptionCode);//sb.deleteCharAt
        //String resultString = sb.toString();
        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
            }
        }
        playfairEncryptionCode = sb.toString();
        System.out.println("CODE then: " + playfairEncryptionCode);
        for (int i = 0; i < sb.length() - 1; i++) {
            for (int z = i + 1; z < sb.length(); z++) {
                if (sb.charAt(i) == sb.charAt(z)) {
                    sb.deleteCharAt(z);
                    z--;
                }
            }
        }
        playfairEncryptionCode = sb.toString();
        System.out.println("CODE IS NOW: " + playfairEncryptionCode);

//        int alpha, beta, encryptedValue, decryptedValue;
//        alpha = (int) ' ';//number to be shifted
//        beta = (int) 'x';//shift key
//        encryptedValue = ( (beta - (126 - alpha)) % 95) + 32;
//        System.out.println("-->" + encryptedValue);
//        System.out.println("so " + (char) alpha + " shifted up by " + (char) beta + " equals " + (char)encryptedValue);
//        System.out.println("Go reverse");
//        
//        decryptedValue = 126 - ( (beta - (encryptedValue - 32)) % 95);
//        System.out.println("so " + (char) encryptedValue + " shifted down " + (char) beta + " equals " + (char)decryptedValue);
//        
//        //now test all possible combinations
//        for (int i = 32; i <= 126; i++){//value, alpha
//            for (int z = 32; z <= 126; z++){//shift, beta
//               encryptedValue = encrypt(i, z);
//               decryptedValue = decrypt(encryptedValue, z);
//               if (i != decryptedValue){
//                   //System.out.println( (char) encryptedValue + " does not equal " + (char) decryptedValue);
//                   System.out.println("original value is " + i + " and shift is " + z);
//                   //System.out.println("Encrypted is: " + encryptedValue);
//                   //System.out.println("Decrypted is: " + decryptedValue);
//               }
//            }
//       }
    }

}
