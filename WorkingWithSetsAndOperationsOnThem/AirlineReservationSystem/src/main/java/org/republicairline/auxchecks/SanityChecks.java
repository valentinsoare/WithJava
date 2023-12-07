package org.republicairline.auxchecks;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class SanityChecks {
    public static String checkHeaderMessage(String message, boolean isForMenu) {
        StringBuilder processedMessage = new StringBuilder();

        if (message.isBlank()) {
            processedMessage.append("none");
            return processedMessage.toString();
        }

        if (isForMenu) {
            Arrays.stream(message.split(" +"))
                    .map(s -> s.trim().toUpperCase())
                    .forEach(e -> processedMessage.append(e).append(" "));
        } else {
            Arrays.stream(message.split(" +"))
                    .map(s -> StringUtils.capitalize(s.trim()))
                    .forEach(e -> processedMessage.append(e).append(" "));
        }

        return processedMessage.toString();
    }

    public static int checkEmptySpacesForHeader(int emptySpaces) {
        return (emptySpaces < 0) ? 2 : emptySpaces;
    }
}
