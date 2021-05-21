package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main{

    public static int[][] tabla;
    public static int lepes=0;

    public Main(){
        ArrayList<Integer> szamok = new ArrayList<>();
        for (int i = 8; i >= 0; i--) {
            szamok.add(i);
        }
        Collections.shuffle(szamok);

        tabla = new int[3][3];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j]=szamok.get(0);
                szamok.remove(0);
            }
        }

    }

    public boolean win(){
        return tabla[0][0] == 1 && tabla[0][1] == 2 && tabla[0][2] == 3 &&
                tabla[1][0] == 4 && tabla[1][1] == 5 && tabla[1][2] == 6 &&
                tabla[2][0] == 7 && tabla[2][1] == 8 && tabla[2][2] == 0;
    }

    public void rajzol(){
        for (int i = 0; i < 3; i++) {
            System.out.println();
            for (int j = 0; j < 3; j++) {
                System.out.print(tabla[i][j] + " ");
            }
        }
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
            System.out.println("ures index: " + a + " " + b);
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
            System.out.println("ures index: " + a + " " + b);
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
            System.out.println("ures index: " + a + " " + b);
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
            System.out.println("ures index: " + a + " " + b);
            int seged = tabla[a + 1][b];
            tabla[a + 1][b] = 0;
            tabla[a][b] = seged;
            lepes++;
        } else{
            System.out.println("Oda nem lehet lépni.");
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Main main = new Main();

        String be="";

        System.out.println("\n\nIrányító gombok: W,A,S,D\n\nE-vel kilép.\n\n");


        main.rajzol();

        while (be!="e"){
            if (!main.win()) {
                System.out.print("       merre: ");
                be = sc.next();
                switch (be) {
                    case "a":
                        main.balra();
                        break;
                    case "d":
                        main.jobbra();
                        break;
                    case "s":
                        main.le();
                        break;
                    case "w":
                        main.fel();
                        break;
                }
                main.rajzol();
            } else {
                System.out.println("\n\nVége a játéknak.");
                System.out.println("Lépésszám: " + lepes);
                break;
            }
        }



    }
}