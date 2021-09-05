package ru.stqa.pft.mantis.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import static org.testng.Assert.assertTrue;

public class PasswordChangeTests extends TestBase{

    @BeforeMethod
    public void startMailServer(){
        app.mail().start();
    }

    @Test
    public void testPasswordChange() throws MessagingException, IOException {
        Users users = app.db().users();
        UserData selectedUser = users.iterator().next();
        Iterator<UserData> iteratedUsers = users.iterator();
        //I seriously did not find another way to skip administrator is being selected
        while (selectedUser.getUserName().equals("administrator")) {
            selectedUser = iteratedUsers.next();
        }
        String userName = selectedUser.getUserName();
        String newPassword = selectedUser.getPassword() + "1";
        String email = selectedUser.getEmail();
        app.registration().login("administrator", "root");
        app.registration().resetPassword(userName);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String passwordResetLink = app.registration().findConfirmarionLink(mailMessages, email);
        //app.registration().finish(passwordResetLink, newPassword);
        app.registration().setNewPassword(passwordResetLink, userName, newPassword);
        assertTrue(app.newSession().login(userName, newPassword));
    }

    @AfterMethod(alwaysRun = true)
    public void stopMailServer(){
        app.mail().stop();
    }

}
