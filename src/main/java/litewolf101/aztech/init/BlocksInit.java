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
    public static final Block ANCIENT_STONE = new AncientStone("ancient_stone", Material.ROCK);
    public static final Block ANCIENT_COBBLESTONE = new BlockBase("ancient_cobblestone", Material.ROCK).setHardness(2);
    public static final Block ANCIENT_DRY_MUD = new BlockBase("ancient_dry_mud", Material.ROCK).setHardness(0.2f);
    public static final Block ANCIENT_ROCK = new BlockBase("ancient_rock", Material.ROCK).setHardness(2.7f);
    public static final Block ANCIENT_BEDROCK = new BlockUnbreakable("ancient_bedrock", Material.ROCK);
    public static final Block DETECTOR_RUNE = new DetectorRune("detector_rune", Material.ROCK);
    public static final Block ANCIENT_DIRT = new AncientDirt("ancient_dirt", Material.GROUND);
    public static final Block ANCIENT_GRASS = new AncientGrass("ancient_grass", Material.GRASS);
    public static final Block LASER_BLOCK = new LaserBlock("laser_block", Material.ROCK);
    public static final Block ANCIENT_LASER = new AncientLaser("ancient_laser", Material.ROCK);
    public static final Block ENEMY_EMITTER_RUNE = new EnemyEmitterRune("enemy_emitter_rune", Material.ROCK);
    public static final Block LANTERN = new Lantern("lantern", Material.WOOD);
    public static final Block OBJECTOR_RUNE = new ObjectorRune("objector_rune", Material.ROCK);
    public static final Block ANCIENT_CHISELED_BRICKS = new BlockBase("ancient_chiseled_bricks", Material.ROCK).setHardness(10);
    public static final Block ANCIENT_BRICKS = new BlockBase("ancient_bricks", Material.ROCK).setHardness(10);
    public static final Block ANCIENT_MUDDY_BRICKS = new BlockBase("ancient_muddy_bricks", Material.ROCK).setHardness(4);
    public static final Block INSERTIVE_RUNE = new InsertiveRune("insertive_rune", Material.ROCK);
    public static final Block KEYHOLE_RUNE = new KeyholeRune("keyhole_rune", Material.ROCK);
    public static final Block DOOR_RUNE = new DoorRune("door_rune", Material.ROCK);
    public static final Block R2R_TRANSLATOR = new R2RTranslator("r_to_r_translator", Material.ROCK);
    public static final Block RUNE_BLOCK = new RuneBlock("rune_block", Material.ROCK);
    public static final Block TEMPLE_RUNE_BLOCK = new TempleRuneBlock("temple_rune_block", Material.ROCK);
    public static final Block TEMPLE_MIRROR = new TempleMirror("temple_mirror", Material.ROCK);
    public static final Block COAL_ORE = new AncientOre("ancient_coal_ore", Material.ROCK);
    public static final Block IRON_ORE = new AncientOre("ancient_iron_ore", Material.ROCK);
    public static final Block REDSTONE_ORE = new AncientOre("ancient_redstone_ore", Material.ROCK);
    public static final Block GOLD_ORE = new AncientOre("ancient_gold_ore", Material.ROCK);
    public static final Block LAPIS_ORE = new AncientOre("ancient_lapis_ore", Material.ROCK);
    public static final Block DIAMOND_ORE = new AncientOre("ancient_diamond_ore", Material.ROCK);
    public static final Block EMERALD_ORE = new AncientOre("ancient_emerald_ore", Material.ROCK);
    public static final Block QUARTZ_ORE = new AncientOre("ancient_quartz_ore", Material.ROCK);
    public static final Block ANCIENT_FARMLAND = new AncientFarmland("ancient_farmland", Material.GROUND);
    public static final Block SHORT_GRASS = new ShortGrass("short_grass", Material.PLANTS);
    //Seems contradictory, but to a programming organization perspective, it makes sense
    public static final Block NEW_RED_RUNE_ORE = new AncientOre("ancient_red_rune_ore", Material.ROCK);
    public static final Block NEW_YELLOW_RUNE_ORE = new AncientOre("ancient_yellow_rune_ore", Material.ROCK);
    public static final Block NEW_GREEN_RUNE_ORE = new AncientOre("ancient_green_rune_ore", Material.ROCK);
    public static final Block NEW_BLUE_RUNE_ORE = new AncientOre("ancient_blue_rune_ore", Material.ROCK);
    public static final Block NEW_WHITE_RUNE_ORE = new AncientOre("ancient_white_rune_ore", Material.ROCK);
    public static final Block NEW_BLACK_RUNE_ORE = new AncientOre("ancient_black_rune_ore", Material.ROCK);

    public static final Block SORGHUM = new BlockSorghum("crop_sorghum");

    public static final Block GEOLUMINESCENT_OBELISK = new GeoluminescentObelisk("geoluminescent_obelisk", Material.ROCK);
    public static final Block AZTECH_PORTAL = new AzTechPortal("aztech_portal", Material.PORTAL);
    public static final Block PORTAL_CONSTRUCT = new PortalMultiblock("portal_multiblock", Material.PORTAL);
    public static final Block ANCIENT_ENERGY_PILLAR = new AncientEnergyPillar("ancient_energy_pillar", Material.ROCK);

    //rune lines. There's a lot of em
    public static final Block TEST_BLOCK = new TestBlock("test_block", Material.CIRCUITS);
    /*public static final Block RED_RUNE_LINE_DOT = new RedRuneLine("red_rune_line");
    public static final Block RED_RUNE_LINE_STRAIGHT = new RedRuneLineStraight("red_rune_line_straight");
    public static final Block RED_RUNE_LINE_LEFT_TURN = new RedRuneLineLeftTurn("red_rune_line_left_turn");
    public static final Block RED_RUNE_LINE_RIGHT_TURN = new RedRuneLineRightTurn("red_rune_line_right_turn");*/
}