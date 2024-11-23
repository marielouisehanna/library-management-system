from flask import Flask, request, jsonify
from flask_sqlalchemy import SQLAlchemy
from datetime import datetime

app = Flask(__name__)

# Configuration for SQL Server
app.config['SQLALCHEMY_DATABASE_URI'] = 'mssql+pyodbc://<USERNAME>:<PASSWORD>@<SERVER>/<DATABASE>?driver=ODBC+Driver+17+for+SQL+Server'
app.config['SQLALCHEMY_TRACK_MODIFICATIONS'] = False

db = SQLAlchemy(app)

# Models
class Librarian(db.Model):
    __tablename__ = 'Librarians'
    LibrarianID = db.Column(db.Integer, primary_key=True)
    Name = db.Column(db.String(255), nullable=False)
    ContactInformation = db.Column(db.String(255), nullable=False)
    HireDate = db.Column(db.Date, nullable=False)


class Member(db.Model):
    __tablename__ = 'Members'
    MemberID = db.Column(db.Integer, primary_key=True)
    Name = db.Column(db.String(255), nullable=False)
    ContactInformation = db.Column(db.String(255), nullable=False)
    MembershipDate = db.Column(db.Date, nullable=False)
    MemberType = db.Column(db.String(50), nullable=False)
    MaxBooksAllowed = db.Column(db.Integer, nullable=False)
    MaxDurationAllowed = db.Column(db.Integer, nullable=False)


class Book(db.Model):
    __tablename__ = 'Books'
    BookID = db.Column(db.Integer, primary_key=True)
    ISBN = db.Column(db.String(20), unique=True, nullable=False)
    Title = db.Column(db.String(255), nullable=False)
    Author = db.Column(db.String(255), nullable=False)
    Genre = db.Column(db.String(100), nullable=False)
    AvailabilityStatus = db.Column(db.String(50), nullable=False)
    IssuedBy = db.Column(db.Integer, db.ForeignKey('Librarians.LibrarianID'), nullable=True)
    IssueDate = db.Column(db.Date, nullable=True)
    DueDate = db.Column(db.Date, nullable=True)
    IssuedTo = db.Column(db.Integer, db.ForeignKey('Members.MemberID'), nullable=True)
    Duration = db.Column(db.Integer, nullable=True)


class Transaction(db.Model):
    __tablename__ = 'Transactions'
    TransactionID = db.Column(db.Integer, primary_key=True)
    MemberID = db.Column(db.Integer, db.ForeignKey('Members.MemberID'), nullable=False)
    BookID = db.Column(db.Integer, db.ForeignKey('Books.BookID'), nullable=False)
    BorrowDate = db.Column(db.Date, nullable=False)
    ReturnDate = db.Column(db.Date, nullable=True)
    Duration = db.Column(db.Integer, nullable=False)


class Fine(db.Model):
    __tablename__ = 'Fines'
    FineID = db.Column(db.Integer, primary_key=True)
    TransactionID = db.Column(db.Integer, db.ForeignKey('Transactions.TransactionID'), nullable=False)
    Amount = db.Column(db.Numeric(10, 2), nullable=False)
    PaymentStatus = db.Column(db.String(50), nullable=False)
    FineDate = db.Column(db.Date, nullable=False)
    PaidDate = db.Column(db.Date, nullable=True)
    IsOverdue = db.Column(db.Boolean, nullable=False)


# Routes
@app.route('/librarians', methods=['GET', 'POST'])
def manage_librarians():
    if request.method == 'GET':
        librarians = Librarian.query.all()
        return jsonify([{
            'LibrarianID': lib.LibrarianID,
            'Name': lib.Name,
            'ContactInformation': lib.ContactInformation,
            'HireDate': lib.HireDate
        } for lib in librarians])
    elif request.method == 'POST':
        data = request.json
        librarian = Librarian(
            Name=data['Name'],
            ContactInformation=data['ContactInformation'],
            HireDate=datetime.strptime(data['HireDate'], '%Y-%m-%d')
        )
        db.session.add(librarian)
        db.session.commit()
        return jsonify({'message': 'Librarian added successfully'}), 201


