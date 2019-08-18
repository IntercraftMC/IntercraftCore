package net.intercraft.intercraftcore.init.capabilities.ore_veins;

public interface IOreVeins
{

    double getWeight(VeinTypes type);

    String getName(VeinTypes type);

    void setWeight(VeinTypes type, double weight);

}
