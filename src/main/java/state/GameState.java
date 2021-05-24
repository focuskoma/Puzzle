package state;

import javafx.scene.input.KeyCode;

import java.util.ArrayList;
import java.util.Collections;

public class GameState {

    private int[][] tabla;
    private static int lepes;

    public GameState() {
        lepes=0;
        ArrayList<Integer> szamok = new ArrayList<>();
        for (int i = 8; i >= 0; i--) {
            szamok.add(i);
        }
        Collections.shuffle(szamok);

        tabla = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = szamok.get(0);
                szamok.remove(0);
            }
        }
    }

    public boolean win(){
        return tabla[0][0] == 1 && tabla[0][1] == 2 && tabla[0][2] == 3 &&
                tabla[1][0] == 4 && tabla[1][1] == 5 && tabla[1][2] == 6 &&
                tabla[2][0] == 7 && tabla[2][1] == 8 && tabla[2][2] == 0;
    }

    public int[] ures(){
        int[] index = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j]==0){
                    index[0]=i;
                    index[1]=j;
                    break;
                }
            }
        }
        return index;
    }

    public void balra(){
        int a=ures()[0];
        int b=ures()[1];
        if (b!=2) {
            int seged = tabla[a][b + 1];
            tabla[a][b + 1] = 0;
            tabla[a][b] = seged;
            lepes++;
        } else{
            System.out.println("Oda nem lehet lépni.");
        }
    }

    public void jobbra(){
        int a=ures()[0];
        int b=ures()[1];
        if (b!=0) {
            int seged = tabla[a][b - 1];
            tabla[a][b - 1] = 0;
            tabla[a][b] = seged;
            lepes++;
        } else{
            System.out.println("Oda nem lehet lépni.");
        }

    }

    public void le(){
        int a=ures()[0];
        int b=ures()[1];
        if (a!=0) {
            int seged = tabla[a - 1][b];
            tabla[a - 1][b] = 0;
            tabla[a][b] = seged;
            lepes++;
        } else{
            System.out.println("Oda nem lehet lépni.");
        }
    }

    public void fel(){
        int a=ures()[0];
        int b=ures()[1];
        if (a!=2) {
            int seged = tabla[a + 1][b];
            tabla[a + 1][b] = 0;
            tabla[a][b] = seged;
            lepes++;
        } else{
            System.out.println("Oda nem lehet lépni.");
        }
    }

    public void mozgatas(KeyCode irany){
        switch (irany.toString()) {
            case "A": balra(); break;
            case "D": jobbra(); break;
            case "S": le(); break;
            case "W": fel(); break;
        }
    }

    public int getlepes() {
        return this.lepes;
    }

    public int[][] getallas(){
        return this.tabla;
    }

    public void gyoztespoz(){
        tabla[0][0]=1;tabla[0][1]=2;tabla[0][2]=3;
        tabla[1][0]=4;tabla[1][1]=5;tabla[1][2]=6;
        tabla[2][0]=7;tabla[2][1]=0;tabla[2][2]=8;
    }
}

