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

package baritone.api;

import baritone.api.utils.SettingsUtil;
import net.minecraft.world.entity.PathfinderMob;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Exposes the {@link IBaritoneProvider} instance and the {@link Settings} instance for API usage.
 *
 * @author Brady
 * @since 9/23/2018
 */
public final class BaritoneAPI {

    public static final List<IBaritone> allBaritones = new ArrayList<>();
//    private static final IBaritoneProvider provider;
    private static final Settings settings;

    static {
        settings = new Settings();
        SettingsUtil.readAndApply(settings);

//        try {
//            provider = (IBaritoneProvider) Class.forName("baritone.BaritoneProvider").newInstance();
//        } catch (ReflectiveOperationException ex) {
//            throw new RuntimeException(ex);
//        }
    }

    public static IBaritone getBaritoneForPlayer(PathfinderMob player){
        for (IBaritone baritone : allBaritones) {
            if (Objects.equals(player, baritone.getPlayerContext().mob())) {
                return baritone;
            }
        }
        return null;
    }

    public static Settings getSettings() {
        return BaritoneAPI.settings;
    }
}
