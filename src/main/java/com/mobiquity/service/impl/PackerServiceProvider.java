package com.mobiquity.service.impl;

import com.mobiquity.service.PackerService;
import com.mobiquity.service.PackerType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author mahmood
 * @since 7/15/21
 */
public class PackerServiceProvider {

    private static final Map<PackerType, PackerService> services = new HashMap<>();

    public static PackerService getInstance(PackerType packerType) {
        //noinspection SwitchStatementWithTooFewBranches
        switch (packerType) {
            case DYNAMIC_APPROACH:
                return getDynamicInstance();
            default:
                throw new UnsupportedOperationException("This operation is not supported");
        }
    }

    private static PackerService getDynamicInstance() {
        if (services.get(PackerType.DYNAMIC_APPROACH) == null) {
            synchronized (DPPackerService.class) {
                if (services.get(PackerType.DYNAMIC_APPROACH) == null) {
                    services.put(PackerType.DYNAMIC_APPROACH, new DPPackerService());
                }
            }
        }
        return services.get(PackerType.DYNAMIC_APPROACH);
    }

}
