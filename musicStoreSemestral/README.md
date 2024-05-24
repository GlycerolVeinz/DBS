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
    <li> WorkerUser (<u>workerId</u>, <u>personalIdentificationNumber</u>, <u>userNickname, userMail</u>, location)
        <ul>
            <li>FK: WorkerUser(userNickname, userMail) ⊆ User(nickname, email)</li>
            <li>FK: WorkerUser(location) ⊆ Store(location)</li>
        </ul>
    </li>

    <li> superiority (<u>superiorityId</u>, superiorId, underlingId,<u>isSuperior, isUnderling</u>)
        <ul>
            <li>FK: superiority(isSuperior) ⊆ WorkerUser(personalIdentificationNumber)</li>
            <li>FK: superiority(isUnderling) ⊆ WorkerUser(personalIdentificationNumber)</li>
            <li>FK: superiority(superiorId) ⊆ WorkerUser(workerId)</li>
            <li>FK: superiority(underlingId) ⊆ WorkerUser(workerId)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> CustomerUser (<u>customerID</u>, <u>userNickname, userMail</u>, cookies, premiumStatus)
        <ul>
            <li>FK: (nickname, email) ⊆ User(nickname, email)</li>
        </ul>
    </li>

    <li> buys (<u>purchaseId</u>, <u>ISBN, modelNumber</u>, <u>nickname, email</u>)
        <ul>
            <li>FK: buys(ISBN, modelNumber) ⊆ Product(ISBN, modelNumber)</li>
            <li>FK: buys(nickname, email) ⊆ CustomerUser(nickname, email)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> Store (<u>storeId</u>, <u>location</u>)</li>
</ul>

<ul>
    <li> Product (<u>productID</u>, <u>ISBN</u>, <u>modelNumber</u>, location)
        <ul>
            <li>FK: Product(location) ⊆ Store(location)</li>
        </ul>
    </li>
</ul>

<ul>
    <li> InstrumentProduct (<u>instrumentId</u>, <u>modelNumber, ISBN</u>, range, type)
        <ul>
            <li>FK: InstrumentProduct(ISBN, modelNumber) ⊆ Product(ISBN, modelNumber)</li>
        </ul>
    </li>

    <li>Pickup(<u>pickupId</u>, <u>modelNumber</u>, name)</li>
    
    <li> InstrumentPickup (<u>installedOnId</u>, instrumentId, pickupID, modelNumber, pickupModelNumber)
        <ul>
            <li>FK: InstrumentPickup(modelNumber) ⊆ InstrumentProduct(modelNumber)</li>
            <li>FK: InstrumentPickup(pickupModelNumber) ⊆ Pickup(modelNumber)</li>
            <li>FK: InstrumentPickup(instrumentId) ⊆ InstrumentProduct(instrumentId)</li>
            <li>FK: InstrumentPickup(pickupID) ⊆ Pickup(pickupID)</li>
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
    <li> Accessory (<u>accessoryId</u>, <u>ISBN</u>, type, comesWith)
        <ul>
            <li>FK: Accessory(comesWith) ⊆ InstrumentProduct(modelNumber)</li>
        </ul>
    </li>
</ul>

</body>
</html>
```

## Database

### SQL

```sql
-- create table queries
CREATE TABLE Users (
                       userID SERIAL PRIMARY KEY,
                       nickname VARCHAR(50) NOT NULL,
                       email VARCHAR(50) NOT NULL CHECK (email LIKE '%@%'),
                       UNIQUE (nickname, email)
);

CREATE TABLE PrivateInfo (
                             privateInfoID SERIAL PRIMARY KEY,
                             userID INTEGER NOT NULL,
                             fullName VARCHAR(50),
                             homeNumber VARCHAR(50),
                             street VARCHAR(50),
                             city VARCHAR(50),
                             zip VARCHAR(50),
                             phoneNumber VARCHAR(12),
                             password VARCHAR(50),
                             FOREIGN KEY (userID) REFERENCES Users(userID)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE
);

