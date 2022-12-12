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

    @SneakyThrows
    private static Connection getConnMySQL() {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "app", "9mREsvXDs9Gk89EF");
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