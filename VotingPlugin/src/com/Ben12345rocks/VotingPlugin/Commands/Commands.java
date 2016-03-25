package com.Ben12345rocks.VotingPlugin.Commands;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;

import org.apache.commons.lang3.time.DateUtils;

import com.Ben12345rocks.VotingPlugin.Main;
import com.Ben12345rocks.VotingPlugin.Utils;
import com.Ben12345rocks.VotingPlugin.Config.Config;
import com.Ben12345rocks.VotingPlugin.Config.ConfigBonusReward;
import com.Ben12345rocks.VotingPlugin.Config.ConfigFormat;
import com.Ben12345rocks.VotingPlugin.Config.ConfigVoteSites;
import com.Ben12345rocks.VotingPlugin.Objects.User;
import com.Ben12345rocks.VotingPlugin.Objects.VoteSite;
import com.Ben12345rocks.VotingPlugin.UserData.Data;

public class Commands {

	static ConfigBonusReward bonusReward = ConfigBonusReward.getInstance();

	static Config config = Config.getInstance();

	static ConfigVoteSites configVoteSites = ConfigVoteSites.getInstance();

	static ConfigFormat format = ConfigFormat.getInstance();

	static Commands instance = new Commands();

	static Main plugin = Main.plugin;

	public static Commands getInstance() {
		return instance;
	}

	private Commands() {
	}

	public Commands(Main plugin) {
		Commands.plugin = plugin;
	}

