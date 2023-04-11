package com.bowchan.Gameprograme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class GameMain {
    //tag name prof grade Exp Str Dex Int Luk att point
    public ArrayList<String> yourInformation;
    private static ArrayList<ArrayList<String>> allInformation;
    public static int n;
    public GameMain(String name) {
        allInformation = new ArrayList<>();
        yourInformation = new ArrayList<>();
        n=0;

        File file = new File("GameInformation", "playerInformation");
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String informationtext = sc.nextLine();
                Scanner sctext = new Scanner(informationtext);
                ArrayList<String> information = new ArrayList<>();
                while (sctext.hasNext()) {
                    String text = sctext.next();
                    information.add(text);
                }

                if (information.isEmpty()) {
                    continue;
                }
                if (information.get(0).equals(name)) {
                    yourInformation = information;
                }
                allInformation.add(information);
            }
            if (yourInformation.isEmpty()) {

                yourInformation.add(name);//tag 1
                yourInformation.add(name.substring(0, name.indexOf("#")));//name 2
                yourInformation.add("初心者");//prof 3
                yourInformation.add("1");//grade 4
                yourInformation.add("0");//Exp 5
                yourInformation.add("1");//Str 6
                yourInformation.add("1");//Dex 7
                yourInformation.add("1");//Int 8
                yourInformation.add("1");//Luk 9
                yourInformation.add("0");//att 10
                yourInformation.add("0");//point 11
                yourInformation.add("0");//coin 12
                yourInformation.add("主城");//位置 13
                yourInformation.add("主城");//位置 14

                allInformation.add(yourInformation);

            }
            getUserListArea(name);
            attUpdate();

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
        public void attUpdate(){
         String k="";
        //更新攻擊力
        switch (allInformation.get(n).get(2)) {
            case "初心者": k= String.valueOf(1.5 * Integer.valueOf(allInformation.get(n).get(5)) + Integer.valueOf(allInformation.get(n).get(6)) + 0.5 * Integer.valueOf(allInformation.get(n).get(7)) + Integer.valueOf(allInformation.get(n).get(8))); break;
            case "劍士": k = String.valueOf(2 * Integer.valueOf(allInformation.get(n).get(5)) + 1 * Integer.valueOf(allInformation.get(n).get(6))); break;
            case "法師": k = String.valueOf(2 * Integer.valueOf(allInformation.get(n).get(7)) + 1 * Integer.valueOf(allInformation.get(n).get(8))); break;
            default: k = JudgeproFessionsort(); break;
        }
        allInformation.get(n).set(9,k);

    }
    private static String JudgeproFessionsort() {

        ProfessionMain professionMain = new ProfessionMain();

        switch (professionMain.getBeforeProfession(allInformation.get(n).get(0))) {
            case "劍士": return String.valueOf(2 * Integer.valueOf(allInformation.get(n).get(5)) + 1 * Integer.valueOf(allInformation.get(n).get(6))+100);
            case "法師": return String.valueOf(2 * Integer.valueOf(allInformation.get(n).get(7)) + 1 * Integer.valueOf(allInformation.get(n).get(8))+100);
            default: return "";
        }
    }
    public ArrayList<ArrayList<String>> viewPlayInformation(){
        return allInformation;
    }
    public int viewPlayInformationSite(){
        return n;
    }
    public void textUpdate(){

        try {
            File file = new File("GameInformation","playerInformation");
            FileWriter fileWriter = new FileWriter(file,false);
            for (ArrayList<String> i : allInformation){
                for (String j : i){
                    fileWriter.write(j+" ");
                }
                fileWriter.write("\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void changePlayerInformation(String object,String value){
        for (int i = 0; i<allInformation.get(0).size();i++){
            if(allInformation.get(0).get(i).equals(object)){
                allInformation.get(n).set(i,value);

                textUpdate();
                return;
            }
        }
    }
    public void LevelUp(String userTag,String Exp){
        for (int i = 0; i < allInformation.size(); i++){
            if (allInformation.get(i).get(0).equals(userTag)){
                int exp = Integer.valueOf(allInformation.get(i).get(4));
                exp += Integer.valueOf(Exp);
                allInformation.get(i).set(4,String.valueOf(exp));
                break;
            }
        }
        textUpdate();
    }
    public ArrayList<Integer> gradeUpdate(String userTag){
        ArrayList<Integer> arrayList = new ArrayList();
        int x = 0;
        for (int i = 0; i < allInformation.size(); i++){
            if (allInformation.get(i).get(0).equals(userTag)){
                int exp = Integer.valueOf(allInformation.get(i).get(4));
                int garde = Integer.valueOf(allInformation.get(i).get(3));
                int point = Integer.valueOf(allInformation.get(i).get(10));
                while (exp >= Math.pow(garde,2)+garde*10){
                    exp -= Math.pow(garde,2)+garde*10;
                    garde++;
                    point +=5;
                    arrayList.add(garde);
                    x++;
                }
                allInformation.get(i).set(3,String.valueOf(garde));
                allInformation.get(i).set(4,String.valueOf(exp));
                allInformation.get(i).set(10,String.valueOf(point));
            }
        }
        textUpdate();
        return arrayList;
    }

    public ArrayList<Integer> addPoint(String userTag,String pointName,int pointValue){
        for (int i = 0; i < allInformation.size(); i++){
            if (allInformation.get(i).get(0).equals(userTag)){
                for (int j = 0; j < allInformation.get(0).size(); j++){
                    if (allInformation.get(0).get(j).equals(pointName)){
                        int point = Integer.valueOf(allInformation.get(i).get(10));
                        if (point < pointValue){
                            return new ArrayList<>();
                        }
                        point -= pointValue;
                        pointValue += Integer.valueOf(allInformation.get(i).get(j));
                        allInformation.get(i).set(j,String.valueOf(pointValue));
                        allInformation.get(i).set(10,String.valueOf(point));
                        attUpdate();
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(pointValue);
                        arrayList.add(point);
                        return arrayList;
                    }
                }
            }
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(-1);
        return arrayList;
    }

    public void changeName(String userTag,String name){
        for (int i = 0; i < allInformation.size(); i++){
            if (allInformation.get(i).get(0).equals(userTag)){
                allInformation.get(i).set(1,name);
                return;
            }
        }
    }
    public void getUserListArea(String userTag){
        for (int i = 0; i < allInformation.size(); i++) {
            if (allInformation.get(i).get(0).equals(userTag)) {
                n = i;
            }
        }

    }
}
