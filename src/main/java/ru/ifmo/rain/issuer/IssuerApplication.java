package ru.ifmo.rain.issuer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class IssuerApplication {

	public static void main(String[] args) throws SQLException {
		final String user = "postgres";
		final String password = "1";
		final String url = "jdbc:postgresql://localhost:5432/checking_list";

		final Connection connection = DriverManager.getConnection(url, user, password);
		SpringApplication.run(IssuerApplication.class, args);

		try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM customers")){

		final ResultSet resultSet = statement.executeQuery();
		if(resultSet.next()){
		String byNumber = "account_number " + resultSet.getString(1);
		String byData = "data " + resultSet.getString(2);
		String byBalance = "balane " + resultSet.getString(3);
		String first_name = "first_name " + resultSet.getString(4);
		String second_name = "second_name " + resultSet.getString(5);
		String middle_name = "middle_name " + resultSet.getString(6);
		System.out.println(byNumber+" "+byData+" "+ byBalance+" "+first_name+" "+second_name+" "+middle_name);
		}
		}finally{
			connection.close();
		}
	}

}
