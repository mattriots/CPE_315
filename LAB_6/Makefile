lab6:
		javac lab6.java
tests:

		java lab6 mem_stream.1 > test1.txt
		java lab6 mem_stream.2 > test2.txt

		diff -w -B test1.txt mem_stream1.out
		diff -w -B test2.txt mem_stream2.out

clean:
		rm -f *.class
		rm -f *.txt
