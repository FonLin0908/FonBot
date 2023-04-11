package com.bowchan.listenner;

import com.bowchan.Gameprograme.AttMonster;
import com.bowchan.Gameprograme.GameMain;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.PermissionOverride;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.channel.concrete.Category;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Listener2 extends ListenerAdapter{
    @Override
    public void onGuildReady(GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();

        commandData.add(Commands.slash("gotoprairie","前往大草原"));
        commandData.add(Commands.slash("returntofirstcity","返回主城"));
        commandData.add(Commands.slash("adventurerinformation","冒險者資訊"));
        commandData.add(Commands.slash("adventurerguild","冒險者公會"));
        commandData.add(Commands.slash("shop","商店"));
        commandData.add(Commands.slash("startanadventure","開始冒險"));

        OptionData pointData1 = new OptionData(OptionType.STRING,"加點對象","選擇你要加點的對象");
        pointData1.addChoice("Str","力量");
        pointData1.addChoice("Dex","敏捷");
        pointData1.addChoice("Int","智力");
        pointData1.addChoice("Luk","幸運");

        OptionData pointData2 = new OptionData(OptionType.INTEGER,"加點個數","輸入你要加多少點");

        commandData.add(Commands.slash("addpoint","能力加點").addOptions(pointData1,pointData2));
        OptionData nameData = new OptionData(OptionType.STRING,"新的名稱","輸入新的角色名稱");
        commandData.add(Commands.slash("changename","更改角色名稱").addOptions(nameData));

        event.getGuild().updateCommands().addCommands(commandData).queue();
    }



    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        String command = event.getName();
        GameMain gameMain = new GameMain(event.getUser().getAsTag());
        if (event.getChannel().asTextChannel().getParentCategory()==null|| !event.getChannel().asTextChannel().getParentCategory().getName().equals("楓之谷")){
            event.getChannel().sendMessage("@everyone 有人意圖在非楓之谷區域使用楓之谷指令XD").queue();
            event.reply("驚不驚喜?\n意不意外?").setEphemeral(true).queue();
            return;
        }
        //玩家資訊
        if(command.equals("adventurerinformation")){
            String message = "```";
            for (int i = 1; i < gameMain.viewPlayInformation().get(0).size(); i++){
                if (gameMain.viewPlayInformation().get(0).get(i).equals("位置ID")){
                    continue;
                }
                if (!gameMain.viewPlayInformation().get(0).get(i).isEmpty()){
                    message += gameMain.viewPlayInformation().get(0).get(i) +" : ";
                }

                if (gameMain.viewPlayInformation().get(0).get(i).equals("經驗")){
                    message += "(" +gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(i)+ "/"+((int)Math.pow(Double.parseDouble(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(3)),2)+Integer.parseInt(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(3))*10)+")\n";
                    continue;
                }
                message += gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(i)+ "\n";
            }
            message +=" ```\n name 預設是***使用者名稱*** 可透過使用指令 ***`/changename`*** 更改";
            event.reply(message).setEphemeral(true).queue();
        }
        //能力加點
        else if(command.equals("addpoint")){
            if (event.getOptionsByName("加點對象").isEmpty()){
                event.reply("沒給** 加點對象 **你叫我怎麼加?!\n**`運氣 -999`** ?").setEphemeral(true).queue();
                return;
            }
            String pointName = event.getOptionsByName("加點對象").get(0).getAsString();
            if (event.getOptionsByName("加點個數").isEmpty()){
                event.reply("沒給** 加點個數** 你叫我怎麼加?!\n**`" + pointName + " -999`** ?").setEphemeral(true).queue();
                return;
            }
            int pointValue = event.getOptionsByName("加點個數").get(0).getAsInt();
            ArrayList<Integer> pointList = gameMain.addPoint(event.getUser().getAsTag(),pointName,pointValue);
            String replay;
            if(pointList.isEmpty()){
                replay = "想白嫖點數嗎?\n***沒門!***";
            }else if(pointList.get(0)==-1){
                replay = "清單都給你了\n這裡就沒有 ***" + pointName + "*** 能力值\n沒有就是沒有\n不要自己加!!! \uD83D\uDE20\uD83D\uDE20\uD83D\uDE20";
            }else {
                switch ((int) (Math.random() * 1000) % 10) {
                    case 0:replay = "已幫你清除 \n||騙你的啦ww  \n有幫你把 **" + pointName + "** 加到 **" + pointList.get(0) + " 點** \n還剩餘 **" + pointList.get(1) + " 點** 能力點數||";break;
                    case 1:replay = "好啦!好啦!都加好了\n結果自己打指令自己去看";break;
                    default:replay = "已幫你把 **" + pointName + "** 加到 **" + pointList.get(0) + " 點** \n還剩餘 **" + pointList.get(1) + " 點** 能力點數\n||這裡甚麼都沒有 這裡甚麼都沒有\n這裡甚麼都沒有 這裡甚麼都沒有 這裡甚麼都沒有\n這裡甚麼都沒有 這裡甚麼都沒有 這裡甚麼都沒有||   ";break;
                }
            }
            event.reply(replay).setEphemeral(true).queue();

        }else if(command.equals("changename")){
            if (event.getOptionsByName("新的名稱").isEmpty()){
                event.reply("沒給新的名稱你換個毛線球").setEphemeral(true).queue();
                return;
            }
            gameMain.changeName(event.getUser().getAsTag(),event.getOptionsByName("新的名稱").get(0).getAsString());
            event.reply("好了\n已經成功把你的名字改成 **" + event.getOptionsByName("新的名稱").get(0).getAsString() + "** 了 \n||我承認這個名字目前沒啥用W||").setEphemeral(true).queue();
        }
        else if(event.getChannel().getName().equals("主城")){
            //冒險者公會
            if (command.equals("adventurerguild") && gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12).equals("主城")){
                if (Integer.parseInt(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(3))<=10){
                    event.reply("等級不夠 無法使用此功能 \n進入冒險者公會需要至少***10等***\n~~小菜雞 不要妄想了 呵呵~~").setEphemeral(true).queue();
                } else {
                    event.reply("就算你等級 10 功能還沒開放 你也用不了\n***略~~***").setEphemeral(true).queue();
                }
            }//商店
            else if (command.equals("shop") && gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12).equals("主城")){
                event.reply("功能尚未開放").setEphemeral(true).queue();
            }//往大草原
            else if(command.equals("gotoprairie") && gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12).equals("主城")){
                Category category = event.getGuild().getCategoriesByName("楓之谷",true).get(0);

                Role role = event.getGuild().getRolesByName("冒險者",true).get(0);
                TextChannel textChannel = event.getGuild().createTextChannel("大草原").complete();
                textChannel.getManager().setParent(category).queue();
                textChannel.getManager().putMemberPermissionOverride(event.getJDA().getSelfUser().getIdLong(), Permission.ALL_TEXT_PERMISSIONS|Permission.VIEW_CHANNEL.getRawValue(),0).putMemberPermissionOverride(event.getUser().getIdLong(), Permission.ALL_TEXT_PERMISSIONS|Permission.VIEW_CHANNEL.getRawValue(),0).putRolePermissionOverride(event.getGuild().getPublicRole().getIdLong(),0,Permission.VIEW_CHANNEL.getRawValue()).queue();

                gameMain.changePlayerInformation("位置","大草原");
                gameMain.changePlayerInformation("位置ID",""+textChannel.getId());
                textChannel.sendMessage(event.getUser().getAsMention() + " 進入此區域 \n你可以透過 `/startanadventure` 開始你的冒險旅途").queue();
                event.reply("走著走呀 \n走到 ~~**奈何橋**~~\n噢不是w\n是 "+textChannel.getAsMention()).setEphemeral(true).queue();
                return;
            }else{
                System.out.println(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12));
                event.reply("此處無法進行此操作").setEphemeral(true).queue();
            }
        }//開始冒險
        else if(command.equals("startanadventure")&& !event.getChannel().getName().contains("主城") && !gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12).contains("主城")){
            AttMonster attMonster = new AttMonster(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12));
            String monster = attMonster.randomMonster();
            ArrayList<String> monster1= attMonster.foundMonster(event.getUser().getAsTag(),monster);
            event.getChannel().sendMessage("遭遇一隻 ***" + monster1.get(1) + "*** (血量:" + monster1.get(2) + ")").queue();
            event.reply("\uD83C\uDF44***冒險開始囉***\uD83C\uDF44\n\n攻擊請輸入 ***att***\n逃跑輸入 ***run***").setEphemeral(true).queue();

        }//回主城
        else if(command.equals("returntofirstcity") && !event.getChannel().getName().contains("主城") && !gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12).contains("主城")){
            Category category = event.getGuild().getCategoriesByName("楓之谷",true).get(0);
            TextChannel textChannel = category.getGuild().getTextChannelsByName("主城",true).get(0);
            event.reply("你成功的返回了 " + textChannel.getAsMention() ).setEphemeral(true).queue();
            category.getGuild().getTextChannelById(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(13)).delete().queueAfter(3, TimeUnit.SECONDS);
            gameMain.changePlayerInformation("位置ID","主城");
            gameMain.changePlayerInformation("位置","主城");
            Message message = textChannel.sendMessage(event.getUser().getAsMention() + " 回到了主城 ..10秒後刪除").complete();
            message.delete().queueAfter(10,TimeUnit.SECONDS);


        }else {
            event.reply("此處無法進行此操作").setEphemeral(true).queue();
        }
        gameMain.attUpdate();
        gameMain.textUpdate();
    }
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        GameMain gameMain = new GameMain(event.getAuthor().getAsTag());
        AttMonster attMonster = new AttMonster(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12));
        if (event.getAuthor().isBot()){
            return;
        }
        if (event.getChannel().getName().contains("公告")||event.getChannel().getName().contains("通知")||event.getChannel().getName().contains("廣播")){
            return;
        }
        if (event.getChannel().asTextChannel().getParentCategory()==null || !event.getChannel().asTextChannel().getParentCategory().getName().equals("楓之谷")){
            return;
        }
        //攻擊
        if(event.getMessage().getContentDisplay().equals("att")||event.getMessage().getContentDisplay().contains("攻擊")){
            ArrayList<ArrayList<String>> monster= attMonster.getFighting(event.getAuthor().getAsTag(),gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(12));
            if (event.getChannel().getName().contains("主城")){
                event.getChannel().sendMessage("在**主城**是要打誰呢?\n守衛嗎?\n是想前往奈何橋嗎?").queue();
                return;
            }
            if (monster.isEmpty()){
                event.getChannel().sendMessage("揮呀揮 打呀打 \n什麼都沒揮到 什麼都沒打到 \n路過的人看到 **你** 以及 **你空空如也的前方** \n表示: **?_?**").queue();
                return;
            }
            double att = Double.parseDouble(gameMain.viewPlayInformation().get(gameMain.viewPlayInformationSite()).get(9));
            Double monsterBlood = Double.valueOf(monster.get(0).get(1));
            event.getChannel().sendMessage("你對 **" + monster.get(0).get(0) + "** 進行攻擊").queue();
            if (monsterBlood <= att){
                event.getChannel().sendMessage("你對 **" + monster.get(0).get(0) + " ** 造成了 **"  + monsterBlood + "** 點傷害").queue();
                event.getChannel().sendMessage("~~***" + monster.get(0).get(0) + " 死亡***~~").queue();
                monsterBlood = 0.0;
                gameMain.LevelUp(event.getAuthor().getAsTag(),monster.get(0).get(2));
                event.getChannel().sendMessage("獲得 ***" + monster.get(0).get(2) + "*** 點 ***經驗值***").queue();
                ArrayList<Integer> x = gameMain.gradeUpdate(event.getAuthor().getAsTag());
                for (Integer integer : x) {
                    event.getChannel().sendMessage("**等級提升!!** " + " 目前等級: **" + integer + " **等").queue();
                }

            } else {
                event.getChannel().sendMessage("你對 **" + monster.get(0).get(0) + "** 造成了 **"  + att + "** 點傷害").queue();
                monsterBlood -= att;
                event.getChannel().sendMessage("**" + monster.get(0).get(0) + " **血量剩餘 **" + monsterBlood + "** 點").queue();
            }
            monster.get(0).set(1,String.valueOf(monsterBlood));
            attMonster.changeFighting(event.getAuthor().getAsTag(),monster);
        }
        //逃跑
        else if (event.getMessage().getContentDisplay().equals("run")||event.getMessage().getContentDisplay().contains("逃跑")){
            if (attMonster.run(event.getAuthor().getAsTag())){
                event.getChannel().sendMessage("你成功的逃走了").queue();
            }else {
                event.getChannel().sendMessage("沒怪你還要跑，有毛豆啊?").queue();
            }
        }
        //沒啥營養
        else if(event.getMessage().getContentDisplay().contains("你好嗆")){
            event.getChannel().sendMessage(event.getAuthor().getAsMention() + "因為你是火車 \n||嘟! 嘟! 請嗆! 請嗆!||").queue();

        }
    }
}
