/*
 * Copyright (C) 2013-2014 Byron 3D Games Studio (www.b3dgs.com) Pierre-Alexandre (contact@b3dgs.com)
 * 
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA 02110-1301, USA.
 */
package com.b3dgs.warcraft.weapon;

import com.b3dgs.lionengine.core.Core;
import com.b3dgs.lionengine.core.Media;
import com.b3dgs.lionengine.game.ContextGame;
import com.b3dgs.lionengine.game.FactoryObjectGame;
import com.b3dgs.lionengine.game.SetupGame;
import com.b3dgs.lionengine.game.configurer.Configurer;
import com.b3dgs.lionengine.game.strategy.ability.attacker.WeaponModel;
import com.b3dgs.warcraft.AppWarcraft;
import com.b3dgs.warcraft.entity.Attacker;
import com.b3dgs.warcraft.entity.Entity;

/**
 * Weapon base implementation.
 * 
 * @author Pierre-Alexandre (contact@b3dgs.com)
 */
public abstract class Weapon
        extends WeaponModel<Entity, Attacker>
{
    /**
     * Get a weapon configuration file.
     * 
     * @param type The config associated class.
     * @return The media config.
     */
    protected static Media getConfig(Class<? extends Weapon> type)
    {
        return Core.MEDIA.create(AppWarcraft.WEAPONS_DIR, type.getSimpleName() + "."
                + FactoryObjectGame.FILE_DATA_EXTENSION);
    }

    /** Frame. */
    private int frame;

    /**
     * Constructor.
     * 
     * @param setup The setup reference.
     */
    protected Weapon(SetupGame setup)
    {
        super(setup);

        final Configurer configurer = setup.getConfigurer();
        setAttackFrame(configurer.getInteger("attackFrame"));
        setAttackTimer(configurer.getInteger("attackTimer"));

        final int distMin = configurer.getInteger("min", "distance");
        final int distMax = configurer.getInteger("max", "distance");
        setAttackDistance(distMin, distMax);

        final int dmgMin = configurer.getInteger("min", "damages");
        final int dmgMax = configurer.getInteger("max", "damages");
        setAttackDamages(dmgMin, dmgMax);
    }

    /**
     * Set the weapon frame.
     * 
     * @param frame The weapon frame.
     */
    public void setFrame(int frame)
    {
        this.frame = frame;
    }

    /**
     * Get the frame number.
     * 
     * @return The frame number.
     */
    public int getFrame()
    {
        return frame;
    }

    /*
     * WeaponModel
     */

    @Override
    public void prepare(ContextGame context)
    {
        // Nothing to do
    }
}
