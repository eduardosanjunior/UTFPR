.text
.equ	SEG_A, 0x80 
.equ	SEG_B, 0x40 
.equ	SEG_C, 0x20 
.equ	SEG_D, 0x08
.equ	SEG_E, 0x04
.equ	SEG_F, 0x02
.equ	SEG_G, 0x01
.equ	SWI_CheckBlack, 0x202
.equ	SWI_CheckBlue, 0x203
.equ	left, 0x02
.equ	right, 0x01
.equ	SWI_CheckBlue, 0x203
.equ	BLUE_KEY_00, 0x01 
.equ	BLUE_KEY_01, 0x02 
.equ	BLUE_KEY_02, 0x04 
.equ	BLUE_KEY_03, 0x08 
.equ	BLUE_KEY_04, 0x10 
.equ	BLUE_KEY_05, 0x20 
.equ	BLUE_KEY_06, 0x40 
.equ	BLUE_KEY_07, 0x80 
.equ	BLUE_KEY_08, 1<<8 
.equ	BLUE_KEY_09, 1<<9 
.equ	BLUE_KEY_10, 1<<10 
.equ	BLUE_KEY_11, 1<<11 
.equ 	BLUE_KEY_12, 1<<12 
.equ 	BLUE_KEY_13, 1<<13 
.equ 	BLUE_KEY_14, 1<<14 
.equ 	BLUE_KEY_15, 1<<15 
.equ	umseg, 1000
.equ	tempo_atualizacao, 50
.equ 	SWI_CLEAR_LINE, 0x208

start:	
	swi	0x206
	mov	r10, #11
	mov	r9, #0
	ldr	r8, =tempo_atualizacao
	mov	r12, #1
	ldr	r7, =umseg
	mov	r5, #0
lcd:	mov	r0, #0
	mov 	r1, #0
	ldr	r2, =strg
	swi	0x204
	mov	r0, #7
	mov	r1, #0
	mov	r2, r12
	swi	0x205
	mov	r2, r5
	mov	r0, #11
	mov	r1, #0
	swi	0x205
	mov	r2, r9
	mov	r0, #13
	mov	r1, #0
	swi	0x205
	mov	r2, #0
	mov	r0, #10
	mov	r1, #0
	swi	0x205
	mov	r0, #12
	mov	r1, #0
	ldr	r2, =ponto
	swi	0x204
	mov	r0, #14
	mov	r1, #0
	ldr	r2, =s
	swi	0x204
	
comeca:	
	swi	SWI_CheckBlack
	cmp	r0, #0
	beq	comeca
	cmp	r0, #right
	beq	escolhe
	bal	escolhe
rodada:	
	swi	0x06d
	mov	r3, r0
	sub	r6, r3, r4
	cmp	r6, r8
	bge	tempo
contin:	swi	SWI_CheckBlue
	cmp	r0, #0
	beq	rodada
	bal	Azul
escolhe:
	swi	0x06d
	and	r0, r0, #15
	cmp	r0, #0
	beq	zero
	cmp	r0, #1
	beq	um
	cmp	r0, #2
	beq	dois
	cmp	r0, #3
	beq	tres
	cmp	r0, #4
	beq	quatro
	cmp	r0, #5
	beq	cinco
	cmp	r0, #6
	beq	seis
	cmp	r0, #7
	beq	sete
	cmp	r0, #8
	beq	oito
	cmp	r0, #9
	beq	nove
	cmp	r0, #10
	beq	a
	cmp	r0, #11
	beq	b
	cmp	r0, #12
	beq	c
	cmp	r0, #13
	beq	d
	cmp	r0, #14
	beq	e
	cmp	r0, #15
	beq	f
zero:	swi 	0x06d
	mov	r4, r0
	mov	r0, #0
	ldr	r11, =BLUE_KEY_00
	BL	mostrardigitos
	bal	rodada
um:	swi 	0x06d
	mov	r4, r0
	mov	r0, #1
	ldr	r11, =BLUE_KEY_01
	BL	mostrardigitos
	bal	rodada
dois:	swi 	0x06d
	mov	r4, r0
	mov	r0, #2
	ldr	r11, =BLUE_KEY_02
	BL	mostrardigitos
	bal	rodada
tres:	swi 	0x06d
	mov	r4, r0
	mov	r0, #3
	ldr	r11, =BLUE_KEY_03
	BL	mostrardigitos
	bal	rodada
quatro:	swi 	0x06d
	mov	r4, r0
	mov	r0, #4
	ldr	r11, =BLUE_KEY_04
	BL	mostrardigitos
	bal	rodada
cinco:	swi 	0x06d
	mov	r4, r0
	mov	r0, #5
	ldr	r11, =BLUE_KEY_05
	BL	mostrardigitos
	bal	rodada
seis:	swi 	0x06d
	mov	r4, r0
	mov	r0, #6
	ldr	r11, =BLUE_KEY_06
	BL	mostrardigitos
	bal	rodada
sete:	swi 	0x06d
	mov	r4, r0
	mov	r0, #7
	ldr	r11, =BLUE_KEY_07
	BL	mostrardigitos
	bal	rodada
