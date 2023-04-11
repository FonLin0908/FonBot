package com.bowchan.Gameprograme;

import java.util.Scanner;

//職業
class Profession {
    //轉職等級,前置職業,職業名稱
    private static final String[] Beginner = {"0", "", "初心者"};
    private static final String[] Swordsman = {"10", "初心者", "劍士"};
    private static final String[] Mage = {"8", "初心者", "法師"};
    private static final String[] SwordsmanAdvanced = {"30", "劍士", "狂戰士", "槍騎兵", "見習騎士"};
    private static final String[] MageAdvanced = {"30", "法師", "僧侶", "火毒巫師", "冰雷巫師"};

    //轉職判斷
    public  void ChangeProfessionFunction (int grade, String profession) {

        if (profession.equals("初心者")) {
            if(grade >= 10){
                System.out.println("可轉職成" + Swordsman[2] + " 與" + Mage[2] + "請選擇轉職對象:");
                Scanner sc = new Scanner(System.in, "big5");
                switch (sc.next()) {
                    case "劍士": ChangeProfessionFunction1(Swordsman);break;
                    case "法師": ChangeProfessionFunction1(Mage);break;
                    case "取消": return;
                    default:System.out.println("無此職業");
                }
            } else {
                System.out.println("等級不足");
            }
            
        } else {
            switch (profession) {
                case "劍士": if (grade >= Integer.valueOf(SwordsmanAdvanced[0])) {ChangeProfessionFunction1(SwordsmanAdvanced);} else {System.out.println("等級不足");} break;
                case "法師": if (grade >= Integer.valueOf(MageAdvanced[0])) {ChangeProfessionFunction1(MageAdvanced);} else {System.out.println("等級不足");} break;
                default: System.out.println("無可轉職職業"); break;
            }
        }

        
    }

    //轉職
    private static void ChangeProfessionFunction1 (String[] Profession){

        PlayerInformation playerInformation = new PlayerInformation();

        if (Profession.length <= 3) {
            playerInformation.changePlayerInformation("prof", Profession[2]);
        } else {
            System.out.println("輸入你想二轉的職業");
            
            for (int i = 2; i < Profession.length; i++) {
                System.out.println((i - 1) + "." + Profession[i]);
            }

            Scanner sc = new Scanner(System.in, "big5");
            String n = sc.next();

            if (n.equals("取消")){
                return;
            }

            for (int i = 2; i < Profession.length; i++) {
                if (Integer.valueOf(n) == i - 1) {
                    playerInformation.changePlayerInformation("prof", Profession[i]);
                    return;
                }
                if (n.equals(Profession[i])){
                    playerInformation.changePlayerInformation("prof", Profession[i]);
                    return;
                }
            }
        }

    }

    //判斷進階職業類型回傳職業類型
    public String getprofession(String profession){

        for (String i : SwordsmanAdvanced) {
            if (i.equals(profession)){
                return SwordsmanAdvanced[1];
            }
        }

        for (String i : MageAdvanced) {
            if (i.equals(profession)){
                return MageAdvanced[1];
            }
        }

        return new String();
    }
}

//技能
class Skill{
    
    private static final String[] Beginner = {"嫩寶丟擲術","疾風之步"};

    private static final String[] Swordsman = {"自身強化", "劍氣縱橫", "昇龍爆"};

    //劍士二轉通用技能
    private static final String[] SecSwordsmanGeneric = {"武器精通", "極速武器"};

    private static final String[] Berserker = {"終極攻擊", "雙連斬", "鬥氣集中"};
    private static final String[] Lancers = {"終極之劍", "烈焰之劍", "騎士密令"};
    private static final String[] TraineeKnight = {"終極之槍", "禦魔陣", "集中之槍"};
    
    private static final String[] Mage = {"魔力增幅", "魔靈彈", "魔力之盾"};

    //法師二轉通用技能
    private static final String[] SecMageGeneric = {"魔力吸收", "咒語精通"};

    private static final String[] FirePoison = {"燎原之火", "毒霧"};
    private static final String[] IceThunder = {"冰錐劍", "電閃雷鳴"};
    private static final String[] Monk = {"群體治癒", "神聖之箭"};

