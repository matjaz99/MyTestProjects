package si.matjazcerkvenik.test.derby.addressbook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.derby.jdbc.EmbeddedDriver;

public class KontaktDAO {
	
	static {
		try {
			DriverManager.registerDriver(new EmbeddedDriver());
			String derby = "jdbc:derby:delavnica;create=true";
			Connection conn = DriverManager.getConnection(derby);
			
			conn.createStatement().execute("create table kontakt(" +
					"id INT NOT NULL GENERATED ALWAYS AS IDENTITY, " +
					"ime varchar(45), " +
					"priimek varchar(45), " +
					"telefon varchar(40))");
			conn.close();
			
		} catch (Exception e) {
		}
	}
	
	private Connection getConnection() throws SQLException {
		
//		String mysql = "jdbc:mysql://localhost/delavnica";
		String derby = "jdbc:derby:delavnica;create=true";
		
		
//		return DriverManager.getConnection(mysql, "root", "admin");
		return DriverManager.getConnection(derby);
		
	}
	
	public void save(Kontakt k) {
		String ins = "insert into kontakt(ime, priimek, telefon) values (?,?,?)";
		String upd = "update kontakt set ime=?, priimek=?, telefon=? where id=?";
		
		try {
			Connection c = getConnection();
			
			if (k.getId() <= 0) {
				// insert
				PreparedStatement ps = c.prepareStatement(ins, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, k.getIme());
				ps.setString(2, k.getPriimek());
				ps.setString(3, k.getTelefon());
				ps.execute();
				
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					k.setId(rs.getInt(1));
				}
				
				ps.close();
				
			} else {
				// update
				PreparedStatement ps = c.prepareStatement(upd);
				ps.setString(1, k.getIme());
				ps.setString(2, k.getPriimek());
				ps.setString(3, k.getTelefon());
				ps.setInt(4, k.getId());
				ps.execute();
				ps.close();
				
			}
			
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Kontakt> getAll() {
		List<Kontakt> seznam = new ArrayList<Kontakt>();
		
		try {
			Connection c = getConnection();
			ResultSet rs = c.createStatement().executeQuery("select * from kontakt");
			
			while (rs.next()) {
				Kontakt k = new Kontakt();
				k.setIme(rs.getString("ime"));
				k.setPriimek(rs.getString("priimek"));
				k.setTelefon(rs.getString("telefon"));
				k.setId(rs.getInt("id"));
				seznam.add(k);
			}
			rs.close();
			
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return seznam;
	}
	
	public Kontakt find(int id) {
		
		Kontakt k = null;
		
		try {
			Connection c = getConnection();
			ResultSet rs = c.createStatement().executeQuery("select * from kontakt where id="+id);
			
			while (rs.next()) {
				k = new Kontakt();
				k.setIme(rs.getString("ime"));
				k.setPriimek(rs.getString("priimek"));
				k.setTelefon(rs.getString("telefon"));
				k.setId(id);
			}
			rs.close();
			
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return k;
	}
	
	public void delete(int id) {
		
		String del = "delete from kontakt where id=?";
		
		try {
			Connection c = getConnection();
			PreparedStatement ps = c.prepareStatement(del);
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
