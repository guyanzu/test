package timetable;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBC {

	ArrayList<String> contacts;

	public JDBC() {

		contacts = new ArrayList<String>();

		System.out.println("-------- MySQL JDBC Connection Testing ------------");

		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your MySQL JDBC Driver?");
			e.printStackTrace();
			return;
		}

		System.out.println("MySQL JDBC Driver Registered!");
		Connection connection = null;

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/test", "root", "");

			Statement stmt = connection.createStatement();
			ResultSet rs;

			rs = stmt.executeQuery("SELECT * FROM testtable");
			while (rs.next()) {
				String prenom = rs.getString("prenom");
				contacts.add(prenom);
			}
			connection.close();

		} catch (SQLException e) {
			System.out.println("Connection Failed.");
			e.printStackTrace();
			return;
		}

		if (connection != null) {
			System.out.println("Connection Successful.");
		}
	}
}