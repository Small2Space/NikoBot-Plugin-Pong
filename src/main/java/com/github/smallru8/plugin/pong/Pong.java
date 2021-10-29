package com.github.smallru8.plugin.pong;

import java.awt.Color;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import com.github.smallru8.NikoBot.Core;
import com.github.smallru8.NikoBot.Embed;
import com.github.smallru8.NikoBot.event.Event.MessageEvent;
import com.github.smallru8.NikoBot.plugins.PluginsInterface;

public class Pong implements PluginsInterface{

	private String pluginName = "PingPong";
	
	public void onDisable() {
		EventBus.getDefault().unregister(this);
	}

	public void onEnable() {
		EventBus.getDefault().register(this);
	}
	@Subscribe(threadMode = ThreadMode.MAIN)
	public void onMessageRecved(MessageEvent e) {
		if(Core.pluginsMGR.isAllowedGuild(e.event.getGuild().getId(), pluginName)&&e.msg.getContentRaw().startsWith("/ping")&&!e.msg.getAuthor().isBot()) {
			long ms = e.msg.getTimeCreated().until(LocalDateTime.now().atOffset(ZoneId.systemDefault().getRules().getOffset(Instant.now())), ChronoUnit.MILLIS);
			Embed.EmbedSender(Color.pink, e.msg.getChannel(), "Pong!:ping_pong:"+ms+"ms", "");
		}
	}
	public String pluginsName() {
		return pluginName;
	}

}
