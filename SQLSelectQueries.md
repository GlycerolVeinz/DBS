# SQL querries training
## Book Reservation System
Book (ISBN, Title, Author, Genre, PublicationYear)
Member (MemberID, FirstName, LastName, Email, PhoneNumber, JoinDate)
Loan (LoanID, ISBN, MemberID, LoanDate, ReturnDate)
Reservation (ReservationID, ISBN, MemberID, ReservationDate, Status)

1. Find the titles and authors of books that have never been loaned.

1.
SELECT b.Title, b.Author
FROM Book AS b
WHERE NOT EXISTS (
	SELECT *
	FROM Loan AS l
	JOIN Book ON l.ISBN = b.ISBN;
);

1. Corrected:
SELECT b.Title, b.Author
FROM Book AS b
WHERE NOT EXISTS (
    SELECT 1
    FROM Loan AS l
    WHERE l.ISBN = b.ISBN
);

1. Problems:
	- Need to select only 1 element for NOT EXISTS to match something
	- No semmicollon in inner querry



2. Find the first and last names of members who have reserved at least one book that has never been loaned.

2. 
SELECT m.FirstName, m.LastName 
FROM Member m
LEFT JOIN Reservation r
	ON m.MemberID = r.MemberID
	WHERE r.ISBN NOT EXISTS (
		SELECT ISBN
		FROM Loan l
		WHERE l.ISBN = r.ISBN
		);

2. Corrected:
SELECT DISTINCT m.FirstName, m.LastName 
FROM Member m
JOIN Reservation r ON m.MemberID = r.MemberID
WHERE NOT EXISTS (
    SELECT 1
    FROM Loan l
    WHERE l.ISBN = r.ISBN
);

2. Problems:
	- Need to select only 1 element for NOT EXISTS to match something
	- Could have used NOT IN, the NOT EXISTS clause should relate to row, not collumn value 


3. List all genres with the total number of books in each genre, sorted by the number of books in descending order.
 
3.
SELECT b.Genre, COUNT(*) AS TotalBooks 
FROM Book b
GROUP BY b.Genre
ORDER BY TotalBooks DESC;

3. Correct!


4. Find the average number of loans per member.

4.
SELECT AVG(LoansPerMember)
FROM (
	SELECT COUNT(*) AS LoansPerMember 
	FROM Loan l
	GROUP BY l.MemberID
	)


4. Corrected:
SELECT AVG(LoansPerMember)
FROM (
    SELECT COUNT(*) AS LoansPerMember 
    FROM Loan l
    GROUP BY l.MemberID
) AS LoanCounts;

4. Problems:
	- Need to add an alias for the clause


 
5. Find the members (first and last names) who have borrowed more than 5 books.

5.
SELECT m.FirstName, m.LastName, COUNT(l.LoanID) AS LoansPerMember
FROM Member m
LEFT JOIN Loan l ON m.MemberID = l.MemberID
GROUP BY m.FirstName, m.LastName
WHERE LoansPerMember > 5;

5. Corrected:
SELECT m.FirstName, m.LastName, COUNT(l.LoanID) AS LoansPerMember
FROM Member m
LEFT JOIN Loan l ON m.MemberID = l.MemberID
GROUP BY m.FirstName, m.LastName
HAVING LoansPerMember > 5;

5. Problems:
	- WHERE should be before GROUP BY clause
	- use HAVING to filter groups


6. List all books (ISBN, Title) that were published before the year 2000 and have never been reserved.

6.
SELECT b.ISBN, b.Title
FROM Book b
LEFT JOIN Reservation r ON b.ISBN = r.ISBN
WHERE r.ReservationID IS NULL AND 
WHERE PublicationYear < 2000;  

6. Corrected:
SELECT b.ISBN, b.Title
FROM Book b
LEFT JOIN Reservation r ON b.ISBN = r.ISBN
WHERE r.ReservationID IS NULL
AND PublicationYear < 2000;

