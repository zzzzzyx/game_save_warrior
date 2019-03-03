package skill;

public abstract class AbstractSkill {
    public int level = 1;
    protected abstract String getSkillName();
    public String getName(){
        return this.getSkillName()+"("+this.level+"级)";
    }
    public abstract String getDescription();
    public abstract String getEnhanceDescription();
}
