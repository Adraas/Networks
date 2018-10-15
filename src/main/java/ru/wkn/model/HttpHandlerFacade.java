package ru.wkn.model;

import ru.wkn.model.connection.Connector;
import ru.wkn.model.connection.HttpManager;
import ru.wkn.model.htmlpage.HtmlPage;
import ru.wkn.model.httppagehandler.HtmlPageHandler;

public class HttpHandlerFacade {

    private HttpManager httpManager;
    private Connector connector;
    private HtmlPage htmlPage;
    private HtmlPageHandler htmlPageHandler;
}
