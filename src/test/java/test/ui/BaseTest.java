package test.ui;

import data.URL;
import org.junit.jupiter.api.BeforeEach;
import util.DBUtil;

import static com.codeborne.selenide.Selenide.open;

public class BaseTest {
    @BeforeEach
    public void setUp() {
        open(URL.getURL());
        DBUtil.clearingTable("order_entity");
        DBUtil.clearingTable("credit_request_entity");
        DBUtil.clearingTable("payment_entity");
    }
}
