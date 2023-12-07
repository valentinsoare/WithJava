package org.republicairline.reservationsystemcomponents;

import org.republicairline.auxchecks.SanityChecks;

public abstract class Header {
    private String headerMessage;
    private String subHeaderMessage;

    private final Integer emptySpacesFromLeftEdgeScreen;
    private final Integer emptySpacesFromTopEdgeScreen;
    private final Integer emptySpacesBelowTheHeader;

    private final boolean isForMenu;
    private final boolean isForSubMenu;

    public Header(String headerMessage, String subHeaderMessage, Integer emptySpacesFromLeftEdgeScreen,
           Integer emptySpacesFromTopEdgeScreen, Integer emptySpacesBelowTheHeader, boolean isForMenu, boolean isForSubMenu) {
        this.isForMenu = isForMenu;
        this.isForSubMenu = !isForMenu && isForSubMenu;

        this.emptySpacesFromLeftEdgeScreen = SanityChecks.checkEmptySpacesForHeader(emptySpacesFromLeftEdgeScreen);
        this.emptySpacesFromTopEdgeScreen = SanityChecks.checkEmptySpacesForHeader(emptySpacesFromTopEdgeScreen);
        this.emptySpacesBelowTheHeader = SanityChecks.checkEmptySpacesForHeader(emptySpacesBelowTheHeader);

        this.headerMessage = setHeaderMessage(headerMessage, isForMenu,  true);
        this.subHeaderMessage = setSubHeaderMessage(subHeaderMessage, isForSubMenu, true);
    }

    public String getHeaderMessage() {
        return headerMessage;
    }

    public String getSubHeaderMessage() {
        return subHeaderMessage;
    }

    public Integer getEmptySpacesFromLeftEdgeScreen() {
        return emptySpacesFromLeftEdgeScreen;
    }

    public Integer getEmptySpacesFromTopEdgeScreen() {
        return emptySpacesFromTopEdgeScreen;
    }

    public Integer getEmptySpacesBelowTheHeader() {
        return emptySpacesBelowTheHeader;
    }

    public String setHeaderMessage(String headerMessage, boolean isForMenu, boolean forConstructor) {
        String processedHeaderMessage = SanityChecks.checkHeaderMessage(headerMessage, isForMenu);

        return (!forConstructor) ? this.headerMessage = processedHeaderMessage : processedHeaderMessage;
    }

    public String setSubHeaderMessage(String subHeaderMessage, boolean isForSubMenu, boolean forConstructor) {
        String processedSubHeaderMessage = SanityChecks.checkHeaderMessage(subHeaderMessage, isForSubMenu);

        return (!forConstructor) ? this.subHeaderMessage = processedSubHeaderMessage : processedSubHeaderMessage;
    }

        public boolean isForMenu() {
        return isForMenu;
    }

    public boolean isForSubMenu() {
        return isForSubMenu;
    }

    protected abstract String generateHeader(int numberOfLinesOfHeaderWithoutUpperBorders, int howManyWhiteSpacesBetweenBorderAndText,
                                             char upperBorderChar, char leftRightBorderChar);
}
