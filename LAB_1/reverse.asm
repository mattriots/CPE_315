 # Name:  Matt DePauw, Sing Tai
 # Section:  7 

 # Description:  

 # Java 
#
#    public static void reverseBinary(int num){
#        int reversed = 0;
#        
#        for(int i = 0; i < 32; i++){
#            reversed <<= 1;
#            reversed |= (num & 1);
#            num >>>= 1;
#        }
#    }
#
#

# declare global so programmer can see actual addresses.
.globl welcome
.globl prompt
.globl sumText

#  Data Area (this area contains strings to be displayed during the program)
.data

welcome:
	.asciiz " This program reverses a number \n\n"

prompt:
	.asciiz " Enter an integer: "

sumText: 
	.asciiz " \n Reversed = "

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
	ori     $a0, $a0,0x23
	syscall

	# Read 1st integer from the user (5 is loaded into $v0, then a syscall)
	ori     $v0, $0, 5
	syscall

	# Store number in $s0 <--- this is the original number
	addu    $s0, $v0, $0

	###Loop to reverse the binary of num ###
	#Set $s1 to 0 <----- this is the reversed number
	add 	$s1, $0, $0
	#set $s2 to be i or the counter
	add		$s2, $0, $0

	loop:
		#If i is less than 32 return true (1)
		slti $t1, $s2, 32
		#if $ti contains 0 - which means the above statement is false, then jump to 'end'
		beq $t1, $0, end


		#otherwise - Here is the loop

		#Shift left the reg that stores the reversed number
		sll $s1, $s1, 1
		#And OG num with 1 to get the right most bit. store in $t1
		andi $t2, $s0, 1
		#Or reversed number with result in $t2
		or	$s1, $s1, $t2
		#Logical Shift original number right by 1
		srl $s0, $s0, 1
		#increment i / count
		addi $s2, $s2, 1
		j loop

	end:

	# Display the reversed text
	ori     $v0, $0, 4			
	lui     $a0, 0x1001
	ori     $a0, $a0,0x38
	syscall
	
	# Display the reversed number
	# load 1 into $v0 to display an integer
	# load $s1 into $a0 for displaying
	ori     $v0, $0, 1			
	add 	$a0, $s1, $0
	syscall
	
	# Exit (load 10 into $v0)
	ori     $v0, $0, 10
	syscall

