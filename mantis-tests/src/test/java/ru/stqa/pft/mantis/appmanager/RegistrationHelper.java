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
}
















