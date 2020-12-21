# The-Kevin-Bacon-Game

For the description of Kevin Bacon Game, you can follow this link: http://oracleofbacon.org/

Usage:
You install text file in project folder and run MakeGraph program with that as argos[0] for the program input.  
You will get a  prompt from the program with various options. 
For example,
By using demo text file in this depository, you can enter "connect" and to the "name" prompt enter "Bob".  
The commands are not case sensitive but the names are.  
If you now enter the command "actor" it should list 5 names in any order:  Joe, Sue, Mary, Han, and Jihan.  
Then type "to" and follow the name prompt with one of those 5 names.   
Here are the paths you should get:
	Joe   Bob => A => Joe
	Sue  Bob => C => Sue
	Mary  Bob => A => Mary  or Bob => C => Mary
	Han  Bob => C => Han
	Jihan  Bob => C => Sue => G => Jihan

You leave the program with the command "quit".
