package database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/*
 * Egy olyan osztály amelynek adattagjai a játékos által jászott játék eredmményeit tartalmazza.
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Eredmeny {

    /**
     * A játékos sorszáma.
     */
    private Long id;

    /**
     * A játékos neve.
     */
    private String jatekos;

    /**
     * Nyert vagy veszített a játékos.
     */
    private boolean win;

    /**
     * A játékos által megtett lépés.
     */
    private int lepes;

    /**
     * Mikor fejezte be a játékot.
     */
    private String date;
}
