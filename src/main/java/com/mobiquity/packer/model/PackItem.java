package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data model for holding pack item
 * Also see {@link PackData}
 * @author mahmood
 * @since 7/14/21
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PackItem {
    private int index;
    private int weight;
    private int cost;
}
