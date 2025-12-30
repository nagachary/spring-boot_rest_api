package com.mylld.amazon.locker.controller;

import com.mylld.amazon.locker.response.DepositOutput;
import com.mylld.amazon.locker.Locker;
import com.mylld.amazon.locker.request.PackageDetails;
import com.mylld.amazon.locker.Size;
import com.mylld.amazon.locker.response.PickUpOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import static com.mylld.amazon.locker.Size.of;

import static com.mylld.amazon.locker.utils.AmazonLockerUtils.validateDepositRequest;

@RestController
@RequestMapping("/amazon-locker")
public class AmazonLockerController {
    private final Logger logger = LoggerFactory.getLogger(AmazonLockerController.class);

    @Autowired
    private Locker locker;

    @PostMapping("/deposit-package")
    public @ResponseBody DepositOutput depositPackage(@RequestBody PackageDetails packageDetails) {
        logger.info("depositPackage");
        validateDepositRequest(packageDetails);
        return locker.depositPackage(of(packageDetails.getPackageSize().toUpperCase()));
    }

    @GetMapping("/pickup-package")
    public @ResponseBody PickUpOutput pickUpPackage(@RequestParam("accessCode") String accessCode) {
        logger.info("pickUpPackage");
        PickUpOutput pickUpOutput = new PickUpOutput();
        pickUpOutput.setCompartmentId(locker.pickUp(accessCode));

        return pickUpOutput;
    }
}
