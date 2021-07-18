package com.mobiqiuty.parser;

import com.mobiquity.exception.APIException;
import com.mobiquity.packer.model.PackData;
import com.mobiquity.parser.FileParser;
import com.mobiquity.validation.PackerValidator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

/**
 * @author mahmood
 * @since 7/14/21
 */
public class FileParserTest {

    @Test
    void parseTest_OK() throws APIException {
        List<PackData> packData = FileParser.parse(Objects.requireNonNull(this.getClass().getResource("/example_input")).getPath());

        /*
         * 81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
         * 8 : (1,15.3,€34)
         * 75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
         * 56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
         */

        //4 lines, 4 PackData
        Assertions.assertThat(packData.size()).isEqualTo(4);
        //check total weights
        checkTotalWeights(packData);
        //check item size of each PackData
        checkItemSized(packData);

        //Item 1 details check
        /*
         * 81 : (1,53.38,€45) (2,88.62,€98) (3,78.48,€3) (4,72.30,€76) (5,30.18,€9) (6,46.34,€48)
         */
        checkItem1(packData);

        //Item 2 details check
        /*
         * 8 : (1,15.3,€34)
         */
        checkItem2(packData);

        //Item 3 details check
        /*
         * 75 : (1,85.31,€29) (2,14.55,€74) (3,3.98,€16) (4,26.24,€55) (5,63.69,€52) (6,76.25,€75) (7,60.02,€74) (8,93.18,€35) (9,89.95,€78)
         */
        checkItem3(packData);

        //Item 4 details check
        /*
         * 56 : (1,90.72,€13) (2,33.80,€40) (3,43.15,€10) (4,37.97,€16) (5,46.81,€36) (6,48.77,€79) (7,81.80,€45) (8,19.36,€79) (9,6.76,€64)
         */
        checkItem4(packData);

    }

    private void checkItem4(List<PackData> packData) {
        Assertions.assertThat(packData.get(3).getPackItems().get(0).getIndex()).isEqualTo(1);
        Assertions.assertThat(packData.get(3).getPackItems().get(0).getWeight()).isEqualTo(9072);
        Assertions.assertThat(packData.get(3).getPackItems().get(0).getCost()).isEqualTo(13);

        Assertions.assertThat(packData.get(3).getPackItems().get(1).getIndex()).isEqualTo(2);
        Assertions.assertThat(packData.get(3).getPackItems().get(1).getWeight()).isEqualTo(3380);
        Assertions.assertThat(packData.get(3).getPackItems().get(1).getCost()).isEqualTo(40);

        Assertions.assertThat(packData.get(3).getPackItems().get(2).getIndex()).isEqualTo(3);
        Assertions.assertThat(packData.get(3).getPackItems().get(2).getWeight()).isEqualTo(4315);
        Assertions.assertThat(packData.get(3).getPackItems().get(2).getCost()).isEqualTo(10);

        Assertions.assertThat(packData.get(3).getPackItems().get(3).getIndex()).isEqualTo(4);
        Assertions.assertThat(packData.get(3).getPackItems().get(3).getWeight()).isEqualTo(3797);
        Assertions.assertThat(packData.get(3).getPackItems().get(3).getCost()).isEqualTo(16);

        Assertions.assertThat(packData.get(3).getPackItems().get(4).getIndex()).isEqualTo(5);
        Assertions.assertThat(packData.get(3).getPackItems().get(4).getWeight()).isEqualTo(4681);
        Assertions.assertThat(packData.get(3).getPackItems().get(4).getCost()).isEqualTo(36);

        Assertions.assertThat(packData.get(3).getPackItems().get(5).getIndex()).isEqualTo(6);
        Assertions.assertThat(packData.get(3).getPackItems().get(5).getWeight()).isEqualTo(4877);
        Assertions.assertThat(packData.get(3).getPackItems().get(5).getCost()).isEqualTo(79);

        Assertions.assertThat(packData.get(3).getPackItems().get(6).getIndex()).isEqualTo(7);
        Assertions.assertThat(packData.get(3).getPackItems().get(6).getWeight()).isEqualTo(8180);
        Assertions.assertThat(packData.get(3).getPackItems().get(6).getCost()).isEqualTo(45);

        Assertions.assertThat(packData.get(3).getPackItems().get(7).getIndex()).isEqualTo(8);
        Assertions.assertThat(packData.get(3).getPackItems().get(7).getWeight()).isEqualTo(1936);
        Assertions.assertThat(packData.get(3).getPackItems().get(7).getCost()).isEqualTo(79);

        Assertions.assertThat(packData.get(3).getPackItems().get(8).getIndex()).isEqualTo(9);
        Assertions.assertThat(packData.get(3).getPackItems().get(8).getWeight()).isEqualTo(676);
        Assertions.assertThat(packData.get(3).getPackItems().get(8).getCost()).isEqualTo(64);
    }

