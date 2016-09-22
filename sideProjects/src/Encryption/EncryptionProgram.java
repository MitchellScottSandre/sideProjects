/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author scott
 */
public class EncryptionProgram {

    /**
     * @param args the command line arguments
     */
    //remember to do error checking
    //get file name to encrypt or decrypt 
    //get output file name to put the encrypted/ decrypted file
    //get user desire: encrypt or decrypt it 
    //if encrypt, get choice for encryption
    //playfair (enter the tableau)
    //vignere //enter the passcode 
    //if decrypt, get what type of encryption it os
    //playfaire, get the tableau
    //viginere, get the code
    //random comments
    // allow them to enter restart, or exit to leave the program, or restart? YUP
    //================================================INPUT OUTPUT ==================================================
    private static Scanner input = new Scanner(System.in);
    private static Scanner fileScanner;
    private static PrintWriter outputWriter;

    //======================================================Constants=================================================
    private static final int ENCRYPT = 1;
    private static final int DECRYPT = 2;
    private static final String EXIT = "EXIT";
    private static final String RESTART = "RESTART";
    private static final String[] typesOfEncryptions = {"Playfair", "Vigenere"};
    //======================================================Variables================================================
    private static String inputFileName;
    private static String outputFileName;
    private static String inputText = "";
    private static String outputText = "";
    private static File inputFile;
    private static File outputFile;
    private static int encryptOrDecrypt;
    private static int typeOfEncryption;
    private static boolean exit = false;
    private static boolean restart = false;
    private static String vigenereEncryptionCode;
    private static String outputLine;
    private static ArrayList<String> inputTextLines_List = new ArrayList<String>();
    private static ArrayList<String> outputTextLines_List = new ArrayList<String>();

