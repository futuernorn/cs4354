Program can be run on Linux with the following command:
 java -Dfile.encoding=UTF-8 -classpath $PROGRAM_DIR/bin simpleLib.Driver

Login Information:

User Login:
username: User
password: 1234

Librarian Login:
username: Librarian
password: 2345

Administrator Login:
username: Admin
password: 3456

========================================================================
Question 1:
(c) Possible Patterns:
Factory pattern could be used when adding users or documents.
Observer pattern may be utilized to monitor and direct all keyboard input
Singleton pattern - implemented for "LibrarySystem" class, as we would only have one instance of this class with the current implementation.


Question 2:
Interface pattern was used to implement search type expansionability. This was done via the "DocumentSearch" interface and the classes that inherited from this.