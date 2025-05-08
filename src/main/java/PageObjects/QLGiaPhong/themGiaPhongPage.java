package PageObjects.QLGiaPhong;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

public class themGiaPhongPage {

    private By btnXemchitiet = By.xpath("//button[normalize-space()='Xem chi tiết']");
    private By btnGiavaTTP = By.xpath("//body/div[@id='root']/div[@id='layout-wrapper']/header[@id='page-topbar']/div[contains(@class,'navbar-header')]/div[@id='navbar-header-menu']/div[@id='horizontal-menu']/div[1]");
    private By sleLoaiGia = By.xpath("(//button[contains(@role,'menuitem')])[2]");
    private By btnThemMoiGia = By.xpath("//a[contains(text(),'Thêm loại giá mới')]");
    private By radioKhongHoanHuy  = By.xpath("//input[@id='No']");
    private By radioLinhDong  = By.xpath("//input[@id='Yes']");
    private By sleSoNgay = By.xpath("//div[@class='select2-selection__control css-b8vwd-control']");
    private By radioKhong  = By.xpath("//input[@id='NotIncluded']");
    private By radioCo  = By.xpath("//input[@id='included']");
    private By radioGiaMoi = By.xpath("//input[@id='NEW_PRICE']");
    private By radioDuaTheoGiaCB = By.xpath("//input[@id='BASE_ON_STANDARD']");
    private By txtTenLoaiGia = By.xpath("//input[@placeholder='Vui lòng nhập tên loại giá']");
    private By btnThem = By.xpath("(//button[normalize-space()='Thêm'])[1]");
    private By btnHuy = By.xpath("(//button[contains(text(),'Hủy')])[1]");
    private By getTextPopup = By.xpath("//div[@class='ModalSuccess_ModalBody__1lRJk modal-body']");
    private By getTextError = By.xpath("//div[contains(text(),'Bữa ăn không được để trống')]");
    private By getTextError_NamePrice = By.xpath("//div[contains(text(),'Tên loại giá không được để trống')]");
    private By closePopup = By.xpath("//button[@name='Đang gửi']");
    private By btnChoNghi = By.xpath("//body[1]/div[1]/div[1]/header[1]/div[1]/div[1]/div[2]/div[2]/button[1]");
    private By sleLoaiPhong = By.xpath("(//button[contains(@role,'menuitem')])[9]");
    private By btnChonTatCa_LPAD = By.xpath("//button[contains(text(),'Chọn tất cả')]");


    public themGiaPhongPage() {
    }

    public WebElement getElement(By elementBy) {
        return Constant.WEBDRIVER.findElement(elementBy);
    }

    public void clickXemchitiet() {
        getElement(btnXemchitiet).click();
        WaitTime.sleep(1000);
    }
    public void clickGiavaTTP() {
        getElement(btnGiavaTTP).click();
        WaitTime.sleep(1000);
    }

    public void clickLoaiGia() {
        getElement(sleLoaiGia).click();
        WaitTime.sleep(1000);
    }

    public void clickButtonThem() {
        getElement(btnThemMoiGia).click();
        WaitTime.sleep(1000);
    }

    public void chonChinhSachHuy(String loai, int soNgay) {
        if (loai.equalsIgnoreCase("Không hoàn hủy")) {
            Constant.WEBDRIVER.findElement(radioKhongHoanHuy).click();
        } else if (loai.equalsIgnoreCase("Linh động")) {
            Constant.WEBDRIVER.findElement(radioLinhDong).click();

            WebElement dropdown = Constant.WEBDRIVER.findElement(sleSoNgay);
            dropdown.click();

            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
            String ngayText = String.valueOf(soNgay);
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[contains(@class, 'select2') and normalize-space(text())='" + ngayText + "']")));
            option.click();
        }
        WaitTime.sleep(1000);
    }