    // Add change listener?
    //======================================================Functions==================================================
    private static void exit() {
        exit = true;
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    private static void restart() {
        System.out.println("Restarting the progam...");
        restart = true;
    }

    private static void startScreen() {
        System.out.println("Start screen here:");
        System.out.println("At any time, enter EXIT to exit the progam or RESTART to restart the program.");
    }

    private static void getEncryptOrDecrypt() {
        System.out.println("Would you like to Encrypt a file or Decrypt a file?\nEnter e to Encrypt or d to Decrypt: ");
        boolean gotGoodInput = false;
        String choice;
        while (gotGoodInput == false) {
            choice = input.nextLine();
            if (choice.equalsIgnoreCase("E")) {
                encryptOrDecrypt = ENCRYPT;
                gotGoodInput = true;
            } else if (choice.equalsIgnoreCase("D")) {
                encryptOrDecrypt = DECRYPT;
                gotGoodInput = true;
            } else if (choice.equalsIgnoreCase(EXIT)) {
                exit();
                gotGoodInput = true;
            } else if (choice.equalsIgnoreCase(RESTART)) {
                restart();
                gotGoodInput = true;
            } else {
                System.out.println("Incorrect input... try again");
            }
        }
    }

    private static void getFileNames() {
        String word = "decrypt", otherWord = "encrypted", correctInput, tempLine;

        boolean gotConfirmedInput = false;
        boolean gotGoodConfirmationInput;
        boolean inputFileExists = false;
        if (encryptOrDecrypt == ENCRYPT) {
            word = "encrypt";
            otherWord = "decrypted";
        }
        //=================================================== get input file location and name
        System.out.println("\nGet Input File Name:");
        while (gotConfirmedInput == false) {
            gotGoodConfirmationInput = false;
            System.out.println("Enter location and file name of file you wish to " + word + ": ");
            inputFileName = input.nextLine();

            if (inputFileName.equalsIgnoreCase("EXIT")) {
                exit();
            }

            System.out.println("You entered: " + inputFileName + ". Is this correct? Y or N: ");
            while (gotGoodConfirmationInput == false) {
                correctInput = input.nextLine();
                if (correctInput.equalsIgnoreCase("Y")) {
                    gotGoodConfirmationInput = true;
                    gotConfirmedInput = true;
                    break;
                } else if (correctInput.equalsIgnoreCase("N")) {
                    gotGoodConfirmationInput = true;
                    System.out.println("Okay...try again");
                } else if (correctInput.equalsIgnoreCase("EXIT")) {
                    exit();
                }
            }

            try {//=========================scan in INPUT FILE
                inputFile = new File(inputFileName);
                fileScanner = new Scanner(inputFile);
                System.out.println(inputFile + " sucessfully scanned in.");
                inputFileExists = true;

                //parse to string
                while (fileScanner.hasNextLine()) {
                    tempLine = fileScanner.nextLine();
                    if (tempLine != null) {
                        //inputText += tempLine;//turn this in to an array
                        inputTextLines_List.add(tempLine);
                    }
                }
                
                for (int i = 0; i < inputTextLines_List.size(); i++){
                    System.out.println(inputTextLines_List.get(i));
                }
            } catch (Exception e) {
                System.out.println("Could not find the file... Try entering it's Location and Name again");
                gotConfirmedInput = false;
            }
        }

        //=================================================== get output file location and name
        gotConfirmedInput = false;
        System.out.println("\nGet Output File Name:");
        while (gotConfirmedInput == false) {
            gotGoodConfirmationInput = false;
            System.out.println("\nEnter output location and file name of " + otherWord + " file: ");
            outputFileName = input.nextLine();

            if (outputFileName.equalsIgnoreCase("EXIT")) {
                exit();
            }

            System.out.println("You entered: " + outputFileName + ". Is this correct? Y or N: ");
            while (gotGoodConfirmationInput == false) {
                correctInput = input.nextLine();
                if (correctInput.equalsIgnoreCase("Y")) {
                    gotGoodConfirmationInput = true;
                    gotConfirmedInput = true;
                    break;
                } else if (correctInput.equalsIgnoreCase("N")) {
                    gotGoodConfirmationInput = true;
                    System.out.println("Okay...try again");
                } else if (correctInput.equalsIgnoreCase("EXIT")) {
                    exit();
                }
            }

            try {
                outputFile = new File(outputFileName);//makes file
                outputWriter = new PrintWriter(outputFile);
                System.out.println("Successfully created template output file");
            } catch (Exception e) {
                System.out.println("There was an error creating the desired output file.");
            }
        }

        //TO DO
        //try reading in the 
    }

    private static void getTypeOfEncryption() {
        String word = "used";
        System.out.println("\nGet Type of Encryption: ");

        for (int i = 0; i < typesOfEncryptions.length; i++) {
            System.out.println((i + 1) + ": " + typesOfEncryptions[i]);
        }
        System.out.println("\n");
        if (encryptOrDecrypt == ENCRYPT) {
            System.out.println("Please enter the type of encryption you would like to use to encrypte your file: ");
            word = "you want to use";
        } else {
            System.out.println("Please enter the type of encryption that was used to encrypt your file: ");
        }

        boolean gotGoodInput = false;
        while (gotGoodInput == false) {
            try {
                typeOfEncryption = Integer.parseInt(input.nextLine());
                if (typeOfEncryption >= 1 && typeOfEncryption <= typesOfEncryptions.length) {
                    gotGoodInput = true;
                } else {
                    System.out.println("Incorrect value entered. Try again");
                }
            } catch (Exception e) {
                System.out.println("Perhaps you made a mistake. Enter the desired numerical value corresponding to the type of encryption " + word);
            }
        }

    }

    private static void doPlayfairEncrypt() {//TO DO
        System.out.println("Playfair Encryption:");
    }

    private static void doPlayfairDecrypt() {//TO DO
        System.out.println("Playfair Decryption:");
    }

    private static void doVigenereEncrypt() {//TO DO
        boolean gotGoodInput = false;
        int[] shiftValues;
        int newAsciiValue, indexOfShiftArray;
        System.out.println("Vigenere Encryption:\n");
        System.out.println("Your passcode can consist of any combination of numbers, letters, spaces, and symbols");
        System.out.println("Ex: 1, 2, 3, a, b, c, A, B, C, ?, <, >, !, @, #, $, %, ^, &, *, , (, ), ;,");
        while (gotGoodInput == false) {
            System.out.println("Please enter your passcode of length <= 20 to encrypt the file: ");
            vigenereEncryptionCode = input.nextLine();
            if (vigenereEncryptionCode.length() <= 20 && vigenereEncryptionCode.length() >= 1) {
                gotGoodInput = true;
            } else {
                System.out.println("Your passcode length wasn't in the correct range. Try again");
            }
        }
        shiftValues = new int[vigenereEncryptionCode.length()];
        for (int i = 0; i < shiftValues.length; i++) {
            shiftValues[i] = vigenereEncryptionCode.charAt(i);
            System.out.println("---> " + shiftValues[i]);
        }

        for (int z = 0; z < inputTextLines_List.size(); z++) {//each index of the array list
            outputLine = "";
            for (int i = 0; i < inputTextLines_List.get(z).length(); i++) {
                if (inputTextLines_List.get(z).charAt(i) < 32 || inputTextLines_List.get(z).charAt(i) > 126) {
                    i++;
                }
                indexOfShiftArray = i % shiftValues.length;
                newAsciiValue = (int) inputTextLines_List.get(z).charAt(i) + shiftValues[indexOfShiftArray];//95
                //TO DO , what if you add x + x = 120 + 120 = 240, - 95 = 145
                if (newAsciiValue >= 127) {//wraps around, so if its 127, goes down to 32
                    newAsciiValue -= 95;
                    System.out.print("shifted down 95...");
                }
                System.out.println("___" + (char) inputTextLines_List.get(z).charAt(i) + " is ascii value " + (int) inputTextLines_List.get(z).charAt(i) + ". shift is " + shiftValues[indexOfShiftArray] + ". new ascii value is " + newAsciiValue + ", that symbol is " + (char) newAsciiValue);

                outputLine += (char) newAsciiValue;
                
            }
            outputTextLines_List.add(outputLine);
            System.out.println(outputLine);
        }

            writeToOutputFile();
        
        
        //outputWriter.println();
        //minimum is Space dec32, max is ~ 126
    }
    
    private static void writeToOutputFile(){
        System.out.println("Size of output text line is " + outputTextLines_List.size());
        for (int i = 0; i < outputTextLines_List.size(); i++){
            outputWriter.write(outputTextLines_List.get(i));
            outputWriter.println();
        }
        
        outputWriter.close();
    }

    private static void doVigenereDecrypt() {//TO DO 
        System.out.println("Vigenere Decryption:");
    }

    public static void main(String[] args) {

        do {
            startScreen();
            getEncryptOrDecrypt();//ex: encrypt
            getFileNames();//input file = ___; output file = ___
            getTypeOfEncryption();//vigenere, playfair
            if (encryptOrDecrypt == ENCRYPT) {
                switch (typeOfEncryption) {
                    case 1:
                        doPlayfairEncrypt();
                        break;
                    case 2:
                        doVigenereEncrypt();
                        break;
                }
            } else {
                switch (typeOfEncryption) {
                    case 1:
                        doPlayfairDecrypt();
                        break;
                    case 2:
                        doVigenereDecrypt();
                        break;
                }
            }
        } while (restart == true);
    }

}
