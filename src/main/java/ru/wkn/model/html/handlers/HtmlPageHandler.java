package ru.wkn.model.html.handlers;

import ru.wkn.model.html.page.elements.image.Image;

import java.util.ArrayList;
import java.util.List;

public class HtmlPageHandler {

    public List<Image> imagesFromTheSameSite(List<Image> htmlImages, String uriAddress) {
        if (htmlImages != null) {
            List<Image> imagesFromTheSameSite = new ArrayList<>();
            for (Image currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (currentHrefToLowerCase.startsWith(uriAddress.toLowerCase())) {
                    imagesFromTheSameSite.add(currentImage);
                }
            }
            return imagesFromTheSameSite;
        }
        return null;
    }

    public List<Image> imagesFromTheOtherSite(List<Image> htmlImages, String uriAddress) {
        if (htmlImages != null) {
            List<Image> imagesFromTheOtherSite = new ArrayList<>();
            for (Image currentImage : htmlImages) {
                String currentHrefToLowerCase = currentImage.getValueOfAttribute("src").toLowerCase();
                if (!currentHrefToLowerCase.startsWith(uriAddress.toLowerCase())) {
                    imagesFromTheOtherSite.add(currentImage);
                }
            }
            return imagesFromTheOtherSite;
        }
        return null;
    }
}
