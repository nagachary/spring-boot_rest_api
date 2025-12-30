## Amazon Locker

### [Class Design](./diagram/Amazon-locker-class_diagram.drawio.png)
```
        Class Locker:
        - compartments: Compartment[]
        - accessTokenMapping: Map<string, AccessToken>
        - occupiedCompartments: Set<string>
        
            + Locker(compartments)
            + depositPackage(size) -> { compartmentId, tokenCode } | error
            + pickup(tokenCode) -> Integer | error
        
        Class AccessToken:
        - code: string
        - expiration: timestamp
        - compartment: Compartment
        
            + AccessToken(code, expiration, compartment)
            + getCompartmentIfValid() -> Compartment | null
            + getCompartment() -> Compartment
            + getCode() -> string
        
        Class Compartment:
        - id: Integer
        - size: Size
        
            + Compartment(id, size)
            + getSize() -> Size
            + getId() -> Integer
        
        Enum Size:
        - SMALL
        - MEDIUM
        - LARGE 
```
* **Locker**: This class provides the core orchestration logic for package deposit, pick-up, access token generation and its mapping with compartment. 
* **AccessToken**: This provides access token and upon verification the available or mapped compartment.
* **Compartment**: This is the compartment class which has id and size properties
* **Size**: This is an Enum which gives the size specification of the compartment for package deposit.
* **DepositOutput**: This is the class which is the output with access code and compartment details for the deposited package.

Endpoints:
```
        http://localhost:8085/mysbapp/amazon-locker/deposit-package
        http://localhost:8085/mysbapp/amazon-locker/pickup-package?accessCode=?
```


**Note:** Learning Reference: https://www.hellointerview.com/learn