Prepared by: Ashish Karki 

Problem Source: http://codingdojo.org/cgi-bin/index.pl?KataBankOCR 

My Project "KataBankOCR" structure:

The project is implemented in Java using eclipse. The folder can be opened as a Java Project from eclipse. FileParser has a main method and Unit tests can be run for other two source files.
The input file name is fed as method/s argument rather program argument (say from command line) for now.

1) The project has two separate packages: one for source code and other for unit tests.
2) The mainPackage has three files:
	2.1) AccountNumberValidator: validates the checksum for actual (numerical) account numbers (User story 2)
	2.2) FileParser: responsible for parsing OCR input into actual account numbers (that is stored in a list) (User story 1)
	2.3) OutputFileWriter: responsible for calling file parser, validating numbers and writing output to a file (Use story 3)

3) The testPackage has two tests:
	3.1) AccountNumberValidatorTest: test AccountNumberValidator method with values from the problem source
	3.2) OutputFileWriterTest: test OutputFileWriter method by mocking file parser and checking the output from output-file

4) Note, FileParser class has a main method to display its working but a similar Unit test could have been implemented for that too.

I will be happy to explain the project and its logic in more details during our discussion.



