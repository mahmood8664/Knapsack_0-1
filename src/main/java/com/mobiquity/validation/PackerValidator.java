package com.mobiquity.validation;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.model.PackData;
import com.mobiquity.packer.model.PackItem;

import java.util.List;

/**
 * Validator of {@link PackData}
 * @author mahmood
 * @since 7/14/21
 */
public class PackerValidator {

    private final static int MAX_WEIGHT = 10000; //we multiply all weights to 100 to make the integer
    private final static int MAX_ITEM = 15;
    private final static int MAX_ITEM_WEIGHT = 10000; //we multiply all weights to 100 to make the integer
    private final static int MAX_ITEM_COST = 100;

    private PackerValidator() {
    }

    /**
     * Validate PackData and throw {@link APIException} is there is an error
     * Items to check:
     * <lo>
     *     <li>total weight</li>
     *     <li>Items count</li>
     *     <li>Items weight</li>
     *     <li>Items Cost</li>
     * </lo>
     * @param packDataList given packData list
     * @throws APIException if there is error in packData
     */
    public static void validate(List<PackData> packDataList) throws APIException {
        for (int i = 0; i < packDataList.size(); i++) {
            PackData packData = packDataList.get(i);
            checkWeight(packData, i + 1);
            checkItemCount(packData, i + 1);
            checkItemWeight(packData, i + 1);
            checkItemCost(packData, i + 1);
        }
    }

    private static void checkWeight(PackData packData, int line) throws APIException {
        if (packData.getTotalWeight() > MAX_WEIGHT) {
            throw new APIException(String.format("Total weight of pack must not exceed 100, line -> %d", line));
        }
    }

    private static void checkItemCount(PackData packData, int line) throws APIException {
        if (packData.getPackItems().size() > MAX_ITEM) {
            throw new APIException(String.format("Total item number of a pack must not exceed 15, line -> %d", line));
        }
    }

    private static void checkItemWeight(PackData packData, int line) throws APIException {
        List<PackItem> packItems = packData.getPackItems();
        for (int i = 0; i < packItems.size(); i++) {
            PackItem packItem = packItems.get(i);
            if (packItem.getWeight() > MAX_ITEM_WEIGHT) {
                throw new APIException(String.format("Item weight must not exceed 100, Item number -> %d, line -> %d", i + 1, line));
            }
        }
    }

    private static void checkItemCost(PackData packData, int line) throws APIException {
        List<PackItem> packItems = packData.getPackItems();
        for (int i = 0; i < packItems.size(); i++) {
            PackItem packItem = packItems.get(i);
            if (packItem.getCost() > MAX_ITEM_COST) {
                throw new APIException(String.format("Item cost must not exceed 100, Item number -> %d, line -> %d", i + 1, line));
            }
        }
    }
}
