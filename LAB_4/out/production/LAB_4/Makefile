lab4:
		javac lab4.java
tests:

		java lab4 sum_10.asm sum_10.script > test1.txt
		java lab4 lab4_test3.asm lab4_test3.script > test2.txt
		java lab4 lab4_fib.asm lab4_fib.script > test3.txt

		
		diff -w -B test1.txt sum_10.output
		diff -w -B test2.txt lab4_test3.output
		diff -w -B test3.txt lab4_fib.output

		
clean:
		rm -f *.class
		rm -f *.txt
	
		
								
		