    public void chonBuaAnBaoGom(String loai, List<String> buaAnOptions) {
        if (loai.equalsIgnoreCase("Không")) {
            Constant.WEBDRIVER.findElement(radioKhong).click();
        } else if (loai.equalsIgnoreCase("Có, thêm lựa chọn bữa ăn")) {
            Constant.WEBDRIVER.findElement(radioCo).click();

            WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
            List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//input[@type='checkbox']")));

            for (WebElement checkbox : checkboxes) {
                WebElement label = checkbox.findElement(By.xpath("following-sibling::label"));
                String labelText = label.getText();

                if (buaAnOptions.contains(labelText) && !checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }
        WaitTime.sleep(2000);

    }


    public void chonLoaiPhongApDung(List<String> loaiPhongOptions) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

        // Nếu người dùng muốn chọn tất cả
        if (loaiPhongOptions.contains("Chọn tất cả")) {
            WebElement chonTatCaBtn = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//button[normalize-space()='Chọn tất cả']")));
            chonTatCaBtn.click();
        } else {
            // Chọn từng checkbox theo tên loại phòng
            List<WebElement> checkboxes = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                    By.xpath("//input[@type='checkbox']")));

            for (WebElement checkbox : checkboxes) {
                WebElement label = checkbox.findElement(By.xpath("following-sibling::label"));
                String labelText = label.getText().trim();

                if (loaiPhongOptions.contains(labelText) && !checkbox.isSelected()) {
                    checkbox.click();
                }
            }
        }
        WaitTime.sleep(1000);
    }

    public void chonCachQLGia(String cachQLGia, String loaiDonVi, String giaChenhlech) {
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

        if (cachQLGia.equalsIgnoreCase("Cài đặt như một loại giá mới")) {
            Constant.WEBDRIVER.findElement(radioGiaMoi).click();
        } else if (cachQLGia.equalsIgnoreCase("Dựa trên Giá cơ bản")) {
            Constant.WEBDRIVER.findElement(radioDuaTheoGiaCB).click();
            WaitTime.sleep(1000);

            WebElement inputGia = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//input[@type='number']")));
            inputGia.sendKeys(Keys.CONTROL + "a");
            inputGia.sendKeys(Keys.DELETE);
            inputGia.sendKeys(giaChenhlech);

            WebElement dropdownDonVi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@class='Section__Body--PriceAdjustment']//div[@class='basic-multi-select css-b62m3t-container']")));
            dropdownDonVi.click();
            WaitTime.sleep(1000);

            WebElement optionDonVi = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//div[@role='option' and normalize-space()='" + loaiDonVi + "']")));
            optionDonVi.click();
        }
        WaitTime.sleep(1000);

    }

    public void nhapTenLoaiGia(String tenLoaiGia) {
        WebElement inputTenLoaiGia = Constant.WEBDRIVER.findElement(txtTenLoaiGia);
        inputTenLoaiGia.sendKeys(tenLoaiGia);
        inputTenLoaiGia.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void clickThem() {
        getElement(btnThem).click();
        WaitTime.sleep(1000);
    }

    public void clickHuy() {
        getElement(btnHuy).click();
        WaitTime.sleep(1000);
    }

    public String layNoiDungPopup() {
        return Constant.WEBDRIVER.findElement(getTextPopup).getText();
    }

    public String layNoiDungError() {
        return Constant.WEBDRIVER.findElement(getTextError).getText();
    }

    public String layNoiDungError_NamePrice() {
        return Constant.WEBDRIVER.findElement(getTextError_NamePrice).getText();
    }

    public void clickClosePopup() {
        Constant.WEBDRIVER.findElement(closePopup).click();
    }

    public String layTenLoaiGiaCuoiCung() {
        WebElement dongCuoi = Constant.WEBDRIVER.findElement(By.xpath("//tbody[@class='Table__Body']/tr[last()]"));
        WaitTime.sleep(1000);
        return dongCuoi.findElement(By.xpath(".//p[@class='TextPrimary']")).getText();
    }

    public int checkboxCount = 0;
    public int roomCardCount = 0;

    public void countCheckboxes() {
        List<WebElement> checkboxes = Constant.WEBDRIVER.findElements(
                By.xpath("//div[@class='Checkbox Checkbox--RoomBlock']")
        );
        checkboxCount = checkboxes.size();
    }

    public void countRoomCards() {
        List<WebElement> roomCards = Constant.WEBDRIVER.findElements(
                By.xpath("//div[contains(@class, 'col-12') and contains(@class, 'col-md-6') and contains(@class, 'col-lg-3') and not(@title='Tạo phòng mới')]")
        );
        roomCardCount = roomCards.size();
    }

    public void soSanhSoLuong() {
        if (checkboxCount == roomCardCount) {
            System.out.println("SỐ LƯỢNG LOẠI PHÒNG KHỚP");
        } else {
            System.out.println("SỐ LƯỢNG LOẠI PHÒNG KHÔNG KHỚP");
            System.out.println("Checkbox: " + checkboxCount + ", RoomCard: " + roomCardCount);
        }
    }

    public void clickChoNghi() {
        getElement(btnChoNghi).click();
        WaitTime.sleep(1000);
    }

    public void clickLoaiPhong() {
        getElement(sleLoaiPhong).click();
        WaitTime.sleep(1000);
    }

    public void chonTatCaCheckboxVaXacNhan() {
        WebElement btnChonTatCa = Constant.WEBDRIVER.findElement(btnChonTatCa_LPAD);
        btnChonTatCa.click();

        List<WebElement> danhSachCheckbox = Constant.WEBDRIVER.findElements(By.xpath("//input[@type='checkbox' and not(@disabled)]"));

        for (WebElement checkbox : danhSachCheckbox) {
            if (!checkbox.isSelected()) {
                throw new AssertionError("Checkbox chưa được tick: " + checkbox.toString());
            }
        }
        System.out.println("Tất cả checkbox đã được tick chọn thành công sau khi nhấn 'Chọn tất cả'");
    }
}
