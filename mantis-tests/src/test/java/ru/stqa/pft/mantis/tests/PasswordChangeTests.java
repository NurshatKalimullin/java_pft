package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;
import ru.stqa.pft.mantis.model.UserData;
import ru.stqa.pft.mantis.model.Users;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class PasswordChangeTests extends TestBase{

    public void startMailServer(){
        app.mail().start();
    }


    @Test
    public void testPasswordChange() throws MessagingException, IOException {
        app.registration().login("administrator", "root");
        Users list = app.db().users();
        UserData selectedUser = list.iterator().next();
        System.out.println(selectedUser);
        Integer userId = selectedUser.getId();
        if (selectedUser.getUserName().equals("administrator")) {
            //list.remove();
            Set<UserData> userDataHashSet = new HashSet<UserData>();
            userDataHashSet.add(selectedUser);
            System.out.println(userDataHashSet);
            selectedUser = list.iterator().next().withId(userId + 1);
        }
        String userName = selectedUser.getUserName();
        String password = selectedUser.getPassword();
        String email = selectedUser.getEmail();
        app.registration().resetPassword(userName);
        List<MailMessage> mailMessages = app.mail().waitForMail(2, 10000);
        String passwordResetLink = findConfirmarionLink(mailMessages, email);
        app.registration().finish(passwordResetLink, password);
    }

    private String findConfirmarionLink(List<MailMessage> mailMessages, String email) {
        MailMessage mailMessage = mailMessages.stream().filter((m) -> m.to.equals(email)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

}
