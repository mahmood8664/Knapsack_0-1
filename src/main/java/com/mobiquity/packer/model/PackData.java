package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data model for holding a packer problem
 * Also see {@link PackItem}
 * @author mahmood
 * @since 7/14/21
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PackData {
    private int totalWeight;
    private List<PackItem> packItems;
}
