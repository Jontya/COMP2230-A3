# COMP2240 Operating Systems
## Assignment 3 (Due: 21/10/22)

### Overview
Simulates a system with a finite ammount of memory. Processes and process instructions are read in by file and placed in the ready queue. The system uses a Round Robin scheduling algotithm with a specified time quantum. The system implements two different resident set management schemes: Fixed-Local and Variable-Global. Process statistics are printed to console at the end of the simulation.

### Running Program
``` javac A3.java ``` <br />
``` java A3 "frames" "timeQuantum" "process" ... ``` <br />

"frames" is the number of frames the system will have in memory <br />
"timeQuantum" is the time quantum used by the Round Robin scheduling algorithm <br />
"process" is a file containg page instructions for a process (minimum one process file)
