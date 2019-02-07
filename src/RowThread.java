class RowThread extends Thread{
  private double[] row;
  private double[][] matrix; //this value is stored as its transpose

  private double[] result;


  /** RowThread
   * processes one row of a matrix multiplication operation.
   *
   * parameters:
   * inputRow: input row
   * inputMatrix: a transposed version of the matrix to be operated on.
   *      its width must be identical to the length of inputRow.
   */

  RowThread(double[] inputRow, double[][] inputMatrix){
    row = inputRow;
    matrix = inputMatrix;
  }

  @Override
  public void run(){
    int width = row.length;
    int innerDim = matrix.length;

    double sum = 0f;
    result = new double[innerDim];

    for (int i=0; i < innerDim; i++){
      sum = 0f;
      for (int j=0; j < width; j++){
        sum += row[j] * matrix[i][j];
      }//for (row)
      result[i] = sum;
    }//for ('columns' (as rows))
  }//run

  public double[] getResult(){
    return result;
  }
}//class