oito:	swi 	0x06d
	mov	r4, r0
	mov	r0, #8
	ldr	r11, =BLUE_KEY_08
	BL	mostrardigitos
	bal	rodada
nove:	swi 	0x06d
	mov	r4, r0
	mov	r0, #9
	ldr	r11, =BLUE_KEY_09
	BL	mostrardigitos
	bal	rodada
a:	swi 	0x06d
	mov	r4, r0
	mov	r0, #10
	ldr	r11, =BLUE_KEY_10
	BL	mostrardigitos
	bal	rodada
b:	swi 	0x06d
	mov	r4, r0
	mov	r0, #11
	ldr	r11, =BLUE_KEY_11
	BL	mostrardigitos
	bal	rodada
c:	swi 	0x06d
	mov	r4, r0
	mov	r0, #12
	ldr	r11, =BLUE_KEY_12
	BL	mostrardigitos
	bal	rodada
d:	swi 	0x06d
	mov	r4, r0
	mov	r0, #13
	ldr	r11, =BLUE_KEY_13
	BL	mostrardigitos
	bal	rodada
e:	swi 	0x06d
	mov	r4, r0
	mov	r0, #14
	ldr	r11, =BLUE_KEY_14
	BL	mostrardigitos
	bal	rodada
f:	swi 	0x06d
	mov	r4, r0
	mov	r0, #15
	ldr	r11, =BLUE_KEY_15
	BL	mostrardigitos
	bal	rodada
mostrardigitos:
	stmfd	sp!,{r0-r2,lr}
	ldr	r2,=digitos
	ldr	r0,[r2,r0,lsl#2]
	swi	0x200
	ldmfd 	sp!,{r0-r2,pc}
tempo:
	add	r9, r9, r6
	cmp	r7, r9
	blt	transf
t2:	mov	r2, r5	
	cmp	r2, #10
	beq	aum
t3:	mov	r0, r10
	mov 	r1, #0
	swi	0x205
	mov	r2, r9
	mov	r0, #13
	mov	r1, #0
	swi	0x205
	mov	r0, #16
	mov	r1, #0
	ldr	r2, =s
	swi	0x204
	mov	r4, r3
	bal	contin
aum:	mov	r10, #10
	bal	t3
transf:	sub	r9, r9, #1000
	add	r5, r5, #1
	cmp	r5, #100
	beq	Miss
	bal	t2	
Azul:	
	cmp	r0, r11
	beq	Hit
	bal	Miss
Hit:	
	cmp	r12, #6
	beq	fim
	add	r12, r12, #1
	mov	r0, #7
	mov	r1, #0
	mov	r2, r12
	swi	0x205
	mov	r0, #0
	swi	0x200
	bne	comeca
	
Miss:
	mov	r0, #0
	swi	SWI_CLEAR_LINE
	mov	r1, #0
	ldr	r2, =perdeu
	swi	0x204
	mov	r0, #0
	swi	0x200
	swi	0x11
fim:	
	mov	r0, #0
	swi	SWI_CLEAR_LINE
	mov	r0, #0
	mov	r1, #0
	mov	r2, r5
	swi	0x205
	mov	r0, #3
	mov	r1, #0
	mov	r2, r9
	swi	0x205
	mov	r0, #6
	mov	r1, #0
	ldr	r2, =s
	swi	0x204
	mov	r0, #2
	mov	r1, #0
	ldr	r2, =ponto
	swi	0x204
	mov	r0, #0
	mov	r1, #1
	ldr	r2, =fjogo
	swi	0x204
	mov	r0, #0
	swi	0x200
	swi	0x11
digitos:
	.word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_G @0
	.word SEG_B|SEG_C @1
	.word SEG_A|SEG_B|SEG_F|SEG_E|SEG_D @2
	.word SEG_A|SEG_B|SEG_F|SEG_C|SEG_D @3
	.word SEG_G|SEG_F|SEG_B|SEG_C @4
	.word SEG_A|SEG_G|SEG_F|SEG_C|SEG_D @5
	.word SEG_A|SEG_G|SEG_F|SEG_E|SEG_D|SEG_C @6
	.word SEG_A|SEG_B|SEG_C @7
	.word SEG_A|SEG_B|SEG_C|SEG_D|SEG_E|SEG_F|SEG_G @8
	.word SEG_A|SEG_B|SEG_F|SEG_G|SEG_C @9
	.word SEG_A|SEG_G|SEG_B|SEG_F|SEG_E|SEG_C @A
	.word SEG_G|SEG_F|SEG_E|SEG_E|SEG_C|SEG_D @b
	.word SEG_A|SEG_G|SEG_E|SEG_D @C
	.word SEG_B|SEG_F|SEG_E|SEG_C|SEG_D @d
	.word SEG_A|SEG_F|SEG_D|SEG_G|SEG_E @E
	.word SEG_A|SEG_F|SEG_G|SEG_E @F
perdeu:	.asciz	"Perdeu!"
fjogo:	.asciz	"Fim de jogo!"
s:	.asciz	"s"
ponto:	.asciz	"."
strg:	.asciz	"Rodada  :"