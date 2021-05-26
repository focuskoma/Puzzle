package state;

import javafx.scene.input.KeyCode;
import lombok.extern.log4j.Log4j2;
import java.util.Random;

/**
 * A játék működéséhez szükséges metódusokat tartalmazza.
 */
@Log4j2
public class GameState {

    private int[][] tabla;
    private static int lepes;

    /**
     * Példányosít egy 3X3-as mátrixot, és feltölti véletlenszerűen 0-tól 8-ig számokkal.
     */
    public GameState() {
        tabla = new int[][]
                {{1,2,3},
                 {4,5,6},
                 {7,8,0}};

        kever();
        lepes=0;

        /*  Ez a rész volt eredetileg a számok összekeverésének a kódja, de rájöttem, hogy
            olyan állásokat is tud generálni, amit nem lehet kirakni.

        ArrayList<Integer> szamok = new ArrayList<>();
        for (int i = 8; i >= 0; i--) {
            szamok.add(i);
        }
        Collections.shuffle(szamok);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                tabla[i][j] = szamok.get(0);
                szamok.remove(0);
            }
        }
         */

    }

    /**
     * Megvizsgálja a gameState állapot győztes állapot e.
     * @return logikai változóval.
     */
    public boolean win(){
        return tabla[0][0] == 1 && tabla[0][1] == 2 && tabla[0][2] == 3 &&
                tabla[1][0] == 4 && tabla[1][1] == 5 && tabla[1][2] == 6 &&
                tabla[2][0] == 7 && tabla[2][1] == 8 && tabla[2][2] == 0;
    }

    /**
     * Megkeresi a gameState objektum tabla mátrixában melyik indexeken található a 0-ás szám, majd
     * @return egy 2 elemű tömbbel amit tartalmazza az indexeket.
     */
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

    /**
     * A 0-tól jobbra eső számot balra tolja egy mezővel.
     */
    public void balra(){
        int a=ures()[0];
        int b=ures()[1];
        if (b!=2) {
            int seged = tabla[a][b + 1];
            tabla[a][b + 1] = 0;
            tabla[a][b] = seged;
            lepes++;
        }
    }

    /**
     * A 0-tól balra eső számot jobbra tolja egy mezővel.
     */
    public void jobbra(){
        int a=ures()[0];
        int b=ures()[1];
        if (b!=0) {
            int seged = tabla[a][b - 1];
            tabla[a][b - 1] = 0;
            tabla[a][b] = seged;
            lepes++;
        }
    }

    /**
     * A 0 feletti számot letolja egy mezővel.
     */
    public void le(){
        int a=ures()[0];
        int b=ures()[1];
        if (a!=0) {
            int seged = tabla[a - 1][b];
            tabla[a - 1][b] = 0;
            tabla[a][b] = seged;
            lepes++;
        }
    }

    /**
     * A 0 alatti számot feltolja egy mezővel.
     */
    public void fel(){
        int a=ures()[0];
        int b=ures()[1];
        if (a!=2) {
            int seged = tabla[a + 1][b];
            tabla[a + 1][b] = 0;
            tabla[a][b] = seged;
            lepes++;
        }
    }

    /**
     * Vizsgálja melyik betűt nyomtuk meg, és ennek függvényében futtat eljárásokat.
     * @param irany a leütött billentyű.
     */
    public void mozgatas(KeyCode irany){
        switch (irany.toString()) {
            case "A": balra(); break;
            case "D": jobbra(); break;
            case "S": le(); break;
            case "W": fel(); break;
        }
    }

    /**
     * @return a lépések számával.
     */
    public int getlepes() {
        return this.lepes;
    }

    /**
     * @return a 3X3-a tábla mátrixal
     */
    public int[][] getallas(){
        return this.tabla;
    }

    /**
     * @param tabla-t átadja a gameState objektum tabla adattagjának.
     */
    public void setallas(int[][] tabla){
        this.tabla = tabla;
    }

    /**
     * A tabla matrix összekeverése egy kirakható állásra.
     */
    public void kever(){
        log.info("A puzzle összekeverése.");
        Random rand = new Random();
        int x;
        for (int i=0;i<1000;i++){
            x = rand.nextInt(4);
            switch (x){
                case 0: balra(); break;
                case 1: jobbra(); break;
                case 2: le(); break;
                case 3: fel(); break;
            }
        }
    }

    /**
     * Létrehoz egy olyan tabla-t amelyik 1 lépés híján győztes állapot.
     */
    public void gyoztespoz(){
        tabla[0][0]=1;tabla[0][1]=2;tabla[0][2]=3;
        tabla[1][0]=4;tabla[1][1]=5;tabla[1][2]=6;
        tabla[2][0]=7;tabla[2][1]=0;tabla[2][2]=8;
    }
}

