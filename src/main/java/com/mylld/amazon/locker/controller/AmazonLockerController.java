package com.mylld.amazon.locker.controller;

import com.mylld.amazon.locker.DepositOutput;
import com.mylld.amazon.locker.Locker;
import com.mylld.amazon.locker.PackageDetails;
import com.mylld.amazon.locker.Size;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static com.mylld.amazon.locker.utils.AmazonLockerUtils.validateDepositRequest;

@RestController
@RequestMapping("/amazon-locker")
public class AmazonLockerController {

    @Autowired
    private Locker locker;

    @PostMapping("/deposit-package")
    public @ResponseBody DepositOutput depositPackage(@RequestBody PackageDetails packageDetails) {

        validateDepositRequest(packageDetails);
        return locker.depositPackage(Size.of(packageDetails.getPackageSize().toUpperCase()));
    }

    @GetMapping("/pickup-package")
    public @ResponseBody Integer pickUpPackage(@RequestBody String accessCode) {

        return locker.pickUp(accessCode);
    }
}
