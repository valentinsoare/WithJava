package org.republicairline.login;

import org.republicairline.reservationsystemcomponents.Header;

public class HeaderForLoginScreen extends Header {

    public HeaderForLoginScreen(String message, String subMessage, Integer emptySpacesFromLeftEdgeScreen,
                                Integer emptySpacesFromTopEdgeScreen, Integer emptySpacesBelowTheHeader) {

        super(message, subMessage, emptySpacesFromLeftEdgeScreen,
                emptySpacesFromTopEdgeScreen, emptySpacesBelowTheHeader, true, false);
    }

    @Override
    public String generateHeader(int numberOfLinesOfHeaderWithoutUpperBorders, int howManyWhiteSpacesBetweenBorderAndText,
                                 char upperBorderChar, char leftRightBorderChar) {
        StringBuilder generatedHeader = new StringBuilder();

        generatedHeader.append("\n".repeat(getEmptySpacesFromTopEdgeScreen()));
        int lengthOfBorders = (getSubHeaderMessage().length() >= getHeaderMessage().length()) ? (getSubHeaderMessage().length() * 2)
                : (getHeaderMessage().length() * 2);
        int betweenBorderAndText = (lengthOfBorders / 2) - (getHeaderMessage().length() / 2);

        String border = String.format("%s%s", " ".repeat(getEmptySpacesFromLeftEdgeScreen()), String.valueOf(upperBorderChar).repeat(lengthOfBorders + 2));

        generatedHeader.append(border).append("\n");

        int qtyParseOperations = 0, countEmptySpacesInsideBorders = 0;

        while (qtyParseOperations < numberOfLinesOfHeaderWithoutUpperBorders) {
            if (countEmptySpacesInsideBorders < howManyWhiteSpacesBetweenBorderAndText) {
                generatedHeader.append(" ".repeat(getEmptySpacesFromLeftEdgeScreen())).append(leftRightBorderChar).
                        append(" ".repeat(lengthOfBorders)).append(leftRightBorderChar).append("\n");
                countEmptySpacesInsideBorders++;
            } else {
                generatedHeader.append(" ".repeat(getEmptySpacesFromLeftEdgeScreen())).append(leftRightBorderChar).
                        append(" ".repeat(betweenBorderAndText)).append(getHeaderMessage()).append(" ".repeat(betweenBorderAndText)).append(leftRightBorderChar).append("\n");
                countEmptySpacesInsideBorders = 0;
            }

            qtyParseOperations++;
        }

        generatedHeader.append(border).append("\n");

        generatedHeader.append(" ".repeat(getEmptySpacesFromLeftEdgeScreen() + ((lengthOfBorders / 2) - (getSubHeaderMessage().length() / 2)))).
                append(getSubHeaderMessage())
                .append("\n".repeat(getEmptySpacesBelowTheHeader()));

        return generatedHeader.toString();
    }
}
