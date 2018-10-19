package ru.wkn.model.html.page;

import ru.wkn.model.html.page.elements.Element;

import java.util.List;

public class Page {

    private List<Element> elements;

    public Page(List<Element> elements) {
        this.elements = elements;
    }

    public List<Element> getElements() {
        return elements;
    }
}
