package ch.shai.cs3.commands;

import ch.shai.cs3.utils.parsing.ParsingSlotBool;
import ch.shai.cs3.utils.parsing.ParsingSlotString;
import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.Random;

import static org.bukkit.Bukkit.createChunkData;

public class GenWorld extends CSCommand{

    public GenWorld() {
        super(
                new ParsingSlotBool("Met -ov si tu veux un overworld (default)","ov", "overworld"),
                new ParsingSlotBool("Met -n ou -nether pour le nether", "n","nether"),
                new ParsingSlotBool("Met -end pour le end", "end"),
                new ParsingSlotBool("Met -v ou -void pour que le monde soit un void default(", "v", "void"),
                new ParsingSlotBool("Met -f ou -flat pour que le monde soit un flat", "f", "flat"),
                new ParsingSlotString("Met -name <nom> pour donner un nom a ton monde", "name").setMandatory(true)
        );
    }


    @Override
    protected void execute(Player player, Location location, World world, String label) {
        String name = (String) this.getSlot(5).getValue();
        name = "worlds/" + name;
        if (name == null){
            player.sendMessage("T'es con y a pas de nom fais -name <nom>");
            return ;
        }
        boolean nether = (boolean) this.getSlot(1).getValue(false);
        boolean end = (boolean) this.getSlot(2).getValue(false);
        boolean void_world = (boolean) this.getSlot(3).getValue(false);
        boolean flat = (boolean) this.getSlot(4).getValue(false);

        WorldCreator wc = new WorldCreator(name);
        wc.environment(World.Environment.NORMAL);
        if (nether && !end)
            wc.environment(World.Environment.NETHER);
        if (end && !nether)
            wc.environment(World.Environment.THE_END);

        if(flat)
            wc.type(WorldType.FLAT);

        if(void_world) {
            wc.generator(new EmptyChunkGenerator()); //The chunk generator from step 1
        }
        player.sendMessage("Generation du monde");
        World newWorld = wc.createWorld();
        if (void_world){
            if (newWorld.getEnvironment().equals(World.Environment.NORMAL)) newWorld.setSpawnLocation(0, -64, 0);
            newWorld.getSpawnLocation().getBlock().setType(Material.STONE);
        }
        player.teleport(newWorld.getSpawnLocation());
    }
}

class EmptyChunkGenerator extends ChunkGenerator {

    @Nonnull
    public org.bukkit.generator.ChunkGenerator.@NotNull ChunkData generateChunkData(@Nonnull World world, @Nonnull Random random, int x, int z, @Nonnull org.bukkit.generator.ChunkGenerator.BiomeGrid biome) {
        return createChunkData(world);
    }
}