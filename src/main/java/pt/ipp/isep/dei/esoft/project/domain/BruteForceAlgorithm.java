package pt.ipp.isep.dei.esoft.project.domain;

import java.util.ArrayList;
import java.util.List;

public class BruteForceAlgorithm {

    private double inTime;

    private int difference=Integer.MAX_VALUE;

    public int getDifference() {
        return difference;
    }

    public Pair<List<Pair<String,Integer>>,List<Pair<String,Integer>>> start(List<Pair<String,Integer>> list){
        inTime=System.currentTimeMillis();
        Pair<List<Pair<String,Integer>>,List<Pair<String,Integer>>> list1=divide(list);
        inTime=System.currentTimeMillis() - inTime;
        return list1;
    }

    public double getInTime() {
        return inTime;
    }

    public Pair<List<Pair<String,Integer>>,List<Pair<String,Integer>>> divide(List<Pair<String,Integer>> list){
        List<Pair<String,Integer>> firstList=new ArrayList<>();
        List<Pair<String,Integer>> secondList=new ArrayList<>();
        int n= list.size();
        int nTotal=(int) Math.pow(2, n) - 1;

        for (int i=1;i<nTotal;i++) {
            List<Pair<String,Integer>> first = new ArrayList<>();
            List<Pair<String,Integer>> second = new ArrayList<>();
            int n1=0,n2=0;
            String numB = toBinary(i);
            for (int j=0;j<n;j++) {
                if(!(j>numB.length()-1)){
                    if (numB.charAt(j) == '1'  ) {
                        first.add(list.get(j));
                        n1=n1+list.get(j).getSecond();
                    } else {
                        second.add(list.get(j));
                        n2=n2+list.get(j).getSecond();
                    }
                }else {
                    second.add(list.get(j));
                    n2=n2+list.get(j).getSecond();
                }
            }
            int aux= Math.abs(n1-n2);
            if(aux<difference){
                difference=aux;
                firstList=first;
                secondList=second;
            }
        }
        return new Pair<>(firstList,secondList);
    }

    public String toBinary(int num){
        if (num == 0) {
            return "0";
        }
        StringBuilder binaryNumber = new StringBuilder();
        while (num > 0) {
            int remainder = num % 2;
            binaryNumber.append(remainder);
            num /= 2;
        }
        return binaryNumber.toString();
    }
}
