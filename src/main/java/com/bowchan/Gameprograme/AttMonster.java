package com.bowchan.Gameprograme;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class AttMonster {
    //name blood Exp
    private static ArrayList<ArrayList<String>> monsterList;
    //area monster
    private static ArrayList<ArrayList> mapList;
    private static ArrayList<ArrayList<String>> fighting;
    private static String n = "";
    public AttMonster(String area){
        monsterList = new ArrayList<>();
        mapList = new ArrayList<>();
        fighting = new ArrayList<>();
        n = area;
        File file;
        try {
            file = new File("GameInformation", "monsterInformation");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String monsterText = sc.nextLine();
                Scanner sc2 = new Scanner(monsterText);
                ArrayList<String> mon = new ArrayList<>();
                while (sc2.hasNext()){
                    mon.add(sc2.next());
                }
                monsterList.add(mon);
            }

            file = new File("GameInformation", "Map");
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                ArrayList k =new ArrayList();
                String mapText = sc.nextLine();
                k.add(mapText.substring(0,mapText.indexOf(" ")));
                mapText = mapText.substring(mapText.indexOf(" ")+1);
                //System.out.println(mapText);
                Scanner sc2 = new Scanner(mapText);
                ArrayList<ArrayList<String>> mapMonsterLevel = new ArrayList<>();
                ArrayList<String> mapMonster = new ArrayList<>();
                while (sc2.hasNext()){
                    String value = sc2.next();
                    if(value.contains(":")){
                        if(mapMonster.isEmpty()){
                            continue;
                        }
                        mapMonsterLevel.add(mapMonster);
                        mapMonster = new ArrayList<>();
                    } else {
                        mapMonster.add(value);
                    }
                    if(sc2.hasNext()==false){
                        mapMonsterLevel.add(mapMonster);
                        mapMonster = new ArrayList<>();
                    }

                }
                //System.out.println(mapMonsterLevel);
                k.add(mapMonsterLevel);
                mapList.add(k);
            }
            //System.out.println(mapList);
            //System.out.println(monsterList);

            file = new File("GameInformation", "Fighting");
            sc = new Scanner(file);
            while (sc.hasNextLine()){
                String fightingMansion = sc.nextLine();
                Scanner sc2 = new Scanner(fightingMansion);
                ArrayList<String> fightingList = new ArrayList<>();
                while (sc2.hasNext()){
                    fightingList.add(sc2.next());
                }
                if(fightingList.isEmpty()){
                    continue;
                }
                fighting.add(fightingList);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String randomMonster(){
        //System.out.println(n);
        for (ArrayList i : mapList){
            //System.out.println(i);
            if(String.valueOf(i.get(0)).equals(n)){
                //System.out.println(i);
                ArrayList<ArrayList<String>> k = (ArrayList<ArrayList<String>>) i.get(1);
                //System.out.println(k);
                int x = 0;
                for (int j = 1; j < k.size()+1; j++){
                    x=x+j;
                    //System.out.println(x);
                }
                Double y = 100.0 / x;
                //System.out.println(y);
                int no = (int)(Math.random()*1000)%100;
                //System.out.println(no);
                for (int j = 0; j < k.size()-1; j++){
                    //System.out.println(100-y*(j+1));
                    if (no>100-y*(j+1)){
                        return k.get(k.size()-1-j).get((int)(Math.random()*1000)%(k.get(k.size()-1-j).size()));
                    }
                }
                return k.get(0).get((int)(Math.random()*1000)%(k.get(0).size()));
            }
        }
        return "";
    }
    public ArrayList<String> foundMonster(String userTag,String monster){
        ArrayList<String> monsterInformation = new ArrayList<>();
        monsterInformation.add(userTag);
        for (ArrayList<String> i : monsterList){
            if (i.get(0).equals(monster)){
                for (String j : i){
                    monsterInformation.add(j);
                }
                break;
            }
        }
        //System.out.println(monsterInformation);
        //System.out.println(monster);
        //System.out.println(monsterList);
        fighting.add(monsterInformation);

        updateFighting();
        return monsterInformation;
    }
    public void updateFighting(){
        File file = new File("GameInformation","Fighting");
        try {
            FileWriter fileWriter = new FileWriter(file,false);
            //System.out.println(fighting + "123");
            for (ArrayList<String> i : fighting){
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
    public ArrayList<ArrayList<String>> getFighting(String userTag ,String monster){
        //System.out.println(fighting);
        ArrayList<ArrayList<String>> yourMonster = new ArrayList<>();
        int kk = 0;
        if (fighting.isEmpty()){
            return yourMonster;
        }
        //System.out.println(fighting);
        for (ArrayList<String> i :fighting){
            if(i.get(0).equals(userTag)){
                ArrayList<String> arrayList = new ArrayList<>();
                for(String j : i){
                    if(j.equals(userTag)){
                        continue;
                    }
                    arrayList.add(j);
                }
                yourMonster.add(arrayList);
            }
            kk++;
        }
        //System.out.println(yourMonster);
        return yourMonster;
    }
    public void changeFighting(String userTag,ArrayList<ArrayList<String>> monster){
        int no = 0;
        for (int i = 0; i < fighting.size(); i++){
            if (fighting.get(no).get(0).equals(userTag)) {
                fighting.get(i).set(0,userTag);
                for (int j = 0; j <monster.get(no).size(); j++){
                    fighting.get(i).set(j+1,monster.get(no).get(j));
                }
                //System.out.println(fighting.get(i).get(2));
                if (Double.valueOf(fighting.get(i).get(2)) < 1){
                    fighting.remove(i);

                }

                no++;
            }
        }

        updateFighting();
    }
    public boolean run(String userTag){
        int x = 0;
        for (int i = 0; i < fighting.size(); i++){
            if(fighting.get(i).get(0).equals(userTag)){
                fighting.remove(i);
                x++;
            }
        }

        if (x!=0){
            updateFighting();
            return true;
        }else {
            return false;
        }
    }
}
