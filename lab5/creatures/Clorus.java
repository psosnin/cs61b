package creatures;

import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;

/**
 * An implementation of a motile pacifist photosynthesizer.
 *
 * @author Josh Hug
 */
public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r ;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates clorus with energy equal to E.
     */
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }

    /**
     * creates a clorus with energy equal to 1.
     */
    public Clorus() {
        this(1);
    }

    public Color color() {
        return color(r, g, b);
    }

    /**
     *  The clorus should gain the other creature's energy
     */
    public void attack(Creature c) {
        energy += c.energy();
    }

    /**
     * Clorus lose 0.03 energy when moving
     */
    public void move() {
        if (energy <= 0.03) {
            energy = 0.0;
        } else {
            energy -= 0.03;
        }
    }


    /**
     * Clorus lose 0.01 energy when staying
     */
    public void stay() {
        if (energy <= 0.01) {
            energy = 0.0;
        } else {
            energy -= 0.01;
        }
    }

    /**
     * Clorus and their offspring each get 50% of the energy, with none
     * lost to the process. Now that's efficiency! Returns a baby
     * Clorus.
     */
    public Clorus replicate() {
        energy = energy * 0.5;
        return new Clorus(energy);
    }

    /**
     * Clorus take exactly the following actions based on NEIGHBORS:
     * 1. If no empty adjacent spaces, STAY.
     * 2. Otherwise, if any plips are seen the clorus will attack one of them randomly
     * 3. Otherwise, if the clorus has an energy greater than or equal to one, it will
     * replicate to a random empty square
     * 4. Otherwise, it will move to a random empty square
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();

        for (Map.Entry<Direction, Occupant> entry : neighbors.entrySet()) {
            Direction direction = entry.getKey();
            Occupant value = entry.getValue();
            if (value.name() == "empty") {
                emptyNeighbors.add(direction);
            } else if (value.name() == "plip") {
                plipNeighbors.add(direction);
            }
        }

        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else if (!plipNeighbors.isEmpty()) {
            return new Action(Action.ActionType.ATTACK, selectRandom(plipNeighbors));
        } else if (energy >= 1.0) {
            return new Action(Action.ActionType.REPLICATE, selectRandom(emptyNeighbors));
        } else {
            return new Action(Action.ActionType.MOVE, selectRandom(emptyNeighbors));
        }
    }

    private Direction selectRandom(Deque<Direction> directions) {
        Direction dir = directions.getFirst();
        for (int i = 0; i < Math.random()*directions.size(); i++) {
            dir = directions.removeFirst();
        }
        return dir;
    }
}
