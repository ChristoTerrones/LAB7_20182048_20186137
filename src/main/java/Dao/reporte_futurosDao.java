package Daos;

import Beans.reporte;

import java.sql.*;
import java.util.ArrayList;

public class reporte_futurosDao {
    private static String user = "root";
    private static String pass = "root";
    private static String url = "jdbc:mysql://localhost:3306/sakila";

    public ArrayList<reporte> obtenerReporte() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ArrayList<reporte> listaReporte = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, pass);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("select a.actor_id as `ID`,\n" +
                     "concat(a.first_name,' ', a.last_name) as 'Nombre Completo', count(l.language_id) as `Cantidad Peliculas`, m.`Categorias` as 'Cantidad Categorias' \n" +
                     "from sakila.actor a\n" +
                     "inner join sakila.film_actor k on (k.actor_id=a.actor_id)\n" +
                     "inner join sakila.film f on (f.film_id=k.film_id)\n" +
                     "inner join sakila.language l on (l.language_id=f.language_id)\n" +
                     "inner join (select n.actor_id, count(n.category_id) as `Categorias` from (select distinct c.category_id, a.actor_id, a.first_name,\n" +
                     "\t\t\t\tc.name\n" +
                     "\t\tfrom sakila.actor a\n" +
                     "\t\t\tinner join sakila.film_actor k on (k.actor_id=a.actor_id)\n" +
                     "\t\t\tinner join sakila.film f on (f.film_id=k.film_id)\n" +
                     "\t\t\tinner join sakila.film_category j on (j.film_id=f.film_id)\n" +
                     "\t\t\tinner join sakila.category c on (c.category_id=j.category_id)\n" +
                     "            order by a.actor_id) n\n" +
                     "            group by n.actor_id) m on (m.actor_id = a.actor_id)\n" +
                     "where (upper(a.first_name) like \'%LO%\' or upper(a.last_name) like \'%LO%\') and l.name='English'\n" +
                     "group by `ID`\n" +
                     "having `Cantidad Peliculas`>20 and `Cantidad Categorias`>12\n" +
                     "order by `ID`;")) {
            while (rs.next()) {
                int idActor = rs.getInt(1);
                String nombre = rs.getString(2);
                int ncategoria = rs.getInt(3);
                int npeliculas = rs.getInt(4);
                listaReporte.add(new reporte(idActor,nombre,ncategoria,npeliculas));
            }

        } catch (SQLException e) {
            System.out.println("No se pudo realizar la busqueda");
        }
        return listaReporte;
    }

}
