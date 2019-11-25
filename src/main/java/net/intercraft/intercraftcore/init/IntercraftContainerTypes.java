package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.inventory.container.ContainerChunkloaderTimer;
import net.intercraft.intercraftcore.inventory.container.ContainerItemItemStack;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.network.IContainerFactory;

import javax.annotation.Nonnull;

public class IntercraftContainerTypes
{

    public static final ContainerType<ContainerChunkloaderTimer> CHUNKLOADER_TIMER_INTERFACE;
    public static final ContainerType<ContainerItemItemStack> ITEMITEMSTACK_CONTAINER;


    static {
        CHUNKLOADER_TIMER_INTERFACE = buildCT("chunkloader_timer", ContainerChunkloaderTimer::new);
        ITEMITEMSTACK_CONTAINER     = buildCT("item_itemstack_container", ContainerItemItemStack::new);
    }


    public static void register()
    {
        registerContainerType(CHUNKLOADER_TIMER_INTERFACE,ITEMITEMSTACK_CONTAINER);
    }


    protected static void registerContainerType(@Nonnull ContainerType<?>...containers)
    {
        for (ContainerType<?> container : containers) {
            if (container != null)
                RegistrationHandler.containerTypes.add(container);
        }
    }


    private static ContainerType buildCT(String name, IContainerFactory<?> containerFactory)
    {
        return IForgeContainerType.create(containerFactory).setRegistryName(name);
    }


}
