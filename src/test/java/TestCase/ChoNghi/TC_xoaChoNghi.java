package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import PageObjects.QLChoNghi.xoaChoNghiPage;
import PageObjects.Login.loginPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TC_xoaChoNghi {
    timKiemChoNghiPage timkiemChonghi;
    PageObjects.QLChoNghi.xoaChoNghiPage xoaChoNghiPage;

    @BeforeTest
    public void beforeTest() {
        System.out.println("Pre-condition");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();

        loginPage loginPage = new loginPage();
        loginPage.openLoginPage();
        loginPage.login(Constant.username, Constant.password);
        WaitTime.sleep(2000);

        timkiemChonghi = new timKiemChoNghiPage();
        xoaChoNghiPage = new xoaChoNghiPage();
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC01_xoaChoNghi_Successfully() {
        System.out.println("FUDL002 - Xác minh rằng hệ thống thực hiện thao tác xóa khi người dùng nhấn button [Xác nhận]");

        String tenChoNghiCanXoa = "Ngày Bình Yên";
        boolean isExist = timkiemChonghi.timKiemChoNghi(tenChoNghiCanXoa);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        xoaChoNghiPage.clickXoa();
        xoaChoNghiPage.xacNhanXoa();

        Assert.assertEquals(xoaChoNghiPage.getAlertText(), "Xóa chỗ nghỉ thành công");
        List<WebElement> ketQuaTimKiem = xoaChoNghiPage.timKiemKetQua(tenChoNghiCanXoa);
        Assert.assertTrue(ketQuaTimKiem.isEmpty(), "Chỗ nghỉ vẫn còn tồn tại sau khi xóa!");
        xoaChoNghiPage.xoaTimkiem();
    }

    @Test
    public void TC02_xoaChoNghi_Cancel() {
        System.out.println("FUDL003 - Xác minh rằng hệ thống không thực hiện thao tác xóa và đóng pop-up khi người dùng nhấn button [Hủy]");

        String tenChoNghi = "Mikazuki Hội An";
        boolean isExist = timkiemChonghi.timKiemChoNghi(tenChoNghi);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        xoaChoNghiPage.clickXoa();
        xoaChoNghiPage.nhanNutHuyXoa();

        List<WebElement> ketQuaTimKiem = xoaChoNghiPage.timKiemKetQua(tenChoNghi);
        Assert.assertFalse(ketQuaTimKiem.isEmpty(), "Chỗ nghỉ đã bị xóa mặc dù nhấn Hủy");
    }
}
