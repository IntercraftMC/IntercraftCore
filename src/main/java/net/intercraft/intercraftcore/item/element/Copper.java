package net.intercraft.intercraftcore.item.element;

public class Copper extends ItemElement
{
    /*
     *
     * */
    public Copper()
    {
        super("cu", "copper", 0xc1810a);
        //System.out.println("Registering Copper.");
    }

//    @Override
//    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand handIn)
//    {
//        System.out.println("My name is " + this.getRegistryName());
//        return new ActionResult<>(EnumActionResult.PASS, playerIn.getHeldItem(handIn));
//    }
}