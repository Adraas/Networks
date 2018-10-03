package ru.wkn.model.htmlpage.htmlelements.image;

import ru.wkn.model.htmlpage.htmlelements.HtmlElement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class HtmlImage extends HtmlElement {

    private List<HtmlAttributeImage> htmlAttributesOfImage;

    public HtmlImage(Map<String, String> data) {
        htmlAttributesOfImage = new ArrayList<>();
        attributesInit(data);
    }

    private void attributesInit(Map<String, String> data) {
        Iterator iterator = data.keySet().iterator();
        for (; iterator.hasNext(); iterator.next()) {
            //
        }
    }

    public List<HtmlAttributeImage> getHtmlAttributesOfImage() {
        return htmlAttributesOfImage;
    }

    public String getValueOfAttribute(String attributeName) {
        for (HtmlAttributeImage htmlAttributeImage : htmlAttributesOfImage) {
            if (attributeName.equals(htmlAttributeImage.getAttributeName())) {
                return htmlAttributeImage.getAttributeValue();
            }
        }
        return null;
    }
}
