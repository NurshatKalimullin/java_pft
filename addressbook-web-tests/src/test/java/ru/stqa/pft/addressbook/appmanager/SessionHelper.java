package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String username, String password) {

        wd.get("http://localhost/addressbook/");
        type(By.name("user"), username );
        type(By.name("pass"), password);
        /**
         * click(By.cssSelector("input[type=\"submit\"]"));
         * click(By.xpath("//form[@id='LoginForm']/input[3]"));
        */
        click(By.xpath("//input[@value='Login']"));
    }

    public void logOut() {
        click(By.xpath("//a[contains(text(),'Logout')]"));
    }
}
