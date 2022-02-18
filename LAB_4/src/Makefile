lab4:
		javac lab4.java
tests:

		java lab4 lab4_test1.asm lab4_test1.script > test1.txt
		java lab4 lab4_test2.asm lab4_test2.script > test2.txt
		java lab4 lab4_fib10.asm lab4_fib10.script > fib10.txt
		java lab4 lab4_fib20.asm lab4_fib20.script > fib20.txt

		
		diff -w -B test1.txt lab4_test1.output
		diff -w -B test2.txt lab4_test2.output
		diff -w -B fib10.txt lab4_fib10.output
		diff -w -B fib20.txt lab4_fib20.output

		
clean:
		rm -f *.class
		rm -f *.txt
	
		
								
		
