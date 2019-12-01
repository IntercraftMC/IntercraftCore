package net.intercraft.intercraftcore.advancement;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.intercraft.intercraftcore.Reference;
import net.minecraft.advancements.ICriterionTrigger;
import net.minecraft.advancements.PlayerAdvancements;
import net.minecraft.advancements.criterion.CriterionInstance;
import net.minecraft.advancements.criterion.DamageSourcePredicate;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.ResourceLocation;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class DeathByTrigger implements ICriterionTrigger<DeathByTrigger.Instance>
{
    private static final ResourceLocation ID = new ResourceLocation(Reference.MODID,"death_by");
    private final Map<PlayerAdvancements, DeathByTrigger.Listeners> listeners = Maps.newHashMap();

    @Override
    public ResourceLocation getId()
    {
        return ID;
    }

    @Override
    public void addListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<DeathByTrigger.Instance> listener)
    {
        DeathByTrigger.Listeners deathBy$listeners = this.listeners.get(playerAdvancementsIn);
        if (deathBy$listeners == null) {
            deathBy$listeners = new DeathByTrigger.Listeners(playerAdvancementsIn);
            this.listeners.put(playerAdvancementsIn, deathBy$listeners);
        }

        deathBy$listeners.add(listener);
    }

    @Override
    public void removeListener(PlayerAdvancements playerAdvancementsIn, ICriterionTrigger.Listener<DeathByTrigger.Instance> listener)
    {
        DeathByTrigger.Listeners deathBy$listeners = this.listeners.get(playerAdvancementsIn);
        if (deathBy$listeners != null) {
            deathBy$listeners.remove(listener);
            if (deathBy$listeners.isEmpty()) {
                this.listeners.remove(playerAdvancementsIn);
            }
        }
    }

    @Override
    public void removeAllListeners(PlayerAdvancements playerAdvancementsIn)
    {
        this.listeners.remove(playerAdvancementsIn);
    }

    @Override
    public DeathByTrigger.Instance deserializeInstance(JsonObject json, JsonDeserializationContext context)
    {
        DamageSourcePredicate damagePredicate = DamageSourcePredicate.deserialize(json.get("source"));

        return new DeathByTrigger.Instance(damagePredicate);
    }


    public void trigger(ServerPlayerEntity player, DamageSource source)
    {
        DeathByTrigger.Listeners deathBy$listeners = this.listeners.get(player.getAdvancements());
        if (deathBy$listeners != null) {
            deathBy$listeners.trigger(player,source);
        }
    }


    public static class Instance extends CriterionInstance
    {
        private final DamageSourcePredicate killingBlow;

        public Instance(DamageSourcePredicate killingBlow)
        {
            super(DeathByTrigger.ID);
            this.killingBlow = killingBlow;
        }




        public boolean test(ServerPlayerEntity player, DamageSource source)
        {
            return !this.killingBlow.test(player,source);
        }



        @Override
        public JsonElement serialize()
        {
            JsonObject json = new JsonObject();
            json.add("source",this.killingBlow.serialize());

            return json;

        }
    }


    static class Listeners
    {
        private final PlayerAdvancements playerAdvancements;
        //private final Set<Listener<DeathByTrigger.Instance>> listeners = Sets.newHashSet();
        private final Set<ICriterionTrigger.Listener<DeathByTrigger.Instance>> listeners = Sets.newHashSet();//Sets.<ICriterionTrigger.Listener<DeathByTrigger.Instance>>newHashSet();

        public Listeners(PlayerAdvancements playerAdvancementsIn)
        {
            this.playerAdvancements = playerAdvancementsIn;
        }

        public void add(ICriterionTrigger.Listener<DeathByTrigger.Instance> listener)
        {
            this.listeners.add(listener);
        }

        public void remove(ICriterionTrigger.Listener<DeathByTrigger.Instance> listener)
        {
            this.listeners.remove(listener);
        }

        public boolean isEmpty()
        {
            return this.listeners.isEmpty();
        }

        public void trigger(ServerPlayerEntity player, DamageSource source)
        {
            List<ICriterionTrigger.Listener<DeathByTrigger.Instance>> list = null;

            for(ICriterionTrigger.Listener<DeathByTrigger.Instance> listener : this.listeners) {
                if (listener.getCriterionInstance().test(player, source)) {
                    if (list == null) {
                        list = Lists.newArrayList();
                    }

                    list.add(listener);
                }
            }

            if (list != null) {
                for(ICriterionTrigger.Listener<DeathByTrigger.Instance> listener1 : list) {
                    listener1.grantCriterion(this.playerAdvancements);
                }
            }
        }
    }
}
