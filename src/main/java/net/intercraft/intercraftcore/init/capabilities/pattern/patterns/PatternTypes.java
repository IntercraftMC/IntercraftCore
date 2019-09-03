package net.intercraft.intercraftcore.init.capabilities.pattern.patterns;

import net.intercraft.intercraftcore.init.capabilities.pattern.PatternCreator;

public enum PatternTypes
{
    CLAY(Patterns.CLAY);

    private final PatternCreator pattern;

    PatternTypes(PatternCreator pattern)
    {

        this.pattern = pattern;
    }


    public PatternCreator getPattern()
    {
        return pattern;
    }
}
