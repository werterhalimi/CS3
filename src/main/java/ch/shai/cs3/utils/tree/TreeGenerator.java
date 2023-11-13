package ch.shai.cs3.utils.tree;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.Random;

public class TreeGenerator {
    private Location origin;
    private int nodeRadius, trunkHeight, minDist;
    private double radius;
    private int noNode;
    private Random random;
    private ArrayList<Location> nodes;
    private ArrayList<Location> todos;


    public void traceLine(Location loc1, Location loc2, double width) {
        Vector direction = loc2.toVector().subtract(loc1.toVector());
        double length = direction.length();
        direction.normalize();

//        Vector side = direction.clone().crossProduct(new Vector(0, 1, 0)).normalize().multiply(width / 2);
//        for (double i = 0; i < length; i += 0.1) {
//            Location loc = loc1.clone().add(direction.clone().multiply(i));
//            for (double j = -width / 2; j < width / 2; j += 0.1) {
//                Location blockLoc = loc.clone().add(side.clone().multiply(j));
//                // Set the block type to your desired type
//                blockLoc.getBlock().setType(Material.STONE);
//            }
//        }
    }

//
//
//    private void traceLine(Location begin, Location end, double radius){
//        Vector vector = end.toVector().subtract(begin.toVector()).normalize().multiply(Math.max(1, radius - 2));
//        int i = 0;
//        int distance = (int) Math.floor(begin.distance(end));
//        ArrayList<Location> memo = new ArrayList<>();
//        while (i++ <= distance) {
//            for (double x = -radius; x < radius; x++) {
//                for (double y = -radius; y < radius; y++) {
//                    for (double z = -radius; z < radius; z++) {
//
//                        Location location = begin.clone().add(x,y,z);
//                        if (location.getBlock().getType() != Material.MANGROVE_WOOD && begin.distance(location) < radius) {
//                            location.getBlock().setType(Material.MANGROVE_WOOD);
//                        }
//                    }
//                }
//            }
//            begin.add(vector);
//        }
//    }

    public TreeGenerator(Location origin, int nodeRadius, int trunkHeight, int minDist , int noNode, double radius) {
        this.origin = origin;
        this.nodeRadius = nodeRadius;
        this.trunkHeight = trunkHeight / 2;
        this.minDist = minDist;
        this.noNode = noNode;
        this.radius = radius;
        this.nodes = new ArrayList<>();
        this.todos = new ArrayList<>();
        this.random = new Random();
    }


    public Vector generateRandomVector(double length) {
        Random rand = new Random();
        double x = (rand.nextDouble() - 0.5);
        double y = (rand.nextDouble() - 0.5);
        double z = (rand.nextDouble() - 0.5);
        Vector vector = new Vector(x,y,z);

        vector.normalize().multiply(length);
        return vector;
    }

    public void generateNodes(){
        if (!this.nodes.isEmpty()) return;
        int offset = this.trunkHeight + this.nodeRadius;
        Location neworigin = origin.clone().add(0,offset,0);
        neworigin.getBlock().setType(Material.DIAMOND_BLOCK);
        for (int i = 0; i < this.noNode; i++) {
            Location loc = neworigin.clone().add(generateRandomVector(this.random.nextDouble() * this.nodeRadius));
            loc.getBlock().setType(Material.GLOWSTONE);
            this.nodes.add(loc);
        }


//        for (int y = 0; y < height; y++) {
//            if (this.random.nextFloat() < (nodeRate + y/(height / 2.5)) / 2) {
//                Location loc = this.origin.clone().add(width * y/height * random.nextFloat() * (random.nextBoolean() ? -1 : 1) + this.origin.getX(), y, width * (random.nextBoolean() ? -1 : 1) * random.nextFloat()  * y/100 + this.origin.getZ());
//                loc.getBlock().setType(Material.GLOWSTONE);
//                this.nodes.add(loc);
//            }
//        }
        this.todos.add(this.origin);
    }

    public void drawNodes(){
        for (Location node : this.todos) {
            node.getBlock().setType(Material.GOLD_BLOCK);
        }
    }


    public boolean drawGen(){
        ArrayList<Location> nextGen = new ArrayList<>();
        for (Location todo : this.todos) {
            nodes.sort((a,b) -> a.distance(todo) < b.distance(todo) ? -1 : 1);
            for (int branch = 0; branch < Math.min(5, nodes.size()); branch++) {
                Location target = nodes.get(branch);
                double dist = target.distance(this.origin);
                this.traceLine(todo.clone(), target.clone(), this.radius * (1.0 - dist / (this.nodeRadius * 2)));
                nextGen.add(target);
            }
        }
        System.out.println("size avant " + this.nodes.size()+ " radius " + this.radius);
        this.todos.clear();
        for (Location location : nextGen) {
            this.nodes.remove(location);
            this.todos.add(location.clone());
        }
        System.out.println("size apres " + this.nodes.size()+ " radius " + this.radius);

        this.minDist++;
        return !this.nodes.isEmpty();
    }

}
