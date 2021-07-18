package com.mobiqiuty.validation;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.model.PackData;
import com.mobiquity.packer.model.PackItem;
import com.mobiquity.validation.PackerValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author mahmood
 * @since 7/17/21
 */
public class PackerValidatorTest {

    @Test
    void validate_TotalWeightTest_ThrowAPIException() {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(10001)
                        .packItems(List.of(PackItem.builder()
                                .cost(10)
                                .weight(10)
                                .index(1)
                                .build())
                        ).build()
        );
        Assertions.assertThatThrownBy(() -> PackerValidator.validate(packDataList))
                .isInstanceOf(APIException.class)
                .hasMessage("Total weight of pack must not exceed 100, line -> 1");

    }

    @Test
    void validate_TooManyItemsTest_ThrowAPIException() {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(100)
                        .packItems(
                                IntStream.range(1, 17).boxed().map(operand -> PackItem.builder()
                                        .index(operand)
                                        .weight(10)
                                        .cost(20)
                                        .build()
                                ).collect(Collectors.toList())
                        ).build()
        );
        Assertions.assertThatThrownBy(() -> PackerValidator.validate(packDataList))
                .isInstanceOf(APIException.class)
                .hasMessage("Total item number of a pack must not exceed 15, line -> 1");

    }

    @Test
    void validate_ItemInvalidWeightTest_ThrowAPIException() {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(10000)
                        .packItems(
                                IntStream.range(1, 2).boxed().map(operand -> PackItem.builder()
                                        .index(operand)
                                        .weight(10001)
                                        .cost(20)
                                        .build()
                                ).collect(Collectors.toList())
                        ).build()
        );
        Assertions.assertThatThrownBy(() -> PackerValidator.validate(packDataList))
                .isInstanceOf(APIException.class)
                .hasMessage("Item weight must not exceed 100, Item number -> 1, line -> 1");

    }

    @Test
    void validate_ItemBoundaryWeightTest_OK() throws APIException {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(10000)
                        .packItems(
                                IntStream.range(1, 2).boxed().map(operand -> PackItem.builder()
                                        .index(operand)
                                        .weight(10000)
                                        .cost(20)
                                        .build()
                                ).collect(Collectors.toList())
                        ).build()
        );
        PackerValidator.validate(packDataList);
    }


    @Test
    void validate_ItemInvalidCostTest_ThrowAPIException() {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(10000)
                        .packItems(
                                IntStream.range(1, 2).boxed().map(operand -> PackItem.builder()
                                        .index(operand)
                                        .weight(10000)
                                        .cost(10001)
                                        .build()
                                ).collect(Collectors.toList())
                        ).build()
        );
        Assertions.assertThatThrownBy(() -> PackerValidator.validate(packDataList))
                .isInstanceOf(APIException.class)
                .hasMessage("Item cost must not exceed 100, Item number -> 1, line -> 1");

    }


    @Test
    void validate_BoundaryTest_OK() throws APIException {
        var packDataList = List.of(
                PackData.builder()
                        .totalWeight(10000)
                        .packItems(List.of(PackItem.builder()
                                .cost(100)
                                .weight(10000)
                                .index(1)
                                .build())
                        ).build()
        );
        PackerValidator.validate(packDataList);
    }


}