6. Problems:
	- Should only use 1 WHERE clause, if you want to expand it, use only AND without adding next WHERE


 
7. Find the email addresses of members who have borrowed books of the genre 'Science Fiction'.

7.
SELECT m.Email 
FROM Member m 
JOIN Loan l on m.MemberID = l.MemberID
JOIN Book b on b.ISBN = l.ISBN
WHERE b.Genre = 'Science Fiction';

7. Correct!



8. Find the first and last names of members who have reserved books in the 'Fantasy' genre but have never borrowed any books of the 'Fantasy' genre.
 
8.
SELECT m.FirstName, m.LastName
FROM Member m 
JOIN Reservation r ON r.MemberID = m.MemberID
WHERE r.ISBN IN (
	SELECT b.ISBN
	FROM Book b
	WHERE b.Genre = 'Fantasy'
)
AND WHERE NOT EXISTS (
	SELECT 1
	FROM Loan l
	WHERE l.ISBN = r.ISBN
)

8. Corrected:
SELECT m.FirstName, m.LastName
FROM Member m 
JOIN Reservation r ON r.MemberID = m.MemberID
WHERE r.ISBN IN (
	SELECT b.ISBN
	FROM Book b
	WHERE b.Genre = 'Fantasy'
)
AND NOT EXISTS (
	SELECT 1
	FROM Loan l
	WHERE l.ISBN = r.ISBN
);

8. Problems:
	- Same problem as 6.



9. List all members who joined the library in the last year and have already borrowed more than 3 books.

SELECT *, COUNT(*) AS LoansPerMember
FROM Member m 
JOIN Loan l ON m.MemberID = l.MemberID
WHERE m.JoinDate > (CURRENT_DATE(year) - 1)
GROUP BY m.MemberID
HAVING LoansPerMember > 3;

9. Corrected:
SELECT m.*, COUNT(*) AS LoansPerMember
FROM Member m 
JOIN Loan l ON m.MemberID = l.MemberID
WHERE m.JoinDate > (CURRENT_DATE - INTERVAL '1 year')
GROUP BY m.MemberID
HAVING COUNT(*) > 3;

9. Problems:
	- specify wheere from do you take *
	- use correct way of calculating dates
	- do not use aliases for count on the came level, they are specified later in interpretation


10. Find the total number of reservations made for each book (ISBN, Title) and only include books with more than 5 reservations.

10.
SELECT b.ISBN, b.Title, COUNT(*) AS ReservationsPerBook
FROM Book b
JOIN Reservation r ON r.ISBN = b.ISBN
GROUP BY b.ISBN, b.Title
HAVING ReservationsPerBook > 5;

10. Corrected:
SELECT b.ISBN, b.Title, COUNT(*) AS ReservationsPerBook
FROM Book b
JOIN Reservation r ON r.ISBN = b.ISBN
GROUP BY b.ISBN, b.Title
HAVING COUNT(*) > 5;

10. Problems:
	- same problem as in 9.


## University System

Student (StudentID, FirstName, LastName, Email, Major, EnrollmentYear)
Course (CourseID, CourseName, Credits, Department)
Enrollment (EnrollmentID, StudentID, CourseID, Semester, Grade)
Professor (ProfessorID, FirstName, LastName, Email, Department)
Teaching (TeachingID, ProfessorID, CourseID, Semester)

1. Find the first and last names of students who are majoring in 'Computer Science'.

1.
SELECT s.FirstName, s.LastName
FROM Student s
WHERE s.Major = 'Computer Science';



2. List all courses (CourseID, CourseName) that have never been taught by any professor.

2.
SELECT c.CourseID, c.CourseName
FROM Course c
LEFT JOIN Teaching t ON t.CourseID = c.CourseID
WHERE ProfessorID IS NULL;



3. Find the email addresses of professors who teach in the 'Mathematics' department.

