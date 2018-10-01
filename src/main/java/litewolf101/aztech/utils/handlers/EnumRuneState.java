package litewolf101.aztech.utils.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class EnumRuneState {
    public static enum EnumType implements IStringSerializable
    {
        INACTIVE(0, "inactive"),
        ACTIVE(1, "active");

        private static final EnumType[] META_LOOKUP = new EnumType[values().length];
        private final int meta;
        private final String name, unlocializedName;

        private EnumType(int meta, String name)
        {
            this(meta, name, name);
        }

        private EnumType(int meta, String name, String unlocializedName)
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

        public static EnumType byMetadata(int meta)
        {
            return META_LOOKUP[meta];
        }

        static
        {
            for(EnumType enumtype : values())
            {
                META_LOOKUP[enumtype.getMeta()] = enumtype;
            }
        }
    }
}
