Conway's Game of Life
======================

Game rules
-----------------------
The universe of the Game of Life is an infinite two-dimensional orthogonal grid of square cells, each of which is in one of two possible states, alive or dead. Every cell interacts with its eight neighbours, which are the cells that are horizontally, vertically, or diagonally adjacent.

At each step in time, the following transitions occur:

* Any live cell with fewer than two live neighbours dies, as if caused by under-population.
* Any live cell with two or three live neighbours lives on to the next generation.
* Any live cell with more than three live neighbours dies, as if by overcrowding.
* Any dead cell with exactly three live neighbours becomes a live cell, as if by reproduction.

The initial pattern constitutes the seed of the system. The first generation is created by applying the above rules simultaneously to every cell in the seed—births and deaths occur simultaneously, and the discrete moment at which this happens is sometimes called a tick (in other words, each generation is a pure function of the preceding one). The rules continue to be applied repeatedly to create further generations.

Game rules source: [wikipedia]

[wikipedia]: http://en.wikipedia.org/wiki/Conway's_Game_of_Life

Version notes
------------------
### 0.5.1 (5/11/2011)
* Updated 'alive cells' counter, it will be updated when user draws or erases cells.

### 0.5.0 (5/11/2011)
* Added draw and remove cells from the field with the mouse-click feature. To draw new cells on the black part of the field and drag; to erase alive cell from the field click on it, dragging mouse in that state will also erase other cells.

### 0.4.0 (30/10/2011)
* Added slider to control speed.
* Added information of total amount of cells, alive cells and current generation.
* Changed control panel colour.
* Changed FPS representation on the field.

### 0.3.5 (28/10/2011)
* Added cell coordinates check on the cell object initialization, it was moved from fieldImpl in order to slightly decrease computation time. Coordinates will be corrected only one time at initialization, not in the runtime as it was previous implemented.
* Added unit test for cell coordinates check.
* Introduced new constant for cell drawing, it is required to draw more better looking cell with 0.6 pixels wide black border around cell.
* Added some GUI constants dependency: many of GUI constants aren't hardcoded, but computed at the game startup. I am not sure if this is a good approach.

### 0.3.4 (27/10/2011)
* Redraw only alive cells.
* Cache number of alive neighbours around cell.
* Added FPS information to the field view.
* Added new test cases.
* Fixed iterator error (iterated twice over first row).

### 0.3.3 (14/10/2011)
* New implementation of GUI.
* Fixed thread issues.
* Added STEP button.
* New behaviour of controls buttons.
* Ant build is updated.

### 0.2 (12/10/2011)
* Added GUI, implemented as GridBagLayout.
* Many issues with GUI, work in progress...
* Ant build is not updated.
* Problem with threads, Core thread and GUI thread read/write the same data object (Field) simultaneously, cause corrupt data.

### 0.1 (7/10/2011) 
* Initial commit.