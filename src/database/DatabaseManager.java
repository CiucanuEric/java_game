package database;
import java.sql.*;
public class DatabaseManager {
    Connection c=null;
    Statement stmt=null;
    public void insertInt(int input,String where) {
        try {
            c = DriverManager.getConnection("jdbc:sqlite:save.db");
            stmt = c.createStatement();
            c.setAutoCommit(false);
            String sql = "UPDATE SAVED_INFO set " + where + " = " + input + " where ID=1";
            stmt.execute(sql);
            ResultSet rs = stmt.executeQuery("SELECT * FROM SAVED_INFO;");
            while (rs.next()) {
                int id = rs.getInt("id");
                int current_hp = rs.getInt("current_hp");
                int max_hp = rs.getInt("max_hp");
                System.out.println("ID = " + id);
                System.out.println("CURRENT_HP = " + current_hp);
                System.out.println("MAX_HP = " + max_hp);
                System.out.println();
                stmt.close();
                c.commit();
                c.close();
            }
        }
        catch(Exception e)
            {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
    }

}
