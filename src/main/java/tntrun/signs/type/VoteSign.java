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

package tntrun.signs.type;

import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import tntrun.TNTRun;
import tntrun.arena.Arena;
import tntrun.messages.Messages;

public class VoteSign implements SignType {

	private TNTRun plugin;

	public VoteSign(TNTRun plugin) {
		this.plugin = plugin;
	}

	@Override
	public void handleCreation(SignChangeEvent e) {
		e.setLine(0, "§7[§6TNTRun§7]");
		e.getPlayer().sendMessage("Sign succesfully created");
	}

	@Override
	public void handleClick(PlayerInteractEvent e) {
		Arena arena = plugin.amanager.getPlayerArena(e.getPlayer().getName());
		if (arena != null) {
			if (arena.getPlayerHandler().vote(e.getPlayer())) {
				Messages.sendMessage(e.getPlayer(), Messages.playervotedforstart);
			} else {
				Messages.sendMessage(e.getPlayer(), Messages.playeralreadyvotedforstart);
			}
			e.setCancelled(true);
		} else {
			Messages.sendMessage(e.getPlayer(), Messages.playernotinarena);
		}
	}

	@Override
	public void handleDestroy(BlockBreakEvent e) {
	}

}