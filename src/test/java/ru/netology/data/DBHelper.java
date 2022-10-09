package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBHelper {
    private DBHelper() {}
    private static QueryRunner queryRunner;
    private static Connection connection;

    @SneakyThrows
    public static void setup() {
        queryRunner = new QueryRunner();
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/app", "app", "password");

    }
    @SneakyThrows
    public static String getUserId(String login){
        setup();
        String userId = "SELECT id FROM users WHERE login = ?";
        return userId;
    }
    @SneakyThrows
    public static String getVerificationCode(String userId){
        setup();
        String code = "SELECT id, number, balance_in_kopecks FROM cards WHERE user_id = ?;";
        return code;
    }

}
