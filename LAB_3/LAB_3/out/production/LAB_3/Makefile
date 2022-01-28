lab2:
		javac lab2.java
tests:
		
		
		java lab2 test1.asm > test1.txt
		java lab2 test2.asm > test2.txt
		java lab2 test3.asm > test3.txt
		java lab2 test4.asm > test4.txt
		
		diff -w -B test1.txt test1.output
		diff -w -B test2.txt test2.output
		diff -w -B test3.txt test3.output
		diff -w -B test4.txt test4.output
		
clean:
		rm -f *.class
		rm -f *.txt
	
		
								
		
