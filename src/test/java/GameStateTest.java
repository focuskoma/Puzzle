import org.junit.Test;
import state.GameState;
import static org.junit.jupiter.api.Assertions.*;

public class GameStateTest {

    @Test
    public void winTrueTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{1,2,3},
                 {4,5,6},
                 {7,8,0}};
        allas.setallas(tabla);
        assertTrue(allas.win());
    }

    @Test
    public void winFalseTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};
        allas.setallas(tabla);
        assertFalse(allas.win());
    }

    @Test
    public void uresTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};
        allas.setallas(tabla);
        int[] ures2 = new int[2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabla[i][j]==0){
                    ures2[0]=i;
                    ures2[1]=j;
                    break;
                }
            }
        }
        assertArrayEquals(ures2,allas.ures());
    }

    @Test
    public void balraTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};

        int[][] tabla2 = new int[][]
                {{5,2,3},
                 {7,6,0},
                 {4,8,1}};
        allas.setallas(tabla);
        allas.balra();
        assertArrayEquals(tabla2, allas.getallas());
    }

    @Test
    public void jobbraTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};

        int[][] tabla2 = new int[][]
                {{5,2,3},
                 {0,7,6},
                 {4,8,1}};
        allas.setallas(tabla);
        allas.jobbra();
        assertArrayEquals(tabla2, allas.getallas());
    }

    @Test
    public void leTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};

        int[][] tabla2 = new int[][]
                {{5,0,3},
                 {7,2,6},
                 {4,8,1}};
        allas.setallas(tabla);
        allas.le();
        assertArrayEquals(tabla2, allas.getallas());
    }

    @Test
    public void felTest(){
        GameState allas = new GameState();
        int[][] tabla = new int[][]
                {{5,2,3},
                 {7,0,6},
                 {4,8,1}};

        int[][] tabla2 = new int[][]
                {{5,2,3},
                 {7,8,6},
                 {4,0,1}};
        allas.setallas(tabla);
        allas.fel();
        assertArrayEquals(tabla2, allas.getallas());
    }
}
