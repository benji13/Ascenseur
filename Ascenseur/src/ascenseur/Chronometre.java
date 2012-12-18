package ascenseur;

/*
Copyright (c) 2005, Corey Goldberg

StopWatch.java is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 2 of the License, or
(at your option) any later version.
*/


public class Chronometre {

		private long startTime = 0;
		private long stopTime = 0;
		private boolean running = false;
		
		
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
		
		
		//elaspsed time in milliseconds
		public long getTempsEcoule() {
		    long ecoule;
		    if (running) {
		         ecoule = (System.currentTimeMillis() - startTime);
		    }
		    else {
		        ecoule = (stopTime - startTime);
		    }
		    return ecoule;
		}
		
		
		//elaspsed time in seconds
		public long getTempsEcouleSecs() {
		    long ecoule;
		    if (running) {
		        ecoule = ((System.currentTimeMillis() - startTime) / 1000);
		    }
		    else {
		        ecoule = ((stopTime - startTime) / 1000);
		    }
		    return ecoule;
	}
}

