package TestCase.GiaPhong;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.loginPage;
import PageObjects.QLGiaPhong.themGiaPhongPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Arrays;

public class TC_themGiaPhong {

    timKiemChoNghiPage timkiemChonghi;
    themGiaPhongPage themGiaPhong;

    static ExtentReports extent;
    ExtentTest test;

    @BeforeClass
    public void beforeClass() {
        System.out.println("Khởi tạo ExtentReport 1 lần trước toàn bộ testcase");
        System.setProperty("webdriver.chrome.driver", "browserDrivers/chromedriver.exe");

        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport_ThemGiaPhong.html");
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

        timkiemChonghi = new timKiemChoNghiPage();
        themGiaPhong = new themGiaPhongPage();
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
        System.out.println("Flush báo cáo 1 lần sau cùng");
        extent.flush();
    }

    @Test
    public void TC01_themGiaPhong_Successfully() {
        test = extent.createTest("TC01_themGiaPhong_Successfully", "Thêm loại giá mới thành công");
        String tenCNAddNewPrice = "Legacy Hoi An Resort";
        test.info("Tìm kiếm chỗ nghỉ: " + tenCNAddNewPrice);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        test.info("Thực hiện thêm loại giá");
        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();
        themGiaPhong.chonChinhSachHuy("Linh động", 2);
        themGiaPhong.chonBuaAnBaoGom("Có, thêm lựa chọn bữa ăn", Arrays.asList("Bữa sáng", "Bữa tối"));
        themGiaPhong.chonLoaiPhongApDung(Arrays.asList("Chọn tất cả"));
        themGiaPhong.chonCachQLGia("Dựa trên Giá cơ bản", "VND", "10");
        themGiaPhong.nhapTenLoaiGia("Super Price 2");
        themGiaPhong.clickThem();

        String actualText = themGiaPhong.layNoiDungPopup();
        String expectedText = "Thêm loại giá mới thành công";
        Assert.assertEquals(actualText, expectedText, "Nội dung popup không khớp");
        test.pass("Thêm loại giá thành công");

        themGiaPhong.clickClosePopup();
        Assert.assertEquals(themGiaPhong.layTenLoaiGiaCuoiCung(), "Super Price 2");
        test.pass("Tên loại giá hiển thị đúng");
    }

    @Test
    public void TC02_themGiaPhong_BuaAn() {
        test = extent.createTest("TC02_themGiaPhong_BuaAn", "Không chọn bữa ăn nào thì báo lỗi");
        String tenCNAddNewPrice = "Legacy Hoi An Resort";
        test.info("Tìm kiếm chỗ nghỉ: " + tenCNAddNewPrice);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        test.info("Không chọn bữa ăn nào");
        themGiaPhong.chonBuaAnBaoGom("Có, thêm lựa chọn bữa ăn", Arrays.asList());

        String actualText = themGiaPhong.layNoiDungError();
        Assert.assertEquals(actualText, "Bữa ăn không được để trống", "Nội dung lỗi không khớp");
        test.pass("Lỗi được hiển thị đúng khi không chọn bữa ăn");
    }

    @Test
    public void TC03_themGiaPhong_loaiPhongApDung() {
        test = extent.createTest("TC03_themGiaPhong_loaiPhongApDung", "Chọn tất cả loại phòng");
        String tenCNAddNewPrice = "Legacy Hoi An Resort";
        test.info("Tìm kiếm chỗ nghỉ: " + tenCNAddNewPrice);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        test.info("Nhấn chọn tất cả loại phòng");
        themGiaPhong.chonTatCaCheckboxVaXacNhan();
        test.pass("Tất cả loại phòng đã được tick chọn");
    }

    @Test
    public void TC04_themGiaPhong_loaiPhongApDung() {
        test = extent.createTest("TC04_themGiaPhong_loaiPhongApDung", "So sánh số lượng loại phòng hiển thị");
        String tenCNAddNewPrice = "Legacy Hoi An Resort";
        test.info("Tìm kiếm chỗ nghỉ: " + tenCNAddNewPrice);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        themGiaPhong.countCheckboxes();
        themGiaPhong.clickChoNghi();
        themGiaPhong.clickLoaiPhong();
        themGiaPhong.countRoomCards();
        themGiaPhong.soSanhSoLuong();
        test.pass("Số lượng loại phòng khớp với dropdown");
    }

    @Test
    public void TC05_themGiaPhong_TenGia() {
        test = extent.createTest("TC05_themGiaPhong_TenGia", "Tên loại giá là bắt buộc");
        String tenCNAddNewPrice = "Legacy Hoi An Resort";
        test.info("Tìm kiếm chỗ nghỉ: " + tenCNAddNewPrice);

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            test.fail("Chỗ nghỉ không tồn tại");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        themGiaPhong.nhapTenLoaiGia("");
        String actualText = themGiaPhong.layNoiDungError_NamePrice();
        Assert.assertEquals(actualText, "Tên loại giá không được để trống", "Nội dung lỗi không khớp");
        test.pass("Hệ thống báo lỗi đúng khi tên loại giá trống");
    }
}
