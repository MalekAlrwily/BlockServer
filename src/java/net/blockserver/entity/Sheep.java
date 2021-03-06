package net.blockserver.entity;

import net.blockserver.level.Level;
import net.blockserver.math.Vector3d;

public class Sheep extends Entity{

    public Sheep(Vector3d pos, Level level)
    {
        super(pos, level);
    }

    @Override
    public EntityType getType()
    {
        return EntityType.SHEEP;
    }

    @Override
    public int getMaxHealth(){
        return 4;
    }

}
