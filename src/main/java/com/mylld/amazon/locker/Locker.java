package com.mylld.amazon.locker;

import com.mylld.amazon.locker.response.DepositOutput;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Instant;
import java.util.*;

import static java.lang.String.format;
import static java.time.Instant.now;
import static java.time.temporal.ChronoUnit.DAYS;

/*Locker is the orchestration class where we handle the deposit and pick-up features of Amazon locker*/
@Component
public class Locker {

    private final List<Compartment> compartments;
    private final Map<String, AccessToken> accessTokenMapping;
    private final Set<Integer> occupiedCompartments;
    private final Random random;

    public Locker(List<Compartment> compartments) {
        this.compartments = compartments;
        this.accessTokenMapping = new HashMap<>();
        this.occupiedCompartments = new HashSet<>();
        this.random = new Random();
    }

    /* Handles the package deposit of specific size */
    public DepositOutput depositPackage(Size packageSize) {
        Compartment availableComp = getAvailableCompartment(packageSize);
        if (null == availableComp) {

            throw new RuntimeException("No available compartment of package size " + packageSize);
        }

        AccessToken accessToken = generateAccessToken(availableComp);
        occupiedCompartments.add(availableComp.getId());
        accessTokenMapping.put(accessToken.getAccessCode(), accessToken);

        return new DepositOutput(accessToken.getAccessCode(), availableComp.getId());
    }

    /* Handles the package pick-up using access code */
    public Integer pickUp(String accessCode) {

        if (!StringUtils.hasText(accessCode)) {
            throw new RuntimeException("Invalid access token code");
        }

        AccessToken userAccessToken = accessTokenMapping.get(accessCode);
        if (null == userAccessToken) {

            throw new RuntimeException("Invalid access token code");
        }

        Compartment compartment = userAccessToken.getCompartmentIfValid();
        if (null == compartment) {
            clearLocker(userAccessToken);

            throw new RuntimeException("Access token has expired");
        }

        clearLocker(userAccessToken);
        return compartment.getId();
    }

    private Compartment getAvailableCompartment(Size size) {
        System.out.println(" getAvailableCompartment : ");

        if (compartments.isEmpty() && occupiedCompartments.isEmpty()) {
            Compartment compartment = new Compartment();
            compartment.setId(1);
            compartment.setSize(size);
            return compartment;
        }

        Optional<Compartment> availableCompartment = compartments.stream()
                //.filter(comp -> !compartments.isEmpty())
                .filter(comp -> comp.getSize() == size)
                .filter(comp -> !occupiedCompartments.contains(comp.getId())).findFirst();

        return availableCompartment.orElse(null);
    }

    private AccessToken generateAccessToken(Compartment compartment) {
        String accessCode = format("%06d", random.nextInt(1_000_000));
        Instant expiration = now().plus(7, DAYS);

        return new AccessToken(accessCode, expiration, compartment);
    }

    private void clearLocker(AccessToken accessToken) {
        Compartment currentCompartment = accessToken.getCompartment();
        occupiedCompartments.remove(currentCompartment.getId());
        accessTokenMapping.remove(accessToken.getAccessCode());
    }
}
