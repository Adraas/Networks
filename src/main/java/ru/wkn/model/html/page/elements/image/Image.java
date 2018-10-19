package ru.wkn.model.html.page.elements.image;

import ru.wkn.model.html.page.elements.Attribute;
import ru.wkn.model.html.page.elements.Element;

import java.util.HashSet;
import java.util.Set;

public class Image extends Element {

    private Set<Attribute> attributesOfImage;

    public Image(Set<Attribute> attributes) {
        super(attributes);
        attributesOfImage = new HashSet<>();
    }

    public Set<Attribute> getAttributesOfImage() {
        return attributesOfImage;
    }

    public String getValueOfAttribute(String attributeName) {
        if (attributesOfImage == null || attributesOfImage.isEmpty()) {
            return "";
        }
        for (Attribute attributeOfImage : attributesOfImage) {
            if (attributeName.equals(attributeOfImage.getAttributeName())) {
                return attributeOfImage.getAttributeValue();
            }
        }
        return "";
    }
}
