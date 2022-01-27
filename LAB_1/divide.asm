 # Name:  Matt DePauw, Sing Tai
 # Section:  7 

 # Description: Divide a 64 bit number by a 32 bit number. The 64 bit number will be split between 2 registers: hi/lo

#  public static void divide(int high, int low, int divisor) {
#
#      while (divisor > 1) {
#          divisor >>>= 1;
#
#          low >>>= 1;
#
#          low |= ((high & 1) * Integer.MIN_VALUE);
#
#          high >>>= 1;
#
#      }


# declare global so programmer can see actual addresses.
.globl welcome
.globl prompt
.globl sumText

#  Data Area (this area contains strings to be displayed during the program)
.data

welcome:
	.asciiz " This program divides a 64 bit number by a 32 bit number \n\n"

prompt1:
	.asciiz " Enter high: "
	
prompt2:
	.asciiz " Enter low: "

prompt3:
	.asciiz " Enter divisor: "

sumText: 
	.asciiz " \n Result = "

commaText:
	.asciiz ","

#Text Area (i.e. instructions)
.text

main:

	# Display the welcome message (load 4 into $v0 to display)
	ori     $v0, $0, 4			

	# This generates the starting address for the welcome message.
	# (assumes the register first contains 0).
	lui     $a0, 0x1001
	syscall

	# Display prompt1
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x3C
	syscall

	# Read 1st integer from the user (5 is loaded into $v0, then a syscall)
	ori     $v0, $0, 5
	syscall

	# Store 1st integer (High) at $s0 <------HIGH
	addu    $s0, $v0, $0
	
	# Display prompt2 
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x4A
	syscall

	# Read 2nd integer 
	ori	$v0, $0, 5			
	syscall
	
	# Store 2nd integer (Low) at $s1 <-----LOW
	addu    $s1, $v0, $0 

	# Display prompt3 
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x57
	syscall

	# Read 2nd integer 
	ori	$v0, $0, 5			
	syscall
	
	# Store 3rd integer (Divisor) at $s2 <-----DIV
	addu    $s2, $v0, $0 

	# Registers #
	# $s0 = high
	# $s1 = low
	# $s2 = div	
	# $t0 = 1 for condition
	# $t1 = result of condition
	# $t2 = result of (high & 1)

	addi		$t0, $t0, 1

	loop:
		#if div < 2 (aka when its 1) $t1 will be true and we will end the loop
		slti $t1, $s2, 2
		beq  $t1, $t0, end

		#shift div >>> 1
		srl $s2, $s2, 1

		#shift low >>> 1
		srl $s1, $s1, 1

	#   low |= ((high & 1) * Integer.MIN_VALUE);
		andi $t2, $s0, 1
	#Shift left by 31 so the rest of (high & 1) is in the MSB of lowest. This takes care of any '1' coming from the Hi reg -> low reg
		sll $t2, $t2, 31
		or $s1, $s1, $t2

		#shift high >>> 1
		srl $s0, $s0, 1

		j loop

	end:

	# Display the result text
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x68
	syscall
	
	# Display the result (hi, low)
	# load high into $a0
	ori     $v0, $0, 1			
	add 	$a0, $s0, $0
	syscall

	# Display the comma 
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x75
	syscall

	# load low into $a0
	ori     $v0, $0, 1			
	add 	$a0, $s1, $0
	syscall
	
	# Exit (load 10 into $v0)
	ori     $v0, $0, 10
	syscall

