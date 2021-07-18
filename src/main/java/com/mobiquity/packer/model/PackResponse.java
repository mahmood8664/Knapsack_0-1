package com.mobiquity.packer.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author mahmood
 * @since 7/16/21
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PackResponse {
    private List<List<Integer>> results;

    /**
     * Returns String contains all solutions in an appropriate format
     *
     * @return solution String
     */
    public String printAll() {
        return results.stream().map(items -> items.isEmpty() ? "-" :
                items.stream().
                        map(Object::toString).
                        collect(Collectors.joining(","))
        ).collect(Collectors.joining("\n"));
    }
}
