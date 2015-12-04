# tomasulo-based-processor


/*
STAGE SUPPOSED EXECUTION ORDER:
	issue ---> commit

*/



// <Commit stage>

if (ROB[h].ready == yes) {
	d = ROB[h].Dest;
	if (ROB[h].Instruction == Branch) {
		if (branch is mispredicted) {
			clear ROB[h], RegisterStat; fetch branch dest;
		}
	} else if (ROB[h].Instruction == Store) {
		Mem[ROB[h].Destination] = ROB[h].Value;
	} else {
		Regs[d] = ROB[h].Value;
	}
	ROB[h].Busy = no;
	if (RegisterStat[d].Reorder == h) {
		RegisterStat[d].Busy = no;
	}
}



// </Commit stage>
// <Write stage>

loop on ROB (idx = head --> tail) {
	if (ROB[idx].RemainingCycles == 0) {
		if (ROB[idx].Instruction == Store) {
			if (RS[r].Qk == 0) {
				ROB[h].Value = RS[r].Vk;
			}
		} else {
			b = RS[r].Dest;
			RS[r].Busy = no;
			// CDB START
			loop(index x in RS) {
				if (RS[x].Qj == b) {
					RS[x].Vj = result;
					RS[x].Qj = 0;
				}
				if (RS[x].Qk == b) {
					RS[x].Vk = result;
					RS[x].Qk = 0;
				}
			}
			//CDB END
			ROB[b].Value = result;
			ROB[b].Ready = yes;

		}
	}
}

// </Write Stage>

// <Execute Stage>

// Arithmetic operation

if (RS[r].Qj == 0 && RS[r].Qk == 0) {
	Compute results;
}

// Load
//Cycle 1
if (RS[r].Qj == 0) {
	RS[r].A = RS[r].Vj + RS[r].A;
}
//Cycle 2
Read from Mem[RS[r].A];

//Store
if (RS[r].Qj == 0) {
	ROB[h].Destination = RS[r].Vj + RS[r].A;
}


// </Execute stage>

// <Issue stage>

if (Reservation station r Available && ROB b Available) {
	if (RegisterStat[rs].Busy) {
		idx = RegisterStat[rs].Reorder;
		if (ROB[idx].Ready) {
			RS[r].Vj = ROB[idx].Value;
			RS[r].Qj = 0;
		} else {
			RS[r].Qj = idx;
		}
	} else {
		RS[r].Vj = Regs[rs];
		RS[r].Qj = 0;
	}
	RS[r].Busy = yes;
	RS[r].Dest = b;
	ROB[b].Instruction = opcode;
	ROB[b].Dest = rd;
	ROB[b].Ready = no;
	if (Arithmetic or Store) {
		if (RegisterStat[rt].Busy) {
			idx = RegisterStat[rt].Reorder;
			if (ROB[idx].Ready) {
				RS[r].Vk = ROB[idx].Value;
				RS[r].Qk = 0;
			} else {
				RS[r].Qk = idx;
			}
		} else {
			RS[r].Vk = Regs[rs];
			RS[r].Qk = 0;
		}
	}
	if (Arithmetic) {
		RegisterStat[rd].Reorder = b;
		RegisterStat[rd].Busy = yes;
		ROB[b].Dest = rt;
	}
	if (Load) {
		RS[r].A = imm;
		RegisterStat[rt].Reorder = b;
		RegisterStat[rt].Busy = yes;
		ROB[b].Dest = rt;
	}
	if (Store) {
		RS[r].A = imm;
	}
}




----------------------------


TODO:


1- Modify memory hierarchy and return number of cycles for the load instruction ==> Mowafy
2- Modify both ROB and RS to add instruction object ==> Aya
3- Register Status ==> Aya
4- Modify instruction object: +Number of cycles remaining ==> Aya
5- Remember to add methods to Reorder buffer:	==> Aya
	Add, delete
6- Remember to handle checking that the whole program is done executing ==> ??
7- Convert register values to 2's complement ==> Hossam
8- vj vk int arrays in station ==> Aya

Tasks: (given that TODOs are done)

8- Processor data structure (has all components)//bob el seneen
9- Above code
10- Parser ==> Hossam
11- Input/Output console and construction ==> Wanis
12- Testing
13- Handling Branching ==> TBD -----> Ask Jailan <-----

10 & 11 assignees can be swapped

8 & 9 & 12 ==> All (preferably on site)

