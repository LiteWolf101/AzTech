package litewolf101.aztech.world.mapgen.dungeon.eye_dungeon;

import litewolf101.aztech.utils.AzTechLootTables;
import litewolf101.aztech.utils.Reference;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureMineshaftPieces;
import net.minecraft.world.gen.structure.template.PlacementSettings;
import net.minecraft.world.gen.structure.template.Template;
import net.minecraft.world.gen.structure.template.TemplateManager;
import org.lwjgl.Sys;

import java.util.List;
import java.util.Random;

import static net.minecraft.block.BlockChest.FACING;

public class EyeDungeon {

    abstract static class Piece extends StructureComponent {
        public Piece() {
        }

        public Piece(int type) {
            super(type);
        }

        protected void writeStructureToNBT(NBTTagCompound tagCompound) {
        }

        protected void readStructureFromNBT(NBTTagCompound tagCompound, TemplateManager manager) {
        }
    }

    private static EyeDungeon.Piece generateAndAddPiece(StructureComponent componentIn, List<StructureComponent> listIn, Random rand, BlockPos pos, Rotation rotation) {
        EyeDungeon.Piece piece = createRandomPiece(rand, pos, rotation);

        if (piece != null) {
            listIn.add(piece);
            piece.buildComponent(componentIn, listIn, rand);
        }
        return piece;
    }

