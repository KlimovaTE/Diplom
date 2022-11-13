package util;

import lombok.SneakyThrows;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.DriverManager;

public class DBUtil {
    @SneakyThrows
    public static void clearingTable(String tableName) {
        var runner = new QueryRunner();
        var codeSQL = "Delete from " + tableName + ";";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            runner.update(conn, codeSQL);
        }
    }

    @SneakyThrows
    public static String getPaymentStatus() {
        var runner = new QueryRunner();
        var codeSQL = "SELECT status FROM payment_entity;";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var paymentStatus = runner.query(conn, codeSQL, new ScalarHandler<>());
            return paymentStatus.toString();
        }
    }

    @SneakyThrows
    public static String countOrdersIfPayment() {
        var runner = new QueryRunner();
        var counterSQL = "SELECT COUNT(*) FROM order_entity WHERE payment_id = (SELECT transaction_id FROM payment_entity);";
        try (
                var conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/app", "app", "pass");
        ) {
            var counter = runner.query(conn, counterSQL, new ScalarHandler<>());
            return counter.toString();
        }

    }
}
