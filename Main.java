package vv;


import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import javax.swing.JFileChooser;

public class Main{
    public static void main(String[] args){
    	DecimalFormat df = new DecimalFormat("###,###,###,###");
    	File file = null;
    	FileReader fr = null;
    	FileWriter fw = null;
    	JFileChooser jfc = new JFileChooser();
    	BufferedReader br = null;
    	BufferedWriter bw = null;
    	
    	
    	HashMap<Double, Double>
    	Mmap = new HashMap<Double, Double>(); // 거래가
    	
    	List<HashMap<Double, Double>>
    	Mlist = new ArrayList<HashMap<Double,Double>>(); // 거래가 List
    	
    	HashMap<String, List<HashMap<Double, Double>>>
    	Tmap = new HashMap<String, List<HashMap<Double,Double>>>(); // 시간, 거래가 List
    	
    	List<HashMap<String,List<HashMap<Double,Double>>>>
    	Tlist = new ArrayList<HashMap<String,List<HashMap<Double,Double>>>>(); // 시간List의 거래가List
    	
    	HashMap<String, List<HashMap<String, List<HashMap<Double, Double>>>>>
    	Monster = new HashMap<String, List<HashMap<String,List<HashMap<Double,Double>>>>>(); // 코드별 시간List의 거래가List 
    	
    	
        try {
        	jfc.showOpenDialog(null);
        	file = jfc.getSelectedFile();
			fr = new FileReader(file);
			br = new BufferedReader(fr);
			fw = new FileWriter("result.txt");
			bw = new BufferedWriter(fw);
		
			String readLine = null;
			
			//거래가  최초, 최고, 최저, 최종
			double max=0, first=0, min=0, last=0, money=0;
			
			
			HashMap<Double, Double> tmap = new HashMap<Double, Double>();
			HashMap<String, HashMap<Double, Double>> Hmap = new HashMap<String, HashMap<Double,Double>>();
			while((readLine = br.readLine())!=null) {
				String time = readLine.substring(0,8).replaceAll(":", "");//시간
				String codenum = readLine.substring(15,21);//종목코드
				String num = readLine.substring(21,26);//일련번호
				double pay =Double.parseDouble(readLine.substring(26,35));//거래가
				double ea = Double.parseDouble(readLine.substring(35));//수량
				
				money=pay*ea;
				
				int HMS = Integer.parseInt(time);
				if(time!=null) {
					if(HMS>=143000) {
						//1500~1430
						time = "14:30";
					}else if(HMS>=140000) {
						//1430~1330
						time = "14:00";
					}else if(HMS>=133000) {
						//1400~1330
						time = "13:30";
					}else if(HMS>=130000) {
						//1300~1230
						time = "13:00";
					}else if(HMS>=123000){
						//1230~1200
						time = "12:30";
					}
					tmap.put(money, money); //시간마다,돈이담김
				}
				
				Hmap.put(codenum, tmap);//코드별로, 시간별로, 돈 
				money=0;
			}
			
			/*
			while((readLine = br.readLine())!=null) {
				String time = readLine.substring(0,8).replaceAll(":", "");
				String code = readLine.substring(15,21);
				String num = readLine.substring(21,26);
				String pay = readLine.substring(26,35);
				String ea = readLine.substring(35);
				int tt = Integer.parseInt(time);
				
				if(code.matches("")) {
					
				}
				
				if(tt<=130000) {
					//12시30분~13시
					bw1.write(readLine);
					bw1.newLine();
				}else if(tt<=133000) {
					//13시~30분
					bw2.write(readLine);
					bw2.newLine();
				}else if(tt<=140000) {
					//13시30분~14시
					bw3.write(readLine);
					bw3.newLine();
				}else if(tt<=143000) {
					//14시~30분
					bw4.write(readLine);
					bw4.newLine();
				}else {
					//12시~30분
					bw5.write(readLine);
					bw5.newLine();
				}
				bw.write(readLine);
				bw.newLine();
				
			}
			bw.flush();
			bw1.flush();
			bw2.flush();
			bw3.flush();
			bw4.flush();
			bw5.flush();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		}
        
//        System.out.println(file);
    }
}