    private static EyeDungeon.Piece createRandomPiece(Random random, BlockPos pos, Rotation rotation) {
        int i = random.nextInt(100);
        if (i >= 55) {
            if (random.nextInt(10) > 5) {
                return new EyeDungeon.RightTurn(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
            } else {
                return new EyeDungeon.LeftTurn(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
            }
        } else if (i >= 10) {
            return new EyeDungeon.Straight(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        } else return EyeDungeon.createRandomCapPiece(random, pos, rotation);
    }

    private static EyeDungeon.Piece createRandomCapPiece(Random random, BlockPos pos, Rotation rotation) {
        int i = random.nextInt(50);
        if (i >= 48) {
            return new EyeDungeon.CapPortal(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        } else if (i >= 40) {
            return new EyeDungeon.CapGarden(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        } else if (i >= 32) {
            return new EyeDungeon.CapSpawner(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        } else if (i >= 27) {
            return new EyeDungeon.CapMined(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        } else {
            return new EyeDungeon.CapNormal(pos.getX(), pos.getY(), pos.getZ(), random, rotation);
        }
    }

    private static boolean findCrossing (StructureComponent component, List<StructureComponent> listIn, BlockPos pos, Rotation rotation) {
        StructureBoundingBox structureboundingbox = new StructureBoundingBox(pos.getX() + 6, pos.getY(), pos.getZ() + 6, pos.getX() + 10, pos.getY() + 2, pos.getZ() + 10);

        if (component instanceof EyeDungeon.Straight) {
            if (rotation == Rotation.NONE) {
                structureboundingbox.minZ = pos.getZ() + 6 + 16;
                structureboundingbox.maxZ = pos.getZ() + 10 + 16;
            }
            if (rotation == Rotation.CLOCKWISE_90) {
                structureboundingbox.minX = pos.getX() + 6 - 16;
                structureboundingbox.maxX = pos.getX() + 10 - 16;
            }
            if (rotation == Rotation.CLOCKWISE_180) {
                structureboundingbox.minZ = pos.getZ() + 6 - 16;
                structureboundingbox.maxZ = pos.getZ() + 10 - 16;
            }
            if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                structureboundingbox.minZ = pos.getZ() + 6 + 16;
                structureboundingbox.maxZ = pos.getZ() + 10 + 16;
            }
        } else
        if (component instanceof EyeDungeon.LeftTurn) {
            if (rotation == Rotation.NONE) {
                structureboundingbox.minX = pos.getX() + 6 + 16;
                structureboundingbox.maxX = pos.getX() + 10 + 16;
            }
            if (rotation == Rotation.CLOCKWISE_90) {
                structureboundingbox.minZ = pos.getZ() + 6 + 16;
                structureboundingbox.maxZ = pos.getZ() + 10 + 16;
            }
            if (rotation == Rotation.CLOCKWISE_180) {
                structureboundingbox.minX = pos.getX() + 6 - 16;
                structureboundingbox.maxX = pos.getX() + 10 - 16;
            }
            if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                structureboundingbox.minZ = pos.getZ() + 6 - 16;
                structureboundingbox.maxZ = pos.getZ() + 10 - 16;
            }
        } else
        if (component instanceof EyeDungeon.RightTurn) {
            if (rotation == Rotation.NONE) {
                structureboundingbox.minX = pos.getX() + 6 - 16;
                structureboundingbox.maxX = pos.getX() + 10 - 16;
            }
            if (rotation == Rotation.CLOCKWISE_90) {
                structureboundingbox.minZ = pos.getZ() + 6 - 16;
                structureboundingbox.maxZ = pos.getZ() + 10 - 16;
            }
            if (rotation == Rotation.CLOCKWISE_180) {
                structureboundingbox.minX = pos.getX() + 6 + 16;
                structureboundingbox.maxX = pos.getX() + 10 + 16;
            }
            if (rotation == Rotation.COUNTERCLOCKWISE_90) {
                structureboundingbox.minZ = pos.getZ() + 6 + 16;
                structureboundingbox.maxZ = pos.getZ() + 10 + 16;
            }
        }

        StructureComponent intersect = StructureComponent.findIntersecting(listIn, structureboundingbox);
        System.out.println(intersect + ":" + structureboundingbox);
        if (intersect instanceof EyeDungeon.Straight || intersect instanceof EyeDungeon.LeftTurn || intersect instanceof EyeDungeon.RightTurn) {
            //System.out.println(component + " found intersect " + intersect + " at: " + structureboundingbox);
            return true;
        }
        return false;

    }
    //------------------------Extra Pieces------------------------//




    //------------------------Caps------------------------//
    public static class CapNormal extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public CapNormal (){}

        public CapNormal (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_cap_1"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        }
    }

    public static class CapMined extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public CapMined (){}

        public CapMined (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_cap_2"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        }
    }

    public static class CapSpawner extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public CapSpawner (){}

        public CapSpawner (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 10, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_cap_3"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        }
    }

    public static class CapGarden extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public CapGarden (){}

        public CapGarden (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_cap_4"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        }
    }

    public static class CapPortal extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public CapPortal (){}

        public CapPortal (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_cap_5"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
        }
    }


    //------------------------Straights And Turns------------------------//
    public static class LeftTurn extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public LeftTurn (){}

        public LeftTurn (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_left_turn"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);
            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            if (this.rotation == Rotation.NONE && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.NONE)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(16, 0, 0), Rotation.COUNTERCLOCKWISE_90);
            }
            if (this.rotation == Rotation.CLOCKWISE_90 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, 16), Rotation.NONE);
            }
            if (this.rotation == Rotation.CLOCKWISE_180 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_180)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(-16, 0, 0), Rotation.CLOCKWISE_90);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.COUNTERCLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, -16), Rotation.CLOCKWISE_180);
            }
        }
    }

    public static class RightTurn extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public RightTurn (){}

        public RightTurn (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_right_turn"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            if (this.rotation == Rotation.NONE && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.NONE)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(-16, 0, 0), Rotation.CLOCKWISE_90);
            }
            if (this.rotation == Rotation.CLOCKWISE_90 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, -16), Rotation.CLOCKWISE_180);
            }
            if (this.rotation == Rotation.CLOCKWISE_180 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_180)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(16, 0, 0), Rotation.COUNTERCLOCKWISE_90);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.COUNTERCLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, 16), Rotation.NONE);
            }
        }
    }

    public static class Straight extends EyeDungeon.Piece {
        private BlockPos pos;
        private Rotation rotation;
        public Straight (){}

        public Straight (int x, int y, int z, Random random, Rotation rotation){
            pos = new BlockPos(x, y, z);
            this.rotation = rotation;
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 7, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_straight"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(this.rotation).setIgnoreStructureBlock(true);

            if (this.rotation == Rotation.NONE) {
                template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 0), placementsettings);
            }
            if (this.rotation == Rotation.CLOCKWISE_180) {
                template.addBlocksToWorldChunk(world, this.pos.add(15, 0, 15), placementsettings);
            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90) {
                template.addBlocksToWorldChunk(world, this.pos.add(0, 0, 15), placementsettings);
            }

            generateChest(world, structureBoundingBox, random, this.pos.add(5, 1, 5), AzTechLootTables.EYE_GUARDIAN, Blocks.CHEST.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
            generateChest(world, structureBoundingBox, random, this.pos.add(5, 1, 10), AzTechLootTables.EYE_GUARDIAN, Blocks.CHEST.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
            generateChest(world, structureBoundingBox, random, this.pos.add(10, 1, 5), AzTechLootTables.EYE_GUARDIAN, Blocks.CHEST.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
            generateChest(world, structureBoundingBox, random, this.pos.add(10, 1, 10), AzTechLootTables.EYE_GUARDIAN, Blocks.CHEST.getDefaultState().withProperty(FACING, EnumFacing.NORTH));
            return true;
        }

        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            if (this.rotation == Rotation.NONE && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.NONE)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, 16), Rotation.NONE);
            } else {
                /*EyeDungeon.Crossing crossing = new EyeDungeon.Crossing(this.pos.getX(), this.pos.getY(), this.pos.getZ() + 16, rand);
                listIn.add(crossing);
                crossing.buildComponent(componentIn, listIn, rand);*/
            }
            if (this.rotation == Rotation.CLOCKWISE_90 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(-16, 0, 0), Rotation.CLOCKWISE_90);
            } else {

            }
            if (this.rotation == Rotation.CLOCKWISE_180 && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.CLOCKWISE_180)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, -16), Rotation.CLOCKWISE_180);
            } else {

            }
            if (this.rotation == Rotation.COUNTERCLOCKWISE_90  && !EyeDungeon.findCrossing(componentIn, listIn, this.pos, Rotation.COUNTERCLOCKWISE_90)) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(16, 0, 0), Rotation.COUNTERCLOCKWISE_90);
            } else {

            }
        }
    }

    //------------------------Entrance and Center Room------------------------//
    public static class CenterRoom extends EyeDungeon.Piece {
        private BlockPos pos;

        public CenterRoom (){}

        public CenterRoom (int x, int y, int z, Random random){
            pos = new BlockPos(x, y, z);
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 12, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "eye_center"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(Rotation.NONE).setIgnoreStructureBlock(true);
            template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            return true;
        }
        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(16, 0, 0), Rotation.COUNTERCLOCKWISE_90);
            EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, 16), Rotation.NONE);
            EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(-16, 0, 0), Rotation.CLOCKWISE_90);
            EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, -16), Rotation.CLOCKWISE_180);
        }

    }

    public static class Entrance extends EyeDungeon.Piece {
        private BlockPos pos;

        public Entrance (){}

        public Entrance (int x, int y, int z, Random random){
            pos = new BlockPos(x, y, z);
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 8, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer ,new ResourceLocation(Reference.MODID,  "eye_dungeon"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(Rotation.NONE).setIgnoreStructureBlock(true);
            template.addBlocksToWorldChunk(world, this.pos, placementsettings);



            System.out.println(this.pos); //debug



            return true;
        }
        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            EyeDungeon.CenterRoom center = new EyeDungeon.CenterRoom(this.pos.getX(), this.pos.getY() - 12, this.pos.getZ(), rand);
            listIn.add(center);
            if (listIn.contains(center)) {
            }
            center.buildComponent(componentIn, listIn, rand);
        }

    }

    public static class Crossing extends EyeDungeon.Piece {
        private BlockPos pos;

        public Crossing (){}

        public Crossing (int x, int y, int z, Random random){
            pos = new BlockPos(x, y, z);
            this.boundingBox = new StructureBoundingBox(x, y, z, x + 16, y + 12, z + 16);
        }

        @Override
        public boolean addComponentParts(World world, Random random, StructureBoundingBox structureBoundingBox) {
            MinecraftServer mcServer = world.getMinecraftServer();
            TemplateManager templateManager = mcServer.getWorld(world.provider.getDimensionType().getId()).getStructureTemplateManager();
            Template template = templateManager.getTemplate(mcServer, new ResourceLocation(Reference.MODID,  "ed_crossing"));
            PlacementSettings placementsettings = (new PlacementSettings()).setIgnoreEntities(true).setRotation(Rotation.NONE).setIgnoreStructureBlock(true);
            template.addBlocksToWorldChunk(world, this.pos, placementsettings);
            return true;
        }
        @Override
        public void buildComponent(StructureComponent componentIn, List<StructureComponent> listIn, Random rand) {
            /*StructureBoundingBox south = new StructureBoundingBox(pos.getX() + 9, pos.getY(), pos.getZ() + 6 + 16, pos.getX() + 10, pos.getY() + 2, pos.getZ() + 10 + 16);
            StructureBoundingBox west = new StructureBoundingBox(pos.getX() + 9 - 16, pos.getY(), pos.getZ() + 6, pos.getX() + 10 - 16, pos.getY() + 2, pos.getZ() + 10);
            StructureBoundingBox north = new StructureBoundingBox(pos.getX() + 9, pos.getY(), pos.getZ() + 6 - 16, pos.getX() + 10, pos.getY() + 2, pos.getZ() + 10 - 16);
            StructureBoundingBox east = new StructureBoundingBox(pos.getX() + 9, pos.getY(), pos.getZ() + 6 + 16, pos.getX() + 10, pos.getY() + 2, pos.getZ() + 10 + 16);

            if (StructureComponent.findIntersecting(listIn, south) == null) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, 16), Rotation.NONE);
            }
            if (StructureComponent.findIntersecting(listIn, west) == null) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(-16, 0, 0), Rotation.CLOCKWISE_90);
            }
            if (StructureComponent.findIntersecting(listIn, north) == null) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(0, 0, -16), Rotation.CLOCKWISE_180);
            }
            if (StructureComponent.findIntersecting(listIn, east) == null) {
                EyeDungeon.generateAndAddPiece(componentIn, listIn, rand, this.pos.add(16, 0, 0), Rotation.COUNTERCLOCKWISE_90);
            }*/
            //System.out.println("Yaddadadad");
        }
    }

}
