package irep;
/

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

//メインクラス
public class Main2 {
	public static void main(String[] args) {
		

		
		//フォルダのパスを取得を行うメソッド
		System.out.println("読み込むパスを入力してください");
		//コマンドラインからパスを取得
		Scanner sc = new Scanner(System.in);
		String folderPath = sc.next();

		//取得したパスが存在するかを確認するメソッド
		checkPath(folderPath);

		//検索を行う文字列を取得する
		System.out.println("検索する単語を入力してください");
		String chara = sc.next();

		sc.close();
		
		//フォルダ内のファイルを配列にして取得
		File file = new File(folderPath);
		String files[] = file.list();
		
		//ファイルの読み込みを行う
    	 for (int i=0; i<files.length; i++) {
			 //ファイル名の取得
			String fileName = files[i]; 
			
			//拡張子の判定
			if(fileName.endsWith(".txt")) {
				fileRead(folderPath,fileName,chara);
			}
		}
    	 System.out.println("処理を終了します。");
	}
	
	/*
	 *・取得したフォルダの存在判定を行うメソッド
	 *  引数
	 *  folderPath;取得したフォルダのパス
	 * 
	 */
	public static void checkPath(String folderPath) {
		
		File path = new File(folderPath);
		//パスの存在判定を行う
		if(path.exists() == false) {
			System.out.println("パスが存在しませんでした。");
			System.exit(0);
		}
	}

	/*
	 * ファイルを読み込み書き出すメソッド
	 * 引数
	 * folderPath;フォルダのパス
	 * fileName;ファイル名
	 * chara;検索を行う文字列
	 */
	public static void fileRead(String folderPath, String fileName, String chara) {
		
		int row = 0;//行数のカウント変数
		FileReader fr =null;
		BufferedReader br =null;
		
		 try {
			fr = new FileReader(folderPath + "\\" + fileName);
			br = new BufferedReader(fr);
			String line;
			
			//最終行まで繰り返す処理
			while((line = br.readLine()) !=null) {
				//行数のカウント
				row = row + 1;

				//検索する文字列の判定
				if(line.contains(chara)) {
					System.out.println(folderPath + "\\" + fileName + ";" + row +"行目");
				}
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}finally {
			try {
				br.close();
				fr.close();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
	}
}	