    private void checkItem3(List<PackData> packData) {
        Assertions.assertThat(packData.get(2).getPackItems().get(0).getIndex()).isEqualTo(1);
        Assertions.assertThat(packData.get(2).getPackItems().get(0).getWeight()).isEqualTo(8531);
        Assertions.assertThat(packData.get(2).getPackItems().get(0).getCost()).isEqualTo(29);

        Assertions.assertThat(packData.get(2).getPackItems().get(1).getIndex()).isEqualTo(2);
        Assertions.assertThat(packData.get(2).getPackItems().get(1).getWeight()).isEqualTo(1455);
        Assertions.assertThat(packData.get(2).getPackItems().get(1).getCost()).isEqualTo(74);

        Assertions.assertThat(packData.get(2).getPackItems().get(2).getIndex()).isEqualTo(3);
        Assertions.assertThat(packData.get(2).getPackItems().get(2).getWeight()).isEqualTo(398);
        Assertions.assertThat(packData.get(2).getPackItems().get(2).getCost()).isEqualTo(16);

        Assertions.assertThat(packData.get(2).getPackItems().get(3).getIndex()).isEqualTo(4);
        Assertions.assertThat(packData.get(2).getPackItems().get(3).getWeight()).isEqualTo(2624);
        Assertions.assertThat(packData.get(2).getPackItems().get(3).getCost()).isEqualTo(55);

        Assertions.assertThat(packData.get(2).getPackItems().get(4).getIndex()).isEqualTo(5);
        Assertions.assertThat(packData.get(2).getPackItems().get(4).getWeight()).isEqualTo(6369);
        Assertions.assertThat(packData.get(2).getPackItems().get(4).getCost()).isEqualTo(52);

        Assertions.assertThat(packData.get(2).getPackItems().get(5).getIndex()).isEqualTo(6);
        Assertions.assertThat(packData.get(2).getPackItems().get(5).getWeight()).isEqualTo(7625);
        Assertions.assertThat(packData.get(2).getPackItems().get(5).getCost()).isEqualTo(75);

        Assertions.assertThat(packData.get(2).getPackItems().get(6).getIndex()).isEqualTo(7);
        Assertions.assertThat(packData.get(2).getPackItems().get(6).getWeight()).isEqualTo(6002);
        Assertions.assertThat(packData.get(2).getPackItems().get(6).getCost()).isEqualTo(74);

        Assertions.assertThat(packData.get(2).getPackItems().get(7).getIndex()).isEqualTo(8);
        Assertions.assertThat(packData.get(2).getPackItems().get(7).getWeight()).isEqualTo(9318);
        Assertions.assertThat(packData.get(2).getPackItems().get(7).getCost()).isEqualTo(35);

        Assertions.assertThat(packData.get(2).getPackItems().get(8).getIndex()).isEqualTo(9);
        Assertions.assertThat(packData.get(2).getPackItems().get(8).getWeight()).isEqualTo(8995);
        Assertions.assertThat(packData.get(2).getPackItems().get(8).getCost()).isEqualTo(78);
    }

    private void checkItem2(List<PackData> packData) {
        Assertions.assertThat(packData.get(1).getPackItems().get(0).getIndex()).isEqualTo(1);
        Assertions.assertThat(packData.get(1).getPackItems().get(0).getWeight()).isEqualTo(1530);
        Assertions.assertThat(packData.get(1).getPackItems().get(0).getCost()).isEqualTo(34);
    }

    private void checkItem1(List<PackData> packData) {
        Assertions.assertThat(packData.get(0).getPackItems().get(0).getIndex()).isEqualTo(1);
        Assertions.assertThat(packData.get(0).getPackItems().get(0).getWeight()).isEqualTo(5338);
        Assertions.assertThat(packData.get(0).getPackItems().get(0).getCost()).isEqualTo(45);

        Assertions.assertThat(packData.get(0).getPackItems().get(1).getIndex()).isEqualTo(2);
        Assertions.assertThat(packData.get(0).getPackItems().get(1).getWeight()).isEqualTo(8862);
        Assertions.assertThat(packData.get(0).getPackItems().get(1).getCost()).isEqualTo(98);

        Assertions.assertThat(packData.get(0).getPackItems().get(2).getIndex()).isEqualTo(3);
        Assertions.assertThat(packData.get(0).getPackItems().get(2).getWeight()).isEqualTo(7848);
        Assertions.assertThat(packData.get(0).getPackItems().get(2).getCost()).isEqualTo(3);

        Assertions.assertThat(packData.get(0).getPackItems().get(3).getIndex()).isEqualTo(4);
        Assertions.assertThat(packData.get(0).getPackItems().get(3).getWeight()).isEqualTo(7230);
        Assertions.assertThat(packData.get(0).getPackItems().get(3).getCost()).isEqualTo(76);

        Assertions.assertThat(packData.get(0).getPackItems().get(4).getIndex()).isEqualTo(5);
        Assertions.assertThat(packData.get(0).getPackItems().get(4).getWeight()).isEqualTo(3018);
        Assertions.assertThat(packData.get(0).getPackItems().get(4).getCost()).isEqualTo(9);

        Assertions.assertThat(packData.get(0).getPackItems().get(5).getIndex()).isEqualTo(6);
        Assertions.assertThat(packData.get(0).getPackItems().get(5).getWeight()).isEqualTo(4634);
        Assertions.assertThat(packData.get(0).getPackItems().get(5).getCost()).isEqualTo(48);
    }

    private void checkItemSized(List<PackData> packData) {
        Assertions.assertThat(packData.get(0).getPackItems().size()).isEqualTo(6);
        Assertions.assertThat(packData.get(1).getPackItems().size()).isEqualTo(1);
        Assertions.assertThat(packData.get(2).getPackItems().size()).isEqualTo(9);
        Assertions.assertThat(packData.get(3).getPackItems().size()).isEqualTo(9);
    }

    private void checkTotalWeights(List<PackData> packData) {
        Assertions.assertThat(packData.get(0).getTotalWeight()).isEqualTo(8100);
        Assertions.assertThat(packData.get(1).getTotalWeight()).isEqualTo(800);
        Assertions.assertThat(packData.get(2).getTotalWeight()).isEqualTo(7500);
        Assertions.assertThat(packData.get(3).getTotalWeight()).isEqualTo(5600);
    }


    @Test
    void parseTest_badFileContent() {

        Assertions.assertThatThrownBy(() -> PackerValidator.validate(
                FileParser.parse(Objects.requireNonNull(this.getClass().getResource("/example_input_invalid")).getPath()))
        )
                .isInstanceOf(APIException.class)
                .hasMessage("Error in parsing packs file");
    }

}
