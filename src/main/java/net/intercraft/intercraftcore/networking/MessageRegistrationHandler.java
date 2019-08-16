package net.intercraft.intercraftcore.networking;

public class MessageRegistrationHandler
{
    private int id = 0;




    public static void register()
    {
        IntercraftPacketHandler.INSTANCE.registerMessage(0, MessageTreeTap.class,
                MessageTreeTap::encode,
                MessageTreeTap::new,
                MessageTreeTap::handle
                );


    }



    private void register(Class clazz)
    {



        id++;
    }



}
