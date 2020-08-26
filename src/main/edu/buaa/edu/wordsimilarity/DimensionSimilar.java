package edu.buaa.edu.wordsimilarity;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class DimensionSimilar {

    private String[][] content;

    public void loadTriple(){
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            File file = new File("E:\\third\\event network\\triple.txt");
            InputStreamReader input = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8);
            BufferedReader bf = new BufferedReader(input);
            // ���ж�ȡ�ַ���
            String str;
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // ��ArrayList�д洢���ַ������д���
        int length = arrayList.size();
        int width = arrayList.get(0).split(",").length;
        String[][] array = new String[length][width];
        for (int i = 0; i < length; i++) {
            //                String s = arrayList.get(i).split(",")[j];
            String s = arrayList.get(i).replace("[","").replace("]","").replace("'","");
            System.arraycopy(s.split(","), 0, array[i], 0, width);
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
        this.content=array;
    }

    public void dimensionSubject(){

        similar2("subject1",0);
    }

    public void dimensionPredicate(){
        similar2("predicate",1);
    }

    public void dimensionObject(){
        similar2("object",2);
    }

    public void similar2(String type,int position){
        Map<Integer,Set<String>> subject=new HashMap<>();
//        Set<String> collect = new HashSet<>();
        int[] group = new int[content.length];
        for (int i=0;i<group.length;i++){
            group[i]=i;
        }
        for (int i=0;i<content.length;i++){
            Set<String> collect = new HashSet<>();
            //�ѱ�����
//            if (group[i]<i){
////                continue;
//                collect=subject.get(group[i]);
//            }
            /*���Լ���ʼ��д��һ��set��ɸ�����ڴʵ����*/
            for (int j=i;j<content.length;j++){
                double sim;
//                ���ѷ���Ĳ����뿼��
                if (group[j]<j){
                    continue;
                }
                if (!content[i][position].equals(content[j][position])){
                    sim=WordSimilarity.simWord(content[i][position],content[j][position]);
                }else sim=1;
                if (sim>0.9){
                    group[j]=group[i];
                    collect.add(content[j][0]+content[j][1]+content[j][2]);
                }
            }
//            subject.add(collect);
            if (!collect.isEmpty()){
//                continue;
                if (group[i]<i){
                    collect.addAll(subject.get(group[i]));
                }
                subject.put(group[i],collect);
                System.out.println(collect);
            }
//            System.out.println(collect);
//            subject.put(group[i],collect);
//            this.output(type,collect);
//            collect.clear();
        }
        /*���*/
        for (Map.Entry<Integer, Set<String>> entry : subject.entrySet()){
            this.output(type,entry.getValue());
            System.out.println(entry.getValue().size());
        }
    }

    public void similar(String type,int position){
//        List<Set<String>> subject=new ArrayList<>();
        Set<String> collect = new HashSet<>();
        int[] group = new int[content.length];
        for (int i=0;i<group.length;i++){
            group[i]=i;
        }
        for (int i=0;i<content.length;i++){
            //�ѱ�����
            if (group[i]<i){
                continue;
            }
            /*���Լ���ʼ��д��һ��set��ɸ�����ڴʵ����*/
            for (int j=i;j<content.length;j++){
                double sim;
                /*���ѷ���Ĳ����뿼��
                if (group[j]<j){
                    continue;
                }*/
                if (!content[i][position].equals(content[j][position])){
                    sim=WordSimilarity.simWord(content[i][position],content[j][position]);
                }else sim=1;
                if (sim>0.9){
                    group[j]=i;
                    collect.add(content[j][0]+content[j][1]+content[j][2]);
                }
            }
//            subject.add(collect);
            System.out.println(collect);
            this.output(type,collect);
            collect.clear();
        }
    }

    public void output(String type,Set<String> collect){
        try {
            File file = new File("E:\\third\\event network\\"+type+".txt");
            if(!file.exists()){
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            bw.write(String.valueOf(collect));
            bw.write("\n");
            bw.close();
            System.out.println("finish");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DimensionSimilar d=new DimensionSimilar();
        d.loadTriple();
//        d.dimensionSubject();
        d.dimensionObject();
        d.dimensionPredicate();
    }
}
