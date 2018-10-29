package ru.wkn.model.html.page.elements.attributes;

import java.util.Objects;

public class Attribute {

    private String attributeName;
    private String attributeValue;

    public Attribute(String attributeName, String attributeValue) {
        this.attributeName = attributeName;
        this.attributeValue = attributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Attribute attribute = (Attribute) o;
        return Objects.equals(attributeName, attribute.attributeName) &&
                Objects.equals(attributeValue, attribute.attributeValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributeName, attributeValue);
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
