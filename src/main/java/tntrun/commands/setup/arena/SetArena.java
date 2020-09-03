/**
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 *
 */

package tntrun.commands.setup.arena;

import org.bukkit.entity.Player;

import tntrun.TNTRun;
import tntrun.arena.Arena;
import tntrun.commands.setup.CommandHandlerInterface;
import tntrun.selectionget.PlayerCuboidSelection;
import tntrun.selectionget.PlayerSelection;
import tntrun.utils.Utils;

public class SetArena implements CommandHandlerInterface {

	private TNTRun plugin;
	private PlayerSelection selection;
	public SetArena(TNTRun plugin, PlayerSelection selection) {
		this.plugin = plugin;
		this.selection = selection;
	}

	@Override
	public boolean handleCommand(Player player, String[] args) {
		Arena arena = plugin.amanager.getArenaByName(args[0]);
		if (arena != null) {
			if (arena.getStatusManager().isArenaEnabled()) {
				player.sendMessage("§7[§6TNTRun§7] §cPlease disable arena §6/trsetup disable " + args[0]);
				return true;
			}
			PlayerCuboidSelection sel = selection.getPlayerSelection(player);
			if (sel != null) {
				arena.getStructureManager().setArenaPoints(sel.getMinimumLocation(), sel.getMaximumLocation());
				player.sendMessage("§7[§6TNTRun§7] §7Arena §6" + args[0] + "§7 set");
				if (Utils.debug()) {
					plugin.getLogger().info("Arena " + arena.getArenaName() + " min point: " + sel.getMinimumLocation().toVector().toString());
					plugin.getLogger().info("Arena " + arena.getArenaName() + " max point: " + sel.getMaximumLocation().toVector().toString());
				}
			} else {
				player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c locations are wrong, use WorldEdit to make locations");
			}
		} else {
			player.sendMessage("§7[§6TNTRun§7] §cArena §6" + args[0] + "§c doesn't exist");
		}
		return true;
	}

	@Override
	public int getMinArgsLength() {
		return 1;
	}

}