3.
SELECT *
FROM Professor
WHERE Department = 'Mathematics';



4. List the names of students who have enrolled in the course 'Database Systems' at least twice.





5. Find the number of courses each student has enrolled in, sorted by the number of courses in descending order.

6. Find the average grade for the course 'Algorithms' for each semester it has been offered.

7. List the first and last names of professors who have never taught a course.

8. Find the departments that offer more than 10 courses.

9. List the courses (CourseID, CourseName) and the number of students enrolled in each, and only include courses with more than 5 students enrolled.

10. Find the first and last names of students who have taken all courses taught by 'Professor Smith'.



## Online Marketplace System

User (UserID, FirstName, LastName, Email, JoinDate)
Product (ProductID, ProductName, Category, Price, StockQuantity)
Order (OrderID, UserID, OrderDate, TotalAmount)
OrderItem (OrderItemID, OrderID, ProductID, Quantity, ItemPrice)
Review (ReviewID, ProductID, UserID, Rating, ReviewDate, Comment)
Category (CategoryID, CategoryName, ParentCategoryID)

1. Find the names and emails of users who have placed an order for more than $500 in total and have joined the marketplace in the last two years.

1.
SELECT u.FirstName, u.LastName, u.Email
FROM User u
WHERE EXISTS (
	SELECT 1
	FROM Order o
	WHERE o.UserID = u.UserID
	AND TotalAmount > 500)
AND JoinDate > CURRENT_DATE - INTERVAL '2 year';



2. List the products (ProductID, ProductName) that have never received a rating higher than 3.

2.
SELECT p.ProductID, p.ProductName
FROM Product p
LEFT JOIN Review r ON p.ProductID = r.ProductID
GROUP BY p.ProductID, p.ProductName
HAVING MAX(r.Rating) <= 3 OR MAX(r.Rating) IS NULL;  

3. Find the users (FirstName, LastName) who have reviewed more than 10 different products but have never rated any product below 4.

3.
SELECT u.FirstName, u.LastName
FROM User u
JOIN Review r ON u.UserID = r.UserID
GROUP BY u.FirstName, u.LastName
HAVING MIN(r.Rating) >= 4
AND COUNT(DISTINCT r.ProductID) > 10;

4. List the categories (CategoryID, CategoryName) along with the total number of products in each category, including subcategories.

4. 
I do not know how to do that, lets learn:
	- First, flatten all subcategories thus defining hierarchy
		- Define root categories (main categories)
		- Recursively define subcategories

	WITH RECURSIVE CategoryHierarchy AS (
    -- Anchor member: Select the root categories (those with no parent)
    SELECT CategoryID, CategoryName, ParentCategoryID
    FROM Category
    WHERE ParentCategoryID IS NULL

    -- UNION : Joins together 2 or more SELECT results,
    -- ALL : Lists all rows, even if they repeat
    UNION ALL

    -- Recursive member: Select the subcategories and build the hierarchy
    SELECT c.CategoryID, c.CategoryName, c.ParentCategoryID
    FROM Category c
    JOIN CategoryHierarchy ch ON c.ParentCategoryID = ch.CategoryID)

    - Second, join with products and count the ammount:
    SELECT ch.CategoryID, ch.CategoryName, COUNT(p.ProductID) AS TotalProducts
	FROM CategoryHierarchy ch
	LEFT JOIN Product p ON ch.CategoryID = p.Category
	GROUP BY ch.CategoryID, ch.CategoryName;



5. Find the top 5 users who have spent the most money on the marketplace, along with the total amount they have spent.

6. List the products (ProductID, ProductName) that are currently out of stock but have been ordered at least 10 times.

7. Find the average rating for each product category, including subcategories, and list the categories with an average rating below 3.5.

8. Identify the users who have never placed an order but have written reviews.

9. List the most frequently ordered product for each month of the current year.

10. Find the users (FirstName, LastName) who have ordered all products in the 'Electronics' category.

