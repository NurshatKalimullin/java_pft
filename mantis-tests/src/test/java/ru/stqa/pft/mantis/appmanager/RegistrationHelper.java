package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.stqa.pft.mantis.tests.TestBase;

public class RegistrationHelper extends HelperBase {
    private WebDriver wd;

    public RegistrationHelper(ApplicationManager app) {
        super(app);
        wd = app.getDriver();
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseUrl") + "/signup_page.php");
        type(By.name("username"), username);
        type(By.name("email"), email);
        click(By.cssSelector("input[class='width-40 pull-right btn btn-success btn-inverse bigger-110']"));

    }

    public void finish(String confirmarionLink, String password) {
        wd.get(confirmarionLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[class='width-100 width-40 pull-right btn btn-success btn-inverse bigger-110']"));
    }

    public void login(String username, String password) {
        wd.get(app.getProperty("web.baseUrl") + "/login_page.php");
        type(By.name("username"), username);
        click(By.cssSelector("input.width-40.pull-right.btn.btn-success.btn-inverse.bigger-110"));
        type(By.name("password"), password);
        click(By.cssSelector("input.width-40.pull-right.btn.btn-success.btn-inverse.bigger-110"));
    }

    public void resetPassword(String userName) {
        click(By.xpath("//div[@id='sidebar']/ul/li[6]/a/span"));
        click(By.xpath("//div[@id='main-container']/div[2]/div[2]/div/ul/li[2]/a"));
        click(By.xpath(String.format("//a[contains(text(),'%s')]", userName)));
        //click(By.xpath("//form[@id='manage-user-reset-form']/fieldset/span/input"));
        //click(By.cssSelector("input[value='Сбросить пароль']"));
        //click(By.xpath("//input[@value='Сбросить пароль']"));
        click(By.xpath("//fieldset/span/input"));


    }
}
















