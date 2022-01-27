 # Name:  Matt DePauw, Sing Tai
 # Section:  7 

 # Description:  Raise x to the power of y.

 # Java  

#    public static int exponent1(int x, int y) {
#        int result = x;
#        int newx = x;
#        for (int i = y; i > 1; i--) {
#            for (int j = x; j > 1; j--) {
#                result += newx;
#            }
#            newx = result;
#        }
#        return result;
#    }
# }
#

# declare global so programmer can see actual addresses.
.globl welcome
.globl prompt
.globl sumText

#  Data Area (this area contains strings to be displayed during the program)
.data

welcome:
	.asciiz " This program returns x to the power of y \n\n"

prompt:
	.asciiz " Enter an integer: "

sumText: 
	.asciiz " \n Result = "

#Text Area (i.e. instructions)
.text

main:

	# Display the welcome message (load 4 into $v0 to display)
	ori     $v0, $0, 4			

	# This generates the starting address for the welcome message.
	# (assumes the register first contains 0).
	lui     $a0, 0x1001
	syscall

	# Display prompt
	ori     $v0, $0, 4			
	
	# This is the starting address of the prompt (notice the
	# different address from the welcome message)
	lui     $a0, 0x1001
	ori     $a0, $a0,0x2D
	syscall

	# Read 1st integer from the user (5 is loaded into $v0, then a syscall)
	ori     $v0, $0, 5
	syscall

	# Store first int (x) in $s0 <-------- x
	addu    $s0, $v0, $0
	
	# Display prompt (4 is loaded into $v0 to display)
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x2D
	syscall

	# Read 2nd integer 
	ori	$v0, $0, 5			
	syscall
	# $v0 now has the value of the second integer
	
	# Store second int (y) in $s1 <-------- y
	addu    $s1, $v0, $0 

	# Registers #
	# $s0 = x
	# $s1 = y
	# $s2 = i
	# $s3 = j
	# $t0 = result
	# $t1 = newx
	# $t2 = 1 <- used for the loop conditions

	#Set result to x -> this will go in loop2
	add $t0, $s0, $0
	#Set newx to x -> this will go in loop1 after end of loop2
	add $t1, $s0, $0
	#Set $t2 = 1 for loop condtions
	addi $t2, $0, 1
	#set i = y
	add $s2, $s1, 0
	#set j = x
	add $s3, $s0, 0

	loop1: 
	
		#if i < 2 return true (1) -> $t3
		slti $t3, $s2, 2

		#if $t3 contains 1 then jump to endloop1
		beq $t3, $t2, endloop1
	
		loop2:

			#if j < 2 return true (1) -> $t3
			slti $t4, $s3, 2

			#if $t3 contains 1 then jump to endloop1
			beq $t4, $t2, endloop2
			
			#result += newx
			add $t0, $t0, $t1

			# i--
			addi $s3, $s3, -1

			#jump back to top of loop2
			j loop2
		endloop2:
		
		#reset j = x
		add $s3, $s0, 0

		# newx = result
		add $t1, $t0, $0

		#j--
		addi $s2, $s2, -1

		#jump back to top of loop1
		j loop1
	endloop1:

	# Display the result text
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x41
	syscall
	
	# Display the result
	# load 1 into $v0 to display an integer
	ori     $v0, $0, 1			
	add 	$a0, $t0, $0
	syscall
	
	# Exit (load 10 into $v0)
	ori     $v0, $0, 10
	syscall

