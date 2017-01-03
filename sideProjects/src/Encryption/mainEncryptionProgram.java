/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Encryption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author scott
 */
public class mainEncryptionProgram {

    //================================================INPUT OUTPUT ==================================================
    private static Scanner input = new Scanner(System.in);
    private static Scanner fileScanner;
    private static PrintWriter outputWriter;
    private static PrintWriter outputWriter2;

    //======================================================Constants================================================
    private static final int ENCRYPT = 1;
    private static final int DECRYPT = 2;
    private static final String EXIT = "EXIT";
    private static final String[] typesOfEncryptions = {"Playfair", "Vigenere"};
    private static final String ALPHABET = "ABCDEFGHIKLMNOPQRSTUVWXYZ";
    private static final int END_COL = 4;
    private static final int END_ROW = 4;
    private static final int BEGINNING_COL = 0;
    private static final int BEGINNING_ROW = 0;
    private static final int ROW = 0;
    private static final int COL = 1;
    //======================================================Variables================================================

    private static String inputFileName;
    private static String outputFileName;
    private static String inputText = "";
    private static String outputText = "";
    private static File inputFile;
    private static File outputFile;
    private static File outputRandomTableauFile;
    private static int encryptOrDecrypt;
    private static int typeOfEncryption;
    private static boolean exit = false;
    private static String vigenereEncryptionCode;
    private static String vigenereDecryptionCode;
    private static String outputLine;
    private static ArrayList<String> inputTextLines_List = new ArrayList<String>();
    private static ArrayList<String> outputTextLines_List = new ArrayList<String>();
    private static String randomTableauOutputLocationAndName = "";
    private static int[][] tableau = new int[5][5];
    private static String playfairEncryptionCode;
    private static String playfairDecryptionCode;
    private static String date = "September 25, 2016";
    private static String version = "1.1";

    //======================================================Functions================================================
    private static void exit() {
        exit = true;
        System.out.println("Exiting the program...");
        System.exit(0);
    }

