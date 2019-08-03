package litewolf101.aztech.utils.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by LiteWolf101 on 10/19/2018.
 */
public class EnumPortalPart {

	public enum EnumType implements IStringSerializable {
		BOTTOM(0, "bottom"),
		MIDDLE(1, "middle"),
		TOP(2, "top"),
		BRACE(3, "brace");

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
