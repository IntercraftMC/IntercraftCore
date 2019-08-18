package net.intercraft.intercraftcore.init.capabilities.ore_veins;

import java.util.HashMap;
import java.util.Map;

public class OreVeins implements IOreVeins
{

    //private VeinTypes[] veins = VeinTypes.values();
    //private List<VeinTypes> veins = Arrays.asList(VeinTypes.values());
    private Map<String, VeinTypes> veins = new HashMap<>();

    public OreVeins()
    {
        for (int i=0;i<VeinTypes.values().length;i++) {
            VeinTypes type = VeinTypes.values()[i];
            veins.put(type.getName(),type);
        }
    }



    @Override
    public double getWeight(VeinTypes type)
    {
        //Chunk f = Minecraft.getInstance().player.world.getChunk(1,1);



        //return VeinTypes.valueOf(type.toString()).getWeight();
        //return ((VeinTypes) veins).valueOf(type.toString()).getWeight();

        /*for (int i=0;i<veins.length;i++)
            if (veins[i] == type) {
                type = veins[i];
                break;
            }

        return type.getWeight();*/

        return veins.get(type.getName()).getWeight();

        //return veins.get(1).getWeight();
    }

    @Override
    public String getName(VeinTypes type)
    {
        return this.veins.get(type.getName()).getName();
    }

    @Override
    public void setWeight(VeinTypes type, double weight)
    {
        this.veins.get(type.getName()).setWeight(weight);
    }
}
