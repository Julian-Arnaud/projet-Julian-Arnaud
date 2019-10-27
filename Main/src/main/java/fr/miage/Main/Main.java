package fr.miage.Main;

import fr.miage.IO.Grid;

import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Pattern;

public class Main {
    static Pattern pattern = Pattern.compile("^[0-8][0-8][1-9]$");
    static Pattern initP = Pattern.compile("^[0-9]$");

    public static void main(String[] args) {
        Grid theGame = null;
        boolean started = false;
        // holds the actions played
        Stack<String> playedStack = new Stack<String>();
        String[] inputs;
        String inputString;
        Scanner scanner = new Scanner(System.in);
        while(!scanner.equals("exit")) {
            inputString = scanner.nextLine();
            inputs = inputString.split(" ");
            System.out.println(inputString);
            // handle inputs
            if(inputString.length() == 0) {
                // no arguments case
                System.out.println("Erreur: saisie vide");
            } else if(initP.matcher(inputString).matches()) {
                theGame = new Grid(Integer.parseInt(inputString));
                started = true;
            } else if(pattern.matcher(inputString).matches()) {
                // case player plays
                String[] tmp = inputString.split("");
                if(theGame.checkValue(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), tmp[2])){
                    theGame.addValue(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]), tmp[2]);
                    playedStack.push(inputString);
                    System.out.println("bien ouej");
                } else {
                    System.out.println("patate");
                }
            } else if(inputs[0].equals("save") && started) {
                theGame.save(inputs[1]);
            } else if(inputs[0].equals("load") && !started) {
                theGame = new Grid();
                theGame.load(inputs[1]);
                started = true;
            } else if(inputString.equals("back") && started) {
                if(!playedStack.empty()) {
                    String[] tmp2 = playedStack.pop().split("");
                    theGame.cancel(Integer.parseInt(tmp2[0]), Integer.parseInt(tmp2[1]));
                }
            } else if(inputString.equals("exit")) {
                return;
            }
            else {
                System.out.println("mauvaise saisie");
            }
            if(started) fr.miage.GUI.Display.Display(theGame.getStartGrid());
        }
    }
}