    //獲取技能
    public void getSkill(String n) {

        switch (n) {
            case "初心者": viewSkill(Beginner); break;
            case "劍士": viewSkill(Swordsman); break;
            case "法師": viewSkill(Mage); break;
            case "狂戰士": viewSkill(SecSwordsmanGeneric); viewSkill(Berserker); break;
            case "槍騎兵": viewSkill(SecSwordsmanGeneric); viewSkill(Lancers); break;
            case "見習騎士": viewSkill(SecSwordsmanGeneric); viewSkill(TraineeKnight); break;
            case "僧侶": viewSkill(SecMageGeneric); viewSkill(Monk); break;
            case "火毒巫師": viewSkill(SecMageGeneric); viewSkill(FirePoison); break;
            case "冰雷巫師": viewSkill(SecMageGeneric); viewSkill(IceThunder); break;
        }
        System.out.println();

    }

    public static void viewSkill(String[] n) {
        for (String i : n) {
            System.out.print(i + " ");
        }
    }
}

//怪物
class Monster{

    //怪物名稱, 血量, 經驗值
    private static final String[][] monster = {{"嫩寶", "8", "3"},
                                                {"藍寶", "15", "5"},
                                                {"紅寶", "40", "9"},
                                                {"肥肥", "75", "15"},
                                                {"姑姑寶貝", "160", "25"},
                                                {"綠姑姑", "250", "35"},
                                                {"刺姑姑", "300", "50"},
                                                {"小幽靈", "1200", "140"},
                                                {"猴子", "1500", "160"}};
    
    //存取名單
    public String[][] getmonsters(){
        
        return monster;
    }
}

//玩家資訊
class PlayerInformation{

    private static String name = "";
    private static String prof = "初心者";
    private static int grade = 1;
    //目前經驗,升級所需經驗
    private static long[] Exp = {0,0};
    private static Double Str = 12.0;
    private static Double Dex = 5.0;
    private static Double Int = 4.0;
    private static Double Luk = 4.0;
    private static Double att = 0.0;
    private static int point = 0;

    //升級後、加點後 更新攻擊力、升級所需經驗
    public void attUpdate(){

        //更新攻擊力
        switch (prof) {
            case "初心者": att = 1.5 * Str + Dex + 0.5 * Int + Luk; break;
            case "劍士": att = 2 * Str + 1 * Dex; break;
            case "法師": att = 2 * Int + 1 * Luk; break;
            default: att = Judgeprofessionsort(prof); break;
        }

        //更新升級所需經驗

        Exp[1] = grade * grade;

    }

    //判斷進階職業類型回傳攻擊力
    private static double Judgeprofessionsort(String profession) {
        
        Profession Profession = new Profession();

        switch (Profession.getprofession(profession)) {
            case "劍士": return (2 * Str + 1 * Dex) + 100;
            case "法師": return (2 * Int + 1 * Luk) + 100;
            default: return 0;
        }
    }

    //獲取玩家資訊
    public String getPlayerInformation(String n) {

        switch (n) {
            case "name": return name;
            case "prof": return prof;
            case "grade": return String.valueOf(grade);
            case "Str": return String.valueOf(Str);
            case "Dex": return String.valueOf(Dex);
            case "Int": return String.valueOf(Int);
            case "Luk": return String.valueOf(Luk);
            case "att": return String.valueOf(att);
            case "point": return String.valueOf(point);
            default: return new String();
        }
    }

    //獲取玩家資訊
    public long[] getPlayerInformation() {
        return Exp;
    }

    //更改玩家資訊
    public void changePlayerInformation(String n, String m) {

        switch (n) {
            case "name": name = m; break;
            case "prof": prof = m; break;
            case "grade": grade = Integer.valueOf(m); break;
            case "Str": Str = Double.valueOf(m); break;
            case "Dex": Dex = Double.valueOf(m); break;
            case "Int": Int = Double.valueOf(m); break;
            case "Luk": Luk = Double.valueOf(m); break;
            case "att": att = Double.valueOf(m); break;
            case "Exp": Exp[0] = Long.valueOf(m); break;
            case "point": point = Integer.valueOf(m); break;
            default: break;
        }
    }

