package ru.ifmo.rain.issuer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;

@SpringBootApplication
public class IssuerApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(IssuerApplication.class, args);
	}

}
