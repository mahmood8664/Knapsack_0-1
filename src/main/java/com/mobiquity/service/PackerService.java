package com.mobiquity.service;

import com.mobiquity.packer.model.PackData;

import java.util.List;

/**
 * Package solver service
 *
 * @author mahmood
 * @since 7/15/21
 */
public interface PackerService {

    /**
     * Solves package problem and return selected items.
     * <br/>
     * <H2>Return empty list if there is no solution, never returns null</H2>
     *
     * @param packData packer problem to solve
     * @return {@link List<Integer>} consist of all solutions
     */
    List<Integer> solve(PackData packData);

}