CREATE TABLE WorkerUser (
                            userID INTEGER PRIMARY KEY,
                            personalIdentificationNumber VARCHAR(50) UNIQUE NOT NULL,
                            location VARCHAR(50),
                            FOREIGN KEY (userID) REFERENCES Users(userID)
                                ON DELETE CASCADE
                                ON UPDATE CASCADE
);

CREATE TABLE CustomerUser (
                              userID INTEGER PRIMARY KEY,
                              cookies INTEGER,
                              premiumStatus BOOLEAN,
                              FOREIGN KEY (userID) REFERENCES Users(userID)
                                  ON DELETE CASCADE
                                  ON UPDATE CASCADE
);

CREATE TABLE Superiority (
                             superiorityID SERIAL PRIMARY KEY,
                             superiorWorkerID INTEGER,
                             underlingWorkerID INTEGER,
                             FOREIGN KEY (superiorWorkerID) REFERENCES WorkerUser(userID)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE,
                             FOREIGN KEY (underlingWorkerID) REFERENCES WorkerUser(userID)
                                 ON DELETE CASCADE
                                 ON UPDATE CASCADE,
                             CONSTRAINT check_notSuperiorToMyself CHECK (superiorWorkerID != underlingWorkerID),
                             CONSTRAINT check_uniqueSuperiority UNIQUE (superiorWorkerID, underlingWorkerID)
);

