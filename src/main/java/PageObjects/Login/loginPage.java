package PageObjects.Login;

import Common.Constant;
import org.openqa.selenium.By;

public class loginPage {
    public void openLoginPage() {
        Constant.WEBDRIVER.get(Constant.Bookese_Admin);
    }

    public void login (String username, String password) {
        Constant.WEBDRIVER.findElement(By.name("email")).sendKeys(username);
        Constant.WEBDRIVER.findElement(By.name("password")).sendKeys(password);
        Constant.WEBDRIVER.findElement(By.xpath("//button[@type='submit']")).click();
    }
}