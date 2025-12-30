package com.mylld.amazon.locker.utils;

import com.mylld.amazon.locker.request.PackageDetails;
import static com.mylld.amazon.locker.Size.of;

public final class AmazonLockerUtils {

    public static void validateDepositRequest(PackageDetails packageDetails) {

        if (null == packageDetails || null == packageDetails.getPackageSize() || null == of(packageDetails.getPackageSize())) {

            throw new RuntimeException("Invalid package details.");
        }
    }
}
