package com.iii.eeit124.adopt.others;

import java.util.Date;
import java.util.TimerTask;

public class CancelAfterOneDayTask extends TimerTask{

	@Override
	public void run() {
		System.out.println("Task 執行時間：" + new Date());
		
	}

}
