package ru.wkn.model;

import ru.wkn.model.http.Connector;
import ru.wkn.model.http.RequestManager;
import ru.wkn.model.html.page.Page;
import ru.wkn.model.html.handlers.HtmlPageHandler;

public class HandlerFacade {

    private RequestManager requestManager;
    private Connector connector;
    private Page page;
    private HtmlPageHandler htmlPageHandler;
}
