package litewolf101.aztech.objects.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;

public class RuneLine extends Block {

    public static final PropertyBool ACTIVATED = PropertyBool.create("activated");

    public RuneLine() {
        super(Material.CIRCUITS);
        setSoundType(SoundType.STONE);
        setHarvestLevel("pickaxe", 1);
        setHardness(1f);
        setResistance(100f);
    }

}
