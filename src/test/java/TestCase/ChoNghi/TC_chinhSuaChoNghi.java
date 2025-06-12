package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.loginPage;
import PageObjects.QLChoNghi.chinhSuaChoNghiPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class TC_chinhSuaChoNghi {
    timKiemChoNghiPage timkiemChonghi;
    chinhSuaChoNghiPage chinhsuaChoNghi;

    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Khởi tạo ExtentReport");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport_ChinhSuaChoNghi.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void beforeTest() {
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();

        loginPage loginPage = new loginPage();
        loginPage.openLoginPage();
        loginPage.login(Constant.username, Constant.password);
        WaitTime.sleep(2000);

        timkiemChonghi = new timKiemChoNghiPage();
        chinhsuaChoNghi = new chinhSuaChoNghiPage();
    }

    @AfterMethod
    public void cleanup() {
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        extent.flush();
    }

    @Test (priority = 1)
    public void TC01_chinhSuaChoNghi_Gui() {
        test = extent.createTest("TC01_chinhSuaChoNghi_Gui", "FUUD001 - Xác minh rằng hệ thống cho phép người dùng chỉnh sửa chỗ nghỉ khi nhập đầy đủ tất cả các trường thông tin hợp lệ");
        try {
            String tenCNUpdate = "Melia Đà Nẵng";
            Assert.assertTrue(timkiemChonghi.timKiemChoNghi(tenCNUpdate), "Không tìm thấy chỗ nghỉ");

            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateTenCN("Melina Hội An");
            chinhsuaChoNghi.updateLoaiHinh("Khách sạn");
            chinhsuaChoNghi.updateHangSao("5");
            chinhsuaChoNghi.updateTinhThanh("Quảng Nam");
            chinhsuaChoNghi.updateQuanHuyen("Thành phố Hội An");
            chinhsuaChoNghi.updatePhuongXa("Phường Cửa Đại");
            chinhsuaChoNghi.updateKhuvuc("Bãi Biển Cửa Đại");
            chinhsuaChoNghi.updateDiaChi("250 Bạch Đằng");
            chinhsuaChoNghi.updateDinhVi("16.074363164471208, 108.21400600837651");
            chinhsuaChoNghi.updateMieuTa("Khách sạn view cầu sông Hàn, trong trung tâm thành phố Đà Nẵng");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Chỉnh sửa chỗ nghỉ thành công!");
            test.pass("Chỉnh sửa thành công");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 2)
    public void TC02_chinhSuaChoNghi_Gui() {
        test = extent.createTest("TC02_chinhSuaChoNghi_Gui", "FUUD002 - Xác minh rằng hệ thống cho phép người dùng chỉnh sửa chỗ nghỉ khi chỉ nhập các trường bắt buộc và để trống các trường tùy chọn");
        try {
            String tenCNUpdate = "Logitech Hotel";
            Assert.assertTrue(timkiemChonghi.timKiemChoNghi(tenCNUpdate), "Không tìm thấy chỗ nghỉ");

            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateTenCN("Luxury Park Views");
            chinhsuaChoNghi.updateLoaiHinh("Căn hộ");
            chinhsuaChoNghi.updateHangSao("5");
            chinhsuaChoNghi.updateTinhThanh("Bình Dương");
            chinhsuaChoNghi.updateQuanHuyen("Huyện Bắc Tân Uyên");
            chinhsuaChoNghi.updatePhuongXa("Thị trấn Tân Bình");
            chinhsuaChoNghi.updateDiaChi("250 Bạch Đằng");
            chinhsuaChoNghi.updateDinhVi("15.883336089339467, 108.35643296607074");
            chinhsuaChoNghi.updateMieuTa("Căn hộ view bậc nhất Bình Dương");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Chỉnh sửa chỗ nghỉ thành công!");
            test.pass("Chỉnh sửa thành công");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 3)
    public void TC03_popupChinhSua_HienThiKhiClick() {
        test = extent.createTest("TC03_popupChinhSua_HienThiKhiClick", "FUUD005 - Xác minh rằng khi nhấn vào button [Chỉnh sửa] hệ thống hiển thị popup chỉnh sửa thông tin");
        try {
            String tenCNUpdate = "Hozito Hotel";
            Assert.assertTrue(timkiemChonghi.timKiemChoNghi(tenCNUpdate), "Không tìm thấy chỗ nghỉ");

            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            Assert.assertTrue(chinhsuaChoNghi.isPopupVisible(), "Popup không hiển thị");
            test.pass("Popup hiển thị thành công");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 4)
    public void TC04_chinhSuaChoNghi_Huy() {
        test = extent.createTest("TC04_chinhSuaChoNghi_Huy", "FUUD007 - Xác minh rằng hệ thống không thực hiện thao tác chỉnh sửa và đóng pop-up khi người dùng nhấn button [Hủy]");
        try {
            String tenCNUpdate = "SunHouse Hotel";
            Assert.assertTrue(timkiemChonghi.timKiemChoNghi(tenCNUpdate), "Không tìm thấy chỗ nghỉ");

            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();
            chinhsuaChoNghi.updateTenCN("Luxury Park");

            chinhsuaChoNghi.clickHuy();
            WaitTime.sleep(1000);

            Assert.assertFalse(chinhsuaChoNghi.isPopupVisible());
            test.pass("Popup đóng thành công");

            String actualTenCN = chinhsuaChoNghi.layTenChoNghi();
            Assert.assertEquals(actualTenCN, tenCNUpdate);
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 5)
    public void TC05_chinhSuaChoNghi_toastThongBao() {
        test = extent.createTest("TC05_chinhSuaChoNghi_toastThongBao", "FUUD009 - Xác minh hệ thống hiển thị toast thông báo khi chỉnh sửa thông tin chỗ nghỉ thành công");
        try {
            String tenCNUpdate = "Mandaza Hotel";
            Assert.assertTrue(timkiemChonghi.timKiemChoNghi(tenCNUpdate), "Không tìm thấy chỗ nghỉ");

            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();
            chinhsuaChoNghi.updateTenCN("Vĩnh Trung Plaza");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Chỉnh sửa chỗ nghỉ thành công!");
            test.pass("Chỉnh sửa thành công");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 6)
    public void TC06_tenChoNghi_BatBuoc() {
        test = extent.createTest("TC06_tenChoNghi_BatBuoc", "FUUD014 - Xác minh rằng trường [Tên chỗ nghỉ] là bắt buộc");
        try {
            timkiemChonghi.timKiemChoNghi("Royal Family Hotel");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateTenCN("");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Tên chỗ nghỉ không được để trống");
            test.pass("Thông báo bắt buộc hiển thị đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }


    @Test (priority = 7)
    public void TC07_tenChoNghi_DaTonTai() {
        test = extent.createTest("TC07_tenChoNghi_DaTonTai", "FUUD016 - Xác minh hệ thống hiển thị thông báo lỗi khi [Tên chỗ nghỉ] đã tồn tại");
        try {
            timkiemChonghi.timKiemChoNghi("Rioca Vienna Posto 2");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateTenCN("Brick House Dalat Hotel");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Tên chỗ nghỉ đã tồn tại");
            test.pass("Thông báo trùng tên hiển thị đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 8)
    public void TC08_diaChi_BatBuoc() {
        test = extent.createTest("TC08_diaChi_BatBuoc", "FUUD028 - Xác minh rằng textbox [Địa chỉ] là bắt buộc");
        try {
            timkiemChonghi.timKiemChoNghi("Royal Family Hotel");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateDiaChi("");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Địa chỉ không được để trống");
            test.pass("Thông báo địa chỉ bắt buộc đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 9)
    public void TC09_diaChi_KiTuToiThieu() {
        test = extent.createTest("TC09_diaChi_KiTuToiThieu", "FUUD029 - Xác minh rằng textbox [Địa chỉ] chỉ chấp nhận số lượng từ >=2");
        try {
            timkiemChonghi.timKiemChoNghi("Royal Family Hotel");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateDiaChi("Đường");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Địa chỉ gồm 2 từ trở lên");
            test.pass("Thông báo địa chỉ phải từ 2 từ trở lên hiển thị đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 10)
    public void TC010_dinhVi_saiDinhDang() {
        test = extent.createTest("TC10_dinhVi_saiDinhDang", "FUUD040 - Xác minh rằng trường [Định vị] không chấp nhận giá trị định dạng khác lat, long");
        try {
            timkiemChonghi.timKiemChoNghi("Royal Family Hotel");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateDinhVi("abx, fns");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Vui lòng nhập định dạng lat, long");
            test.pass("Thông báo định vị sai định dạng hiển thị đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test (priority = 11)
    public void TC011_mieuTaChoNghi_BatBuoc() {
        test = extent.createTest("TC011_mieuTaChoNghi_BatBuoc", "FUUD041 - Xác minh rằng textbox [Miêu tả chỗ nghỉ] là bắt buộc");
        try {
            timkiemChonghi.timKiemChoNghi("Royal Family Hotel");
            timkiemChonghi.hoverTenChoNghi();
            chinhsuaChoNghi.clickXemchitiet();
            chinhsuaChoNghi.clickChinhSua();

            chinhsuaChoNghi.updateMieuTa("");

            chinhsuaChoNghi.clickGui();
            WaitTime.sleep(1000);

            Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Giới thiệu chổ nghỉ không được để trống");
            test.pass("Thông báo địa chỉ bắt buộc đúng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }
}
