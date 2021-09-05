package ru.stqa.pft.mantis.tests;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

import static org.testng.Assert.assertTrue;

public class RegistrationTests extends TestBase {

    //@BeforeMethod /*используем только со встроенным сервером*/
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testRegistration() throws MessagingException, IOException {
        long now = System.currentTimeMillis();
        String email = String.format("user%s@localhost", now);
        String user = String.format("user%s", now);
        String password = "password";
        app.james().createUser(user, password);
        app.james().drainEmail(user, password);
        app.registration().start(user, email);
        //List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000); //встроенный почтовый сервер
        List<MailMessage> mailMessages = app.james().waitForMail(user, password, 70000); //внешний почтовый сервер
        String confirmarionLink = app.registration().findConfirmarionLink(mailMessages, email);
        app.registration().finish(confirmarionLink, password);
        assertTrue(app.newSession().login(user,password));
    }

    //@AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }
}
