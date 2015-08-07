import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.sql.*;

public class RegIdManager {
	static Connection con;
	
	public static void writeToFile(String pNo, String regId){

        PreparedStatement ps;
		try {
			dbConnect();
			ps = con.prepareStatement("INSERT INTO chatServer.RegTable(Mobile, RegId) VALUES (?,?)");

	        ps.setString(1, pNo);
	        ps.setString(2, regId);
	        ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Set<String> readFromFile(String mobi) throws SQLException{
		dbConnect();
		Set<String> regIdSet = new HashSet<String>();
		String query = "Select * from chatServer.RegTable WHERE Mobile =" + mobi;

        Statement s = con.createStatement();
        ResultSet rs = s.executeQuery(query);
        
        while (rs.next()) {
			regIdSet.add(rs.getString(1));
		}
		return regIdSet;
		
		}
 	
	
	/**	
	 * creating database connection
	 **/
	protected static void dbConnect(){
		try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("loaded class");
            con = DriverManager.getConnection("jdbc:hsqldb:file:\\ChatServerDual\\db_script", "root", "");
            System.out.println("created con");
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        }
	}
}
