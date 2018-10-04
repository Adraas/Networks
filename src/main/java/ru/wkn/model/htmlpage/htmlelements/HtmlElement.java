package ru.wkn.model.htmlpage.htmlelements;

import java.util.List;

public abstract class HtmlElement {

    private List<HtmlAttribute> htmlAttributes;

    public HtmlElement(List<HtmlAttribute> htmlAttributes) {
        this.htmlAttributes = htmlAttributes;
    }

    public List<HtmlAttribute> getHtmlAttributes() {
        return htmlAttributes;
    }
}
