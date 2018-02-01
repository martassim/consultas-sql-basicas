package curso.mysql;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class Consultar {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		
Class.forName("com.mysql.jdbc.Driver");

Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca", "root", "");

Statement st = conn.createStatement();


ResultSet rs = st.executeQuery("select * from libros");
while(rs.next()) {
	System.out.println("titulo :" + rs.getString("titulo")
					+" , autor: " + rs.getString("autor")
					+", precio: " + rs.getFloat("precio")
					+"€, fecha: "+ rs.getDate("fecha_publicacion")
					);
				}
System.out.println("/Statement ---------------------------------------------");

//int insertar = st.executeUpdate("insert into libros (titulo, autor, precio,fecha_publicacion ) values ('el ultimo deseo','andrzej sapkowski',15.95, '1990-01-01')");
//System.out.println("fila insertada "+insertar);

System.out.println("------------------------------------------------");

 PreparedStatement psmt = conn.prepareStatement("select*from libros where titulo = ?");
 		psmt.setString(1, "el quijote");
 	
 ResultSet rs1 =psmt.executeQuery();
 while(rs1.next()) {
 	System.out.println("titulo :" + rs1.getString("titulo")
 					+" , autor: " + rs1.getString("autor")
 					+", precio: " + rs1.getFloat("precio")
 					+"€, fecha: "+ rs1.getDate("fecha_publicacion")
 					);
 				}
 System.out.println("/prepare statement ---------------------------------------------");
 
 CallableStatement cs = conn.prepareCall("{call lista_libros_por_autor(?)}");
 cs.setString(1, "andrzej sapkowski");
 ResultSet rs2 =cs.executeQuery();
 while(rs2.next()) {
 	System.out.println("titulo :" + rs2.getString("titulo")
 					+" , autor: " + rs2.getString("autor")
 					+", precio: " + rs2.getFloat("precio")
 					+"€, fecha: "+ rs2.getDate("fecha_publicacion")
 					);
 				}
 
 System.out.println("/callable statement ---------------------------------------------");
}

}
