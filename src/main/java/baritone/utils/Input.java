/*
 * This file is part of Baritone.
 *
 * Baritone is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Baritone is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with Baritone.  If not, see <https://www.gnu.org/licenses/>.
 */

package baritone.utils;

import net.minecraft.world.phys.Vec2;

public class Input {
    public float leftImpulse;
    public float forwardImpulse;
    public boolean up;
    public boolean down;
    public boolean left;
    public boolean right;
    public boolean jumping;
    public boolean shiftKeyDown;

    public Input() {
    }

    public void tick(boolean var1) {
    }

    public Vec2 getMoveVector() {
        return new Vec2(this.leftImpulse, this.forwardImpulse);
    }

    public boolean hasForwardImpulse() {
        return this.forwardImpulse > 1.0E-5F;
    }
}

