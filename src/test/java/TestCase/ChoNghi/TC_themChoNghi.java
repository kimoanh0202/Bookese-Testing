package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.QLChoNghi.themChoNghiPage;
import PageObjects.Login.loginPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TC_themChoNghi {
    themChoNghiPage themChoNghi;
    timKiemChoNghiPage timkiemChonghi;

    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Khởi tạo ExtentReport 1 lần trước toàn bộ testcase");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport_ThemChoNghi.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Mở Chrome trước mỗi testcase");
        Constant.WEBDRIVER = new ChromeDriver();
        Constant.WEBDRIVER.manage().window().maximize();

        loginPage loginPage = new loginPage();
        loginPage.openLoginPage();
        loginPage.login(Constant.username, Constant.password);
        WaitTime.sleep(2000);

        themChoNghi = new themChoNghiPage();
        timkiemChonghi = new timKiemChoNghiPage();
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("Đóng Chrome sau mỗi testcase");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @AfterClass
    public void afterClass() {
        System.out.println("Flush báo cáo sau tất cả testcase");
        extent.flush();
    }


    @Test
    public void TC01_themChoNghi_ThanhCong() {
        test = extent.createTest("TC01_themChoNghi_ThanhCong", "FUCR002 - Xác minh rằng chỗ nghỉ hiển thị trong [Danh sách chỗ nghỉ] sau khi thêm mới thành công");

        try {
            test.info("Click nút thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            String tenChoNghi = "Aurora Central";
            test.info("Nhập thông tin chỗ nghỉ: " + tenChoNghi);

            themChoNghi.sleLoaiHinh("Khách sạn");
            themChoNghi.txtTenCN(tenChoNghi);
            themChoNghi.sleHangSao("4");
            themChoNghi.txtTennguoiLH("Trịnh Thị Kim Oanh");
            themChoNghi.sleNgonNgu("Tiếng Anh");
            themChoNghi.sleChucVu("Chủ Chỗ Nghỉ");
            themChoNghi.txtEmail("Oanh@gmail.com");
            themChoNghi.txtSDT("0364563845");
            themChoNghi.txtSDTkhac("0364675485");
            themChoNghi.sleTinhTP("Đà Nẵng");
            themChoNghi.sleQuanHuyen("Quận Sơn Trà");
            themChoNghi.slePhuongXa("Phường An Hải Đông");
            themChoNghi.txtDiaChi("39 Võ Văn Kiệt");
            themChoNghi.txtDinhVi("15.883336089339467, 108.35643296607071");
            themChoNghi.txtMieutachonghi("Royal Family Hotel ...");
            WaitTime.sleep(1000);

            test.info("Nhập thông tin phòng và giá");
            themChoNghi.clickBTT_Information();
            themChoNghi.sleTenPhong("Phòng Standard 1 Giường Đơn");
            themChoNghi.txtTenTuyChon("Phòng Đơn");
            themChoNghi.txtDientich("50");
            themChoNghi.txtSophong("40");
            themChoNghi.txtSophongngu("15");
            themChoNghi.txtGiaPhong("400000");
            WaitTime.sleep(1000);

            themChoNghi.clickBTT_PhongVaGia();
            themChoNghi.sleNgonNguDuocSD("Tiếng Anh", "Tiếng Việt");

            test.info("Chọn dịch vụ và chính sách");
            themChoNghi.clickBTT_Service();
            themChoNghi.addIMG();
            WaitTime.sleep(3000);
            themChoNghi.clickBTT_Img();

            themChoNghi.clickBTT_Policy();
            themChoNghi.selectPolicy1();
            themChoNghi.selectPolicy2();

            themChoNghi.btnTaoChoNghi();
            WaitTime.sleep(2000);

            String actualText = themChoNghi.getTextPopUp();
            String expectedText = "Bạn đã tạo chỗ nghỉ thành công";
            Assert.assertEquals(actualText, expectedText, "Nội dung popup không khớp");
            test.pass("Thông báo popup tạo chỗ nghỉ hiển thị đúng");

            themChoNghi.btnClosePopup();

            test.info("Kiểm tra kết quả sau khi tạo chỗ nghỉ");
            timkiemChonghi.timKiemChoNghi(tenChoNghi);
            WaitTime.sleep(2000);
            List<WebElement> ketQuaChinhXac = themChoNghi.isTenChoNghiDisplayed(tenChoNghi);
            Assert.assertTrue(ketQuaChinhXac.isEmpty(), "Không tìm thấy chỗ nghỉ vừa tạo");
            test.pass("Chỗ nghỉ hiển thị trong danh sách sau khi tạo thành công");

        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }


    @Test
    public void TC02_tenChoNghi_DaTonTai() {
        test = extent.createTest("TC02_tenChoNghi_DaTonTai", "FUCR003 - Xác minh hệ thống hiển thị thông báo lỗi khi thêm chỗ nghỉ mới có tên trùng tên với chỗ nghỉ đã tồn tại");

        try {
            test.info("Click nút thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            String tenChoNghi = "Nostalgia Boutique Hotel";
            test.info("Nhập thông tin chỗ nghỉ có tên đã tồn tại: " + tenChoNghi);

            themChoNghi.sleLoaiHinh("Khách sạn");
            themChoNghi.txtTenCN(tenChoNghi);
            themChoNghi.sleHangSao("4");
            themChoNghi.txtTennguoiLH("Trịnh Thị Kim Oanh");
            themChoNghi.sleNgonNgu("Tiếng Anh");
            themChoNghi.sleChucVu("Chủ Chỗ Nghỉ");
            themChoNghi.txtEmail("Oanh@gmail.com");
            themChoNghi.txtSDT("0364563845");
            themChoNghi.txtSDTkhac("0364675485");
            themChoNghi.sleTinhTP("Đà Nẵng");
            themChoNghi.sleQuanHuyen("Quận Sơn Trà");
            themChoNghi.slePhuongXa("Phường An Hải Đông");
            themChoNghi.txtDiaChi("39 Võ Văn Kiệt");
            themChoNghi.txtDinhVi("15.883336089339467, 108.35643296607071");
            themChoNghi.txtMieutachonghi("Royal Family Hotel nằm tại thành phố Đà Nẵng...");
            WaitTime.sleep(1000);

            test.info("Nhập thông tin phòng và giá");
            themChoNghi.clickBTT_Information();
            themChoNghi.sleTenPhong("Phòng Standard 1 Giường Đơn");
            themChoNghi.txtTenTuyChon("Phòng Đơn");
            themChoNghi.txtDientich("50");
            themChoNghi.txtSophong("40");
            themChoNghi.txtSophongngu("15");
            themChoNghi.txtGiaPhong("400000");
            WaitTime.sleep(1000);

            themChoNghi.clickBTT_PhongVaGia();
            themChoNghi.sleNgonNguDuocSD("Tiếng Anh", "Tiếng Việt");

            test.info("Chọn dịch vụ và chính sách");
            themChoNghi.clickBTT_Service();
            themChoNghi.addIMG();
            WaitTime.sleep(3000);
            themChoNghi.clickBTT_Img();

            themChoNghi.clickBTT_Policy();
            themChoNghi.selectPolicy1();
            themChoNghi.selectPolicy2();

            themChoNghi.btnTaoChoNghi();
            WaitTime.sleep(2000);

            String actualText = themChoNghi.getTextAlert();
            String expectedText = "Tên chỗ nghỉ đã tồn tại";
            Assert.assertEquals(actualText, expectedText, "Thông báo không đúng khi tên chỗ nghỉ bị trùng");
            test.pass("Hệ thống hiển thị đúng thông báo lỗi khi tên chỗ nghỉ đã tồn tại: \"" + actualText + "\"");

        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC03_themChoNghi_ThanhCong() {
        test = extent.createTest("TC03_themChoNghi_ThanhCong", "FUCR004 - Xác minh rằng hệ thống cho phép người dùng thêm chỗ nghỉ mới khi nhập đầy đủ tất cả các trường thông tin hợp lệ");

        try {
            test.info("Click nút thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            String tenChoNghi = "Velvet Crown";
            test.info("Nhập thông tin chỗ nghỉ: " + tenChoNghi);

            themChoNghi.sleLoaiHinh("Căn hộ");
            themChoNghi.txtTenCN(tenChoNghi);
            themChoNghi.sleHangSao("4");
            themChoNghi.txtTennguoiLH("Trịnh Thị Kim Oanh");
            themChoNghi.sleNgonNgu("Tiếng Anh");
            themChoNghi.sleChucVu("Chủ Chỗ Nghỉ");
            themChoNghi.txtEmail("Oanh@gmail.com");
            themChoNghi.txtSDT("0364563845");
            themChoNghi.txtSDTkhac("0364675485");
            themChoNghi.sleTinhTP("Đà Nẵng");
            themChoNghi.sleQuanHuyen("Quận Sơn Trà");
            themChoNghi.slePhuongXa("Phường An Hải Đông");
            themChoNghi.txtDiaChi("39 Võ Văn Kiệt");
            themChoNghi.txtDinhVi("15.883336089339467, 108.35643296607071");
            themChoNghi.txtMieutachonghi("Royal Family Hotel ...");
            WaitTime.sleep(1000);

            test.info("Nhập thông tin phòng và giá");
            themChoNghi.clickBTT_Information();
            themChoNghi.sleTenPhong("Phòng Standard 1 Giường Đơn");
            themChoNghi.txtTenTuyChon("Phòng Đơn");
            themChoNghi.txtDientich("50");
            themChoNghi.txtSophong("40");
            themChoNghi.txtSophongngu("15");
            themChoNghi.txtGiaPhong("400000");
            WaitTime.sleep(1000);

            themChoNghi.clickBTT_PhongVaGia();
            themChoNghi.sleNgonNguDuocSD("Tiếng Anh", "Tiếng Việt");

            test.info("Chọn dịch vụ và chính sách");
            themChoNghi.clickBTT_Service();
            themChoNghi.addIMG();
            WaitTime.sleep(3000);
            themChoNghi.clickBTT_Img();

            themChoNghi.clickBTT_Policy();
            themChoNghi.selectPolicy1();
            themChoNghi.selectPolicy2();

            themChoNghi.btnTaoChoNghi();
            WaitTime.sleep(2000);

            String actualText = themChoNghi.getTextPopUp();
            String expectedText = "Bạn đã tạo chỗ nghỉ thành công";
            Assert.assertEquals(actualText, expectedText, "Nội dung popup không khớp");
            test.pass("Thông báo popup tạo chỗ nghỉ hiển thị đúng");

            themChoNghi.btnClosePopup();

            test.info("Kiểm tra kết quả sau khi tạo chỗ nghỉ");
            timkiemChonghi.timKiemChoNghi(tenChoNghi);
            WaitTime.sleep(2000);
            List<WebElement> ketQuaChinhXac = themChoNghi.isTenChoNghiDisplayed(tenChoNghi);
            Assert.assertTrue(ketQuaChinhXac.isEmpty(), "Không tìm thấy chỗ nghỉ vừa tạo");
            test.pass("Chỗ nghỉ hiển thị trong danh sách sau khi tạo thành công");

        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC04_tenChoNghi_BatBuoc() {
        test = extent.createTest("TC04_tenChoNghi_BatBuoc", "FUCR012 - Xác minh rằng trường textbox [Tên chỗ nghỉ] là bắt buộc");

        try {
            test.info("Click nút Thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            test.info("Click nút Xác nhận");
            themChoNghi.btnXacNhan();

            test.info("Để trống trường Tên chỗ nghỉ");
            themChoNghi.txtTenCN("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Tên chỗ nghỉ không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi để trống tên chỗ nghỉ");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC05_tenNguoiLH_BatBuoc() {
        test = extent.createTest("TC05_tenNguoiLH_BatBuoc", "FUCR016 - Xác minh rằng textbox [Tên người liên hệ] là bắt buộc");

        try {
            test.info("Click nút Thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            test.info("Click nút Xác nhận");
            themChoNghi.btnXacNhan();

            test.info("Để trống trường Tên người liên hệ");
            themChoNghi.txtTennguoiLH("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Tên người liên hệ không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi để trống tên người liên hệ");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC06_tenNguoiLH_kiTuToiThieu() {
        test = extent.createTest("TC06_tenNguoiLH_kiTuToiThieu", "FUCR017 - Xác minh rằng textbox [Tên người liên hệ] chỉ chấp nhận số lượng từ >=2");

        try {
            test.info("Click nút Thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            test.info("Click nút Xác nhận");
            themChoNghi.btnXacNhan();

            test.info("Nhập tên người liên hệ <2 kí tự");
            themChoNghi.txtTennguoiLH("Oanh");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Tên người liên hệ gồm 2 từ trở lên", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi nhập tên người liên hệ <2 kí tự");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC07_tenNguoiLH_khongChuaSo() {
        test = extent.createTest("TC07_tenNguoiLH_khongChuaSo", "FUCR023 - Xác minh rằng textbox [Tên người liên hệ] không chứa số");

        try {
            test.info("Click nút Thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            test.info("Click nút Xác nhận");
            themChoNghi.btnXacNhan();

            test.info("Nhập tên người liên hệ chứa số");
            themChoNghi.txtTennguoiLH("Oanh 123");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Tên người liên hệ không chứa ký tự đặc biệt và số", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi nhập tên người liên hệ chứa số");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC08_tenNguoiLH_khongChuaKiTuDB() {
        test = extent.createTest("TC08_tenNguoiLH_khongChuaKiTuDB", "FUCR024 - Xác minh rằng textbox [Tên người liên hệ] không chứa ký tự đặc biệt");

        try {
            test.info("Click nút Thêm chỗ nghỉ");
            themChoNghi.btnThemChoNghi();
            test.info("Click nút Xác nhận");
            themChoNghi.btnXacNhan();

            test.info("Nhập tên người liên hệ chứa kí tự đặc biệt");
            themChoNghi.txtTennguoiLH("Kim Oanh@");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Tên người liên hệ không chứa ký tự đặc biệt và số", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi nhập tên người liên hệ chứa kí tự đặc biệt");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC09_Email_Batbuoc() {
        test = extent.createTest("TC09_Email_Batbuoc", "FUCR029 - Xác minh rằng trường [Email] là bắt buộc");

        try {
            test.info("Click Thêm chỗ nghỉ và Xác nhận");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            test.info("Để trống trường Email");
            themChoNghi.txtEmail("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Email không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi Email để trống");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC10_Email_SaiDinhDang1() {
        test = extent.createTest("TC10_Email_SaiDinhDang1", "FUCR030 - Xác minh textbox [Email] không chấp nhận email thiếu ký tự @");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            test.info("Nhập email không có ký tự @");
            themChoNghi.txtEmail("usergmail.com");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng với định dạng email sai (thiếu @)");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC11_Email_SaiDinhDang2() {
        test = extent.createTest("TC11_Email_SaiDinhDang2", "FUCR031 - Xác minh textbox [Email] không chấp nhận email thiếu dấu chấm");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtEmail("user@gmailcom");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi email sai định dạng thiếu dấu chấm");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC12_Email_SaiDinhDang3() {
        test = extent.createTest("TC12_Email_SaiDinhDang3", "FUCR032-Xác minh textbox [Email] không chấp nhận chứa khoảng trắng");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtEmail("user @gmail.com");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng với email chứa khoảng trắng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC13_Email_SaiDinhDang4() {
        test = extent.createTest("TC13_Email_SaiDinhDang4", "FUCR033-Xác minh textbox [Email] không chấp nhận ký tự đặc biệt không hợp lệ");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtEmail("user@#mail.com");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng với email chứa ký tự đặc biệt");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC14_SDT_BatBuoc() {
        test = extent.createTest("TC14_SDT_BatBuoc", "FUCR035 - Xác minh rằng textbox [Số điện thoại liên lạc] là bắt buộc");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng khi để trống SĐT");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC15_SDT_ChuCai() {
        test = extent.createTest("TC15_SDT_ChuCai", "FUCR036 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa ký tự chữ cái");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("09abc45678");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi SĐT chứa chữ cái");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC16_SDT_KyTuDacBiet() {
        test = extent.createTest("TC16_SDT_KyTuDacBiet", "FUCR037 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa ký tự đặc biệt");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("09@#45678");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng khi SĐT chứa ký tự đặc biệt");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC17_SDT_KhoangTrang() {
        test = extent.createTest("TC17_SDT_KhoangTrang", "FUCR038 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa khoảng trắng");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("0912 345 678");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng khi SĐT chứa khoảng trắng");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC18_SDT_NganHon() {
        test = extent.createTest("TC18_SDT_NganHon", "FUCR039 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT <10 số");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("04581356");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng khi SĐT < 10 số");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC19_SDT_DaiHon() {
        test = extent.createTest("TC19_SDT_DaiHon", "FUCR040 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT >10 số");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtSDT("091234567845");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng khi SĐT > 10 số");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC20_DiaChi_Batbuoc() {
        test = extent.createTest("TC20_DiaChi_Batbuoc", "FUCR051 - Xác minh rằng textbox [Địa chỉ] là bắt buộc");

        try {
            test.info("Click Thêm chỗ nghỉ và Xác nhận");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            test.info("Để trống trường Địa chỉ");
            themChoNghi.txtDiaChi("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Địa chỉ không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi địa chỉ để trống");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC21_DiaChi_kiTuToiThieu() {
        test = extent.createTest("TC21_DiaChi_kiTuToiThieu", "FUCR052 - Xác minh rằng textbox [Địa chỉ] chỉ chấp nhận số lượng từ >=2");

        try {
            test.info("Click Thêm chỗ nghỉ và Xác nhận");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            test.info("Nhập địa chỉ <2 kí tự ");
            themChoNghi.txtDiaChi("Đường");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Địa chỉ gồm 2 từ trở lên", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi địa chỉ < 2 kí tự");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC22_DinhVi_SaiDinhDang() {
        test = extent.createTest("TC22_DinhVi_SaiDinhDang", "FUCR063 - Xác minh rằng trường [Định vị] không chấp nhận giá trị khác định dạng lat, long");

        try {
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            themChoNghi.txtDinhVi("abx, fns");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Vui lòng nhập giá trị dạng lat, long", "Thông báo lỗi không chính xác");

            test.pass("Thông báo đúng với định dạng định vị sai");

        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

    @Test
    public void TC23_mieuTaCN_Batbuoc() {
        test = extent.createTest("TC23_mieuTaCN_Batbuoc", "FUCR064 - Xác minh rằng textbox [Miêu tả chỗ nghỉ] là bắt buộc");

        try {
            test.info("Click Thêm chỗ nghỉ và Xác nhận");
            themChoNghi.btnThemChoNghi();
            themChoNghi.btnXacNhan();

            test.info("Để trống trường Miêu tả chỗ nghỉ");
            themChoNghi.txtMieutachonghi("");

            String errorMessage = themChoNghi.checkValidte();
            Assert.assertEquals(errorMessage, "Giới thiệu chổ nghĩ không được để trống", "Thông báo lỗi không chính xác");

            test.pass("Thông báo lỗi đúng khi miêu tả chỗ nghỉ để trống");
        } catch (AssertionError ae) {
            test.fail("Assertion failed: " + ae.getMessage());
            throw ae;
        } catch (Exception e) {
            test.fail("Có lỗi xảy ra: " + e.getMessage());
            Assert.fail("Test thất bại: " + e.getMessage());
        }
    }

}
