package net.intercraft.intercraftcore.item.masks;

import net.intercraft.intercraftcore.Reference;
import net.intercraft.intercraftcore.init.IntercraftItemGroups;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import top.theillusivec4.curios.api.capability.CuriosCapability;
import top.theillusivec4.curios.api.capability.ICurio;


import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class ItemMask extends Item
{

    private final ResourceLocation texture;

    public ItemMask(String name, ResourceLocation texture)
    {
        super(new Item.Properties().group(IntercraftItemGroups.VANITY).maxStackSize(1));

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



    public boolean hidesIdentity()
    {
        return true;
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt)
    {

        ICurio curio = new ICurio() {

            @Override
            public void playEquipSound(LivingEntity entityLivingBase)
            {
                entityLivingBase.world.playSound(null, entityLivingBase.getPosition(), SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, SoundCategory.NEUTRAL, 1.0f, 1.0f);
            }

            @Override
            public void onEquipped(String identifier, LivingEntity entityLivingBase)
            {
                ItemMask.this.onEquipped(identifier, entityLivingBase);
                playEquipSound(entityLivingBase);

                if (hidesIdentity()) {
                    if (entityLivingBase instanceof PlayerEntity) {
                        entityLivingBase.sendMessage(new TranslationTextComponent("info."+Reference.MODID+".identity.hidden"));

                        //((PlayerEntity) entityLivingBase).addStat();
                        entityLivingBase.addTag(Reference.MODID+":name_hidden");

                    }
                }
            }

            @Override
            public void onUnequipped(String identifier, LivingEntity entityLivingBase)
            {
                ItemMask.this.onUnequipped(identifier,entityLivingBase);

                if (hidesIdentity()) {
                    if (entityLivingBase instanceof PlayerEntity) {
                        entityLivingBase.sendMessage(new TranslationTextComponent("info."+Reference.MODID+".identity.shown"));
                        //((PlayerEntity) entityLivingBase).addStat();
                        entityLivingBase.removeTag(Reference.MODID+":name_hidden");

                    }
                }
            }

            @Override
            public void onCurioTick(String identifier, int index, LivingEntity entityLivingBase)
            {
                ItemMask.this.onCurioTick(identifier,index,entityLivingBase);
            }

            @Override
            public void doRender(String identifier, LivingEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale)
            {

                Minecraft.getInstance().getTextureManager().bindTexture(texture);

                ICurio.RenderHelper.followHeadRotations(entitylivingbaseIn,ModelMask.modelMask.mask);
                ModelMask.modelMask.render(entitylivingbaseIn,limbSwing,limbSwingAmount,ageInTicks,netHeadYaw,headPitch,scale);


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

        ICapabilityProvider provider = new ICapabilityProvider()
        {
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
