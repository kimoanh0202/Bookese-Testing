package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.Login.loginPage;
import PageObjects.QLChoNghi.chinhSuaChoNghiPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TC_chinhSuaChoNghi {
    timKiemChoNghiPage timkiemChonghi;
    chinhSuaChoNghiPage chinhsuaChoNghi;

    @BeforeMethod
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
        chinhsuaChoNghi = new chinhSuaChoNghiPage();
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC01_popupChinhSua_HienThiKhiClick() {
        System.out.println("FUUD001 - Xác minh rằng khi nhấn vào button [Chỉnh sửa] hệ thống hiển thị popup chỉnh sửa thông tin");
        String tenCNUpdate = "Khách sạn Ban Mai";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();

        Assert.assertTrue(chinhsuaChoNghi.isPopupVisible(), "Popup chỉnh sửa không hiển thị khi nhấn button [Chỉnh sửa]");
    }

    @Test
    public void TC02_chinhSuaChoNghi_Gui() {
        System.out.println("FUUD002 - Xác minh rằng hệ thống thực hiện cập nhật thông tin chỗ nghỉ khi người dùng nhấn button [Gửi]");
        String tenCNUpdate = "Mikazuki Hội An";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();

        chinhsuaChoNghi.updateTenCN("Khách sạn");
        chinhsuaChoNghi.updateLoaiHinh("Khách sạn");
        chinhsuaChoNghi.updateHangSao("3");
        chinhsuaChoNghi.updateTinhThanh("Đà Nẵng");
        chinhsuaChoNghi.updateQuanHuyen("Quận Thanh Khê");
        chinhsuaChoNghi.updatePhuongXa("Phường Xuân Hà");
        chinhsuaChoNghi.updateDiaChi("03 Đỗ Ngọc Du (Đối diện bệnh viện Thanh Khê)");
        chinhsuaChoNghi.updateDinhVi("16.074363164471208, 108.21400600837651");
        chinhsuaChoNghi.updateMieuTa("Khách sạn view đồi núi trong trung tâm thành phố Đà Nẵng");

        chinhsuaChoNghi.clickGui();
        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Chỉnh sửa chỗ nghỉ thành công!");
    }


    @Test
    public void TC03_chinhSuaChoNghi_Huy() {
        System.out.println("FUUD003 - Xác minh rằng hệ thống không cập nhật thông tin chỉnh sửa và đóng pop-up khi người dùng nhấn button [Hủy]");
        String tenCNUpdate = "Hotel Ngày Mai Về Nhà nè";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();

        chinhsuaChoNghi.updateTenCN("Khách sạn Ban Mai");

        chinhsuaChoNghi.clickHuy();
        WaitTime.sleep(1000);
        Assert.assertFalse(chinhsuaChoNghi.isPopupVisible(), "Popup vẫn còn hiển thị sau khi nhấn Hủy.");

        String actualTenCN = chinhsuaChoNghi.layTenChoNghi();
        Assert.assertEquals(actualTenCN, tenCNUpdate, "Tên chỗ nghỉ đã bị thay đổi mặc dù đã nhấn Hủy.");
    }


    @Test
    public void TC04_tenChoNghi_DaTonTai() {
        System.out.println("FUUD013 - Xác minh hệ thống hiển thị thông báo lỗi khi [Tên chỗ nghỉ] đã tồn tại");
        String tenCNUpdate = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();

        chinhsuaChoNghi.updateTenCN("Brick House Dalat Hotel");

        chinhsuaChoNghi.clickGui();
        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getAlertTextSuccessful(), "Tên chỗ nghỉ đã tồn tại");
    }

    @Test
    public void TC05_tenChoNghi_BatBuoc() {
        System.out.println("FUUD010 - Xác minh rằng trường textbox [Tên chỗ nghỉ] là bắt buộc");
        String tenCNUpdate = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();
        chinhsuaChoNghi.updateTenCN("");
        chinhsuaChoNghi.clickGui();

        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Tên chỗ nghỉ không được để trống", "Nội dung thông báo lỗi không đúng!");
    }

    @Test
    public void TC06_diaChi_KiTuToiThieu() {
        System.out.println("FUUD033 - Xác minh rằng textbox [Địa chỉ] là bắt buộc");
        String tenCNUpdate = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();
        chinhsuaChoNghi.updateDiaChi("");
        chinhsuaChoNghi.clickGui();

        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Địa chỉ không được để trống", "Nội dung thông báo lỗi không đúng!");
    }

    @Test
    public void TC07_diaChi_KiTuToiThieu() {
        System.out.println("FUUD035 - Xác minh rằng textbox [Địa chỉ] không chấp nhận số lượng từ tối thiểu <2");
        String tenCNUpdate = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();
        chinhsuaChoNghi.updateDiaChi("Đường");
        chinhsuaChoNghi.clickGui();

        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Địa chỉ gồm 2 từ trở lên", "Nội dung thông báo lỗi không đúng!");
    }

    @Test
    public void TC08_dinhVi_saiDinhDang() {
        System.out.println("FUUD046 - Xác minh rằng trường [Định vị] không chỉ chấp nhận giá trị định dạng khác lat, long");
        String tenCNUpdate = "Khách sạn";

        boolean isExist = timkiemChonghi.timKiemChoNghi(tenCNUpdate);
        Assert.assertTrue(isExist, "Chỗ nghỉ không tồn tại trong hệ thống.");

        timkiemChonghi.hoverTenChoNghi();
        chinhsuaChoNghi.clickXemchitiet();
        chinhsuaChoNghi.clickChinhSua();
        chinhsuaChoNghi.updateDinhVi("xxx, yyy");
        chinhsuaChoNghi.clickGui();

        WaitTime.sleep(1000);
        Assert.assertEquals(chinhsuaChoNghi.getErrorMessage(), "Vui lòng nhập định dạng lat, long", "Nội dung thông báo lỗi không đúng!");
    }
}
