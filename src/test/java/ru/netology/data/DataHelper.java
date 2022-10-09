package ru.netology.data;

import lombok.SneakyThrows;
import lombok.Value;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataHelper {
    private DataHelper() {}
    private static QueryRunner runner = new QueryRunner();
    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    public static AuthInfo getOtherAuthInfo(AuthInfo original) {
        return new AuthInfo("petya", "123qwerty");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    @SneakyThrows
    public static VerificationCode getVerificationCode(AuthInfo authInfo) {
        var login = authInfo.getLogin();
        var authId = "SELECT id FROM users WHERE login = ?";
        var codeSQL = "SELECT code FROM auth_codes JOIN users ON auth_codes.user_id = ?";

        try (var conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/app-deadline", "user", "pass")) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return new VerificationCode;
        }
    }
}
