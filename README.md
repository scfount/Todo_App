# Homework 9

Steven Fountain, Sruti Munukutla, Bo Mendez  
CS5004  
Spring 2021

- We used both the factory and builder design patterns for two main reasons. The program called for heavy command line parsing and building Todo objects with a single required field and many optional fields, some of which have a default value.

## Factory Pattern
- The factory pattern we used primarily in the parsing of the command line arguments in the commands package. This gave us the ability to iterate over the provided commands and look for specific strings, then return the appropriate object.
- We implemented this pattern to find the four major types - csv, add, complete, display
  Then we used the pattern again for add and display, as those commands have relevant sub-commands that need to be found:  
  CmdFactory, AddFactory, DisplayFactory

## Builder Pattern
- The Builder pattern we used for building a Todo object. This was particularly useful when parsing the command line arguments with an --add-todo command present. Combining the builder pattern with the factory pattern allowed us to build a Todo based on what the user provided and ensured the required text field was present.
- In TodoHandler, we called the Builder class to build our Todo objects after reading the todo data from the CSV file. We added conditionals to determine whether fields of the Todo were present.
