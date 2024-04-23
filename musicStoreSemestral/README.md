# Database semestral work 

## Team
    - Matvej Safrankov (saframa9)

## Theme
    - music store (like a company)

    ### Quick description
        * Theme was chosen based on my connections and knowledge inmusic industry
        * Whole work should include: 
            * Online store
                * Admins
                * Registered Customers 
            * Offline store
                * Workers
                * Managers
            * Instruments catalogue
                * Instruments
                * PA systems
                * Accessories
    
## Diagram
    * diagram is inclued in "./cp1-krm.pdf"

## Relation model
```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>RM for DBS</title>
</head>

<body>

<h1>Relational model for music store database</h1>

<ul>
    <li> User (<u>userID</u>, <u>nickname, email</u>)</li>
</ul>

<ul>
    <li> PrivateInfo (<u>fullName, street, city, zip, userNickname, userMail, phoneNumber</u>, password)
        <ul>
            <li>FK: (userNickname, userMail) ⊆ User(nickname, email)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> WorkerUser (<u>workerId</u>, <u>personalIdentificationNumber</u>, <u>nickname, email</u>, location)
        <ul>
            <li>FK: WorkerUser(nickname, email) ⊆ User(nickname, email)</li>
            <li>FK: WorkerUser(location) ⊆ Store(location)</li>
        </ul>
    </li>

    <li> superiority (<u>isSuperior, isUnderling</u>)
        <ul>
            <li>FK: superiority(isSuperior) ⊆ WorkerUser(personalIdentificationNumber)</li>
            <li>FK: superiority(isUnderling) ⊆ WorkerUser(personalIdentificationNumber)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> CustomerUser (<u>customerID</u>, <u>nickname, email</u>, cookies, premiumStatus)
        <ul>
            <li>FK: (nickname, email) ⊆ User(nickname, email)</li>
        </ul>
    </li>

    <li> buys (<u>ISBN</u>, <u>nickname, email</u>)
        <ul>
            <li>FK: buys(ISBN) ⊆ Product(ISBN)</li>
            <li>FK: buys(nickname, email) ⊆ CustomerUser(nickname, email)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> Store (<u>storeId</u>, <u>location</u>)</li>
</ul>

<ul>
    <li> Product (<u>productID</u>, <u>ISBN</u>, <u>modelNumber</u>, manufacturer, location)
        <ul>
            <li>FK: Product(location) ⊆ Store(location)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> InstrumentProduct (<u>instrumentId</u>, <u>modelNumber</u>, range, type, pickupType)
        <ul>
            <li>pickupType(<u>pickupId</u>, <u>modelNumber</u>, name)
                <ul>
                    <li>FK: pickupType(modelNumber) ⊆ InstrumentProduct(modelNumber)</li>
                </ul>
            </li>
            <li>FK: InstrumentProduct(ISBN, modelNumber) ⊆ Product(ISBN, modelNumber)</li>
        </ul>
    </li>

    <li> includes(<u>ISBN</u>, <u>modelNumber</u>)
        <ul>
            <li>FK: includes(modelNumber) ⊆ InstrumentProduct(modelNumber)</li>
            <li>FK: includes(ISBN) ⊆ Accessory(ISBN)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> PASystemProduct (<u>systemId</u>, <u>ISBN</u>, range, type, resistance)
        <ul>
            <li>FK: PASystemProduct(ISBN) ⊆ Product(ISBN)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> Accessory (<u>accessoryId</u>, <u>ISBN</u>, type)</li>
</ul>

</body>
</html>
```

## Database

### SQL table creation
```sql
CREATE TABLE "User" (
    userID SERIAL UNIQUE,
    nickname VARCHAR(50),
    email VARCHAR(50)
        CHECK ( email LIKE '%@%'),
    PRIMARY KEY (nickname, email)
);

CREATE TABLE PrivateInfo (
    userNickname VARCHAR(50) NOT NULL,
    userMail VARCHAR(50) NOT NULL,
    fullName VARCHAR(50) NOT NULL,
    street VARCHAR(50),
    city VARCHAR(50),
    zip VARCHAR(50),
    phoneNumber VARCHAR(10),
    password VARCHAR(50),
    PRIMARY KEY (userNickname, userMail, fullName, street, city, zip, phoneNumber),
    FOREIGN KEY (userNickname, userMail) REFERENCES "User"(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE WorkerUser (
    workerId SERIAL PRIMARY KEY,
    personalIdentificationNumber VARCHAR(50) PRIMARY KEY,
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    location VARCHAR(50),
    PRIMARY KEY (personalIdentificationNumber, userNickname, userMail),
    FOREIGN KEY (userNickname, userMail) REFERENCES "User"(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (location) REFERENCES Store(location)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE CustomerUser (
    customerID SERIAL PRIMARY KEY,
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    cookies INTEGER,
    premiumStatus BOOLEAN,
    FOREIGN KEY (userNickname, userMail) REFERENCES "User"(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Superiority (
    isSuperior VARCHAR(50),
    isUnderling VARCHAR(50),
    PRIMARY KEY (isSuperior, isUnderling),
    FOREIGN KEY (isSuperior) REFERENCES WorkerUser(personalIdentificationNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (isUnderling) REFERENCES WorkerUser(personalIdentificationNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE buys (
    ISBN VARCHAR(50),
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    PRIMARY KEY (ISBN, userNickname, userMail),
    FOREIGN KEY (ISBN) REFERENCES Product(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (userNickname, userMail) REFERENCES CustomerUser(userNickname, userMail)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Store (
    storeId SERIAL PRIMARY KEY,
    location VARCHAR(50) UNIQUE
);

CREATE TABLE Product (
    productID SERIAL PRIMARY KEY,
    ISBN VARCHAR(50),
    modelNumber VARCHAR(50),
    manufacturer VARCHAR(50),
    location VARCHAR(50),
    PRIMARY KEY (ISBN, modelNumber),
    FOREIGN KEY (location) REFERENCES Store(location)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE InstrumentProduct (
    instrumentId SERIAL PRIMARY KEY,
    modelNumber VARCHAR(50),
    range VARCHAR(50),
    type VARCHAR(50),
    pickupType VARCHAR(50),
    FOREIGN KEY (pickupType) REFERENCES PickupType(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (modelNumber) REFERENCES Product(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE PickupType (
    pickupId SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    modelNumber VARCHAR(50),
    FOREIGN KEY (modelNumber) REFERENCES InstrumentProduct(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE includes (
    ISBN VARCHAR(50),
    modelNumber VARCHAR(50),
    PRIMARY KEY (ISBN, modelNumber),
    FOREIGN KEY (modelNumber) REFERENCES InstrumentProduct(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (ISBN) REFERENCES Accessory(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE PASystemProduct (
    systemId SERIAL PRIMARY KEY,
    ISBN VARCHAR(50),
    range VARCHAR(50),
    type VARCHAR(50),
    resistance VARCHAR(50),
    FOREIGN KEY (ISBN) REFERENCES Product(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);

CREATE TABLE Accessory (
    accessoryId SERIAL PRIMARY KEY,
    ISBN VARCHAR(50),
    type VARCHAR(50),
    FOREIGN KEY (ISBN) REFERENCES Product(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);



```
