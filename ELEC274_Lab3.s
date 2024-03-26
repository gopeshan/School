.text
	.global _start
	.org 	0x0
	.equ	JTAG_UART_BASE,    0x10001000
	.equ	DATA_OFFSET, 	   0
	.equ	STATUS_OFFSET,     4
	.equ	WSPACE_MASK,       0xFFFF
 
#---------------------------------------------------------
_start:
movia r2, TEXT
call PrintString
 
_end:
	br	_end
#---------------------------------------------------------
PrintString:
	subi	sp, sp, 12
	stw	ra, 0(sp)		#ra store
	stw	r2, 4(sp)
	stw	r3, 8(sp)		#will be ptr
 
	mov	r3, r2
	ldw	r2, 0(r3)
	beq	r2, r0, ps_endLoop	#empty string
ps_loop:
	ldb	r2, 0(r3)
ps_break_loop_if:
	beq	r2, r0, ps_endLoop	#null character
ps_endIf:
	call	PrintChar
	addi	r3, r3, 1
	br	ps_loop
ps_endLoop:
	ldw	ra, 0(sp)
	ldw	r2, 4(sp)
	ldw	r3, 8(sp)
	addi	sp, sp, 12
	re
