package ru.stqa.pft.mantis.tests;

import biz.futureware.mantis.rpc.soap.client.IssueData;
import biz.futureware.mantis.rpc.soap.client.MantisConnectPortType;
import com.google.protobuf.ServiceException;
import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.SkipException;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.RemoteException;

import static ru.stqa.pft.mantis.appmanager.SoapHelper.getMantisConnect;

public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);


    protected static final ApplicationManager app
            = new ApplicationManager (System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/resources/config_inc.php"), "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config_inc.php.bak","config_inc.php");
        app.stop();
    }

    public boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, javax.xml.rpc.ServiceException, RemoteException {
        MantisConnectPortType mc = app.soap().getMantisConnect();;
        IssueData issue = mc.mc_issue_get("administrator", "root", BigInteger.valueOf(issueId));
        if  (issue.getStatus().getName().equals("resolved")) {
            return  false;
        }
        else {
            return true;
        }
    }

    public boolean skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, javax.xml.rpc.ServiceException, RemoteException {
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
        return false;
    }

}










