package com.bowchan.Gameprograme;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ProfessionMain {
    public ArrayList<ArrayList<String>> profession;
    public ArrayList<ArrayList<String>> genericSkill;
    public ProfessionMain(){
        profession = new ArrayList<>();
        genericSkill = new ArrayList<>();
        try {
            File file = new File("GameInformation","Profession");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()){
                String text = sc.nextLine();
                if(text.contains("職業類型")||text.contains("說明")){
                    continue;
                }
                Scanner sc2 = new Scanner(text);
                ArrayList<String> arrayList = new ArrayList<>();
                while (sc2.hasNext()){
                    arrayList.add(sc2.next());
                }
                if(text.contains("通用技能")){
                    break;
                }
                profession.add(arrayList);
            }
            while (sc.hasNextLine()){
                String text = sc.nextLine();
                if(text.contains("職業類型")||text.contains("說明")){
                    continue;
                }
                Scanner sc2 = new Scanner(text);
                ArrayList<String> arrayList = new ArrayList<>();
                while (sc2.hasNext()){
                    arrayList.add(sc2.next());
                }
                genericSkill.add(arrayList);
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public String getBeforeProfession(String n) {

        for (ArrayList<String> i : profession){
            if(i.get(0).equals(n)){
                return i.get(2);
            }
        }
        return  "";
    }
}
