package com.iestyn.carjam.puzzlesolver;

import com.iestyn.carjam.puzzle.PuzzleInterface;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;


/**
 * BreathFirstSearch solver for {@link PuzzleInterface}.
 */
public class BreathFirstSearch {

  Queue<PuzzleInterface> currentQueue = new LinkedList<>();
  Queue<PuzzleInterface> nextQueue = new LinkedList<>();

  HashSet<String> alreadyVisited = new HashSet<>();

  public BreathFirstSearch(PuzzleInterface firstQueue) {
    currentQueue.add(firstQueue);
  }

  /**
   * Solves the puzzle.

   * @return null or "No Solution"
   */

  public String getSolution() {
    var moveAmount = 0;

    while (!currentQueue.isEmpty() || !nextQueue.isEmpty()) {

      while (!currentQueue.isEmpty()) {
        PuzzleInterface puzzle = currentQueue.remove();
        if (!alreadyVisited.contains(puzzle.toString())) {
          if (puzzle.isPuzzleComplete()) {
            System.out.println("Solution found " + moveAmount);
            return puzzle.getMoveHistory();
          }
          nextQueue.addAll(puzzle.getAllMovesForAllVehicles());
          alreadyVisited.add(puzzle.toString());
        }
      }
      System.out.println("Moves: " + moveAmount + ", nextQueue: " + nextQueue.size());
      moveAmount++;
      currentQueue = nextQueue;
      nextQueue = new LinkedList<>();
    }

    System.out.println("Solution Not found");
    return "No Solution";
  }
}