/*
Readers-writers problem solution in an airline reservation system. Readers want to read the current situation,
writers want to book a place.
If write wants to read, no new reader will want to read. Writer will read, if no writing, no reading is being done.
To make the situation more realistic, I have put some sleep in threads, so Writer can interfer before all readers 
finished reading.
*/
import java.util.ArrayList;

public class Main {
	

    public static void main(String[] args) {
    	System.out.println("Program starts");
    	ArrayList<Reader> listR = new ArrayList<Reader>();
		ArrayList<Writer> listW = new ArrayList<Writer>();
        ReaderWriter rw = new ReaderWriter();
        Airport airport = new Airport(5);
        for(int i=0; i < 3; i++){
    		listR.add(new Reader(rw, airport));
        }
    	for(int i=0; i < 2; i++){
    		listW.add(new Writer(rw, airport));
        }
        for(int i=0; i < listR.size(); i++){
           	listR.get(i).start();
        };
        for(int i=0; i < listW.size(); i++){
       		listW.get(i).start();
        };
    }
}

class Reader extends Thread {
	Airport airport;
	ReaderWriter rw;

	Reader(ReaderWriter rw, Airport airport){
		this.rw = rw;
		this.airport = airport;
	}

	public void run(){
		int i=0, freeSpace = -1;
		for(int j=0; j<6; j++){
			rw.prepareReading();
			freeSpace = airport.getFreeSpaceToParis();
			System.out.println("Free spaces after read: " + freeSpace + " Thread: " + Thread.currentThread());
			rw.afterRead();
			
			try{
				sleep((int)(Math.random()+1));
			}
			catch(InterruptedException e){
				System.out.println("IE error");

			}
			
			if(freeSpace == 0){
				break;
			}
		}
		System.out.println("reader stopped");
	}
}

class Writer extends Thread {
	ReaderWriter rw;
	Airport airport;

	Writer(ReaderWriter rw, Airport airport){
		this.rw = rw;
		this.airport = airport;
	}

	public void run(){
		int i = 0;

		for(int j=0; j<6; j++){
			rw.prepareWriting();
			airport.bookPlace();
			rw.afterWrite();
			try{
				sleep((int)(Math.random()+1));
			}
			catch(InterruptedException e){
				System.out.println("IE error");

			}
			if(airport.getFreeSpaceToParis() == 0)
				break;
		}
	System.out.println("writer stopped");
	}
}

class ReaderWriter{
	int readers = 0;
	int waitingReaders = 0, waitingWriters = 0;
	boolean writing = false;

	public synchronized void prepareReading(){
		System.out.println("prepareReading");
		while(writing || waitingWriters > 0){
			try{
				 System.out.println("wait reader");
				this.wait();
			}
			catch(InterruptedException ie){
				System.out.println("error");
			}
		}
		++readers;
		++waitingReaders;
	}

	public synchronized void afterRead(){
		System.out.println("afterRead");
		--readers;
		--waitingReaders;
		if(readers == 0){
			++waitingWriters;
			this.notifyAll();
		}
	}

	public synchronized void prepareWriting(){
		System.out.println("prepareWriting");
		while(readers > 0 || writing) {
			try{
				// System.out.println("wait writer");
				this.wait();
			}
			catch(InterruptedException ie){
				System.out.println("error");
			}
		}
			writing = true;
	}

	public synchronized void afterWrite(){
		System.out.println("afterWrite");
		if(waitingWriters>0)
			--waitingWriters;
  		writing = false;
		this.notifyAll();
	}

}

class Airport {
	private int freeSpaceToParis;

	public Airport(int number){
		freeSpaceToParis = number;
	}

	public int getFreeSpaceToParis() {
		return freeSpaceToParis;
	}

	public void bookPlace() {
		if(freeSpaceToParis > 0)	
			--freeSpaceToParis;
		else System.out.println("No free space left!");
	}
}