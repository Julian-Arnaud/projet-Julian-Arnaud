package fr.miage.IO;

import java.io.*;
import java.util.Scanner;

public class Grid {
    private String[] startGrid;

    public Grid(int numGrid) {
        startGrid = new String[81];
        //loadGrid = new String[81];
        this.fillGrids(numGrid);
    }

    public Grid() {
        startGrid = new String[81];
    }

    /**
     * Utilisée si on initialise la grille avec une grille existante en interne
     * @param numGrid
     */
    private void fillGrids(int numGrid) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("IO/src/main/resources/grid_" + numGrid + ".txt"));
            String line = br.readLine();
            startGrid = line.split(",");
            br.close();
            // #ZeroRace
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Si fichier provenant d'une partie sauvegardée, on reprend le fonctionnement de fillGrids
     * Sinon on lit ligne par ligne et on s'adapte
     * @param path
     */
    public void load(String path) {
        try {
            Scanner in = new Scanner(new FileReader(path));
            StringBuilder builder = new StringBuilder();
            while (in.hasNext()) {
                builder.append(in.next());
            }
            startGrid = builder.toString().split("");
            in.close();
            //on suppose que fichier correct
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * @param path
     */
    public void save(String path) {
        try {
            PrintWriter writer = new PrintWriter(path);
            for(int i = 0; i < 81; ++i) {
                if(i % 9 == 0 && i != 0) {
                    writer.print('\n');
                }
                writer.print(startGrid[i]);
            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Permet d'annuler le dernier coup joué
     * @param x
     * @param y
     */
    public void cancel(int x, int y) {
        startGrid[9*x + y] = "_";
    }

    public String[] getStartGrid() {
        return startGrid;
    }

    //input considéré comme valide

    /**
     * Vérifie la validité du coup (ligne, colonne, carré)
     * @param x
     * @param y
     * @param v
     * @return
     */
    public boolean checkValue(int x, int y, String v) {
        int god = (9 * x + y) / 9;
        //check ligne
        for (int i = 0; i < 9; ++i) {
            if(startGrid[9*god + i].equals(v)) {
                return false;
            }
        }
        //check colonne
        for (int j = 0; j < 9; ++j) {
            if(startGrid[god + 9*j].equals(v)) {
                return false;
            }
        }
        //check carré
        int carre = god / 3;
        if (startGrid[carre * 3].equals(v)) return false;
        if (startGrid[carre * 3 + 1].equals(v)) return false;
        if (startGrid[carre * 3 + 2].equals(v)) return false;
        if(startGrid[(carre+1)*3].equals(v)) return false;
        if(startGrid[(carre+1)*3 + 1].equals(v)) return false;
        if(startGrid[(carre+1)*3 + 2].equals(v)) return false;
        if(startGrid[(carre+2)*3].equals(v)) return false;
        if(startGrid[(carre+2)*3 + 1].equals(v)) return false;
        if(startGrid[(carre+2)*3 + 2].equals(v)) return false;
        return true;
    }

    public void addValue(int x, int y, String v) {
        startGrid[9*x +y] = v;
    }
}
