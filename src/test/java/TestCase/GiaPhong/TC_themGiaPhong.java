package TestCase.GiaPhong;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.loginPage;
import PageObjects.QLGiaPhong.themGiaPhongPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Arrays;

public class TC_themGiaPhong {

    timKiemChoNghiPage timkiemChonghi;
    themGiaPhongPage themGiaPhong;


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
        themGiaPhong = new themGiaPhongPage();
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }


    @Test
    public void TC01_themGiaPhong_Successfully() {
        System.out.println("FURP002 - Xác minh rằng hệ thống thực hiện thêm loại giá mới khi người dùng nhấn button [Thêm]");
        String tenCNAddNewPrice = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

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

        themGiaPhong.clickClosePopup();
        Assert.assertEquals(themGiaPhong.layTenLoaiGiaCuoiCung(), "Super Price 2");
        if (themGiaPhong.layTenLoaiGiaCuoiCung().equals("Super Price 2")) {
            System.out.println("Loại giá được thêm thành công");
        }
    }

    @Test
    public void TC02_themGiaPhong_BuaAn() {
        System.out.println("FURP015 - Xác minh rằng khi chọn option “Có, thêm lựa chọn bữa ăn” nhưng không chọn bữa ăn nào, hệ thống hiển thị thông báo");
        String tenCNAddNewPrice = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        themGiaPhong.chonBuaAnBaoGom("Có, thêm lựa chọn bữa ăn", Arrays.asList());

        String actualText = themGiaPhong.layNoiDungError();
        String expectedText = "Bữa ăn không được để trống";
        Assert.assertEquals(actualText, expectedText, "Nội dung lỗi không khớp");
    }

    @Test
    public void TC03_themGiaPhong_loaiPhongApDung() {
        System.out.println("FURP020 - Xác minh rằng khi nhấn button “Chọn tất cả”, tất cả checkbox đều được tick chọn");
        String tenCNAddNewPrice = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();
        themGiaPhong.chonTatCaCheckboxVaXacNhan();
    }

    @Test
    public void TC04_themGiaPhong_loaiPhongApDung() {
        System.out.println("FURP022 - Xác minh rằng số lượng phòng hiển thị khớp với số lượng phòng đã tạo ở dropdown [Loại Phòng]");
        String tenCNAddNewPrice = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
            Assert.fail("Chỗ nghỉ không tồn tại trong hệ thống.");
            return;
        }

        timkiemChonghi.hoverTenChoNghi();
        themGiaPhong.clickXemchitiet();
        themGiaPhong.clickGiavaTTP();
        themGiaPhong.clickLoaiGia();
        themGiaPhong.clickButtonThem();

        themGiaPhong.countCheckboxes();
        System.out.println("Số lượng loại phòng (Loại giá): " + themGiaPhong.checkboxCount);

        themGiaPhong.clickChoNghi();
        themGiaPhong.clickLoaiPhong();

        themGiaPhong.countRoomCards();
        System.out.println("Số lượng loại phòng (Loại phòng): " + themGiaPhong.roomCardCount);

        themGiaPhong.soSanhSoLuong();
    }

    @Test
    public void TC05_themGiaPhong_TenGia() {
        System.out.println("FURP038 - Xác minh rằng trường textbox [Tên loại giá] là bắt buộc");
        String tenCNAddNewPrice = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNAddNewPrice);
        if (!isExist) {
            System.out.println("Chỗ nghỉ không tồn tại trong hệ thống");
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
        String expectedText = "Tên loại giá không được để trống";
        Assert.assertEquals(actualText, expectedText, "Nội dung lỗi không khớp");


    }



}
