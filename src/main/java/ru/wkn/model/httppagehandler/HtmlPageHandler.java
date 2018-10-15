package ru.wkn.model.httppagehandler;

import ru.wkn.model.htmlpage.htmlelements.image.HtmlImage;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public List<HtmlImage> imagesFromTheSameSite(List<HtmlImage> htmlImages, String protocolAndHostname) {
        List<HtmlImage> imagesFromTheSameSite = new ArrayList<>();
        for (HtmlImage currentImage : htmlImages) {
            String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
            if (currentHrefToLowerCase.startsWith(protocolAndHostname.toLowerCase())) {
                imagesFromTheSameSite.add(currentImage);
            }
        }
        return imagesFromTheSameSite;
    }

    public List<HtmlImage> imagesFromTheOtherSite(List<HtmlImage> htmlImages, String protocolAndHostname) {
        List<HtmlImage> imagesFromTheOtherSite = new ArrayList<>();
        for (HtmlImage currentImage : htmlImages) {
            String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
            if (!currentHrefToLowerCase.startsWith(protocolAndHostname.toLowerCase())) {
                imagesFromTheOtherSite.add(currentImage);
            }
        }
        return imagesFromTheOtherSite;
    }
}
