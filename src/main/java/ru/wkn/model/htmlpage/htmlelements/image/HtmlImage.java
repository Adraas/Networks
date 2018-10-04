package ru.wkn.model.htmlpage.htmlelements.image;

import ru.wkn.model.htmlpage.htmlelements.HtmlAttribute;
import ru.wkn.model.htmlpage.htmlelements.HtmlElement;

import java.util.ArrayList;
import java.util.List;

public class HtmlImage extends HtmlElement {

    private List<HtmlAttribute> htmlAttributesOfImage;

    public HtmlImage(List<HtmlAttribute> htmlAttributes) {
        super(htmlAttributes);
        htmlAttributesOfImage = new ArrayList<>();
    }

    public List<HtmlAttribute> getHtmlAttributesOfImage() {
        return htmlAttributesOfImage;
    }

    public String getValueOfAttribute(String attributeName) {
        for (HtmlAttribute htmlAttributeOfImage : htmlAttributesOfImage) {
            if (attributeName.equals(htmlAttributeOfImage.getAttributeName())) {
                return htmlAttributeOfImage.getAttributeValue();
            }
        }
        return null;
    }
}
