package litewolf101.aztech.utils.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumAzTechPlantType {

	public enum EnumType implements IStringSerializable {
		NORMAL(0, "1"),
		STALKY(1, "2"),
		FLAT(2, "3"),
		THICC(3, "4");

		private static final EnumType[] META_LOOKUP = new EnumType[values().length];

		static {
			for(EnumType enumtype : values()) {
				META_LOOKUP[enumtype.getMeta()] = enumtype;
			}
		}

		private final int meta;
		private final String name, unlocializedName;

		EnumType(int meta, String name) {
			this(meta, name, name);
		}

		EnumType(int meta, String name, String unlocializedName) {
			this.meta = meta;
			this.name = name;
			this.unlocializedName = unlocializedName;
		}

		public static EnumType byMetadata(int meta) {
			return META_LOOKUP[meta];
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getUnlocializedName() {
			return this.unlocializedName;
		}

		@Override
		public String toString() {
			return this.name;
		}
	}

}
