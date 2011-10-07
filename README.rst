Conway's Game of Life
------------
I have written only the game core and bare minimum of controls to test it in debug mode. 
All output goes to standard console. Controls are done with Java Swing and I'm planning to do Java Swing front-end. 

Version
-----------
0.1 initial commit

Game rules
-------------------------
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent.

At each step in time, the following transitions occur:

#. Any live cell with fewer than two live neighbours dies, as if caused by under-population.
#. Any live cell with two or three live neighbours lives on to the next generation.
#. Any live cell with more than three live neighbours dies, as if by overcrowding.
#. Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.

Game rules source: Wikipedia_

.. _Wikipedia: http://en.wikipedia.org/wiki/Conway's_Game_of_Life