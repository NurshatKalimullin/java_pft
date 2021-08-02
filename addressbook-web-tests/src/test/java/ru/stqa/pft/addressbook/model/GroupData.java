package ru.stqa.pft.addressbook.model;

import java.util.Objects;

public class GroupData {
    private static int id;
    private final String name;
    private final String header;
    private final String footer;


    /* this constructor works as next one, because id here is null. And when code will appeal to GroupData()
    * without id it will use this one GroupData
    * here id is not read from web interface, but created as null with code
     */
    public GroupData(String name, String header, String footer) {
        this.id = Integer.MAX_VALUE; // id for new group always will be equal to max value in code
        this.name = name;
        this.header = header;
        this.footer = footer;
    }


    public GroupData(int id, String name, String header, String footer) {
        this.id = id;
        this.name = name;
        this.header = header;
        this.footer = footer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getHeader() {
        return header;
    }

    public String getFooter() {
        return footer;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupData groupData = (GroupData) o;
        return Objects.equals(name, groupData.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
