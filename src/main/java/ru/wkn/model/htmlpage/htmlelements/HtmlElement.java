package ru.wkn.model.htmlpage.htmlelements;

import java.util.Set;

public abstract class HtmlElement {

    private Set<HtmlAttribute> htmlAttributes;

    public HtmlElement(Set<HtmlAttribute> htmlAttributes) {
        this.htmlAttributes = htmlAttributes;
    }

    public Set<HtmlAttribute> getHtmlAttributes() {
        return htmlAttributes;
    }
}
