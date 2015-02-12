# codegolf_catch_the_cat

This is a controller for a future challenge on PPCG.SE: http://meta.codegolf.stackexchange.com/questions/2140/sandbox-for-proposed-challenges/4774#4774

The Submissions must consit of a Cat or Catcher class, which should be placed in the Players package. They should have a takeTurn() method. The Cat returns a vector relative to it's position (move) and the Catcher return a an absolute positoin. Both return an integer array of two elements (x,y). They should check whether their move/position is valid beforehand:

The takeTurn() method gets the whole playing field f passed as an argument. The Cat/Catcher submissions can check their moves with f.isValidMove()/f.isValidPosition().
