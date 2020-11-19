# Address-Book

Address book

1 ===> View All  Record 
2 ===> Add New Contact 
3 ===> Add New Relation between two contacts
4 ===> Update Specific Record 
5 ===> Search Specific Record 
6 ===> Delete  Record 

Enter your choice: 
1
+----+------+---------+------------------+-------+-----+------------+---------------+---------------+------------------+
| ID | Name | Surname | Telephone number | Email | Age | Hair color | Relation With | Relation Type | Relation Details |
+----+------+---------+------------------+-------+-----+------------+---------------+---------------+------------------+
| 1  | aaa  | sss     | 222              | eee   | 0   | -          |  --           |  --           |  --              |
+----+------+---------+------------------+-------+-----+------------+---------------+---------------+------------------+
Do you want to continue? Y/N
y

Enter your choice: 
2
Enter the ID: 2
Enter the  Name: bbbb
Enter the  Surname: aaaa
Enter the Telephone number: 4444
Enter the  Email: www@qqq
Enter the  Age: 
Enter the  Hair Color: red
Do you want to continue? Y/N
y

Enter your choice: 
3
Enter the ID of Relation: 1
Enter the ID of Contact 1: 1
Enter the ID of Contact 2 (that is in relation with Contact 1) : 2
Choose the relation Type: 
1 ===> Friends 
2 ===> Family 
3 ===> Acquaintance
1
Enter the number of years of frienship : 3
Do you want to continue? Y/N
y
Enter your choice: 
1
+----+------+---------+------------------+---------+-----+------------+---------------+---------------+------------------+
| ID | Name | Surname | Telephone number | Email   | Age | Hair color | Relation With | Relation Type | Relation Details |
+----+------+---------+------------------+---------+-----+------------+---------------+---------------+------------------+
| 2  | bbbb | aaaa    | 4444             | www@qqq | 0   | red        |  --           |  --           |  --              |
| 1  | aaa  | sss     | 222              | eee     | 1   | -          | aaaa          | Friends       | 3                |
+----+------+---------+------------------+---------+-----+------------+---------------+---------------+------------------+
Do you want to continue? Y/N




/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

Address book
Build a java application to add, display and remove contacts from an address book.
The address book has mandatory fields:
• Name
• Surname
• Telephone number
• Email
It can have also optional fields:
• Age
• Hair color
Each contact can belong to 3 different categories:
• Friends
• Family
• Acquaintance
Each category has a name. The “family” category has a description indicating the family’s relationship
(parent, granparent, son/daughter, aunt/uncle). The “friends”category has a field indicating the number
of friendship’s years.
The user is able, throught the command line, to add, display, remove or edit the contacts and each one
of their properties. The contacts and the related information are saved in 1 (or more) txt file(s). When all
the contacts are displayed, sort them alphabetically by surname.
