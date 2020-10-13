package jim;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class aa123 {
			public static void main(String[] args) throws IOException  {
				
				 try {
					   InputStreamReader isr = new InputStreamReader(new FileInputStream("c:/a.csv"));//檔案讀取路徑
//					   InputStreamReader isr = new InputStreamReader(new FileInputStream("c:/Users/Student/Desktop/Project2/Project2_CatProduct/white.csv"));//檔案讀取路徑
					   BufferedReader reader = new BufferedReader(isr);
//					   BufferedWriter bw = new BufferedWriter(new FileWriter("D://file_output.csv"));//檔案輸出路徑
					   String line = null;
					   java.util.List<String>  list=new ArrayList<>();
					   while((line=reader.readLine())!=null){
					      String item[] = line.split(",");
					      /** 讀取 **/
					      list.add(item[0].trim());
					      //可自行變化成存入陣列或arrayList方便之後存取
				
//					      String[] dataArray = {item[0].trim(),item[1].trim(),item[2].trim()};
//					      System.out.print(dataArray[0]+"\t"+ dataArray[1]+"\t"+ dataArray[2]+"\n"); 
					   
					   }

					   for (int i=0;i<list.size();i++) {
							//Download 圖片
							URL url = new URL(list.get(i));
						
							try(InputStream is = url.openStream();
								BufferedInputStream bis = new BufferedInputStream(is);
								
								FileOutputStream fos = new FileOutputStream("c:\\iii\\w"+(i+1)+".jpg");
								BufferedOutputStream bos = new BufferedOutputStream(fos);
							){
								//假設下載圖片共1026 Bytes
								byte[] buffer = new byte[1024];
								int length;
								while ((length = bis.read(buffer)) != -1) {
									bos.write(buffer,0,length);
								}
								System.out.println("finish");
							}catch (IOException e) {
								e.printStackTrace();
							}
						   
						   
						   
					      System.out.println(list.get(i));
					    } 
//					   bw.close();
					  } catch (FileNotFoundException e) {
					   // TODO Auto-generated catch block
					   e.printStackTrace();
					  }
					   
				 

				

			}
}
