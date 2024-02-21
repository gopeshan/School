.text
 
.global _start
 
.org 0x0000
 
.equ JTAG_UART_BASE, 0x10001000
.equ DATA_OFFSET, 0
.equ STATUS_OFFEST, 4
.equ WSPACE_MASK, 0xFFFF
 
_start:
 
Main:
 
movi r2,LIST1
 
movi r3,LIST2
 
movi r4, 3
 
movi r5, 5
 
movi r6, 2
 
Call ListCalc
 
Call SumList
 
break
 
ListCalc:
 
subi sp,sp,16
 
stw r6,12(sp)
 
stw r5,8(sp)
 
stw r4,4(sp)
 
stw r3,0(sp)
 
ldw r8, 0(r2)
 
movi r7,0
 
LC_loop:
 
LC_if:
 
ldw r8, 0(r2)
 
bge r8, r5, LC_else
 
LC_then:
 
ldw r9, 0(r3)
 
mul r10,r6,r8
 
subi r10, r10,5
 
mov r9, r10
 
stw r9,0(r3)
 
br LC_endif
 
#---------------#
 
LC_else:
 
movi r9,0
 
stw r9,0(r3)
 
mov r8,r5
 
stw r8,0(r2)
 
addi r7,r7,1 # count = count + 1
 
 
LC_endif:
 
addi r2,r2,4
 
addi r3,r3,4
 
subi r4,r4,1
 
bgt  r4,r0,LC_loop
 
#------------------------# End of loop
 
ldw r6, 12(sp)
 
ldw r5, 8(sp)
 
ldw r4, 4(sp)
 
ldw r3, 0(sp)
 
addi sp,sp,16
 
mov r2, r7
 
ret
#--------------------------------------------------------------# end of pt 1
