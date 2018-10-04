package ru.wkn.model.htmlpage;

import ru.wkn.model.htmlpage.htmlelements.HtmlElements;

public class HtmlPage {

    private HtmlElements htmlElements;

    public HtmlPage(HtmlElements htmlElements) {
        this.htmlElements = htmlElements;
    }

    public HtmlElements getHtmlElements() {
        return htmlElements;
    }

    public void setHtmlElements(HtmlElements htmlElements) {
        this.htmlElements = htmlElements;
    }
}
