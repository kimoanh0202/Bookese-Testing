package PageObjects.TimKiemChoNghi;

import Common.Constant;
import Common.WaitTime;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class timKiemChoNghiPage {

    private final By inputTimKiem = By.xpath("//input[@placeholder='Tìm kiếm theo tên chỗ nghỉ']");
    private final By btnTimKiem = By.xpath("//button[contains(text(),'Tìm kiếm')]");
    private final By tenChoNghi = By.xpath("//td[2]");

    public boolean timKiemChoNghi(String tenChoNghiText) {
        try {
            WebElement input = Constant.WEBDRIVER.findElement(inputTimKiem);
            input.clear();
            input.sendKeys(tenChoNghiText);

            Constant.WEBDRIVER.findElement(btnTimKiem).click();
            WaitTime.sleep(2000);

            WebElement result = Constant.WEBDRIVER.findElement(tenChoNghi);
            return result != null && result.getText().contains(tenChoNghiText);
        } catch (Exception e) {
            return false;
        }
    }

    public void hoverTenChoNghi() {
        try {
            WebElement element = Constant.WEBDRIVER.findElement(tenChoNghi);
            Actions actions = new Actions(Constant.WEBDRIVER);
            actions.moveToElement(element).perform();
            WaitTime.sleep(1000);
        } catch (Exception e) {
            System.out.println("Không thể hover vào tên chỗ nghỉ: " + e.getMessage());
        }
    }
}
