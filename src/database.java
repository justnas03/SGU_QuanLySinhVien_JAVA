import java.awt.BorderLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import java.sql.ResultSetMetaData;

public class database {
	
	public static final String DB_URL = "jdbc:derby:HocSinhDB";
	
	//Connection and Statement
	public static Connection conn = null;
	public static Statement stm = null;
    
	public database() throws SQLException {
		createConnection();
	}
	
	public static Statement getStatement() {
        return stm;
	}
    public static Connection getConnection() {
        return conn;
    }
    
	public static Connection createConnection() throws SQLException{
		//Create Connection
		conn = DriverManager.getConnection(DB_URL);
		stm = conn.createStatement();
		System.out.println("Connection to HocSinhDB is created.");	
		return conn;
    }
	
	public static void Insert(String name, String age, String address,String gpa) throws SQLException{
        stm.executeUpdate("insert into HocSinh values(default,'"+name+"',"+age+",'"+address+"',"+gpa+")");
    }
	
    public static void DropTable(String tablename) throws SQLException{
    	stm.executeUpdate("DROP TABLE " + tablename);
    }
    
    public static void DeleteData(String dieukien) throws SQLException{
        getStatement().executeUpdate("DELETE FROM HocSinh WHERE ID=" + dieukien+"");
    }
    
    public static void edit(String name, String age, String address, String gpa, String id) throws SQLException {
    	getStatement().executeUpdate("update HocSinh set name='"+name+"',age="+age+",address='"+address+"',gpa="+gpa+" where id="+id);
    }
    
    public static ResultSet Select(String selection, String tablename, String dieukien) throws SQLException {
    	ResultSet result = stm.executeQuery("SELECT "+ selection +" FROM "+ tablename + "WHERE " + dieukien);
        return result;
    }
    
    public static void Sort(String colname) throws SQLException {
    	ResultSet sorted = stm.executeQuery("Select * From HocSinh Order By "+colname+" ASC");
    	printSortedTable(sorted);
    }
    
    public static ResultSet Query(String statement) {
    	ResultSet RS = null;
		try {
			RS = database.getStatement().executeQuery(statement);
		} catch (SQLException e) {
			e.printStackTrace();  	
		}
		return RS;
    }
    
    
    public static void printTable() { //for delete-add-edit buttons
		//remove all table data elements
		GUI.getModel().getDataVector().removeAllElements();
		GUI.getModel().fireTableDataChanged();
		ResultSet RS = database.Query("select * from HocSinh");
		//print table
		try {
            while (RS.next()) {
                String[] rows = new String[5];
                rows[0] = RS.getString("id");
                rows[1] = RS.getString("name");
                rows[2] = RS.getString("age");
                rows[3] = RS.getString("address");
                rows[4] = RS.getString("GPA");
                GUI.getModel().addRow(rows);
            }
        } catch (SQLException e1) {
        	e1.printStackTrace();
        }
	}
    
    private static void printSortedTable(ResultSet RS) { //for sorted buttons
		//remove all table data elements
		GUI.getModel().getDataVector().removeAllElements();
		GUI.getModel().fireTableDataChanged();
		//print table
		try {
            while (RS.next()) {
                String[] rows = new String[5];
                rows[0] = RS.getString("id");
                rows[1] = RS.getString("name");
                rows[2] = RS.getString("age");
                rows[3] = RS.getString("address");
                rows[4] = RS.getString("GPA");
                GUI.getModel().addRow(rows);
            }
        } catch (SQLException e1) {
        	e1.printStackTrace();
        }
	}
    
    public static void displayTableData() { //for GUI table
    	try {
        	ResultSet RS = database.Query("select * from HocSinh");
 			while (RS.next()){
 				String S[] = {RS.getString(1),RS.getString(2),RS.getString(3),RS.getString(4),RS.getString(5)};
 				GUI.getModel().addRow(S);
 			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
    
    public static void CreateTable() throws SQLException {
    	String createTable = "CREATE TABLE HocSinh( "
		         + "id INT NOT NULL, "
		         + "name VARCHAR(40), "
		         + "age INT, "
		         + "address VARCHAR(40), "
		         + "gpa DOUBLE, "
		         + "PRIMARY KEY (id))";
		stm.execute(createTable);
    }
	

	
}
