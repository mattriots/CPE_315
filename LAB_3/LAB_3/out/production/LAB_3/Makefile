lab3:
		javac lab3.java
tests:

		java lab3 sum_10.asm sum_10.script > test1.txt
		java lab3 lab3_test3.asm lab3_test3.script > test2.txt
		java lab3 lab3_fib.asm lab3_fib.script > test3.txt

		
		diff -w -B test1.txt sum_10.output
		diff -w -B test2.txt lab3_test3.output
		diff -w -B test3.txt lab3_fib.output

		
clean:
		rm -f *.class
		rm -f *.txt
	
		
								
		
