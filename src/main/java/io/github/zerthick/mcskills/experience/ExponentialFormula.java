package io.github.zerthick.mcskills.experience;

public class ExponentialFormula implements IExperienceFormula {
    private float multiplier;
    private float exponent;
    private float base;

    public ExponentialFormula(float multiplier, float exponent, float base) {
        this.multiplier = multiplier;
        this.exponent = exponent;
        this.base = base;
    }

    @Override
    public long getLevelExperience(int level) {
        return Math.round(multiplier * Math.pow(level, exponent) + base);
    }
}