	public TextComponent[] adminVoteHelp() {

		TextComponent[] msg = new TextComponent[19];

		msg[0] = new TextComponent("VotingPlugin Admin Help");
		msg[0].setColor(ChatColor.DARK_AQUA);
		msg[0].setBold(true);

		msg[1] = new TextComponent("[] = Optional");
		msg[1].setColor(ChatColor.DARK_AQUA);
		msg[1].setBold(false);

		msg[2] = new TextComponent("() = Needed");
		msg[2].setColor(ChatColor.DARK_AQUA);
		msg[2].setBold(false);

		msg[3] = new TextComponent("Aliases: adminvote, av");
		msg[3].setColor(ChatColor.DARK_AQUA);
		msg[3].setBold(false);

		msg[4] = new TextComponent("/adminvote vote (player) (sitename)");
		msg[4].setColor(ChatColor.AQUA);
		msg[4].setBold(true);
		msg[4].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote vote (player) (sitename)"));
		msg[4].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Trigger a vote for server").color(
						ChatColor.AQUA).create()));

		msg[5] = new TextComponent("/adminvote bungeevote (player) (sitename)");
		msg[5].setColor(ChatColor.AQUA);
		msg[5].setBold(true);
		msg[5].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote bungeevote (player) (sitename)"));
		msg[5].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Trigger bungee vote").color(
						ChatColor.AQUA).create()));

		msg[6] = new TextComponent("/adminvote globalvote (player) (sitename)");
		msg[6].setColor(ChatColor.AQUA);
		msg[6].setBold(true);
		msg[6].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote globalvote (player) (sitename)"));
		msg[6].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Trigger server and bungee vote").color(
						ChatColor.AQUA).create()));

		msg[7] = new TextComponent(
				"/adminvote settotal (player) (sitename) (amount)");
		msg[7].setColor(ChatColor.AQUA);
		msg[7].setBold(true);
		msg[7].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote settotal (player) (sitename) (amount)"));
		msg[7].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Set player's total votes for votesite")
						.color(ChatColor.AQUA).create()));

		msg[8] = new TextComponent("/adminvote reload");
		msg[8].setColor(ChatColor.AQUA);
		msg[8].setBold(true);
		msg[8].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote reload"));
		msg[8].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Reload the plugin").color(ChatColor.AQUA)
						.create()));

		msg[9] = new TextComponent("/adminvote uuid (playername)");
		msg[9].setColor(ChatColor.AQUA);
		msg[9].setBold(true);
		msg[9].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote uuid (playername)"));
		msg[9].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Get player's uuid").color(ChatColor.AQUA)
						.create()));

		msg[10] = new TextComponent("/adminvote version");
		msg[10].setColor(ChatColor.AQUA);
		msg[10].setBold(true);
		msg[10].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote version"));
		msg[10].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See plugin version info").color(
						ChatColor.AQUA).create()));

		msg[11] = new TextComponent("/adminvote sites [site]");
		msg[11].setColor(ChatColor.AQUA);
		msg[11].setBold(true);
		msg[11].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote sites [site]"));
		msg[11].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Display vote sites and vote site info")
						.color(ChatColor.AQUA).create()));

		msg[12] = new TextComponent(
				"/adminvote VoteSite (SiteName) AddItem Item");
		msg[12].setColor(ChatColor.AQUA);
		msg[12].setBold(true);
		msg[12].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) AddItem Item"));
		msg[12].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Add current item in hand to votesite")
						.color(ChatColor.AQUA).create()));

		msg[13] = new TextComponent(
				"/adminvote VoteSite (SiteName) SetMoney (Money)");
		msg[13].setColor(ChatColor.AQUA);
		msg[13].setBold(true);
		msg[13].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) SetMoney (Money)"));
		msg[13].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Set money for votesite").color(
						ChatColor.AQUA).create()));

		msg[14] = new TextComponent(
				"/adminvote VoteSite (SiteName) SetServiceSite (ServiceSite)");
		msg[14].setColor(ChatColor.AQUA);
		msg[14].setBold(true);
		msg[14].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) SetServiceSite (ServiceSite)"));
		msg[14].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Set service site for votesite").color(
						ChatColor.AQUA).create()));

		msg[15] = new TextComponent(
				"/adminvote VoteSite (SiteName) SetDisabled (Disabled)");
		msg[15].setColor(ChatColor.AQUA);
		msg[15].setBold(true);
		msg[15].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) SetDisabled (Disabled)"));
		msg[15].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Set disabled (true/false) on votesite")
						.color(ChatColor.AQUA).create()));

		msg[16] = new TextComponent(
				"/adminvote VoteSite (SiteName) AddCommandPlayer (Command)");
		msg[16].setColor(ChatColor.AQUA);
		msg[16].setBold(true);
		msg[16].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) AddCommandPlayer (Command)"));
		msg[16].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(
						"Add command to list of player commands for votesite")
						.color(ChatColor.AQUA).create()));

		msg[17] = new TextComponent(
				"/adminvote VoteSite (SiteName) AddCommandConsole (Command)");
		msg[17].setColor(ChatColor.AQUA);
		msg[17].setBold(true);
		msg[17].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote VoteSite (SiteName) AddCommandConsole (Command)"));
		msg[17].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(
						"Add command to list of console commands for votesite")
						.color(ChatColor.AQUA).create()));

		msg[18] = new TextComponent("/adminvote help");
		msg[18].setColor(ChatColor.AQUA);
		msg[18].setBold(true);
		msg[18].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/adminvote help"));
		msg[18].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See this page").color(ChatColor.AQUA)
						.create()));

		return msg;

	}

	public String[] commandVoteToday(int page) {
		int pagesize = ConfigFormat.getInstance().getPageSize();
		if (page < 1) {
			page = 1;
		}
		ArrayList<String> msg = new ArrayList<String>();

		msg.add("&cToday's Votes " + page + "/"
				+ ((plugin.voteToday.length / pagesize) + 1));
		msg.add("&cPlayerName : VoteSite : Time");
		page--;

		for (int i = pagesize * page; (i < plugin.voteToday.length)
				&& (i < ((page + 1) * pagesize)); i++) {
			msg.add(plugin.voteToday[i]);
		}

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);

	}

	public String[] playerInfo(User user) {
		ArrayList<String> msg = new ArrayList<String>();

		// title
		msg.add("&cPlayer '" + user.getPlayerName() + "' Info");

		// last vote
		msg.addAll(Utils.getInstance().convertArray(voteCommandLast(user)));

		// next vote
		msg.addAll(Utils.getInstance().convertArray(voteCommandNext(user)));

		// total
		msg.addAll(Utils.getInstance().convertArray(voteCommandTotal(user)));

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public String[] voteCommandLast(User user) {

		ArrayList<String> msg = new ArrayList<String>();

		ArrayList<VoteSite> voteSites = configVoteSites.getVoteSites();

		String playerName = user.getPlayerName();

		msg.add(format.getCommandsVoteLastTitle().replace("%player%",
				playerName));

		for (VoteSite voteSite : voteSites) {
			Date date = new Date(user.getTime(voteSite));
			String timeString = new SimpleDateFormat(format.getTimeFormat())
					.format(date);

			msg.add(format
					.getCommandsVoteLastLine()
					.replace("%Month% %Day%, %Year% %Hour%:%Minute% %ampm%",
							"%time%").replace("%time%", timeString)
					.replace("%SiteName%", voteSite.getSiteName()));
		}

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	@SuppressWarnings({ "deprecation", "unused" })
	public String[] voteCommandNext(User user) {
		ArrayList<String> msg = new ArrayList<String>();

		ArrayList<VoteSite> voteSites = configVoteSites.getVoteSites();

		String playerName = user.getPlayerName();

		msg.add(Utils.getInstance().colorize(
				format.getCommandsVoteNextTitle().replace("%player%",
						playerName)));

		for (VoteSite voteSite : voteSites) {

			String msgLine = format.getCommandsVoteNextLayout();

			Date date = new Date(user.getTime(voteSite));

			int month = date.getMonth();
			int day = date.getDate();
			int hour = date.getHours();
			int min = date.getMinutes();
			int year = date.getYear();

			int votedelay = configVoteSites
					.getVoteDelay(voteSite.getSiteName());
			if (votedelay == 0) {
				String errorMsg = format.getCommandsVoteNextInfoError();
				msgLine = msgLine.replace("%info%", errorMsg);
			} else {

				Date voteTime = new Date(year, month, day, hour, min);
				Date nextvote = DateUtils.addHours(voteTime, votedelay);

				int cday = new Date().getDate();
				int cmonth = new Date().getMonth();
				int chour = new Date().getHours();
				int cmin = new Date().getMinutes();
				int cyear = new Date().getYear();
				Date currentDate = new Date(cyear, cmonth, cday, chour, cmin);

				if ((nextvote == null) || (day == 0) || (hour == 0)) {
					String canVoteMsg = format.getCommandsVoteNextInfoCanVote();
					msgLine = msgLine.replace("%info%", canVoteMsg);
				} else {
					if (!currentDate.after(nextvote)) {
						long diff = nextvote.getTime() - currentDate.getTime();

						long diffSeconds = (diff / 1000) % 60;
						long diffMinutes = (diff / (60 * 1000)) % 60;
						long diffHours = diff / (60 * 60 * 1000);
						// long diffDays = diff / (24 * 60 * 60 * 1000);

						String timeMsg = format.getCommandsVoteNextInfoTime();
						timeMsg = timeMsg.replace("%hours%",
								Long.toString(diffHours));
						timeMsg = timeMsg.replace("%minutes%",
								Long.toString(diffMinutes));
						msgLine = msgLine.replace("%info%", timeMsg);
					} else {
						String canVoteMsg = format
								.getCommandsVoteNextInfoCanVote();
						msgLine = msgLine.replace("%info%", canVoteMsg);
					}
				}
			}
			msgLine = msgLine.replace("%SiteName%", voteSite.getSiteName());
			msg.add(Utils.getInstance().colorize(msgLine));

		}
		return Utils.getInstance().convertArray(msg);
	}

	public String[] voteCommandSiteInfo(String voteSiteName) {
		ArrayList<String> msg = new ArrayList<String>();

		if (!ConfigVoteSites.getInstance().getVoteSites()
				.contains(voteSiteName)) {
			msg.add("&cInvalid Vote Site, see /av sites!");
		} else {

			VoteSite voteSite = plugin.getVoteSite(voteSiteName);

			msg.add("&c&lVote Site Info for " + voteSiteName + ":");

			msg.add("&cSite: &6" + voteSite.getServiceSite());
			msg.add("&cVoteURL: &6" + voteSite.getVoteURL());
			msg.add("&cVote Delay: &6" + voteSite.getVoteDelay());
			msg.add("&cMoney: &6" + voteSite.getMoney());

			msg.add("&cItems:");
			for (String item : ConfigVoteSites.getInstance().getItems(
					voteSite.getSiteName())) {
				msg.add("&c- &6" + item);
			}

			msg.add("&cPlayer Commands:");

			try {
				for (String playerCommands : voteSite.getPlayerCommands()) {
					msg.add("&c- " + playerCommands);
				}
			} catch (Exception ex) {
			}

			msg.add("&cConsole Commands:");

			try {
				for (String consoleCommands : voteSite.getConsoleCommands()) {
					msg.add("&c- " + consoleCommands);
				}
			} catch (Exception ex) {
			}
		}
		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public String[] voteCommandSites() {
		ArrayList<String> msg = new ArrayList<String>();

		msg.add("&c&lVote Sites:");

		int count = 1;
		ArrayList<VoteSite> voteSites = ConfigVoteSites.getInstance()
				.getVoteSites();
		if (voteSites != null) {
			for (VoteSite voteSite : voteSites) {
				msg.add("&c" + count + ". &6" + voteSite.getSiteName());
				count++;
			}
		}

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public String[] voteCommandTotal(User user) {
		ArrayList<String> msg = new ArrayList<String>();
		ArrayList<VoteSite> voteSites = configVoteSites.getVoteSites();

		String playerName = user.getPlayerName();

		msg.add(format.getCommandsVoteTotalTitle().replace("%player%",
				playerName));

		// total votes
		int total = 0;

		for (VoteSite voteSite : voteSites) {
			int votes = user.getTotal(voteSite);
			// int votes = Data.getInstance().getTotal(playerName, siteName);
			total += votes;
			msg.add(format.getCommandsVoteTotalLine()
					.replace("%SiteName%", voteSite.getSiteName())
					.replace("%Total%", "" + votes));
		}
		msg.add(format.getCommandsVoteTotalTotal().replace("%Totals%",
				"" + total));

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public String[] voteCommandTotalAll() {

		ArrayList<String> msg = new ArrayList<String>();

		ArrayList<VoteSite> voteSites = configVoteSites.getVoteSites();

		ArrayList<String> voteNames = Data.getInstance().getPlayerNames();

		msg.add(format.getCommandsVoteTotalAllTitle());
		int total = 0;
		for (VoteSite voteSite : voteSites) {
			int votes = 0;
			for (String playerName : voteNames) {
				if (playerName != null) {
					User user = new User(playerName);
					votes += user.getTotal(voteSite);
				}
			}
			msg.add(format.getCommandsVoteTotalAllLine()
					.replace("%SiteName%", voteSite.getSiteName())
					.replace("%Total%", "" + votes));
			total += votes;
		}
		msg.add(format.getCommandsVoteTotalAllTotal().replace("%Totals%",
				"" + total));

		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public TextComponent[] voteHelp() {
		TextComponent[] msg = new TextComponent[11];

		msg[0] = new TextComponent("VotingPlugin Player Help");
		msg[0].setColor(ChatColor.DARK_AQUA);
		msg[0].setBold(true);

		msg[1] = new TextComponent("[] = Optional");
		msg[1].setColor(ChatColor.DARK_AQUA);
		msg[1].setBold(false);

		msg[2] = new TextComponent("Aliases: vote, v");
		msg[2].setColor(ChatColor.DARK_AQUA);
		msg[2].setBold(false);

		msg[3] = new TextComponent("/vote");
		msg[3].setColor(ChatColor.AQUA);
		msg[3].setBold(true);
		msg[3].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote"));
		msg[3].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("Show list of voting websites").color(
						ChatColor.AQUA).create()));

		msg[4] = new TextComponent("/vote total [Player/All]");
		msg[4].setColor(ChatColor.AQUA);
		msg[4].setBold(true);
		msg[4].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote total"));
		msg[4].setHoverEvent(new HoverEvent(
				HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(
						"See total votes of yourself, another player, or server total")
						.color(ChatColor.AQUA).create()));

		msg[5] = new TextComponent("/vote next [Player]");
		msg[5].setColor(ChatColor.AQUA);
		msg[5].setBold(true);
		msg[5].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote next"));
		msg[5].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(
						"See when you or another player can vote next").color(
						ChatColor.AQUA).create()));

		msg[6] = new TextComponent("/vote last [Player]");
		msg[6].setColor(ChatColor.AQUA);
		msg[6].setBold(true);
		msg[6].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote last"));
		msg[6].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder(
						"See when you or another player voted last").color(
						ChatColor.AQUA).create()));

		msg[7] = new TextComponent("/vote top [Page]");
		msg[7].setColor(ChatColor.AQUA);
		msg[7].setBold(true);
		msg[7].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote top"));
		msg[7].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See the top voters")
						.color(ChatColor.AQUA).create()));

		msg[8] = new TextComponent("/vote info [Player]");
		msg[8].setColor(ChatColor.AQUA);
		msg[8].setBold(true);
		msg[8].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote info"));
		msg[8].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See you or another player's info").color(
						ChatColor.AQUA).create()));

		msg[9] = new TextComponent("/vote today [Page]");
		msg[9].setColor(ChatColor.AQUA);
		msg[9].setBold(true);
		msg[9].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote today"));
		msg[9].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See who voted today").color(
						ChatColor.AQUA).create()));

		msg[10] = new TextComponent("/vote help");
		msg[10].setColor(ChatColor.AQUA);
		msg[10].setBold(true);
		msg[10].setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND,
				"/vote help"));
		msg[10].setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT,
				new ComponentBuilder("See this help info")
						.color(ChatColor.AQUA).create()));

		return msg;
	}

	@SuppressWarnings("deprecation")
	public String[] voteToday() {
		ArrayList<String> msg = new ArrayList<String>();

		ArrayList<User> users = Utils.getInstance().convertSet(
				Data.getInstance().getUsers());
		if (users != null) {
			for (User user : users) {
				for (VoteSite voteSite : configVoteSites.getVoteSites()) {
					long time = user.getTime(voteSite);
					if (new Date().getDate() == Utils.getInstance()
							.getDayFromMili(time)) {

						String timeString = new SimpleDateFormat(
								format.getTimeFormat()).format(new Date(time));
						msg.add("&6" + user.getPlayerName() + " : "
								+ voteSite.getSiteName() + " : " + timeString);
					}
				}
			}
		}
		msg = Utils.getInstance().colorize(msg);
		return Utils.getInstance().convertArray(msg);
	}

	public ArrayList<String> voteURLs() {
		ArrayList<String> sites = new ArrayList<String>();
		ArrayList<VoteSite> voteSites = configVoteSites.getVoteSites();
		int counter = 0;
		for (VoteSite voteSite : voteSites) {
			counter++;
			String voteURL = configVoteSites.getVoteURL(voteSite.getSiteName());
			String msg = format.getCommandsVoteURLS();
			msg = Utils.getInstance().colorize(msg);
			msg = msg.replace("%num%", Integer.toString(counter));
			msg = msg.replace("%url%", voteURL);
			msg = msg.replace("%SiteName%", voteSite.getSiteName());
			sites.add(msg);
		}
		return sites;
	}

}
