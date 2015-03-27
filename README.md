# Undo-redo framework using commands

3 variations can be used depending on the concrete commands:
* Compensation: For concrete commands that are easy to revert
* Replay: Event-sourcing-like approach where on undo all commands are replayed except the last one
* Memento: Take snapshots of the whole system state on every command execution.

The latter two are to be used when the concrete commands are hard to revert:
* The memento variation is for when the system state is easy to capture and not too big.
* The replay variation is for when the system state is hard to capture or too big.

TODO: implement limited stack depth in Stack
 
