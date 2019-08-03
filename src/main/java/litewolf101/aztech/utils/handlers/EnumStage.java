package litewolf101.aztech.utils.handlers;

import net.minecraft.util.IStringSerializable;

/**
 * Created by LiteWolf101 on 9/21/2018.
 */
public class EnumStage {

	public enum EnumType implements IStringSerializable {
		STAGE_0(0, "stage_0"),
		STAGE_1(1, "stage_1"),
		STAGE_2(2, "stage_2"),
		STAGE_3(3, "stage_3"),
		STAGE_4(4, "stage_4"),
		STAGE_5(5, "stage_5"),
		STAGE_6(6, "stage_6");

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
