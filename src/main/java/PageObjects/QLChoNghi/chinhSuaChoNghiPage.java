package PageObjects.QLChoNghi;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class chinhSuaChoNghiPage {

    private By btnXemchitiet = By.xpath("//button[normalize-space()='Xem chi tiết']");
    private By btnChinhSua = By.xpath("//button[contains(text(),'Chỉnh sửa')]");
    private By btnGui = By.xpath("//button[contains(text(),'Gửi')]");
    private By btnHuy = By.xpath("//button[contains(text(),'Hủy')]");
    private By alertTextSuccessful = By.xpath("//div[@role='alert']");
    private By errorMessage = By.xpath("//div[@type='invalid']");

    private By txtTenCN = By.xpath("//input[@id='name']");
    private By sleLoaiHinh = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[2]/div[2]/div[1]");
    private By sleHangSao = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[3]/div[2]/div[1]/div[1]");
    private By sleTinhTP = By.xpath("//body/div/div/div[@role='dialog']/div[@role='document']/div[@class='modal-content']/form/div[@class='ModalEditLocation_ModalBody__dnUHj modal-body']/div[@class='FormField_Component__tD-GQ']/div[4]/div[2]/div[1]");
    private By sleQuanHuyen = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[5]/div[2]/div[1]");
    private By slePhuongXa = By.xpath("//body[1]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[2]/div[1]/div[6]/div[2]/div[1]");
    private By txtDiaChi = By.xpath("//input[@id='address']");
    private By txtDinhVi = By.xpath("//input[@id='coordinates']");
    private By txtMieutachonghi = By.xpath("//textarea[@id='description']");

    public chinhSuaChoNghiPage() {
    }

    public WebElement getElement(By elementBy) {
        return Constant.WEBDRIVER.findElement(elementBy);
    }

    public void clickXemchitiet() {
        getElement(btnXemchitiet).click();
        WaitTime.sleep(1000);
    }

    public void clickChinhSua() {
        getElement(btnChinhSua).click();
        WaitTime.sleep(1000);
    }

    public void clickGui() {
        getElement(btnGui).click();
        WaitTime.sleep(1000);
    }

    public void clickHuy() {
        getElement(btnHuy).click();
        WaitTime.sleep(1000);
    }

    public boolean isPopupVisible() {
        try {
            return Constant.WEBDRIVER.findElement(By.xpath("//div[@class='modal-content']")).isDisplayed();
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public String layTenChoNghi() {
        return Constant.WEBDRIVER.findElement(By.xpath("//p[@class='InformationContentTitle__Name']")).getText();
    }

    public String getAlertTextSuccessful() {
        return getElement(alertTextSuccessful).getText();
    }

    public String getErrorMessage() {
        return getElement(errorMessage).getText();
    }

    public void updateTenCN(String TenCN) {
        WebElement inputTenCN = getElement(txtTenCN);
        inputTenCN.sendKeys(Keys.CONTROL + "a");
        inputTenCN.sendKeys(Keys.DELETE);
        inputTenCN.sendKeys(TenCN);
        WaitTime.sleep(1000);
    }

    public void updateLoaiHinh(String LoaiHinh) {
        WebElement selectType = getElement(sleLoaiHinh);
        selectType.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxType = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement typeOption = listBoxType.findElement(By.xpath(".//div[text()='" + LoaiHinh + "']"));
        typeOption.click();
        WaitTime.sleep(1000);
    }

    public void updateHangSao(String HangSao) {
        WebElement selectStar = getElement(sleHangSao);
        selectStar.click();
        WebDriverWait wait = new WebDriverWait(Constant.WEBDRIVER, Duration.ofSeconds(10));
        WebElement listBoxStar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[contains(@class,'select2-selection__menu-list')]")
        ));
        WebElement starOption = listBoxStar.findElement(By.xpath(".//div[text()='" + HangSao + "']"));
        starOption.click();
        WaitTime.sleep(1000);
    }

    public void updateTinhThanh(String TinhThanh) {
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

    public void updateQuanHuyen(String QuanHuyen) {
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

    public void updatePhuongXa(String PhuongXa) {
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

    public void updateDiaChi(String DiaChi) {
        WebElement inputDiaChi = getElement(txtDiaChi);
        inputDiaChi.sendKeys(Keys.CONTROL + "a");
        inputDiaChi.sendKeys(Keys.DELETE);
        inputDiaChi.sendKeys(DiaChi);
        WaitTime.sleep(1000);
    }

    public void updateDinhVi(String DinhVi) {
        WebElement inputDinhVi = getElement(txtDinhVi);
        inputDinhVi.sendKeys(Keys.CONTROL + "a");
        inputDinhVi.sendKeys(Keys.DELETE);
        inputDinhVi.sendKeys(DinhVi);
        WaitTime.sleep(1000);
    }

    public void updateMieuTa(String MieuTa) {
        WebElement inputMieuTa = getElement(txtMieutachonghi);
        inputMieuTa.sendKeys(Keys.CONTROL + "a");
        inputMieuTa.sendKeys(Keys.DELETE);
        inputMieuTa.sendKeys(MieuTa);
        WaitTime.sleep(1000);
    }
}