    //經驗值增加
    public void ExpIncrease(int x) {

        Exp[0] += x;

        while (Exp[1] <Exp [0]) {
            Exp[0] -= Exp[1];
            grade++;
            point += 5;
            System.out.println("等級提升!");
            attUpdate();

        }
    }

}


class hw7 {

    //宣告類
    private static Profession profession = new Profession();
    private static Skill skill = new Skill();
    private static Monster monster = new Monster();
    private static PlayerInformation playerInformation = new PlayerInformation();

    //主程式
    public hw7(){
        Scanner sc = new Scanner(System.in, "big5");

        System.out.println("遊戲開始之前 先告訴我的名字吧!");

        playerInformation.changePlayerInformation("name", sc.next());

        //無限回圈輸入
        while (true) {
            System.out.println("輸入執行動作:");
            System.out.println("1.打怪");
            System.out.println("2.配點");
            System.out.println("3.轉職");
            System.out.println("4.查看技能");
            System.out.println("5.查看角色資料");
            System.out.println("6.刪除角色");

            String n = sc.next();

            if (n.contains("1")||n.contains("打怪")) {
                attackmonster();
            } else if (n.contains("2")||n.contains("配點")) {
                assignPoints();
            } else if (n.contains("3")||n.contains("轉職")) {
                profession.ChangeProfessionFunction(Integer.valueOf(playerInformation.getPlayerInformation("grade")), playerInformation.getPlayerInformation("prof"));
            } else if (n.contains("4")||n.contains("查看技能")) {
                skill.getSkill(playerInformation.getPlayerInformation("prof"));
            } else if (n.contains("5")||n.contains("查看角色資料")) {
                System.out.println("姓名: " + playerInformation.getPlayerInformation("name"));
                System.out.println("職業: " + playerInformation.getPlayerInformation("prof"));
                System.out.println("等級: " + playerInformation.getPlayerInformation("grade"));
                System.out.println("經驗: (" + playerInformation.getPlayerInformation()[0] + "/" + playerInformation.getPlayerInformation()[1] + ")");
                System.out.println("力量: " + playerInformation.getPlayerInformation("Str"));
                System.out.println("敏捷: " + playerInformation.getPlayerInformation("Dex"));
                System.out.println("智力: " + playerInformation.getPlayerInformation("Int"));
                System.out.println("幸運: " + playerInformation.getPlayerInformation("Luk"));
                System.out.println("攻擊力: " + playerInformation.getPlayerInformation("att"));
            } else if (n.contains("6")||n.contains("刪除角色")) {
                break;
            }
            System.out.println();
        }
    }

    //打怪
    private static void attackmonster() {

        Scanner sc = new Scanner(System.in, "big5");
        String attmonster = "";

        playerInformation.attUpdate();

        while (true) {
            System.out.println("輸入行動:");
            System.out.println("1.選擇怪物");
            System.out.println("2.進行戰鬥");

            String n = sc.next();

            if (n.equals("1")||n.equals("選擇怪物")) {
                attmonster = choosemonster();
            } else if (n.equals("2")||n.equals("進行戰鬥")) {
                if (attmonster.equals("")){
                    System.out.println("尚未選擇攻擊目標");
                } else {
                    attmonster(attmonster);
                    break;
                }
            } else {
                System.out.println("輸入錯誤");
            }
        }
    }

    //選擇怪物
    private static String choosemonster() {

        String[][] monsterlist = monster.getmonsters();

        System.out.println("可選擇怪物: ");

        for (String[] i : monsterlist) {
            System.out.print(i[0] + " ");
        }

        System.out.println();

        Scanner sc = new Scanner(System.in, "big5");

        String n = sc.next();

        for (int i = 0; i < monster.getmonsters().length; i++) {
            if (monster.getmonsters()[i][0].equals(n)){
                return n;
            }
        }

        System.out.println("無此怪物");

        return "";
    }