    private static void startScreen() {
        System.out.println("Encryption Program:");
        System.out.println("This program allows a user to import a file, select whether they would like to encrypt or decrypt,\n and then have the encrypted or decrypted file exported to a directory and file of their choosing");
        System.out.println("At any time, enter EXIT to exit the progam.");
        System.out.println("Version: " + version + "\nDate of Version Release: " + date + "\n");
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
            } else {
                System.out.println("Incorrect input... try again");
            }
        }
    }

    private static void getFileNames() {
        String word = "decrypt", otherWord = "decrypted", correctInput, tempLine;
        boolean gotConfirmedInput = false;
        boolean gotGoodConfirmationInput;
        boolean inputFileExists = false;
        if (encryptOrDecrypt == ENCRYPT) {
            word = "encrypt";
            otherWord = "encrypted";
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
                        outputTextLines_List.add("");
                    }
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

    private static void displayTableau() {
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                System.out.print((char) tableau[r][c] + "  ");
            }
            System.out.print("\n");
        }
    }

    private static void findLocOfRandomTableauOutput(){

        String loc = inputFileName.substring(0, inputFileName.lastIndexOf("\\"));
        randomTableauOutputLocationAndName = loc + "\\randomTableau.txt";
        System.out.println("tableau output is " + randomTableauOutputLocationAndName);
        
    }
    
    private static void makeRandomTableau() throws FileNotFoundException {//TO DO ----> print this tableau to same directory as output, but C:\test\tableau + dateTimeStamp.txt
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
                    j = (int) (Math.random() * 25);
                    if (usedLetters[j] == 0) {
                        usedLetters[j] = 1;
                        foundGoodSpot = true;
                        tableau[r][c] = ALPHABET.charAt(j);
                        break;
                    }
                }
            }
        }
        findLocOfRandomTableauOutput();
        outputRandomTableauFile = new File(randomTableauOutputLocationAndName);//makes that file
        outputWriter2 = new PrintWriter(outputRandomTableauFile);
        String s;
        for (int i = 0; i < 5; i++) {
            s = "";
            for (int z = 0; z < 5; z++){
                s += (char) tableau[i][z] + " ";
            }
            outputWriter2.write(s);
            outputWriter2.println();
        }
        outputWriter2.close();
        
    }

    private static boolean goodCodeWord(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (((int) s.charAt(i) < 65 || (int) s.charAt(i) > 90)) {
                return false; // ISNT A CAPITAL LETTER
            }
        }
        return true;
    }

    private static void removeRepeatedLettersAndSpaces_in_PlayfairCodeWord() {
        //System.out.println("CODE WAS: " + playfairEncryptionCode);
        StringBuilder sb = new StringBuilder(playfairEncryptionCode);//sb.deleteCharAt
        //String resultString = sb.toString();

        playfairEncryptionCode = sb.toString();
        //System.out.println("CODE then: " + playfairEncryptionCode);
        for (int i = 0; i < sb.length() - 1; i++) {
            for (int z = i + 1; z < sb.length(); z++) {
                if (sb.charAt(i) == sb.charAt(z)) {
                    sb.deleteCharAt(z);
                    z--;
                }
            }
        }
        playfairEncryptionCode = sb.toString();
        //System.out.println("CODE IS NOW: " + playfairEncryptionCode);
    }

    private static String getRestOfAlphabet() {
        //playfairEncryptionCode 
        //if the code is EFGHIKL do ABCD
        String s = "";
        int[] usedLetters = new int[25];
        for (int i = 0; i < 25; i++) {
            usedLetters[i] = 0;//empty, that letter is not used
        }
        for (int i = 0; i < playfairEncryptionCode.length(); i++) {//TODO we dont want J in the tableau!
            System.out.println("--> " + (int) playfairEncryptionCode.charAt(i));
            if ((int) playfairEncryptionCode.charAt(i) > 73) {
                usedLetters[(int) playfairEncryptionCode.charAt(i) - 66] = 1;
            } else {
                usedLetters[(int) playfairEncryptionCode.charAt(i) - 65] = 1;
            }

        }
        
//        for (int i = 0; i < 25; i++){
//            System.out.println(i + "__" + ALPHABET.charAt(i) + "__" + usedLetters[i]);
//        }

        for (int i = 0; i < 25; i++) {
            if (usedLetters[i] == 0) {
                s += ALPHABET.charAt(i) + "";
            }
        }
        System.out.println("s is " + s);
        return s;
    }

    private static void makeCodePhraseTableau() {
        boolean gotGoodInput = false;
        int lastR = 0, lastC = 0;
        String restOfAlphabet;
        while (gotGoodInput == false) {
            System.out.println("Please enter your code word for the tableau: ");
            playfairEncryptionCode = input.nextLine();
            playfairEncryptionCode = playfairEncryptionCode.toUpperCase();//to upper case

            StringBuilder sb = new StringBuilder(playfairEncryptionCode);//sb.deleteCharAt
            //String resultString = sb.toString();
            for (int i = 0; i < sb.length(); i++) {
                if (sb.charAt(i) == ' ') {
                    sb.deleteCharAt(i);
                }
            }
            playfairEncryptionCode = sb.toString();
            if (goodCodeWord(playfairEncryptionCode) == true) {
                gotGoodInput = true;
                System.out.println("Good code word");
                //remove any repeated letters
                playfairEncryptionCode = playfairEncryptionCode.replace('J', 'I');//replace any J with I
                removeRepeatedLettersAndSpaces_in_PlayfairCodeWord();
            } else {
                System.out.println("That code word is not acceptable. Try again");
            }
        }
        //now insert letters into tableau, fill the rest in alphabetically

        for (int r = 0; r < 5; r++) {//place the passphrase characters into the tableau
            for (int c = 0; c < 5; c++) {
                if (r * 5 + c < playfairEncryptionCode.length()) {
                    tableau[r][c] = playfairEncryptionCode.charAt(r * 5 + c);
                } else {
                    lastR = r;
                    lastC = c;
                    r = 100;
                    c = 100;
                    break;
                }
            }
        }
        //now fill the rest of it alphabetically
        restOfAlphabet = getRestOfAlphabet();
        int counter = 0;
        boolean addNewChars = false;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (r == lastR && c == lastC) {
                    addNewChars = true;
                }
                if (addNewChars == true) {
                    tableau[r][c] = (int) restOfAlphabet.charAt(counter);
                    counter++;
                }

            }
        }
    }

    private static int[] findLetterRowAndCol(char findMe){
        int z[] = new int[2];
        z[0] = -1;
        z[1] = -1;
        for (int r = 0; r < 5; r++){
            for (int c = 0; c < 5; c++){
                if (tableau[r][c] == (int) findMe){
                    z[0] = r;
                    z[1] = c;
                    return z;
                }
            }
        }
        return z;
    }
    
    private static void doEncryptionWithTableau(int firstChar[], int secondChar[], int rowOfArrayList, int startOfNewRow){
        //char encryptedCharOne = ' ', encryptedCharTwo = ' ';
        String s = "";
        char originalFirstChar, originalSecondChar;
        char newFirstChar, newSecondChar;

        originalFirstChar = (char) tableau[firstChar[ROW]][firstChar[COL]];
        originalSecondChar = (char) tableau[secondChar[ROW]][secondChar[COL]];
        
        s =outputTextLines_List.get(rowOfArrayList);
        
        //firstChar (array) at 0 == row 0 1 2 3 4
        //firstCharr (array) at 1 == column 0 1 2 3 4
            ///================================================SAME ROW, MOVE COLUMN OVER ONE RIGHT WITH WRAP AROUND ================                                    
            if (firstChar[ROW] == secondChar[ROW]){//case 1: same row, with wrap around horizontal
                //System.out.print("SAME ROW     ");
                //first char at the right end
                if (firstChar[COL] == END_COL){//second char must be to left
                    //System.out.println(rowOfArrayList + " --- Fist Char at end Col Loc");
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [BEGINNING_COL]        ;//wrap around to beginning
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [secondChar[COL] + 1  ];//right one moves over one
                } else if (secondChar[COL] == END_COL){
                    //System.out.println(rowOfArrayList + " --- Second Char at end Col Loc");
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [firstChar[COL] + 1]   ;//wrap around to beginning
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [BEGINNING_COL ]       ;//right one moves over one                  
                } else {
                    //System.out.println(rowOfArrayList + " --- None at end col, moving each over to right one");
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [firstChar[COL] + 1]   ;
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [secondChar[COL] + 1]   ;               
                }
            } else if (firstChar[COL] == secondChar[COL]){//case 2: same column, with wrap around vertical
                //System.out.print("SAME COLUMN    ");
                 if (firstChar[ROW] == END_ROW){//second char must be above, first char at BOTTOM
                    //System.out.println(rowOfArrayList + " --- Fist Char at bottom ROW");
                    newFirstChar = (char) tableau[  BEGINNING_ROW      ]  [firstChar[COL]]        ;//wrap around to beginning
                    newSecondChar= (char) tableau[  secondChar[ROW] + 1]  [secondChar[COL]  ];//right one moves over one
                } else if (secondChar[ROW] == END_ROW){//second char is at bottom
                    //System.out.println(rowOfArrayList + " --- Second Char at end row, move to top");
                    newFirstChar = (char) tableau[  firstChar [ROW] + 1] [firstChar[COL]]        ;//wrap around to beginning
                    newSecondChar= (char) tableau[  BEGINNING_ROW      ] [secondChar[COL]  ];//right one moves over one                 
                } else {
                    //System.out.println(rowOfArrayList + " --- None at end row");
                    newFirstChar = (char) tableau[   firstChar[ROW] + 1]   [firstChar[COL]]   ;
                    newSecondChar= (char) tableau[  secondChar[ROW] + 1]  [secondChar[COL]]   ;               
                }               
                
                
            } else {//case 3: use opposite corners of the rectangle

                newFirstChar= (char) tableau[firstChar[ROW]][secondChar[COL]];
                newSecondChar= (char) tableau[secondChar[ROW]][firstChar[COL]];
            }
            
        s += newFirstChar;
        s += newSecondChar;
        //System.out.println("BEFORE " + originalFirstChar + " " + originalSecondChar + " ___ AFTER " + newFirstChar + " " + newSecondChar);

        outputTextLines_List.add(rowOfArrayList, s);
        outputTextLines_List.remove(rowOfArrayList + 1);
        
    
    }
    
    private static void doPlayfairEncrypt() throws FileNotFoundException {//TO DO
        int choice, typeOfPlayfair = -1;
        final int ALPHABETIC = 1;
        final int CODE_MERGE_ALPHABETIC = 2;
        int firstChar_letterRowAndCol[] = new int[2];
        int secondChar_letterRowAndCol[] = new int[2];
        boolean gotGoodInput = false;
        String s1 = "";
        String s2 = "";

        //put to uppercase
        //remove any other characters
        System.out.println("Playfair Encryption:");
        System.out.println("Playfair Encryption uses a tableau of 5 x 5 letters to encode your file.");
        System.out.println("This tableau can either use a scrambled alphabet or use a codephrase made by you,");
        System.out.println("which automatically has any repeated letters removed, insterted into an alphabetic tableau.\nYour choices are:");
        System.out.println("1: Random, Scrabled Tableau (key will be saved to input file directory)");
        System.out.println("2: Code Phrase merged into an Alphabetic Tableau");

        while (gotGoodInput == false) {
            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice == ALPHABETIC || choice == CODE_MERGE_ALPHABETIC) {
                    gotGoodInput = true;
                    typeOfPlayfair = choice;
                } else {
                    System.out.println("You did not enter a number within the desired range...Try again");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong... Try again.");
            }
        }

        switch (typeOfPlayfair) {
            case ALPHABETIC:
                makeRandomTableau();
                System.out.println("Finished creating random tableau.");
                System.out.println("NEED TO OUTPUT IT TO SAME DIRECTORY AS INPUT FILE HERE.....");
                break;
            case CODE_MERGE_ALPHABETIC:
                makeCodePhraseTableau();
                System.out.println("Finished creating Codephrase tableau.");
                break;
            default://error
                exit();
                break;
        }

        displayTableau();
        //hello world // he ll ow or ld // he lx lo wo rl dx
        //================================PUT INTO CAPITAL LETTERS WITH NO SPACES and NO Js===============================

       // System.out.println("initial size is : " + inputTextLines_List.size());
        for (int i = 0; i < inputTextLines_List.size(); i++) {
            s1 = "";
            s2 = "";
            s1 = inputTextLines_List.get(i).toUpperCase();
            for (int j = 0; j < s1.length(); j++) {//s.charAt(i) < 65 || (int) s.charAt(i) > 90)
                if (s1.charAt(j) == 'J') {
                    s2 += 'I';
                } else if ((int) s1.charAt(j) >= 65 && (int) s1.charAt(j) <= 90) {
                    s2 += s1.charAt(j);
                }
//                else if (s1.charAt(j) == ' '){
//                    s2 += " ";
//                }
            }
            inputTextLines_List.add(i, s2);
            inputTextLines_List.remove(i + 1);
        }
        //System.out.println("after size is : " + inputTextLines_List.size());
        int offset = 0;
        //===========================put into pairs separated by * and still spaces
        s1 = "";
        s2 = "";
        for (int i = 0; i < inputTextLines_List.size(); i++) {//HELLO WORLD //HE*LL*OW*OR*LD // HE*LX*LO*WO*RL*DX
            s1 = inputTextLines_List.get(i);
            //System.out.println("was -->" + s1);
            s2 = "";
            for (int j = 0; j < s1.length(); j++) {
                if (j != s1.length() - 1) {
                    if (s1.charAt(j) != s1.charAt(j + 1)) {
                        s2 += (char) s1.charAt(j) + "" + (char) s1.charAt(j + 1) + "" + "*";
                        j++;
                    } else {//they do equal eachother
                        s2 += (char) s1.charAt(j) + "X*";
                    }
                } else {
                    s2 += (char) s1.charAt(j) + "X";
                }
            }
            inputTextLines_List.add(i, s2);
            inputTextLines_List.remove(i + 1);

        }

        System.out.println("Printing out info from row and col finder loop...");
        for (int i = 0; i < inputTextLines_List.size(); i++){

            if (inputTextLines_List.get(i).length() == 0) {
                    //System.out.println("length is 0 at index " + i);
                    i++;
                }
            for (int j = 0; j < inputTextLines_List.get(i).length() - 1; j++){
                //System.out.println(inputTextLines_List.get(i).charAt(j) + " $$$$  " + inputTextLines_List.get(i).charAt(j + 1));
                firstChar_letterRowAndCol = findLetterRowAndCol ( inputTextLines_List.get(i).charAt(j) );
                secondChar_letterRowAndCol = findLetterRowAndCol( inputTextLines_List.get(i).charAt(j + 1) );
           
                doEncryptionWithTableau(firstChar_letterRowAndCol, secondChar_letterRowAndCol, i, j);//firstchar, second char, row of array list
                j +=2;//skip the *
                
            }
        }
        
        writeToOutputFile();
        
        
    }
    
    private static void getRandomTableauFromUser(){
        System.out.println("For each row, please enter the 5 letters, without spaces, correspoding to  that row on your encryption tableau: ");
        String row = "", answer = "";
        boolean gotConfirmedInput = false;

        while (gotConfirmedInput == false) {
            for (int i = 0; i < 5; i++) {
                System.out.print("Row #" + (i + 1) + ": ");
                row = input.nextLine();
                for (int c = 0; c < 5; c++) {
                    tableau[i][c] = row.charAt(c);
                }
            }
            System.out.println("Is this the correct tableau you meant to enter? \nY for Yes, N for N:");
            answer = input.nextLine();
            if (answer.equalsIgnoreCase("Y")){
                gotConfirmedInput = true;
            } else {
                System.out.println("Okay. Try again.");
            }
        }
        
    }

    private static void doDecryptionWithTableau(int firstChar[], int secondChar[], int rowOfArrayList){
             //char encryptedCharOne = ' ', encryptedCharTwo = ' ';
        String s = "";
        char originalFirstChar, originalSecondChar;
        char newFirstChar, newSecondChar;

        originalFirstChar = (char) tableau[firstChar[ROW]][firstChar[COL]];
        originalSecondChar = (char) tableau[secondChar[ROW]][secondChar[COL]];
        
        s =outputTextLines_List.get(rowOfArrayList);
        
     
            ///================================================SAME ROW, MOVE COLUMN OVER ONE RIGHT WITH WRAP AROUND ================                                    
            if (firstChar[ROW] == secondChar[ROW]){//case 1: same row, with wrap around horizontal LEFT
                if (firstChar[COL] == BEGINNING_COL){//second char must be to right
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [END_COL]        ;
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [secondChar[COL] - 1  ];
                } else if (secondChar[COL] == BEGINNING_COL){
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [firstChar[COL] - 1]   ;
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [END_COL ]       ;//                  
                } else {
                    newFirstChar = (char) tableau[  firstChar [ROW]]  [firstChar[COL] - 1]   ;
                    newSecondChar= (char) tableau[  secondChar[ROW]]  [secondChar[COL] - 1]   ;               
                }
            } else if (firstChar[COL] == secondChar[COL]){//case 2: same column, with wrap around vertical
                 if (firstChar[ROW] == BEGINNING_ROW){      
                    newFirstChar = (char) tableau[  END_ROW      ]  [firstChar[COL]]  ;
                    newSecondChar= (char) tableau[  secondChar[ROW] - 1]  [secondChar[COL]  ];
                } else if (secondChar[ROW] == BEGINNING_ROW){
                    
                    newFirstChar = (char) tableau[  firstChar [ROW] - 1] [firstChar[COL]]        ;
                    newSecondChar= (char) tableau[  END_ROW      ] [secondChar[COL]  ];               
                } else {
              
                    newFirstChar = (char) tableau[   firstChar[ROW] - 1]   [firstChar[COL]]   ;
                    newSecondChar= (char) tableau[  secondChar[ROW] - 1]  [secondChar[COL]]   ;               
                }               
                
                
            } else {//case 3: use opposite corners of the rectangle

                newFirstChar= (char) tableau[firstChar[ROW]][secondChar[COL]];
                newSecondChar= (char) tableau[secondChar[ROW]][firstChar[COL]];
            }
            
        s += newFirstChar;
        s += newSecondChar;
        //System.out.println("BEFORE " + originalFirstChar + " " + originalSecondChar + " ___ AFTER " + newFirstChar + " " + newSecondChar);

        outputTextLines_List.add(rowOfArrayList, s);
        outputTextLines_List.remove(rowOfArrayList + 1);
        
    }
    
    private static void doPlayfairDecrypt() {//TO DO
        System.out.println("Playfair Decryption:");
        //get them to input the random tableau, or input the passphrase
        int firstChar_letterRowAndCol[] = new int[2];
        int secondChar_letterRowAndCol[] = new int[2];
        boolean gotGoodInput = false;
        int choice;
        System.out.println("Playfair Encryption uses a tableau of 5 x 5 letters to encode your file.");
        System.out.println("This tableau can either use a scrambled alphabet or use a codephrase made by you,");
        System.out.println("which automatically has any repeated letters removed, insterted into an alphabetic tableau.\nYour choices for the encryption used are:");
        System.out.println("1: Random, Scrabled Tableau ");
        System.out.println("2: Code Phrase merged into an Alphabetic Tableau");
        while (gotGoodInput == false){
            System.out.println("Please enter your choice: ");
            try {
                choice = Integer.parseInt(input.nextLine());
                if (choice == 1){
                    //get them to input the entire random tableau
                    gotGoodInput = true;
                    getRandomTableauFromUser();
                } else if (choice == 2){
                    makeCodePhraseTableau();
                    gotGoodInput = true;
                } else {
                    System.out.println("Incorrect number entered. Please try again");
                }
            } catch (Exception e){
                System.out.println("Oh no, something went wrong. Try again");
            }
        }
        
        displayTableau();
        
        for (int i = 0; i < inputTextLines_List.size(); i++) {

            if (inputTextLines_List.get(i).length() == 0) {
                i++;
            }
            for (int j = 0; j < inputTextLines_List.get(i).length() - 1; j++) {              
                firstChar_letterRowAndCol = findLetterRowAndCol(inputTextLines_List.get(i).charAt(j));
                secondChar_letterRowAndCol = findLetterRowAndCol(inputTextLines_List.get(i).charAt(j + 1));
                doDecryptionWithTableau(firstChar_letterRowAndCol, secondChar_letterRowAndCol, i);//firstchar, second char, row of array list
                j++;//skip the *

            }
        }
        
        writeToOutputFile();
    }

    private static void doVigenereEncrypt() {//TO DO
        boolean gotGoodInput = false;
        int[] shiftValues;
        int newAsciiValue, indexOfShiftArray, beta, alpha;
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
            //System.out.println("---> " + shiftValues[i]);
        }

        for (int z = 0; z < inputTextLines_List.size(); z++) {//each index of the array list
            outputLine = "";
            for (int i = 0; i < inputTextLines_List.get(z).length(); i++) {
                if (inputTextLines_List.get(z).charAt(i) < 32 || inputTextLines_List.get(z).charAt(i) > 126) {
                    i++;
                }
                indexOfShiftArray = i % shiftValues.length;
                beta = shiftValues[indexOfShiftArray];
                alpha = (int) inputTextLines_List.get(z).charAt(i);
                newAsciiValue = ((beta - (126 - alpha)) % 95) + 32;
                outputLine += (char) newAsciiValue;

            }

            outputTextLines_List.add(z, outputLine);//ERROR ==> fixed
            outputTextLines_List.remove(z + 1);
            //System.out.println(outputLine);//testing purposes only
        }

        writeToOutputFile();

    }

    private static void doVigenereDecrypt() {//TO DO
        int[] shiftValues;
        int beta, encryptedValue, indexOfShiftArray, decryptedValue;

        System.out.println("Vigenere Decryption:");
        System.out.println("Please enter your passcode to decrypt the file: ");
        vigenereDecryptionCode = input.nextLine();

        shiftValues = new int[vigenereDecryptionCode.length()];
        for (int i = 0; i < shiftValues.length; i++) {
            shiftValues[i] = vigenereDecryptionCode.charAt(i);//negative, shift the other way
            //System.out.println("---> " + shiftValues[i]);
        }

        for (int z = 0; z < inputTextLines_List.size(); z++) {//each index of the array list
            outputLine = "";
            for (int i = 0; i < inputTextLines_List.get(z).length(); i++) {
                if (inputTextLines_List.get(z).charAt(i) < 32 || inputTextLines_List.get(z).charAt(i) > 126) {
                    i++;
                }
                indexOfShiftArray = i % shiftValues.length;
                beta = shiftValues[indexOfShiftArray];
                encryptedValue = (int) inputTextLines_List.get(z).charAt(i);
                decryptedValue = 126 - ((beta - (encryptedValue - 32)) % 95);

                outputLine += (char) decryptedValue;

            }
            outputTextLines_List.add(z, outputLine);//ERROR ==> fixed
            outputTextLines_List.remove(z + 1);
            //System.out.println(outputLine);testing purposes only
        }

        writeToOutputFile();

    }

    private static void writeToOutputFile() {
        System.out.println("Outputting encrypted text...");
        //System.out.println("Size of output text line is " + outputTextLines_List.size());
        for (int i = 0; i < outputTextLines_List.size(); i++) {
            outputWriter.write(outputTextLines_List.get(i));
            outputWriter.println();
        }
        outputWriter.close();
        System.out.println("Done.");
    }

    public static void main(String[] args) throws FileNotFoundException {

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

    }

}
