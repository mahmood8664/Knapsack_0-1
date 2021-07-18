package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.parser.FileParser;
import com.mobiquity.service.PackerType;
import com.mobiquity.service.Stringifier;
import com.mobiquity.service.impl.PackerServiceProvider;
import com.mobiquity.validation.PackerValidator;

import java.util.List;
import java.util.stream.Collectors;

public class Packer {

    private Packer() {
    }

    public static String pack(String filePath) throws APIException {

        var packDataList = FileParser.parse(filePath);
        //validate input
        PackerValidator.validate(packDataList);
        //solve all pack problems
        List<List<Integer>> finalResults = packDataList
                .stream()
                .map(item -> PackerServiceProvider.getInstance(PackerType.DYNAMIC_APPROACH).solve(item))
                .collect(Collectors.toList());
        //print the results
        return Stringifier.stringify(finalResults);
    }
}
