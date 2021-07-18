package com.mobiquity.service.impl;

import com.mobiquity.packer.model.PackData;
import com.mobiquity.packer.model.PackItem;
import com.mobiquity.service.PackerService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Dynamic algorithm approach of Packer
 *
 * @author mahmood
 * @since 7/15/21
 */
class DPPackerService implements PackerService {

    /**
     * This method solve Package problem using dynamic programming.
     * <p/>
     * Before solving problem, we first sort items based on their weight in ascending order.
     * The reason is that we want to prioritize items with less weight but equal cost in our solution.
     * By sorting, the order of answers is changed which we solve that by soring final solution,
     * but if there would be items with same cost but different weights, we consider item with less weight first.
     *
     * @param packData List of problems
     * @return List of selected items (optimized solution)
     */
    @Override
    public List<Integer> solve(PackData packData) {
        sortItemsBasedOnWeight(packData);
        List<Integer> singleSolution = singleSolve(packData);
        Collections.sort(singleSolution);
        return singleSolution;
    }

    private void sortItemsBasedOnWeight(PackData packData) {
        packData.getPackItems().sort(Comparator.comparingInt(PackItem::getWeight));
    }

    /**
     * solve packer problem by DP approach.
     *
     * @param packData pack data to be solved
     * @return optimum item indexes
     * @see <a href="https://www.javatpoint.com/0-1-knapsack-problem">https://www.javatpoint.com/0-1-knapsack-problem</a>
     */
    private List<Integer> singleSolve(PackData packData) {
        int i, w;
        int size = packData.getPackItems().size();

        /* We keep the solution of maximum cost in this array.
         * So the solution[i][j] means max achievable cost if we have j limit for pack weight and we have i items.
         */
        int[][] solution = new int[size + 1][packData.getTotalWeight() + 1];

        for (i = 0; i <= size; i++) {
            for (w = 0; w <= packData.getTotalWeight(); w++) {
                if (i == 0 || w == 0) {
                    /*
                     * Max cost is Zero when the weight or the number of items are 0
                     */
                    solution[i][w] = 0;
                } else if (packData.getPackItems().get(i - 1).getWeight() <= w) {//if weight of item is bigger than pack limit weight, we cannot consider than for solution
                    /*
                     * if we want to calculate max achievable cost with the first i-th items, that means we have to calculate
                     * cost of i-th item plus max achievable cost of (i-1)-th items with weight limit of (w-weight of i-th item)
                     * so the cost would be cost of i-th item plus the max cost of (i-1)-th item with the weight limit (w - weight of i-th item).
                     *
                     * So, if the calculated cost is greater than max cost of (i-1)-th item and weight limit w, we have to
                     * consider new item for the solution
                     */
                    solution[i][w] = Math.max(
                            packData.getPackItems().get(i - 1).getCost() + solution[i - 1][w - packData.getPackItems().get(i - 1).getWeight()],
                            solution[i - 1][w]);
                } else {
                    //In this condition, the max cost is the same as max cost of (i-1)-th item
                    solution[i][w] = solution[i - 1][w];
                }
            }
        }

        return findItems(packData, size, solution);
    }

    private List<Integer> findItems(PackData packData, int size, int[][] solution) {

        List<Integer> optimumItems = new ArrayList<>();

        int cost = solution[size][packData.getTotalWeight()];
        int weight = packData.getTotalWeight();

        for (int i = size; i > 0 && cost > 0; i--) {
            if (cost != solution[i - 1][weight]) {
                /*
                 * if max cost of i-th items is equal to max cost of (i-1)-th items, it means current item is not included
                 * otherwise it is included
                 */

                optimumItems.add(packData.getPackItems().get(i - 1).getIndex());

                // Since this item is included, we deduct it's cost and weight from cost and weight variables
                cost = cost - packData.getPackItems().get(i - 1).getCost();
                weight = weight - packData.getPackItems().get(i - 1).getWeight();
            }
        }

        return optimumItems;
    }


}
