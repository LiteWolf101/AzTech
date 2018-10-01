package litewolf101.aztech.init;

import litewolf101.aztech.objects.blocks.*;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class BlocksInit {
    public static final List<Block> BLOCKS = new ArrayList<Block>();

    public static final Block RUNE_ORE = new BlockRuneOre("rune_ore", Material.ROCK);
    public static final Block TEMPLE_STONE = new BlockTempleStone("temple_stone", Material.ROCK);
    public static final Block SLAUGHTIVE_RUNE = new BlockSlaughtiveRune("slaughtive_rune", Material.ROCK);
    public static final Block EMPOWER_RUNE = new EmpowerRune("empower_rune", Material.ROCK);
    public static final Block ANCIENT_STONE = new BlockBase("ancient_stone", Material.ROCK);
    public static final Block ANCIENT_COBBLESTONE = new BlockBase("ancient_cobblestone", Material.ROCK);
    public static final Block ANCIENT_DRY_MUD = new BlockBase("ancient_dry_mud", Material.ROCK);
    public static final Block ANCIENT_ROCK = new BlockBase("ancient_rock", Material.ROCK);
    public static final Block ANCIENT_BEDROCK = new BlockUnbreakable("ancient_bedrock", Material.ROCK);
    public static final Block DETECTOR_RUNE = new DetectorRune("detector_rune", Material.ROCK);
    public static final Block ANCIENT_DIRT = new AncientDirt("ancient_dirt", Material.GROUND);
    public static final Block ANCIENT_GRASS = new AncientGrass("ancient_grass", Material.GRASS);
    public static final Block LASER_BLOCK = new LaserBlock("laser_block", Material.ROCK);
    public static final Block ANCIENT_LASER = new AncientLaser("ancient_laser", Material.ROCK);
    public static final Block ENEMY_EMITTER_RUNE = new EnemyEmitterRune("enemy_emitter_rune", Material.ROCK);
    public static final Block LANTERN = new Lantern("lantern", Material.ROCK);
    public static final Block OBJECTOR_RUNE = new ObjectorRune("objector_rune", Material.ROCK);
    public static final Block ANCIENT_CHISELED_BRICKS = new BlockBase("ancient_chiseled_bricks", Material.ROCK);
    public static final Block ANCIENT_BRICKS = new BlockBase("ancient_bricks", Material.ROCK);
    public static final Block ANCIENT_MUDDY_BRICKS = new BlockBase("ancient_muddy_bricks", Material.ROCK);
    public static final Block INSERTIVE_RUNE = new InsertiveRune("insertive_rune", Material.ROCK);
    public static final Block KEYHOLE_RUNE = new KeyholeRune("keyhole_rune", Material.ROCK);
    public static final Block DOOR_RUNE = new DoorRune("door_rune", Material.ROCK);
    public static final Block RUNE_LINE = new RuneLine("rune_line", Material.CIRCUITS);
    public static final Block R2R_TRANSLATOR = new R2RTranslator("r_to_r_translator", Material.ROCK);
    public static final Block RUNE_BLOCK = new RuneBlock("rune_block", Material.ROCK);
    public static final Block TEMPLE_RUNE_BLOCK = new TempleRuneBlock("temple_rune_block", Material.ROCK);
}