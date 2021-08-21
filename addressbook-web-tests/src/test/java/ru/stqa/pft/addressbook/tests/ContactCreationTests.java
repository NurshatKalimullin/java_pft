package ru.stqa.pft.addressbook.tests;

import com.google.gson.*;
import com.thoughtworks.xstream.XStream;
import org.openqa.selenium.json.TypeToken;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class ContactCreationTests extends TestBase {

    @DataProvider
    public Iterator<Object[]> validContactsFromXml() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/contacts.xml")));){
            String xml = "";
            String line = reader.readLine();
            while (line != null) {
                xml += line;
                line = reader.readLine();
            }
            XStream xstream = new XStream();
            xstream.processAnnotations(ContactData.class);
            List<ContactData> contacts = (List<ContactData>) xstream.fromXML(xml);
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }


    }

    @DataProvider
    public Iterator<Object[]> validContactFromJson() throws IOException {
        try (BufferedReader reader = new BufferedReader(
                new FileReader(new File("src/test/resources/contacts.json")));) {
            String json = "";
            String line = reader.readLine();
            while (line != null) {
                json += line;
                line = reader.readLine();
            }
            //deserialize to read property file path
            Gson gson = new GsonBuilder().registerTypeAdapter(File.class, new DateTimeDeserializer()).create();
            List<ContactData> contacts = gson.fromJson(json, new TypeToken<List<ContactData>>() {
            }
                    .getType());
            return contacts.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
        }


    }

    private static class DateTimeDeserializer implements JsonDeserializer<File> {
        public File deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return new File(json.getAsJsonPrimitive().getAsString());
        }
    }

    @Test(dataProvider = "validContactFromJson")
    public void testCreateContact(ContactData contact) {
        app.goTo().homePage();
        Contacts before = app.db().contacts(); //from database
        //Contacts before = app.contact().all(); //from web interface
        app.contact().createContact(contact, true);
        Contacts after = app.db().contacts(); //from database
        //Contacts after = app.contact().all(); //from web interface

        //compare number of Contacts
        assertThat(after.size(), equalTo(before.size() + 1));

        //compare insides of Contacts (here before is a copy)
        assertThat(after, equalTo(before.
                withAdded(contact.withId(after.stream().mapToInt(g -> g.getId()).max().getAsInt()))));
    }


    @Test(enabled = false)
    public void testCurrentDir() {
        File currentDir = new File(".");
        System.out.println(currentDir.getAbsolutePath());
        File photo = new File("src/test/resources/icon.png");
        System.out.println(photo.getAbsolutePath());
        System.out.println(photo.exists());
    }

}