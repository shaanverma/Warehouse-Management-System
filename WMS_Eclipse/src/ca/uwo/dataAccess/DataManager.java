package ca.uwo.dataAccess;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import ca.uwo.model.Item;

/**
 * @author kkontog, ktsiouni, mgrigori
 * This class is responsible for the data persistence, i.e., database connection, updates, etc.
 */
public class DataManager {
	private static DataManager instance = null;
	
	private Connection conn;

	/**
	 * connect to sqlite database, prints the error message if failed.
	 */
	public void connect() {
        conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:warehouse.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * create a new database.
	 */
	public void createNewDatabase() {
		
		if (conn != null) {
			DatabaseMetaData meta;
			try {
				meta = conn.getMetaData();
				System.out.println("The driver name is " + meta.getDriverName());
	            System.out.println("A new database has been created.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
        }   
    }
	
	/**
	 * create a table in the database.
	 */
	public void createNewTable() {
        
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS warehouses (\n"
                + "    id integer PRIMARY KEY,\n"
                + "    name text NOT NULL,\n"
                + "    quantity integer NOT NULL,\n"
                + "    unitPrice real\n"
                + ");";
        
        try {
                Statement stmt = conn.createStatement();
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
	
	/**
	 * retrieves an item from the database using name.
	 * @param itemName the name of the item
	 * @return data object of the item 
	 */
	public Item getItem(String itemName) {
		Item item = null;
		String sql = "SELECT id, name, quantity, unitPrice FROM warehouses WHERE name = ?";
        
        try (PreparedStatement pstmt  = conn.prepareStatement(sql)){
            
        	pstmt.setString(1, itemName);
        	ResultSet rs    = pstmt.executeQuery();
            rs.next();
            item = new Item(
            				rs.getInt("id"), 
            				rs.getString("name"), 
            				rs.getInt("quantity"), 
            				rs.getDouble("unitPrice")
            			   );
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
		return item;
	}
	
	/**
	 * insert an item into the database.
	 * @param data object of {@link ca.uwo.model.Item} class
	 */
	public void insertItem(Item item) {
		String sql = "INSERT INTO warehouses(name,quantity,unitPrice) VALUES(?,?,?)";
		 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, item.getName());
            pstmt.setInt(2, item.getAvailableQuantity());
            pstmt.setDouble(3, item.getPrice());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
	
	/**
	 * update an item in the database.
	 * @param item the item to be updated.
	 */
	public void updateItem(Item item) {
        String sql = "UPDATE warehouses SET quantity = ? WHERE name = ?";
 
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            // set the corresponding param
            pstmt.setInt(1, item.getAvailableQuantity());
            pstmt.setString(2, item.getName());
            // update 
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	/**
	 * there should be only one instance of DataManager class.
	 * @return the instance of DataManager.
	 */
	public static DataManager getInstance() {
		if (instance == null)
			instance = new DataManager();
		
		return instance;
	}
	
	/**
	 * constructor for DataManager class.
	 */
	private DataManager() {
		connect();
		createNewDatabase();
		createNewTable();
		
	}

}
