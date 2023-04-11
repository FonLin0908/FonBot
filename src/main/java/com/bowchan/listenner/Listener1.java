package com.bowchan.listenner;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.channel.concrete.VoiceChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.api.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Listener1 extends ListenerAdapter {


    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if(event.getMessage().getContentDisplay().indexOf("/")==0) {
            String instruction = event.getMessage().getContentDisplay().substring(1);
            if (instruction.contains("Start")) {
                String key = instruction.substring(instruction.indexOf(":")+1);
                event.getMessage().delete().queueAfter(1, TimeUnit.SECONDS);
                try {
                    File file = new File("GameInformation","gamekey");
                    Scanner sc = new Scanner(file);
                    boolean trueKey = false;
                    while (sc.hasNextLine()) {
                        if (sc.nextLine().contains(key.substring(key.indexOf("=")+1))) {
                            System.out.println(123);
                            trueKey = true;
                            break;
                        }
                    }
                    if(trueKey){
                        boolean createDetermination;

                        if (event.getGuild().getRolesByName("冒險者",true).isEmpty()){
                            event.getGuild().createRole().setName("冒險者").setColor(Color.RED).complete();
                        }

                        Role role = event.getGuild().getRolesByName("冒險者",true).get(0);



                        if (event.getGuild().getCategoriesByName("楓之谷",true).isEmpty()){
                            event.getGuild().createCategory("楓之谷").complete();
                        }

                        Category category = event.getGuild().getCategoriesByName("楓之谷",true).get(0);
                        category.getManager().putRolePermissionOverride(event.getGuild().getRoleByBot(event.getJDA().getSelfUser()).getIdLong(),Permission.VIEW_CHANNEL.getRawValue(),0)
                                .putRolePermissionOverride(role.getIdLong(),Permission.VIEW_CHANNEL.getRawValue(),0)
                                .putRolePermissionOverride(event.getGuild().getPublicRole().getIdLong(),0,Permission.VIEW_CHANNEL.getRawValue()).queue();

                        createDetermination = true;
                        for (TextChannel i : event.getGuild().getTextChannelsByName("楓之谷公告",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }

                        if (createDetermination){
                            category.createTextChannel("楓之谷公告").syncPermissionOverrides().complete();
                        }else {
                            event.getGuild().getTextChannelsByName("楓之谷公告",true).get(0).getManager().sync(category).queue();
                        }

                        createDetermination = true;
                        for (TextChannel i : event.getGuild().getTextChannelsByName("楓之谷廣播",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }
                        if (createDetermination){
                            category.createTextChannel("楓之谷廣播").syncPermissionOverrides().complete();
                        }else {
                            event.getGuild().getTextChannelsByName("楓之谷廣播",true).get(0).getManager().sync(category).queue();
                        }

                        createDetermination = true;
                        for (TextChannel i : event.getGuild().getTextChannelsByName("主城",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }
                        if (createDetermination){
                            category.createTextChannel("主城").syncPermissionOverrides().complete();
                        }else{
                            event.getGuild().getTextChannelsByName("主城",true).get(0).getManager().sync(category).queue();
                        }

                        createDetermination = true;
                        for (VoiceChannel i : event.getGuild().getVoiceChannelsByName("冒險語音區#1",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }
                        if (createDetermination){
                            category.createVoiceChannel("冒險語音區#1").syncPermissionOverrides().complete();
                        }else{
                            event.getGuild().getVoiceChannelsByName("冒險語音區#1",true).get(0).getManager().sync(category).queue();
                        }

                        createDetermination = true;
                        for (VoiceChannel i : event.getGuild().getVoiceChannelsByName("冒險語音區#2",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }
                        if (createDetermination){
                            category.createVoiceChannel("冒險語音區#2").syncPermissionOverrides().complete();
                        }else{
                            event.getGuild().getVoiceChannelsByName("冒險語音區#2",true).get(0).getManager().sync(category).queue();
                        }

                        createDetermination = true;
                        for (VoiceChannel i : event.getGuild().getVoiceChannelsByName("冒險語音區#3",true)){
                            if (i.getParentCategory().getName().equals("楓之谷")) {
                                createDetermination = false;
                            }
                        }
                        if (createDetermination){
                            category.createVoiceChannel("冒險語音區#3").syncPermissionOverrides().complete();
                        }else{
                            event.getGuild().getVoiceChannelsByName("冒險語音區#3",true).get(0).getManager().sync(category).queue();
                        }


                        if (event.getGuild().getTextChannelsByName("身分組領取",true).isEmpty()){
                            event.getGuild().createTextChannel("身分組領取").complete();
                        }
                        if (event.getGuild().getTextChannelsByName("身分組領取清除",true).isEmpty()){
                            event.getGuild().createTextChannel("身分組領取清除").complete();
                        }

                        Message message = event.getGuild().getTextChannelsByName("身分組領取",true).get(0).sendMessage("\uD83D\uDD14 楓之谷Discord小遊戲 上線囉!!\uD83D\uDD14 \n \n@everyone\n" +
                                " 點擊表情領取身分組 ＝＞ \uD83C\uDF41").complete();
                        message.addReaction(Emoji.fromUnicode("\uD83C\uDF41")).queue();

                        Message message1 = event.getChannel().sendMessage("設定結束...3秒後消失").complete();
                        message1.delete().queueAfter(3,TimeUnit.SECONDS);
                        event.getGuild().getTextChannelsByName("楓之谷公告",true).get(0).sendMessage("\uD83D\uDD14 楓之谷Discord小遊戲 上線囉!!\uD83D\uDD14 \n \n@everyone\n ***目前版本 1.0*** \uD83E\uDD29 敬請期待更多版本功能更新").queue();

                    }else{
                        event.getChannel().sendMessage("非正確啟動密鑰").queue();
                    }

                } catch (FileNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        if ((event.getChannel().getName().contains("公告")||event.getChannel().getName().contains("廣播")||event.getChannel().getName().contains("通知"))&&event.getChannel().asTextChannel().getParentCategory().getName().equals("楓之谷")&&event.getAuthor()!=event.getJDA().getSelfUser()){
            event.getMessage().delete().queue();
            Message message = event.getChannel().sendMessage("***此區域禁止發言***...3秒後消失").complete();
            message.delete().queueAfter(3,TimeUnit.SECONDS);
        }
        if (event.getMessage().getAuthor().isBot()) {
            return;
        }else if (event.getAuthor().getAsTag().equals("豹欠擬哪喂#6347")&&event.getMessage().getContentDisplay().contains("楓之谷公告:")) {
            String message = event.getMessage().getContentDisplay().substring(7);
            for (Guild j : event.getJDA().getGuilds()){

                if(j == null){
                    j.getTextChannelsByName("楓之谷公告", true).get(0).sendMessage(message).queue();
                }

            }
        }

    }



    @Override
    public void onMessageReactionAdd(MessageReactionAddEvent event) {
        if (event.getGuild().getId().equals("1039141210016727080")&&event.getMessageId().equals("1093569642758426694")) {
            if (event.getEmoji().getName().equals("\uD83D\uDCBB")) {

                event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById("1090272637961392279")).queue();

            } else if (event.getEmoji().getName().equals("\uD83C\uDFAE")) {

                event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById("1090276776745386096")).queue();

            } else if (event.getEmoji().getName().equals("\uD83C\uDFA3")) {

                event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById("1093566932961218620")).queue();

            }
        } else if(event.getGuild().getId().equals("1087392797054742659")&&event.getMessageId().equals("1093575647969951764")) {
            if (event.getEmoji().getName().equals("\uD83C\uDFA3")){
                event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById("1093571707131924560")).queue();
                event.getGuild().getTextChannelsByName("身分組領取清除",true).get(0).sendMessage("已給予"+event.getUser().getAsMention()+event.getGuild().getRoleById("1093571707131924560").getAsMention()).queue();
            }
        }if (event.getEmoji().getName().equals("\uD83C\uDF41")) {
            event.getGuild().addRoleToMember(event.getUser(),event.getGuild().getRoleById(event.getGuild().getRolesByName("冒險者",true).get(0).getId())).queue();
            event.getGuild().getTextChannelsByName("身分組領取清除",true).get(0).sendMessage("已給予"+event.getUser().getAsMention()+event.getGuild().getRoleById(event.getGuild().getRolesByName("冒險者",true).get(0).getId()).getAsMention()).queue();
        }


    }

    @Override
    public void onMessageReactionRemove(MessageReactionRemoveEvent event) {
        if (event.getUser() == null) {
            return;
        }
        if (event.getGuild().getId().equals("1039141210016727080")&&event.getMessageId().equals("1093569642758426694")){
            if(event.getEmoji().getName().equals("\uD83D\uDCBB")) {

                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getUser()),event.getGuild().getRoleById("1090272637961392279")).queue();

            } else if(event.getEmoji().getName().equals("\uD83C\uDFAE")) {

                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getUser()),event.getGuild().getRoleById("1090276776745386096")).queue();

            } else if(event.getEmoji().getName().equals("\uD83C\uDFA3")) {

                event.getGuild().removeRoleFromMember(Objects.requireNonNull(event.getUser()),event.getGuild().getRoleById("1093566932961218620")).queue();

            }
        } else if (event.getGuild().getId().equals("1087392797054742659")) {
            if (event.getEmoji().getName().equals("\uD83C\uDFA3")&&event.getMessageId().equals("1093575647969951764")){
                event.getGuild().removeRoleFromMember(event.getUser(),event.getGuild().getRoleById("1093571707131924560")).queue();
                event.getGuild().getTextChannelsByName("身分組領取清除",true).get(0).sendMessage("已清除"+event.getUser().getAsMention()+event.getGuild().getRoleById("1093571707131924560").getAsMention()).queue();
            }
        }else if (event.getEmoji().getName().equals("\uD83C\uDF41")) {

            event.getGuild().removeRoleFromMember(event.getUser(),event.getGuild().getRoleById(event.getGuild().getRolesByName("冒險者",true).get(0).getId())).queue();
            event.getGuild().getTextChannelsByName("身分組領取清除",true).get(0).sendMessage("已清除"+event.getUser().getAsMention()+event.getGuild().getRoleById(event.getGuild().getRolesByName("冒險者",true).get(0).getId()).getAsMention()).queue();
        }

    }

}
