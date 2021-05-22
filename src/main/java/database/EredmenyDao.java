package database;

import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.statement.GetGeneratedKeys;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;

import java.util.List;

@RegisterBeanMapper(Eredmeny.class)
public interface EredmenyDao {

    /**
     * Ez a lekérdezés  létrehozza az eredmeny táblát.
     */
    @SqlUpdate("""
            CREATE TABLE IF NOT EXISTS eredmeny (
            id IDENTITY PRIMARY KEY,
            jatekos VARCHAR NOT NULL,
            win BOOLEAN,
            lepes INTEGER NOT NULL,
            date VARCHAR NOT NULL
        )
        """
    )
    void ujtabla();

    /**
     * @param eredmeny típusó rekordot illeszt be a táblába.
     * @return az id-val.
     */
    @SqlUpdate("INSERT INTO eredmeny (id, jatekos, win, lepes, date) VALUES (:id, :jatekos, :win, :lepes, :date)")
    @GetGeneratedKeys
    Long beszur(@BindBean Eredmeny eredmeny);

    /**
     * @return a tábla összes elemével egy Eredmeny típusú listában.
     */
    @SqlQuery("SELECT * FROM eredmeny")
    List<Eredmeny> list();

    /**
     * @param id -val megegyező eredmeny elemet megkeresi, majd
     * @return az ehhez tartozó összes mezővel.
     */
    @SqlQuery("SELECT * FROM eredmeny WHERE id = :id")
    Eredmeny keresid(@Bind("id") long id);
}