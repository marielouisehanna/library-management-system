-- Table: Librarians
CREATE TABLE Librarians (
    LibrarianID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255) NOT NULL,
    ContactInformation NVARCHAR(255) NOT NULL,
    HireDate DATE NOT NULL
);

-- Table: Members
CREATE TABLE Members (
    MemberID INT PRIMARY KEY IDENTITY(1,1),
    Name NVARCHAR(255) NOT NULL,
    ContactInformation NVARCHAR(255) NOT NULL,
    MembershipDate DATE NOT NULL,
    MemberType NVARCHAR(50) CHECK (MemberType IN ('Student', 'Faculty')) NOT NULL,
    MaxBooksAllowed INT NOT NULL,
    MaxDurationAllowed INT NOT NULL
);

-- Table: Books
CREATE TABLE Books (
    BookID INT PRIMARY KEY IDENTITY(1,1),
    ISBN NVARCHAR(20) NOT NULL UNIQUE,
    Title NVARCHAR(255) NOT NULL,
    Author NVARCHAR(255) NOT NULL,
    Genre NVARCHAR(100) NOT NULL,
    AvailabilityStatus NVARCHAR(50) CHECK (AvailabilityStatus IN ('Available', 'Checked Out')) NOT NULL,
    IssuedBy INT NULL,
    IssueDate DATE NULL,
    DueDate DATE NULL,
    IssuedTo INT NULL,
    Duration INT NULL,
    FOREIGN KEY (IssuedBy) REFERENCES Librarians(LibrarianID),
    FOREIGN KEY (IssuedTo) REFERENCES Members(MemberID)
);

-- Table: Transactions
CREATE TABLE Transactions (
    TransactionID INT PRIMARY KEY IDENTITY(1,1),
    MemberID INT NOT NULL,
    BookID INT NOT NULL,
    BorrowDate DATE NOT NULL,
    ReturnDate DATE NULL,
    Duration INT NOT NULL,
    FOREIGN KEY (MemberID) REFERENCES Members(MemberID),
    FOREIGN KEY (BookID) REFERENCES Books(BookID)
);

-- Table: Fines
CREATE TABLE Fines (
    FineID INT PRIMARY KEY IDENTITY(1,1),
    TransactionID INT NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentStatus NVARCHAR(50) CHECK (PaymentStatus IN ('Paid', 'Unpaid')) NOT NULL,
    FineDate DATE NOT NULL,
    PaidDate DATE NULL,
    IsOverdue BIT NOT NULL,
    FOREIGN KEY (TransactionID) REFERENCES Transactions(TransactionID)
);
