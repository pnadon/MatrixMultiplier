class MatrixThreadCell extends Thread{
  private final double row[];
  private final double column[];
  private double res;

  MatrixThreadCell(double[] rowIn, double[] columnIn){
    row = rowIn;
    column = columnIn;
  }

  @Override
  public void run(){
    for( int i = 0; i < row.length; i++){
      res += row[i]*column[i];
    }
  }//run

  public double getRes(){
    return res;
  }
}//class
