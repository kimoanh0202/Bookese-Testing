package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import PageObjects.QLChoNghi.xoaChoNghiPage;
import PageObjects.Login.loginPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TC_xoaChoNghi {
    timKiemChoNghiPage timkiemChonghi;
    xoaChoNghiPage xoaChoNghiPage;

    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport_XoaChoNghi.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void beforeMethod() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();

        loginPage loginPage = new loginPage();
        loginPage.openLoginPage();
        loginPage.login(Constant.username, Constant.password);
        WaitTime.sleep(2000);

        timkiemChonghi = new timKiemChoNghiPage();
        xoaChoNghiPage = new xoaChoNghiPage();
    }

    @AfterMethod
    public void afterMethod() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
    }

    @Test
    public void TC01_xoaChoNghi_Successfully() {
        test = extent.createTest("TC01_xoaChoNghi_Successfully", "FUDL001 - Xác minh rằng chỗ nghỉ đã bị xóa không còn tồn tại trong danh sách chỗ nghỉ");

        String tenChoNghiCanXoa = "red sands pool villa"; //tạo biến, chỉ lưu những gt đã trả về kq

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenChoNghiCanXoa); //ktra true false
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }
        //tìm kiem là object chứa cua calss tkcnpage
        timkiemChonghi.hoverTenChoNghi();
        xoaChoNghiPage.clickXoa();
        xoaChoNghiPage.xacNhanXoa();

        String actualAlert = xoaChoNghiPage.getAlertText();
        Assert.assertEquals(actualAlert, "Xóa chỗ nghỉ thành công", "Thông báo không đúng");
        test.pass("Thông báo xóa thành công hiển thị đúng");

        List<WebElement> ketQuaTimKiem = xoaChoNghiPage.timKiemKetQua(tenChoNghiCanXoa);
        Assert.assertTrue(ketQuaTimKiem.isEmpty(), "Chỗ nghỉ vẫn còn sau khi xóa");
        test.pass("Chỗ nghỉ đã được xóa khỏi hệ thống");

        xoaChoNghiPage.xoaTimkiem();
    }

    @Test
    public void TC02_xoaChoNghi_Cancel() {
        test = extent.createTest("TC02_xoaChoNghi_Cancel", "FUDL004 - Xác minh rằng hệ thống không thực hiện thao tác xóa và đóng pop-up khi người dùng nhấn button [Hủy]");

        String tenChoNghi = "Mường Thanh";
        test.info("Tìm kiếm chỗ nghỉ: " + tenChoNghi);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenChoNghi);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        xoaChoNghiPage.clickXoa();
        xoaChoNghiPage.nhanNutHuyXoa();

        List<WebElement> ketQuaTimKiem = xoaChoNghiPage.timKiemKetQua(tenChoNghi);
        Assert.assertFalse(ketQuaTimKiem.isEmpty(), "Chỗ nghỉ đã bị xóa mặc dù nhấn Hủy");
        test.pass("Chỗ nghỉ vẫn còn, không bị xóa khi nhấn Hủy");
    }
}
