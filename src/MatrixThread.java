class MatrixThread extends Thread{
  private final int threadID;

  MatrixThread(int idNum){
    threadID = idNum;
  }

  @Override
  public void run(){
    System.out.println("Hello from thread " + threadID);
  }//run
}//class
