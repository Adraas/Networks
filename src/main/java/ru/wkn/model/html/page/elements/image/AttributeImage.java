package ru.wkn.model.html.page.elements.image;

import ru.wkn.model.html.page.elements.Attribute;

import java.util.Objects;

public class AttributeImage extends Attribute {

    private String attributeName;
    private String attributeValue;

    public AttributeImage(String attributeName, String attributeValue) {
        super(attributeName, attributeValue);
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
        AttributeImage that = (AttributeImage) o;
        return Objects.equals(attributeName, that.attributeName) &&
                Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public String toString() {
        return "AttributeImage{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
