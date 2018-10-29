package ru.wkn.model.html.page;

import ru.wkn.model.html.page.elements.Element;

import java.util.ArrayList;
import java.util.List;

public class Page {

    private List<Element> elements;

    public Page(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getElements() {
        return elements;
    }

    public List<Element> getElements(String tagName) {
        List<Element> elementsByTagName = new ArrayList<>();
        for (Element element : elements) {
            if (element.getTagName().equals(tagName)) {
                elementsByTagName.add(element);
            }
        }
        return elementsByTagName;
    }
}
