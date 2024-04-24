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
    <li> PrivateInfo (<u>fullName, homeNumber, street, city, zip, userNickname, userMail, phoneNumber</u>, password)
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
            <li>pickupType(<u>pickupId</u>, <u>modelNumber</u>, <u>pickupModelNumber</u>, name)
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
-- Creating tables =====================================================================================================
CREATE TABLE Users (
    userID SERIAL UNIQUE,
    nickname VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL
        CHECK ( email LIKE '%@%'),
    PRIMARY KEY (nickname, email)
);


CREATE TABLE PrivateInfo (
    userNickname VARCHAR(50) NOT NULL,
    userMail VARCHAR(50) NOT NULL,
    fullName VARCHAR(50),
    homeNumber VARCHAR(50),
    street VARCHAR(50),
    city VARCHAR(50),
    zip VARCHAR(50),
    phoneNumber VARCHAR(10),
    password VARCHAR(50),
    PRIMARY KEY (userNickname, userMail, fullName, street, city, zip, phoneNumber),
    FOREIGN KEY (userNickname, userMail) REFERENCES Users(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE WorkerUser (
    workerId SERIAL UNIQUE,
    personalIdentificationNumber VARCHAR(50) UNIQUE NOT NULL,
    userNickname VARCHAR(50) NOT NULL,
    userMail VARCHAR(50) NOT NULL,
    location VARCHAR(50),
    PRIMARY KEY (userNickname, userMail),
    FOREIGN KEY (userNickname, userMail) REFERENCES Users(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (location) REFERENCES Store(location)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE CustomerUser (
    customerId SERIAL UNIQUE,
    userNickname VARCHAR(50) NOT NULL,
    userMail VARCHAR(50) NOT NULL,
    cookies INTEGER,
    premiumStatus BOOLEAN,
    PRIMARY KEY (userNickname, userMail),
    FOREIGN KEY (userNickname, userMail) REFERENCES Users(nickname, email)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE Superiority (
    isSuperior VARCHAR(50) NOT NULL,
    isUnderling VARCHAR(50) NOT NULL,
    PRIMARY KEY (isSuperior, isUnderling),
    FOREIGN KEY (isSuperior) REFERENCES WorkerUser(personalIdentificationNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (isUnderling) REFERENCES WorkerUser(personalIdentificationNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE buys (
    ISBN VARCHAR(50) NOT NULL,
    userNickname VARCHAR(50) NOT NULL,
    userMail VARCHAR(50) NOT NULL,
    PRIMARY KEY (userNickname, userMail, ISBN),
    FOREIGN KEY (ISBN) REFERENCES Product(ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (userNickname, userMail) REFERENCES CustomerUser(userNickname, userMail)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE Store (
    storeId SERIAL UNIQUE,
    location VARCHAR(50) PRIMARY KEY NOT NULL
);


CREATE TABLE Product (
    productID SERIAL UNIQUE,
    ISBN VARCHAR(50) NOT NULL,
    modelNumber VARCHAR(50) NOT NULL,
    manufacturer VARCHAR(50),
    location VARCHAR(50),
    PRIMARY KEY (ISBN, modelNumber),
    FOREIGN KEY (location) REFERENCES Store(location)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE InstrumentProduct (
    instrumentId SERIAL UNIQUE,
    ISBN VARCHAR(50) UNIQUE NOT NULL,
    modelNumber VARCHAR(50) PRIMARY KEY NOT NULL,
    range VARCHAR(50) CHECK (range LIKE '%Hz-%Hz'),
    type VARCHAR(50) CHECK (type IN ('key', 'string', 'wind', 'percussion', 'special')),
    pickupModel VARCHAR(50),
    FOREIGN KEY (pickupModel) REFERENCES PickupType(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    FOREIGN KEY (modelNumber, ISBN) REFERENCES Product(modelNumber, ISBN)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE PickupType (
    pickupId SERIAL UNIQUE,
    name VARCHAR(50) NOT NULL 
        CHECK (name IN ('single coil', 'humbucker', 'piezo', 'lipstick', 'active', 'passive')),
    instrumentModelNumber VARCHAR(50),
    modelNumber VARCHAR(50) PRIMARY KEY NOT NULL,
    FOREIGN KEY (instrumentModelNumber) REFERENCES InstrumentProduct(modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE includes(
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
    systemId SERIAL UNIQUE,
    ISBN VARCHAR(50) PRIMARY KEY NOT NULL,
    modelNumber VARCHAR(50) UNIQUE NOT NULL,
    range VARCHAR(50) CHECK (range LIKE '%Hz-%Hz'),
    type VARCHAR(50) CHECK (type IN ('active', 'passive')),
    resistance VARCHAR(50) CHECK (resistance LIKE '%Ohm'),
    FOREIGN KEY (ISBN, modelNumber) REFERENCES Product(ISBN, modelNumber)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


CREATE TABLE Accessory (
    accessoryId SERIAL UNIQUE,
    ISBN VARCHAR(50) PRIMARY KEY NOT NULL,
    type VARCHAR(50)
);


-- New types for easy data insertion ====================================================================================
CREATE TYPE WorkerUserType AS (
    personalIdentificationNumber VARCHAR(50),
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    location VARCHAR(50)
);


CREATE TYPE CustomerUserType AS (
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    cookies INTEGER,
    premiumStatus BOOLEAN
);


CREATE TYPE PASystemProductType AS (
    ISBN VARCHAR(50),
    modelNumber VARCHAR(50),
    range VARCHAR(50),
    type VARCHAR(50),
    resistance VARCHAR(50)
);


CREATE TYPE InstrumentProductType AS (
    ISBN VARCHAR(50),
    modelNumber VARCHAR(50),
    range VARCHAR(50),
    type VARCHAR(50),
    pickupModel VARCHAR(50)
);
    

CREATE TYPE privateInfoType AS (
    userNickname VARCHAR(50),
    userMail VARCHAR(50),
    fullName VARCHAR(50),
    homeNumber VARCHAR(50),
    street VARCHAR(50),
    city VARCHAR(50),
    zip VARCHAR(50),
    phoneNumber VARCHAR(10),
    password VARCHAR(50)
);

-- Insert functions for easy and safe hereditary data insertion =======================================================
CREATE OR REPLACE FUNCTION insertWorkerUser(
    i_personalIdentificationNumber VARCHAR(50),
    i_userNickname VARCHAR(50),
    i_userMail VARCHAR(50),
    i_location VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_userId INTEGER;
BEGIN
    SELECT userID INTO e_userId FROM Users WHERE nickname = i_userNickname AND email = i_userMail;
    IF NOT FOUND THEN
        INSERT INTO Users(nickname, email) VALUES (i_userNickname, i_userMail);
    END IF;
    
    INSERT INTO WorkerUser(personalIdentificationNumber, userNickname, userMail, location) 
        VALUES (i_personalIdentificationNumber, i_userNickname, i_userMail, i_location);
        
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION insertWorkers(i_users WorkerUserType[])
RETURNS VOID AS $$
DECLARE
    userRecord WorkerUserType;
BEGIN
    FOREACH userRecord IN ARRAY i_users
    LOOP 
        PERFORM insertWorkerUser(userRecord.personalIdentificationNumber, 
                                 userRecord.userNickname, 
                                 userRecord.userMail, 
                                 userRecord.location);
    END LOOP;
END;
$$ LANGUAGE plpgsql;
    

CREATE OR REPLACE FUNCTION insertCustomerUser(
    i_userNickname VARCHAR(50),
    i_userMail VARCHAR(50),
    i_cookies INTEGER,
    i_premiumStatus BOOLEAN
) RETURNS VOID AS $$
DECLARE
    e_userId INTEGER;
BEGIN
    SELECT userID INTO e_userId FROM Users WHERE nickname = i_userNickname AND email = i_userMail;
    IF NOT FOUND THEN
        INSERT INTO Users(nickname, email) VALUES (i_userNickname, i_userMail);
    END IF;
    
    INSERT INTO CustomerUser(userNickname, userMail, cookies, premiumStatus) 
        VALUES (i_userNickname, i_userMail, i_cookies, i_premiumStatus);
        
END;
$$ LANGUAGE plpgsql;

    
CREATE OR REPLACE FUNCTION insertCustomers(i_users CustomerUserType[])
RETURNS VOID AS $$
DECLARE
    userRecord CustomerUserType;
BEGIN
    FOREACH userRecord IN ARRAY i_users
        LOOP
            PERFORM insertCustomerUser(userRecord.userNickname,
                                       userRecord.userMail,
                                       userRecord.cookies,
                                       userRecord.premiumStatus);
        END LOOP;
END;
$$ LANGUAGE plpgsql;


CREATE FUNCTION insertInstrumentProduct(
    i_ISBN VARCHAR(50),
    i_modelNumber VARCHAR(50),
    i_range VARCHAR(50),
    i_type VARCHAR(50),
    i_pickupModel VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_productID INTEGER;
BEGIN
    SELECT productID INTO e_productID FROM Product WHERE modelNumber = i_modelNumber;
    IF NOT FOUND THEN
        INSERT INTO Product(ISBN, modelNumber) VALUES (i_ISBN, i_modelNumber);
    END IF;
    
    INSERT INTO InstrumentProduct(ISBN, modelNumber, range, type, pickupModel) 
        VALUES (i_ISBN, i_modelNumber, i_range, i_type, i_pickupModel);
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION insertInstruments(i_instruments InstrumentProductType[])
RETURNS VOID AS $$
DECLARE
    instrumentRecord InstrumentProductType;
BEGIN
    FOREACH instrumentRecord IN ARRAY i_instruments
    LOOP
        PERFORM insertInstrumentProduct(instrumentRecord.ISBN,
                                        instrumentRecord.modelNumber,
                                        instrumentRecord.range,
                                        instrumentRecord.type,
                                        instrumentRecord.pickupModel);
    END LOOP;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION insertPAProduct(
    i_ISBN VARCHAR(50),
    i_modelNumber VARCHAR(50),
    i_range VARCHAR(50),
    i_type VARCHAR(50),
    i_resistance VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_productID INTEGER;
BEGIN
    SELECT productID INTO e_productID FROM Product WHERE modelNumber = i_modelNumber;
    IF NOT FOUND THEN
        INSERT INTO Product(ISBN, modelNumber) VALUES (i_ISBN, i_modelNumber);
    END IF;
    
    INSERT INTO PASystemProduct(ISBN, modelNumber, range, type, resistance) 
        VALUES (i_ISBN, i_modelNumber, i_range, i_type, i_resistance);
END;
$$ LANGUAGE plpgsql;

  
CREATE OR REPLACE FUNCTION insertPASystems(i_systems PASystemProductType[])
RETURNS VOID AS $$
DECLARE
    systemRecord PASystemProductType;
BEGIN
    FOREACH systemRecord IN ARRAY i_systems
    LOOP
        PERFORM insertPAProduct(systemRecord.ISBN,
                                systemRecord.modelNumber,
                                systemRecord.range,
                                systemRecord.type,
                                systemRecord.resistance);
    END LOOP;
END;
$$ LANGUAGE plpgsql;
   

CREATE OR REPLACE FUNCTION insertPrivateInfo(
    i_userNickname VARCHAR(50),
    i_userMail VARCHAR(50),
    i_fullName VARCHAR(50),
    i_homeNumber VARCHAR(50),
    i_street VARCHAR(50),
    i_city VARCHAR(50),
    i_zip VARCHAR(50),
    i_phoneNumber VARCHAR(10),
    i_password VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_userId INTEGER;
BEGIN
    SELECT userID INTO e_userId FROM Users WHERE nickname = i_userNickname AND email = i_userMail;
    IF FOUND THEN
        INSERT INTO PrivateInfo(userNickname, userMail, fullName, homeNumber, street, city, zip, phoneNumber, password) 
            VALUES (i_userNickname, i_userMail, i_fullName, i_homeNumber, i_street, i_city, i_zip, i_phoneNumber, i_password);
    ELSE
        RAISE EXCEPTION 'User % % not found, create new user using insertWorkerUser() or insertCustomerUser()', i_userNickname, i_userMail;
    END IF;
END;
$$ LANGUAGE plpgsql;


CREATE OR REPLACE FUNCTION insertPrivateInfos(i_infos privateInfoType[])
RETURNS VOID AS $$
DECLARE
    infoRecord privateInfoType;
BEGIN
    FOREACH infoRecord IN ARRAY i_infos
    LOOP
        PERFORM insertPrivateInfo(infoRecord.userNickname,
                                  infoRecord.userMail,
                                  infoRecord.fullName,
                                  infoRecord.homeNumber,
                                  infoRecord.street,
                                  infoRecord.city,
                                  infoRecord.zip,
                                  infoRecord.phoneNumber,
                                  infoRecord.password);
    END LOOP;
END;
$$ LANGUAGE plpgsql;


-- Inserting data in to tables =========================================================================================
    -- Insert at least 10 records into each table

INSERT INTO Store(location) 
    VALUES ('Prague/Muzeum'),
              ('Prague/Andel'),
              ('Brno/Namesti Svobody'),
              ('Praha/Namesti Republiky'),
              ('Ostrava/Namesti Svobody'),
              ('Online'),
              ('Berlin/Alexanderplatz'),
              ('Berlin/Kurfurstendamm'),
              ('Vienna/Stephansplatz'),
              ('Vienna/Praterstern');

SELECT insertWorkers(ARRAY[
    ('123456789', 'worker1', 'worker1@gmail.com', 'Prague/Muzeum'),
    ('987654321', 'worker2', 'worker2@gmail.com', 'Prague/Andel'),
    ('123123123', 'worker3', 'worker3@gmail.com', 'Brno/Namesti Svobody'),
    ('321321321', 'worker4', 'worker4@gmail.com', 'Praha/Namesti Republiky'),
    ('456456456', 'worker5', 'worker5@gmail.com', 'Ostrava/Namesti Svobody'),
    ('654654654', 'worker6', 'worker6@gmail.com', 'Online'),
    ('789789789', 'worker7', 'worker7@gmail.com', 'Berlin/Alexanderplatz'),
    ('987987987', 'worker8', 'worker8@gmail.com', 'Berlin/Kurfurstendamm'),
    ('654654654', 'worker9', 'worker9@gmail.com', 'Vienna/Stephansplatz'),
    ('321321321', 'worker10', 'worker10@gmail.com', 'Vienna/Praterstern')
    ]::WorkerUserType[]);

SELECT insertCustomers(ARRAY[
    ('customer1', 'cus1@seznam.cz', 0, FALSE),
    ('customer2', 'cus2@seznam.cz', 505, FALSE),
    ('customer3', 'cus3@seznam.cz', 404, FALSE),
    ('customer4', 'cus4@seznam.cz', 436, TRUE),
    ('customer5', 'cus5@seznam.cz', 0, FALSE),
    ('customer6', 'cus6@seznam.cz', 0, FALSE),
    ('customer7', 'cus7@seznam.cz', 102, TRUE),
    ('customer8', 'cus8@seznam.cz', 0, FALSE),
    ('customer9', 'cus9@seznam.cz', 555, TRUE),
    ('customer10', 'cus10@seznam.cz', 291, FALSE)
    ]::CustomerUserType[]);
    

```