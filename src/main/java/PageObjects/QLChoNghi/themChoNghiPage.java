package PageObjects.QLChoNghi;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class themChoNghiPage {
    // ===== BUTTONS =====
    private By btnBTT_RoomPrice = By.xpath("//div[@class='StepRoomInformation_FormAction__2OIUC']//button[@type='submit'][contains(text(),'Bước tiếp theo')]");
    private By btnBTT_Information = By.xpath("//button[@type='submit'][contains(text(),'Bước tiếp theo')]");
    private By btnBTT_Service = By.xpath("//div[@class='StepAmenitiesService_FormAction__2QC9o']//button[@type='submit'][contains(text(),'Bước tiếp theo')]");
    private By btnBTT_Img = By.xpath("(//button[@type='submit'][contains(text(),'Bước tiếp theo')])[4]");
    private By btnBTT_Policy = By.xpath("(//button[@type='submit'][contains(text(),'Bước tiếp theo')])[5]");
    private By btnThemChoNghi = By.xpath("//button[contains(text(),'Thêm chỗ nghỉ')]");
    private By btnXacNhan = By.xpath("//button[contains(text(),'Xác nhận')]");
    private By btnTaoChoNghi = By.xpath("//button[contains(text(),'Tạo chỗ nghỉ')]");
    private By btnClosePopup = By.xpath("//button[@name='Đang gửi']");

    // ===== POPUP / ERROR =====
    private By getTextPopup = By.xpath("//div[@class='ModalSuccess_ModalBody__1lRJk modal-body']");
    private By messageError = By.xpath("//div[@type='invalid']");
    private By alertMessage = By.xpath("//div[@role='alert']/div[2]");

    // ===== BASIC INFORMATION =====
    private By sleLoaihinh = By.xpath("//form[@class='d-flex flex-column gap-4']//div[contains(@class,'select2-selection__value-container')]");
    private By txtTenCN = By.xpath("(//input[@id='name'])[1]");
    private By sleHangSao = By.xpath("//body/div[@id='root']/div[@id='layout-wrapper']/div[@class='main-content']/div[@class='page-content']/div[@class='container-fluid']/div[@class='EditLocation_Page__gQLBv']/section[@class='EditLocation_NavBody__2lsA8']/div[@class='tab-content']/div[@class='tab-pane active']/div[@class='StepBasic_Component__3thMQ']/form[@class='d-flex flex-column gap-4']/div[1]/div[3]/div[1]");
    private By txtTennguoiLH = By.xpath("(//input[@id='name'])[2]");
    private By sleNgonNgu = By.xpath("//div[@id='mainLanguage']");
    private By sleChucVu = By.xpath("//div[@id='role']");
    private By txtEmail = By.xpath("//input[@id='email']");
    private By txtSDT = By.xpath("//input[@id='phoneNumbers']");
    private By txtSDTkhac = By.xpath("(//input[@id='phoneNumbers'])[2]");
    private By sleTinhTP = By.xpath("//input[@id='province']");
    private By sleQuanHuyen = By.xpath("//input[@id='district']");
    private By slePhuongXa = By.xpath("//input[@id='ward']");
    private By txtDiaChi = By.xpath("//input[@id='address']");
    private By txtDinhVi = By.xpath("//input[@id='coordinates']");
    private By txtMieutachonghi = By.xpath("//input[@id='description']");

    // ===== ROOM AND PRICE =====
    private By sleTenPhong = By.xpath("//input[@id='roomName']");
    private By txtTenTuyChon = By.xpath("//form[@class='StepRoomInformation_Component__2CbHo']//input[@id='name']");
    private By txtDientich = By.xpath("//input[@name='size']");
    private By txtSophong = By.xpath("//input[@name='quantity']");
    private By txtSophongNgu = By.xpath("//input[@name='totalBedrooms']");
    private By txtGia = By.xpath("//input[@placeholder='Nhập số tiền']");

    // ===== LANGUAGE SELECT =====
    private By sleNgonNguDuocSD = By.xpath("//div[@class='StepAmenitiesService_Component__wnqBH']//div[@class='basic-multi-select css-b62m3t-container']");

    // ===== IMAGE =====
    private By addImg = By.xpath("//input[@accept='image/*']");
    private By addImgSVG = By.xpath("//input[@accept='image/*']");


    // ===== POLICY =====
    private By slePolicy1 = By.xpath("//input[@id='policy1']");
    private By slePolicy2 = By.xpath("//input[@id='policy2']");

    public themChoNghiPage(){
    }

    public WebElement getElement(By elementBy) {
        return Constant.WEBDRIVER.findElement(elementBy);
    }


    public void sleLoaiHinh(String LoaiHinh) {
        WebElement selectType = getElement(sleLoaihinh);
        selectType.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxType = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement typeOption = listBoxType.findElement(By.xpath(".//div[text()='" + LoaiHinh + "']"));
        typeOption.click();
        WaitTime.sleep(1000);
    }

    public void txtTenCN(String TenCN) {
        WebElement inputTenCN = getElement(txtTenCN);
        inputTenCN.sendKeys(TenCN);
        inputTenCN.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void sleHangSao(String Hangsao) {
        WebElement selectStar = getElement(sleHangSao);
        selectStar.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxStar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement starOption = listBoxStar.findElement(By.xpath(".//div[text()='" + Hangsao + "']"));
        starOption.click();
        WaitTime.sleep(1000);
    }

    public void txtTennguoiLH(String TenNguoiLienHe) {
        WebElement inputTennguoiLH = getElement(txtTennguoiLH);
        inputTennguoiLH.sendKeys(TenNguoiLienHe);
        inputTennguoiLH.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void sleNgonNgu(String NgonNgu) {
        WebElement selectLanguage = getElement(sleNgonNgu);
        selectLanguage.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxLanguage = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement languageOption = listBoxLanguage.findElement(By.xpath(".//div[text()='" + NgonNgu + "']"));
        languageOption.click();
        WaitTime.sleep(1000);
    }

    public void sleChucVu(String ChucVu) {
        WebElement selectChucvu = getElement(sleChucVu);
        selectChucvu.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxChucVu = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement chucVuOption = listBoxChucVu.findElement(By.xpath(".//div[text()='" + ChucVu + "']"));
        chucVuOption.click();
        WaitTime.sleep(1000);
    }

    public void txtEmail(String Email) {
        WebElement inputEmail = getElement(txtEmail);
        inputEmail.sendKeys(Email);
        inputEmail.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtSDT(String SDT) {
        WebElement inputSDT = getElement(txtSDT);
        inputSDT.sendKeys(SDT);
        inputSDT.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtSDTkhac(String SDTkhac) {
        WebElement inputSDTkhac = getElement(txtSDTkhac);
        inputSDTkhac.sendKeys(SDTkhac);
        inputSDTkhac.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void sleTinhTP(String TinhThanh) {
        WebElement selectCity = getElement(sleTinhTP);
        selectCity.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxCity = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement cityOption = listBoxCity.findElement(By.xpath(".//div[text()='" + TinhThanh + "']"));
        cityOption.click();
        WaitTime.sleep(1000);
    }

    public void sleQuanHuyen(String QuanHuyen) {
        WebElement selectDistrict = getElement(sleQuanHuyen);
        selectDistrict.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxDistrict = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement districtOption = listBoxDistrict.findElement(By.xpath(".//div[text()='" + QuanHuyen + "']"));
        districtOption.click();
        WaitTime.sleep(1000);
    }

    public void slePhuongXa(String PhuongXa) {
        WebElement selectWard = getElement(slePhuongXa);
        selectWard.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxWard = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement wardOption = listBoxWard.findElement(By.xpath(".//div[text()='" + PhuongXa + "']"));
        wardOption.click();
        WaitTime.sleep(1000);
    }

    public void txtDiaChi(String DiaChi) {
        WebElement inputDiaChi = getElement(txtDiaChi);
        inputDiaChi.sendKeys(DiaChi);
        inputDiaChi.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtDinhVi(String DinhVi) {
        WebElement inputDinhVi = getElement(txtDinhVi);
        inputDinhVi.sendKeys(DinhVi);
        inputDinhVi.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtMieutachonghi(String Mieuta) {
        WebElement inputMieuta = getElement(txtMieutachonghi);
        inputMieuta.sendKeys(Mieuta);
        inputMieuta.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void sleTenPhong(String Tenphong) {
        WebElement selectRoomName = getElement(sleTenPhong);
        selectRoomName.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxRoomName = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement roomNameOption = listBoxRoomName.findElement(By.xpath(".//div[text()='" + Tenphong + "']"));
        roomNameOption.click();
        WaitTime.sleep(1000);
    }

    public void txtTenTuyChon(String Tentuychon) {
        WebElement inputTenTuyChon = getElement(txtTenTuyChon);
        inputTenTuyChon.sendKeys(Tentuychon);
        inputTenTuyChon.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtDientich(String Dientich) {
        WebElement inputDienTich = getElement(txtDientich);
        inputDienTich.sendKeys(Dientich);
        inputDienTich.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtSophong(String Sophong) {
        WebElement inputSoPhong = getElement(txtSophong);
        inputSoPhong.sendKeys(Sophong);
        inputSoPhong.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtSophongngu(String SophongNgu) {
        WebElement inputSophongngu = getElement(txtSophongNgu);
        inputSophongngu.sendKeys(SophongNgu);
        inputSophongngu.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void txtGiaPhong(String Gia) {
        WebElement inputGiaPhong = getElement(txtGia);
        inputGiaPhong.sendKeys(Gia);
        inputGiaPhong.sendKeys(Keys.TAB);
        WaitTime.sleep(1000);
    }

    public void clickBTT_PhongVaGia() {
        getElement(btnBTT_RoomPrice).click();
        WaitTime.sleep(1000);
    }

    public void sleNgonNguDuocSD(String ngonNgu1, String ngonNgu2) {
        WebElement selectNgonNgu = getElement(sleNgonNguDuocSD);
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));

        selectNgonNgu.click();
        WebElement listBoxNgonNgu = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement option1 = listBoxNgonNgu.findElement(By.xpath(".//div[text()='" + ngonNgu1 + "']"));
        option1.click();
        WaitTime.sleep(500);

        selectNgonNgu.click();
        listBoxNgonNgu = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement option2 = listBoxNgonNgu.findElement(By.xpath(".//div[text()='" + ngonNgu2 + "']"));
        option2.click();
        WaitTime.sleep(500);
    }

    public void addIMG() {
        WebElement imgInput = getElement(addImg);
        imgInput.sendKeys(
                "D:\\Testing\\Intern\\Wiicamp 2025\\img-test\\img1.jpg\n" +
                        "D:\\Testing\\Intern\\Wiicamp 2025\\img-test\\img2.jpg\n" +
                        "D:\\Testing\\Intern\\Wiicamp 2025\\img-test\\img3.jpg\n" +
                        "D:\\Testing\\Intern\\Wiicamp 2025\\img-test\\img4.jpg"
        );
    }

    public void clickBTT_Information() {
        getElement(btnBTT_Information).click();
        WaitTime.sleep(1000);
    }

    public void clickBTT_Service(){
        getElement(btnBTT_Service).click();
        WaitTime.sleep(1000);
    }

    public void clickBTT_Img(){
        getElement(btnBTT_Img).click();
        WaitTime.sleep(1000);
    }

    public void clickBTT_Policy(){
        getElement(btnBTT_Policy).click();
        WaitTime.sleep(1000);
    }

    public void selectPolicy1(){
        getElement(slePolicy1).click();
        WaitTime.sleep(1000);
    }

    public void selectPolicy2(){
        getElement(slePolicy2).click();
        WaitTime.sleep(1000);
    }

    public void btnThemChoNghi(){
        getElement(btnThemChoNghi).click();
        WaitTime.sleep(1000);
    }

    public void btnXacNhan(){
        getElement(btnXacNhan).click();
        WaitTime.sleep(1000);
    }

    public void btnTaoChoNghi(){
        getElement(btnTaoChoNghi).click();
        WaitTime.sleep(1000);
    }

    public String getTextPopUp() {
        return getElement(getTextPopup).getText();
    }

    public void btnClosePopup(){
        getElement(btnClosePopup).click();
        WaitTime.sleep(1000);
    }

    public List<WebElement> isTenChoNghiDisplayed(String tenChoNghi) {
        List<WebElement> ketQuaTimKiem = Constant.WEBDRIVER.findElements(By.xpath("//td[2][contains(text(),'" + tenChoNghi + "')]"));
        List<WebElement> ketQuaChinhXac = new ArrayList<>();
        for (WebElement element : ketQuaTimKiem) {
            if (element.getText().equals(tenChoNghi)) {
                ketQuaChinhXac.add(element);
            }
        }
        return ketQuaChinhXac;
    }

    public String checkValidte() {
        try {
            WebElement errorMesaage = getElement(messageError);
            if (errorMesaage.isDisplayed()) {
                return errorMesaage.getText();
            }
            return "";
        } catch (Exception e) {
            return "";
        }
    }
}