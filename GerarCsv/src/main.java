import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.RestoreAction;

public class main {



public static void main(String[]args){
	conectar();
}

public static void conectar()  {
	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	String usuario = "";
	String senha = "";
	String url = "jdbc:sqlserver://ip:porta;databaseName=;user=" + usuario + ";password=" + senha + ";";
	try {
	    Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	    String sql = "select";
	    con = DriverManager.getConnection(url);
	    stmt = con.prepareStatement(sql);
	    rs = stmt.executeQuery();
	    StringBuilder builder = new StringBuilder();
	    String ColumnNamesList = "Cabeçalho";
	    builder.append(ColumnNamesList +"\n");
	    PrintWriter pw = null;
	    while (rs.next()) 
	    {
	    	String exemplo = rs.getString("nome_campo");
	    			
	    	try {
		    	pw = new PrintWriter(new File("Resultado.csv"));  
				builder.append(exemplo+";");
				builder.append('\n');
				pw.write(builder.toString());
				pw.close();
	    	}catch(FileNotFoundException fn)
	    	{
	    		System.out.println(fn);
	    	}
	
	    }
	    
		JOptionPane.showMessageDialog(null, "Arquivo Gerado!");
	    System.out.println("Conectado!");
	    

	    
	} catch (ClassNotFoundException e) {
	    System.out.println("JDBC não encontrado!");
	} catch (SQLException e) {
	    System.out.println("Erro de conexão: " + e);
	}
	
	
}

}



