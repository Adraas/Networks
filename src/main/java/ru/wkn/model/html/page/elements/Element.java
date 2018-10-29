package ru.wkn.model.html.page.elements;

import ru.wkn.model.html.page.elements.attributes.Attribute;

import java.util.Objects;
import java.util.Set;

public class Element {

    private String tagName;
    private Set<Attribute> attributes;

    public Element(String tagName, Set<Attribute> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    public String getTagName() {
        return tagName;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public String getValueOfAttribute(String attributeName) {
        if (attributes == null || attributes.isEmpty()) {
            return "";
        }
        for (Attribute attributeOfImage : attributes) {
            if (attributeName.equals(attributeOfImage.getAttributeName())) {
                return attributeOfImage.getAttributeValue();
            }
        }
        return "";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Element element = (Element) o;
        return Objects.equals(tagName, element.tagName) &&
                Objects.equals(attributes, element.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tagName, attributes);
    }

    @Override
    public String toString() {
        return "Element{" +
                "tagName='" + tagName + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
