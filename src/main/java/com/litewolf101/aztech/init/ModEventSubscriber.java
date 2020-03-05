package com.litewolf101.aztech.init;

import com.google.common.base.Preconditions;
import com.litewolf101.aztech.AzTech;
import com.litewolf101.aztech.blocks.*;
import com.litewolf101.aztech.blocks.tileEntity.TEGasTrapBlock;
import com.litewolf101.aztech.blocks.tileEntity.TEObjectiveBlock;
import com.litewolf101.aztech.blocks.tileEntity.TESlaughterhouseBlock;
import com.litewolf101.aztech.items.*;
import com.litewolf101.aztech.utils.ItemTierAzTech;
import com.litewolf101.aztech.utils.NoAutomaticBlockItem;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nonnull;

import static com.litewolf101.aztech.init.ModBlocks.*;

@Mod.EventBusSubscriber(modid = com.litewolf101.aztech.AzTech.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventSubscriber {
    private static final Logger LOGGER = LogManager.getLogger(com.litewolf101.aztech.AzTech.MODID + " MES");

    @SubscribeEvent
    public static void registerBlocks(final RegistryEvent.Register<Block> event) {
        event.getRegistry().registerAll(
                    //Basic Earth
                setup(new BedrockBlock(Block.Properties.create(Material.ROCK, MaterialColor.BLACK_TERRACOTTA).noDrops().hardnessAndResistance(-1.0F, 3600000.0F).sound(SoundType.STONE)), "ancient_bedrock"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(2F, 10.0F).sound(SoundType.STONE)), "ancient_stone"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.SAND).hardnessAndResistance(2F, 10.0F).sound(SoundType.STONE)), "ancient_cobblestone"),
                setup(new Block(Block.Properties.create(Material.EARTH, MaterialColor.DIRT).hardnessAndResistance(0.5F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)), "ancient_dirt"),
                setup(new AncientGrass(Block.Properties.create(Material.ORGANIC).hardnessAndResistance(0.6F).sound(SoundType.PLANT).tickRandomly().harvestTool(ToolType.SHOVEL)), "ancient_grass"),
                setup(new Block(Block.Properties.create(Material.CLAY).hardnessAndResistance(0.6F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)), "ancient_clay"),
                setup(new GravelBlock(Block.Properties.create(Material.SAND, MaterialColor.STONE).hardnessAndResistance(0.3F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)), "ancient_gravel"),
                setup(new WetMud(Block.Properties.create(Material.CLAY, MaterialColor.BROWN).hardnessAndResistance(0.2F).sound(SoundType.GROUND).tickRandomly().harvestTool(ToolType.SHOVEL)), "wet_mud"),
                setup(new Block(Block.Properties.create(Material.ROCK, MaterialColor.ADOBE).hardnessAndResistance(1.3F, 10.0F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE)), "dry_mud"),
                setup(new AncientRock(Block.Properties.create(Material.ORGANIC, MaterialColor.SAND).hardnessAndResistance(1.6F, 4.0F).sound(SoundType.STONE).noDrops().doesNotBlockMovement().harvestTool(ToolType.PICKAXE)), "ancient_rock"),
                setup(new AncientFarmland(Block.Properties.create(Material.EARTH).tickRandomly().hardnessAndResistance(0.6F).sound(SoundType.GROUND).harvestTool(ToolType.SHOVEL)), "ancient_farmland"),
                //Ancient Stone Wall
                //Ancient Cobblestone Wall
                //Dry Mud Wall

                    //Ores
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "red_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "yellow_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "green_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "blue_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "white_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "black_rune_ore"),

                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_red_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_yellow_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_green_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_blue_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_white_rune_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_black_rune_ore"),

                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(0).harvestTool(ToolType.PICKAXE)), "ancient_coal_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "ancient_iron_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_gold_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_redstone_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "ancient_lapis_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_diamond_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "ancient_emerald_ore"),
                setup(new ModOreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "ancient_quartz_ore"),

                    //Extra Utils Ores
                //See what every other mod is doing first, and see if compatibility should be added
                //Ancient Copper Ore
                //Ancient Nickel Ore
                //Ancient Aluminum Ore
                //Ancient Silver Ore
                //Ancient Lead Ore
                //Ancient Zinc Ore
                //Ancient Platinum Ore
                //Ancient Tin Ore
                //Ancient Sodium Ore
                //Ancient Cobalt Ore
                //Ancient Titanium Ore
                //Ancient Tungsten Ore

                    //Temple Building Blocks
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_normal"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_bricks"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_chiseled"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_cracked"),

                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_slab_normal"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_slab_bricks"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_slab_chiseled"),
                setup(new SlabBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_slab_cracked"),

                setup(new StairsBlock(() -> TEMPLE_STONE_NORMAL.getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stairs_normal"),
                setup(new StairsBlock(() -> TEMPLE_STONE_BRICKS.getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stairs_bricks"),
                setup(new StairsBlock(() -> TEMPLE_STONE_CHISELED.getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stairs_chiseled"),
                setup(new StairsBlock(() -> TEMPLE_STONE_CRACKED.getDefaultState(), Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stairs_cracked"),

                setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_pillar_basic"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(4)), "temple_stone_pillar_energized"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.GLASS).harvestLevel(1).harvestTool(ToolType.PICKAXE).lightValue(15)), "temple_stone_pillar_lighting"),

                setup(new StainedGlassBlock(DyeColor.BROWN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5F, 3.7F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)), "temple_glass"),
                setup(new StainedGlassPaneBlock(DyeColor.BROWN, Block.Properties.create(Material.GLASS).hardnessAndResistance(0.5F, 3.7F).sound(SoundType.GLASS).harvestTool(ToolType.PICKAXE)), "temple_glass_pane"),

                setup(new WallBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_wall_normal"),
                setup(new WallBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_wall_bricks"),
                setup(new WallBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_wall_chiseled"),
                setup(new WallBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_stone_wall_cracked"),

                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_red_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_yellow_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_green_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_blue_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_white_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 15.5F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "temple_black_rune_block"),

                setup(new TempleLantern(Block.Properties.create(Material.ROCK).hardnessAndResistance(1F, 3F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).lightValue(15).doesNotBlockMovement()), "temple_lantern"),
                //setup(new ObeliskBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(1F, 3F).sound(SoundType.STONE).harvestTool(ToolType.PICKAXE).lightValue(15)), "geoluminescent_obelisk")

                    //Misc
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "red_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "yellow_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "green_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "blue_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "white_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "black_rune_block"),
                setup(new Block(Block.Properties.create(Material.ROCK).hardnessAndResistance(5, 6).sound(SoundType.METAL).harvestLevel(2).harvestTool(ToolType.PICKAXE)), "entropy_rune_block"),

                    //Runes
                //Red Rune Stone
                //Yellow Rune Stone
                //Green Rune Stone
                //Blue Rune Stone
                //White Rune Stone
                //Black Rune Stone
                //Void Rune Stone
                //Entropy Rune Stone

                    //Foliage
                setup(new AncientFoliage(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)), "short_grass"),
                setup(new AncientFoliage(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)), "flowered_short_grass"),
                setup(new AncientFoliage(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)), "stalk_grass"),
                setup(new AncientFoliage(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)), "dead_grass"),
                setup(new LeafPile(Block.Properties.create(Material.TALL_PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)), "leaf_pile"),
                //Tight Vines
                //Ancient Moss
                //Ancient Dry Moss

                setup(new FlowerBlock(Effects.REGENERATION, 12, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"sword_lily"),
                setup(new FlowerBlock(Effects.STRENGTH, 14, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"chocolate_cosmos"),
                setup(new FlowerBlock(Effects.INSTANT_HEALTH, 16, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"poinsettia"),
                setup(new FlowerBlock(Effects.FIRE_RESISTANCE, 13, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"honeysuckle"),
                setup(new FlowerBlock(Effects.ABSORPTION, 17, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"marigold"),
                setup(new FlowerBlock(Effects.NIGHT_VISION, 18, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"aster"),
                setup(new FlowerBlock(Effects.GLOWING, 25, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().hardnessAndResistance(0).sound(SoundType.PLANT)),"yucca"),

                setup(new LeavesBlock(Block.Properties.create(Material.LEAVES).hardnessAndResistance(0.2F).tickRandomly().sound(SoundType.PLANT)), "ahuehuete_leaves"),
                setup(new ModLogBlock(() -> STRIPPED_AHUEHUETE_LOG, MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN).hardnessAndResistance(2.0F).sound(SoundType.WOOD)),"ahuehuete_log"),
                setup(new LogBlock(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)),"stripped_ahuehuete_log"),
                setup(new ModLogBlock(() -> STRIPPED_AHUEHUETE_WOOD, MaterialColor.WOOD ,Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)),"ahuehuete_wood"),
                setup(new RotatedPillarBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F).sound(SoundType.WOOD)),"stripped_ahuehuete_wood"),
                setup(new Block(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ahuehuete_planks"),
                setup(new StairsBlock(() -> AHUEHUETE_PLANKS.getDefaultState(), Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ahuehuete_stairs"),
                setup(new SlabBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ahuehuete_slab"),
                setup(new FenceBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ahuehuete_fence"),
                setup(new FenceGateBlock(Block.Properties.create(Material.WOOD, MaterialColor.WOOD).hardnessAndResistance(2.0F, 3.0F).sound(SoundType.WOOD)), "ahuehuete_fence_gate"),
                setup(new ModdedWoddenButton(Block.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ahuehuete_button"),
                setup(new ModdedPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, Block.Properties.create(Material.WOOD, MaterialColor.WOOD).doesNotBlockMovement().hardnessAndResistance(0.5F).sound(SoundType.WOOD)), "ahuehuete_pressure_plate"),
                //Ahuehuete Sapling

                //Dead Leaves
                //Dead Wood

                //Kopok Tree

                    //Crops
                setup(new ModCropBlock(() -> ModItems.SORGHUM, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)), "sorghum_crop"),
                setup(new ModCropBlock(() -> ModItems.RICE, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)), "rice_crop"),
                setup(new ModCropBlock(() -> ModItems.TOMATO, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)), "tomato_crop"),
                setup(new ModCropBlock(() -> ModItems.CHILI_PEPPER, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)), "chili_pepper_crop"),
                setup(new ModCropBlock(() -> ModItems.ONION, Block.Properties.create(Material.PLANTS).doesNotBlockMovement().tickRandomly().hardnessAndResistance(0).sound(SoundType.CROP)), "onion_crop"),

                    //Puzzle Blocks
                setup(new ObjectiveBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 200F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "objective_block"),
                //Temple Door
                setup(new TempleLaser(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.2f, 200f).sound(SoundType.GLASS).harvestLevel(1).harvestTool(ToolType.PICKAXE).tickRandomly()), "temple_laser"),
                setup(new BeamBlock(Block.Properties.create(Material.MISCELLANEOUS).hardnessAndResistance(2000f, 2000f).sound(SoundType.CLOTH).noDrops().tickRandomly().doesNotBlockMovement()), "beam_block"),
                //Temple Material Comparator*
                //Laser Receiver*
                //Laser Passthrough
                //Temple Mirror
                //Rune Line
                setup(new RedstoneToRuneTranslator(Block.Properties.create(Material.ROCK).hardnessAndResistance(0.2f, 200f).sound(SoundType.METAL).harvestLevel(1).harvestTool(ToolType.PICKAXE)),"redstone_to_rune_translator"),
                //Rune to Redstone Translator
                setup(new SpikeTrapBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 200F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).tickRandomly()), "spike_trap_block"),
                setup(new SpikeBlock(Block.Properties.create(Material.ANVIL).hardnessAndResistance(2F, 200F).sound(SoundType.METAL).doesNotBlockMovement()), "spike_block"),
                //Temple Fire Trap
                //Temple Dart Trap
                setup(new GasTrapBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 200F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE).tickRandomly()), "gas_trap_block"),
                setup(new SlaughterhouseBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(2F, 200F).sound(SoundType.STONE).harvestLevel(1).harvestTool(ToolType.PICKAXE)), "slaughterhouse_detector")
                //Enemy Emitter
                //Temple Heal Block

                    //Really Cool Guns n Shit
                //Temple Turret Base
                //Temple Flamethrower Turret
                //Temple Laser Turret
                //Temple Harpoon Turret
                //Temple Endermatic Turret

                //Temple Shield
                //Temple Flipper

                    //Decor
                //Vase
                //Runic Vase
                //Pot
                //AzTech Plate (16 colors)
                //AzTech Cup (16 colors)
                //AzTech Bowl (16 Colors)
                //Stone Chest
                //Hidden Temple Chest
                //AzTech Table
                //AzTech Chair
                //AzTech Glass
                //AzTech Stone Mural (3 Designs, 2x4)

                    //Portal
                //AzTech Portal
                //Red Rune Holder
                //Yellow Rune Holder
                //Green Rune Holder
                //Blue Rune Holder
                //White Rune Holder
                //Black Rune Holder
                //Portal Block (Placeholder)

                    //Spawner Holders
                //Ultimate Eye
                //Ao Ao
                //Eclipse Bunny
                //Quetzalcoatl
                //Ruggerus
                //Him

                    //Machines (Saves these for later)
                //Ancient Furnace
                //Stone Press
                //Runic Reactor Core
                //Runic Reactor Wall
                //Rune Melter
                //Stone Tank
                //Runic Assembler
                //Stone Crusher
                //Runic Mobile Slayer
                //Runic Item Channel
                //Runic Generator (Okay, i promise, you dont need RF in this mod. This is *only* a compat machine)

        );
        LOGGER.debug("Registered Blocks");
    }

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        final IForgeRegistry<Item> registry = event.getRegistry();
        registry.registerAll(
                //Basic
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "red_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "yellow_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "green_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "blue_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "white_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "black_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "entropy_rune"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "mud_ball"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "mud_brick"),
                setup(new AzTechRing(new Item.Properties().group(AzTech.AT_ITEMGROUP).maxStackSize(1).maxDamage(32)), "aztech_ring"),

                //Weapons
                setup(new SwordItem(ItemTierAzTech.RED_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "red_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.YELLOW_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "yellow_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.GREEN_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "green_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.BLUE_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "blue_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.WHITE_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "white_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.BLACK_RUNE, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "black_rune_sword"),
                setup(new SwordItem(ItemTierAzTech.GREAT_AZTECH, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "great_aztech_sword"),
                setup(new SwordItem(ItemTierAzTech.ULTIMATE_AZTECH, 3, -2.4F, new Item.Properties().group(AzTech.AT_ITEMGROUP)), "ultimate_aztech_sword"),

                //Tools
                //Red Rune Pickaxe
                //Yellow Rune Pickaxe
                //Green Rune Pickaxe
                //Blue Rune Pickaxe
                //White Rune Pickaxe
                //Black Rune Pickaxe

                //Red Rune Shovel
                //Yellow Rune Shovel
                //Green Rune Shovel
                //Blue Rune Shovel
                //White Rune Shovel
                //Black Rune Shovel

                //Red Rune Axe
                //Yellow Rune Axe
                //Green Rune Axe
                //Blue Rune Axe
                //White Rune Axe
                //Black Rune Axe

                //Red Rune Hoe
                //Yellow Rune Hoe
                //Green Rune Hoe
                //Blue Rune Hoe
                //White Rune Hoe
                //Black Rune Hoe

                //Great AzTech Pickaxe
                //Great AzTech Shovel
                //Great AzTech Axe
                //Great AzTech Hoe

                //Ultimate Pickaxe
                //Ultimate Shovel
                //Ultimate Axe
                //Ultimate Hoe

                    //Food
                setup(new CustomBlockNamedItem(() -> ModBlocks.SORGHUM_CROP, new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(1).saturation(1).fastToEat().build())), "sorghum"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(5).saturation(7).build())), "sorghum_soup"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(3).saturation(4).fastToEat().effect(new EffectInstance(Effects.SPEED, 600, 2), 1).build())), "aztech_popcorn"),
                setup(new CustomBlockNamedItem(() -> ModBlocks.RICE_CROP, new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(3).saturation(4).build())), "rice"),
                setup(new CustomBlockNamedItem(() -> ModBlocks.TOMATO_CROP, new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(4).saturation(4).build())), "tomato"),
                setup(new CustomBlockNamedItem(() -> ModBlocks.CHILI_PEPPER_CROP, new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(6).saturation(3).fastToEat().effect(new EffectInstance(Effects.FIRE_RESISTANCE, 100, 1), 0.5f).build())), "chili_pepper"),
                setup(new CustomBlockNamedItem(() -> ModBlocks.ONION_CROP, new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(3).saturation(3).build())), "onion"),
                setup(new Item(new Item.Properties().group(AzTech.AT_ITEMGROUP).food(new Food.Builder().hunger(15).saturation(10).effect(new EffectInstance(Effects.ABSORPTION, 900, 1), 1).build())), "aztech_burger"),

                    //Special
                //Dungeon Locator
                setup(new Azotome(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "azotome"),
                setup(new PuzzleLinkingTool(new Item.Properties().group(AzTech.AT_ITEMGROUP)), "puzzle_linking_tool_link"),
                setup(new PuzzleUnlinkingTool(new Item.Properties()), "puzzle_linking_tool_unlink"),
                setup(new PuzzleDebugTool(new Item.Properties()), "puzzle_linking_tool_debug")
        );

        for (final Block block : ForgeRegistries.BLOCKS.getValues()) {
            final ResourceLocation blockRegistryName = block.getRegistryName();
            Preconditions.checkNotNull(blockRegistryName, "Registry Name of Block \"" + block + "\" of class \"" + block.getClass().getName() + "\"is null! This is not allowed!");

            if (!blockRegistryName.getNamespace().equals(com.litewolf101.aztech.AzTech.MODID)) {
                continue;
            }

            //In case of blocks with no auto itemblock creation
			if (block instanceof NoAutomaticBlockItem) {
				continue;
			}

            final Item.Properties properties = new Item.Properties().group(com.litewolf101.aztech.AzTech.AT_ITEMGROUP);
            final BlockItem blockItem = new BlockItem(block, properties);
            registry.register(setup(blockItem, blockRegistryName));
        }
        LOGGER.debug("Registered Items");
    }

    @SubscribeEvent
    public static void registerTileEntityTypes(@Nonnull final RegistryEvent.Register<TileEntityType<?>> event) {
        event.getRegistry().registerAll(
                setup(TileEntityType.Builder.create(TEObjectiveBlock::new, OBJECTIVE_BLOCK).build(null), "objective_block"),
                setup(TileEntityType.Builder.create(TESlaughterhouseBlock::new, SLAUGHTERHOUSE_DETECTOR).build(null), "slaughterhouse_detector"),
                setup(TileEntityType.Builder.create(TEGasTrapBlock::new, GAS_TRAP_BLOCK).build(null), "gas_trap_block")
        );
        LOGGER.debug("Registered TileEntityTypes");
    }

    /*@SubscribeEvent
    public static void registerContainerTypes(@Nonnull final RegistryEvent.Register<ContainerType<?>> event) {
        // Register your ContainerTypes here if you have them
        event.getRegistry().registerAll(
                setup(IForgeContainerType.create(ModFurnaceContainer::new), "mod_furnace")
        );
        LOGGER.debug("Registered ContainerTypes");
    }*/


    /**
     * Credits to Cadiboo for these two methods
     */
    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final String name) {
        Preconditions.checkNotNull(name, "Name to assign to entry cannot be null!");
        return setup(entry, new ResourceLocation(com.litewolf101.aztech.AzTech.MODID, name));
    }

    @Nonnull
    private static <T extends IForgeRegistryEntry<T>> T setup(@Nonnull final T entry, @Nonnull final ResourceLocation registryName) {
        Preconditions.checkNotNull(entry, "Entry cannot be null!");
        Preconditions.checkNotNull(registryName, "Registry name to assign to entry cannot be null!");
        entry.setRegistryName(registryName);
        return entry;
    }
}
