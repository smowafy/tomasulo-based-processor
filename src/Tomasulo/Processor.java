package Tomasulo;

import Buffers.*;
import MemoryHierarchy.*;
import Registers.*;
import ReservationStation.*;

public class Processor {
	private int cycles;
	private int pc;
	private MemorySet dataMemory;
	private MemorySet insMemory;
	private InstructionBuffer insBuffer;
	private ReservationStations reservationStation;
	private ReorderBuffer reorderBuffer;
	private Registers registersFile;
	private RegisterStat registerStat;
	
	public Processor(int pc, MemorySet dataMemory,MemorySet insMemory,InstructionBuffer insBuffer,
			ReservationStations reservationStation,ReorderBuffer reorderBuffer,Registers registersFile,
			RegisterStat registerStat){
		this.pc = pc;
		this.dataMemory = dataMemory;
		this.insMemory = insMemory;
		this.registersFile = registersFile;
		this.insBuffer = insBuffer;
		this.reservationStation = reservationStation;
		this.reorderBuffer = reorderBuffer;
	}

}
