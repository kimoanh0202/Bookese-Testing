package TestCase.ChoNghi;

import Common.Constant;
import Common.WaitTime;
import PageObjects.QLChoNghi.themChoNghiPage;
import PageObjects.Login.loginPage;
import PageObjects.TimKiemChoNghi.timKiemChoNghiPage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class TC_themChoNghi {
    themChoNghiPage themChoNghi;
    timKiemChoNghiPage timkiemChonghi;

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

        themChoNghi = new themChoNghiPage();
        timkiemChonghi = new timKiemChoNghiPage();
    }

    @AfterTest
    public void cleanup() {
        System.out.println("Post-Condition");
        if (Constant.WEBDRIVER != null) {
            Constant.WEBDRIVER.quit();
        }
    }

    @Test
    public void TC01_themChoNghi_ThanhCong() {
        System.out.println("FUCR002 - Xác minh rằng chỗ nghỉ hiển thị trong [Danh sách chỗ nghỉ] sau khi thêm mới thành công");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();
        String tenChoNghi = "Ngày Bình Yên";

        themChoNghi.sleLoaiHinh("Căn hộ");
        themChoNghi.txtTenCN(tenChoNghi);
        themChoNghi.sleHangSao("5");
        themChoNghi.txtTennguoiLH("Kim Oanh");
        themChoNghi.sleNgonNgu("Tiếng Anh");
        themChoNghi.sleChucVu("Chủ Chỗ Nghỉ");
        themChoNghi.txtEmail("Oanh@gmail.com");
        themChoNghi.txtSDT("0364563845");
        themChoNghi.txtSDTkhac("0364675485");
        themChoNghi.sleTinhTP("Gia Lai");
        themChoNghi.sleQuanHuyen("Huyện Đức Cơ");
        themChoNghi.slePhuongXa("Thị trấn Chư Ty");
        themChoNghi.txtDiaChi("30 Đường");
        themChoNghi.txtDinhVi("15.883336089339467, 108.35643296607071");
        themChoNghi.txtMieutachonghi("đẸP HE");
        WaitTime.sleep(1000);

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

        themChoNghi.btnClosePopup();
        timkiemChonghi.timKiemChoNghi(tenChoNghi);
        WaitTime.sleep(2000);
        List<WebElement> ketQuaChinhXac = themChoNghi.isTenChoNghiDisplayed(tenChoNghi);
        Assert.assertTrue(ketQuaChinhXac.isEmpty(), "Không tìm thấy chỗ nghỉ vừa tạo");
    }

    @Test
    public void TC02_tenChoNghi_BatBuoc() {
        System.out.println("FUCR011 - Xác minh rằng trường textbox [Tên chỗ nghỉ] là bắt buộc");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtTenCN("");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Tên chỗ nghỉ không được để trống", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC03_Email_Batbuoc() {
        System.out.println("FUCR038 - Xác minh rằng textbox [Địa chỉ Email] là bắt buộc");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtEmail("");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Email không được để trống", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC04_Email_SaiDinhDang() {
        System.out.println("FUCR039 - Xác minh textbox [Email] không chấp nhận email thiếu ký tự @");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtEmail("usergmail.com");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC05_Email_SaiDinhDang() {
        System.out.println("FUCR040 - Xác minh textbox [Email] không chấp nhận email thiếu dấu chấm");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtEmail("user@gmailcom");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC06_Email_SaiDinhDang() {
        System.out.println("FUCR041 - Xác minh textbox [Email] không chấp nhận chứa khoảng trắng");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtEmail("user @gmail.com");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC07_Email_SaiDinhDang() {
        System.out.println("FUCR042 - Xác minh textbox [Email] không chấp nhận ký tự đặc biệt không hợp lệ");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtEmail("user@#mail.com");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Email không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC08_SDT_BatBuoc() {
        System.out.println("FUCR044 - Xác minh rằng textbox [Số điện thoại liên lạc] là bắt buộc");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không được để trống", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC09_SDT_SaiDinhDang() {
        System.out.println("FUCR045 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa ký tự chữ cái");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("09abc45678");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC10_SDT_SaiDinhDang() {
        System.out.println("FUCR046 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa ký tự đặc biệt");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("09@#45678");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC11_SDT_SaiDinhDang() {
        System.out.println("FUCR047 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT chứa khoảng trắng");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("0912 345 678");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC12_SDT_SaiDinhDang() {
        System.out.println("FUCR048 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT <10 số");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("04581356");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC13_SDT_SaiDinhDang() {
        System.out.println("FUCR049 - Xác minh textbox [Số điện thoại liên lạc] không chấp nhận SĐT >10 số");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtSDT("091234567845");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Số điện thoại không hợp lệ", "Thông báo lỗi không chính xác");
    }

    @Test
    public void TC14_DinhVi_SaiDinhDang() {
        System.out.println("FUCR073 - Xác minh rằng trường [Định vị] không chấp nhận giá trị khác định dạng lat, long");
        themChoNghi.btnThemChoNghi();
        themChoNghi.btnXacNhan();

        themChoNghi.txtDinhVi("abx, fns");

        String errorMessage = themChoNghi.checkValidte();
        Assert.assertEquals(errorMessage, "Vui lòng nhập giá trị dạng lat, long", "Thông báo lỗi không chính xác");
    }
}




//    @Test
//    public void TC15_HinhAnh_SaiDinhDang() {
//        System.out.println("FUCR149 - Xác minh rằng hệ thống không chấp nhận ảnh có định dạng khác PNG, JPG, JPEG");
//        themChoNghi.btnThemChoNghi();
//        themChoNghi.btnXacNhan();
//
//        themChoNghi.sleLoaiHinh("Căn hộ");
//        themChoNghi.txtTenCN("Ánh Bình Minh");
//        themChoNghi.sleHangSao("5");
//        themChoNghi.txtTennguoiLH("Kim Oanh");
//        themChoNghi.sleNgonNgu("Tiếng Anh");
//        themChoNghi.sleChucVu("Chủ Chỗ Nghỉ");
//        themChoNghi.txtEmail("Oanh@gmail.com");
//        themChoNghi.txtSDT("0364563845");
//        themChoNghi.txtSDTkhac("0364675485");
//        themChoNghi.sleTinhTP("Gia Lai");
//        themChoNghi.sleQuanHuyen("Huyện Đức Cơ");
//        themChoNghi.slePhuongXa("Thị trấn Chư Ty");
//        themChoNghi.txtDiaChi("30 Đường");
//        themChoNghi.txtDinhVi("15.883336089339467, 108.35643296607071");
//        themChoNghi.txtMieutachonghi("đẸP HE");
//        WaitTime.sleep(1000);
//
//        themChoNghi.clickBTT_Information();
//
//        themChoNghi.sleTenPhong("Phòng Standard 1 Giường Đơn");
//        themChoNghi.txtTenTuyChon("Phòng Đơn");
//        themChoNghi.txtDientich("50");
//        themChoNghi.txtSophong("40");
//        themChoNghi.txtSophongngu("15");
//        themChoNghi.txtGiaPhong("400000");
//        WaitTime.sleep(1000);
//
//        themChoNghi.clickBTT_PhongVaGia();
//
//        themChoNghi.sleNgonNguDuocSD("Tiếng Anh", "Tiếng Việt");
//
//        themChoNghi.clickBTT_Service();
//
//        themChoNghi.addImgSVG();
//        WaitTime.sleep(3000);
//
//        String errorMessage = themChoNghi.checkImg();
//        Assert.assertEquals(errorMessage, "Ảnh định dạng JPG, JPEG, PNG", "Thông báo lỗi không chính xác");
//    }