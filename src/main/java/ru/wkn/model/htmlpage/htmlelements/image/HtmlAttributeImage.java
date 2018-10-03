package ru.wkn.model.htmlpage.htmlelements.image;

import ru.wkn.model.htmlpage.htmlelements.HtmlAttribute;

import java.util.Objects;

public class HtmlAttributeImage extends HtmlAttribute {

    private String attributeName;
    private String attributeValue;

    public HtmlAttributeImage(String attributeName, String attributeValue) {
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
        HtmlAttributeImage that = (HtmlAttributeImage) o;
        return Objects.equals(attributeName, that.attributeName) &&
                Objects.equals(attributeValue, that.attributeValue);
    }

    @Override
    public String toString() {
        return "HtmlAttributeImage{" +
                "attributeName='" + attributeName + '\'' +
                ", attributeValue='" + attributeValue + '\'' +
                '}';
    }
}
