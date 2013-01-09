package ascenseur;

/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.
*/


public class Chronometre {

		private int xtemps;
		private long startTime;
		private long stopTime;
		private boolean running;
		
		public Chronometre(int xtemps) {
			// TODO Auto-generated constructor stub
			this.xtemps = xtemps;
			this.startTime = 0;
			this.stopTime = 0;
			this.running = false;
		}
		
		public boolean isRunning() {
			return running;
		}

		
		public void start() {
		    this.startTime = System.currentTimeMillis();
		    this.running = true;
		}
		
		
		public void stop() {
		    this.stopTime = System.currentTimeMillis();
		    this.running = false;
		}
		
		
		//elaspsed time in seconds
		public long getTempsEcouleSecs() {
		    long ecoule;
		    if (running) {
		         ecoule = (System.currentTimeMillis() - startTime);
		    }
		    else {
		        ecoule = (stopTime - startTime);
		    }
		    return (ecoule*xtemps)/1000;
		}
}

