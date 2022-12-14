package util;

import lombok.SneakyThrows;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBUtil {
    private static final String url = System.getProperty("spring.datasource.url");
    private static final String user = System.getProperty("spring.datasource.username");
    private static final String passwd = System.getProperty("spring.datasource.password");

    @SneakyThrows
    private static Connection conn() {
        return DriverManager.getConnection(url, user, passwd);
    }

    @SneakyThrows
    public static void clearingTable(String tableName) {
        var runner = new QueryRunner();
        var codeSQL = "Delete from " + tableName + ";";
        runner.update(conn(), codeSQL);
    }

    @SneakyThrows
    public static String getOperationStatus(String tableName) {
        System.out.println(tableName);
        var runner = new QueryRunner();
        var codeSQL = "SELECT status FROM " + tableName + ";";
        var paymentStatus = runner.query(conn(), codeSQL, new ScalarHandler<>());
        return paymentStatus.toString();
    }

    @SneakyThrows
    public static Object countOrdersIfPayment() {
        var runner = new QueryRunner();
        var counterSQL = "SELECT COUNT(*) FROM order_entity WHERE payment_id = (SELECT transaction_id FROM payment_entity);";
        var count = runner.query(conn(), counterSQL, new ScalarHandler<>());
        return count;
    }


    @SneakyThrows
    public static Object countOrdersIfCredit() {
        var runner = new QueryRunner();
        var counterSQL = "SELECT COUNT(*) FROM order_entity WHERE credit_id = (SELECT bank_id FROM credit_request_entity);";
        var count = runner.query(conn(), counterSQL, new ScalarHandler<>());
        return count;
    }
}
