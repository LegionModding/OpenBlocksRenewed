package com.legionmodding.openblocksrenewed.util;

import com.google.common.collect.Lists;

import com.legionmodding.openblocksrenewed.handler.ConfigHandler;
import com.legionmodding.openblocksrenewed.handler.registry.FluidRegistry;
import com.legionmodding.yalm.fluid.ConversionFactory;
import com.legionmodding.yalm.fluid.IExperienceConverter;
import com.legionmodding.yalm.util.EnchantmentUtil;

import net.minecraftforge.fluids.FluidStack;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public class ExperienceUtil
{
    public static final int XP_PER_BOTTLE = 8;


    private static class FluidXP implements IExperienceConverter
    {

        @Override
        public int fluidToExperience(int fluid)
        {
            return fluid / ConfigHandler.XP_TO_LIQUID_RATIO.get();
        }

        @Override
        public int experienceToFluid(int xp)
        {
            return xp * ConfigHandler.XP_TO_LIQUID_RATIO.get();
        }

    }
    public static final IExperienceConverter xpConverter = new FluidXP();

    private static final List<ConversionFactory> converters = Lists.newArrayList();

    public static void initializeFromConfig()
    {
        converters.add(new ConversionFactory(new FluidStack(FluidRegistry.XP_FLUID.get(), 1000), xpConverter));
    }

    public static FluidStack[] getAcceptedFluids()
    {
        final FluidStack[] result = new FluidStack[converters.size()];
        int i = 0;

        for (ConversionFactory e : converters)
        {
            result[i++] = e.fluid.copy();
        }

        return result;
    }

    public static Optional<IExperienceConverter> getConverter(FluidStack stack)
    {
        if (stack == null)
        {
            return Optional.empty();
        }

        for (ConversionFactory e : converters)
        {
            if (e.matches(stack))
            {
                return e.optionalConverter;
            }
        }

        return Optional.empty();
    }

    public static int getMaxPossibleFluidForLevel(int level)
    {
        final int xp = EnchantmentUtil.getExperienceForLevel(level);
        return getMaxPossibleFluidForXp(xp);
    }

    public static int getMaxPossibleFluidForXp(final int xp)
    {
        int result = 0;

        for (ConversionFactory e : converters)
        {
            result = Math.max(result, e.converter.experienceToFluid(xp));
        }

        return result;
    }

    public static final Function<FluidStack, FluidStack> FLUID_TO_LEVELS = input ->
    {
        if (input == null)
        {
            return null;
        }

        final Optional<IExperienceConverter> maybeConverter = getConverter(input);

        return maybeConverter.map(converter ->
        {
            final FluidStack result = input.copy();
            // display levels instead of actual xp fluid level
            result.setAmount(EnchantmentUtil.getLevelForExperience(converter.fluidToExperience(input.getAmount())));
            return result;
        }).orElse(null);
    };
}
