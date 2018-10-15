package ru.wkn.model.htmlpage.htmlelements.image;

import ru.wkn.model.htmlpage.htmlelements.HtmlAttribute;
import ru.wkn.model.htmlpage.htmlelements.HtmlElement;

import java.util.HashSet;
import java.util.Set;

public class HtmlImage extends HtmlElement {

    private Set<HtmlAttribute> htmlAttributesOfImage;

    public HtmlImage(Set<HtmlAttribute> htmlAttributes) {
        super(htmlAttributes);
        htmlAttributesOfImage = new HashSet<>();
    }

    public Set<HtmlAttribute> getHtmlAttributesOfImage() {
        return htmlAttributesOfImage;
    }

    public String getValueOfAttribute(String attributeName) {
        if (htmlAttributesOfImage == null || htmlAttributesOfImage.isEmpty()) {
            return "";
        }
        for (HtmlAttribute htmlAttributeOfImage : htmlAttributesOfImage) {
            if (attributeName.equals(htmlAttributeOfImage.getAttributeName())) {
                return htmlAttributeOfImage.getAttributeValue();
            }
        }
        return "";
    }
}
