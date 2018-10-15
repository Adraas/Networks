package ru.wkn.model.htmlpage;

import ru.wkn.model.htmlpage.htmlelements.HtmlElement;

import java.util.List;

public class HtmlPage {

    private List<HtmlElement> htmlElements;

    public HtmlPage(List<HtmlElement> htmlElements) {
        this.htmlElements = htmlElements;
    }

    public List<HtmlElement> getHtmlElements() {
        return htmlElements;
    }
}