CREATE TABLE Store (
                       storeID SERIAL PRIMARY KEY,
                       location VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE Product (
                         productID SERIAL PRIMARY KEY,
                         ISBN VARCHAR(50) NOT NULL,
                         modelNumber VARCHAR(50) NOT NULL,
                         location VARCHAR(50),
                         price INTEGER, 
                         UNIQUE (ISBN, modelNumber),
                         FOREIGN KEY (location) REFERENCES Store(location)
                             ON DELETE CASCADE
                             ON UPDATE CASCADE
);

CREATE TABLE InstrumentProduct (
                                   productID INTEGER PRIMARY KEY,
                                   range VARCHAR(50) CHECK (range LIKE '%Hz-%Hz'),
                                   type VARCHAR(50) CHECK (type IN ('key', 'string', 'wind', 'percussion', 'special')),
                                   FOREIGN KEY (productID) REFERENCES Product(productID)
                                       ON DELETE CASCADE
                                       ON UPDATE CASCADE
);

CREATE TABLE Pickup (
                        pickupID SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        CONSTRAINT check_pickupType CHECK (name IN ('single coil', 'humbucker', 'piezo', 'lipstick', 'active', 'passive'))
);

CREATE TABLE InstrumentPickup (
                                  installedOnID SERIAL PRIMARY KEY,
                                  instrumentID INTEGER NOT NULL,
                                  pickupID INTEGER,
                                  FOREIGN KEY (instrumentID) REFERENCES InstrumentProduct(productID)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE,
                                  FOREIGN KEY (pickupID) REFERENCES Pickup(pickupID)
                                      ON DELETE CASCADE
                                      ON UPDATE CASCADE,
                                  CONSTRAINT check_noPickupAtAll CHECK (
                                      (pickupID IS NULL) OR
                                      (pickupID IS NOT NULL)
                                      )
);

CREATE TABLE Includes (
                          productID INTEGER NOT NULL,
                          accessoryID INTEGER NOT NULL,
                          PRIMARY KEY (productID, accessoryID),
                          FOREIGN KEY (productID) REFERENCES InstrumentProduct(productID)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE,
                          FOREIGN KEY (accessoryID) REFERENCES Accessory(accessoryID)
                              ON DELETE CASCADE
                              ON UPDATE CASCADE
);

CREATE TABLE PASystemProduct (
                                 productID INTEGER PRIMARY KEY,
                                 range VARCHAR(50) CHECK (range LIKE '%Hz-%Hz'),
                                 type VARCHAR(50) CHECK (type IN ('active', 'passive')),
                                 resistance VARCHAR(50) CHECK (resistance LIKE '%Ohm'),
                                 FOREIGN KEY (productID) REFERENCES Product(productID)
                                     ON DELETE CASCADE
                                     ON UPDATE CASCADE
);

CREATE TABLE Accessory (
                           accessoryID SERIAL PRIMARY KEY,
                           type VARCHAR(50),
                           comesWith INTEGER REFERENCES InstrumentProduct(productID) ON UPDATE CASCADE ON DELETE SET NULL,
                           ISBN VARCHAR(50) UNIQUE
);

CREATE TABLE Buys (
                      purchaseID SERIAL PRIMARY KEY,
                      productID INTEGER NOT NULL,
                      userID INTEGER NOT NULL,
                      UNIQUE (userID, productID),
                      FOREIGN KEY (productID) REFERENCES Product(productID)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE,
                      FOREIGN KEY (userID) REFERENCES CustomerUser(userID)
                          ON DELETE CASCADE
                          ON UPDATE CASCADE
);

-- Insert functions for easy and safe hereditary data insertion
CREATE OR REPLACE FUNCTION insertWorkerUser(
    i_personalIdentificationNumber VARCHAR(50),
    i_userNickname VARCHAR(50),
    i_userMail VARCHAR(50),
    i_location VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_userID INTEGER;
BEGIN
    -- Check if the user exists, if not, insert it
    SELECT userID INTO e_userID FROM Users WHERE nickname = i_userNickname AND email = i_userMail;
    IF NOT FOUND THEN
        INSERT INTO Users(nickname, email) VALUES (i_userNickname, i_userMail) RETURNING userID INTO e_userID;
    END IF;

    -- Perform the replace operation in WorkerUser
    INSERT INTO WorkerUser (personalIdentificationNumber, userID, location)
    VALUES (i_personalIdentificationNumber, e_userID, i_location)
    ON CONFLICT (userID) DO UPDATE
        SET personalIdentificationNumber = EXCLUDED.personalIdentificationNumber,
            location = EXCLUDED.location;
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
    e_userID INTEGER;
BEGIN
    -- Check if the user exists, if not, insert it
    SELECT userID INTO e_userID FROM Users WHERE nickname = i_userNickname AND email = i_userMail;
    IF NOT FOUND THEN
        INSERT INTO Users(nickname, email) VALUES (i_userNickname, i_userMail) RETURNING userID INTO e_userID;
    END IF;

    -- Perform the replace operation in CustomerUser
    INSERT INTO CustomerUser (userID, cookies, premiumStatus)
    VALUES (e_userID, i_cookies, i_premiumStatus)
    ON CONFLICT (userID) DO UPDATE
        SET cookies = EXCLUDED.cookies,
            premiumStatus = EXCLUDED.premiumStatus;
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


CREATE OR REPLACE FUNCTION insertInstrumentProduct(
    i_ISBN VARCHAR(50),
    i_modelNumber VARCHAR(50),
    i_range VARCHAR(50),
    i_type VARCHAR(50)
) RETURNS VOID AS $$
DECLARE
    e_productID INTEGER;
BEGIN
    -- Check if the product exists, if not, insert it
    SELECT productID INTO e_productID FROM Product WHERE ISBN = i_ISBN AND modelNumber = i_modelNumber;
    IF NOT FOUND THEN
        INSERT INTO Product(ISBN, modelNumber) VALUES (i_ISBN, i_modelNumber) RETURNING productID INTO e_productID;
    END IF;

    -- Perform the replace operation in InstrumentProduct
    INSERT INTO InstrumentProduct (productID, range, type)
    VALUES (e_productID, i_range, i_type)
    ON CONFLICT (productID) DO UPDATE
        SET range = EXCLUDED.range,
            type = EXCLUDED.type;
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
                                            instrumentRecord.type);
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
    -- Check if the product exists, if not, insert it
    SELECT productID INTO e_productID FROM Product WHERE ISBN = i_ISBN AND modelNumber = i_modelNumber;
    IF NOT FOUND THEN
        INSERT INTO Product(ISBN, modelNumber) VALUES (i_ISBN, i_modelNumber) RETURNING productID INTO e_productID;
    END IF;

    -- Perform the replace operation in PASystemProduct
    INSERT INTO PASystemProduct (productID, range, type, resistance)
    VALUES (e_productID, i_range, i_type, i_resistance)
    ON CONFLICT (productID) DO UPDATE
        SET range = EXCLUDED.range,
            type = EXCLUDED.type,
            resistance = EXCLUDED.resistance;
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
    i_userID INTEGER,
    i_fullName VARCHAR(50),
    i_homeNumber VARCHAR(50),
    i_street VARCHAR(50),
    i_city VARCHAR(50),
    i_zip VARCHAR(50),
    i_phoneNumber VARCHAR(10),
    i_password VARCHAR(50)
) RETURNS VOID AS $$
BEGIN
    INSERT INTO PrivateInfo(userID, fullName, homeNumber, street, city, zip, phoneNumber, password)
    VALUES (i_userID, i_fullName, i_homeNumber, i_street, i_city, i_zip, i_phoneNumber, i_password)
    ON CONFLICT (userID) DO UPDATE
        SET fullName = EXCLUDED.fullName,
            homeNumber = EXCLUDED.homeNumber,
            street = EXCLUDED.street,
            city = EXCLUDED.city,
            zip = EXCLUDED.zip,
            phoneNumber = EXCLUDED.phoneNumber,
            password = EXCLUDED.password;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insertPrivateInfos(i_infos privateInfoType[])
    RETURNS VOID AS $$
