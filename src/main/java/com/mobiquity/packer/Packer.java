package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.parser.FileParser;
import com.mobiquity.service.PackerType;
import com.mobiquity.service.Stringifier;
import com.mobiquity.service.impl.PackerServiceProvider;
import com.mobiquity.validation.PackerValidator;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class is responsible for solving packaging problem
 */
public class Packer {

    private Packer() {
    }

    /**
     * Solve package problems defined in the filePath file and return solutions.
     * @param filePath path to file
     * @return String contains solution, separated by new line
     * @throws APIException if file not found, or is not in correct format or data in file is not valid
     */
    public static String pack(String filePath) throws APIException {

        var packDataList = FileParser.parse(filePath);
        PackerValidator.validate(packDataList);

        List<List<Integer>> finalResults = packDataList
                .stream()
                .map(item -> PackerServiceProvider.getInstance(PackerType.DYNAMIC_APPROACH).solve(item))
                .collect(Collectors.toList());

        return Stringifier.stringify(finalResults);
    }
}
