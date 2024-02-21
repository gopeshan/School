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

SumList:
subi sp,sp,4
ldw r3, 0(sp)
Loop:
ldw r4, 0(r2)
if:
bgt r5, r0, positive
beq r5,r0,zerox
br negative
 
positive:
subi sp,sp,8
stw ra,0(sp)
stw r2,4(sp)
movi r2,'+'
call PrintChar
movi r2,'\n'
call PrintChar
ldw ra,0(sp)
ldw r2,4(sp)
br endif
 
negative:
subi sp,sp,8
stw ra,0(sp)
stw r2,4(sp)
movi r2,'-'
call PrintChar
movi r2,'\n'
call PrintChar
ldw ra,0(sp)
ldw r2,4(sp)
br endif
 
zerox:
subi sp,sp,8
stw ra,0(sp)
stw r2,4(sp)
 
movi r2,'0'
call PrintChar
movi r2,'\n'
call PrintChar
ldw ra,0(sp)
ldw r2,4(sp)
br endif
 
 
endif:
subi r3,r3,1
bgt r3,r0, Loop
ldw r3, 0(sp)
addi r2, r2, 4
ret
 
 
 
PrintChar:
subi sp, sp, 8
stw r3, 4(sp)
stw r4, 0(sp)
movia r3, JTAG_UART_BASE
pc_loop:
ldwio r4, STATUS_OFFSET(r3)
andhi r4, r4, WSPACE_MASK
beq r4, r0, pc_loop
stwio r2, DATA_OFFSET(r3)
ldw r3, 4(sp)
ldw r4, 0(sp)
addi sp, sp, 8
ret

_end:
 
.org 0x1000
 
LIST1:  .word 2,4,6
 
LIST2:  .word -1,-1,-1
 
.end
