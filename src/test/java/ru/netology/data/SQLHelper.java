package ru.netology.data;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLHelper {
    private static QueryRunner runner = new QueryRunner();

    private SQLHelper() {
    }

    static String url = System.getProperty("db.url");
    static String user = System.getProperty("db.user");
    static String password = System.getProperty("db.password");

    @SneakyThrows
    private static Connection getConnMySQL() {

        return DriverManager.getConnection(url, user, password);
    }

    @SneakyThrows
    public static String getOperationStatusOfPayment() {
        var codeSQL = "SELECT status FROM payment_entity";
        try (var conn = getConnMySQL()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return (result);
        }
    }

    @SneakyThrows
    public static String getOperationStatusOfCredit() {
        var codeSQL = "SELECT status FROM credit_request_entity";
        try (var conn = getConnMySQL()) {
            var result = runner.query(conn, codeSQL, new ScalarHandler<String>());
            return (result);
        }
    }

    @SneakyThrows
    public static void cleanDatabase() {
        var connection = getConnMySQL();
        runner.execute(connection, "DELETE FROM credit_request_entity");
        runner.execute(connection, "DELETE FROM order_entity");
        runner.execute(connection, "DELETE FROM payment_entity");
    }
}