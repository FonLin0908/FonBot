package com.bowchan;

import com.bowchan.listenner.Listener1;
import com.bowchan.listenner.Listener2;
import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Main {
    private  final ShardManager shardManager;
    private  final Dotenv config;

    public  Main(){

        config = Dotenv.configure().load();
        String token = config.get("TOKEN");
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token);
        builder.setStatus(OnlineStatus.ONLINE);
        builder.setActivity(Activity.playing("DC楓之谷"));
        builder.enableIntents(GatewayIntent.GUILD_MESSAGES, GatewayIntent.DIRECT_MESSAGES, GatewayIntent.MESSAGE_CONTENT);

        shardManager = builder.build();

        shardManager.addEventListener(new Listener1());
        shardManager.addEventListener(new Listener2());
    }

    public Dotenv getConfig(){
        return config;
    }

    public  ShardManager getShardManager(){
        return shardManager;
    }

    public static void main(String[] args) {

        Main main = new Main();
    }
}
