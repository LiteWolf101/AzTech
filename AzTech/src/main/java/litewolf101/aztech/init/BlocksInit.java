package litewolf101.aztech.init;

import litewolf101.aztech.objects.blocks.BlockRuneOre;
import litewolf101.aztech.objects.blocks.BlockSlaughtiveRune;
import litewolf101.aztech.objects.blocks.BlockTempleStone;
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
}
