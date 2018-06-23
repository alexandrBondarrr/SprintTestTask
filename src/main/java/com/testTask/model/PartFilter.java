package com.testTask.model;

import java.util.Arrays;
import java.util.List;

public enum PartFilter {
    ALL,
    ONLY_REQUIRED,
    ONLY_NOT_REQUIRED;

    public String label() {
        switch(this) {
            case ALL:
                return "Все";
            case ONLY_REQUIRED:
                return "Только необходимые";
            case ONLY_NOT_REQUIRED:
                return "Только не необходимые";
        }
        return null;
    }

    public static List<PartFilter> all() {
        return Arrays.asList(ALL, ONLY_REQUIRED, ONLY_NOT_REQUIRED);
    }
}
