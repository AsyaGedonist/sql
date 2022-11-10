package ru.netology.data;

import com.github.javafaker.Faker;
import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class DBHelper {
    private DBHelper() {}
    private static QueryRunner queryRunner;
    private static Connection connection;
    static Faker faker = new Faker(new Locale("ru"));
    @SneakyThrows
    public static void setup() {
        queryRunner = new QueryRunner();
        connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");

    }
    @SneakyThrows
    public static String getVerificationCode(String login){
        setup();
        String code = "SELECT code FROM auth_codes JOIN users ON auth_codes.user_id = users.id WHERE login = ?;";
        return queryRunner.query(connection, code, new ScalarHandler<>(), login);
    }
    @SneakyThrows
    public static void cleanAuth(){
        setup();
        queryRunner.update(connection, "DELETE FROM auth_codes;");
    }

    @SneakyThrows
    public static void cleanBase(){
        setup();
        queryRunner.update(connection, "DELETE FROM cards;");
        queryRunner.update(connection, "DELETE FROM users;");
    }

    public static String generateFakeVerificationCode(String code) {
        String fakeCode = faker.number().digits(5);
        while (fakeCode == code) {
            fakeCode = faker.number().digits(5);
        }
        return fakeCode;
    }

    @SneakyThrows
    public static String getStatus(String login){
        setup();
        String status = "SELECT status FROM users WHERE login = ?;";
        return queryRunner.query(connection, status, new ScalarHandler<>(), login);
    }
}
