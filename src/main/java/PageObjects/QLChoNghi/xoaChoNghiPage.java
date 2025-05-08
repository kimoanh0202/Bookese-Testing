package PageObjects.QLChoNghi;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class xoaChoNghiPage {

    private By btnXoa = By.xpath("//button[normalize-space()='Xóa']");
    private By btnXacNhanXoa = By.xpath("//button[contains(text(),'Xác nhận')]");
    private By alertText = By.xpath("//div[@role='alert']");
    private By inputTimKiem = By.xpath("//input[@placeholder='Tìm kiếm theo tên chỗ nghỉ']");
    private By btnTimKiem = By.xpath("//button[contains(text(),'Tìm kiếm')]");
    private By ketQuaTimKiem = By.xpath("//td[2]");
    private By btnHuy = By.xpath("//button[contains(text(),'Huỷ')]");

    public WebElement getElement(By locator) {
        return Constant.WEBDRIVER.findElement(locator);
    }

    public void clickXoa() {
        getElement(btnXoa).click();
        WaitTime.sleep(1000);
    }

    public void xacNhanXoa() {
        getElement(btnXacNhanXoa).click();
        WaitTime.sleep(1000);
    }

    public String getAlertText() {
        return getElement(alertText).getText();
    }

    // Tìm kiếm chỗ nghỉ theo tên
    public List<WebElement> timKiemKetQua(String tenChoNghi) {
        WebElement inputTimKiemElement = getElement(inputTimKiem);
        inputTimKiemElement.sendKeys(Keys.CONTROL + "a");
        inputTimKiemElement.sendKeys(Keys.DELETE);
        WaitTime.sleep(1000);
        inputTimKiemElement.sendKeys(tenChoNghi);
        getElement(btnTimKiem).click();
        WaitTime.sleep(1000);

        List<WebElement> ketQuaTimKiemList = Constant.WEBDRIVER.findElements(ketQuaTimKiem);
        List<WebElement> ketQuaChinhXac = new ArrayList<>();
        for (WebElement element : ketQuaTimKiemList) {
            if (element.getText().equals(tenChoNghi)) {
                ketQuaChinhXac.add(element);
            }
        }
        return ketQuaChinhXac;
    }

    public void nhanNutHuyXoa() {
        getElement(btnHuy).click();
        WaitTime.sleep(2000);
    }

    public void xoaTimkiem() {
        WebElement inputTimKiemElement = getElement(inputTimKiem);
        inputTimKiemElement.sendKeys(Keys.CONTROL + "a");
        inputTimKiemElement.sendKeys(Keys.DELETE);
    }
}