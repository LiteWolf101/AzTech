package litewolf101.aztech.utils.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class EnumDirectional {
    public static enum EnumFacing implements IStringSerializable
    {
        NORTH(0, "north"),
        EAST(1, "east"),
        SOUTH(2, "south"),
        WEST(3, "west"),
        UP(4, "up"),
        DOWN(5, "down");

        private static final EnumFacing[] META_LOOKUP = new EnumFacing[values().length];
        private final int meta;
        private final String name, unlocializedName;

        private EnumFacing(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumFacing(int meta, String name, String unlocializedName)
        {
            this.meta = meta;
            this.name = name;
            this.unlocializedName = unlocializedName;
        }

        @Override
        public String getName()
        {
            return this.name;
        }

        public int getMeta()
        {
            return this.meta;
        }

        public String getUnlocializedName()
        {
            return this.unlocializedName;
        }

        @Override
        public String toString()
        {
            return this.name;
        }

        public static EnumFacing byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumFacing enumFacing : values())
            {
                META_LOOKUP[enumFacing.getMeta()] = enumFacing;
            }
        }
    }
}