package io.github.zerthick.mcskills.experience;

public class LinearFormula implements IExperienceFormula {
    private float base;
    private float multiplier;

    public LinearFormula(float base, float multiplier) {
        this.base = base;
        this.multiplier = multiplier;
    }

    @Override
    public long getLevelExperience(int level) {
        return Math.round(base + (level * multiplier));
    }
}
