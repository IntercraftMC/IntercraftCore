package net.intercraft.intercraftcore.init;

import net.intercraft.intercraftcore.Authors;
import net.intercraft.intercraftcore.advancement.DeathByTrigger;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class IntercraftCriteriaTriggers
{

    private static Method CriterionRegister;

    public static final DeathByTrigger DEATH_BY_TRIGGER;

    static {
        DEATH_BY_TRIGGER = (DeathByTrigger) registerTrigger(new DeathByTrigger());
    }




    private static <T extends ICriterionInstance> ICriterionTrigger<T> registerTrigger(ICriterionTrigger<T> trigger)
    {
        if (CriterionRegister == null) {
            CriterionRegister = ObfuscationReflectionHelper.findMethod(CriteriaTriggers.class, "register", ICriterionTrigger.class);
            CriterionRegister.setAccessible(true);
        }
        try {
            trigger = (ICriterionTrigger<T>) CriterionRegister.invoke(null,trigger);
        } catch (IllegalAccessException | InvocationTargetException err) {
            System.out.println("Failed to register trigger " + trigger.getId() + "! "+ Authors.SIMON.fault());
            err.printStackTrace();
        }

        return trigger;
    }

}
