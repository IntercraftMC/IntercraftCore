package net.intercraft.intercraftcore.item.masks;

import net.intercraft.intercraftcore.client.models.ModelGlasses;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemGlasses extends Item
{

    private final ResourceLocation texture;

    public ItemGlasses(String name, ResourceLocation texture)
    {
        super(new Properties().group(IntercraftItemGroups.VANITY).maxStackSize(1));

        this.texture = texture;
        setRegistryName(name);
    }

    public void onEquipped(String identifier, LivingEntity entityLivingBase)
    {

    }

    public void onUnequipped(String identifier, LivingEntity entityLivingBase)
    {

    }

    public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
    {

    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {

        ICurio curio = new ICurio() {

            @Override
            public void playEquipSound(LivingEntity entityLivingBase)
            {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.NEUTRAL, 1.0f, 5.0f);
            }


            @Override
            public void onEquipped(String identifier, LivingEntity entityLivingBase)
            {
                ItemGlasses.this.onEquipped(identifier, entityLivingBase);
                playEquipSound(entityLivingBase);
            }

            @Override
            public void onUnequipped(String identifier, LivingEntity entityLivingBase)
            {
                ItemGlasses.this.onUnequipped(identifier,entityLivingBase);
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                ItemGlasses.this.onCurioTick(identifier,index,entityLivingBase);
            }

            @Override
            public void doRender(String identifier, LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {

                Minecraft.getInstance().getTextureManager().bindTexture(texture);

                RenderHelper.followHeadRotations(entitylivingbaseIn, ModelGlasses.modelGlasses.mask);
                ModelGlasses.modelGlasses.render(entitylivingbaseIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scale);


            }

            @Override
            public boolean canRightClickEquip()
            {
                return true;
            }

            @Override
            public boolean hasRender(String identifier, LivingEntity entityLivingBase)
            {
                return true;
            }
        };

        ICapabilityProvider provider = new ICapabilityProvider() {
            private final LazyOptional<ICurio> curioOpt = LazyOptional.of(() -> curio);

            @Nonnull
            @Override
            public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side)
            {
                return CuriosCapability.ITEM.orEmpty(cap, curioOpt);
            }
        };
        return provider;
    }
}
