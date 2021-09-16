import java.sql.*;

import org.junit.jupiter.api.*;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DataBaseConnection {
    private static Statement stmt;
    static Connection con = null;

    @Test
    public void checkKeyThreeHasCorrectValue(){
        TreeMap<String,String> sql = createSimpleRequest("select * from scientist;");
        System.out.println(sql);
        assertEquals ("marie curie", sql.get("3"), "Marie Curie isn't in the house.");
    }

    @Test
    public void checkKeyTwoHasCorrectValue(){
        TreeMap<String,String> sql = createSimpleRequest("select s.id, s.lastname, y.years_of_life from scientist as s inner join years as y on s.id = y.id;");
        System.out.println(sql);
        assertEquals ("newton 1643-1727", sql.get("2"), "Isaac Newton isn't in the house.");
    }


    @AfterAll
    public static void closeConnection() throws Exception {
        if (con != null) {con.close();
        }
    }

    public TreeMap<String, String> createSimpleRequest(String request){
        TreeMap<String, String> sqlRequestResult = new TreeMap<>();

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/mytest_schema","dbadmin", "Banana5");

            Statement stmt = con.createStatement();

            ResultSet result = stmt.executeQuery(request);
            while (result.next()){
                sqlRequestResult.put(result.getString(1),result.getString(2) + " " + result.getString(3));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return sqlRequestResult;
    }

}
