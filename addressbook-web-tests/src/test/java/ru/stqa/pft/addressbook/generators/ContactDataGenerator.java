package ru.stqa.pft.addressbook.generators;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.google.gson.*;
import com.thoughtworks.xstream.XStream;
import ru.stqa.pft.addressbook.model.ContactData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContactDataGenerator {

    @Parameter(names = "-c", description = "Group count")
    public int count;

    @Parameter(names = "-f", description = "Target file")
    public String file;

    @Parameter(names = "-d", description = "Data format")
    public String format;


    public static void main(String[] args) throws IOException {
        ContactDataGenerator generator = new ContactDataGenerator();
        JCommander jCommander = new JCommander(generator);
        try {
            jCommander.parse(args);
        } catch (ParameterException ex){
            jCommander.usage();
            return;
        }
        generator.run();
    }

    private void run() throws IOException {
        List<ContactData> contacts = generateContacts(count);
        if (format.equals("csv")){
            saveAsCsv(contacts, new File(file));
        } else if (format.equals("xml")){
            saveAsXml(contacts, new File(file));
        } else if (format.equals("json")) {
            saveAsJson(contacts, new File(file));
        } else {
            System.out.println("Unrecognized format " + format);
        }

    }

    private void saveAsJson(List<ContactData> contacts, File file) throws IOException {
        //serialize to write property path
        Gson gson = new GsonBuilder().setPrettyPrinting()
                .registerTypeAdapter(File.class, new FileSerializer())
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        String json = gson.toJson(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(json);
        }
    }

    private static class FileSerializer implements JsonSerializer<File> {
        public JsonElement serialize(File src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    private void saveAsXml(List<ContactData> contacts, File file) throws IOException {
        XStream xstream = new XStream();
        xstream.processAnnotations(ContactData.class);
        String xml = xstream.toXML(contacts);
        try (Writer writer = new FileWriter(file)){
            writer.write(xml);
        }
    }

    private void saveAsCsv(List<ContactData> contacts, File file) throws IOException {
        try (Writer writer = new FileWriter(file)){
            for (ContactData contact : contacts){
                writer.write(String.format("%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s;%s\n",
                        contact.getFirstName(), contact.getLastName(), contact.getNickname(),
                        contact.getCompanyType(), contact.getCompanyName(), contact.getHomeAddress(),
                        contact.getHomePhone(), contact.getEmail(), contact.getEmail2(),
                        contact.getEmail3(), contact.getMobilePhone(),
                        contact.getWorkPhone(), contact.getPhoto())); //contact.getGroup()
            }
        }
    }

    private List<ContactData> generateContacts(int count) {
        List<ContactData> contacts = new ArrayList<ContactData>();
        File photo = new File("src/test/resources/icon.png");
        for (int i = 0; i < count; i++){
            ContactData newContact = new ContactData().withFirstName(String.format("Frodo %s", i))
                    .withLastName(String.format("Baggins %s", i))
                    .withNickname(String.format("Burglar %s", i)).withCompanyType("LLC")
                    .withCompanyName(String.format("The Fellowship of the Ring #%s", i))
                    .withHomeAddress(String.format("The Shire, The Hill, Bag End #%s", i))
                    .withHomePhone(String.format("+(277)29098126%s", i))
                    .withMobilePhone(String.format("+7 962 123 09 1%s", i))
                    .withWorkPhone(String.format("+8-812-264-54-7%s", i))
                    .withEmail(String.format("Bilbo-Adventurer%s@shire.com", i))
                    .withEmail2(String.format("The.Ring.Holder%s@shire.com", i))
                    .withEmail3(String.format("Oldest_Hobbit%s@shire.com", i))
                    .withPhoto(photo); //.inGroup(groups.iterator().next())
            contacts.add(newContact);
        }
        return contacts;
    }
}