DECLARE
    infoRecord privateInfoType;
BEGIN
    FOREACH infoRecord IN ARRAY i_infos
        LOOP
            PERFORM insertPrivateInfo(infoRecord.userID,
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

create function insertsuperiorityrelation(superior_workerid integer, underling_workerid integer) returns void
    language plpgsql
as
$$
DECLARE
    superior_pn VARCHAR(50);
    underling_pn VARCHAR(50);
BEGIN
    SELECT userid INTO superior_pn FROM WorkerUser WHERE userid = superior_workerId;
    SELECT userid INTO underling_pn FROM WorkerUser WHERE userid = underling_workerId;

    IF superior_pn IS NOT NULL AND underling_pn IS NOT NULL THEN
        INSERT INTO Superiority (superiorworkerid, underlingworkerid)
        VALUES (superior_pn, underling_pn)
        ON CONFLICT (superiorworkerid, underlingworkerid) DO NOTHING;
    ELSE
        RAISE EXCEPTION 'Invalid worker IDs: % %', superior_workerId, underling_workerId;
    END IF;
END;
$$;

CREATE OR REPLACE FUNCTION insertInstrumentPickup(
    i_instrumentId INTEGER,
    i_pickupID INTEGER
) RETURNS VOID AS $$
DECLARE
    e_instrument_mn INTEGER;
    e_pickupID_mn INTEGER;
BEGIN
    SELECT productid INTO e_instrument_mn FROM InstrumentProduct WHERE productid = i_instrumentId;
    SELECT pickupid INTO e_pickupID_mn FROM Pickup WHERE pickupId = i_pickupID;
    IF e_instrument_mn IS NOT NULL AND e_pickupID_mn IS NOT NULL THEN
        INSERT INTO InstrumentPickup(instrumentId, pickupID)
        VALUES (e_instrument_mn, e_pickupID_mn)
        ON CONFLICT (instrumentId, pickupID) DO NOTHING;
    ELSE
        RAISE EXCEPTION 'Invalid instrument ID: % or pickup ID: %', i_instrumentId, i_pickupID;
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION insertInstrumentPickups(i_pairs intPair[])
    RETURNS VOID AS $$
DECLARE
    pair intPair;
BEGIN
    FOREACH pair IN ARRAY i_pairs
        LOOP
            PERFORM insertInstrumentPickup(pair.frst, pair.scnd);
        END LOOP;
END;
$$ LANGUAGE plpgsql;

-- Inserting data into tables =========================================================================================
-- Insert at least 10 records into each table
-- !!! NOTE: Some numeric values could be flawed, check server for data that was inputted

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
    ('654654655', 'worker9', 'worker9@gmail.com', 'Vienna/Stephansplatz'),
    ('321321323', 'worker10', 'worker10@gmail.com', 'Vienna/Praterstern')
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


-- superiority should be like a tree
SELECT insertSuperiorityRelations(ARRAY[
    (11, 12),
    (11, 13),
    (11, 19),
    (13, 14),
    (14, 17),
    (14, 18),
    (15, 16),
    (15, 110),
    (15, 18),
    (19, 20)
    ]::intPair[]);

SELECT insertPrivateInfos(ARRAY[
    (1, 'John Doe', '123', 'Main Street', 'Prague', '12345', '420123456789', 'password1'),
    (2, 'Jane Doe', '456', 'Second Street', 'Prague', '54321', '420987654321', 'password2'),
    (3, 'John Smith', '789', 'Third Street', 'Brno', '67890', '420123123123', 'password3'),
    (4, 'Jane Smith', '012', 'Fourth Street', 'Praha', '09876', '420321321321', 'password4'),
    (5, 'John Johnson', '345', 'Fifth Street', 'Ostrava', '54321', '420456456456', 'password5'),
    (6, 'Jane Johnson', '678', 'Sixth Street', 'Online', '67890', '420654654654', 'password6'),
    (4, 'Bob Doe', '123', 'Main Street', 'Prague', '12345', '420123456789', 'password1'),
    (9, 'Janek Doe', '456', 'Second Street', 'Prague', '54321', '420987654321', 'password2'),
    (10, 'Pepa Smith', '789', 'Third Street', 'Brno', '67890', '420123123123', 'password3'),
    (7, 'Janek Smith', '012', 'Fourth Street', 'Praha', '09876', '420321321321', 'password4')
    ]::privateInfoType[]);

SELECT insertInstruments(ARRAY[
    ('123456789', 'instrument1', '20Hz-20kHz', 'key'),
    ('187654321', 'instrument2', '20Hz-20kHz', 'string'),
    ('123123123', 'instrument3', '20Hz-20kHz', 'wind'),
    ('121321321', 'instrument4', '20Hz-20kHz', 'percussion'),
    ('156121212', 'instrument5', '20Hz-20kHz', 'special'),
    ('154654654', 'instrument6', '20Hz-20kHz', 'key'),
    ('189789789', 'instrument7', '20Hz-20kHz', 'string'),
    ('187987987', 'instrument8', '20Hz-20kHz', 'wind'),
    ('154654654', 'instrument9', '20Hz-20kHz', 'percussion'),
    ('121321321', 'instrument10', '20Hz-20kHz', 'special')
    ]::InstrumentProductType[]);

SELECT insertInstruments(ARRAY[
    ('123456789', 'instrument1', '20Hz-20kHz', 'key'),
    ('187654321', 'instrument2', '20Hz-20kHz', 'string'),
    ('123123123', 'instrument3', '20Hz-20kHz', 'wind'),
    ('121321321', 'instrument4', '20Hz-20kHz', 'percussion'),
    ('156121212', 'instrument5', '20Hz-20kHz', 'special'),
    ('154654654', 'instrument6', '20Hz-20kHz', 'key'),
    ('189789789', 'instrument7', '20Hz-20kHz', 'string'),
    ('187987987', 'instrument8', '20Hz-20kHz', 'wind'),
    ('154654654', 'instrument9', '20Hz-20kHz', 'percussion'),
    ('121321321', 'instrument10', '20Hz-20kHz', 'special')
    ]::InstrumentProductType[]);


SELECT insertPASystems(ARRAY[
    ('223456789', 'system1', '20Hz-20kHz', 'active', '8Ohm'),
    ('287654321', 'system2', '20Hz-20kHz', 'passive', '4Ohm'),
    ('223123123', 'system3', '20Hz-20kHz', 'active', '8Ohm'),
    ('221321321', 'system4', '20Hz-20kHz', 'passive', '4Ohm'),
    ('256121212', 'system5', '20Hz-20kHz', 'active', '8Ohm'),
    ('254654654', 'system6', '20Hz-20kHz', 'passive', '4Ohm'),
    ('289789789', 'system7', '20Hz-20kHz', 'active', '8Ohm'),
    ('287987987', 'system8', '20Hz-20kHz', 'passive', '4Ohm'),
    ('254654654', 'system9', '20Hz-20kHz', 'active', '8Ohm'),
    ('221321321', 'system10', '20Hz-20kHz', 'passive', '4Ohm')
    ]::PASystemProductType[]);


INSERT INTO Pickup(pickupid, name) VALUES
                                       (1, 'single coil'),
                                       (2, 'humbucker'),
                                       (3, 'piezo'),
                                       (4, 'lipstick'),
                                       (5, 'active'),
                                       (6, 'passive'),
                                       (7, 'single coil'),
                                       (8, 'humbucker'),
                                       (9, 'piezo'),
                                       (10, 'lipstick');

SELECT insertInstrumentPickups(ARRAY[
    (1, 5),
    (2, 6),
    (3, 7),
    (4, 8),
    (5, 9),
    (6, 10),
    (7, 1),
    (8, 2),
    (9, 3),
    (10, 4)
    ]::intPair[]);

INSERT INTO buys(productID, customerid)
VALUES  (11, 1),
        (12, 2),
        (13, 3),
        (14, 4),
        (15, 5),
        (16, 6),
        (1, 7),
        (2, 8),
        (3, 9),
        (4, 10);

-- put some products into one of the stores (location) -> the product is still in store
UPDATE Product SET location = 'Prague/Muzeum' WHERE productID = 1;
UPDATE Product SET location = 'Prague/Andel' WHERE productID = 2;
UPDATE Product SET location = 'Brno/Namesti Svobody' WHERE productID = 3;
UPDATE Product SET location = 'Praha/Namesti Republiky' WHERE productID = 4;
UPDATE Product SET location = 'Ostrava/Namesti Svobody' WHERE productID = 5;
UPDATE Product SET location = 'Online' WHERE productID = 6;
UPDATE Product SET location = 'Berlin/Alexanderplatz' WHERE productID = 11;
UPDATE Product SET location = 'Berlin/Kurfurstendamm' WHERE productID = 12;
UPDATE Product SET location = 'Vienna/Stephansplatz' WHERE productID = 13;
UPDATE Product SET location = 'Vienna/Praterstern' WHERE productID = 14;

-- SQL queries =========================================================================================================

-- This query uses a LEFT OUTER JOIN to ensure that all instruments are listed,
-- even those without any associated pickups.
SELECT ip.productid, ip.productID, p.name AS pickup_name
FROM InstrumentProduct ip
         LEFT OUTER JOIN InstrumentPickup ipu ON ip.productid = ipu.instrumentId
         LEFT OUTER JOIN Pickup p ON ipu.pickupid = p.pickupid;

-- This query uses INNER JOIN to combine rows from
-- InstrumentProduct, Product, and Store tables
-- where there are matches in the productID and location columns, respectively.
SELECT ip.productID, ip.type, s.location
FROM InstrumentProduct ip
         INNER JOIN Product p ON ip.productID = p.productID
         INNER JOIN Store s ON p.location = s.location;

-- This query searches for accessories
-- where the type starts with 'Bass' and the ISBN starts with '97'.
SELECT *
FROM Accessory
WHERE type LIKE 'Bass%' AND ISBN LIKE '97%';

-- This query groups instruments by type and counts them,
-- only showing those types where there are more than 5 instruments.
SELECT type, COUNT(*) AS num_instruments
FROM InstrumentProduct
GROUP BY InstrumentProduct.type
HAVING COUNT(*) > 5;

-- This UNION operation combines and removes duplicates,
-- listing all unique productIDs across both tables.
SELECT productID FROM InstrumentProduct
UNION
SELECT productID FROM PASystemProduct;

-- This query uses a nested SELECT to find products
-- that have a isbn that is also listed in the Accessory table.
-- (could be good to eliminate duplicate isbn)
SELECT *
FROM product
WHERE product.isbn IN (SELECT accessory.isbn FROM Accessory); 

-- Trigger
CREATE OR REPLACE FUNCTION check_accessory_type_conflict()
    RETURNS TRIGGER AS $$
DECLARE
    countSameType INT;
BEGIN
    SELECT COUNT(*) INTO countSameType FROM Accessory
    WHERE comeswith = NEW.productID AND type = NEW.type AND ISBN != NEW.ISBN;

    IF countSameType > 0 THEN
        RAISE EXCEPTION 'An accessory of type % is already assigned to instrument model %', NEW.type, NEW.productID;
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_check_accessory_type_conflict
    BEFORE INSERT OR UPDATE OF comeswith, type ON Accessory
    FOR EACH ROW
EXECUTE FUNCTION check_accessory_type_conflict();

-- Transaction
BEGIN TRANSACTION;

-- Variables
DO $$
    DECLARE
        p_ISBN VARCHAR := '223456789';         -- Product ISBN
        p_modelNumber VARCHAR := 'system1';    -- Product model number
        p_userNickname VARCHAR := 'customer1'; -- Customer nickname
        p_userMail VARCHAR := 'cus1@seznam.cz';-- Customer email
        p_purchaseID INTEGER;
        e_productID INTEGER;
        e_userID INTEGER;
        e_customerID INTEGER;
    BEGIN
        -- Check if the product exists and get its productID
        SELECT productID INTO e_productID FROM Product WHERE ISBN = p_ISBN AND modelNumber = p_modelNumber FOR UPDATE;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'Product with ISBN % and model number % does not exist', p_ISBN, p_modelNumber;
        END IF;

        -- Check if the customer exists and get their userID and customerID
        SELECT CU.userID, CU.userid INTO e_userID, e_customerID
        FROM CustomerUser CU
                 JOIN Users U ON CU.userID = U.userID
        WHERE U.nickname = p_userNickname AND U.email = p_userMail FOR UPDATE;
        IF NOT FOUND THEN
            RAISE EXCEPTION 'Customer with nickname % and email % does not exist', p_userNickname, p_userMail;
        END IF;

        -- Insert the purchase record
        INSERT INTO Buys (customerid, productid)
        VALUES (e_customerID, e_productID)
        RETURNING purchaseID INTO p_purchaseID;

        -- Update the customer's premium status to TRUE
        UPDATE CustomerUser SET premiumStatus = TRUE WHERE userid = e_customerID;

        -- Commit the transaction
        COMMIT;
    EXCEPTION
        WHEN OTHERS THEN
            -- Rollback in case of any error
            ROLLBACK;
            RAISE;
    END $$;

-- View
CREATE VIEW CustomerInfo AS
SELECT c.userid, p.userID, p.fullName,
       p.city, p.zip, p.street, p.phoneNumber,
       c.cookies, c.premiumStatus
FROM PrivateInfo p
         JOIN CustomerUser c ON p.userID = c.userID;

-- Indexing
CREATE INDEX idx_AccessoryType ON Accessory(type);

```