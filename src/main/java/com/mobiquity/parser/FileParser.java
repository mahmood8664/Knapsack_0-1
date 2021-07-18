package com.mobiquity.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.model.PackData;
import com.mobiquity.packer.model.PackItem;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Pattern;

/**
 * This class is responsible for parsing pack data file, doing some validation about the format of data and finally converting
 * file data into desired data model
 *
 * @author mahmood
 * @since 7/14/21
 */
public class FileParser {

    private static final Pattern LINE_PATTERN = Pattern.compile("(?<weight>\\d+)\\s:(?<items>(\\s\\(\\d+,\\d+\\.\\d+,€\\d+\\))+)");
    private static final Pattern ITEM_PATTERN = Pattern.compile("(\\s\\((?<index>\\d+),(?<weight>\\d+\\.\\d+),€(?<cost>\\d+)\\))");

    private FileParser() {
    }

    /**
     * Takes file address, read lines of file and convert each line into {@link PackData}
     *
     * @param filePath address of file
     * @return {@link List<PackData>}
     * @throws APIException if there is problem in opening or processing the content of the file
     */
    public static List<PackData> parse(String filePath) throws APIException {
        if (filePath == null || filePath.isEmpty()) {
            throw new APIException("File path is empty");
        }

        try (var reader = new BufferedReader(new FileReader(URLDecoder.decode(filePath, StandardCharsets.UTF_8)))) {

            var lineNumber = new AtomicReference<>(0);
            var allPacksItems = new ArrayList<PackData>();

            var line = reader.readLine();
            while (line != null) {

                lineNumber.getAndSet(lineNumber.get() + 1);

                var matcher = LINE_PATTERN.matcher(line);
                if (matcher.find()) {
                    var packData = new PackData();
                    //We multiply all weight to 100 to eliminate fractions from number.
                    packData.setTotalWeight(Integer.parseInt(matcher.group("weight")) * 100);
                    packData.setPackItems(extractItems(matcher.group("items")));
                    allPacksItems.add(packData);
                } else {
                    throw new APIException(String.format("There is a problem in processing file line %d", lineNumber.get()));
                }
                line = reader.readLine();
            }
            return allPacksItems;
        } catch (FileNotFoundException e) {
            throw new APIException("File not found", e);
        } catch (IOException e) {
            throw new APIException("There is IO Exception error in opening file", e);
        } catch (Exception e) {
            throw new APIException("Error in parsing packs file", e);
        }
    }

    /**
     * extract all indexes, weights and costs from given string
     *
     * @param itemsStr given string
     * @return {@link List<PackItem>}
     */
    private static List<PackItem> extractItems(String itemsStr) {
        var packItemList = new ArrayList<PackItem>();

        var matcher = ITEM_PATTERN.matcher(itemsStr);
        while (matcher.find()) {
            packItemList.add(PackItem.builder()
                    .index(Integer.parseInt(matcher.group("index")))
                    .weight((int) (Float.parseFloat(matcher.group("weight")) * 100))
                    .cost(Integer.parseInt(matcher.group("cost")))
                    .build());
        }
        return packItemList;
    }

}
