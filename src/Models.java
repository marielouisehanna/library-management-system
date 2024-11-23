import java.util.Date;

public class Models {

    // Librarian Model
    public static class Librarian {
        private int librarianID;
        private String name;
        private String contactInformation;
        private Date hireDate;

        public Librarian(int librarianID, String name, String contactInformation, Date hireDate) {
            this.librarianID = librarianID;
            this.name = name;
            this.contactInformation = contactInformation;
            this.hireDate = hireDate;
        }

        public int getLibrarianID() { return librarianID; }
        public void setLibrarianID(int librarianID) { this.librarianID = librarianID; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getContactInformation() { return contactInformation; }
        public void setContactInformation(String contactInformation) { this.contactInformation = contactInformation; }
        public Date getHireDate() { return hireDate; }
        public void setHireDate(Date hireDate) { this.hireDate = hireDate; }
    }

    // Member Model
    public static class Member {
        private int memberID;
        private String name;
        private String contactInformation;
        private Date membershipDate;
        private String memberType;
        private int maxBooksAllowed;
        private int maxDurationAllowed;

        public Member(int memberID, String name, String contactInformation, Date membershipDate, String memberType, int maxBooksAllowed, int maxDurationAllowed) {
            this.memberID = memberID;
            this.name = name;
            this.contactInformation = contactInformation;
            this.membershipDate = membershipDate;
            this.memberType = memberType;
            this.maxBooksAllowed = maxBooksAllowed;
            this.maxDurationAllowed = maxDurationAllowed;
        }

        public int getMemberID() { return memberID; }
        public void setMemberID(int memberID) { this.memberID = memberID; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public String getContactInformation() { return contactInformation; }
        public void setContactInformation(String contactInformation) { this.contactInformation = contactInformation; }
        public Date getMembershipDate() { return membershipDate; }
        public void setMembershipDate(Date membershipDate) { this.membershipDate = membershipDate; }
        public String getMemberType() { return memberType; }
        public void setMemberType(String memberType) { this.memberType = memberType; }
        public int getMaxBooksAllowed() { return maxBooksAllowed; }
        public void setMaxBooksAllowed(int maxBooksAllowed) { this.maxBooksAllowed = maxBooksAllowed; }
        public int getMaxDurationAllowed() { return maxDurationAllowed; }
        public void setMaxDurationAllowed(int maxDurationAllowed) { this.maxDurationAllowed = maxDurationAllowed; }
    }

    // Book Model
    public static class Book {
        private int bookID;
        private String isbn;
        private String title;
        private String author;
        private String genre;
        private String availabilityStatus;
        private Integer issuedBy; // Nullable
        private Date issueDate;   // Nullable
        private Date dueDate;     // Nullable
        private Integer issuedTo; // Nullable
        private Integer duration; // Nullable

        public Book(int bookID, String isbn, String title, String author, String genre, String availabilityStatus,
                    Integer issuedBy, Date issueDate, Date dueDate, Integer issuedTo, Integer duration) {
            this.bookID = bookID;
            this.isbn = isbn;
            this.title = title;
            this.author = author;
            this.genre = genre;
            this.availabilityStatus = availabilityStatus;
            this.issuedBy = issuedBy;
            this.issueDate = issueDate;
            this.dueDate = dueDate;
            this.issuedTo = issuedTo;
            this.duration = duration;
        }

        public int getBookID() { return bookID; }
        public void setBookID(int bookID) { this.bookID = bookID; }
        public String getIsbn() { return isbn; }
        public void setIsbn(String isbn) { this.isbn = isbn; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getAuthor() { return author; }
        public void setAuthor(String author) { this.author = author; }
        public String getGenre() { return genre; }
        public void setGenre(String genre) { this.genre = genre; }
        public String getAvailabilityStatus() { return availabilityStatus; }
        public void setAvailabilityStatus(String availabilityStatus) { this.availabilityStatus = availabilityStatus; }
        public Integer getIssuedBy() { return issuedBy; }
        public void setIssuedBy(Integer issuedBy) { this.issuedBy = issuedBy; }
        public Date getIssueDate() { return issueDate; }
        public void setIssueDate(Date issueDate) { this.issueDate = issueDate; }
        public Date getDueDate() { return dueDate; }
        public void setDueDate(Date dueDate) { this.dueDate = dueDate; }
        public Integer getIssuedTo() { return issuedTo; }
        public void setIssuedTo(Integer issuedTo) { this.issuedTo = issuedTo; }
        public Integer getDuration() { return duration; }
        public void setDuration(Integer duration) { this.duration = duration; }
    }

    // Transaction Model
    public static class Transaction {
        private int transactionID;
        private int memberID;
        private int bookID;
        private Date borrowDate;
        private Date returnDate; // Nullable
        private int duration;

        public Transaction(int transactionID, int memberID, int bookID, Date borrowDate, Date returnDate, int duration) {
            this.transactionID = transactionID;
            this.memberID = memberID;
            this.bookID = bookID;
            this.borrowDate = borrowDate;
            this.returnDate = returnDate;
            this.duration = duration;
        }

        public int getTransactionID() { return transactionID; }
        public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
        public int getMemberID() { return memberID; }
        public void setMemberID(int memberID) { this.memberID = memberID; }
        public int getBookID() { return bookID; }
        public void setBookID(int bookID) { this.bookID = bookID; }
        public Date getBorrowDate() { return borrowDate; }
        public void setBorrowDate(Date borrowDate) { this.borrowDate = borrowDate; }
        public Date getReturnDate() { return returnDate; }
        public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
        public int getDuration() { return duration; }
        public void setDuration(int duration) { this.duration = duration; }
    }

    // Fine Model
    public static class Fine {
        private int fineID;
        private int transactionID;
        private double amount;
        private String paymentStatus;
        private Date fineDate;
        private Date paidDate; // Nullable
        private boolean isOverdue;

        public Fine(int fineID, int transactionID, double amount, String paymentStatus, Date fineDate, Date paidDate, boolean isOverdue) {
            this.fineID = fineID;
            this.transactionID = transactionID;
            this.amount = amount;
            this.paymentStatus = paymentStatus;
            this.fineDate = fineDate;
            this.paidDate = paidDate;
            this.isOverdue = isOverdue;
        }

        public int getFineID() { return fineID; }
        public void setFineID(int fineID) { this.fineID = fineID; }
        public int getTransactionID() { return transactionID; }
        public void setTransactionID(int transactionID) { this.transactionID = transactionID; }
        public double getAmount() { return amount; }
        public void setAmount(double amount) { this.amount = amount; }
        public String getPaymentStatus() { return paymentStatus; }
        public void setPaymentStatus(String paymentStatus) { this.paymentStatus = paymentStatus; }
        public Date getFineDate() { return fineDate; }
        public void setFineDate(Date fineDate) { this.fineDate = fineDate; }
        public Date getPaidDate() { return paidDate; }
        public void setPaidDate(Date paidDate) { this.paidDate = paidDate; }
        public boolean isOverdue() { return isOverdue; }
        public void setOverdue(boolean overdue) { isOverdue = overdue; }
    }
}
