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

### --placeholders

Placeholders helps you to replace texts in output files

Each placeholder separates by a vertical bar

In each placeholder, there is a placeholder text and
a directory of the file or a URL which you want to replace from, they 
separates by a comma

Example:

> --placeholders "{text1},C:/texts/1.txt|{text2},C:/texts/2.txt"

> --placeholders "%a%,C:/a.html"

> --placeholders "{{content}},http://mywebsite.com/"

## Example

> java -Dfile.encoding=UTF-8 -Xmx1G -Xms1G -jar FilePacker.jar --input "C:/fd1,C:/fd2,C:/fd3,C:/fd4,C:/fd5" --output "C:/fd-packed"

> java -Dfile.encoding=UTF-8 -Xmx1G -Xms1G -jar FilePacker.jar --placeholders "{text},https://google.com/|{a},C:/w.txt" "--input "C:/fd1,C:/fd2,C:/fd3,C:/fd4,C:/fd5" --output "C:/fd-packed"