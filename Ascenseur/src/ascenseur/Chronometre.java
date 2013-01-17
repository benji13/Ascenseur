package ascenseur;

/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.
*/


public class Chronometre extends Thread{

		private int xtemps;
		private long startTime;
		private long stopTime;
		private long actuTime;
		private boolean running;
		private Seconde sec;
		
		
		public Chronometre(int xtemps, Seconde sec) {
			// TODO Auto-generated constructor stub
			this.xtemps = xtemps;
			this.startTime = 0;
			this.stopTime = 0;
			this.running = false;
			this.sec = sec;
			this.setPriority(MAX_PRIORITY);
		}
		public long getActuTime() {
			return actuTime;
		}
		public void setActuTime(long actuTime) {
			this.actuTime = actuTime;
		}
		public void run (){
			this.startChrono();
			chronometrer();
		}
		
		public boolean isRunning() {
			return running;
		}

		
		public void startChrono() {
		    this.startTime = System.currentTimeMillis();
		    this.actuTime = 0;
		    this.running = true;
		}
		
		
		public void stopChrono() {
		    this.stopTime = System.currentTimeMillis();
		    this.running = false;
		}
		
		
		//elaspsed time in seconds
		public void getTempsEcouleSecs() {
		    long ecoule;
		    if (running) {
		         ecoule = (System.currentTimeMillis() - startTime);
		    }
		    else {
		        ecoule = (stopTime - startTime);
		    }
		    
		    if(this.actuTime!=((ecoule*xtemps)/1000) && ((ecoule*xtemps) % 1000) == 0){
		    	sec.declenchementSeconde();
		    	this.actuTime = (ecoule*xtemps)/1000;
		    }
		}
		
		public void chronometrer(){
			for(;;){
				this.getTempsEcouleSecs();
			}
		}
		
}

