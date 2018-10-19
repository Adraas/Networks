package ru.wkn.model.html.handlers;

import ru.wkn.model.html.page.elements.image.Image;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public List<Image> imagesFromTheSameSite(List<Image> htmlImages, String protocolAndHostname) {
        List<Image> imagesFromTheSameSite = new ArrayList<>();
        for (Image currentImage : htmlImages) {
            String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
            if (currentHrefToLowerCase.startsWith(protocolAndHostname.toLowerCase())) {
                imagesFromTheSameSite.add(currentImage);
            }
        }
        return imagesFromTheSameSite;
    }

    public List<Image> imagesFromTheOtherSite(List<Image> htmlImages, String protocolAndHostname) {
        List<Image> imagesFromTheOtherSite = new ArrayList<>();
        for (Image currentImage : htmlImages) {
            String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
            if (!currentHrefToLowerCase.startsWith(protocolAndHostname.toLowerCase())) {
                imagesFromTheOtherSite.add(currentImage);
            }
        }
        return imagesFromTheOtherSite;
    }
}
