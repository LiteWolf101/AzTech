package litewolf101.aztech.utils;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StringUtils;

import javax.annotation.Nullable;

public class SlaughtiveRuneData {
    private final NBTTagCompound nbt;

    public SlaughtiveRuneData() {
        this.nbt = new NBTTagCompound();
        this.nbt.setString("TargetEntity", "minecraft:pig");
    }

    public NBTTagCompound toCompoundTag()
    {
        NBTTagCompound nbttagcompound = new NBTTagCompound();

        if (!this.nbt.hasKey("TargetEntity", 8))
        {
            this.nbt.setString("TargetEntity", "minecraft:pig");
        }
        else if (!this.nbt.getString("TargetEntity").contains(":"))
        {
            this.nbt.setString("TargetEntity", (new ResourceLocation(this.nbt.getString("TargetEntity"))).toString());
        }
        return nbttagcompound;
    }

    public NBTTagCompound getNbt()
    {
        return this.nbt;
    }
}
