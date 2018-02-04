# FilePacker

How to use?

* Use command line / git bash / .bat file

Code (run in folder contains FilePacker.jar):

java -Dfile.encoding=UTF-8 -Xmx1G -Xms1G -jar FilePacker.jar --input "" --output ""

### --input

Use to declare sources folder which you want to pack together

Each directory separates by a comma

### --output

The directory which you want to host packed folders

Each directory separates by a comma

## Example

java -Dfile.encoding=UTF-8 -Xmx1G -Xms1G -jar FilePacker.jar --input "C:/fd1,C:/fd2,C:/fd3,C:/fd4,C:/fd5" --output "C:/fd-packed"