@app.route('/members', methods=['GET', 'POST'])
def manage_members():
    if request.method == 'GET':
        members = Member.query.all()
        return jsonify([{
            'MemberID': mem.MemberID,
            'Name': mem.Name,
            'ContactInformation': mem.ContactInformation,
            'MembershipDate': mem.MembershipDate,
            'MemberType': mem.MemberType,
            'MaxBooksAllowed': mem.MaxBooksAllowed,
            'MaxDurationAllowed': mem.MaxDurationAllowed
        } for mem in members])
    elif request.method == 'POST':
        data = request.json
        member = Member(
            Name=data['Name'],
            ContactInformation=data['ContactInformation'],
            MembershipDate=datetime.strptime(data['MembershipDate'], '%Y-%m-%d'),
            MemberType=data['MemberType'],
            MaxBooksAllowed=data['MaxBooksAllowed'],
            MaxDurationAllowed=data['MaxDurationAllowed']
        )
        db.session.add(member)
        db.session.commit()
        return jsonify({'message': 'Member added successfully'}), 201


@app.route('/books', methods=['GET', 'POST'])
def manage_books():
    if request.method == 'GET':
        books = Book.query.all()
        return jsonify([{
            'BookID': book.BookID,
            'ISBN': book.ISBN,
            'Title': book.Title,
            'Author': book.Author,
            'Genre': book.Genre,
            'AvailabilityStatus': book.AvailabilityStatus,
            'IssuedBy': book.IssuedBy,
            'IssueDate': book.IssueDate,
            'DueDate': book.DueDate,
            'IssuedTo': book.IssuedTo,
            'Duration': book.Duration
        } for book in books])
    elif request.method == 'POST':
        data = request.json
        book = Book(
            ISBN=data['ISBN'],
            Title=data['Title'],
            Author=data['Author'],
            Genre=data['Genre'],
            AvailabilityStatus=data['AvailabilityStatus']
        )
        db.session.add(book)
        db.session.commit()
        return jsonify({'message': 'Book added successfully'}), 201


@app.route('/transactions', methods=['GET', 'POST'])
def manage_transactions():
    if request.method == 'GET':
        transactions = Transaction.query.all()
        return jsonify([{
            'TransactionID': txn.TransactionID,
            'MemberID': txn.MemberID,
            'BookID': txn.BookID,
            'BorrowDate': txn.BorrowDate,
            'ReturnDate': txn.ReturnDate,
            'Duration': txn.Duration
        } for txn in transactions])
    elif request.method == 'POST':
        data = request.json
        transaction = Transaction(
            MemberID=data['MemberID'],
            BookID=data['BookID'],
            BorrowDate=datetime.strptime(data['BorrowDate'], '%Y-%m-%d'),
            Duration=data['Duration']
        )
        db.session.add(transaction)
        db.session.commit()
        return jsonify({'message': 'Transaction recorded successfully'}), 201


@app.route('/fines', methods=['GET', 'POST'])
def manage_fines():
    if request.method == 'GET':
        fines = Fine.query.all()
        return jsonify([{
            'FineID': fine.FineID,
            'TransactionID': fine.TransactionID,
            'Amount': float(fine.Amount),
            'PaymentStatus': fine.PaymentStatus,
            'FineDate': fine.FineDate,
            'PaidDate': fine.PaidDate,
            'IsOverdue': fine.IsOverdue
        } for fine in fines])
    elif request.method == 'POST':
        data = request.json
        fine = Fine(
            TransactionID=data['TransactionID'],
            Amount=data['Amount'],
            PaymentStatus=data['PaymentStatus'],
            FineDate=datetime.strptime(data['FineDate'], '%Y-%m-%d'),
            PaidDate=datetime.strptime(data['PaidDate'], '%Y-%m-%d') if data.get('PaidDate') else None,
            IsOverdue=data['IsOverdue']
        )
        db.session.add(fine)
        db.session.commit()
        return jsonify({'message': 'Fine recorded successfully'}), 201


if __name__ == '__main__':
    db.create_all()
    app.run(debug=True)
