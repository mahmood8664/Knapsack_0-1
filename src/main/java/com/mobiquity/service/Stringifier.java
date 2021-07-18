package com.mobiquity.service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mahmood
 * @since 7/18/21
 */
public class Stringifier {

    /**
     * convert results of packer service into desired String
     *
     * @param toPrint list to convert into string
     * @return string with desired format
     */
    public static String stringify(List<List<Integer>> toPrint) {
        return toPrint.stream()
                .map(items -> items.size() == 0 ? "-" : items.stream().map(Object::toString).collect(Collectors.joining(",")))
                .collect(Collectors.joining("\n"));
    }

}
