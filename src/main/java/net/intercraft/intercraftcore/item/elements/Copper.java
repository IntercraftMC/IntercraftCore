package net.intercraft.intercraftcore.item.elements;

/*import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;*/

public class Copper extends ElementItemBase {
    /*
    *
    * */
    public Copper() {

        super("cu","copper",0xffb732);
        //System.out.println("Registering Copper.");
    }

    /*@Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn) {
        System.out.println("My name is "+this.getRegistryName());

        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
    }*/
}