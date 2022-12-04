package com.vdoichev.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
    private static Connection con;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost/db_for_abp?user=root&password=siavaismail";
            return DriverManager.getConnection(url);
        }catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException ex){
            System.out.println("Помилка з'єднання з БД DB_FOR_ABP");
        }

        return null;
    }
}
