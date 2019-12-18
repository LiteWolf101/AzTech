package litewolf101.aztech.tileentity;

import litewolf101.aztech.init.BlocksInit;
import static litewolf101.aztech.objects.blocks.BlockSlaughtiveRune.STAGE;

import litewolf101.aztech.utils.SlaughtiveRuneData;
import litewolf101.aztech.utils.handlers.EnumStage;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.entity.passive.EntityPig;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ITickable;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.chunk.storage.AnvilChunkLoader;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import javax.annotation.Nullable;
import java.util.List;

/**
 * Created by LiteWolf101 on 9/30/2018.
 */
public class TESlaughtiveRune0 extends TileEntity implements ITickable {
	//TODO replace with target entity logic
	private final SlaughtiveRuneData entityData = new SlaughtiveRuneData();

	@Nullable
	private ResourceLocation getEntityId()
	{
		String s = this.entityData.getNbt().getString("TargetEntity");
		return StringUtils.isNullOrEmpty(s) ? null : new ResourceLocation(s);
	}

	private Entity getEntity(){
		Entity targetEntity = EntityList.createEntityByIDFromName(this.getEntityId(), world);
		return targetEntity;
	}

	@Override
	public void update() {
		AxisAlignedBB aabb = new AxisAlignedBB(pos, pos.add(1, 1, 1)).grow(5D, 2D, 5D);
		List<Entity> nearbyEntities = world.getEntitiesWithinAABB(this.getEntity().getClass(), aabb);
		for(Entity entity : nearbyEntities) {
			if (entity instanceof EntityLiving) {
				if(((EntityLiving) entity).deathTime > 0) {
					if (((EntityLiving) entity).deathTime == 1) {
						world.setBlockState(pos, BlocksInit.SLAUGHTIVE_RUNE.getDefaultState().withProperty(STAGE, EnumStage.EnumType.STAGE_1));
					}
				}
			}
		}
	}

	@Override
	public void readFromNBT(NBTTagCompound compound) {
		super.readFromNBT(compound);
	}

	@Override
	public NBTTagCompound writeToNBT(NBTTagCompound compound) {
		ResourceLocation resourceLocation = this.getEntityId();

		if (resourceLocation == null) {
			return compound;
		} else {
			compound.setString("TargetEntity", this.getEntityId().toString());
		}
		return super.writeToNBT(compound);
	}
}