    //進行戰鬥
    private static void attmonster(String n) {

        Scanner sc = new Scanner(System.in, "big5");

        String monsterinformation[] = {};

        for (int i = 0; i < monster.getmonsters().length; i++) {
            if (monster.getmonsters()[i][0].equals(n)){
                monsterinformation = monster.getmonsters()[i];
            }
        }

        int monsterHP = Integer.valueOf(monsterinformation[1]);

        while (true) {

            System.out.println("選擇行動:");
            System.out.println("1.攻擊");
            System.out.println("2.逃跑");

            String as = sc.next();

            if (as.contains("1")||as.contains("攻擊")) {

                double att = Double.valueOf(playerInformation.getPlayerInformation("att"));
                monsterHP -= att;

                System.out.println(playerInformation.getPlayerInformation("name") + "攻擊了" + monsterinformation[0]);
                System.out.println(monsterinformation[0] + "損失" + att +"點血量");

                if (monsterHP <= 0) {
                    monsterHP = Integer.valueOf(monsterinformation[1]);
                    System.out.println();
                    System.out.println("戰鬥勝利!");
                    System.out.println();
                    playerInformation.ExpIncrease(Integer.valueOf(monsterinformation[2]));
                }

            } else if (as.contains("2")||as.contains("逃跑")) {
                break;
            } else {
                System.out.println("無此選項");
            }

        }
    }

    //配點
    private static void assignPoints(){
        
        Scanner sc = new Scanner(System.in,"big5");

        while (true) {
            int n = Integer.valueOf(playerInformation.getPlayerInformation("point"));

            System.out.println("現有能力值:");
            System.out.println("力量(Str): " + playerInformation.getPlayerInformation("Str"));
            System.out.println("敏捷(Dex): " + playerInformation.getPlayerInformation("Dex"));
            System.out.println("智力(Int): " + playerInformation.getPlayerInformation("Int"));
            System.out.println("幸運(Luk): " + playerInformation.getPlayerInformation("Luk"));
            System.out.println("剩餘可分配點數: "+n);
            
            

            System.out.println("輸入點數分配方式: (ex: 分配1點在力量上面、分配3點在Luk上面)(輸入「退出」可離開介面)");

            

            String k = sc.nextLine();

            if (k.contains("力量") || k.contains("Str")){
                int x = Integer.valueOf(k.substring(k.indexOf("配")+1,k.indexOf("點")));
                
                if (n != 0 && x<=n){
                    playerInformation.changePlayerInformation("Str", String.valueOf(x + Double.valueOf(playerInformation.getPlayerInformation("Str"))));
                    playerInformation.changePlayerInformation("point", String.valueOf(n - x));
                } else {
                    System.out.println("點數不足");
                }
            } else if (k.contains("敏捷") || k.contains("Dex")){
                int x = Integer.valueOf(k.substring(k.indexOf("配")+1,k.indexOf("點")));
                
                if (n != 0 && x<=n){
                    playerInformation.changePlayerInformation("Dex", String.valueOf(x + Double.valueOf(playerInformation.getPlayerInformation("Dex"))));
                    playerInformation.changePlayerInformation("point", String.valueOf(n - x));
                } else {
                    System.out.println("點數不足");
                }
            } else if (k.contains("智力") || k.contains("Int")){
                int x = Integer.valueOf(k.substring(k.indexOf("配")+1,k.indexOf("點")));
                
                if (n != 0 && x<=n){
                    playerInformation.changePlayerInformation("Int", String.valueOf(x + Double.valueOf(playerInformation.getPlayerInformation("Int"))));
                    playerInformation.changePlayerInformation("point", String.valueOf(n - x));
                } else {
                    System.out.println("點數不足");
                }
            } else if (k.contains("幸運") || k.contains("Luk")){
                int x = Integer.valueOf(k.substring(k.indexOf("配")+1,k.indexOf("點")));
                
                if (n != 0 && x<=n){
                    playerInformation.changePlayerInformation("Luk", String.valueOf(x + Double.valueOf(playerInformation.getPlayerInformation("Luk"))));
                    playerInformation.changePlayerInformation("point", String.valueOf(n - x));
                } else {
                    System.out.println("點數不足");
                }
            } else if (k.equals("退出")){
                break;
            } else {
                System.out.println("輸入錯誤");
            }
            playerInformation.attUpdate();
        }
    } 

}