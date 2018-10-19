package ru.wkn.model.html.page.elements;

import java.util.Set;

public abstract class Element {

    private Set<Attribute> attributes;

    public Element(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<Attribute> getAttributes() {
        return attributes;
    }
}
