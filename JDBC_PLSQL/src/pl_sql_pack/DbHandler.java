package pl_sql_pack;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
//import java.time.format.DateTimeFormatter

public class DbHandler {
	
	
	
	public static void  hivoNev() {
		
		Connection conn = connect();
		
		String procedureCall = "{ call Hivas_hivonev(?, ?) }";

        try ( CallableStatement stmt = conn.prepareCall(procedureCall)) {

         
            stmt.setDate(1, java.sql.Date.valueOf("2023.05.28"));
            stmt.setString(2, "Kis Janos");

            
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs != null) {
                while (rs.next()) {
                    int hivasId = rs.getInt("HivasID");
                    String hivoNev = rs.getString("HivoNev");
                    Date datum = rs.getDate("Datum");
     
                    System.out.println("Hívás ID: " + hivasId + ", Hívó név: " + hivoNev + ", Dátum: " + datum);
                }
            } else {
                System.out.println("Nincs találat..");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        disconnect(conn);
		
				
		
	}
	
	
	public static void hivasSzam(String actualDate) {
		
		Connection conn = connect();
		
		java.sql.Date hivDate = java.sql.Date.valueOf(actualDate);

		try (CallableStatement stmt = conn.prepareCall("{ ? = call Hivasok_szama(?) }")) {

			stmt.registerOutParameter(1, Types.INTEGER);

			stmt.setDate(2, hivDate);

			stmt.execute();

			int hivasokSzama = stmt.getInt(1);

			if (stmt.wasNull()) {
				System.out.println("Null.");
			} else {
				System.out.println("A hivasok szama " + hivasokSzama);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void hivasSzures1() {
		
		Connection conn = connect();
		
		String procedureCall = "{ call Hivas_hivonev(?, ?) }";

        try ( CallableStatement stmt = conn.prepareCall(procedureCall)) {

         
            stmt.setDate(1, java.sql.Date.valueOf("2023.05.28"));
            stmt.setString(2, "Kis Janos");

            
            stmt.execute();

            ResultSet rs = stmt.getResultSet();
            if (rs != null) {
                while (rs.next()) {
                    int hivasId = rs.getInt("HivasID");
                    String hivoNev = rs.getString("HivoNev");
                    Date datum = rs.getDate("Datum");
     
                    System.out.println("Hívás ID: " + hivasId + ", Hívó név: " + hivoNev + ", Dátum: " + datum);
                }
            } else {
                System.out.println("Nincs találat..");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
        disconnect(conn);
		
	}
	
	
	public static void hivasBeolvas() {
		
		Connection conn = connect();
		try {
			CallableStatement cstmt = conn.prepareCall("{call MENTOSZOLGALAT.HIVAS_BEOLVAS(?)}");
			cstmt.registerOutParameter(1, Types.INTEGER);
			cstmt.execute();
			int error = cstmt.getInt(1);
			if(error!=0) {
				throw new SQLException("hiba");
			} else System.out.println("siekres.");
			cstmt.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		disconnect(conn);
	}
	
	
	public static void randomGeneralMentes() {
		
		Connection conn = connect();
		try {
			CallableStatement cstmt = conn.prepareCall("{call MENTOSZOLGALAT.MENTES_FELTOLT()}");
			
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.setInt(1, 0);
			cstmt.execute();
			int error = cstmt.getInt(2);
			if(error!=0) {
				throw new SQLException("HIBA");
			}
			System.out.println(" sikeres generalas");
			cstmt.close();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		disconnect(conn);
		
	}
	
	
	public static void randomGeneralMentos() {
		
		Connection conn = connect();
		try {
			CallableStatement cstmt = conn.prepareCall("{call MENTOSZOLGALAT.MENTOS_FELTOLT()}");
			
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.setInt(1, 0);
			cstmt.execute();
			int error = cstmt.getInt(2);
			if(error!=0) {
				throw new SQLException("HIBA");
			}
			System.out.println(" sikeres generalas");
			cstmt.close();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		disconnect(conn);
		
	}
	
	
	
	public static void randomGeneralHivas() {
		
		Connection conn = connect();
		try {
			CallableStatement cstmt = conn.prepareCall("{call MENTOSZOLGALAT.HIVAS_FELTOLT_RANDOM()}");
			
			cstmt.registerOutParameter(2, Types.INTEGER);
			cstmt.setInt(1, 0);
			cstmt.execute();
			int error = cstmt.getInt(2);
			if(error!=0) {
				throw new SQLException("HIBA");
			}
			System.out.println(" sikeres generalas");
			cstmt.close();
		}catch(SQLException ex) {
			
			System.out.println(ex.getMessage());
		}
		disconnect(conn);
		
	}
	
	
	
	
	public static void Kiir() {
		
		System.out.println("test");
	}
	
	
	
	//test 
	
	public static void hivasSzures2() {
		
		Connection conn = connect();
		try {
			CallableStatement cstmt = conn.prepareCall("{call MENTOSZOLGALAT.HIVAS(?, ?, ?)}");
			cstmt.registerOutParameter(3, Types.REF_CURSOR);
			String beginning = null;
			cstmt.setTimestamp(1, Timestamp.valueOf(beginning));
			String end = null;
			cstmt.setTimestamp(2, Timestamp.valueOf(end));
			cstmt.execute();
			ResultSet rs = (ResultSet)cstmt.getObject(3);
			System.out.format("%4s%20s%20s%15s%6s%30s%10s%10s%n", "id","időpont", "név", "telefon", "irsz", "cím");
			while(rs.next()) {
				DateTimeFormatter dtf = null;
				System.out.format("%4d%20s%20s%15s%6s%30s%10d%10s%n", rs.getInt(1), rs.getTimestamp(2).toLocalDateTime().format(dtf), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getString(8));
			}
			rs.close();
			cstmt.close();
		}catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		disconnect(conn);
	}
	
	
	
	public static void UgyeletesLekerdez() {
		
		Connection connect = connect();
		
		 String userInputDate = "2023.05.26"; 
	       

	       
	            String sqlQuery = "SELECT UgyeletesNev FROM HIVAS WHERE TRUNC(Datum) = ?";
	            try (PreparedStatement statement = connect.prepareStatement(sqlQuery)) {
	                statement.setDate(1, Date.valueOf(userInputDate));

	                try (ResultSet resultSet = statement.executeQuery()) {
	                    if (resultSet.next()) {
	                        String ugyeletesNev = resultSet.getString("UgyeletesNev");
	                        System.out.println("Az ugyeletes neve: " + ugyeletesNev);
	                    } else {
	                        System.out.println("Nincs találat az adott dátumhoz.");
	                    }
	                }
	            } catch (SQLException e) {
					e.printStackTrace();
				}
	        
	    }
		
		
	private static  Connection connect() {
		
		String connectionString = "jdbc:oracle:thin:@193.6.5.58:1521:xe";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(connectionString, "H23_H2Z4X3", "H2Z4X3_A9");
			System.out.println("sikeres kapcsolodas");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
		return conn;
		
	}
	
	private static void disconnect(Connection conn) {
		try {
			if(conn!=null) {
				conn.close();
			}
			System.out.println("sikeres lekapcsolodas");
		} catch(SQLException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	

	
			
		

}
