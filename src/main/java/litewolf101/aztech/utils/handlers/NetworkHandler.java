package litewolf101.aztech.utils.handlers;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.storage.WorldInfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class NetworkHandler {
    public static List<BlockPos> runePosList = new ArrayList<BlockPos>();
    public static List<BlockPos> runePowerSourcePosList = new ArrayList<BlockPos>();
    public static int id;
    public static List idIlist = new ArrayList();
    public NetworkHandler(){}

    public NetworkHandler(int id, List<BlockPos> list, List<BlockPos> list1) {
        list = this.runePosList;
        list1 = this.runePowerSourcePosList;
        id = this.id;
    }

    /*public static NetworkHandler createNetwork(List runeList, List runePowerList) {
        if (!runeList.isEmpty()) {
            for (int i = 0; i <= runeList.size(); i++) {
                runePosList.add((BlockPos) runeList.get(i));
            }
            for (int i = 0; i <= runePowerList.size(); i++) {
                runePowerSourcePosList.add((BlockPos) runePowerList.get(i));
            }
            if ()
        }
    }

    public static int getId(NetworkHandler network) {

    }
    public static boolean compareNetworks(NetworkHandler thisNetwork, NetworkHandler otherNetwork) {

    }*/

    public List getRuneBlockPosList () {
        return this.runePosList;
    }

    public List getRunePowerPosList () {
        return this.runePowerSourcePosList;
    }

    public NBTTagCompound writeToNBT (NBTTagCompound compound) {
        NBTTagList runeList = new NBTTagList();
        //compound.setIntArray("pos", );
        //runeList.set();
        compound.setInteger("dim", 0);
        //compound.setTag("rune_line_list", );
        return compound;
    }

    public static void createNetworkFile(String path, WorldInfo info) {
        if (path != null) {
            File file = new File(path, "networks.nbt");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                    System.out.println("Network file not found. Creating a new network file for world:" + info.getWorldName());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
